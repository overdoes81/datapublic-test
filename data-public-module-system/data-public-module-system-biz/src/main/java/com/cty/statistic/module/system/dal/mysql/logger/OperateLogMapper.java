package com.cty.statistic.module.system.dal.mysql.logger;

import com.cty.statistic.framework.common.exception.enums.GlobalErrorCodeConstants;
import com.cty.statistic.framework.common.pojo.PageResult;
import com.cty.statistic.framework.mybatis.core.mapper.BaseMapperX;
import com.cty.statistic.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.cty.statistic.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import com.cty.statistic.module.system.dal.dataobject.logger.OperateLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface OperateLogMapper extends BaseMapperX<OperateLogDO> {

    default PageResult<OperateLogDO> selectPage(OperateLogPageReqVO reqVO, Collection<Long> userIds) {
        LambdaQueryWrapperX<OperateLogDO> query = new LambdaQueryWrapperX<OperateLogDO>()
                .likeIfPresent(OperateLogDO::getModule, reqVO.getModule())
                .inIfPresent(OperateLogDO::getUserId, userIds)
                .eqIfPresent(OperateLogDO::getType, reqVO.getType())
                .betweenIfPresent(OperateLogDO::getStartTime, reqVO.getStartTime());
        if (Boolean.TRUE.equals(reqVO.getSuccess())) {
            query.eq(OperateLogDO::getResultCode, GlobalErrorCodeConstants.SUCCESS.getCode());
        } else if (Boolean.FALSE.equals(reqVO.getSuccess())) {
            query.gt(OperateLogDO::getResultCode, GlobalErrorCodeConstants.SUCCESS.getCode());
        }
        query.orderByDesc(OperateLogDO::getId); // 降序
        return selectPage(reqVO, query);
    }

}
