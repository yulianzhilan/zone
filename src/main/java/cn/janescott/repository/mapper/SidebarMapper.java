package cn.janescott.repository.mapper;

import cn.janescott.domain.dto.SidebarDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by scott on 2017/7/14.
 */
@Component
public interface SidebarMapper {
    SidebarDTO getSidebar(@Param("roleId")Integer roleId);
}
