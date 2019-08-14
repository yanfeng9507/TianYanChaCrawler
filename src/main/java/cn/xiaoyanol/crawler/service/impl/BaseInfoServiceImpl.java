package cn.xiaoyanol.crawler.service.impl;

import cn.xiaoyanol.crawler.constant.UrlConstant;
import cn.xiaoyanol.crawler.domain.baseinfo.BaseInfo;
import cn.xiaoyanol.crawler.domain.baseinfo.Data;
import cn.xiaoyanol.crawler.domain.baseinfo.JsonRootBean;
import cn.xiaoyanol.crawler.service.IBaseInfoService;
import cn.xiaoyanol.crawler.utils.EnumParser;
import cn.xiaoyanol.crawler.utils.HttpClientUtils;
import cn.xiaoyanol.crawler.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

//import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-25
 * @Time: 下午10:22
 */
public class BaseInfoServiceImpl implements IBaseInfoService {

    private String url = UrlConstant.BASE_INFO_URL;
    private Map<String, String> headers;

    public BaseInfoServiceImpl(Map<String, String> _headers) {
        this.headers = _headers;
    }

    @Override
    public BaseInfo getBaseInfoResult(Long companyId) {

        try {
            if (companyId == null) {
                return null;
            }
            CloseableHttpResponse response = HttpClientUtils.doGet(url+ URLEncoder.encode(companyId.toString()), this.headers, null);

            String s = EntityUtils.toString(response.getEntity());

            JsonRootBean jsonRootBean = JsonUtils.convertValue(s, JsonRootBean.class);
            Data data = jsonRootBean.getData();
            BaseInfo baseInfo = getBaseInfo(data);

            return baseInfo;
        }catch (Exception e){

        }
        return null;
    }

    private BaseInfo getBaseInfo(Data data) {
        if (data == null) {
            return null;
        }
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setPercentileScore(data.getPercentileScore()+"");
        baseInfo.setStaffNumRange(data.getStaffNumRange());
        baseInfo.setFromTime(data.getFromTime() > 0? new Date(data.getFromTime()).toString() : "");
        baseInfo.setType(EnumParser.parseType(data.getType()));
//        baseInfo.setBondName(data.getB);
        baseInfo.setId(data.getId()+"");
        baseInfo.setIsMicroEnt(EnumParser.parseIsMicroEnt(data.getIsMicroEnt()));
//        baseInfo.setUsedBondName(data.get);
        baseInfo.setRegNumber(data.getRegNumber());
        baseInfo.setRegCapital(data.getRegCapital());
        baseInfo.setName(data.getName());
        baseInfo.setRegInstitute(data.getRegInstitute());
        baseInfo.setRegLocation(data.getRegLocation());
        baseInfo.setIndustry(data.getIndustry());
        baseInfo.setApprovedTime(data.getApprovedTime() > 0? new Date(data.getApprovedTime()).toString() : "");
        baseInfo.setSocialStaffNum(data.getSocialStaffNum()+"");
        baseInfo.setTags(data.getTags());
        baseInfo.setTaxNumber(data.getTaxNumber());
        baseInfo.setBusinessScope(data.getBusinessScope());
        baseInfo.setProperty3(data.getProperty3());
        baseInfo.setAlias(data.getAlias());
        baseInfo.setOrgNumber(data.getOrgNumber());
        baseInfo.setRegStatus(data.getRegStatus());
        baseInfo.setEstiblishTime(data.getEstiblishTimeTitleName());
//        baseInfo.setBondType(data.getbo);
        baseInfo.setLegalPersonName(data.getLegalPersonName());
        baseInfo.setToTime(data.getToTime() > 0? new Date(data.getToTime()).toString() : "");
        baseInfo.setActualCapital(data.getActualCapital());
        baseInfo.setCompanyOrgType(data.getCompanyOrgType());
        baseInfo.setBase(data.getBase());
        baseInfo.setCreditCode(data.getCreditCode());
//        baseInfo.setHistoryNames(data.gethi);
//        baseInfo.setBondNum();
//        baseInfo.setRegCapitalCurrency(data.getreg);
//        baseInfo.setActualCapitalCurrency(data.getac);
        baseInfo.setEmail(data.getEmail());
        baseInfo.setWebsiteList(data.getWebsiteList());
        baseInfo.setPhoneNumber(data.getPhoneNumber());
//        baseInfo.setRevokeDate(data.getRevo);
//        baseInfo.setRevokeReason(data.getrevo);
//        baseInfo.setCancelDate(data.getc);
//        baseInfo.setCancelReason(data.getre);

// private String bondName;//股票名
// private String usedBondName;//股票曾用名
// private String bondType;//股票类型
// private String historyNames;//曾用名
// private String bondNum;//股票号
// private String regCapitalCurrency;//注册资本币种 人民币 美元 欧元 等（暂未使用）
// private String actualCapitalCurrency;//实收注册资本币种 人民币 美元 欧元 等（暂未使用）
// private String revokeDate;//吊销日期
// private String revokeReason;//吊销原因
// private String cancelDate;//注销日期
// private String cancelReason;//注销原因
        return baseInfo;
    }

}
