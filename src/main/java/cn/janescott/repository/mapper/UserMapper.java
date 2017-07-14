package cn.janescott.repository.mapper;

import cn.janescott.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by scott on 2017/7/13.
 */
//@Component
public interface UserMapper {
    @Select("select * from t_user where username=#{username}")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "roleId", column = "role_id")
    })
    @Cacheable(cacheNames = "user", key = "'findByUsername:username@' + args")
    UserDTO getOne(String username);
}
