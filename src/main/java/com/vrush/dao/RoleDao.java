
package com.vrush.dao;

import com.vrush.model.Role;
import com.vrush.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByName(String rolename);

}
