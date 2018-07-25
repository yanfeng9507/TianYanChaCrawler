package cn.xiaoyanol.crawler;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 把查询到的页面信息中的数据提取到excel表格中
 * @Author: chenyanfeng
 * @Date: 2018-07-21
 * @Time: 下午2:15
 */
public class GetExcel {
    public static void main(String[] args) throws IOException, InvalidFormatException {

        File directory = new File("info");

        if (!directory.exists()) {
            System.out.println("文件夹不存在，程序结束运行");
            return;
        }

        //获取要提取的文件
        String[] files = directory.list();
        List<String> fileNames = new ArrayList<String>();
        for (String file : files) {
            if (file.contains(".html")) {
                fileNames.add(file);
            }
        }


        File xlsxFile = new File("查询结果.xlsx");

        //如果文件不存在，创建文件
        if (!xlsxFile.exists()) {
            //创建一个工作簿
            XSSFWorkbook workbook = new XSSFWorkbook();
            //创建一个工作表
            XSSFSheet sheet = workbook.createSheet("sheet1");

            //初始化第一行信息头
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("搜索公司名");
            row.createCell(1).setCellValue("实际公司名");
            row.createCell(2).setCellValue("工商注册号");
            row.createCell(3).setCellValue("组织机构代码");
            row.createCell(4).setCellValue("统一信用代码");
            row.createCell(5).setCellValue("公司类型");
            row.createCell(6).setCellValue("纳税人识别号");
            row.createCell(7).setCellValue("行业");
            row.createCell(8).setCellValue("营业期限");
            row.createCell(9).setCellValue("核准日期");
            row.createCell(10).setCellValue("纳税人资质");
            row.createCell(11).setCellValue("人员规模");
            row.createCell(12).setCellValue("实缴资本");
            row.createCell(13).setCellValue("登记机关");
            row.createCell(14).setCellValue("参保人数");
            row.createCell(15).setCellValue("英文名称");
            row.createCell(16).setCellValue("注册地址");
            row.createCell(17).setCellValue("经营范围");


            FileOutputStream outputStream = new FileOutputStream(xlsxFile);
            workbook.write(outputStream);
            outputStream.close();
        }


        //打开工作簿
        FileInputStream fileInputStream = new FileInputStream(xlsxFile);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        //获取工作表
        Sheet sheet = workbook.getSheet("sheet1");

        //提取信息
        int rowNum = 1;
        for (String fileName : fileNames) {
            try {
                List<String> messageList = new ArrayList<String>();
                //搜索的公司
                messageList.add(fileName.substring(0, fileName.length()-5));

                File file = new File("info/" + fileName);
                Document document = Jsoup.parse(file, "UTF-8");

                //获取实际查询到的公司名
                Elements h1 = document.select("h1");
                String realCompany = h1.text();
                messageList.add(realCompany);

                Elements tbodys = document.select("tbody");

                Element tbody = tbodys.get(1);
                Elements rows = tbody.select("tr");

                //提取查询到的公司的工商信息
                for (int i = 0; i < rows.size(); i++) {
                    Elements tds = rows.get(i).select("td");
                    for (int j = 0; j < tds.size(); j++) {
                        if (j % 2 == 0) {
                            continue;
                        } else {
                            messageList.add(tds.get(j).text());
                        }
                    }
                }
                Row row1 = sheet.createRow(rowNum++);
                for (int i = 0; i < messageList.size(); i++) {
                    row1.createCell(i).setCellValue(messageList.get(i));
                }
                FileOutputStream outputStream = new FileOutputStream(xlsxFile);
                outputStream.flush();
                workbook.write(outputStream);
                outputStream.close();
                System.out.println("rowNum:"+(rowNum - 1)+" "+ fileName);
            }catch (Exception e) {
                System.out.println(fileName+"-------------------");
            }
        }

    }
}
