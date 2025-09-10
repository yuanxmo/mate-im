package com.mate.im.datasource.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * 自动填充
 *
 * @author yuanxmo
 */
public class DataObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByNameNotNull(
                "gmtCreate",
                OffsetDateTime.of(
                        LocalDateTime.now(),
                        ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()
                        )
                ),
                metaObject
        );
        this.setFieldValByNameNotNull(
                "gmtModified",
                OffsetDateTime.of(
                        LocalDateTime.now(),
                        ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()
                        )
                ),
                metaObject
        );
        this.setFieldValByName("deleted", false, metaObject);
        this.setFieldValByName("lockVersion", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(
                "gmtModified",
                OffsetDateTime.of(
                        LocalDateTime.now(),
                        ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()
                        )
                ),
                metaObject
        );
    }

    /**
     * 无值时设置属性
     * @param fieldName
     * @param fieldVal
     * @param metaObject
     */
    private void setFieldValByNameNotNull(String fieldName, Object fieldVal, MetaObject metaObject) {
        if (metaObject.getValue(fieldName) == null) {
            this.setFieldValByName(fieldName, fieldVal, metaObject);
        }
    }
}
