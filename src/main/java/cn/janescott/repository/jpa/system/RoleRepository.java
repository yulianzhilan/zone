package cn.janescott.repository.jpa.system;

import cn.janescott.common.LoggerManage;
import cn.janescott.domain.entity.system.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by scott on 2017/6/15.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @LoggerManage(description = "findRoleByRoleId")
    @Cacheable(cacheNames = "sidebars", key = "'findRoleByRoleId:Id@' + args")
    Role findOneById(Integer id);
}
