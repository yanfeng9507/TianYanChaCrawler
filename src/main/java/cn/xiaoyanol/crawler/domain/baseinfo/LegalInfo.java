/**
  * Copyright 2019 bejson.com 
  */
package cn.xiaoyanol.crawler.domain.baseinfo;
import java.util.List;

/**
 * Auto-generated: 2019-08-13 17:42:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class LegalInfo {

    private String name;
    private long hid;
    private String headUrl;
    private int companyNum;
    private List<Office> office;
    private String partners;
    private long cid;
    private String typeJoin;
    private String alias;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setHid(long hid) {
         this.hid = hid;
     }
     public long getHid() {
         return hid;
     }

    public void setHeadUrl(String headUrl) {
         this.headUrl = headUrl;
     }
     public String getHeadUrl() {
         return headUrl;
     }

    public void setCompanyNum(int companyNum) {
         this.companyNum = companyNum;
     }
     public int getCompanyNum() {
         return companyNum;
     }

    public void setOffice(List<Office> office) {
         this.office = office;
     }
     public List<Office> getOffice() {
         return office;
     }

    public void setPartners(String partners) {
         this.partners = partners;
     }
     public String getPartners() {
         return partners;
     }

    public void setCid(long cid) {
         this.cid = cid;
     }
     public long getCid() {
         return cid;
     }

    public void setTypeJoin(String typeJoin) {
         this.typeJoin = typeJoin;
     }
     public String getTypeJoin() {
         return typeJoin;
     }

    public void setAlias(String alias) {
         this.alias = alias;
     }
     public String getAlias() {
         return alias;
     }

}