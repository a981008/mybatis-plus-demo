package com.wang.project.controller;

import com.wang.project.dto.LoginDTO;
import com.wang.project.entity.Account;
import com.wang.project.service.IAccountService;
import com.wang.project.service.IResourceService;
import com.wang.project.vo.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登陆
 *
 * @author Wang
 * @since 2022/1/22
 */
@Controller
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IResourceService resourceService;

    /**
     * 登陆
     */
    @PostMapping("/login")
    public String login(String username, String password
            , HttpSession session
            , RedirectAttributes attributes
            , Model model) {

        LoginDTO loginDTO = accountService.login(username, password);
        String error = loginDTO.getError();
        if (error == null) { // 成功
            Account account = loginDTO.getAccount();
            session.setAttribute("account", account);
            List<ResourceVO> resourceVOS = resourceService.listResourceByRoleId(account.getRoleId());
            model.addAttribute("resources", resourceVOS);
        } else { // 失败
            attributes.addFlashAttribute("error", error);
        }
        return  loginDTO.getPath();
    }


    /**
     * 登出
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
