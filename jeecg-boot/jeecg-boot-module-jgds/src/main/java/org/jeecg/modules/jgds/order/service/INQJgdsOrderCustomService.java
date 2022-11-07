package org.jeecg.modules.jgds.order.service;

import org.jeecg.modules.jgds.order.entity.NQJgdsOrderCustom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: NQ订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface INQJgdsOrderCustomService extends IService<NQJgdsOrderCustom> {

	public List<NQJgdsOrderCustom> selectByMainId(String mainId);
}
