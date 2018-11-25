package cn.xiaoyanol.crawler;

import cn.xiaoyanol.crawler.domain.baseinfo.BaseInfoJsonRootBean;
import cn.xiaoyanol.crawler.domain.search.SearchJsonRootBean;
import cn.xiaoyanol.crawler.service.IBaseInfoService;
import cn.xiaoyanol.crawler.service.ISearchService;
import cn.xiaoyanol.crawler.service.impl.BaseInfoServiceImpl;
import cn.xiaoyanol.crawler.service.impl.SearchServiceImpl;
import cn.xiaoyanol.crawler.utils.ExcelExportUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

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

        ISearchService searchService = new SearchServiceImpl();
        SearchJsonRootBean searchResult = searchService.getSearchResult("阿里巴巴");
        Gson searchJson = new GsonBuilder().setPrettyPrinting().create();;

        System.out.println(searchJson.toJson(searchResult));


        IBaseInfoService baseInfoService = new BaseInfoServiceImpl();
        BaseInfoJsonRootBean baseInfoResult = baseInfoService.getBaseInfoResult("1698375");

        Gson baseInoJson = new GsonBuilder().setPrettyPrinting().create();;
        System.out.println(baseInoJson.toJson(baseInfoResult));


        List<List> excelData= new ArrayList<List>();
        List<Object> dataList = new ArrayList<Object>();
        dataList.add(baseInfoResult.getData().getName());
        dataList.add(baseInfoResult.getData().getLegalInfo().getName());
        excelData.add(dataList);

        List<String> rowNames = new ArrayList<String>();
        rowNames.add("公司名");
        rowNames.add("法人信息");
        ExcelExportUtils excelExportUtils = new ExcelExportUtils(rowNames, excelData);
        excelExportUtils.exportData();
    }
}
