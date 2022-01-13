package com.eshop.stockmanager.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
public class AppConfig {

    @Value("${eshop.show.error.details}")
    private Boolean serverThrowErrorDetails;

}
