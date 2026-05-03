package com.example.userservice.common;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseWrapper<T>  {
    String status;
    String message;
    String description;
    T content;
}
