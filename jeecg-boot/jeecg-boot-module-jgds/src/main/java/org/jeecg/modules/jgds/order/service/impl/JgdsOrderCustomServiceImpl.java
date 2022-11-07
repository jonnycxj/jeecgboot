package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.JgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.JgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.service.IJgdsOrderCustomService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class JgdsOrderCustomServiceImpl extends ServiceImpl<JgdsOrderCustomMapper, JgdsOrderCustom> implements IJgdsOrderCustomService {
	
	@Autowired
	private JgdsOrderCustomMapper jgdsOrderCustomMapper;
	
	@Override
	public List<JgdsOrderCustom> selectByMainId(String mainId) {
		return jgdsOrderCustomMapper.selectByMainId(mainId);
	}
}
