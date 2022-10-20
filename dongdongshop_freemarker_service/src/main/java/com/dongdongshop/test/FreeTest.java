//package com.dongdongshop.test;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//public class FreeTest {
//    public static void main(String[] args) throws Exception {
////        第一步：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。
//        Configuration configuration = new Configuration(Configuration.getVersion());
////        第二步：设置模板文件所在的路径。
//        configuration.setDirectoryForTemplateLoading(new File("F:\\workspace_01\\shiro\\dongdongshop_parent\\dongdongshop_freemarker_service\\src\\main\\resources"));
////        第三步：设置模板文件使用的字符集。一般就是 utf-8.
//        configuration.setDefaultEncoding("utf-8");
////        第四步：加载一个模板，创建一个模板对象。
//        Template template = configuration.getTemplate("test.ftl");
////        第五步：创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map。
//        Map map = new HashMap<>();
//        map.put("name","张花花");
//        map.put("message","大家好,我叫张花花,我宣布个事,我是傻逼");
//        map.put("flag",true);
////        第六步：创建一个 Writer 对象，一般创建一 FileWriter 对象，指定生成的文件名。
//        FileWriter fileWriter = new FileWriter("F:/html/test.html");
////        第七步：调用模板对象的 process 方法输出文件。
//        template.process(map,fileWriter);
////        第八步：关闭流
//        fileWriter.close();
//    }
//}
