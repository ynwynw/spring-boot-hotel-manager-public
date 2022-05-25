package com.example.hotel.modular.hotel.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoomType {
    private Integer typeId;

    private String typeName;

    private Double price;

    private Integer area;

    private Integer bedNum;

    private String bedSize;

    private Integer window;

    private Integer rest;


}