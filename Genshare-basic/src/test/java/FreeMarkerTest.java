import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {
    @Test
    public void test() throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 设置数字格式
        configuration.setNumberFormat("0.####");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("myweb.html.ftl");

        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2025);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://baike.baidu.com/item/%E6%A8%B1%E6%A1%83%E5%B0%8F%E4%B8%B8%E5%AD%90/224494");
        menuItem1.put("label", "丸子介绍");
        Map<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "https://www.bilibili.com/video/BV1myFXeXEh8/");
        menuItem2.put("label", "第一季 第一集");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems", menuItems);

        // 指定生成的文件
        Writer out = new FileWriter("myweb.html");

        // 生成文件
        template.process(dataModel, out);

        // 生成文件后别忘了关闭哦
        out.close();


    }
}
