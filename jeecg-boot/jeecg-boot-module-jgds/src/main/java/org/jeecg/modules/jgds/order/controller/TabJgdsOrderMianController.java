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
import org.jeecg.modules.jgds.order.entity.TabJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.TabJgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.TabJgdsOrderMian;
import org.jeecg.modules.jgds.order.vo.TabJgdsOrderMianPage;
import org.jeecg.modules.jgds.order.service.ITabJgdsOrderMianService;
import org.jeecg.modules.jgds.order.service.ITabJgdsOrderGoodsService;
import org.jeecg.modules.jgds.order.service.ITabJgdsOrderCustomService;
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
 * @Description: Tab商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Api(tags="Tab商品订单")
@RestController
@RequestMapping("/order/tabJgdsOrderMian")
@Slf4j
public class TabJgdsOrderMianController {
	@Autowired
	private ITabJgdsOrderMianService tabJgdsOrderMianService;
	@Autowired
	private ITabJgdsOrderGoodsService tabJgdsOrderGoodsService;
	@Autowired
	private ITabJgdsOrderCustomService tabJgdsOrderCustomService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tabJgdsOrderMian
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "Tab商品订单-分页列表查询")
	@ApiOperation(value="Tab商品订单-分页列表查询", notes="Tab商品订单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TabJgdsOrderMian tabJgdsOrderMian,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TabJgdsOrderMian> queryWrapper = QueryGenerator.initQueryWrapper(tabJgdsOrderMian, req.getParameterMap());
		Page<TabJgdsOrderMian> page = new Page<TabJgdsOrderMian>(pageNo, pageSize);
		IPage<TabJgdsOrderMian> pageList = tabJgdsOrderMianService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tabJgdsOrderMianPage
	 * @return
	 */
	@AutoLog(value = "Tab商品订单-添加")
	@ApiOperation(value="Tab商品订单-添加", notes="Tab商品订单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TabJgdsOrderMianPage tabJgdsOrderMianPage) {
		TabJgdsOrderMian tabJgdsOrderMian = new TabJgdsOrderMian();
		BeanUtils.copyProperties(tabJgdsOrderMianPage, tabJgdsOrderMian);
		tabJgdsOrderMianService.saveMain(tabJgdsOrderMian, tabJgdsOrderMianPage.getTabJgdsOrderGoodsList(),tabJgdsOrderMianPage.getTabJgdsOrderCustomList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tabJgdsOrderMianPage
	 * @return
	 */
	@AutoLog(value = "Tab商品订单-编辑")
	@ApiOperation(value="Tab商品订单-编辑", notes="Tab商品订单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TabJgdsOrderMianPage tabJgdsOrderMianPage) {
		TabJgdsOrderMian tabJgdsOrderMian = new TabJgdsOrderMian();
		BeanUtils.copyProperties(tabJgdsOrderMianPage, tabJgdsOrderMian);
		TabJgdsOrderMian tabJgdsOrderMianEntity = tabJgdsOrderMianService.getById(tabJgdsOrderMian.getId());
		if(tabJgdsOrderMianEntity==null) {
			return Result.error("未找到对应数据");
		}
		tabJgdsOrderMianService.updateMain(tabJgdsOrderMian, tabJgdsOrderMianPage.getTabJgdsOrderGoodsList(),tabJgdsOrderMianPage.getTabJgdsOrderCustomList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Tab商品订单-通过id删除")
	@ApiOperation(value="Tab商品订单-通过id删除", notes="Tab商品订单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tabJgdsOrderMianService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "Tab商品订单-批量删除")
	@ApiOperation(value="Tab商品订单-批量删除", notes="Tab商品订单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tabJgdsOrderMianService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Tab商品订单-通过id查询")
	@ApiOperation(value="Tab商品订单-通过id查询", notes="Tab商品订单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TabJgdsOrderMian tabJgdsOrderMian = tabJgdsOrderMianService.getById(id);
		if(tabJgdsOrderMian==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tabJgdsOrderMian);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Tab订单商品通过主表ID查询")
	@ApiOperation(value="Tab订单商品主表ID查询", notes="Tab订单商品-通主表ID查询")
	@GetMapping(value = "/queryTabJgdsOrderGoodsByMainId")
	public Result<?> queryTabJgdsOrderGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<TabJgdsOrderGoods> tabJgdsOrderGoodsList = tabJgdsOrderGoodsService.selectByMainId(id);
		return Result.OK(tabJgdsOrderGoodsList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "Tab订单客户通过主表ID查询")
	@ApiOperation(value="Tab订单客户主表ID查询", notes="Tab订单客户-通主表ID查询")
	@GetMapping(value = "/queryTabJgdsOrderCustomByMainId")
	public Result<?> queryTabJgdsOrderCustomListByMainId(@RequestParam(name="id",required=true) String id) {
		List<TabJgdsOrderCustom> tabJgdsOrderCustomList = tabJgdsOrderCustomService.selectByMainId(id);
		return Result.OK(tabJgdsOrderCustomList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tabJgdsOrderMian
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TabJgdsOrderMian tabJgdsOrderMian) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<TabJgdsOrderMian> queryWrapper = QueryGenerator.initQueryWrapper(tabJgdsOrderMian, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<TabJgdsOrderMian> queryList = tabJgdsOrderMianService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<TabJgdsOrderMian> tabJgdsOrderMianList = new ArrayList<TabJgdsOrderMian>();
      if(oConvertUtils.isEmpty(selections)) {
          tabJgdsOrderMianList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          tabJgdsOrderMianList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<TabJgdsOrderMianPage> pageList = new ArrayList<TabJgdsOrderMianPage>();
      for (TabJgdsOrderMian main : tabJgdsOrderMianList) {
          TabJgdsOrderMianPage vo = new TabJgdsOrderMianPage();
          BeanUtils.copyProperties(main, vo);
          List<TabJgdsOrderGoods> tabJgdsOrderGoodsList = tabJgdsOrderGoodsService.selectByMainId(main.getId());
          vo.setTabJgdsOrderGoodsList(tabJgdsOrderGoodsList);
          List<TabJgdsOrderCustom> tabJgdsOrderCustomList = tabJgdsOrderCustomService.selectByMainId(main.getId());
          vo.setTabJgdsOrderCustomList(tabJgdsOrderCustomList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "Tab商品订单列表");
      mv.addObject(NormalExcelConstants.CLASS, TabJgdsOrderMianPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("Tab商品订单数据", "导出人:"+sysUser.getRealname(), "Tab商品订单"));
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
              List<TabJgdsOrderMianPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TabJgdsOrderMianPage.class, params);
              for (TabJgdsOrderMianPage page : list) {
                  TabJgdsOrderMian po = new TabJgdsOrderMian();
                  BeanUtils.copyProperties(page, po);
                  tabJgdsOrderMianService.saveMain(po, page.getTabJgdsOrderGoodsList(),page.getTabJgdsOrderCustomList());
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
