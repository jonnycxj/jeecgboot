package org.jeecg.modules.jgds.tree.service;

import org.jeecg.modules.jgds.tree.entity.JgdsCesShopType;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IJgdsCesShopTypeService extends IService<JgdsCesShopType> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addJgdsCesShopType(JgdsCesShopType jgdsCesShopType);
	
	/**修改节点*/
	void updateJgdsCesShopType(JgdsCesShopType jgdsCesShopType) throws JeecgBootException;
	
	/**删除节点*/
	void deleteJgdsCesShopType(String id) throws JeecgBootException;

	/**查询所有数据，无分页*/
    List<JgdsCesShopType> queryTreeListNoPage(QueryWrapper<JgdsCesShopType> queryWrapper);

}
