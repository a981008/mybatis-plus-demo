package com.wang.project.generator;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

/**
 * MybatisPlus 代码生成器
 *
 * @author Wang
 * @since 2022/1/22
 */
//执行 main 方法，控制台输入模块表名，回车自动生成对应项目目录中
@SpringBootApplication
public class MybatisPlusCodeGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir") + "/mp-03-project/src/main/java";
        String url = "jdbc:mysql://localhost:3306/project?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "88888888";
        FastAutoGenerator
                .create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Wang") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wang.project"); // 设置父包名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
