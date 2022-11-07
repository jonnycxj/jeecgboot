package org.jeecg.modules.jgds.order.mapper;

import java.util.List;
import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: JUXE订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface JUXEJgdsOrderGoodsMapper extends BaseMapper<JUXEJgdsOrderGoods> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<JUXEJgdsOrderGoods> selectByMainId(@Param("mainId") String mainId);
}
