package com.eshop.stockmanager.repository;

import com.eshop.stockmanager.repository.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByName(String name);

    Product findByCode(String code);

    void deleteByCode(String code);

}
