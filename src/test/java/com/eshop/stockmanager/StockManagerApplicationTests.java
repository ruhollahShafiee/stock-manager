package com.eshop.stockmanager;

import com.eshop.stockmanager.controller.ProductController;
import com.eshop.stockmanager.controller.exception.MoreThanTheStockException;
import com.eshop.stockmanager.service.dto.ProductDto;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class StockManagerApplicationTests {

	@Autowired
	ProductController productController;


	@Test
	public void checkBuy() throws Exception {

		ResponseEntity<String> response=productController.buy("t1",1);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	public void checkBuyOutOfOrder() {

		ResponseEntity<String> response= null;
		try {
			response = productController.buy("t1",110);
		} catch (MoreThanTheStockException e) {
			assertThat(true).isEqualTo(true);
		}
		catch (Exception e) {
			assertThat(true).isEqualTo(false);
		}

	}

	@Test
	public void checkFilling() {

		ResponseEntity<String> response = productController.refill("t1", 100);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
