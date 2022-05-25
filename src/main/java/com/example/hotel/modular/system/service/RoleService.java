package com.example.hotel.modular.system.service;


import com.example.hotel.modular.system.model.Role;

import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-28 20:15
 * @Description: RoleService
 */
public interface RoleService {


    /**
     * @param role
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-28 20:17
     * @description: 新增角色
     */
     int saveRole(Role role);


    /**
     * @param role
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-28 20:17
     * @description: 更新角色
     */
     int updateRole(Role role);


    /**
     * @param roleId
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-28 20:17
     * @description: 根据id删除角色
     */
     int deleteRole(Integer roleId);


    /**
     * @param userId
     * @return : void
     * @author: luhailiang
     * @date: 2019-03-28 21:15
     * @description: 通过用户id删除用户角色表的关联关系
     */
     void deleteRoleUserRsByUserId(Long userId);


    /**
     * @param roleId
     * @return : void
     * @author: luhailiang
     * @date: 2019-03-28 21:28
     * @description: 通过角色id删除用户角色表的关联关系
     */
     void deleteRoleUserRsByRoleId(Integer roleId);


    /**
     * @param map
     * @return : java.util.List<io.hailiang.web.book.model.Role>
     * @author: luhailiang
     * @date: 2019-03-28 21:40
     * @description: 查询角色列表
     */
     List<Role> selectRoleList(Map<String, Object> map);


    /**
     * @param map
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-28 21:41
     * @description: 查询角色总数
     */
     int getTotalRole(Map<String, Object> map);


    /**
     * @param userId
     * @return : java.util.List<io.hailiang.web.book.model.Role>
     * @author: luhailiang
     * @date: 2019-03-29 17:14
     * @description: 根据用户id查找角色集合
     */
     List<Role> findByUserId(Long userId);


    /**
     * @param map
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-29 22:20
     * @description: 为角色分配权限
     */
     int insertRolePermissions(Map<String, Object> map);
}
