package cn.janescott.domain.system;

import cn.janescott.domain.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by scott on 2017/6/14.
 */
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(cascade = {},fetch = FetchType.EAGER)
    @JoinTable(name = "role_menu", joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    private List<Menu> menus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
