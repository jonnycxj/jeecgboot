package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.TabJgdsOrderCustom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: Tab订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface ITabJgdsOrderCustomService extends IService<TabJgdsOrderCustom> {

	public List<TabJgdsOrderCustom> selectByMainId(String mainId);
}
