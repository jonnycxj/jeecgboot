package org.jeecg.modules.jgds.goods.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 商品
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Data
@TableName("jgds_ces_shop_good")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="jgds_ces_shop_good对象", description="商品")
public class JgdsCesShopGood implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
    @ApiModelProperty(value = "商品名称")
    private java.lang.String name;
	/**价格*/
	@Excel(name = "价格", width = 15)
    @ApiModelProperty(value = "价格")
    private java.math.BigDecimal price;
	/**出厂时间*/
	@Excel(name = "出厂时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出厂时间")
    private java.util.Date chucDate;
	/**商品简介*/
	@Excel(name = "商品简介", width = 15)
    @ApiModelProperty(value = "商品简介")
    private java.lang.String contents;
	/**商品分类*/
	@Excel(name = "商品分类", width = 15, dictTable = "jgds_ces_shop_type", dicText = "name", dicCode = "id")
	@Dict(dictTable = "jgds_ces_shop_type", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "商品分类")
    private java.lang.String goodTypeId;
}
