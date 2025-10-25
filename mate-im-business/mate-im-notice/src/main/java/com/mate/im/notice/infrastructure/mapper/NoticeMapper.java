package com.mate.im.notice.infrastructure.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mate.im.notice.domain.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通知 Mapper 接口
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
