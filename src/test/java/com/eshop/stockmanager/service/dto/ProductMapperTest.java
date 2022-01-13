package com.eshop.stockmanager.service.dto;

import com.eshop.stockmanager.repository.model.Product;
import com.eshop.stockmanager.service.dto.mapper.ProductMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void givenProduct_thenReturnProductDto() {
        Product product = new Product(1, "tmpCode", "test", 4, 24D, 23D, null);
        ProductDto actualProductDto = productMapper.productToProductDto(product);
        ProductDto expectedProductDto = new ProductDto(1, "tmpCode", "test", 4, 24D, 23D, null);
        Assert.assertEquals(expectedProductDto, actualProductDto);
    }

    @Test
    public void givenProductIncomingDto_thenReturnProduct() {
        ProductIncomingDto productIncomingDto = new ProductIncomingDto("tmpCode", "test", 4, 14D, 12D);
        Product actualProduct = productMapper.productIncomingDtoToProduct(productIncomingDto);
        Product expectedProduct = new Product(null, "tmpCode", "test", 4, 14D, 12D, null);
        Assert.assertEquals(expectedProduct, actualProduct);
    }
}
