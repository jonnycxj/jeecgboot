package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.TabJgdsOrderGoods;
import org.jeecg.modules.jgds.order.mapper.TabJgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.service.ITabJgdsOrderGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: Tab订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class TabJgdsOrderGoodsServiceImpl extends ServiceImpl<TabJgdsOrderGoodsMapper, TabJgdsOrderGoods> implements ITabJgdsOrderGoodsService {
	
	@Autowired
	private TabJgdsOrderGoodsMapper tabJgdsOrderGoodsMapper;
	
	@Override
	public List<TabJgdsOrderGoods> selectByMainId(String mainId) {
		return tabJgdsOrderGoodsMapper.selectByMainId(mainId);
	}
}
