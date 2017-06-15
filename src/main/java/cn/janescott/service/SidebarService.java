package cn.janescott.service;

import cn.janescott.common.LoginExceprion;
import cn.janescott.domain.system.Menu;
import cn.janescott.domain.system.Module;
import cn.janescott.domain.system.Role;
import cn.janescott.domain.dto.SidebarDTO;
import cn.janescott.domain.system.User;
import cn.janescott.repository.system.MenuRepository;
import cn.janescott.repository.system.ModuleRepository;
import cn.janescott.repository.system.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by scott on 2017/6/15.
 */
@Service
public class SidebarService {
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private ModuleRepository moduleRepository;


    public SidebarDTO getSidebar(@NotNull Integer roleId){
        Role role = roleRepository.findAllById(roleId);
        return assemble(role);
    }

    public SidebarDTO getSidebar(@NotNull User user){
        Role role = user.getRole();
        return assemble(role);
    }

    private SidebarDTO assemble(@NotNull Role role){
        List<Menu> menus = role.getMenus();
        Map<Module, Set<Menu>> sidebar = new TreeMap<>();
        for(Menu menu : menus){
            Module module = menu.getModule();
            if(!sidebar.containsKey(module)){
                Set<Menu> menuSet = new TreeSet<>();
                menuSet.add(menu);
                sidebar.put(module, menuSet);
            } else {
                Set<Menu> menuSet = sidebar.get(module);
                menuSet.add(menu);
            }
        }
        return new SidebarDTO(sidebar);
    }

}
