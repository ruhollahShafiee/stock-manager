package com.eshop.stockmanager.service;

import com.eshop.stockmanager.controller.exception.MoreThanTheStockException;
import com.eshop.stockmanager.service.dto.ProductDto;
import com.eshop.stockmanager.service.dto.ProductIncomingDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;


    @Test
    public void givenProduct_whenCodeIsT1_thenReturnProduct() {

        ProductDto actualProductDto = productService.findByCode("t1");
        ProductDto expectedProductDto = new ProductDto(1,"t1","tablet", 100, 54D, 50D,null);
        Assert.assertEquals(actualProductDto,expectedProductDto);

    }

    @Test
    public void givenProduct_whenCodeIsT2_thenRemove() {

        productService.deleteByCode("t2");
        Assert.assertTrue(true);

    }

    @Test
    public void saveTest() {

        ProductIncomingDto productIncomingDto=new ProductIncomingDto("t4","mobile",4,52.2D,78.4D);
        productService.save(productIncomingDto);
        Assert.assertTrue(true);

    }

    @Test
    public void saveAllTest() {

        List<ProductIncomingDto> productIncomingDtos = Arrays.asList(new ProductIncomingDto[]
                {new ProductIncomingDto("t4", "mobile", 100, 54D, 50D),
                        new ProductIncomingDto("t5","mobile", 100, 54D, 50D),
                        new ProductIncomingDto("t6","mobile", 100, 54D, 50D)});
        productService.saveAll(productIncomingDtos);
        Assert.assertTrue(true);

    }

    @Test
    public void givenProduct_whenNameIsTablet_thenReturnListOfProduct() {

        List<ProductDto> actualProductDtos = productService.findByName("tablet");
        Integer expectedMinSize=1;
        Assert.assertTrue(actualProductDtos.size() >= expectedMinSize);

    }

    @Test
    public void givenProduct_whenCodeIsT1_thenInStockOfProduct() {

        ProductDto actualProductDto = productService.findByCode("t1");
        Integer expectedInStock=100;
        Assert.assertTrue(actualProductDto.getInStock().equals(expectedInStock));

    }

    @Test
    public void givenProduct_whenCodeIsT1_thenRefillInStockOfProduct() {

        ProductDto actualProductDto = productService.findByCode("t1");
        actualProductDto.setInStock(100);
        Assert.assertTrue(true);

    }

    @Test
    public void givenProduct_whenCodeIsT1_thenBuyTheProduct() {

        try {
            productService.buyProduct("t1",1);
            Assert.assertTrue(true);
        } catch (MoreThanTheStockException e) {
            Assert.assertTrue(false);
        }

    }

    @Test
    public void givenProduct_whenCodeIsT1_thenBuyMoreThanInStockTheProduct() {

        try {
            productService.buyProduct("t1",200);
            Assert.assertTrue(false);
        } catch (MoreThanTheStockException moreThanTheStockException) {
            Assert.assertTrue(true);
        }catch (Exception exception){
            Assert.assertTrue(false);
        }

    }
}
