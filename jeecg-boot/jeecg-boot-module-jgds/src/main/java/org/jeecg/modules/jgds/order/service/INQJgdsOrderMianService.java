package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.NQJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.NQJgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.NQJgdsOrderMian;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: NQ商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface INQJgdsOrderMianService extends IService<NQJgdsOrderMian> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(NQJgdsOrderMian nQJgdsOrderMian,List<NQJgdsOrderGoods> nQJgdsOrderGoodsList,List<NQJgdsOrderCustom> nQJgdsOrderCustomList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(NQJgdsOrderMian nQJgdsOrderMian,List<NQJgdsOrderGoods> nQJgdsOrderGoodsList,List<NQJgdsOrderCustom> nQJgdsOrderCustomList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
