package com.feri.netpacket.provider.service.impl;

import com.feri.common.config.ElasticConfig;
import com.feri.common.vo.R;
import com.feri.netpacket.provider.dao.ProductDao;
import com.feri.netpacket.provider.dao.ProductOpLogDao;
import com.feri.netpacket.provider.entity.ProductOpLog;
import com.feri.netpacket.provider.model.Product;
import com.feri.netpacket.provider.service.ProductService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.similarity.ScriptedSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:19
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao dao;
    @Autowired
    private ElasticsearchRestTemplate restTemplate;
    @Autowired
    private ProductOpLogDao logDao;

    @Override
    public R search(String msg) {
        BoolQueryBuilder boolQuery= QueryBuilders.boolQuery();
        boolQuery.should(QueryBuilders.wildcardQuery("pname","%"+msg+"%"));
        boolQuery.should(QueryBuilders.wildcardQuery("tname","%"+msg+"%"));
        Query query= new NativeSearchQuery(boolQuery);
        SearchHits<Product> searchHits=restTemplate.search(query, Product.class);
        Set<Product> set=new HashSet<>();
        for(SearchHit<Product> s:searchHits){
            if(!set.contains(s.getContent())){
                set.add(s.getContent());
            }
        }
        return R.setOK(set);
    }

    @Override
    public R syncData() {
        //同步数据 - 定时任务 -Spring Task 默认2小时同步一次数据
        List<IndexQuery> list=new ArrayList<>();
        IndexQuery query= new IndexQueryBuilder().build();
        List<ProductOpLog> logsdel=logDao.findByFlag(3);
        List<Product> dels=new ArrayList<>(logsdel.size());
//        for(ProductOpLog l:logsdel){
//            dels.add(new Product(l.getPid()));
//        }
        //jdk8 新特性 Stream流
        logsdel.stream().forEach((l)->{dels.add(new Product(l.getPid()));});
        //批量删除 ES服务器数据
        dao.deleteAll(dels);
        //批量修改
        List<Product> listops=new ArrayList<>();
        List<ProductOpLog> logsupd=logDao.findByFlag(2);
        List<ProductOpLog> logsadd=logDao.findByFlag(1);

        //批量新增
       for(ProductOpLog l:logsupd){
           listops.add(new Product(l.getPid(),l.getPname(),l.getTname(),l.getTid()));
       }
        for(ProductOpLog l:logsadd){
            listops.add(new Product(l.getPid(),l.getPname(),l.getTname(),l.getTid()));
        }
        dao.saveAll(listops);
        return R.setOK();
    }
}
