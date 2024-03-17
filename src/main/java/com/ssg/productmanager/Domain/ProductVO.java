package com.ssg.productmanager.Domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductVO {

    private long pno;
    private String name;
    private int price;
    private int amount;
}
