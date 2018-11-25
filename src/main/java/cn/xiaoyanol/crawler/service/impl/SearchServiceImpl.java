package cn.xiaoyanol.crawler.service.impl;

import cn.xiaoyanol.crawler.constant.UrlConstant;
import cn.xiaoyanol.crawler.domain.search.SearchJsonRootBean;
import cn.xiaoyanol.crawler.service.ISearchService;
import cn.xiaoyanol.crawler.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-23
 * @Time: 下午6:34
 */
public class SearchServiceImpl implements ISearchService {

    private String url = UrlConstant.SEARCH_RUL;

    private static final String DEFAULT_PAGE_NUM = "1";
    private static final String DEFAULT_PAGE_SIZE = "10";
    private static final String DEFAULT_SORT_TYPE = "0";


    @Override
    public SearchJsonRootBean getSearchResult(String companyName, String pageNum, String pageSize, String sortType) {
        try {
            if (StringUtils.isBlank(companyName)) {
                return null;
            }
            // 设置默认参数
            if ( StringUtils.isBlank(pageNum) ) {
                pageNum = DEFAULT_PAGE_NUM;
            }
            if ( StringUtils.isBlank(pageSize) ) {
                pageSize = DEFAULT_PAGE_SIZE;
            }
            if ( StringUtils.isBlank(sortType) ) {
                sortType = DEFAULT_SORT_TYPE;
            }
            HashMap<String, String> paramsMap = new HashMap<String, String>();
            paramsMap.put("pageNum", pageNum);
            paramsMap.put("pageSize", pageSize);
            paramsMap.put("sortType", sortType);

            // 拼接URL
            url += URLEncoder.encode(companyName, "UTF-8");

            HttpResponse response = HttpUtils.get(url, paramsMap);
            String s = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            SearchJsonRootBean searchJsonRootBean = gson.fromJson(s, SearchJsonRootBean.class);

            return searchJsonRootBean;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public SearchJsonRootBean getSearchResult(String companyName, String pageNum, String pageSize) {
        return getSearchResult(companyName, pageNum, pageSize, null);
    }

    @Override
    public SearchJsonRootBean getSearchResult(String companyName) {
        return getSearchResult(companyName, null, null, null);
    }
}
