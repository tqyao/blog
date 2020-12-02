package cn.tqyao.blog.common.result;

import lombok.Data;

/**
 * 分页封装
 * .<br>
 * @author tanqinyao<br>
 * @date Create in 2020/11/5 11:11  <br>
 * @version 1.0.0 <br>
*/
@Data
public class PageResult<T> extends Result<T> {

    private long total;

    public static <T> PageResult<T> success(T data, Long total) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(ResultCode.SUCCESS.getCode());
        pageResult.setMsg(ResultCode.SUCCESS.getMsg());
        pageResult.setData(data);
        pageResult.setTotal(total);
        return pageResult;
    }

}
