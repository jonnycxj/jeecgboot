package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderCustom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: JUXE订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IJUXEJgdsOrderCustomService extends IService<JUXEJgdsOrderCustom> {

	public List<JUXEJgdsOrderCustom> selectByMainId(String mainId);
}
