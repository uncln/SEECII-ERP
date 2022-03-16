package com.nju.edu.erp.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductVO {

    private Integer categoryId;

    private String name;

    private String type;

    private BigDecimal purchasePrice;

    private BigDecimal retailPrice;
}
