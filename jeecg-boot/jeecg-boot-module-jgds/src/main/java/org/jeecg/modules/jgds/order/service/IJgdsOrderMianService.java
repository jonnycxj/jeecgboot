package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.JgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.JgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.JgdsOrderMian;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IJgdsOrderMianService extends IService<JgdsOrderMian> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(JgdsOrderMian jgdsOrderMian,List<JgdsOrderGoods> jgdsOrderGoodsList,List<JgdsOrderCustom> jgdsOrderCustomList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(JgdsOrderMian jgdsOrderMian,List<JgdsOrderGoods> jgdsOrderGoodsList,List<JgdsOrderCustom> jgdsOrderCustomList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
