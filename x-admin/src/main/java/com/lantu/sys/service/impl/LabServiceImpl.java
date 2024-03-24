package com.lantu.sys.service.impl;

import com.lantu.sys.entity.Lab;
import com.lantu.sys.mapper.LabMapper;
import com.lantu.sys.service.ILabService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laocai
 * @since 2024-03-03
 */
@Service
public class LabServiceImpl extends ServiceImpl<LabMapper, Lab> implements ILabService {

    @Autowired
    LabMapper labMapper;
    public void ResetBorrow(){
        labMapper.ResetBorrow();
    }
}
