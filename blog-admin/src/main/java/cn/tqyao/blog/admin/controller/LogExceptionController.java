package cn.tqyao.blog.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import cn.tqyao.blog.admin.service.LogExceptionService;
import cn.tqyao.blog.entity.LogException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 后台管理-异常日志 前端控制器
 * </p>
 *
 * @author tqyao
 * @since 2021-04-13
 */
@Controller
@RequestMapping("/logException")
public class LogExceptionController {


    @Autowired
    private LogExceptionService logExceptionService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<LogException>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<LogException> aPage = logExceptionService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LogException> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(logExceptionService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody LogException params) {
        logExceptionService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        logExceptionService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody LogException params) {
        logExceptionService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
