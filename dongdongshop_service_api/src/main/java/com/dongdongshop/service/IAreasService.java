package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Areas;

import java.util.List;

/**
 * <p>
 * 行政区域县区信息表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
public interface IAreasService extends IService<Areas> {

    List<Areas> listAreas(String cityid);

}
