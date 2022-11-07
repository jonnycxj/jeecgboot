package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderCustom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: ERP订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IERPJgdsOrderCustomService extends IService<ERPJgdsOrderCustom> {

	public List<ERPJgdsOrderCustom> selectByMainId(String mainId);
}
