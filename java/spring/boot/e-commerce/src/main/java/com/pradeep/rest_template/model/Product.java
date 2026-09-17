package com.pradeep.rest_template.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class Product {
    private Long id;
    private String title;
    private String description;
    private String category;
    private BigDecimal price;
    private Integer rating;
    private Long stock;
    private List<String> tags;
    private String brand;
}
