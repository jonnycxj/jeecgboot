package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.ERPJgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.service.IERPJgdsOrderCustomService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: ERP订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ERPJgdsOrderCustomServiceImpl extends ServiceImpl<ERPJgdsOrderCustomMapper, ERPJgdsOrderCustom> implements IERPJgdsOrderCustomService {
	
	@Autowired
	private ERPJgdsOrderCustomMapper eRPJgdsOrderCustomMapper;
	
	@Override
	public List<ERPJgdsOrderCustom> selectByMainId(String mainId) {
		return eRPJgdsOrderCustomMapper.selectByMainId(mainId);
	}
}
