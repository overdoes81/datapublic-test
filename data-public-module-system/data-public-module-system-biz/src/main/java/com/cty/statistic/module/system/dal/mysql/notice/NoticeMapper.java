package com.cty.statistic.module.system.dal.mysql.notice;

import com.cty.statistic.framework.common.pojo.PageResult;
import com.cty.statistic.framework.mybatis.core.mapper.BaseMapperX;
import com.cty.statistic.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.cty.statistic.module.system.controller.admin.notice.vo.NoticePageReqVO;
import com.cty.statistic.module.system.dal.dataobject.notice.NoticeDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper extends BaseMapperX<NoticeDO> {

    default PageResult<NoticeDO> selectPage(NoticePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NoticeDO>()
                .likeIfPresent(NoticeDO::getTitle, reqVO.getTitle())
                .eqIfPresent(NoticeDO::getStatus, reqVO.getStatus())
                .orderByDesc(NoticeDO::getId));
    }

}
