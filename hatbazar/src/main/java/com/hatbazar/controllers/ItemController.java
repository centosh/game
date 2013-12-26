package com.hatbazar.controllers;

import com.hatbazar.services.ItemService;
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
 * Date: 6/24/13
 * Time: 12:43 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws IllegalAccessException, SQLException, InstantiationException {
        if(request.getSession().getAttribute("userId")==null)return "redirect:/login";
        int id =(Integer) request.getSession().getAttribute("userId");
        request.setAttribute("yourList",itemService.getYourItems(id));
        request.setAttribute("availableList",itemService.getAvailableItems(id));
        request.setAttribute("reservedList",itemService.getReservedItems(id));
        request.setAttribute("soldList",itemService.getSoldItems(id));
        return "item/index";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) throws IllegalAccessException, InstantiationException, UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        itemService.save(request,attributes);
        return "redirect:/item";
    }
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attributes) throws IllegalAccessException, SQLException, InstantiationException {
        itemService.delete(request,attributes);
        return "redirect:/item";
    }

    @RequestMapping(value = "/reserve")
    public String reserve(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attributes) throws IllegalAccessException, SQLException, InstantiationException {
        itemService.reserve(request,attributes);
        return "redirect:/item";
    }
    @RequestMapping(value = "/cancelReserved")
    public String cancelReserved(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attributes) throws IllegalAccessException, SQLException, InstantiationException {
        itemService.cancelReserved(request,attributes);
        return "redirect:/item";
    }
    @RequestMapping(value = "/sold")
    public String sold(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attributes) throws IllegalAccessException, SQLException, InstantiationException, UnsupportedEncodingException, NoSuchAlgorithmException {
        itemService.sold(request,attributes);
        return "redirect:/item";
    }
}
