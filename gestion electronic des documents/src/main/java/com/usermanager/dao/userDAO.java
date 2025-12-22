package com.usermanager.dao;

import com.usermanager.dao.base.BaseDAO;
import com.usermanager.model.UserModel;
import com.usermanager.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends BaseDAO<UserModel, Integer> {

    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);
    List<UserModel> findByRole(UserRole role);
    List<UserModel> searchByName(String keyword);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean authenticate(String username, String password);

}