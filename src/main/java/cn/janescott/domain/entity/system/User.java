package cn.janescott.domain.entity.system;

import cn.janescott.domain.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by scott on 2017/6/13.
 */
@Entity
@Table(name = "t_user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "CREATE_TIME", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name = "MODIFY_TIME", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date modifyTime;

    @Column(name = "FLAG")
    private Boolean flag;

    //去掉级联和多对多，从而解决懒加载异常
//    @ManyToOne(fetch = FetchType.EAGER)
//    @PrimaryKeyJoinColumn
//    @JsonBackReference
//    private Role role;
    @Column(name = "ROLE_ID")
    private Integer roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", flag=" + flag +
                ", roleId=" + roleId +
                '}';
    }
}
