package cn.xiaoyanol.crawler.domain.baseinfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-25
 * @Time: 下午10:34
 */
public class BaseInfoLegalInfo {

    private List<BaseInfoOffice> office;
    private long hid;
    private String name;
    private int companyNum;
    private String headUrl;
    private long cid;
    public void setOffice(List<BaseInfoOffice> baseInfoOffice) {
        this.office = baseInfoOffice;
    }
    public List<BaseInfoOffice> getOffice() {
        return office;
    }

    public void setHid(long hid) {
        this.hid = hid;
    }
    public long getHid() {
        return hid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setCompanyNum(int companyNum) {
        this.companyNum = companyNum;
    }
    public int getCompanyNum() {
        return companyNum;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public String getHeadUrl() {
        return headUrl;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }
    public long getCid() {
        return cid;
    }

}
