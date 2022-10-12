package com.dongdongshop.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class MybatisPlusCodeConfig {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/dongdongshop?useSSL=false&serverTimezone=Asia/Shanghai", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Smoky") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                             .fileOverride() // 覆盖已生成文件
                            .outputDir("F:\\workspace_01\\shiro\\dongdongshop_parent\\dongdongshop_seller_service" + "/src/main/java"); // 指定输出目录
                })
                //
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包路径
                            .moduleName("dongdongshop") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "F:\\workspace_01\\shiro\\dongdongshop_parent\\dongdongshop_seller_service"+"/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tb_item_cat") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_","tb"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
