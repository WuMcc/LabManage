package com.lantu.sys.service;

import com.lantu.sys.entity.Device;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author laocai
 * @since 2024-03-02
 */
public interface IDeviceService extends IService<Device> {

    void ResetBorrow();
}
