package com.mate.im.datasource.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.StringJoiner;

/**
 * 通用实体类
 *
 * @author yuanxmo
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private OffsetDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private OffsetDateTime gmtModified;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Boolean deleted;

    /**
     * 乐观锁版本号
     */
    @TableField(value = "lock_version", fill = FieldFill.INSERT)
    private Integer lockVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(OffsetDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public OffsetDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(OffsetDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BaseEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("deleted=" + deleted)
                .add("lockVersion=" + lockVersion)
                .toString();
    }
}
