package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderMian;
import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.JUXEJgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.mapper.JUXEJgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.mapper.JUXEJgdsOrderMianMapper;
import org.jeecg.modules.jgds.order.service.IJUXEJgdsOrderMianService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: JUXE商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class JUXEJgdsOrderMianServiceImpl extends ServiceImpl<JUXEJgdsOrderMianMapper, JUXEJgdsOrderMian> implements IJUXEJgdsOrderMianService {

	@Autowired
	private JUXEJgdsOrderMianMapper jUXEJgdsOrderMianMapper;
	@Autowired
	private JUXEJgdsOrderGoodsMapper jUXEJgdsOrderGoodsMapper;
	@Autowired
	private JUXEJgdsOrderCustomMapper jUXEJgdsOrderCustomMapper;
	
	@Override
	@Transactional
	public void saveMain(JUXEJgdsOrderMian jUXEJgdsOrderMian, List<JUXEJgdsOrderGoods> jUXEJgdsOrderGoodsList,List<JUXEJgdsOrderCustom> jUXEJgdsOrderCustomList) {
		jUXEJgdsOrderMianMapper.insert(jUXEJgdsOrderMian);
		if(jUXEJgdsOrderGoodsList!=null && jUXEJgdsOrderGoodsList.size()>0) {
			for(JUXEJgdsOrderGoods entity:jUXEJgdsOrderGoodsList) {
				//外键设置
				entity.setOderMainId(jUXEJgdsOrderMian.getId());
				jUXEJgdsOrderGoodsMapper.insert(entity);
			}
		}
		if(jUXEJgdsOrderCustomList!=null && jUXEJgdsOrderCustomList.size()>0) {
			for(JUXEJgdsOrderCustom entity:jUXEJgdsOrderCustomList) {
				//外键设置
				entity.setOrderMainId(jUXEJgdsOrderMian.getId());
				jUXEJgdsOrderCustomMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(JUXEJgdsOrderMian jUXEJgdsOrderMian,List<JUXEJgdsOrderGoods> jUXEJgdsOrderGoodsList,List<JUXEJgdsOrderCustom> jUXEJgdsOrderCustomList) {
		jUXEJgdsOrderMianMapper.updateById(jUXEJgdsOrderMian);
		
		//1.先删除子表数据
		jUXEJgdsOrderGoodsMapper.deleteByMainId(jUXEJgdsOrderMian.getId());
		jUXEJgdsOrderCustomMapper.deleteByMainId(jUXEJgdsOrderMian.getId());
		
		//2.子表数据重新插入
		if(jUXEJgdsOrderGoodsList!=null && jUXEJgdsOrderGoodsList.size()>0) {
			for(JUXEJgdsOrderGoods entity:jUXEJgdsOrderGoodsList) {
				//外键设置
				entity.setOderMainId(jUXEJgdsOrderMian.getId());
				jUXEJgdsOrderGoodsMapper.insert(entity);
			}
		}
		if(jUXEJgdsOrderCustomList!=null && jUXEJgdsOrderCustomList.size()>0) {
			for(JUXEJgdsOrderCustom entity:jUXEJgdsOrderCustomList) {
				//外键设置
				entity.setOrderMainId(jUXEJgdsOrderMian.getId());
				jUXEJgdsOrderCustomMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		jUXEJgdsOrderGoodsMapper.deleteByMainId(id);
		jUXEJgdsOrderCustomMapper.deleteByMainId(id);
		jUXEJgdsOrderMianMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			jUXEJgdsOrderGoodsMapper.deleteByMainId(id.toString());
			jUXEJgdsOrderCustomMapper.deleteByMainId(id.toString());
			jUXEJgdsOrderMianMapper.deleteById(id);
		}
	}
	
}
