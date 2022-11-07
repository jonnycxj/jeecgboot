package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderMian;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: ERP商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IERPJgdsOrderMianService extends IService<ERPJgdsOrderMian> {

	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);


}
