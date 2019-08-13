package cn.xiaoyanol.crawler;

import cn.xiaoyanol.crawler.domain.baseinfo.BaseInfo;
import cn.xiaoyanol.crawler.service.IBaseInfoService;
import cn.xiaoyanol.crawler.service.impl.BaseInfoServiceImpl;
import cn.xiaoyanol.crawler.utils.EasyExcelUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-25
 * @Time: 下午9:39
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "0###oo34J0ZRgatN5UBO8UQRwap6Ew_A###1565664617903###24ed6f7b1512aee63869b97552a2bd8f");
        headers.put("host", "api9.tianyancha.com");
        headers.put("Content-Type", "application/json");
        headers.put("X-AUTH-TOKEN", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODIxMTI0NDg2NiIsImlhdCI6MTU1Nzc0Mzc2NCwiZXhwIjoxNTYwMzM1NzY0fQ.BACR-0C0uRCbINF6Lv-aZ4Tz16TYhxrUyhWZ7D2rm9F4kIkZtwIjqePPj_WXVIhtYDQhkXbtEBaldpq9fnAT-A");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept", "*/*");
        headers.put("version", "TYC-XCX-WX");
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 MicroMessenger/7.0.5(0x17000523) NetType/WIFI Language/zh_CN");
        headers.put("Accept-Language", "zh-cn");

        IBaseInfoService baseInfoService = new BaseInfoServiceImpl(headers);
        BaseInfo baseInfoResult = baseInfoService.getBaseInfoResult("22822");

        ArrayList<BaseInfo> data = new ArrayList<>();
        data.add(baseInfoResult);
        EasyExcelUtils.write("123", data);
    }
}
