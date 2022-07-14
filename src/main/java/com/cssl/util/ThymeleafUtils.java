package com.cssl.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ThymeleafUtils {
    /**
     * 创建出一个name.html文件
     * @param templateName 模板名称
     * @param name 生成name.html
     * @param map 模板映射参数
     */
    public static void createHtml(String templateName, String name, Map<String, Object> map) {
        PrintWriter writer = null;
        try {
            // 1. 创建模板解析器 并设置相关属性
            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            // 配置解析器模板存储文件夹路径
            resolver.setPrefix("/templates/model/");
            // 配置解析器模板文件后缀
            resolver.setSuffix(".html");

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(resolver);

            // 2. 模板上下文 主要存储Model参数
            Context context = new Context();
            context.setVariables(map);

            // 3. 创建输出文件
            File folder = new File("demo/src/main/resources/static/html");
            //如果目录不存在,直接创建
            if (!folder.exists()) {
                folder.mkdir();
            }

            folder = new File("demo/src/main/resources/static/html", name + ".html");

            // 5. 获取输出目标文件输出流
            writer = new PrintWriter(folder, "UTF-8");

            // 6. 生成静态模板参数1:template模板名称  参数2:上下文对象  参数3:目标文件输出流
            templateEngine.process(templateName, context, writer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // flush输出流并关闭
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name","蔬菜列表");
        map.put("content","【现货速发】华为P50 Pro 新品手机 8+256GB 曜金黑【麒麟9000】 官方标配+66W闪充套装,华为（HUAWEI）,,京东,网上购物");
        map.put("array",new String[]{"土豆", "番茄", "白菜", "芹菜"});

        ThymeleafUtils.createHtml("mymodel","result",map);
    }
}
