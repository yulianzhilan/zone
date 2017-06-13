package cn.janescott.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by scott on 2017/6/13.
 */
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String account;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "CREATE_TIME")
    private Date createTime;

    @Column(columnDefinition = "MODIFY_TIME")
    private Date modifyTime;

    @Column
    private boolean flag;

    public User() {
    }

    public User(String account, String password, String email, Date createTime, Date modifyTime, boolean flag) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
