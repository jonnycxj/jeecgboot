package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.JgdsOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IJgdsOrderGoodsService extends IService<JgdsOrderGoods> {

	public List<JgdsOrderGoods> selectByMainId(String mainId);
}
