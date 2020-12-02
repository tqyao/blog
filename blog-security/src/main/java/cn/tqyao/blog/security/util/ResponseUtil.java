package cn.tqyao.blog.security.util;

import cn.tqyao.blog.common.result.Result;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class ResponseUtil {

    public static void send(HttpServletResponse response, Result result){
        String json = JSONObject.toJSONString(result);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            out.append(json);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (out != null){
                out.close();
            }
        }
    }
}
