package com.hatbazar.services;

import com.hatbazar.dao.UserDao;
import com.hatbazar.domains.User;
import com.hatbazar.utilities.Constants;
import com.hatbazar.utilities.Utils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 5/29/13
 * Time: 9:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserService {
    public boolean authenticate(HttpServletRequest request, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = userInstance(request);
        boolean login = validUser(user,request, attributes);
        if(login){
            attributes.addFlashAttribute("message","Successfully logged in, Welcome to Online Haatbazar");
            return true;
        }else
            return false;
//        return validUser(user,request, attributes);
    }
    public boolean isLogin(User user, HttpServletRequest request) throws UnsupportedEncodingException, SQLException, InstantiationException, NoSuchAlgorithmException, IllegalAccessException {
        if(user.getId()!=0){
            request.getSession().setAttribute("isLogin",true);
            request.getSession().setAttribute("userId",user.getId());
            request.getSession().setAttribute("userType",user.getType());
            new UserLogService().create(user.getId(), "Login");
            return true;
        }
        return false;
    }
    public User get(int id) throws InstantiationException, IllegalAccessException, SQLException {
        UserDao userDao = new UserDao();
        return userDao.getUser(id);
    }

    public boolean save(HttpServletRequest request, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        User user = userInstance(request);
        if(isValid(user, request, attributes)){
            UserDao userDao = new UserDao();
            boolean flag = false;
            if(user.getId()==0)flag= userDao.create(user);
            else flag= userDao.update(user);
            if (flag)attributes.addFlashAttribute("message","Data Save Successfully");
            return flag;
        }
        return false;
    }
    private User userInstance(HttpServletRequest request) throws IllegalAccessException, InstantiationException, UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        User user=null;
        if(!Utils.checkNull(request.getParameter("id")))user = get(Integer.parseInt(request.getParameter("id")));
        else {
            user = new User();
            if(request.getSession().getAttribute("userId")!=null && !Utils.checkNull(request.getSession().getAttribute("userId").toString())) user.setAddedBy((Integer)request.getSession().getAttribute("userId"));
        }
        if(!Utils.checkNull(request.getParameter("name"))) user.setName(request.getParameter("name").trim());
        if(!Utils.checkNull(request.getParameter("address"))) user.setAddress(request.getParameter("address").trim());
        if(!Utils.checkNull(request.getParameter("email"))) user.setEmail(request.getParameter("email").replace(" ","").trim());
        if(!Utils.checkNull(request.getParameter("phone"))) user.setPhone(request.getParameter("phone").trim());
        if(!Utils.checkNull(request.getParameter("type"))) user.setType(request.getParameter("type").trim());
        if(!Utils.checkNull(request.getParameter("username"))) user.setUsername(request.getParameter("username").trim());
        if(!Utils.checkNull(request.getParameter("password"))) user.setPassword(Utils.md5(request.getParameter("password").trim()));
        return user;
    }
    public boolean isValid(User user, HttpServletRequest request, RedirectAttributes attributes) throws UnsupportedEncodingException, NoSuchAlgorithmException, IllegalAccessException, SQLException, InstantiationException {
        if((Integer)request.getSession().getAttribute("userId")==user.getId()) attributes.addFlashAttribute("tab","profile");
        else attributes.addFlashAttribute("tab","list");
        String msg="";
        if(!isNewUserName(user.getUsername(),user.getId())){
            attributes.addFlashAttribute("error","\""+user.getUsername()+"\" username is already exists, please try another username");
            return false;
        }
        if(user.getName()==null || Utils.checkNull(user.getName())) msg += "Name is required field";
        if(user.getEmail() == null || Utils.checkNull(user.getEmail())) msg += "<br />Email is required field";
        if(user.getType() == null || Utils.checkNull(user.getType())) msg += "<br />User Role is required field";
        else{
            if (!Utils.isValidEmail(user.getEmail())) msg += "<br />Please enter valid Email";
        }
        if(user.getPassword() == null || Utils.checkNull(user.getPassword())){
            msg += "<br />Password is required field";
        }else{
            if(user.getPassword().equalsIgnoreCase(Utils.md5(request.getParameter("password").trim()))){  // if password request for change or not OR new
                if((request.getParameter("rpassword").trim() == null || Utils.checkNull(request.getParameter("rpassword").trim())))msg  += "<br />Re-Enter Password is required field";
                else {
                    if (!user.getPassword().equals(Utils.md5(request.getParameter("rpassword").trim()))) msg+="<br /> Re-Enter password not match";
                }
            } else System.out.println("password not changing :::");
        }
        attributes.addFlashAttribute("error",msg);
        return msg=="";
    }
    public List<User> findAll() throws IllegalAccessException, SQLException, InstantiationException {
        UserDao userDao = new UserDao();
        return userDao.listUsers();
    }
    private boolean validUser(User user,HttpServletRequest request, RedirectAttributes attributes) throws UnsupportedEncodingException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, SQLException {
        String msg ="";
        if(Utils.checkNull(user.getUsername()))msg+="<br /> Username is required field";
        if(Utils.checkNull(user.getPassword()) || Utils.md5("") == user.getPassword())msg+="<br /> Password is required field";
        if(msg==""){
            UserDao userDao = new UserDao();
            User u = userDao.authenticate(user);
            if(u==null)msg ="The credentials you provided cannot be determined to be authentic.";
            else{
                if (!isLogin(u, request))msg="Your are not ready to login yet, please contact your administrator.";
            }
        }
        attributes.addFlashAttribute("error",msg);
        return msg=="";
    }
    public void delete(HttpServletRequest request, RedirectAttributes attributes) throws InstantiationException, IllegalAccessException, SQLException {
        int id=Integer.parseInt(request.getParameter("id"));
        UserDao userDao=new UserDao();
        attributes.addFlashAttribute("tab","list");
        if(userDao.delete(id))attributes.addFlashAttribute("message","Data delete successfully");
        else attributes.addFlashAttribute("error","User not exists, Refresh your browser properly");
    }
    public boolean isNewUserName(String username, int id) throws InstantiationException, IllegalAccessException, SQLException {
        User user = new UserDao().getByUserName(username, id);
        if(user==null)return true;
        return false;
    }
    public String dbBackup(HttpServletRequest request, RedirectAttributes attributes, HttpServletResponse response){
        String message = "";
        String executeCmd = Constants.DB_PATH;
        System.out.println("executeCmd = " + executeCmd);
        Process runtimeProcess;
        try
        {
            System.out.println(executeCmd);

            //this out put works in mysql shell
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0)
            {
                System.out.println("Backup created successfully");
                message = "Backup created successfully in " + Constants.DB_OUTPUT_PATH;
            }
            else
            {
                System.out.println("Could not create the backup");
                message = "Could not create the backup";
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  message;
    }
}