package cn.xiaoyanol.crawler.domain.search;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-23
 * @Time: 下午6:45
 */

public class SearchBrandAndAgencyList {

    private String id;
    private String name;
    private String logo;
    private String intro;
    private String base;
    private Date setupDate;
    private int type;
    private String companyName;
    private String round;
    private int competingCount;
    private String eventCount;
    private String fundCount;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getLogo() {
        return logo;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    public String getIntro() {
        return intro;
    }

    public void setBase(String base) {
        this.base = base;
    }
    public String getBase() {
        return base;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }
    public Date getSetupDate() {
        return setupDate;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setRound(String round) {
        this.round = round;
    }
    public String getRound() {
        return round;
    }

    public void setCompetingCount(int competingCount) {
        this.competingCount = competingCount;
    }
    public int getCompetingCount() {
        return competingCount;
    }

    public void setEventCount(String eventCount) {
        this.eventCount = eventCount;
    }
    public String getEventCount() {
        return eventCount;
    }

    public void setFundCount(String fundCount) {
        this.fundCount = fundCount;
    }
    public String getFundCount() {
        return fundCount;
    }

}
