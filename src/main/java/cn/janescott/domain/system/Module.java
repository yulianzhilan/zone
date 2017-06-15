package cn.janescott.domain.system;

import cn.janescott.domain.BaseEntity;

import javax.persistence.*;

/**
 * Created by scott on 2017/6/14.
 */
@Entity
@Table(name = "t_module")
public class Module extends BaseEntity implements Comparable{
    @Id
    @GeneratedValue
    private Integer id;

    private String icon;

    private Integer seq;

    private String name;

    public Module() {
    }

    public Module(Integer id, String icon, Integer seq, String name) {
        this.id = id;
        this.icon = icon;
        this.seq = seq;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if(o == null || !(o instanceof Module)){
            return 1;
        } else {
            return this.getSeq() - ((Module) o).getSeq();
        }
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", seq=" + seq +
                ", name='" + name + '\'' +
                '}';
    }
}
