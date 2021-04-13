package cn.tqyao.blog.ui.server;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;
import com.google.common.base.Strings;

public class GeberatorUIServer {
    public static void main(String[] args) {
        GeneratorConfig config = GeneratorConfig.builder().jdbcUrl("jdbc:mysql://47.112.114.47:3306/blog")
                .userName("root")
                .password("790461387")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                //数据库schema，POSTGRE_SQL,ORACLE,DB2类型的数据库需要指定
                .schemaName("myBusiness")
                //如果需要修改各类生成文件的默认命名规则，可自定义一个NameConverter实例，覆盖相应的名称转换方法：
                .nameConverter(new NameConverter () {

                    @Override
                    public String entityNameConvert(String tableName) {

                        if (Strings.isNullOrEmpty(tableName)) {
                            return "";
                        }
                        if (tableName.contains("_")) {
                            return StrUtil.upperFirst(StrUtil.toCamelCase(tableName.toLowerCase()));
                        }
                        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName.toLowerCase()));
                    }

                    @Override
                    public String mapperNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Dao";
                    }

                    /**
                     * 自定义Service类文件的名称规则
                     */
                    @Override
                    public String serviceNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Service";
                    }
                    /**
                     * 自定义Controller类文件的名称规则
                     */
                    @Override
                    public String controllerNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Controller";
                    }
                })
                .typeConvert(new MySqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        System.out.println("转换类型：" + fieldType);

                        String t = fieldType.toLowerCase();
                        if (t.contains("tinyint(1)")) {
                            return DbColumnType.INTEGER;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                })
                .basePackage("cn.tqyao.blog")
                .port(8068)
                .build();
        MybatisPlusToolsApplication.run(config);
    }

}
