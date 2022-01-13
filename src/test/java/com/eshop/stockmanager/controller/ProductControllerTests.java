package com.eshop.stockmanager.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenProduct_whenCodeIsT1_thenReturnTheProduct() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/product/findByCode/{code}", "t1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());

    }

    @Test
    public void givenProduct_whenNameIsTablet_thenReturnProductDto() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/product/findByName/{name}", "tablet")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray());

    }

    @Test
    public void givenProduct_whenCodeIsT1_thenBuyOutOfOrderProduct() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/product/buy/{code}", "t1")
                .param("count", "200")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnavailableForLegalReasons());

    }

    @Test
    public void givenProduct_whenCodeIsT1_thenRefillProduct() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/product/refill/{code}", "t1")
                .param("in_stock", "100")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void givenProduct_whenCodeIsT1_thenReturnInStock() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/product/stock/{code}", "t1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNumber());

    }


}
