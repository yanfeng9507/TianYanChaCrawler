package cn.xiaoyanol.crawler.domain.baseinfo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2019-08-13
 * @Time: 下午5:44
 */
@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class BaseInfo extends BaseRowModel {

    @ExcelProperty(value = "企业id", index = 0)
    private String id;//企业id
    @ExcelProperty(value = "企业名", index = 1)
    private String name;  //企业名
    @ExcelProperty(value = "企业评分", index = 6)
    private String percentileScore;// 万分制	企业评分
    @ExcelProperty(value = "人员规模", index = 11)
    private String staffNumRange; // 人员规模
    @ExcelProperty(value = "经营开始时间", index = 3)
    private String fromTime; // 经营开始时间
    @ExcelProperty(value = "法人类型", index = 4)
    private String type; // 法人类型，1 人 2 公司
    @ExcelProperty(value = "股票名", index = 5)
    private String bondName;//股票名

    @ExcelProperty(value = "是否是小微企业", index = 7)
    private String isMicroEnt; // 是否是小微企业 0不是 1是
    @ExcelProperty(value = "股票曾用名", index = 8)
    private String usedBondName;//股票曾用名
    @ExcelProperty(value = "注册号", index = 9)
    private String regNumber;//注册号
    @ExcelProperty(value = "注册资本", index = 10)
    private String regCapital;//注册资本

    @ExcelProperty(value = "登记机关", index = 12)
    private String regInstitute;//登记机关
    @ExcelProperty(value = "注册地址", index = 13)
    private String regLocation;//注册地址
    @ExcelProperty(value = "行业", index = 14)
    private String industry;//行业
    @ExcelProperty(value = "核准时间", index = 15)
    private String approvedTime;//核准时间
    @ExcelProperty(value = "参保人数", index = 16)
    private String socialStaffNum;//参保人数
    @ExcelProperty(value = "企业标签", index = 17)
    private String tags;//企业标签
    @ExcelProperty(value = "纳税人识别号", index = 18)
    private String taxNumber;//纳税人识别号
    @ExcelProperty(value = "经营范围", index = 19)
    private String businessScope;//经营范围
    @ExcelProperty(value = "英文名", index = 20)
    private String property3;//英文名
    @ExcelProperty(value = "简称", index = 21)
    private String alias;//简称
    @ExcelProperty(value = "组织机构代码", index = 22)
    private String orgNumber;//组织机构代码
    @ExcelProperty(value = "企业状态", index = 23)
    private String regStatus;//企业状态
    @ExcelProperty(value = "成立日期", index = 24)
    private String estiblishTime;//成立日期
    @ExcelProperty(value = "股票类型", index = 25)
    private String bondType;//股票类型
    @ExcelProperty(value = "法人", index = 26)
    private String legalPersonName;//法人
    @ExcelProperty(value = "经营结束时间", index = 27)
    private String toTime;//经营结束时间
    @ExcelProperty(value = "实收注册资金", index = 28)
    private String actualCapital;//实收注册资金
    @ExcelProperty(value = "企业类型", index = 29)
    private String companyOrgType;//企业类型
    @ExcelProperty(value = "省份简称", index = 30)
    private String base;// 省份简称
    @ExcelProperty(value = "统一社会信用代码", index = 31)
    private String creditCode;//统一社会信用代码
    @ExcelProperty(value = "曾用名", index = 32)
    private String historyNames;//曾用名
    @ExcelProperty(value = "股票号", index = 33)
    private String bondNum;//股票号
    @ExcelProperty(value = "注册资本币种", index = 34)
    private String regCapitalCurrency;//注册资本币种 人民币 美元 欧元 等（暂未使用）
    @ExcelProperty(value = "实收注册资本币种", index = 35)
    private String actualCapitalCurrency;//实收注册资本币种 人民币 美元 欧元 等（暂未使用）
    @ExcelProperty(value = "邮箱", index = 36)
    private String email;// 邮箱
    @ExcelProperty(value = "网址", index = 37)
    private String websiteList;//网址
    @ExcelProperty(value = "企业联系方式", index = 38)
    private String phoneNumber;//企业联系方式
    @ExcelProperty(value = "吊销日期", index = 39)
    private String revokeDate;//吊销日期
    @ExcelProperty(value = "吊销原因", index = 40)
    private String revokeReason;//吊销原因
    @ExcelProperty(value = "注销日期", index = 41)
    private String cancelDate;//注销日期
    @ExcelProperty(value = "注销原因", index = 42)
    private String cancelReason;//注销原因
}
