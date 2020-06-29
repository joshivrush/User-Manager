package com.vrush.service.impl;

import com.vrush.dao.RoleDao;
import com.vrush.model.Role;
import com.vrush.model.User;
import com.vrush.model.UserDto;
import com.vrush.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public User save(UserDto user) {
        return null;
    }

    public List<Role> findAll() {
        List<Role> list = new ArrayList<Role>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {

    }


    @Override
    public Role findOne(String username) {
        return userDao.findByName(username);
    }

    @Override
    public Role findById(Long id) {
        return userDao.findById(id).get();
    }


}
