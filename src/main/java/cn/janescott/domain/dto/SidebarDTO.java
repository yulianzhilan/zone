package cn.janescott.domain.dto;

import java.util.List;

/**
 * Created by scott on 2017/6/15.
 */
public class SidebarDTO {
    private String icon;

    private String name;

    private List<MenuDTO> menus;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }
}
