package com.cty.statistic.module.system.convert.logger;

import com.cty.statistic.framework.common.pojo.PageResult;
import com.cty.statistic.framework.common.util.collection.CollectionUtils;
import com.cty.statistic.framework.common.util.collection.MapUtils;
import com.cty.statistic.framework.common.util.object.BeanUtils;
import com.cty.statistic.module.system.api.logger.dto.OperateLogV2RespDTO;
import com.cty.statistic.module.system.controller.admin.logger.vo.operatelog.OperateLogRespVO;
import com.cty.statistic.module.system.dal.dataobject.logger.OperateLogDO;
import com.cty.statistic.module.system.dal.dataobject.logger.OperateLogV2DO;
import com.cty.statistic.module.system.dal.dataobject.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static com.cty.statistic.framework.common.util.collection.CollectionUtils.convertMap;
import static com.cty.statistic.framework.common.util.collection.MapUtils.findAndThen;

@Mapper
public interface OperateLogConvert {

    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);

    default List<OperateLogRespVO> convertList(List<OperateLogDO> list, Map<Long, AdminUserDO> userMap) {
        return CollectionUtils.convertList(list, log -> {
            OperateLogRespVO logVO = BeanUtils.toBean(log, OperateLogRespVO.class);
            MapUtils.findAndThen(userMap, log.getUserId(), user -> logVO.setUserNickname(user.getNickname()));
            return logVO;
        });
    }

    default PageResult<OperateLogV2RespDTO> convertPage(PageResult<OperateLogV2DO> operateLogPage, List<AdminUserDO> userList) {
        return BeanUtils.toBean(operateLogPage, OperateLogV2RespDTO.class).setList(setUserInfo(operateLogPage.getList(), userList));
    }

    OperateLogV2RespDTO convert(OperateLogV2DO operateLogV2DO);

    default List<OperateLogV2RespDTO> setUserInfo(List<OperateLogV2DO> logList, List<AdminUserDO> userList) {
        Map<Long, AdminUserDO> userMap = convertMap(userList, AdminUserDO::getId);
        return CollectionUtils.convertList(logList, item -> {
            OperateLogV2RespDTO respDTO = convert(item);
            findAndThen(userMap, item.getUserId(), user -> respDTO.setUserName(user.getNickname()));
            return respDTO;
        });
    }

}
