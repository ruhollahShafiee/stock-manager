package com.eshop.stockmanager.repository;

import com.eshop.stockmanager.repository.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByName(String name);

    Optional<Product> findByCode(String code);

    void deleteByCode(String code);

}
