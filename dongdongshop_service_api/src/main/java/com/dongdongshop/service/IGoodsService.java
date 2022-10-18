package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Goods;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.vo.GoodsVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 商品表，SPU表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-16
 */
public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> listGoodsVo(String goodsName);

    boolean updateAuditStatus(Long[] ids,String auditStatus);

}
