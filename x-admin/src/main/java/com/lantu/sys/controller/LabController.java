package com.lantu.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Lab;
import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.LabMapper;
import com.lantu.sys.service.ILabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author laocai
 * @since 2024-03-03
 */
@RestController
@RequestMapping("/sys/lab")
public class LabController {

    @Autowired
    ILabService labService;
    @Autowired
    LabMapper labMapper;

    @GetMapping("/list")
    public Result<Map<String,Object>> getLabList(@RequestParam(value = "lname",required = false) String lname,
                                                 @RequestParam(value = "lid",required = false) Integer lid){
        QueryWrapper wrapper = new QueryWrapper();
        if(lid != null)
            wrapper.eq("lid",lid);
        if(lname != null)
            wrapper.like("lname", lname);
        List<Lab> list = labMapper.selectList(wrapper);
        List<Lab> list1 = labService.list();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> data1 = new HashMap<>();
        data.put("total",list.size());
        data.put("rows",list);
        data1.put("total",list1.size());
        data1.put("rows",list1);
        if(lname == null && lid == null)
            return Result.success(20000,data1,"成功");
        else
            return Result.success(20000,data,"成功");
    }
    @PostMapping
    public Result<?> addLab(@RequestBody Lab lab){
        labService.save(lab);
        return Result.success("新增实验室成功");
    }

    @PutMapping("/set")
    public Result<?> updateLab(@RequestBody Lab lab){
        labService.updateById(lab);
        return Result.success("修改实验室成功");
    }
    @PutMapping("/borrow")
    public Result<?> updateLabs(@RequestBody Lab lab){
        labService.updateById(lab);
        return Result.success("预约实验室成功");
    }
    @GetMapping("/{id}")
    public Result<Lab> getLabById(@PathVariable("id") Integer id){
        Lab lab = labService.getById(id);
        return Result.success(lab);
    }

    @DeleteMapping("/{id}")
    public Result<Lab> deleteLabById(@PathVariable("id") Integer id){
        labService.removeById(id);
        return Result.success("删除实验室成功");
    }
}
