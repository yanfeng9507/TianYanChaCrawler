package cn.xiaoyanol.crawler.domain.search;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-23
 * @Time: 下午6:45
 */
public class SearchMatchField {

    private String field;
    private String content;
    public void setField(String field) {
        this.field = field;
    }
    public String getField() {
        return field;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

}
