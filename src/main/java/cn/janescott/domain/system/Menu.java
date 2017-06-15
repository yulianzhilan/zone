package cn.janescott.domain.system;

import cn.janescott.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by scott on 2017/6/14.
 */
@Entity
@Table(name = "t_menu")
public class Menu extends BaseEntity implements Comparable{
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String url;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Module module;

    private Integer seq;

    private String icon;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int compareTo(Object o) {
        if(o == null || !(o instanceof Menu)){
            return 1;
        } else {
            return this.getSeq() - ((Menu) o).getSeq();
        }
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", module=" + module +
                ", seq=" + seq +
                ", icon='" + icon + '\'' +
                '}';
    }
}
