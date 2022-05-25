package com.example.hotel.modular.system.service.impl;

import com.example.hotel.core.common.exception.ParamException;
import com.example.hotel.modular.system.dao.RoleMapper;
import com.example.hotel.modular.system.model.Role;
import com.example.hotel.modular.system.service.RoleService;
import com.google.common.base.Preconditions;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: luhailiang
 * @Date: 2019-03-28 20:17
 * @Description: RoleServiceImpl
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * @param role
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-28 20:17
     * @description: 新增角色
     */
    @Override
    public int saveRole(Role role) {
        if (checkRoleNameExist(role.getRoleName(), role.getRoleId())) {
            throw new ParamException("角色名已被占用");
        }
        Role roles = Role.builder().roleName(role.getRoleName()).build();
        return roleMapper.insertSelective(roles);
    }

    /**
     * @param role
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-28 20:17
     * @description: 更新角色
     */
    @Override
    public int updateRole(Role role) {
        if (checkRoleNameExist(role.getRoleName(), role.getRoleId())) {
            throw new ParamException("角色名已被占用");
        }
        Role before = roleMapper.selectByPrimaryKey(role.getRoleId());
        Preconditions.checkNotNull(before, "需更新的角色不存在");
        Role roles = Role.builder().roleId(role.getRoleId()).roleName(role.getRoleName()).build();
        return roleMapper.updateByPrimaryKeySelective(roles);
    }

    /**
     * @param roleId
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-28 20:17
     * @description: 根据id删除角色
     */
    @Override
    public int deleteRole(Integer roleId) {
        Role before = roleMapper.selectByPrimaryKey(roleId);
        Preconditions.checkNotNull(before, "需删除的角色不存在");
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    /**
     * @param userId
     * @return : void
     * @author: luhailiang
     * @date: 2019-03-28 21:15
     * @description: 通过用户id删除用户角色表的关联关系
     */
    @Override
    public void deleteRoleUserRsByUserId(Long userId) {

        roleMapper.deleteRoleUserRsByUserId(userId);
    }

    /**
     * @param roleId
     * @return : void
     * @author: luhailiang
     * @date: 2019-03-28 21:28
     * @description: 通过角色id删除用户角色表的关联关系
     */
    @Override
    public void deleteRoleUserRsByRoleId(Integer roleId) {

        roleMapper.deleteRoleUserRsByRoleId(roleId);
    }

    /**
     * @param map
     * @return : java.util.List<io.hailiang.web.book.model.Role>
     * @author: luhailiang
     * @date: 2019-03-28 21:40
     * @description: 查询角色列表
     */
    @Override
    public List<Role> selectRoleList(Map<String, Object> map) {
        return roleMapper.selectRoleList(map);
    }

    /**
     * @param map
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-28 21:41
     * @description: 查询角色总数
     */
    @Override
    public int getTotalRole(Map<String, Object> map) {
        return roleMapper.getTotalRole(map);
    }

    /**
     * @param userId
     * @return : java.util.List<io.hailiang.web.book.model.Role>
     * @author: luhailiang
     * @date: 2019-03-29 17:14
     * @description: 根据用户id查找角色集合
     */
    @Override
    public List<Role> findByUserId(Long userId) {
        return roleMapper.findByUserId(userId);
    }

    /**
     * @param map
     * @return : int
     * @author: luhailiang
     * @date: 2019-03-29 22:20
     * @description: 为角色分配权限
     */
    @Override
    public int insertRolePermissions(Map<String, Object> map) {
        return roleMapper.insertRolePermissions(map);
    }


    /**
     * @param roleName
     * @param roleId
     * @return : boolean
     * @author: luhailiang
     * @date: 2019-03-28 20:37
     * @description: check角色名是否存在
     */
    public boolean checkRoleNameExist(String roleName, Integer roleId) {
        return roleMapper.countByRoleName(roleName, roleId) > 0;
    }
}
