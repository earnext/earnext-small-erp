package top.dongxibao.erp.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.dongxibao.erp.entity.system.SysUser;
import top.dongxibao.erp.service.system.SysMenuService;
import top.dongxibao.erp.service.system.SysRoleService;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author Dongxibao
 * @date 2021-01-08
 */
@Component
public class SysPermissionService {
    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysMenuService menuService;

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限代码
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getId()));
        }
        return perms;
    }
}
