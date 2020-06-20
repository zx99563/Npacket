package com.feri.netpacket.provider.dao;

import com.feri.netpacket.provider.entity.ProductOpLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-11 14:44
 */
public interface ProductOpLogDao extends JpaRepository<ProductOpLog,Integer> {
    //方法名解析查询
    List<ProductOpLog> findByFlag(int flag);
}
