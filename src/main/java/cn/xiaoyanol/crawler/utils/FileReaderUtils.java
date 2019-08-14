package cn.xiaoyanol.crawler.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2019-08-14
 * @Time: 下午3:09
 */
public class FileReaderUtils {

    public static List<String> getCompanyList() {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        List<String> list = new ArrayList<>();
        try {
            String fileName = FileReaderUtils.class.getClassLoader().getResource("company.txt").getPath();
            reader = new FileReader(fileName);
            bufferedReader = new BufferedReader(reader);

            String companyName = "";
            while ((companyName = bufferedReader.readLine()) != null) {
                list.add(companyName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


    public static void main(String[] args) {
        List<String> companyList = getCompanyList();
        System.out.println(JsonUtils.toJSONString(companyList));
    }
}
