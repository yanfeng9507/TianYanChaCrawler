package cn.xiaoyanol.crawler.service;

import cn.xiaoyanol.crawler.domain.search.Search;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 搜索接口，有分页，默认查询 一页， 十条
 * 返回结果字段含义看实体类注释
 * @see Search
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-23
 * @Time: 下午6:33
 */
public interface ISearchService {

    /**
     * 获取查询结果
     * @param companyName
     * @param pageNum
     * @param pageSize
     * @param sortType
     * @return
     */
    List<Search> getSearchResult(String companyName, String pageNum, String pageSize, String sortType);


    /**
     * 获取查询结果
     * @param companyName
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Search> getSearchResult(String companyName, String pageNum, String pageSize);


    /**
     * 获取查询结果
     * @param companyName
     * @return
     */
    List<Search> getSearchResult(String companyName);



}
