package com.dongdongshop.service.impl;
import cn.hutool.json.JSONUtil;
import com.dongdongshop.entity.Item;
import com.dongdongshop.entity.OrderItem;
import com.dongdongshop.service.CortService;
import com.dongdongshop.service.IItemService;
import com.dongdongshop.vo.ItemVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.dongdongshop.constants.RedisConstants.CART_REDIS_KEY;

@Service
public class CortServiceImpl implements CortService {

    @DubboReference
    IItemService itemService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<ItemVO> buileItemCookie(List<ItemVO> itemVOList, Long itemId, Integer num) {

        //根据id查询item表
        Item item = itemService.getById(itemId);

        //获取到商家ID
        String sellerId = item.getSellerId();

        //判断用户购物车中是否存在本商家
        ItemVO itemVO = buileIfBySellerId(itemVOList,sellerId);

        if (itemVO==null){
            //说明当前用户购物车中不存在此商家,构造商家和商品数据加入购物车中
            itemVO = new ItemVO();
            itemVO.setSellerID(sellerId);
            itemVO.setSellerName(item.getSeller());
            OrderItem orderItem = buildSellerWithGoods(item,num);
            List<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem);
            itemVO.setOrderItemList(orderItems);
            itemVOList.add(itemVO);
        }else {
            //说明当前用户购物车中存在这个商家,需要判断当前用户购物车中是否存在此商品
            OrderItem orderItem = buileIfByGoodsId(itemVO.getOrderItemList(), itemId);

            if (orderItem == null) {
                //当前购物车中不存在这个商品,构造商品数据添加
                OrderItem orderItem1 = buildSellerWithGoods(item, num);
                itemVO.getOrderItemList().add(orderItem1);
            }

            if (orderItem != null) {
                //当前购物车中存在此商品,数量和价格改变
                orderItem.setNum(orderItem.getNum() + num);
                orderItem.setTotalFee(new BigDecimal(orderItem.getNum() * orderItem.getPrice().doubleValue()));
            }

            if (orderItem.getNum() <= 0) {
                //删除此商品
                itemVO.getOrderItemList().remove(orderItem);
            }

            if (itemVO.getOrderItemList().size() == 0) {
                //移除商家
                itemVOList.remove(itemVO);
            }

        }
        return itemVOList;
    }

    //查询redis购物车数据
    @Override
    public List<ItemVO> getCartByRedis(Long id) {
        String key = CART_REDIS_KEY+id;

        List<String> catrTypeList = new ArrayList<>();

        catrTypeList = stringRedisTemplate.opsForList().range(key,0,-1);

        List<ItemVO> itemVOList = new ArrayList<>();

        //判断缓存是否命中
        if (catrTypeList.isEmpty()){
            //命中直接返回
            return itemVOList;
        }

        catrTypeList.stream().forEach(s -> {
            ItemVO itemVO = JSONUtil.toBean(s, ItemVO.class);
            itemVOList.add(itemVO);
        });

        return itemVOList;
    }

    //合并redis和cookie购物车数据
    @Override
    public List<ItemVO> buildCartCookieWithRedis(List<ItemVO> list1, List<ItemVO> list2) {
        for (ItemVO itemVO : list2) {
            for (OrderItem orderItem : itemVO.getOrderItemList()) {
                list1 = buileItemCookie(list1,orderItem.getItemId(),orderItem.getNum());
            }
        }
        return list1;
    }

    /**
     * 用户已登录,将cookie的数据合并到redis
     * @param id  用户ID
     * @param itemVOList  cookie中的购物车数据
     */
    @Override
    public void saveCartRedis(Long id, List<ItemVO> itemVOList) {
        String key = CART_REDIS_KEY+id;
        //添加redis缓存
        List<String> catrTypeList = new ArrayList<>();

        for (ItemVO itemVO : itemVOList) {
            String s = JSONUtil.toJsonStr(itemVO);
            catrTypeList.add(s);
        }

        //先删除再增加,防止多条记录
        stringRedisTemplate.delete(key);

        stringRedisTemplate.opsForList().rightPushAll(key,catrTypeList);
    }

    //判断当前是否存在商品
    private OrderItem buileIfByGoodsId(List<OrderItem> orderItemList, Long itemId) {
        return orderItemList.stream().filter(orderItem -> orderItem.getItemId().longValue() == itemId.longValue()).findFirst().orElse(null);
    }

    //构造商品数据
    private OrderItem buildSellerWithGoods(Item item, Integer num) {
        OrderItem orderItem = new OrderItem();
        orderItem.setGoodsId(item.getGoodsId());
        orderItem.setSellerId(item.getSellerId());
        orderItem.setNum(num);
        orderItem.setTitle(item.getTitle());
        orderItem.setItemId(item.getId());
        orderItem.setPrice(item.getPrice());
        orderItem.setPicPath(item.getImage());
        orderItem.setTotalFee(new BigDecimal(item.getPrice().doubleValue()*num));
        return orderItem;
    }

    //判断当前是否存在商家
    private ItemVO buileIfBySellerId(List<ItemVO> itemVOList, String sellerId) {
        return itemVOList.stream().filter(itemVO -> Objects.equals(itemVO.getSellerID(), sellerId)).findFirst().orElse(null);

    }
}
