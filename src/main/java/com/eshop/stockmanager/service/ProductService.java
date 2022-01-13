package com.eshop.stockmanager.service;

import com.eshop.stockmanager.controller.exception.MoreThanTheStockException;
import com.eshop.stockmanager.repository.ProductRepository;
import com.eshop.stockmanager.repository.model.Product;
import com.eshop.stockmanager.service.dto.ProductDto;
import com.eshop.stockmanager.service.dto.ProductIncomingDto;
import com.eshop.stockmanager.service.dto.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    /**
     * @param productIncomingDto
     */
    public void save(ProductIncomingDto productIncomingDto) {

        Product product = productMapper.productIncomingDtoToProduct(productIncomingDto);
        productRepository.save(product);

    }

    public void saveAll(List<ProductIncomingDto> productIncomingDtos) {

        List<Product> products = productMapper.productsIncomingDtoToProducts(productIncomingDtos);
        productRepository.saveAll(products);

    }


    /**
     * @param
     * @Description This method delete a product by code
     */
    public void deleteByCode(String code) {
        productRepository.deleteByCode(code);
    }

    /**
     * @param
     * @Description this method delete all of product records
     */
    public void deleteAll() {
        productRepository.deleteAll();
    }

    /**
     * @param code
     * @return find product and convert to productDto
     */
    public ProductDto findByCode(String code) {

        Product product = productRepository.findByCode(code).orElseThrow(NoSuchElementException::new);
        return productMapper.productToProductDto(product);
    }

    /**
     * @param name
     * @return find by name and return list of productDto
     */
    public List<ProductDto> findByName(String name) {

        List<Product> products = productRepository.findByName(name);
        return productMapper.productsToProductDtos(products);
    }

    /**
     * @param code
     * @return return inStockCode
     */
    public Integer getInStockByCode(String code) {

        Product product = productRepository.findByCode(code).orElseThrow(NoSuchElementException::new);
        return product.getInStock();
    }

    /**
     * @param code
     * @param inStock
     * @Description refill the inStock when find product by code
     */
    public void refillInStock(String code, Integer inStock) {

        Product product = productRepository.findByCode(code).orElseThrow(NoSuchElementException::new);
        product.setInStock(inStock);
        productRepository.save(product);

    }

    /**
     * @param code
     * @param count
     * @throws MoreThanTheStockException
     */
    public void buyProduct(String code, Integer count) throws MoreThanTheStockException {

        Product product = productRepository.findByCode(code).orElseThrow(NoSuchElementException::new);
        if (count >= product.getInStock()) {
            throw new MoreThanTheStockException("The requested quantity of this product is more than the stock.");
        }
        product.setInStock(product.getInStock() - count);
        productRepository.save(product);

    }

    /**
     * @param code
     * @param count
     * @param sessionId
     * @throws MoreThanTheStockException
     * @descriptioin this method didn't use and should be developed
     */
    public void reserveProduct(String code, Integer count, String sessionId) throws MoreThanTheStockException {

        Product product = productRepository.findByCode(code).orElseThrow(NoSuchElementException::new);
        if (count >= product.getInStock()) {
            throw new MoreThanTheStockException("The requested quantity of this product is more than the stock.");
        }
        product.setInStock(product.getInStock() - count);

        productRepository.save(product);

    }
}
