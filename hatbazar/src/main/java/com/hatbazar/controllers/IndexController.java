package com.hatbazar.controllers;

import com.hatbazar.domains.Item;
import com.hatbazar.services.ItemService;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 5/29/13
 * Time: 9:23 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) throws IllegalAccessException, SQLException, InstantiationException {
        model.addAttribute("list", itemService.getDefaultItems());
        return "index/welcome";
    }
    @RequestMapping(value = "/contact")
    public String contact(ModelMap model){
        return "index/contact";
    }
    @RequestMapping(value = "/about")
    public String about(ModelMap model){
        return "index/about";
    }
    @RequestMapping(value = "/typeahead" , method = RequestMethod.GET)
    public @ResponseBody String typeahead(HttpServletRequest request) throws IllegalAccessException, SQLException, InstantiationException {
        String data = itemService.getTypeAhead(request.getParameter("query"));
        return data;
    }

    @RequestMapping(value="/searchItem",method=RequestMethod.POST)
    public String addUser(HttpServletRequest request, RedirectAttributes attributes) throws IllegalAccessException, SQLException, InstantiationException {
        List<Item> searchList = itemService.searchByname(request.getParameter("searchQuery"), attributes);
        request.setAttribute("searchList",searchList);
        return "index/itemSearchResult";
    }

}
