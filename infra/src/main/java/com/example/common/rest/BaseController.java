package com.example.common.rest;

import com.example.common.usecase.UseCasePublisherImpl;
import org.springframework.http.ResponseEntity;

public class BaseController extends UseCasePublisherImpl {

    protected <T> ResponseEntity<T> respond(T item) {
        return ResponseEntity.ok().body(item);
    }
}
