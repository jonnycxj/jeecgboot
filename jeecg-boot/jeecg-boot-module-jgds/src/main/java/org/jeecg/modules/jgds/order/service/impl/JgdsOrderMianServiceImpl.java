package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.JgdsOrderMian;
import org.jeecg.modules.jgds.order.entity.JgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.JgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.JgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.mapper.JgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.mapper.JgdsOrderMianMapper;
import org.jeecg.modules.jgds.order.service.IJgdsOrderMianService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class JgdsOrderMianServiceImpl extends ServiceImpl<JgdsOrderMianMapper, JgdsOrderMian> implements IJgdsOrderMianService {

	@Autowired
	private JgdsOrderMianMapper jgdsOrderMianMapper;
	@Autowired
	private JgdsOrderGoodsMapper jgdsOrderGoodsMapper;
	@Autowired
	private JgdsOrderCustomMapper jgdsOrderCustomMapper;
	
	@Override
	@Transactional
	public void saveMain(JgdsOrderMian jgdsOrderMian, List<JgdsOrderGoods> jgdsOrderGoodsList,List<JgdsOrderCustom> jgdsOrderCustomList) {
		jgdsOrderMianMapper.insert(jgdsOrderMian);
		if(jgdsOrderGoodsList!=null && jgdsOrderGoodsList.size()>0) {
			for(JgdsOrderGoods entity:jgdsOrderGoodsList) {
				//外键设置
				entity.setOderMainId(jgdsOrderMian.getId());
				jgdsOrderGoodsMapper.insert(entity);
			}
		}
		if(jgdsOrderCustomList!=null && jgdsOrderCustomList.size()>0) {
			for(JgdsOrderCustom entity:jgdsOrderCustomList) {
				//外键设置
				entity.setOrderMainId(jgdsOrderMian.getId());
				jgdsOrderCustomMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(JgdsOrderMian jgdsOrderMian,List<JgdsOrderGoods> jgdsOrderGoodsList,List<JgdsOrderCustom> jgdsOrderCustomList) {
		jgdsOrderMianMapper.updateById(jgdsOrderMian);
		
		//1.先删除子表数据
		jgdsOrderGoodsMapper.deleteByMainId(jgdsOrderMian.getId());
		jgdsOrderCustomMapper.deleteByMainId(jgdsOrderMian.getId());
		
		//2.子表数据重新插入
		if(jgdsOrderGoodsList!=null && jgdsOrderGoodsList.size()>0) {
			for(JgdsOrderGoods entity:jgdsOrderGoodsList) {
				//外键设置
				entity.setOderMainId(jgdsOrderMian.getId());
				jgdsOrderGoodsMapper.insert(entity);
			}
		}
		if(jgdsOrderCustomList!=null && jgdsOrderCustomList.size()>0) {
			for(JgdsOrderCustom entity:jgdsOrderCustomList) {
				//外键设置
				entity.setOrderMainId(jgdsOrderMian.getId());
				jgdsOrderCustomMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		jgdsOrderGoodsMapper.deleteByMainId(id);
		jgdsOrderCustomMapper.deleteByMainId(id);
		jgdsOrderMianMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			jgdsOrderGoodsMapper.deleteByMainId(id.toString());
			jgdsOrderCustomMapper.deleteByMainId(id.toString());
			jgdsOrderMianMapper.deleteById(id);
		}
	}
	
}
