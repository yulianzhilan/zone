package cn.janescott.repository.system;

import cn.janescott.common.LoggerManage;
import cn.janescott.domain.system.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by scott on 2017/6/15.
 */
//public interface RoleRepository extends JpaRepository<Role, Integer> {
//
//    @LoggerManage(description = "findSidebarByRoleId")
//    @Cacheable(cacheNames = "sidebars", key = "'findSidebarByRoleId:Id@' + #id")
//    Role findAllById(@Param("id") Integer id);
//}
