/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/20 10:07 <br>
 */
public enum ArticleCommonDataEnum {

    LIVE_TYPE(0),
    COLLECTION_TYPE(1);

    @Getter
    int code;

    ArticleCommonDataEnum(Integer code){
        this.code = code;
    }

//    public int typeCode(ArticleCommonDataEnum articleCommonDataEnum){
//        for (ArticleCommonDataEnum e : ArticleCommonDataEnum.values()) {
//            if (Objects.equals(e,articleCommonDataEnum)) {
//                return
//            }
//        }
//        return 0;
//    }

}
