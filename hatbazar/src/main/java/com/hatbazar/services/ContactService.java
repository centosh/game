package com.hatbazar.services;

import com.hatbazar.dao.ContactDao;
import com.hatbazar.dao.ItemDao;
import com.hatbazar.domains.Contact;
import com.hatbazar.domains.Item;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/22/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContactService {
        public boolean save(HttpServletRequest request, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException, NoSuchAlgorithmException, SQLException
        {
            Contact contact= setInstance(request);
            ContactDao contactDao = new ContactDao();
            boolean flag = false;
            if(contact.getId()==0)flag= contactDao.create(contact);
            else flag= contactDao.update(contact);
            if (flag)attributes.addFlashAttribute("message","Your message has been saved successfully, we will contact you soon...");
            return flag;
        }
    private Contact setInstance(HttpServletRequest request){
        Contact contact = new Contact();
        contact.setName(request.getParameter("name"));
        contact.setEmail(request.getParameter("email"));
        contact.setPhone(request.getParameter("phone"));
        contact.setSubject(request.getParameter("subject"));
        contact.setMessage(request.getParameter("message"));
        return contact;
    }
    public List<Contact> getAllUnReadMessage() throws InstantiationException, IllegalAccessException, SQLException {
        return new ContactDao().getAllUnReadMessage();
    }
    public List<Contact> getReadMessage() throws InstantiationException, IllegalAccessException, SQLException {
        return new ContactDao().getReadMessage();
    }
    public Contact get(int id) throws InstantiationException, IllegalAccessException, SQLException {
        return new ContactDao().get(id);
    }
    public boolean delete(int id, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, SQLException {
        if(new ContactDao().delete(id)){
            attributes.addFlashAttribute("message","Message successfully deleted");
            return true;
        }else {
            attributes.addFlashAttribute("error","Message not exists please refresh your browser");
        }
        return false;

    }
}
