/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.util;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/8 16:33 <br>
 */
public interface GeneratorPathConstant {

    //---------------基本配置信息--------------------------
    /**
     * 项目路径：d:/my project/blog
     */
    String PROJECT_PATH = System.getProperty("user.dir");

    String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    String DB_NAME = "blog";

    String USERNAME = "root";

    String PASSWORD = "790461387";

    String DB_URL = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf8&tinyInt1isBit=false&useSSL=false&useAffectedRows=true&serverTimezone=CTT";

    //------------------模块-----------------------

    String MODULE_BLOG_ADMIN = PROJECT_PATH + "/blog-admin";

    String MODULE_BLOG_COMMON = PROJECT_PATH + "/blog-common";

    String MODULE_BLOG_REPOSITORY = PROJECT_PATH + "/blog-repository";

    String MODULE_SECURITY = PROJECT_PATH + "/blog-security";

    String MODULE_BLOG_WEB = PROJECT_PATH + "/blog-web";

    //-----------------------------------------

    String XML_MAPPER_PACKAGE_DAO = "cn/tqyao/blog/dao";

    String XML_MAPPER_PACKAGE_MAPPER = "mapper";


}
