package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.TabJgdsOrderMian;
import org.jeecg.modules.jgds.order.entity.TabJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.TabJgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.TabJgdsOrderGoodsMapper;
import org.jeecg.modules.jgds.order.mapper.TabJgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.mapper.TabJgdsOrderMianMapper;
import org.jeecg.modules.jgds.order.service.ITabJgdsOrderMianService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: Tab商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class TabJgdsOrderMianServiceImpl extends ServiceImpl<TabJgdsOrderMianMapper, TabJgdsOrderMian> implements ITabJgdsOrderMianService {

	@Autowired
	private TabJgdsOrderMianMapper tabJgdsOrderMianMapper;
	@Autowired
	private TabJgdsOrderGoodsMapper tabJgdsOrderGoodsMapper;
	@Autowired
	private TabJgdsOrderCustomMapper tabJgdsOrderCustomMapper;
	
	@Override
	@Transactional
	public void saveMain(TabJgdsOrderMian tabJgdsOrderMian, List<TabJgdsOrderGoods> tabJgdsOrderGoodsList,List<TabJgdsOrderCustom> tabJgdsOrderCustomList) {
		tabJgdsOrderMianMapper.insert(tabJgdsOrderMian);
		if(tabJgdsOrderGoodsList!=null && tabJgdsOrderGoodsList.size()>0) {
			for(TabJgdsOrderGoods entity:tabJgdsOrderGoodsList) {
				//外键设置
				entity.setOderMainId(tabJgdsOrderMian.getId());
				tabJgdsOrderGoodsMapper.insert(entity);
			}
		}
		if(tabJgdsOrderCustomList!=null && tabJgdsOrderCustomList.size()>0) {
			for(TabJgdsOrderCustom entity:tabJgdsOrderCustomList) {
				//外键设置
				entity.setOrderMainId(tabJgdsOrderMian.getId());
				tabJgdsOrderCustomMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(TabJgdsOrderMian tabJgdsOrderMian,List<TabJgdsOrderGoods> tabJgdsOrderGoodsList,List<TabJgdsOrderCustom> tabJgdsOrderCustomList) {
		tabJgdsOrderMianMapper.updateById(tabJgdsOrderMian);
		
		//1.先删除子表数据
		tabJgdsOrderGoodsMapper.deleteByMainId(tabJgdsOrderMian.getId());
		tabJgdsOrderCustomMapper.deleteByMainId(tabJgdsOrderMian.getId());
		
		//2.子表数据重新插入
		if(tabJgdsOrderGoodsList!=null && tabJgdsOrderGoodsList.size()>0) {
			for(TabJgdsOrderGoods entity:tabJgdsOrderGoodsList) {
				//外键设置
				entity.setOderMainId(tabJgdsOrderMian.getId());
				tabJgdsOrderGoodsMapper.insert(entity);
			}
		}
		if(tabJgdsOrderCustomList!=null && tabJgdsOrderCustomList.size()>0) {
			for(TabJgdsOrderCustom entity:tabJgdsOrderCustomList) {
				//外键设置
				entity.setOrderMainId(tabJgdsOrderMian.getId());
				tabJgdsOrderCustomMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		tabJgdsOrderGoodsMapper.deleteByMainId(id);
		tabJgdsOrderCustomMapper.deleteByMainId(id);
		tabJgdsOrderMianMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			tabJgdsOrderGoodsMapper.deleteByMainId(id.toString());
			tabJgdsOrderCustomMapper.deleteByMainId(id.toString());
			tabJgdsOrderMianMapper.deleteById(id);
		}
	}
	
}
