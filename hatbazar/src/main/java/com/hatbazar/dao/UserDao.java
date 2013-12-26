package com.hatbazar.dao;

import com.hatbazar.domains.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Mysql {
    String table="`user`";
    public UserDao() throws IllegalAccessException, InstantiationException {
        this.connect();
    }
    public boolean create(User user) throws InstantiationException, IllegalAccessException {
        System.out.println("in userDAo :: create ::");
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO "+table+" (`name`, `address`, `email`, `phone`,`type`,`username`, `password`,`added_by`) VALUES (?,?,?,?,?,?,?,?)");
            setPreparedStatement(user, ps);
            return affect(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(int id) throws InstantiationException, IllegalAccessException, SQLException {
        String sql = "SELECT * FROM "+table+" WHERE `id` =? LIMIT 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        List<User> list =rows(find(ps));
        close();
        if (list!=null) return list.get(0);
        return null;
    }


    public List<User> listUsers() throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table+" WHERE `type`<>'SUPER'";
        PreparedStatement ps = conn.prepareStatement(sql);
        List<User> list = rows(find(ps));
        close();
        if(list ==null)return null;
        else return list;
    }

    public boolean delete(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "DELETE FROM "+table+" WHERE `id`=? LIMIT 1";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1, id);
        return affect(ps);
    }

    public boolean update(User user) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE "+table+" SET `name`=?,`address`=?,`email`=?,`phone`=?,`type`=?,`username`=?,`password`=?,`added_by`=? WHERE `id`=? LIMIT 1";
        PreparedStatement ps= conn.prepareStatement(sql);
        setPreparedStatement(user,ps);
        ps.setInt(9,user.getId());
        return affect(ps);
    }
    public User authenticate(User user) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM `user` WHERE `username`=? AND `password`=? LIMIT 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        List<User> list = rows(find(ps));
        close();
        if(list.size()== 0)return null;
        else return list.get(0);
    }
    public List<User> rows(ResultSet rs) throws SQLException {
        if(rs!=null){
            List<User> list=new ArrayList<User>();
            while (rs.next()){
                list.add(row(rs));
            }
            return list;
        }
        return null;
    }
    public User row(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setAddress(rs.getString("address"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setType(rs.getString("type"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setAddedBy(rs.getInt("added_by"));
        return user;
    }
    private void setPreparedStatement(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getAddress());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPhone());
        ps.setString(5, user.getType());
        ps.setString(6, user.getUsername());
        ps.setString(7, user.getPassword());
        ps.setInt(8, user.getAddedBy());
    }
    public User getByUserName(String username, int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table+" WHERE `username`=? AND `id`<>?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,username);
        ps.setInt(2,id);
        List<User> list = rows(find(ps));
        close();
        if(list== null || list.size()==0)return null;
        else return list.get(0);
    }
}