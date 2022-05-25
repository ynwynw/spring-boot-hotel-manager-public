package com.example.hotel.modular.hotel.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CheckIn {
    private Integer checkInId;

    private Integer orderId;

    private Integer roomId;

    private Integer roomNumber;

    private String persons;

    private String ids;

    private Date checkInTime;


}