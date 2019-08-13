package cn.xiaoyanol.crawler.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2019-08-13
 * @Time: 下午4:35
 */
public class EnumParser {
    // 公司类型 1-公司，2-香港公司，3-社会组织，4-律所，5-事业单位，6-基金会
    public static String parseCompanyType(Integer companyType) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "公司");
        map.put(2, "香港公司");
        map.put(3, "社会组织");
        map.put(4, "律所");
        map.put(5, "事业单位");
        map.put(6, "基金会");

        String s = map.get(companyType);
        return s;
    }

    // 法人类型，1 人 2 公司
    public static String parseType(Integer type) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "人");
        map.put(2, "公司");

        String s = map.get(type);
        return s;
    }

    //    isMicroEnt; // 是否是小微企业 0不是 1是
    public static String parseIsMicroEnt(Integer type) {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "不是");
        map.put(1, "是");

        String s = map.get(type);
        return s;
    }
}
