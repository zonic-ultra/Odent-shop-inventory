package com.dendev.odent_shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUnitPartsErrorResponse {
    private Integer status;
    private String message;
    private Long timeStamp;
}
