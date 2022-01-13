package com.eshop.stockmanager.repository;

import com.eshop.stockmanager.repository.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void givenProduct_whenCodeIsT1_thenReturnProduct() {
        Optional<Product> actualProductOptional = productRepository.findByCode("t1");
        Assert.assertNotNull("There isn't product with t1 code!", actualProductOptional.orElse(null));
    }


    @Test
    public void givenProduct_whenNameIsTablet_thenReturnProducts() {
        int actualProductsSize = productRepository.findByName("tablet").size();
        int expectedMinimumProductSize = 1;
        Assert.assertTrue(actualProductsSize >= expectedMinimumProductSize);
    }
}
