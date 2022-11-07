package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderMian;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: JUXE商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IJUXEJgdsOrderMianService extends IService<JUXEJgdsOrderMian> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(JUXEJgdsOrderMian jUXEJgdsOrderMian,List<JUXEJgdsOrderGoods> jUXEJgdsOrderGoodsList,List<JUXEJgdsOrderCustom> jUXEJgdsOrderCustomList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(JUXEJgdsOrderMian jUXEJgdsOrderMian,List<JUXEJgdsOrderGoods> jUXEJgdsOrderGoodsList,List<JUXEJgdsOrderCustom> jUXEJgdsOrderCustomList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
