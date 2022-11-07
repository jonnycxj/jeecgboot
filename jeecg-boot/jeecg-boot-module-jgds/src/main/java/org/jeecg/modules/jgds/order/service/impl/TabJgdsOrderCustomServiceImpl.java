package org.jeecg.modules.jgds.order.service.impl;

import org.jeecg.modules.jgds.order.entity.TabJgdsOrderCustom;
import org.jeecg.modules.jgds.order.mapper.TabJgdsOrderCustomMapper;
import org.jeecg.modules.jgds.order.service.ITabJgdsOrderCustomService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: Tab订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class TabJgdsOrderCustomServiceImpl extends ServiceImpl<TabJgdsOrderCustomMapper, TabJgdsOrderCustom> implements ITabJgdsOrderCustomService {
	
	@Autowired
	private TabJgdsOrderCustomMapper tabJgdsOrderCustomMapper;
	
	@Override
	public List<TabJgdsOrderCustom> selectByMainId(String mainId) {
		return tabJgdsOrderCustomMapper.selectByMainId(mainId);
	}
}
