package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderGoods;
import org.jeecg.modules.jgds.order.mapper.JUXEJgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.service.IJUXEJgdsOrderGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: JUXE订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class JUXEJgdsOrderGoodsServiceImpl extends ServiceImpl<JUXEJgdsOrderGoodsMapper, JUXEJgdsOrderGoods> implements IJUXEJgdsOrderGoodsService {
	
	@Autowired
	private JUXEJgdsOrderGoodsMapper jUXEJgdsOrderGoodsMapper;
	
	@Override
	public List<JUXEJgdsOrderGoods> selectByMainId(String mainId) {
		return jUXEJgdsOrderGoodsMapper.selectByMainId(mainId);
	}
}
