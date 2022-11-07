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
import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.JUXEJgdsOrderMian;
import org.jeecg.modules.jgds.order.vo.JUXEJgdsOrderMianPage;
import org.jeecg.modules.jgds.order.service.IJUXEJgdsOrderMianService;
import org.jeecg.modules.jgds.order.service.IJUXEJgdsOrderGoodsService;
import org.jeecg.modules.jgds.order.service.IJUXEJgdsOrderCustomService;
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
 * @Description: JUXE商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Api(tags="JUXE商品订单")
@RestController
@RequestMapping("/order/jUXEJgdsOrderMian")
@Slf4j
public class JUXEJgdsOrderMianController {
	@Autowired
	private IJUXEJgdsOrderMianService jUXEJgdsOrderMianService;
	@Autowired
	private IJUXEJgdsOrderGoodsService jUXEJgdsOrderGoodsService;
	@Autowired
	private IJUXEJgdsOrderCustomService jUXEJgdsOrderCustomService;
	
	/**
	 * 分页列表查询
	 *
	 * @param jUXEJgdsOrderMian
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "JUXE商品订单-分页列表查询")
	@ApiOperation(value="JUXE商品订单-分页列表查询", notes="JUXE商品订单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(JUXEJgdsOrderMian jUXEJgdsOrderMian,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<JUXEJgdsOrderMian> queryWrapper = QueryGenerator.initQueryWrapper(jUXEJgdsOrderMian, req.getParameterMap());
		Page<JUXEJgdsOrderMian> page = new Page<JUXEJgdsOrderMian>(pageNo, pageSize);
		IPage<JUXEJgdsOrderMian> pageList = jUXEJgdsOrderMianService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param jUXEJgdsOrderMianPage
	 * @return
	 */
	@AutoLog(value = "JUXE商品订单-添加")
	@ApiOperation(value="JUXE商品订单-添加", notes="JUXE商品订单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JUXEJgdsOrderMianPage jUXEJgdsOrderMianPage) {
		JUXEJgdsOrderMian jUXEJgdsOrderMian = new JUXEJgdsOrderMian();
		BeanUtils.copyProperties(jUXEJgdsOrderMianPage, jUXEJgdsOrderMian);
		jUXEJgdsOrderMianService.saveMain(jUXEJgdsOrderMian, jUXEJgdsOrderMianPage.getJUXEJgdsOrderGoodsList(),jUXEJgdsOrderMianPage.getJUXEJgdsOrderCustomList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param jUXEJgdsOrderMianPage
	 * @return
	 */
	@AutoLog(value = "JUXE商品订单-编辑")
	@ApiOperation(value="JUXE商品订单-编辑", notes="JUXE商品订单-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody JUXEJgdsOrderMianPage jUXEJgdsOrderMianPage) {
		JUXEJgdsOrderMian jUXEJgdsOrderMian = new JUXEJgdsOrderMian();
		BeanUtils.copyProperties(jUXEJgdsOrderMianPage, jUXEJgdsOrderMian);
		JUXEJgdsOrderMian jUXEJgdsOrderMianEntity = jUXEJgdsOrderMianService.getById(jUXEJgdsOrderMian.getId());
		if(jUXEJgdsOrderMianEntity==null) {
			return Result.error("未找到对应数据");
		}
		jUXEJgdsOrderMianService.updateMain(jUXEJgdsOrderMian, jUXEJgdsOrderMianPage.getJUXEJgdsOrderGoodsList(),jUXEJgdsOrderMianPage.getJUXEJgdsOrderCustomList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "JUXE商品订单-通过id删除")
	@ApiOperation(value="JUXE商品订单-通过id删除", notes="JUXE商品订单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		jUXEJgdsOrderMianService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "JUXE商品订单-批量删除")
	@ApiOperation(value="JUXE商品订单-批量删除", notes="JUXE商品订单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jUXEJgdsOrderMianService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "JUXE商品订单-通过id查询")
	@ApiOperation(value="JUXE商品订单-通过id查询", notes="JUXE商品订单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		JUXEJgdsOrderMian jUXEJgdsOrderMian = jUXEJgdsOrderMianService.getById(id);
		if(jUXEJgdsOrderMian==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(jUXEJgdsOrderMian);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "JUXE订单商品通过主表ID查询")
	@ApiOperation(value="JUXE订单商品主表ID查询", notes="JUXE订单商品-通主表ID查询")
	@GetMapping(value = "/queryJUXEJgdsOrderGoodsByMainId")
	public Result<?> queryJUXEJgdsOrderGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<JUXEJgdsOrderGoods> jUXEJgdsOrderGoodsList = jUXEJgdsOrderGoodsService.selectByMainId(id);
		return Result.OK(jUXEJgdsOrderGoodsList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "JUXE订单客户通过主表ID查询")
	@ApiOperation(value="JUXE订单客户主表ID查询", notes="JUXE订单客户-通主表ID查询")
	@GetMapping(value = "/queryJUXEJgdsOrderCustomByMainId")
	public Result<?> queryJUXEJgdsOrderCustomListByMainId(@RequestParam(name="id",required=true) String id) {
		List<JUXEJgdsOrderCustom> jUXEJgdsOrderCustomList = jUXEJgdsOrderCustomService.selectByMainId(id);
		return Result.OK(jUXEJgdsOrderCustomList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param jUXEJgdsOrderMian
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JUXEJgdsOrderMian jUXEJgdsOrderMian) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<JUXEJgdsOrderMian> queryWrapper = QueryGenerator.initQueryWrapper(jUXEJgdsOrderMian, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<JUXEJgdsOrderMian> queryList = jUXEJgdsOrderMianService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<JUXEJgdsOrderMian> jUXEJgdsOrderMianList = new ArrayList<JUXEJgdsOrderMian>();
      if(oConvertUtils.isEmpty(selections)) {
          jUXEJgdsOrderMianList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          jUXEJgdsOrderMianList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<JUXEJgdsOrderMianPage> pageList = new ArrayList<JUXEJgdsOrderMianPage>();
      for (JUXEJgdsOrderMian main : jUXEJgdsOrderMianList) {
          JUXEJgdsOrderMianPage vo = new JUXEJgdsOrderMianPage();
          BeanUtils.copyProperties(main, vo);
          List<JUXEJgdsOrderGoods> jUXEJgdsOrderGoodsList = jUXEJgdsOrderGoodsService.selectByMainId(main.getId());
          vo.setJUXEJgdsOrderGoodsList(jUXEJgdsOrderGoodsList);
          List<JUXEJgdsOrderCustom> jUXEJgdsOrderCustomList = jUXEJgdsOrderCustomService.selectByMainId(main.getId());
          vo.setJUXEJgdsOrderCustomList(jUXEJgdsOrderCustomList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "JUXE商品订单列表");
      mv.addObject(NormalExcelConstants.CLASS, JUXEJgdsOrderMianPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("JUXE商品订单数据", "导出人:"+sysUser.getRealname(), "JUXE商品订单"));
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
              List<JUXEJgdsOrderMianPage> list = ExcelImportUtil.importExcel(file.getInputStream(), JUXEJgdsOrderMianPage.class, params);
              for (JUXEJgdsOrderMianPage page : list) {
                  JUXEJgdsOrderMian po = new JUXEJgdsOrderMian();
                  BeanUtils.copyProperties(page, po);
                  jUXEJgdsOrderMianService.saveMain(po, page.getJUXEJgdsOrderGoodsList(),page.getJUXEJgdsOrderCustomList());
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
