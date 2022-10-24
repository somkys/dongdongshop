package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Cities;

import java.util.List;

/**
 * <p>
 * 行政区域地州市信息表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
public interface ICitiesService extends IService<Cities> {

    List<Cities> city(String provinceid);

}
