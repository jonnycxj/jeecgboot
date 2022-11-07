package org.jeecg.modules.jgds.order.vo;

import java.util.List;
import org.jeecg.modules.jgds.order.entity.JgdsOrderMian;
import org.jeecg.modules.jgds.order.entity.JgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.JgdsOrderCustom;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Data
@ApiModel(value="jgds_order_mianPage对象", description="商品订单")
public class JgdsOrderMianPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
	@ApiModelProperty(value = "订单编号")
    private java.lang.String orderCode;
	/**下单时间*/
	@Excel(name = "下单时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "下单时间")
    private java.util.Date xdTime;
	/**订单总额*/
	@Excel(name = "订单总额", width = 15)
	@ApiModelProperty(value = "订单总额")
    private java.lang.Double money;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
    private java.lang.String remark;

	@ExcelCollection(name="订单商品")
	@ApiModelProperty(value = "订单商品")
	private List<JgdsOrderGoods> jgdsOrderGoodsList;
	@ExcelCollection(name="订单客户")
	@ApiModelProperty(value = "订单客户")
	private List<JgdsOrderCustom> jgdsOrderCustomList;

}
