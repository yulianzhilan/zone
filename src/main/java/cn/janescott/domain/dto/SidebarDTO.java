package cn.janescott.domain.dto;

import cn.janescott.domain.system.Menu;
import cn.janescott.domain.system.Module;

import java.util.Map;
import java.util.Set;

/**
 * Created by scott on 2017/6/15.
 */
public class SidebarDTO {
    Map<Module, Set<Menu>> moduleListMap;

    public Map<Module, Set<Menu>> getModuleListMap() {
        return moduleListMap;
    }

    public void setModuleListMap(Map<Module, Set<Menu>> moduleListMap) {
        this.moduleListMap = moduleListMap;
    }

    public SidebarDTO() {
    }

    public SidebarDTO(Map<Module, Set<Menu>> moduleListMap) {
        this.moduleListMap = moduleListMap;
    }

    @Override
    public String toString() {
        return "SidebarDTO{" +
                "moduleListMap=" + moduleListMap +
                '}';
    }
}
