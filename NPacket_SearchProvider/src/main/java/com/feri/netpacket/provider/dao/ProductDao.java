package com.feri.netpacket.provider.dao;

import com.feri.netpacket.provider.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:14
 */
public interface ProductDao  extends ElasticsearchRepository<Product,Integer> {
}
