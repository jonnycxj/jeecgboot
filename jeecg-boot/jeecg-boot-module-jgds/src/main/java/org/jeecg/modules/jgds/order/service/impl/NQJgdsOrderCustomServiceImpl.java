package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.NQJgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.NQJgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.service.INQJgdsOrderCustomService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: NQ订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class NQJgdsOrderCustomServiceImpl extends ServiceImpl<NQJgdsOrderCustomMapper, NQJgdsOrderCustom> implements INQJgdsOrderCustomService {
	
	@Autowired
	private NQJgdsOrderCustomMapper nQJgdsOrderCustomMapper;
	
	@Override
	public List<NQJgdsOrderCustom> selectByMainId(String mainId) {
		return nQJgdsOrderCustomMapper.selectByMainId(mainId);
	}
}
