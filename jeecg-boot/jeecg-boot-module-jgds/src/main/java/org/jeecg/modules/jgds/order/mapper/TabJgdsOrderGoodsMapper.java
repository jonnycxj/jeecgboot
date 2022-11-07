package org.jeecg.modules.jgds.order.mapper;

import java.util.List;
import org.jeecg.modules.jgds.order.entity.TabJgdsOrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: Tab订单商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface TabJgdsOrderGoodsMapper extends BaseMapper<TabJgdsOrderGoods> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<TabJgdsOrderGoods> selectByMainId(@Param("mainId") String mainId);
}
