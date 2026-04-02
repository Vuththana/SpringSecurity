package com.goros.springbootsecurity.repository;

import com.goros.springbootsecurity.model.entity.User;
import com.goros.springbootsecurity.model.request.UserRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {
    @Results(id = "appUserMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "fullName", column = "fullname"),
            @Result(property = "roles", column = "user_id", many = @Many(select = "getAllRolesByUserId"))
    })
    @Select("""
        SELECT * FROM app_users WHERE email = #{email}
    """)
    User getUserByEmail(String email);

    @Select("""
        SELECT role_name FROM app_user_role aur
        INNER JOIN app_roles ar
        ON aur.role_id = ar.role_id
        WHERE user_id = #{userId}
    """)
    List<String> getAllRolesByUserId(Long userId);

    @Select("""
                INSERT INTO app_users
                VALUES (default, #{request.username}, #{request.email}, #{request.password})
                RETURNING *
            """)
    @ResultMap("appUserMapper")
    User register(@Param("request") UserRequest request);

    @Insert("""
                INSERT INTO user_role
                VALUES (#{userId}, #{roleId})
            """)
    void insertUserIdAndRoleId(Long roleId, Long userId);

    @Select("""
                SELECT * FROM app_users
                WHERE user_id = #{userId}
            """)
    @ResultMap("appUserMapper")
    User getUserById(Long userId);
}
