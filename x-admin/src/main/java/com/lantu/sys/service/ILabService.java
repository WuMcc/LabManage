package com.lantu.sys.service;

import com.lantu.sys.entity.Lab;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author laocai
 * @since 2024-03-03
 */
public interface ILabService extends IService<Lab> {
    public void ResetBorrow();
}
