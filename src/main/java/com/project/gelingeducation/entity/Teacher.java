package com.project.gelingeducation.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: LL
 * @Description: 老师的实体类
 */
@Entity
@Table(name = "teacher")
@Accessors(chain = true)
@Setter
@Getter
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1595117855604940548L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 教师名
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 头像
     */
    @Column(name = "big_img")
    private String bigImg;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 多对多 教师
     */
    @JsonIgnore
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Video> videos = new HashSet<>();

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time", nullable = false)
    private Date modifyTime;
}
