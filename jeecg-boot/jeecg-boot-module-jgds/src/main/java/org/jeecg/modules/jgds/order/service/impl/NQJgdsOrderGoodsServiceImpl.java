package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.NQJgdsOrderGoods;
import org.jeecg.modules.jgds.order.mapper.NQJgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.service.INQJgdsOrderGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: NQ订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class NQJgdsOrderGoodsServiceImpl extends ServiceImpl<NQJgdsOrderGoodsMapper, NQJgdsOrderGoods> implements INQJgdsOrderGoodsService {
	
	@Autowired
	private NQJgdsOrderGoodsMapper nQJgdsOrderGoodsMapper;
	
	@Override
	public List<NQJgdsOrderGoods> selectByMainId(String mainId) {
		return nQJgdsOrderGoodsMapper.selectByMainId(mainId);
	}
}
