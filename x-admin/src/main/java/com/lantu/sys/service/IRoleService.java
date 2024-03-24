package com.lantu.sys.service;

import com.lantu.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IRoleService extends IService<Role> {

    void addRole(Role role);

    Role getRoleById(Integer id);

    void updateRole(Role role);

    void deleteRoleById(Integer id);
}
