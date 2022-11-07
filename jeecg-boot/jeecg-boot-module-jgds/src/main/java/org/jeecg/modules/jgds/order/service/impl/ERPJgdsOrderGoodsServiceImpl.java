package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderGoods;
import org.jeecg.modules.jgds.order.mapper.ERPJgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.service.IERPJgdsOrderGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: ERP订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ERPJgdsOrderGoodsServiceImpl extends ServiceImpl<ERPJgdsOrderGoodsMapper, ERPJgdsOrderGoods> implements IERPJgdsOrderGoodsService {
	
	@Autowired
	private ERPJgdsOrderGoodsMapper eRPJgdsOrderGoodsMapper;
	
	@Override
	public List<ERPJgdsOrderGoods> selectByMainId(String mainId) {
		return eRPJgdsOrderGoodsMapper.selectByMainId(mainId);
	}
}
