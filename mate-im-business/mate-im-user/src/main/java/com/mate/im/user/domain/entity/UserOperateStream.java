package com.mate.im.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.OffsetDateTime;

import com.mate.im.datasource.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户操作流水
 * </p>
 *
 * @author yuanxmo
 * @since 2025-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_operate_stream")
public class UserOperateStream extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 操作类型
     */
    @TableField("type")
    private String type;

    /**
     * 操作时间
     */
    @TableField("operate_time")
    private OffsetDateTime operateTime;

    /**
     * 操作参数
     */
    @TableField("param")
    private String param;

    /**
     * 拓展字段
     */
    @TableField("extend_info")
    private String extendInfo;
}
