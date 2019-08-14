package cn.xiaoyanol.crawler;

import cn.xiaoyanol.crawler.domain.baseinfo.BaseInfo;
import cn.xiaoyanol.crawler.domain.search.Search;
import cn.xiaoyanol.crawler.mapper.BaseInfoMapper;
import cn.xiaoyanol.crawler.service.IBaseInfoService;
import cn.xiaoyanol.crawler.service.ISearchService;
import cn.xiaoyanol.crawler.service.impl.BaseInfoServiceImpl;
import cn.xiaoyanol.crawler.service.impl.SearchServiceImpl;
import cn.xiaoyanol.crawler.utils.EasyExcelUtils;
import cn.xiaoyanol.crawler.utils.FileReaderUtils;
import cn.xiaoyanol.crawler.utils.HeaderUtils;
import cn.xiaoyanol.crawler.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-25
 * @Time: 下午9:39
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application implements CommandLineRunner {

    @Autowired
    private BaseInfoMapper baseInfoMapper;

    private boolean exportFlag = true;

    private String authorization;

    private int seconds;

    private int sleepTime;

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        // 查询前设置
        // token 非常重要， 微信小程序抓包获取
        authorization = "0###oo34J0ZRgatN5UBO8UQRwap6Ew_A###1565664617903###24ed6f7b1512aee63869b97552a2bd8f";
        // 导出数据频率 默认 5 秒每次
        seconds = 5;
        // 查询频率 单位：毫秒
        sleepTime = 300;


        // 查询线程
        executorService.execute(() ->{
            search();
        });

        // 导出线程
        executorService.execute(()->{
            export(true, seconds);
        });

    }




















    /**
     * 查询
     */
    private synchronized void search() {
        // 读取要查询的公司
        List<String> companyList = FileReaderUtils.getCompanyList();
        if (companyList.size() == 0) {
            log.info("没有需要查询的门店");
            return;
        }

        // 设置token  通过小程序抓包获取
        Map<String, String> headers = HeaderUtils.getHeaders(authorization);

        // 先搜索

        for (String companyName : companyList) {
            ISearchService searchService = new SearchServiceImpl(headers);
            try {
                log.info("正在查询：{}", companyName);

                if (sleepTime < 0) {
                    sleepTime = 1;
                }
                Thread.sleep(sleepTime);

                // 默认搜索10个
                List<Search> searchResult = searchService.getSearchResult(companyName);
                // 只取第一个
                Search search = searchResult.get(0);

                // 查询是否已经在库里
                BaseInfo baseInfo = baseInfoMapper.selectById(search.getId());
                if (baseInfo != null) {
                    log.info("查询结果：{}", baseInfo);
                    continue;
                }
                IBaseInfoService baseInfoService = new BaseInfoServiceImpl(headers);
                BaseInfo baseInfoResult = baseInfoService.getBaseInfoResult(search.getId());
                if (baseInfoResult != null) {
                    String s = JsonUtils.toJSONString(baseInfoResult);
                    System.out.println(s);
                    baseInfoMapper.insert(baseInfoResult);
                }
                log.info("查询结果：{}", baseInfo);
            }catch (Exception e) {
                log.error("查询错误, 公司名：{}, 错误信息：{}", companyName, e.getMessage());
            }

        }
        exportFlag = false;
        export(false, 0);
        log.info("查询完成，退出程序");
        executorService.shutdown();

    }

    /**
     * 导出
     */
    private synchronized void export(boolean sleep, int seconds) {
        while (exportFlag) {

            // 导出频率不能小于1秒每次
            if (seconds < 1) {
                seconds = 1;
            }

            // 导出数据
            List<BaseInfo> baseInfos = baseInfoMapper.selectAll();
            EasyExcelUtils.write("查询结果", baseInfos);

            // 判断是否睡眠
            if (sleep) {
                try {
                    Thread.sleep(seconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        }
        //
        if (!sleep) {
            List<BaseInfo> baseInfos = baseInfoMapper.selectAll();
            EasyExcelUtils.write("查询结果", baseInfos);
        }

    }

}
