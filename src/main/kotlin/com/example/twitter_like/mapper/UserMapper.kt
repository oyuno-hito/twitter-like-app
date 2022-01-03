package com.example.twitter_like.mapper

import com.example.twitter_like.model.LoginUser
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Component

@Mapper
@Component
interface UserMapper {
    @Select(
        """
            <script>
                SELECT
                    users.id,
                    users.login_id,
                    users.password,
                    users.name AS name,
                    users.role_id,
                    roles.name AS role
                FROM users
                INNER JOIN roles ON users.role_id = roles.id
                WHERE login_id = #{loginId}
                AND users.deleted_at IS NULL
                LIMIT 1
            </script>
        """
    )
    @ResultMap("findByLoginId")
    fun findByLoginId(loginId: String): LoginUser?
}
