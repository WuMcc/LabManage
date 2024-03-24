package com.lantu.sys.mapper;

import com.lantu.sys.entity.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author laocai
 * @since 2024-03-02
 */
public interface DeviceMapper extends BaseMapper<Device> {
    @Update("update device set status = 1 where status = 0")
    int ResetBorrow();
}
