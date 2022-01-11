package com.eshop.stockmanager.controller;

import com.eshop.stockmanager.controller.exception.MoreThanTheStockException;
import com.eshop.stockmanager.service.dto.ProductDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTests {

    @Autowired
    ProductController productController;

    @Test
    public void givenProduct_whenCodeIsT1_thenBuyProduct() {


        try {
            ResponseEntity<String> actualResponse = productController.buy("t1", 1);
            HttpStatus expectedStatusCode = HttpStatus.OK;
            Assert.assertEquals(actualResponse.getStatusCode(), expectedStatusCode);
        } catch (MoreThanTheStockException moreThanTheStockException) {
            Assert.assertTrue(false);
        } catch (Exception exception) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void givenProduct_whenCodeIsT1_thenBuyOutOfOrderProduct() {


        try {
            ResponseEntity<String> actualResponse = productController.buy("t1", 200);
            HttpStatus expectedStatusCode = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS;
            Assert.assertEquals(actualResponse.getStatusCode(), expectedStatusCode);
        } catch (MoreThanTheStockException e) {
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void givenProduct_whenCodeIsT1_thenRefillProduct() {

        ResponseEntity<String> actualResponse = productController.refill("t1", 100);
        HttpStatus expectedStatusCode = HttpStatus.OK;
        Assert.assertEquals(actualResponse.getStatusCode(), expectedStatusCode);
    }

    @Test
    public void givenProduct_whenCodeIsT1_thenReturnInStock() {

        ResponseEntity<Integer> actualResponse = productController.getStockByCode("t1");
        HttpStatus expectedStatusCode = HttpStatus.OK;
        Assert.assertEquals(actualResponse.getStatusCode(), expectedStatusCode);
    }

    @Test
    public void givenProduct_whenCodeIsT1_thenReturnProductDto() {

        ResponseEntity<ProductDto> actualResponse = productController.findByCode("t1");
        HttpStatus expectedStatusCode = HttpStatus.OK;
        Assert.assertEquals(actualResponse.getStatusCode(), expectedStatusCode);
    }

    @Test
    public void givenProduct_whenNameIsTablet_thenReturnProductDto() {

        ResponseEntity<List<ProductDto>> actualResponse = productController.findByName("tablet");
        HttpStatus expectedStatusCode = HttpStatus.OK;
        Assert.assertEquals(actualResponse.getStatusCode(), expectedStatusCode);
    }
}
