package com.eshop.stockmanager.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppConfig {

    @Value("${eshop.show.error.details}")
    private Boolean serverThrowErrorDetails;

}
