package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.JgdsOrderGoods;
import org.jeecg.modules.jgds.order.mapper.JgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.service.IJgdsOrderGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class JgdsOrderGoodsServiceImpl extends ServiceImpl<JgdsOrderGoodsMapper, JgdsOrderGoods> implements IJgdsOrderGoodsService {
	
	@Autowired
	private JgdsOrderGoodsMapper jgdsOrderGoodsMapper;
	
	@Override
	public List<JgdsOrderGoods> selectByMainId(String mainId) {
		return jgdsOrderGoodsMapper.selectByMainId(mainId);
	}
}
