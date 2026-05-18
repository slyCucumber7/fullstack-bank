package com.example.userservice.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class ResponseWrapper<T>  {
    String status;
    String message;
    String description;
    T content;

}
