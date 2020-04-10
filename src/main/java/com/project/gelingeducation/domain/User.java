package com.project.gelingeducation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *     //0为超级管理员，1为管理员，2为访客
 *     @Column(name = "isAdaim", length = 1)
 *     private int isAdaim;
 *
 *     超级管理员
 *     管理用户的权限
 *     管理员
 *     更新和删除资源的权限
 *     访客
 *     浏览资源的权限
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Setter
@Getter
public class User implements Serializable {

    private static final long serialVersionUID = -218595055003268321L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "account", nullable = false, length = 11)
    private String account;
    @Column(name = "password", nullable = false, length = 32)
    private String password;
    @Column(name = "user_name", length = 32)
    private String userName;
    @Column(name = "cover_img")
    private String coverImg;
    @Column(name = "email", length = 32)
    private String eMail;
    //0为保密，1为男，2为女
    @Column(name = "ssex", length = 1)
    private int sex;
    //,columnDefinition = "备注"
    @Column(name = "note", length = 100)
    private String note;
    //,columnDefinition = "状态 0锁定 1有效"
    @Column(name = "status", length = 1)
    private int status;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;
    //修改时间
    @Column(name = "modify_time")
    private Date modifyTime;
    //身份名
    @Column(name = "role_name",length = 24)
    private String roleName;
    //身份列表
    @ManyToMany(targetEntity = Role.class, mappedBy = "users")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", eMail='" + eMail + '\'' +
                ", sex=" + sex +
                ", note='" + note + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
