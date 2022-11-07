package org.jeecg.modules.jgds.tree.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.jgds.tree.entity.JgdsCesShopType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface JgdsCesShopTypeMapper extends BaseMapper<JgdsCesShopType> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);

}
