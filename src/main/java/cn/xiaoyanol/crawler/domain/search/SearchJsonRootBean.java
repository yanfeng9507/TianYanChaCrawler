package cn.xiaoyanol.crawler.domain.search;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-23
 * @Time: 下午6:44
 */

public class SearchJsonRootBean {

    private String state;
    private String message;
    private String special;
    private String vipMessage;
    private int isLogin;
    private SearchData data;
    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
    public String getSpecial() {
        return special;
    }

    public void setVipMessage(String vipMessage) {
        this.vipMessage = vipMessage;
    }
    public String getVipMessage() {
        return vipMessage;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }
    public int getIsLogin() {
        return isLogin;
    }

    public void setData(SearchData baseInfoData) {
        this.data = baseInfoData;
    }
    public SearchData getData() {
        return data;
    }

}