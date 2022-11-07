package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: ERP订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IERPJgdsOrderGoodsService extends IService<ERPJgdsOrderGoods> {

	public List<ERPJgdsOrderGoods> selectByMainId(String mainId);
}
