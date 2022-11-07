package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: JUXE订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IJUXEJgdsOrderGoodsService extends IService<JUXEJgdsOrderGoods> {

	public List<JUXEJgdsOrderGoods> selectByMainId(String mainId);
}
