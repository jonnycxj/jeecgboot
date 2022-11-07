package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.TabJgdsOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: Tab订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface ITabJgdsOrderGoodsService extends IService<TabJgdsOrderGoods> {

	public List<TabJgdsOrderGoods> selectByMainId(String mainId);
}
