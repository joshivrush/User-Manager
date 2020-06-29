package com.vrush.service;

        import com.vrush.model.Role;
        import com.vrush.model.User;
        import com.vrush.model.UserDto;

        import java.util.List;

public interface RoleService {
    User save(UserDto user);

    List<Role> findAll();

    void delete(long id);

    Role findOne(String name);

    Role findById(Long id);
}
