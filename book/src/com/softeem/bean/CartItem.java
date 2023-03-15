package com.softeem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Integer id; //主键id
    private String name;//商品名称
    private Integer count;//商品数量
    private BigDecimal price;//商品价格
    private BigDecimal totalPrice;//商品总价【count*price】
}
