/**
  * Copyright 2019 bejson.com 
  */
package cn.xiaoyanol.crawler.domain.search;
import java.util.List;

/**
 * Auto-generated: 2019-08-13 16:8:25
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class CompanyHumanInfo {

    private long graphId;
    private String companyName;
    private int distinctNum;
    private int resultCount;
    private List<ResultList> resultList;
    private int totalPage;
    public void setGraphId(long graphId) {
         this.graphId = graphId;
     }
     public long getGraphId() {
         return graphId;
     }

    public void setCompanyName(String companyName) {
         this.companyName = companyName;
     }
     public String getCompanyName() {
         return companyName;
     }

    public void setDistinctNum(int distinctNum) {
         this.distinctNum = distinctNum;
     }
     public int getDistinctNum() {
         return distinctNum;
     }

    public void setResultCount(int resultCount) {
         this.resultCount = resultCount;
     }
     public int getResultCount() {
         return resultCount;
     }

    public void setResultList(List<ResultList> resultList) {
         this.resultList = resultList;
     }
     public List<ResultList> getResultList() {
         return resultList;
     }

    public void setTotalPage(int totalPage) {
         this.totalPage = totalPage;
     }
     public int getTotalPage() {
         return totalPage;
     }

}