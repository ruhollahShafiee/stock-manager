package com.eshop.stockmanager.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

@Data
public class ResponseTemplate<T> {

    private Instant timestamp;
    private Boolean succeed;
    private ErrorTemplate error;
    @JsonIgnore
    private HttpStatus status;
    private T data;

    public ResponseTemplate(Instant timestamp, Boolean succeed, HttpStatus status, ErrorTemplate error, T data) {
        this.timestamp = timestamp;
        this.succeed = succeed;
        this.error = error;
        this.data = data;
        this.status = status;

    }

    public ResponseEntity build() {
        return new ResponseEntity<>(this, status);
    }
}
