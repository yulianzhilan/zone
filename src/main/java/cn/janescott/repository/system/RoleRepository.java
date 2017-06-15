package cn.janescott.repository.system;

import cn.janescott.domain.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by scott on 2017/6/15.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findAllById(Integer id);
}
