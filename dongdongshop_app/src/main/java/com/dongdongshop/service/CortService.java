package com.dongdongshop.service;

import com.dongdongshop.vo.ItemVO;
import java.util.List;

public interface CortService {

    List<ItemVO> buileItemCookie(List<ItemVO> itemVOList, Long itemId, Integer num);

    List<ItemVO> getCartByRedis(Long id);

    List<ItemVO> buildCartCookieWithRedis(List<ItemVO> cookie, List<ItemVO> rediscart);

    void saveCartRedis(Long id, List<ItemVO> itemVOList);

}
