package cn.xiaoyanol.crawler.service.impl;

import cn.xiaoyanol.crawler.constant.UrlConstant;
import cn.xiaoyanol.crawler.domain.baseinfo.BaseInfoJsonRootBean;
import cn.xiaoyanol.crawler.domain.search.SearchJsonRootBean;
import cn.xiaoyanol.crawler.service.IBaseInfoService;
import cn.xiaoyanol.crawler.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-25
 * @Time: 下午10:22
 */
public class BaseInfoServiceImpl implements IBaseInfoService {
    @Override
    public BaseInfoJsonRootBean getBaseInfoResult(String companyId) {
        try {
            if (StringUtils.isBlank(companyId)) {
                return null;
            }

            // 拼接URL
            String url = UrlConstant.BASE_INFO_URL + companyId;
            HttpResponse response = HttpUtils.get(url, null);

            String s = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            BaseInfoJsonRootBean baseInfoJsonRootBean = gson.fromJson(s, BaseInfoJsonRootBean.class);

            return baseInfoJsonRootBean;
        }catch (Exception e){

        }
        return null;
    }
}
