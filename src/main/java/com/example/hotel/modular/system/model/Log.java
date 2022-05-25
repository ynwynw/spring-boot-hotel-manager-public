package com.example.hotel.modular.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @Auther: luhailiang
 * @Date: 2019-10-30 10:28
 * @Description: LogModel
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Log {
    private Integer logId;

    private String logUserName;

    private String logUserRole;

    private String logOperateContent;

    private String logIpAddress;

    private String logIpLocation;

    private String logSystemType;

    private String logBrowserType;

    @JsonFormat(locale = "zh", timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logCreateTime;

    private String logNote;

}