/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.service;

import cn.tqyao.blog.entity.Member;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/13 9:00 <br>
 */
public interface IMemberCacheService {
    /**
     * 删除会员用户缓存
     */
    void delMember(String memberId);

    /**
     * 获取会员用户缓存
     */
    Member getMember(String username);

    /**
     * 设置会员用户缓存
     */
    void setMember(Member member);


}
