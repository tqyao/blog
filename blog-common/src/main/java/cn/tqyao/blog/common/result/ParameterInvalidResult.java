package cn.tqyao.blog.common.result;

import java.util.List;

/**
 * Controller参数校验 错误返回封装
 *
 * @author shimh
 * <p>
 * 2018年1月23日
 */
public class ParameterInvalidResult {

    private String fieldName;

    private String message;

    private List<String> messages;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
