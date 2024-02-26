package com.cty.statistic.module.system.dal.mysql.logger;

import com.cty.statistic.framework.common.pojo.PageResult;
import com.cty.statistic.framework.mybatis.core.mapper.BaseMapperX;
import com.cty.statistic.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.cty.statistic.module.system.api.logger.dto.OperateLogV2PageReqDTO;
import com.cty.statistic.module.system.dal.dataobject.logger.OperateLogV2DO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogV2Mapper extends BaseMapperX<OperateLogV2DO> {

    default PageResult<OperateLogV2DO> selectPage(OperateLogV2PageReqDTO pageReqDTO) {
        return selectPage(pageReqDTO, new LambdaQueryWrapperX<OperateLogV2DO>()
                .eqIfPresent(OperateLogV2DO::getType, pageReqDTO.getBizType())
                .eqIfPresent(OperateLogV2DO::getBizId, pageReqDTO.getBizId())
                .eqIfPresent(OperateLogV2DO::getUserId, pageReqDTO.getUserId())
                .orderByDesc(OperateLogV2DO::getId));
    }

}
