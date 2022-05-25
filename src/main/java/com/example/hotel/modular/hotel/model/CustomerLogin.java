package com.example.hotel.modular.hotel.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerLogin {

    private String customerLoginName;

    private String customerLoginPassword;

}
