package com.example.hotel.modular.hotel.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoomInfo {
    private Integer roomId;

    private Integer roomNumber;

    private Integer typeId;

    private String roomType;

    private Double roomPrice;

    private Integer roomStatus;


}