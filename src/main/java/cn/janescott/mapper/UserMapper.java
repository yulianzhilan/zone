package cn.janescott.mapper;

import cn.janescott.entity.UserEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by scott on 2017/7/13.
 */
public interface UserMapper {
    @Select("select * from t_user where username=#{username}")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "roleId", column = "role_id")
    })
    @Cacheable(cacheNames = "user", key = "'findByUsername:username@' + args")
    UserEntity getOne(String username);
}
