package com.wang.project.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wang.project.commonn.Result;
import com.wang.project.commonn.ResultUtils;
import com.wang.project.entity.Account;
import com.wang.project.entity.Customer;
import com.wang.project.entity.Role;
import com.wang.project.query.RolePageQuery;
import com.wang.project.service.IAccountService;
import com.wang.project.service.IResourceService;
import com.wang.project.service.IRoleService;
import com.wang.project.vo.CustomerDetailVO;
import com.wang.project.vo.RoleDetailVO;
import com.wang.project.vo.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Wang
 * @since 2022-01-22
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IAccountService accountService;

    /**
     * 进入列表
     */
    @GetMapping("/toList")
    public String toList() {
        return "role/roleList";
    }

    /**
     * 查询角色列表
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<Map<String, Object>> list(RolePageQuery rolePageQuery) {
        String roleName = rolePageQuery.getRoleName();
        Long page = rolePageQuery.getPage();
        Long limit = rolePageQuery.getLimit();

        return ResultUtils.buildPageResult(
                roleService.lambdaQuery()
                        .like(StringUtils.isNotBlank(roleName), Role::getRoleName, roleName)
                        .orderByDesc(Role::getRoleId).page(new Page<>(page, limit))
        );
    }

    /**
     * 进入新增页
     */
    @GetMapping("/toAdd")
    public String toAdd() {
        return "role/roleAdd";
    }

    private static final Integer DETAIL_RESOURCE = 0;
    private static final Integer UPDATE_RESOURCE = 1;

    /**
     * 获取资源列表
     */
    @GetMapping({"/listResource/{flag}/{roleId}", "/listResource/{flag}"})
    @ResponseBody
    public Result<List<TreeVO>> listResource(@PathVariable(required = false) Integer flag,
                                             @PathVariable(required = false) Long roleId) {
        if (flag.equals(DETAIL_RESOURCE)) return Result.success(resourceService.detailResource(roleId));
        if (flag.equals(UPDATE_RESOURCE)) return Result.success(resourceService.updateResource(roleId));
        return Result.success(resourceService.listResource());
    }

    /**
     * 新增角色
     */
    @PostMapping
    @ResponseBody
    public Result<String> add(@RequestBody Role role) {
        return ResultUtils.buildResult(roleService.saveRole(role));
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{roleId}")
    @ResponseBody
    public Result<String> delete(@PathVariable Long roleId) {
        Long cnt = accountService.lambdaQuery().eq(Account::getRoleId, roleId).count();
        if (cnt > 0) {
            return Result.failed("有账号拥有该角色，不可删除");
        }
        return ResultUtils.buildResult(roleService.removeById(roleId));
    }

    /**
     * 进入更新页
     */
    @GetMapping("/toUpdate/{roleId}")
    public String toUpdate(@PathVariable Long roleId, Model model) {
        Role role = roleService.getById(roleId);
        model.addAttribute("role", role);
        return "role/roleUpdate";
    }

    /**
     * 修改角色
     */
    @PutMapping
    @ResponseBody
    public Result<String> update(@RequestBody Role role) {
        return ResultUtils.buildResult(roleService.updateRole(role));
    }

    /**
     * 进入详情页
     */
    @GetMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        RoleDetailVO roleDetailVO = roleService.roleDetailById(id);
        model.addAttribute("role", roleDetailVO);
        return "role/roleDetail";
    }
}
