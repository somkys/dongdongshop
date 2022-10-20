package com.dongdongshop.service.impl;
import com.dongdongshop.entity.Goods;
import com.dongdongshop.entity.GoodsDesc;
import com.dongdongshop.entity.Item;
import com.dongdongshop.service.FreeService;
import com.dongdongshop.service.IGoodsDescService;
import com.dongdongshop.service.IGoodsService;
import com.dongdongshop.service.IItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FreeServiceImpl implements FreeService {

    @Autowired
    FreeMarkerConfigurer freeMarkerConfigurer;

    @DubboReference
    IGoodsService iGoodsService;

    @DubboReference
    IGoodsDescService iGoodsDescService;

    @DubboReference
    IItemService itemService;

    @Override
    public void item(Long goodsId) {
        //加载配置
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        FileWriter fileWriter = null;
        try {
            Template template = configuration.getTemplate("item.ftl");

            Goods goods = iGoodsService.getById(goodsId);
            
            GoodsDesc goodsDesc = iGoodsDescService.getGoodsDescById(goodsId);

            List<Item> itemList = itemService.getItemByGoodsId(goodsId);

            //创建数据模型
            Map map = new HashMap<>();
            map.put("goods",goods);
            map.put("goodsDesc",goodsDesc);
            map.put("item",itemList);
            fileWriter = new FileWriter("F:\\freemarker\\"+goodsId+".html");
            template.process(map,fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileWriter!=null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
