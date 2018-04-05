package com.seezoon.framework.modules.system.web;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.seezoon.framework.common.context.beans.ResponeModel;
import com.seezoon.framework.common.web.BaseController;
import com.seezoon.framework.modules.system.entity.SysDict;
import com.seezoon.framework.modules.system.service.SysDictService;

@RestController
@RequestMapping("${admin.path}/sys/dict")
public class SysDictController extends BaseController {

	@Autowired
	private SysDictService sysDictService;

	@PostMapping("/qryPage.do")
	public ResponeModel qryPage(SysDict sysDict) {
		PageInfo<SysDict> page = sysDictService.findByPage(sysDict, sysDict.getPage(), sysDict.getPageSize());
		return ResponeModel.ok(page);
	}

	@RequestMapping("/get.do")
	public ResponeModel get(@RequestParam Serializable id) {
		System.out.println(id);
		SysDict sysDict = sysDictService.findById(id);
		return ResponeModel.ok(sysDict);
	}

	@PostMapping("/save.do")
	public ResponeModel save(@Validated SysDict sysDict, BindingResult bindingResult) {
		int cnt = sysDictService.save(sysDict);
		return ResponeModel.ok(cnt);
	}

	@PostMapping("/update.do")
	public ResponeModel update(@Validated SysDict sysDict,BindingResult bindingResult) {
		int cnt = sysDictService.updateSelective(sysDict);
		return ResponeModel.ok(cnt);
	}

	@PostMapping("/delete.do")
	public ResponeModel delete(@RequestParam Serializable id) {
		int cnt = sysDictService.deleteById(id);
		return ResponeModel.ok(cnt);
	}
	@RequestMapping("/getTypes.do")
	public ResponeModel getTypes() {
		return ResponeModel.ok(sysDictService.findTypes());
	}
}
