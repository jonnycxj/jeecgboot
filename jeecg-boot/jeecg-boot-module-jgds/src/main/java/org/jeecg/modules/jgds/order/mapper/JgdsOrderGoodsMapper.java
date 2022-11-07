package org.jeecg.modules.jgds.order.mapper;

import java.util.List;
import org.jeecg.modules.jgds.order.entity.JgdsOrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface JgdsOrderGoodsMapper extends BaseMapper<JgdsOrderGoods> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<JgdsOrderGoods> selectByMainId(@Param("mainId") String mainId);
}
