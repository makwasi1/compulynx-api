package com.compulynx.demo.dao.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
    String message;
    int statusCode;
}
