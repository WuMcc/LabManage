package com.lantu.sys.mapper;

import com.lantu.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    public List<Menu> getMenuListByUserId(@Param("userId") Integer userId, @Param("i") int i);
}

