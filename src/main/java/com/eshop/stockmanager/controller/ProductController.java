package com.eshop.stockmanager.controller;

import com.eshop.stockmanager.controller.exception.MoreThanTheStockException;
import com.eshop.stockmanager.controller.response.ResponseTemplate;
import com.eshop.stockmanager.service.ProductService;
import com.eshop.stockmanager.service.dto.ProductDto;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     *
     * @param code
     * @return productDto
     */
    @GetMapping("/findByCode/{code}")
    public ResponseEntity<ProductDto> findByCode(@PathVariable("code") String code){

        ProductDto productDto=productService.findByCode(code);
        return new ResponseTemplate(Instant.now(), true, HttpStatus.OK, null, productDto).build();

    }

    /**
     *
     * @param name
     * @return returns products by name
     */
    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<ProductDto>> findByName(@PathVariable("name") String name){

        List<ProductDto> productDtos=productService.findByName(name);
        return new ResponseTemplate(Instant.now(), true, HttpStatus.OK, null, productDtos).build();

    }

    /**
     *
     * @param code
     * @return
     */
    @GetMapping("stock/{code}")
    public ResponseEntity<Integer> getStockByName(@PathVariable("code") String code){

        Integer inStock=productService.getInStockByCode(code);
        return new ResponseTemplate(Instant.now(), true, HttpStatus.OK, null, inStock).build();

    }

    /**
     *
     * @param code
     * @return refill
     */
    @PutMapping("refill/{code}")
    public ResponseEntity<String> refill(@PathVariable("code") String code,@RequestParam("in_stock") Integer in_stock){

        productService.refillInStock(code,in_stock);
        return new ResponseTemplate(Instant.now(), true, HttpStatus.OK, null, "refilled").build();

    }

    /**
     *
     * @param code
     * @param count
     * @return
     * @throws MoreThanTheStockException
     */
    @PutMapping("buy/{code}")
    public ResponseEntity<String> buy(@PathVariable("code") String code,@RequestParam("count") Integer count) throws MoreThanTheStockException {

        productService.buyProduct(code,count);
        return new ResponseTemplate(Instant.now(), true, HttpStatus.OK, null, "Sold to you! ").build();

    }


}
