package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.JgdsOrderCustom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IJgdsOrderCustomService extends IService<JgdsOrderCustom> {

	public List<JgdsOrderCustom> selectByMainId(String mainId);
}
