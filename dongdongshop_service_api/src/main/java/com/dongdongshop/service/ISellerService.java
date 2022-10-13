package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Seller;
import com.dongdongshop.page.PageResult;

/**
 * <p>
 * 商家表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-13
 */
public interface ISellerService extends IService<Seller> {

    Long getCountByName(String sellerId);


    Seller getSellerByLoginname(String loginname);

    PageResult<Seller> getPageSeller(Integer pageNum, Integer pageSize,String name , String nickName);

    Seller selectSellerById(String sellerId);

    boolean updateStatus(String status, String sellerId);

}
