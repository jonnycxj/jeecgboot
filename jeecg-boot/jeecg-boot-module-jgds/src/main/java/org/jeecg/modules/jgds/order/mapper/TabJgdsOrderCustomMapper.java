package org.jeecg.modules.jgds.order.mapper;

import java.util.List;
import org.jeecg.modules.jgds.order.entity.TabJgdsOrderCustom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: Tab订单客户
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface TabJgdsOrderCustomMapper extends BaseMapper<TabJgdsOrderCustom> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<TabJgdsOrderCustom> selectByMainId(@Param("mainId") String mainId);
}
