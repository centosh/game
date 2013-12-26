package com.hatbazar.dao;

import com.hatbazar.domains.UserLog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/29/13
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserLogDao extends Mysql {
    String table="`user_log`";
    public boolean create(UserLog log) throws InstantiationException, IllegalAccessException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO "+table+" (`username`, `name`, `user_id`, `action`) VALUES (?,?,?,?)");
            ps.setString(1,log.getUserName());
            ps.setString(2,log.getName());
            ps.setInt(3,log.getUserId());
            ps.setString(4,log.getAction());
            return affect(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<UserLog> getLatest() throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table+" ORDER BY `id` DESC LIMIT 30";
        PreparedStatement ps = conn.prepareStatement(sql);
        List<UserLog> list =rows(find(ps));
        close();
        if (list!=null || list.size()!=0) return list;
        return null;
    }

    public List<UserLog> rows(ResultSet rs) throws SQLException {
        if(rs!=null){
            List<UserLog> list=new ArrayList<UserLog>();
            while (rs.next()){
                list.add(row(rs));
            }
            return list;
        }
        return null;
    }

    public UserLog row(ResultSet rs) throws SQLException {
        UserLog log = new UserLog();
        log.setId(rs.getInt("id"));
        log.setName(rs.getString("name"));
        log.setUserId(rs.getInt("user_id"));
        log.setUserName(rs.getString("username"));
        log.setAction(rs.getString("action"));
        return log;
    }
}