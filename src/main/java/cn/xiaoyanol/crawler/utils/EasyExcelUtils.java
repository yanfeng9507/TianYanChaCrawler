package cn.xiaoyanol.crawler.utils;

import cn.xiaoyanol.crawler.domain.baseinfo.BaseInfo;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2019-08-13
 * @Time: 下午7:52
 */
@Slf4j
public class EasyExcelUtils {

    public static void write(String fileName, List list) {
        try {

            if (StringUtils.isEmpty(fileName)) {
                throw new RuntimeException("文件名为空");
            }

            fileName = fileName + ".xlsx";

            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
            // 文件输出位置
            OutputStream out = new FileOutputStream(fileName);

            ExcelWriter writer = EasyExcelFactory.getWriter(out);

            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet1 = new Sheet(1, 0, BaseInfo.class);

            // 第一个 sheet 名称
            sheet1.setSheetName("sheet1");

            // 写数据到 Writer 上下文中
            // 入参1: 创建要写入的模型数据
            // 入参2: 要写入的目标 sheet
            writer.write(list, sheet1);

            // 将上下文中的最终 outputStream 写入到指定文件中
            writer.finish();

            // 关闭流
            out.close();
        }catch (Exception e) {

        }
    }
}
