package org.jeecg.modules.jgds.order.controller;

import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderGoods;
import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderCustom;
import org.jeecg.modules.jgds.order.entity.ERPJgdsOrderMian;
import org.jeecg.modules.jgds.order.service.IERPJgdsOrderMianService;
import org.jeecg.modules.jgds.order.service.IERPJgdsOrderGoodsService;
import org.jeecg.modules.jgds.order.service.IERPJgdsOrderCustomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

 /**
 * @Description: ERP商品订单
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Api(tags="ERP商品订单")
@RestController
@RequestMapping("/order/eRPJgdsOrderMian")
@Slf4j
public class ERPJgdsOrderMianController extends JeecgController<ERPJgdsOrderMian, IERPJgdsOrderMianService> {

	@Autowired
	private IERPJgdsOrderMianService eRPJgdsOrderMianService;

	@Autowired
	private IERPJgdsOrderGoodsService eRPJgdsOrderGoodsService;

	@Autowired
	private IERPJgdsOrderCustomService eRPJgdsOrderCustomService;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
	 * @param eRPJgdsOrderMian
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ERP商品订单-分页列表查询")
	@ApiOperation(value="ERP商品订单-分页列表查询", notes="ERP商品订单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ERPJgdsOrderMian eRPJgdsOrderMian,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ERPJgdsOrderMian> queryWrapper = QueryGenerator.initQueryWrapper(eRPJgdsOrderMian, req.getParameterMap());
		Page<ERPJgdsOrderMian> page = new Page<ERPJgdsOrderMian>(pageNo, pageSize);
		IPage<ERPJgdsOrderMian> pageList = eRPJgdsOrderMianService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
     *   添加
     * @param eRPJgdsOrderMian
     * @return
     */
    @AutoLog(value = "ERP商品订单-添加")
    @ApiOperation(value="ERP商品订单-添加", notes="ERP商品订单-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ERPJgdsOrderMian eRPJgdsOrderMian) {
        eRPJgdsOrderMianService.save(eRPJgdsOrderMian);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     * @param eRPJgdsOrderMian
     * @return
     */
    @AutoLog(value = "ERP商品订单-编辑")
    @ApiOperation(value="ERP商品订单-编辑", notes="ERP商品订单-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ERPJgdsOrderMian eRPJgdsOrderMian) {
        eRPJgdsOrderMianService.updateById(eRPJgdsOrderMian);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "ERP商品订单-通过id删除")
    @ApiOperation(value="ERP商品订单-通过id删除", notes="ERP商品订单-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        eRPJgdsOrderMianService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "ERP商品订单-批量删除")
    @ApiOperation(value="ERP商品订单-批量删除", notes="ERP商品订单-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.eRPJgdsOrderMianService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ERPJgdsOrderMian eRPJgdsOrderMian) {
        return super.exportXls(request, eRPJgdsOrderMian, ERPJgdsOrderMian.class, "ERP商品订单");
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ERPJgdsOrderMian.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/
	

    /*--------------------------------子表处理-ERP订单商品-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	@AutoLog(value = "ERP订单商品-通过主表ID查询")
	@ApiOperation(value="ERP订单商品-通过主表ID查询", notes="ERP订单商品-通过主表ID查询")
	@GetMapping(value = "/listERPJgdsOrderGoodsByMainId")
    public Result<?> listERPJgdsOrderGoodsByMainId(ERPJgdsOrderGoods eRPJgdsOrderGoods,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<ERPJgdsOrderGoods> queryWrapper = QueryGenerator.initQueryWrapper(eRPJgdsOrderGoods, req.getParameterMap());
        Page<ERPJgdsOrderGoods> page = new Page<ERPJgdsOrderGoods>(pageNo, pageSize);
        IPage<ERPJgdsOrderGoods> pageList = eRPJgdsOrderGoodsService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param eRPJgdsOrderGoods
	 * @return
	 */
	@AutoLog(value = "ERP订单商品-添加")
	@ApiOperation(value="ERP订单商品-添加", notes="ERP订单商品-添加")
	@PostMapping(value = "/addERPJgdsOrderGoods")
	public Result<?> addERPJgdsOrderGoods(@RequestBody ERPJgdsOrderGoods eRPJgdsOrderGoods) {
		eRPJgdsOrderGoodsService.save(eRPJgdsOrderGoods);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param eRPJgdsOrderGoods
	 * @return
	 */
	@AutoLog(value = "ERP订单商品-编辑")
	@ApiOperation(value="ERP订单商品-编辑", notes="ERP订单商品-编辑")
	@PutMapping(value = "/editERPJgdsOrderGoods")
	public Result<?> editERPJgdsOrderGoods(@RequestBody ERPJgdsOrderGoods eRPJgdsOrderGoods) {
		eRPJgdsOrderGoodsService.updateById(eRPJgdsOrderGoods);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ERP订单商品-通过id删除")
	@ApiOperation(value="ERP订单商品-通过id删除", notes="ERP订单商品-通过id删除")
	@DeleteMapping(value = "/deleteERPJgdsOrderGoods")
	public Result<?> deleteERPJgdsOrderGoods(@RequestParam(name="id",required=true) String id) {
		eRPJgdsOrderGoodsService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ERP订单商品-批量删除")
	@ApiOperation(value="ERP订单商品-批量删除", notes="ERP订单商品-批量删除")
	@DeleteMapping(value = "/deleteBatchERPJgdsOrderGoods")
	public Result<?> deleteBatchERPJgdsOrderGoods(@RequestParam(name="ids",required=true) String ids) {
	    this.eRPJgdsOrderGoodsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportERPJgdsOrderGoods")
    public ModelAndView exportERPJgdsOrderGoods(HttpServletRequest request, ERPJgdsOrderGoods eRPJgdsOrderGoods) {
		 // Step.1 组装查询条件
		 QueryWrapper<ERPJgdsOrderGoods> queryWrapper = QueryGenerator.initQueryWrapper(eRPJgdsOrderGoods, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<ERPJgdsOrderGoods> pageList = eRPJgdsOrderGoodsService.list(queryWrapper);
		 List<ERPJgdsOrderGoods> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "ERP订单商品"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ERPJgdsOrderGoods.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("ERP订单商品报表", "导出人:" + sysUser.getRealname(), "ERP订单商品"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importERPJgdsOrderGoods/{mainId}")
    public Result<?> importERPJgdsOrderGoods(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<ERPJgdsOrderGoods> list = ExcelImportUtil.importExcel(file.getInputStream(), ERPJgdsOrderGoods.class, params);
				 for (ERPJgdsOrderGoods temp : list) {
                    temp.setOderMainId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 eRPJgdsOrderGoodsService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-ERP订单商品-end----------------------------------------------*/

    /*--------------------------------子表处理-ERP订单客户-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	@AutoLog(value = "ERP订单客户-通过主表ID查询")
	@ApiOperation(value="ERP订单客户-通过主表ID查询", notes="ERP订单客户-通过主表ID查询")
	@GetMapping(value = "/listERPJgdsOrderCustomByMainId")
    public Result<?> listERPJgdsOrderCustomByMainId(ERPJgdsOrderCustom eRPJgdsOrderCustom,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<ERPJgdsOrderCustom> queryWrapper = QueryGenerator.initQueryWrapper(eRPJgdsOrderCustom, req.getParameterMap());
        Page<ERPJgdsOrderCustom> page = new Page<ERPJgdsOrderCustom>(pageNo, pageSize);
        IPage<ERPJgdsOrderCustom> pageList = eRPJgdsOrderCustomService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param eRPJgdsOrderCustom
	 * @return
	 */
	@AutoLog(value = "ERP订单客户-添加")
	@ApiOperation(value="ERP订单客户-添加", notes="ERP订单客户-添加")
	@PostMapping(value = "/addERPJgdsOrderCustom")
	public Result<?> addERPJgdsOrderCustom(@RequestBody ERPJgdsOrderCustom eRPJgdsOrderCustom) {
		eRPJgdsOrderCustomService.save(eRPJgdsOrderCustom);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param eRPJgdsOrderCustom
	 * @return
	 */
	@AutoLog(value = "ERP订单客户-编辑")
	@ApiOperation(value="ERP订单客户-编辑", notes="ERP订单客户-编辑")
	@PutMapping(value = "/editERPJgdsOrderCustom")
	public Result<?> editERPJgdsOrderCustom(@RequestBody ERPJgdsOrderCustom eRPJgdsOrderCustom) {
		eRPJgdsOrderCustomService.updateById(eRPJgdsOrderCustom);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ERP订单客户-通过id删除")
	@ApiOperation(value="ERP订单客户-通过id删除", notes="ERP订单客户-通过id删除")
	@DeleteMapping(value = "/deleteERPJgdsOrderCustom")
	public Result<?> deleteERPJgdsOrderCustom(@RequestParam(name="id",required=true) String id) {
		eRPJgdsOrderCustomService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ERP订单客户-批量删除")
	@ApiOperation(value="ERP订单客户-批量删除", notes="ERP订单客户-批量删除")
	@DeleteMapping(value = "/deleteBatchERPJgdsOrderCustom")
	public Result<?> deleteBatchERPJgdsOrderCustom(@RequestParam(name="ids",required=true) String ids) {
	    this.eRPJgdsOrderCustomService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportERPJgdsOrderCustom")
    public ModelAndView exportERPJgdsOrderCustom(HttpServletRequest request, ERPJgdsOrderCustom eRPJgdsOrderCustom) {
		 // Step.1 组装查询条件
		 QueryWrapper<ERPJgdsOrderCustom> queryWrapper = QueryGenerator.initQueryWrapper(eRPJgdsOrderCustom, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<ERPJgdsOrderCustom> pageList = eRPJgdsOrderCustomService.list(queryWrapper);
		 List<ERPJgdsOrderCustom> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 mv.addObject(NormalExcelConstants.FILE_NAME, "ERP订单客户"); //此处设置的filename无效 ,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.CLASS, ERPJgdsOrderCustom.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("ERP订单客户报表", "导出人:" + sysUser.getRealname(), "ERP订单客户"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importERPJgdsOrderCustom/{mainId}")
    public Result<?> importERPJgdsOrderCustom(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			 MultipartFile file = entity.getValue();// 获取上传文件对象
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<ERPJgdsOrderCustom> list = ExcelImportUtil.importExcel(file.getInputStream(), ERPJgdsOrderCustom.class, params);
				 for (ERPJgdsOrderCustom temp : list) {
                    temp.setOrderMainId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 eRPJgdsOrderCustomService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-ERP订单客户-end----------------------------------------------*/




}
