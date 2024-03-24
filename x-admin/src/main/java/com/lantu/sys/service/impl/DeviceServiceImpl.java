package com.lantu.sys.service.impl;

import com.lantu.sys.entity.Device;
import com.lantu.sys.mapper.DeviceMapper;
import com.lantu.sys.service.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laocai
 * @since 2024-03-02
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Resource
    DeviceMapper deviceMapper;
    public void ResetBorrow(){
        deviceMapper.ResetBorrow();
    }
}
