package com.bootdo.prepareLessons.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.prepareLessons.domain.LessionsDO;
import com.bootdo.prepareLessons.service.LessionsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * @author zyj
 * @email 1992lcg@163.com
 * @date 2019-04-10 13:38:35
 */

@Controller
@RequestMapping("/prepareLessons/lessions")
public class LessionsController extends BaseController {
	@Autowired
	private LessionsService lessionsService;

    /**
     * 跳转到我的备课界面
     */
	@GetMapping()
	@RequiresPermissions("prepareLessons:lessions:lessions")
	String Lessions(){
	    return "prepareLessons/lessions/lessions";
	}

    /**
     * 跳转到备课统计
     */
    @GetMapping("/count")
    @RequiresPermissions("prepareLessons:lessions:lession_count")
    String LessionsCount(){
        return "prepareLessons/count/lession_count";
    }

    /**
     * 跳转到备课目录设置
     */
    @GetMapping("/set")
    @RequiresPermissions("prepareLessons:lessions:lessionsSet")
    String LessionsSet(){
        return "prepareLessons/set/lessionsSet";
    }



	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("prepareLessons:lessions:lessions")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LessionsDO> lessionsList = lessionsService.list(query);
		int total = lessionsService.count(query);
		PageUtils pageUtils = new PageUtils(lessionsList, total);
		return pageUtils;
	}

    /**
     * 备课统计表格数据展示
     */
    @ResponseBody
    @GetMapping("/listCount")
    @RequiresPermissions("prepareLessons:lessions:lessionsCount")
    public PageUtils listCount(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        //List<CatalogDO> catalogList = lessionsService.listCata(query);
        List<LessionsDO> lessionsList = lessionsService.list(query);
        int total = lessionsService.count(query);
        PageUtils pageUtils = new PageUtils(lessionsList, total);
        return pageUtils;
    }

	@GetMapping("/add")
	String add(){
	    return "prepareLessons/lessions/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("prepareLessons:lessions:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LessionsDO lessions = lessionsService.get(id);
        model.addAttribute("lessions", lessions);
	    return "prepareLessons/lessions/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( LessionsDO lessions) throws ParseException {
		if(lessionsService.save(lessions)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("prepareLessons:lessions:edit")
	public R update( LessionsDO lessions){
		lessionsService.update(lessions);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("prepareLessons:lessions:remove")
	public R remove( Integer id){
		if(lessionsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] ids){
		lessionsService.batchRemove(ids);
		return R.ok();
	}

    /**
     * 查看
     */
    @PostMapping( "/look")
    @ResponseBody
    @RequiresPermissions("prepareLessons:lessions:resetPwd")
    public R look(){
        return R.ok();
    }
}
