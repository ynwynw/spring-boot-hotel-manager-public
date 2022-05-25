package com.example.hotel.modular.system.service;



import com.example.hotel.modular.system.model.Permission;
import com.example.hotel.modular.system.model.User;

import java.util.List;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-26 23:03
 * @Description: PermissionService
 */
public interface PermissionService {

    /**
     * @param user
     * @return : java.util.List<io.hailiang.web.book.model.Permission>
     * @author: luhailiang
     * @date: 2019-03-26 23:04
     * @description: 获取用户权限信息
     */
    List<Permission> queryPermissionsByUser(User user);

    /**
     * @return : java.util.List<io.hailiang.web.book.model.Permission>
     * @author: luhailiang
     * @date: 2019-03-26 23:29
     * @description: 获取所有权限列表
     */
    List<Permission> queryAll();

    /**
     * @param roleId
     * @return : void
     * @author: luhailiang
     * @date: 2019-03-28 21:30
     * @description: 通过角色id删除角色权限表的关联关系
     */
    void deleteRolePermissionRsByRoleId(Integer roleId);

    /**
     * @param roleId
     * @return : java.util.List<java.lang.Integer>
     * @author: luhailiang
     * @date: 2019-03-29 20:10
     * @description: 通过角色id查询已经分配的权限信息
     */
    List<Integer> queryPermissionIdsByRoleId(Integer roleId);
}
