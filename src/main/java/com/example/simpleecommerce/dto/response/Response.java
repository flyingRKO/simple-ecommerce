package com.example.simpleecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    private String resultCode;

    private T result;

    public static <T> Response<T> success() {
        return new Response<>("성공", null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<>("성공", result);
    }

    public static Response<Void> error(String resultCode) {
        return new Response<>(resultCode, null);
    }

    public String toStream() {
        if (result == null) {
            return "{" +
                    "\"resultCode\":" + "\"" + resultCode + "\"," +
                    "\"result\":" + null +
                    "}";
        }
        return "{" +
                "\"resultCode\":" + "\"" + resultCode + "\"," +
                "\"result\":" + "\"" + result + "\"," +
                "}";
    }
}
