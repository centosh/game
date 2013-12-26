package com.hatbazar.controllers;

import com.hatbazar.domains.User;
import com.hatbazar.domains.UserLog;
import com.hatbazar.services.UserLogService;
import com.hatbazar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/4/13
 * Time: 9:41 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    UserLogService userLogService;


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "login/index";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attributes) {
       try {
            if (userService.authenticate(request, attributes))return "redirect:/user";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes attributes,HttpServletRequest request) throws UnsupportedEncodingException, SQLException, InstantiationException, NoSuchAlgorithmException, IllegalAccessException {
        userLogService.create((Integer)request.getSession().getAttribute("userId"), "Logout");
        request.getSession().setAttribute("isLogin", null);
        request.getSession().setAttribute("userId",null);
        attributes.addFlashAttribute("message","You have successfully logged out from this application");
        return "redirect:/login";
    }

}
