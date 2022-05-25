package com.example.hotel.modular.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-26 22:10
 * @Description: PermissionModel
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Permission {

    private Integer permissionId;
    private String permissionName;
    private String permissionUrl;
    private Integer permissionParentId;

    private String permissionIcon;
    @JsonFormat(locale = "zh", timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date permissionCreateTime;
    @JsonFormat(locale = "zh", timezone = "GMT", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date permissionLastModifyTime;
    //不对应数据库字段
    private boolean open = true;
    //不对应数据库字段
    private boolean checked = false;
    //不对应数据库字段
    private List<Permission> children = new ArrayList<>();
    //不对应数据库字段(easy-ui tree grid)
    private Integer _parentId;
}
