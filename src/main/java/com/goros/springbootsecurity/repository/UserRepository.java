package com.goros.springbootsecurity.repository;

import com.goros.springbootsecurity.model.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {
    @Results(id = "userMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "fullName", column = "fullname"),
            @Result(property = "roles", column = "user_id", many = @Many(select = "getAllRoles"))
})

@Select("""
    
        SELECT * FROM app_users where email = #{email}
    """)
    public User getUser(String email);

    @Select(
"""
    SELECT role_name FROM app_user_role aur INNER JOIN app_roles ar ON aur.role_id = ar.role_id WHERE user_id = #{userId}
    """)
    public List<String> getAllRoles(Long userId);
}
