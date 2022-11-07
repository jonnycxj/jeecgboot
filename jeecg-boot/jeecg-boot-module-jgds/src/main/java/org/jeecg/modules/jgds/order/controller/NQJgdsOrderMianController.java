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
import org.jeecg.modules.jgds.order.entity.NQJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.NQJgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.NQJgdsOrderMian;
import org.jeecg.modules.jgds.order.vo.NQJgdsOrderMianPage;
import org.jeecg.modules.jgds.order.service.INQJgdsOrderMianService;
import org.jeecg.modules.jgds.order.service.INQJgdsOrderGoodsService;
import org.jeecg.modules.jgds.order.service.INQJgdsOrderCustomService;
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
 * @Description: NQ商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Api(tags="NQ商品订单")
@RestController
@RequestMapping("/order/nQJgdsOrderMian")
@Slf4j
public class NQJgdsOrderMianController {
	@Autowired
	private INQJgdsOrderMianService nQJgdsOrderMianService;
	@Autowired
	private INQJgdsOrderGoodsService nQJgdsOrderGoodsService;
	@Autowired
	private INQJgdsOrderCustomService nQJgdsOrderCustomService;
	
	/**
	 * 分页列表查询
	 *
	 * @param nQJgdsOrderMian
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "NQ商品订单-分页列表查询")
	@ApiOperation(value="NQ商品订单-分页列表查询", notes="NQ商品订单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(NQJgdsOrderMian nQJgdsOrderMian,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<NQJgdsOrderMian> queryWrapper = QueryGenerator.initQueryWrapper(nQJgdsOrderMian, req.getParameterMap());
		Page<NQJgdsOrderMian> page = new Page<NQJgdsOrderMian>(pageNo, pageSize);
		IPage<NQJgdsOrderMian> pageList = nQJgdsOrderMianService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param nQJgdsOrderMianPage
	 * @return
	 */
	@AutoLog(value = "NQ商品订单-添加")
	@ApiOperation(value="NQ商品订单-添加", notes="NQ商品订单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody NQJgdsOrderMianPage nQJgdsOrderMianPage) {
		NQJgdsOrderMian nQJgdsOrderMian = new NQJgdsOrderMian();
		BeanUtils.copyProperties(nQJgdsOrderMianPage, nQJgdsOrderMian);
		nQJgdsOrderMianService.saveMain(nQJgdsOrderMian, nQJgdsOrderMianPage.getNQJgdsOrderGoodsList(),nQJgdsOrderMianPage.getNQJgdsOrderCustomList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param nQJgdsOrderMianPage
	 * @return
	 */
	@AutoLog(value = "NQ商品订单-编辑")
	@ApiOperation(value="NQ商品订单-编辑", notes="NQ商品订单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody NQJgdsOrderMianPage nQJgdsOrderMianPage) {
		NQJgdsOrderMian nQJgdsOrderMian = new NQJgdsOrderMian();
		BeanUtils.copyProperties(nQJgdsOrderMianPage, nQJgdsOrderMian);
		NQJgdsOrderMian nQJgdsOrderMianEntity = nQJgdsOrderMianService.getById(nQJgdsOrderMian.getId());
		if(nQJgdsOrderMianEntity==null) {
			return Result.error("未找到对应数据");
		}
		nQJgdsOrderMianService.updateMain(nQJgdsOrderMian, nQJgdsOrderMianPage.getNQJgdsOrderGoodsList(),nQJgdsOrderMianPage.getNQJgdsOrderCustomList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "NQ商品订单-通过id删除")
	@ApiOperation(value="NQ商品订单-通过id删除", notes="NQ商品订单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		nQJgdsOrderMianService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "NQ商品订单-批量删除")
	@ApiOperation(value="NQ商品订单-批量删除", notes="NQ商品订单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.nQJgdsOrderMianService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "NQ商品订单-通过id查询")
	@ApiOperation(value="NQ商品订单-通过id查询", notes="NQ商品订单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		NQJgdsOrderMian nQJgdsOrderMian = nQJgdsOrderMianService.getById(id);
		if(nQJgdsOrderMian==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(nQJgdsOrderMian);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "NQ订单商品-通过主表ID查询")
	@ApiOperation(value="NQ订单商品-通过主表ID查询", notes="NQ订单商品-通过主表ID查询")
	@GetMapping(value = "/queryNQJgdsOrderGoodsByMainId")
	public Result<?> queryNQJgdsOrderGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<NQJgdsOrderGoods> nQJgdsOrderGoodsList = nQJgdsOrderGoodsService.selectByMainId(id);
		IPage <NQJgdsOrderGoods> page = new Page<>();
		page.setRecords(nQJgdsOrderGoodsList);
		page.setTotal(nQJgdsOrderGoodsList.size());
		return Result.OK(page);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "NQ订单客户-通过主表ID查询")
	@ApiOperation(value="NQ订单客户-通过主表ID查询", notes="NQ订单客户-通过主表ID查询")
	@GetMapping(value = "/queryNQJgdsOrderCustomByMainId")
	public Result<?> queryNQJgdsOrderCustomListByMainId(@RequestParam(name="id",required=true) String id) {
		List<NQJgdsOrderCustom> nQJgdsOrderCustomList = nQJgdsOrderCustomService.selectByMainId(id);
		IPage <NQJgdsOrderCustom> page = new Page<>();
		page.setRecords(nQJgdsOrderCustomList);
		page.setTotal(nQJgdsOrderCustomList.size());
		return Result.OK(page);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param nQJgdsOrderMian
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NQJgdsOrderMian nQJgdsOrderMian) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<NQJgdsOrderMian> queryWrapper = QueryGenerator.initQueryWrapper(nQJgdsOrderMian, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<NQJgdsOrderMian> queryList = nQJgdsOrderMianService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<NQJgdsOrderMian> nQJgdsOrderMianList = new ArrayList<NQJgdsOrderMian>();
      if(oConvertUtils.isEmpty(selections)) {
          nQJgdsOrderMianList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          nQJgdsOrderMianList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<NQJgdsOrderMianPage> pageList = new ArrayList<NQJgdsOrderMianPage>();
      for (NQJgdsOrderMian main : nQJgdsOrderMianList) {
          NQJgdsOrderMianPage vo = new NQJgdsOrderMianPage();
          BeanUtils.copyProperties(main, vo);
          List<NQJgdsOrderGoods> nQJgdsOrderGoodsList = nQJgdsOrderGoodsService.selectByMainId(main.getId());
          vo.setNQJgdsOrderGoodsList(nQJgdsOrderGoodsList);
          List<NQJgdsOrderCustom> nQJgdsOrderCustomList = nQJgdsOrderCustomService.selectByMainId(main.getId());
          vo.setNQJgdsOrderCustomList(nQJgdsOrderCustomList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "NQ商品订单列表");
      mv.addObject(NormalExcelConstants.CLASS, NQJgdsOrderMianPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("NQ商品订单数据", "导出人:"+sysUser.getRealname(), "NQ商品订单"));
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
              List<NQJgdsOrderMianPage> list = ExcelImportUtil.importExcel(file.getInputStream(), NQJgdsOrderMianPage.class, params);
              for (NQJgdsOrderMianPage page : list) {
                  NQJgdsOrderMian po = new NQJgdsOrderMian();
                  BeanUtils.copyProperties(page, po);
                  nQJgdsOrderMianService.saveMain(po, page.getNQJgdsOrderGoodsList(),page.getNQJgdsOrderCustomList());
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
