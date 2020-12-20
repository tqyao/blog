/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.web.service.HomeService;
import cn.tqyao.blog.web.service.IArticleService;
import cn.tqyao.blog.web.service.IMemberService;
import cn.tqyao.blog.web.vo.HomeVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/18 11:56 <br>
 */
@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService {

    private IMemberService memberService;
    private IArticleService articleService;


    @Override
    public HomeVO getHomeContent() {

        return null;
    }
}
