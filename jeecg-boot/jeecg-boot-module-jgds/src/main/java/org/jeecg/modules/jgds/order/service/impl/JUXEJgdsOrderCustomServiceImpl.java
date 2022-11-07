package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.JUXEJgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.service.IJUXEJgdsOrderCustomService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: JUXE订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class JUXEJgdsOrderCustomServiceImpl extends ServiceImpl<JUXEJgdsOrderCustomMapper, JUXEJgdsOrderCustom> implements IJUXEJgdsOrderCustomService {
	
	@Autowired
	private JUXEJgdsOrderCustomMapper jUXEJgdsOrderCustomMapper;
	
	@Override
	public List<JUXEJgdsOrderCustom> selectByMainId(String mainId) {
		return jUXEJgdsOrderCustomMapper.selectByMainId(mainId);
	}
}
