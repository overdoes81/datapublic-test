package com.cty.statistic.module.system.api.logger;

import com.cty.statistic.framework.common.pojo.PageResult;
import com.cty.statistic.module.system.api.logger.dto.OperateLogCreateReqDTO;
import com.cty.statistic.module.system.api.logger.dto.OperateLogV2CreateReqDTO;
import com.cty.statistic.module.system.api.logger.dto.OperateLogV2PageReqDTO;
import com.cty.statistic.module.system.api.logger.dto.OperateLogV2RespDTO;

import javax.validation.Valid;

/**
 * 操作日志 API 接口
 *
 * @author 芋道源码
 */
public interface OperateLogApi {

    /**
     * 创建操作日志
     *
     * @param createReqDTO 请求
     */
    void createOperateLog(@Valid OperateLogCreateReqDTO createReqDTO);

    /**
     * 创建操作日志
     *
     * @param createReqDTO 请求
     */
    void createOperateLogV2(@Valid OperateLogV2CreateReqDTO createReqDTO);

    /**
     * 获取指定模块的指定数据的操作日志分页
     *
     * @param pageReqVO 请求
     * @return 操作日志分页
     */
    PageResult<OperateLogV2RespDTO> getOperateLogPage(OperateLogV2PageReqDTO pageReqVO);

}
