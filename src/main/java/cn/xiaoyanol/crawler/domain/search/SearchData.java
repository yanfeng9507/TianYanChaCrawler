package cn.xiaoyanol.crawler.domain.search;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-23
 * @Time: 下午6:46
 */
public class SearchData {

    private int companyTotalPage;
    private List<SearchBrandAndAgencyList> brandAndAgencyList;
    private int companyTotal;
    private int agencyCount;
    private String adviceQuery;
    private int companyCount;
    private int brandCount;
    private String modifiedQuery;
    private int humanCount;
    private List<SearchCompanyList> companyList;
    private int brandTotal;
    private String companyTotalStr;
    private int agencyTotal;
    public void setCompanyTotalPage(int companyTotalPage) {
        this.companyTotalPage = companyTotalPage;
    }
    public int getCompanyTotalPage() {
        return companyTotalPage;
    }

    public void setBrandAndAgencyList(List<SearchBrandAndAgencyList> searchBrandAndAgencyList) {
        this.brandAndAgencyList = searchBrandAndAgencyList;
    }
    public List<SearchBrandAndAgencyList> getBrandAndAgencyList() {
        return brandAndAgencyList;
    }

    public void setCompanyTotal(int companyTotal) {
        this.companyTotal = companyTotal;
    }
    public int getCompanyTotal() {
        return companyTotal;
    }

    public void setAgencyCount(int agencyCount) {
        this.agencyCount = agencyCount;
    }
    public int getAgencyCount() {
        return agencyCount;
    }

    public void setAdviceQuery(String adviceQuery) {
        this.adviceQuery = adviceQuery;
    }
    public String getAdviceQuery() {
        return adviceQuery;
    }

    public void setCompanyCount(int companyCount) {
        this.companyCount = companyCount;
    }
    public int getCompanyCount() {
        return companyCount;
    }

    public void setBrandCount(int brandCount) {
        this.brandCount = brandCount;
    }
    public int getBrandCount() {
        return brandCount;
    }

    public void setModifiedQuery(String modifiedQuery) {
        this.modifiedQuery = modifiedQuery;
    }
    public String getModifiedQuery() {
        return modifiedQuery;
    }

    public void setHumanCount(int humanCount) {
        this.humanCount = humanCount;
    }
    public int getHumanCount() {
        return humanCount;
    }

    public void setCompanyList(List<SearchCompanyList> baseInfoCompanyList) {
        this.companyList = baseInfoCompanyList;
    }
    public List<SearchCompanyList> getCompanyList() {
        return companyList;
    }

    public void setBrandTotal(int brandTotal) {
        this.brandTotal = brandTotal;
    }
    public int getBrandTotal() {
        return brandTotal;
    }

    public void setCompanyTotalStr(String companyTotalStr) {
        this.companyTotalStr = companyTotalStr;
    }
    public String getCompanyTotalStr() {
        return companyTotalStr;
    }

    public void setAgencyTotal(int agencyTotal) {
        this.agencyTotal = agencyTotal;
    }
    public int getAgencyTotal() {
        return agencyTotal;
    }

}