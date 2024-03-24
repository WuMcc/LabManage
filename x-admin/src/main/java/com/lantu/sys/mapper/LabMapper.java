package com.lantu.sys.mapper;

import com.lantu.sys.entity.Lab;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author laocai
 * @since 2024-03-03
 */
@Mapper
public interface LabMapper extends BaseMapper<Lab> {

    @Select("select * from x_lab where status = 0")
    Lab getLabByStatus(int status);

    @Update("update x_lab set status = 1 where status = 0")
    int ResetBorrow();
}
