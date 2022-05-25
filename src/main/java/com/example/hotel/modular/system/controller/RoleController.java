package com.example.hotel.modular.system.controller;

import com.example.hotel.core.common.annotation.LoginRequired;
import com.example.hotel.core.common.page.DataGridDataSource;
import com.example.hotel.core.common.page.JsonData;
import com.example.hotel.core.common.page.PageBean;
import com.example.hotel.modular.system.model.Role;
import com.example.hotel.modular.system.service.PermissionService;
import com.example.hotel.modular.system.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-28 20:19
 * @Description:
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;


    @Resource
    private PermissionService permissionService;


    /**
     * @param role
     * @return : io.hailiang.web.book.common.JsonData
     * @author: luhailiang
     * @date: 2019-03-28 20:23
     * @description: 新增角色
     */
    @PostMapping("/save")
    @LoginRequired
    public JsonData saveRole(Role role) {
        int count = roleService.saveRole(role);
        if (count > 0) {
            return JsonData.success(count, "添加成功");
        } else {
            return JsonData.fail("添加失败");
        }

    }


    /**
     * @param role
     * @return : io.hailiang.web.book.common.JsonData
     * @author: luhailiang
     * @date: 2019-03-28 20:25
     * @description: 更新角色
     */
    @PutMapping("/update")
    @LoginRequired
    public JsonData updateRole(Role role) {
        int count = roleService.updateRole(role);
        if (count > 0) {
            return JsonData.success(count, "更新成功");
        } else {
            return JsonData.fail("更新失败");
        }
    }


    /**
     * @param roleId
     * @return : io.hailiang.web.book.common.JsonData
     * @author: luhailiang
     * @date: 2019-03-28 20:28
     * @description: 删除角色(先根据角色id删除角色权限关联信息, 再根据角色id删除用户角色关联信息)
     */
    @DeleteMapping("/delete")
    @LoginRequired
    public JsonData deleteRole(@RequestParam(value = "roleId") Integer roleId) {
        if (roleId.equals(1)) {
            return JsonData.fail("超级管理员角色无法删除！");
        } else {
            //TODO 根据角色id删除角色权限关联信息，再根据角色id删除用户角色关联信息
            permissionService.deleteRolePermissionRsByRoleId(roleId);
            roleService.deleteRoleUserRsByRoleId(roleId);
            int count = roleService.deleteRole(roleId);
            if (count > 0) {
                return JsonData.success(count, "删除成功");
            } else {
                return JsonData.fail("删除失败");
            }
        }
    }


    /**
     * @return : io.hailiang.web.book.common.DataGridDataSource<io.hailiang.web.book.model.Role>
     * @author: luhailiang
     * @date: 2019-03-28 21:48
     * @description: 服务端分页查询角色列表
     */
    @PostMapping("/list")
    @LoginRequired
    public DataGridDataSource<Role> getRoleList(@RequestParam(value = "roleName", required = false, defaultValue = "") String roleName,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                @RequestParam(value = "rows", required = false, defaultValue = "5") Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("roleName", "%" + roleName + "%");
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Role> roleList = roleService.selectRoleList(map);
        int totalRole = roleService.getTotalRole(map);
        DataGridDataSource<Role> dataGridDataSource = new DataGridDataSource<>();
        dataGridDataSource.setRows(roleList);
        dataGridDataSource.setTotal(totalRole);
        return dataGridDataSource;
    }


    /**
     *
     * @author: luhailiang
     * @date: 2019-03-29 22:14
     * @param roleId
     * @param permissionIds
     * @return : io.hailiang.web.book.common.JsonData
     * @description: 角色权限设置(先删除当前角色拥有的权限关系, 再重新设置)
     */
    @PostMapping("/savePermissionSet")
    @LoginRequired
    public JsonData savePermissionSet(Integer roleId, Integer[] permissionIds) {
        //先删除当前角色拥有的权限关系
        permissionService.deleteRolePermissionRsByRoleId(roleId);
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("permissionIds", permissionIds);
        int count = roleService.insertRolePermissions(map);
        if (count > 0) {
            return JsonData.success(count, "设置成功");
        } else {
            return JsonData.fail("设置失败");
        }

    }

}
