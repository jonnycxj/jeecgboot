package org.jeecg.modules.jgds.order.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.jgds.order.entity.JgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.JgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.JgdsOrderMian;
import org.jeecg.modules.jgds.order.vo.JgdsOrderMianPage;
import org.jeecg.modules.jgds.order.service.IJgdsOrderMianService;
import org.jeecg.modules.jgds.order.service.IJgdsOrderGoodsService;
import org.jeecg.modules.jgds.order.service.IJgdsOrderCustomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Api(tags="商品订单")
@RestController
@RequestMapping("/order/jgdsOrderMian")
@Slf4j
public class JgdsOrderMianController {
	@Autowired
	private IJgdsOrderMianService jgdsOrderMianService;
	@Autowired
	private IJgdsOrderGoodsService jgdsOrderGoodsService;
	@Autowired
	private IJgdsOrderCustomService jgdsOrderCustomService;
	
	/**
	 * 分页列表查询
	 *
	 * @param jgdsOrderMian
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商品订单-分页列表查询")
	@ApiOperation(value="商品订单-分页列表查询", notes="商品订单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(JgdsOrderMian jgdsOrderMian,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<JgdsOrderMian> queryWrapper = QueryGenerator.initQueryWrapper(jgdsOrderMian, req.getParameterMap());
		Page<JgdsOrderMian> page = new Page<JgdsOrderMian>(pageNo, pageSize);
		IPage<JgdsOrderMian> pageList = jgdsOrderMianService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param jgdsOrderMianPage
	 * @return
	 */
	@AutoLog(value = "商品订单-添加")
	@ApiOperation(value="商品订单-添加", notes="商品订单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JgdsOrderMianPage jgdsOrderMianPage) {
		JgdsOrderMian jgdsOrderMian = new JgdsOrderMian();
		BeanUtils.copyProperties(jgdsOrderMianPage, jgdsOrderMian);
		jgdsOrderMianService.saveMain(jgdsOrderMian, jgdsOrderMianPage.getJgdsOrderGoodsList(),jgdsOrderMianPage.getJgdsOrderCustomList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param jgdsOrderMianPage
	 * @return
	 */
	@AutoLog(value = "商品订单-编辑")
	@ApiOperation(value="商品订单-编辑", notes="商品订单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody JgdsOrderMianPage jgdsOrderMianPage) {
		JgdsOrderMian jgdsOrderMian = new JgdsOrderMian();
		BeanUtils.copyProperties(jgdsOrderMianPage, jgdsOrderMian);
		JgdsOrderMian jgdsOrderMianEntity = jgdsOrderMianService.getById(jgdsOrderMian.getId());
		if(jgdsOrderMianEntity==null) {
			return Result.error("未找到对应数据");
		}
		jgdsOrderMianService.updateMain(jgdsOrderMian, jgdsOrderMianPage.getJgdsOrderGoodsList(),jgdsOrderMianPage.getJgdsOrderCustomList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品订单-通过id删除")
	@ApiOperation(value="商品订单-通过id删除", notes="商品订单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		jgdsOrderMianService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商品订单-批量删除")
	@ApiOperation(value="商品订单-批量删除", notes="商品订单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jgdsOrderMianService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品订单-通过id查询")
	@ApiOperation(value="商品订单-通过id查询", notes="商品订单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		JgdsOrderMian jgdsOrderMian = jgdsOrderMianService.getById(id);
		if(jgdsOrderMian==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(jgdsOrderMian);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单商品通过主表ID查询")
	@ApiOperation(value="订单商品主表ID查询", notes="订单商品-通主表ID查询")
	@GetMapping(value = "/queryJgdsOrderGoodsByMainId")
	public Result<?> queryJgdsOrderGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<JgdsOrderGoods> jgdsOrderGoodsList = jgdsOrderGoodsService.selectByMainId(id);
		return Result.OK(jgdsOrderGoodsList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单客户通过主表ID查询")
	@ApiOperation(value="订单客户主表ID查询", notes="订单客户-通主表ID查询")
	@GetMapping(value = "/queryJgdsOrderCustomByMainId")
	public Result<?> queryJgdsOrderCustomListByMainId(@RequestParam(name="id",required=true) String id) {
		List<JgdsOrderCustom> jgdsOrderCustomList = jgdsOrderCustomService.selectByMainId(id);
		return Result.OK(jgdsOrderCustomList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param jgdsOrderMian
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JgdsOrderMian jgdsOrderMian) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<JgdsOrderMian> queryWrapper = QueryGenerator.initQueryWrapper(jgdsOrderMian, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<JgdsOrderMian> queryList = jgdsOrderMianService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<JgdsOrderMian> jgdsOrderMianList = new ArrayList<JgdsOrderMian>();
      if(oConvertUtils.isEmpty(selections)) {
          jgdsOrderMianList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          jgdsOrderMianList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<JgdsOrderMianPage> pageList = new ArrayList<JgdsOrderMianPage>();
      for (JgdsOrderMian main : jgdsOrderMianList) {
          JgdsOrderMianPage vo = new JgdsOrderMianPage();
          BeanUtils.copyProperties(main, vo);
          List<JgdsOrderGoods> jgdsOrderGoodsList = jgdsOrderGoodsService.selectByMainId(main.getId());
          vo.setJgdsOrderGoodsList(jgdsOrderGoodsList);
          List<JgdsOrderCustom> jgdsOrderCustomList = jgdsOrderCustomService.selectByMainId(main.getId());
          vo.setJgdsOrderCustomList(jgdsOrderCustomList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "商品订单列表");
      mv.addObject(NormalExcelConstants.CLASS, JgdsOrderMianPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商品订单数据", "导出人:"+sysUser.getRealname(), "商品订单"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<JgdsOrderMianPage> list = ExcelImportUtil.importExcel(file.getInputStream(), JgdsOrderMianPage.class, params);
              for (JgdsOrderMianPage page : list) {
                  JgdsOrderMian po = new JgdsOrderMian();
                  BeanUtils.copyProperties(page, po);
                  jgdsOrderMianService.saveMain(po, page.getJgdsOrderGoodsList(),page.getJgdsOrderCustomList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
