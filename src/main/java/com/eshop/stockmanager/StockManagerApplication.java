package com.eshop.stockmanager;

import com.eshop.stockmanager.service.ProductService;
import com.eshop.stockmanager.service.dto.ProductDto;
import com.eshop.stockmanager.service.dto.ProductIncomingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StockManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockManagerApplication.class, args);
	}


	/**
	 * @Description this class filled the database when the application started
	 */
	@Component
	class InitializingCommandLineRunner implements CommandLineRunner{

		@Autowired
		private ProductService productService;

		@Override
		public void run(String... args) throws Exception {

			try {
				productService.deleteByCode("t1");
				List<ProductIncomingDto> productIncomingDtos =Arrays.asList(new ProductIncomingDto[]
						{new ProductIncomingDto("t1", "tablet", 100, 54D, 50D),
								new ProductIncomingDto("t2","mobile", 100, 54D, 50D),
								new ProductIncomingDto("t3","mobile", 100, 54D, 50D)});

				productService.saveAll(productIncomingDtos);
			}catch (Exception e){}
		}
	}
}
