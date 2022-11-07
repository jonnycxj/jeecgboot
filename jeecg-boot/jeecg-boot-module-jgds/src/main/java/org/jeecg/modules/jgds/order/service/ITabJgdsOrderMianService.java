package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.TabJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.TabJgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.TabJgdsOrderMian;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: Tab商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface ITabJgdsOrderMianService extends IService<TabJgdsOrderMian> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TabJgdsOrderMian tabJgdsOrderMian,List<TabJgdsOrderGoods> tabJgdsOrderGoodsList,List<TabJgdsOrderCustom> tabJgdsOrderCustomList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TabJgdsOrderMian tabJgdsOrderMian,List<TabJgdsOrderGoods> tabJgdsOrderGoodsList,List<TabJgdsOrderCustom> tabJgdsOrderCustomList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
