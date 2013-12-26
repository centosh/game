package com.hatbazar.controllers;

import com.hatbazar.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/22/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) throws UnsupportedEncodingException, SQLException, InstantiationException, NoSuchAlgorithmException, IllegalAccessException {
        contactService.save(request,attributes);
        return "redirect:/";
    }


}
