package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.NQJgdsOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: NQ订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface INQJgdsOrderGoodsService extends IService<NQJgdsOrderGoods> {

	public List<NQJgdsOrderGoods> selectByMainId(String mainId);
}
