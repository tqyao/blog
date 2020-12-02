package cn.tqyao.blog.security;

import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @author -Tanqy
 * @version 1.0
 * @since 2020/6/24 10:28
 */
public enum JwtTokenTypeEnum {


    ACCESS_TOKEN("access_token"),
    REFRESH_TOKEN("refresh_token");


    @Getter
    String tokenType;

    JwtTokenTypeEnum(String tokenType){
        this.tokenType = tokenType;
    }




}
