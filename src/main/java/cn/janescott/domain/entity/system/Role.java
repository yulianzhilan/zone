package cn.janescott.domain.entity.system;

import cn.janescott.domain.BaseEntity;

import javax.persistence.*;

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

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
}
