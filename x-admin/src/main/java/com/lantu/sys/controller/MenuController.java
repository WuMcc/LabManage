package com.lantu.sys.controller;

import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Menu;
import com.lantu.sys.service.IMenuService;
import com.lantu.sys.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author laocai
 * @since 2023-02-07
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;
    @GetMapping
    public Result<List<Menu>> getAllMenu(){
        List<Menu> menuList = menuService.getAllMenu();
        return Result.success(menuList);
    }
}
