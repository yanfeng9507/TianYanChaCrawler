package cn.xiaoyanol.crawler.utils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2019-08-13
 * @Time: 下午5:02
 */
public class EmUtils {
    public static String removeEmTag(String value) {
        if (value != null) {
            value = value.replace("<em>", "").replace("</em>", "");
        }
        return value;
    }
}
