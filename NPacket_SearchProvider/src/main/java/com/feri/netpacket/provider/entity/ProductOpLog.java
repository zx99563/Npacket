package com.feri.netpacket.provider.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:41
 */
@Data
@Entity
@Table(name = "product_oplog")
public class ProductOpLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer pid;
    private Integer tid;
    private String pname;
    private String tname;
    private Integer flag;
}