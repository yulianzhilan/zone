package cn.janescott.repository.mapper;

import cn.janescott.common.LoggerManage;
import cn.janescott.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by scott on 2017/7/13.
 */
@Component
public interface UserMapper {
    @LoggerManage(description = "findUserDTOByUsername")
    @Select("select u.*, r.name from t_user u,t_role r where u.role_id = r.id and u.username=#{username}")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "name")
    })
    @Cacheable(cacheNames = "user", key = "'findUserDTOByUsername:username@' + args")
    UserDTO getOne(String username);

    @CachePut(cacheNames = "user", key = "'findUserDTOByUsername:username@' + args.username")
    UserDTO updateOne(UserDTO userDTO);
}
