/**
  * Copyright 2019 bejson.com 
  */
package cn.xiaoyanol.crawler.domain.baseinfo;

/**
 * Auto-generated: 2019-08-13 17:42:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String state;
    private String message;
    private String special;
    private String vipMessage;
    private int isLogin;
    private Data data;
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

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

}