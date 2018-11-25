package cn.xiaoyanol.crawler.service;

import cn.xiaoyanol.crawler.domain.baseinfo.BaseInfoJsonRootBean;

/**
 * Created with IntelliJ IDEA.
 * Description: 获取企业基本信息（含主要人员）
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-25
 * @Time: 下午10:21
 */
public interface IBaseInfoService {

    /**
     * 获取查询结果
     * @param companyId
     * @return
     */
    BaseInfoJsonRootBean getBaseInfoResult(String companyId);

}
