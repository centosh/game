package com.hatbazar.controllers;

import com.hatbazar.domains.Contact;
import com.hatbazar.domains.UserLog;
import com.hatbazar.services.ContactService;
import com.hatbazar.services.UserLogService;
import com.hatbazar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ContactService contactService;

    @Autowired
    UserLogService userLogService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InstantiationException, SQLException, IOException {
        if(request.getSession().getAttribute("isLogin")!=null){
            request.setAttribute("logList", userLogService.getLatest());
            request.setAttribute("list",userService.findAll());
            request.setAttribute("user",userService.get(Integer.parseInt(request.getSession().getAttribute("userId").toString())));
            return  "user/index";
        }
        return "login/index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) throws IllegalAccessException, InstantiationException, UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        userService.save(request,attributes);
        return "redirect:/user";
    }
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attributes) throws IllegalAccessException, SQLException, InstantiationException {
        userService.delete(request,attributes);
        return "redirect:/user";
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public @ResponseBody String getMessage(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) throws UnsupportedEncodingException, SQLException, InstantiationException, NoSuchAlgorithmException, IllegalAccessException {
        Contact contact = contactService.get(Integer.parseInt(request.getParameter("id")));
        String data = contact.getName()+"#"+contact.getEmail()+"#"+contact.getSubject()+"#"+contact.getPhone()+"#"+contact.getMessage();
        return  data;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) throws UnsupportedEncodingException, SQLException, InstantiationException, NoSuchAlgorithmException, IllegalAccessException {
        request.setAttribute("unRead",contactService.getAllUnReadMessage());
        request.setAttribute("readMessage",contactService.getReadMessage());
        return  "contact/index";
    }

    @RequestMapping(value = "/messageDelete", method = RequestMethod.GET)
    public String messageDelete(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) throws UnsupportedEncodingException, SQLException, InstantiationException, NoSuchAlgorithmException, IllegalAccessException {
        contactService.delete(Integer.parseInt(request.getParameter("id")), attributes);
        return  "redirect:/user/message";
    }
    @RequestMapping(value = "/dbBackup", method = RequestMethod.GET)
    public String dbBackup(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) throws UnsupportedEncodingException, SQLException, InstantiationException, NoSuchAlgorithmException, IllegalAccessException {
        String msg = userService.dbBackup(request, attributes,response);
        attributes.addFlashAttribute("warning",msg);
        return  "redirect:/user";
    }

}
