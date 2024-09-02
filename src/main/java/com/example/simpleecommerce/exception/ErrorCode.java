package com.example.simpleecommerce.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 등록된 이메일입니다."),
    NOT_SUPPORTED_PAYMENT_METHOD(HttpStatus.BAD_REQUEST, "지원하지 않는 결제 수단입니다."),
    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
    NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),;
    private final HttpStatus status;
    private final String message;
}
