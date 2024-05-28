package com.kpanda.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class MyExceptionHandler {
    //모든에러를 잡는 함수
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(){
        return ResponseEntity.status(400).body("니 잘못임");
    }

}
