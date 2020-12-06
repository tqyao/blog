package cn.tqyao.blog.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.Scanner;

/**
 * @author Tanqy
 * @since 2019-12-24
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + "表名" + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + "表名" + "！");
    }


    public static void main(String[] args) {
        String scanner = scanner();
        AutoGenerator autoGenerator = new AutoGenerator();
        CodeGenerator generator = new CodeGenerator();
        generator.setDataSource(autoGenerator);
        generator.setGlobalConfig(autoGenerator,GeneratorPathConstant.MODULE_BLOG_REPOSITORY,true,true, true);
        generator.setPackageConfig(autoGenerator,"cn.tqyao.blog","dao", true);
        generator.setStrategy(autoGenerator, scanner, true);
        generator.setTemplateConfig(autoGenerator, false);
        generator.setCfg(autoGenerator,
                GeneratorPathConstant.MODULE_BLOG_REPOSITORY,
                GeneratorPathConstant.XML_MAPPER_PACKAGE_DAO, "Dao");
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();

        //import cn.tqyao.blog.web.null.Article;
        AutoGenerator autoGenerator2 = new AutoGenerator();
        CodeGenerator generator2 = new CodeGenerator();
        generator2.setDataSource(autoGenerator2);
        generator2.setGlobalConfig(autoGenerator2,GeneratorPathConstant.MODULE_BLOG_WEB,false,false, false);
        generator2.setPackageConfig(autoGenerator2,"cn.tqyao.blog.web","mapper", false);
        generator2.setStrategy(autoGenerator2, scanner, false);
        generator2.setTemplateConfig(autoGenerator2, true);
        generator2.setCfg(autoGenerator2,
                GeneratorPathConstant.MODULE_BLOG_WEB,
                GeneratorPathConstant.XML_MAPPER_PACKAGE_MAPPER, "Mapper");
        autoGenerator2.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator2.execute();
    }

    private void setDataSource(AutoGenerator autoGenerator){
        // 设置数据源
        autoGenerator.
                setDataSource(new DataSourceConfig()
                        .setDriverName(GeneratorPathConstant.DRIVER_NAME)
                        // 设置数据库类型
                        .setDbType(DbType.MYSQL)
                        .setUsername(GeneratorPathConstant.USERNAME)
                        .setPassword(GeneratorPathConstant.PASSWORD)
                        .setUrl(GeneratorPathConstant.DB_URL)
                        .setTypeConvert(new MySqlTypeConvert() {
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
                );
    }

    private void setGlobalConfig(AutoGenerator autoGenerator, String modulePath,
                                        boolean baseColumn,boolean baseResultMap, boolean flag){
        GlobalConfig globalConfig = new GlobalConfig()
                // 输出目录
                .setOutputDir(modulePath + "/src/main/java")
                // 是否覆盖
                .setFileOverride(true)
                //swagger注解
                .setSwagger2(true)
                //时间格式
                .setDateType(DateType.ONLY_DATE)
                // 开启AR模式
                .setActiveRecord(false)
                // XML二级缓存
                .setEnableCache(false)
                // 生成ResultMap
                .setBaseResultMap(baseResultMap)
                // 生成 sql片段
                .setBaseColumnList(baseColumn)
                // 自动打开生成后的文件夹
                .setOpen(false)
                // 所有文件的生成者
                .setAuthor("-Tanqy");
//                // 自定义文件命名,%s会自动填充表实体类名字
//                .setMapperName("%sMapper")
//                .setXmlName("%sMapper")
//                .setServiceName("I%sService")
//                .setServiceImplName("%sServiceImpl")
//                .setControllerName("%sController")
        if (flag) {
            globalConfig.setMapperName("%sDao");
        }else {
            globalConfig.setMapperName("%sMapper");
        }
        // 全局配置
        autoGenerator.setGlobalConfig(globalConfig);
    }

    private void setPackageConfig(AutoGenerator autoGenerator, String parentPackage, String md,boolean flag){
        // 包配置
        PackageConfig packageConfig = new PackageConfig()
                // 基本包路径
                .setParent(parentPackage)
//                .setModuleName()
//                .setModuleName(scanner("模块名"))
                // 设置Mapper包名
                .setMapper(md);
//                // 设置Controller包名
//                .setController("controller")
                // 设置entity包名
//                .setEntity("entity");
//                // 设置Service包名
//                .setService("service")
//                // 设置Service实现类包名
//                .setServiceImpl("service.impl");

        if (flag) {
            packageConfig.setController(null);
        }else {
            packageConfig.setEntity(null);
        }

        autoGenerator.setPackageInfo(packageConfig);

    }

    private void setStrategy(AutoGenerator autoGenerator,String tableName,boolean flag){
        StrategyConfig strategyConfig = new StrategyConfig()
                // 需要生成的表
                .setInclude(tableName)
                .setTablePrefix("b_")
                // 实体类使用Lombok
                .setEntityLombokModel(flag)
                // 表名生成策略,下划线转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass("cn.tqyao.blog.base.BaseEntity")
                .setSuperEntityColumns("id", "deleted", "create_time", "update_time")
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
                .setLogicDeleteFieldName("deleted");


        // 策略配置
        autoGenerator.setStrategy(strategyConfig);
    }

    private void setTemplateConfig(AutoGenerator autoGenerator,boolean flag){
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//         templateConfig.setEntity("templates/entity.java");
        if (flag) {
            templateConfig.setEntity(null);
        }else {
            templateConfig.setService(null);
            templateConfig.setServiceImpl(null);
            templateConfig.setController(null);
        }

        //        templateConfig.setMapper(null);
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
    }

    private void setCfg(AutoGenerator autoGenerator, String modulePath, String xmlPath, String md){
        // 注入自定义配置
        autoGenerator.setCfg(new InjectionConfig() {
            @Override
            public void initMap() {
//                Map<String, Object> map = new HashMap<>(1);
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.singletonList(
                new FileOutConfig("/templates/mapper.xml.ftl") {
                    // 自定义Mapper.xml输出路径
                    //packageConfig.getModuleName()
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return modulePath + "/src/main/resources/" + xmlPath
                                + "/"  + tableInfo.getEntityName() + md + StringPool.DOT_XML;
                    }
                })));
    }

}
