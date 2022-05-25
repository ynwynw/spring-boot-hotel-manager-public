package com.example.hotel.modular.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customer {
    private Integer customerId;

    private String customerLoginName;

    private String customerLoginPassword;

    private String customerName;

    private String customerPhone;

    private String customerCardNumber;

    @JsonFormat(locale = "zh", timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date customerCreateTime;

    @JsonFormat(locale = "zh", timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date customerLastModifyTime;


}