package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderMian;
import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.ERPJgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.mapper.ERPJgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.mapper.ERPJgdsOrderMianMapper;
import org.jeecg.modules.jgds.order.service.IERPJgdsOrderMianService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: ERP商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ERPJgdsOrderMianServiceImpl extends ServiceImpl<ERPJgdsOrderMianMapper, ERPJgdsOrderMian> implements IERPJgdsOrderMianService {

	@Autowired
	private ERPJgdsOrderMianMapper eRPJgdsOrderMianMapper;
	@Autowired
	private ERPJgdsOrderGoodsMapper eRPJgdsOrderGoodsMapper;
	@Autowired
	private ERPJgdsOrderCustomMapper eRPJgdsOrderCustomMapper;
	
	@Override
	@Transactional
	public void delMain(String id) {
		eRPJgdsOrderGoodsMapper.deleteByMainId(id);
		eRPJgdsOrderCustomMapper.deleteByMainId(id);
		eRPJgdsOrderMianMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			eRPJgdsOrderGoodsMapper.deleteByMainId(id.toString());
			eRPJgdsOrderCustomMapper.deleteByMainId(id.toString());
			eRPJgdsOrderMianMapper.deleteById(id);
		}
	}
	
}
