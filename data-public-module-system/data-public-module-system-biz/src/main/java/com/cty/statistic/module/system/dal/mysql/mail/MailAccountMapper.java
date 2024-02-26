package com.cty.statistic.module.system.dal.mysql.mail;

import com.cty.statistic.framework.common.pojo.PageResult;
import com.cty.statistic.framework.mybatis.core.mapper.BaseMapperX;
import com.cty.statistic.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.cty.statistic.framework.mybatis.core.query.QueryWrapperX;
import com.cty.statistic.module.system.controller.admin.mail.vo.account.MailAccountPageReqVO;
import com.cty.statistic.module.system.dal.dataobject.mail.MailAccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailAccountMapper extends BaseMapperX<MailAccountDO> {

    default PageResult<MailAccountDO> selectPage(MailAccountPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<MailAccountDO>()
                .likeIfPresent(MailAccountDO::getMail, pageReqVO.getMail())
                .likeIfPresent(MailAccountDO::getUsername , pageReqVO.getUsername()));
    }

}
