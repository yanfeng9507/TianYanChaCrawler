package cn.xiaoyanol.crawler.domain.baseinfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-25
 * @Time: 下午10:34
 */
public class BaseInfoOffice {

    private int total;
    private String area;
    private int score;
    private String companyName;
    private long cid;
    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getArea() {
        return area;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }
    public long getCid() {
        return cid;
    }

}