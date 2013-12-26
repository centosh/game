package com.hatbazar.services;

import com.hatbazar.dao.ItemDao;
import com.hatbazar.dao.UserLogDao;
import com.hatbazar.domains.Item;
import com.hatbazar.domains.User;
import com.hatbazar.domains.UserLog;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/29/13
 * Time: 12:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserLogService {
    UserService userService= new UserService();
    public boolean create(int id, String action) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        User user= userService.get(id);
        UserLog log  = new UserLog();
        log.setAction(action);
        log.setName(user.getName());
        log.setUserId(user.getId());
        log.setUserName(user.getUsername());
        return new UserLogDao().create(log);
    }
    public List<UserLog> getLatest() throws IllegalAccessException, SQLException, InstantiationException {
        return new UserLogDao().getLatest();
    }
}
