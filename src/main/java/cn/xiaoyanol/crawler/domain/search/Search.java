package cn.xiaoyanol.crawler.domain.search;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2019-08-13
 * @Time: 下午12:50
 */
@Data
public class Search {
    // 公司id
    private Long id;

    // 注册资金
    private String regCapital;

    // 公司名
    private String name;

    // 省份
    private String base;

    // 公司类型 1-公司，2-香港公司，3-社会组织，4-律所，5-事业单位，6-基金会
    private String companyType;

    // 开业时间
    private String estiblishTime;

    // 法人
    private String legalPersonName;

    // 1-公司 2-人
    private Integer type;


}
