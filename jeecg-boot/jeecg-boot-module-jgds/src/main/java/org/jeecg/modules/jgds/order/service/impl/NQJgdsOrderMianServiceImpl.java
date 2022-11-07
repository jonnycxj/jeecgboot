package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.NQJgdsOrderMian;
import org.jeecg.modules.jgds.order.entity.NQJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.NQJgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.NQJgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.mapper.NQJgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.mapper.NQJgdsOrderMianMapper;
import org.jeecg.modules.jgds.order.service.INQJgdsOrderMianService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: NQ商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class NQJgdsOrderMianServiceImpl extends ServiceImpl<NQJgdsOrderMianMapper, NQJgdsOrderMian> implements INQJgdsOrderMianService {

	@Autowired
	private NQJgdsOrderMianMapper nQJgdsOrderMianMapper;
	@Autowired
	private NQJgdsOrderGoodsMapper nQJgdsOrderGoodsMapper;
	@Autowired
	private NQJgdsOrderCustomMapper nQJgdsOrderCustomMapper;
	
	@Override
	@Transactional
	public void saveMain(NQJgdsOrderMian nQJgdsOrderMian, List<NQJgdsOrderGoods> nQJgdsOrderGoodsList,List<NQJgdsOrderCustom> nQJgdsOrderCustomList) {
		nQJgdsOrderMianMapper.insert(nQJgdsOrderMian);
		if(nQJgdsOrderGoodsList!=null && nQJgdsOrderGoodsList.size()>0) {
			for(NQJgdsOrderGoods entity:nQJgdsOrderGoodsList) {
				//外键设置
				entity.setOderMainId(nQJgdsOrderMian.getId());
				nQJgdsOrderGoodsMapper.insert(entity);
			}
		}
		if(nQJgdsOrderCustomList!=null && nQJgdsOrderCustomList.size()>0) {
			for(NQJgdsOrderCustom entity:nQJgdsOrderCustomList) {
				//外键设置
				entity.setOrderMainId(nQJgdsOrderMian.getId());
				nQJgdsOrderCustomMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(NQJgdsOrderMian nQJgdsOrderMian,List<NQJgdsOrderGoods> nQJgdsOrderGoodsList,List<NQJgdsOrderCustom> nQJgdsOrderCustomList) {
		nQJgdsOrderMianMapper.updateById(nQJgdsOrderMian);
		
		//1.先删除子表数据
		nQJgdsOrderGoodsMapper.deleteByMainId(nQJgdsOrderMian.getId());
		nQJgdsOrderCustomMapper.deleteByMainId(nQJgdsOrderMian.getId());
		
		//2.子表数据重新插入
		if(nQJgdsOrderGoodsList!=null && nQJgdsOrderGoodsList.size()>0) {
			for(NQJgdsOrderGoods entity:nQJgdsOrderGoodsList) {
				//外键设置
				entity.setOderMainId(nQJgdsOrderMian.getId());
				nQJgdsOrderGoodsMapper.insert(entity);
			}
		}
		if(nQJgdsOrderCustomList!=null && nQJgdsOrderCustomList.size()>0) {
			for(NQJgdsOrderCustom entity:nQJgdsOrderCustomList) {
				//外键设置
				entity.setOrderMainId(nQJgdsOrderMian.getId());
				nQJgdsOrderCustomMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		nQJgdsOrderGoodsMapper.deleteByMainId(id);
		nQJgdsOrderCustomMapper.deleteByMainId(id);
		nQJgdsOrderMianMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			nQJgdsOrderGoodsMapper.deleteByMainId(id.toString());
			nQJgdsOrderCustomMapper.deleteByMainId(id.toString());
			nQJgdsOrderMianMapper.deleteById(id);
		}
	}
	
}
