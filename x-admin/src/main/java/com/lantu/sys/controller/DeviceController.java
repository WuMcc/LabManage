package com.lantu.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Device;
import com.lantu.sys.entity.Lab;
import com.lantu.sys.mapper.DeviceMapper;
import com.lantu.sys.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author laocai
 * @since 2024-03-02
 */
@RestController
@RequestMapping("/sys/device")
public class DeviceController {
    @Autowired
    IDeviceService deviceService;
    @Autowired
    DeviceMapper deviceMapper;

    @GetMapping("/list")
    public Result<Map<String,Object>> getDeviceList(@RequestParam(value = "dname",required = false) String dname,
                                                 @RequestParam(value = "did",required = false) Integer did){
        QueryWrapper wrapper = new QueryWrapper();
        if(did != null)
            wrapper.eq("did",did);
        if(dname != null)
            wrapper.like("dname", dname);
        List<Device> list = deviceMapper.selectList(wrapper);
        List<Device> list1 = deviceService.list();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> data1 = new HashMap<>();
        data.put("total",list.size());
        data.put("rows",list);
        data1.put("total",list1.size());
        data1.put("rows",list1);
        if(dname == null && did == null)
            return Result.success(20000,data1,"成功");
        else
            return Result.success(20000,data,"成功");
    }

    @PostMapping
    public Result<?> addDevice(@RequestBody Device device){
        deviceService.save(device);
        return Result.success("新增设备成功");
    }

    @PutMapping("/set")
    public Result<?> updateDevice(@RequestBody Device device){
        deviceService.updateById(device);
        return Result.success("修改设备成功");
    }

    @PutMapping("/borrow")
    public Result<?> updateDevices(@RequestBody Device device){
        deviceService.updateById(device);
        return Result.success("预约设备成功");
    }

    @GetMapping("/{id}")
    public Result<Device> getDeviceById(@PathVariable("id") Integer id){
        Device device = deviceService.getById(id);
        return Result.success(device);
    }

    @DeleteMapping("/{id}")
    public Result<Device> deleteDeviceById(@PathVariable("id") Integer id){
        deviceService.removeById(id);
        return Result.success("删除设备成功");
    }
}
