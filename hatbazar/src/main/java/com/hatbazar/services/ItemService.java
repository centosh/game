package com.hatbazar.services;

import com.hatbazar.dao.ItemDao;
import com.hatbazar.domains.Item;
import com.hatbazar.utilities.Utils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Item: bsejawal
 * Date: 6/24/13
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class ItemService {
    ItemSoldService itemSoldService = new ItemSoldService();
    public Item get(int id) throws InstantiationException, IllegalAccessException, SQLException {
        ItemDao itemDao = new ItemDao();
        return itemDao.getItem(id);
    }

    public boolean save(HttpServletRequest request, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        Item item = itemInstance(request);
        if(isValid(item, request, attributes)){
            ItemDao itemDao = new ItemDao();
            boolean flag = false;
            if(item.getId()==0)flag= itemDao.create(item);
            else flag= itemDao.update(item);
            if (flag)attributes.addFlashAttribute("message","Data Save Successfully");
            return flag;
        }
        return false;
    }
    private Item itemInstance(HttpServletRequest request) throws IllegalAccessException, InstantiationException, UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        Item item=null;
        if(!Utils.checkNull(request.getParameter("id")))item = get(Integer.parseInt(request.getParameter("id")));
        else{
            item = new Item();
            item.setAddedBy(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        }
        if(!Utils.checkNull(request.getParameter("name"))) item.setName(request.getParameter("name").trim());
        if(!Utils.checkNull(request.getParameter("category"))) item.setCategory(request.getParameter("category").trim());
        if(!Utils.checkNull(request.getParameter("price"))&& Utils.isNumeric(request.getParameter("price"))) item.setPrice(Double.parseDouble(request.getParameter("price").trim()));
        if(!Utils.checkNull(request.getParameter("status"))) item.setStatus(request.getParameter("status").trim());
        if(!Utils.checkNull(request.getParameter("contactPerson"))) item.setContactPerson(request.getParameter("contactPerson").trim());
        if(!Utils.checkNull(request.getParameter("contactPhone"))) item.setContactPhone(request.getParameter("contactPhone").trim());
        if(!Utils.checkNull(request.getParameter("details"))) item.setDetails(request.getParameter("details").trim());
        return item;
    }
    public boolean isValid(Item item, HttpServletRequest request, RedirectAttributes attributes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String msg = "";
        if(item.getName()==null || Utils.checkNull(item.getName())) msg += "Name is required field";
        if(item.getCategory() == null || Utils.checkNull(item.getCategory())) msg += "<br />Category is required field";
        if(item.getPrice()==0) msg += "<br />Price is required field";
        if(item.getStatus() == null || Utils.checkNull(item.getStatus())) msg += "<br />Status is required field";
        if(item.getContactPerson() == null || Utils.checkNull(item.getContactPerson())) msg += "<br />Contact Person is required field";
        if(item.getContactPhone() == null || Utils.checkNull(item.getContactPhone())) msg += "<br />Contact Phone is required field";
        attributes.addFlashAttribute("error",msg);
        return msg=="";
    }
    public List<Item> findAll() throws IllegalAccessException, SQLException, InstantiationException {
        ItemDao itemDao = new ItemDao();
        return itemDao.all();
    }
    public void delete(HttpServletRequest request, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, SQLException {
        int id=Integer.parseInt(request.getParameter("id"));
        ItemDao itemDao=new ItemDao();
        attributes.addFlashAttribute("tab","item_list");
        if(itemDao.delete(id))attributes.addFlashAttribute("message","Data delete successfully");
        else attributes.addFlashAttribute("error","Item not exists, Refresh your browser properly");
    }
    public List<Item> getDefaultItems() throws InstantiationException, IllegalAccessException, SQLException {
        return new ItemDao().getDefaultItems();
    }
    public List<Item> getYourItems(int id) throws IllegalAccessException, SQLException, InstantiationException {
        return new ItemDao().findByUser(id);
    }
    public List<Item> getAvailableItems(int id) throws InstantiationException, IllegalAccessException, SQLException {
        return new ItemDao().getAvailableItems(id);
    }
    public List<Item> getReservedItems(int id) throws InstantiationException, IllegalAccessException, SQLException {
        return new ItemDao().getReservedItems(id);
    }
    public List<Item> getSoldItems(int id) throws InstantiationException, IllegalAccessException, SQLException {
        return new ItemDao().getSoldItems(id);
    }
    public boolean reserve(HttpServletRequest request, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, SQLException {
        if (!Utils.isLogin(request)){
            return false;
        } else {
            int userId = (Integer)request.getSession().getAttribute("userId");
            int id = Integer.parseInt(request.getParameter("id"));
            if(new ItemDao().reserve(userId, id)){
                attributes.addFlashAttribute("message","Item Reserved successfully.");
                attributes.addFlashAttribute("tab","reserved");
                return true;
            }
            return false;
        }
    }

    public boolean cancelReserved(HttpServletRequest request, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, SQLException {
        if (!Utils.isLogin(request)){
            return false;
        } else {
            int userId = (Integer)request.getSession().getAttribute("userId");
            int id = Integer.parseInt(request.getParameter("id"));
            if(new ItemDao().cancelReserved(id)){
                attributes.addFlashAttribute("message","Reserved Item Cancel Successfully.");
                attributes.addFlashAttribute("tab","your_list");
                return true;
            }
            return false;
        }
    }
    public boolean sold(HttpServletRequest request, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (!Utils.isLogin(request)){
            return false;
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            if(new ItemDao().sold(id)){
                itemSoldService.save(id, (Integer)request.getSession().getAttribute("userId"));
                attributes.addFlashAttribute("message","You have successfully moved reserved to sold.");
                attributes.addFlashAttribute("tab","sold");
                return true;
            }
            return false;
        }
    }

    public String getTypeAhead(String query) throws InstantiationException, IllegalAccessException, SQLException {
       List<Item> list=new ItemDao().searchByName(query);
        if (list==null || list.size()==0){
            return "null";
        }else{
            String toRet = "";
            for (Item item : list) {
                toRet+= item.getName()+",";
            }
            toRet = toRet.substring(0, toRet.length() - 1);
            return toRet;
        }
    }

    public List<Item> searchByname(String query, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, SQLException {
        List<Item> list = new ItemDao().searchByName(query);
       if(list==null || list.size()==0) {
           attributes.addFlashAttribute("error","Result Not Found Please Provide other word");
           return null;
       }else{
           attributes.addFlashAttribute("message",list.size() +" Result Found !");
           return list;
       }
    }


}
