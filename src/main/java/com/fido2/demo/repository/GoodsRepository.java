package com.fido2.demo.repository;

import com.fido2.demo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Long> {
    //List<Goods> findByGoodsName(String name);
    //Goods findById(Long id);
    //Optional<Goods> findById(Long id);
}
