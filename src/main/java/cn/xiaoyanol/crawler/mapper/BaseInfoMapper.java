package cn.xiaoyanol.crawler.mapper;

import cn.xiaoyanol.crawler.domain.baseinfo.BaseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2019-08-14
 * @Time: 下午2:27
 */
@Mapper
@Component
public interface BaseInfoMapper {

//    @Insert("INSERT INTO t_baseinfo(id, name) VALUES(#{id}, #{name})")

    String originField = "id, name, percentile_score, staff_num_range, " +
            "from_time, type, bond_name, is_micro_ent, used_bond_name," +
            " reg_number, reg_capital, reg_institute, reg_location, " +
            "industry, approved_time, social_staff_num, tags, tax_number," +
            " business_scope, property3, alias, org_number, reg_status, estiblish_time, " +
            "bond_type, legal_person_name, to_time, actual_capital, company_org_type," +
            " base, credit_code, history_names, bond_num, reg_capital_currency," +
            " actual_capital_currency, email, website_list, phone_number, " +
            "revoke_date, revoke_reason, cancel_date, cancel_reason";

    String objectFiled = "#{id}, #{name}, #{percentileScore}, #{staffNumRange}, #{fromTime}, " +
            "#{type}, #{bondName}, #{isMicroEnt}, #{usedBondName}, #{regNumber}, #{regCapital}, " +
            "#{regInstitute}, #{regLocation}, #{industry}, #{approvedTime}, #{socialStaffNum}," +
            "#{tags}, #{taxNumber}, #{businessScope}, #{property3}, #{alias}, #{orgNumber}, #{regStatus}, " +
            "#{estiblishTime}, #{bondType}, #{legalPersonName}, #{toTime}, #{actualCapital}, " +
            "#{companyOrgType}, #{base}, #{creditCode}, #{historyNames}, #{bondNum}, #{regCapitalCurrency}, " +
            "#{actualCapitalCurrency}, #{email}, #{websiteList}, #{phoneNumber}, #{revokeDate}, " +
            "#{revokeReason}, #{cancelDate}, #{cancelReason}";

    String selectSQL = "id, name, percentile_score as percentileScore, staff_num_range as staffNumRange, " +
            "from_time as fromTime, type, bond_name as bondName, is_micro_ent as isMicroEnt, used_bond_name as usedBondName," +
            " reg_number as regNumber, reg_capital as regCapital, reg_institute as regInstitute, reg_location as regLocation, " +
            "industry, approved_time as approvedTime, social_staff_num as socialStaffNum, tags, tax_number as taxNumber," +
            " business_scope as businessScope, property3, alias, org_number as orgNumber, reg_status as regStatus, estiblish_time as estiblishTime , " +
            "bond_type as bondType, legal_person_name as legalPersonName, to_time as toTime, actual_capital as actualCapital, company_org_type as companyOrgType," +
            " base, credit_code as creditCode, history_names as historyNames, bond_num as bondNum, reg_capital_currency as regCapitalCurrency," +
            " actual_capital_currency as actualCapitalCurrency, email, website_list as websiteList, phone_number as phoneNumber, " +
            "revoke_date as revokeDate, revoke_reason as revokeReason, cancel_date as cancelDate, cancel_reason as cancelReason";

    @Insert("INSERT INTO t_baseinfo("+originField+") VALUES("+objectFiled+")")
    int insert(BaseInfo baseInfo);


    @Select("select "+selectSQL+" from t_baseinfo where id=#{id}")
    BaseInfo selectById(@Param("id") Long id);

    @Select("select "+selectSQL+" from t_baseinfo")
    List<BaseInfo> selectAll();
}
