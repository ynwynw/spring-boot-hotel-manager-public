package com.example.hotel.modular.hotel.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderInfo {
    private Integer orderId;

    private String name;

    private String phone;

    private Integer roomTypeId;

    private String roomType;

    private Date orderDate;

    private Integer orderDays;

    private Integer orderStatus;

    private Double orderCost;

    private Integer userId;

    private String idcard;

}