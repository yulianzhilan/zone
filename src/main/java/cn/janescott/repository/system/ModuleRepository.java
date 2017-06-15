package cn.janescott.repository.system;

import cn.janescott.domain.system.Module;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by scott on 2017/6/15.
 */
public interface ModuleRepository extends JpaRepository<Module, Integer>{
}
