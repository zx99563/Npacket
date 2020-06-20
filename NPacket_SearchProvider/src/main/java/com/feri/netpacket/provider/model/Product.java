package com.feri.netpacket.provider.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Objects;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:13
 */
@Document(indexName = "npacket_product")
public class Product {
    @Id //标记唯一属性
    private Integer id;
//    @Field()
    private String pname;
    private String tname;
    private int tid;

    @Override
    public boolean equals(Object o) {
       return pname.equals(((Product)o).pname);
    }

    @Override
    public int hashCode() {
        return pname.hashCode();
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String pname, String tname, int tid) {
        this.id = id;
        this.pname = pname;
        this.tname = tname;
        this.tid = tid;
    }

    public Product() {
    }
}