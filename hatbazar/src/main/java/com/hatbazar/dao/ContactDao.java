package com.hatbazar.dao;


import com.hatbazar.domains.Contact;
import com.hatbazar.domains.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/22/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContactDao extends Mysql {
    String table="`contact`";
    public ContactDao() throws IllegalAccessException, InstantiationException {
        connect();
    }
    public boolean create(Contact contact) throws InstantiationException, IllegalAccessException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO "+table+" (`name`, `email`, `phone`, `subject`,`message`,`is_new`) VALUES (?,?,?,?,?,?)");
            setPreparedStatement(contact, ps);
            return affect(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Contact getContact(int id) throws InstantiationException, IllegalAccessException, SQLException {
        String sql = "SELECT * FROM "+table+" WHERE `id` =? LIMIT 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        List<Contact> list =rows(find(ps));
        close();
        if (list!=null) return list.get(0);
        return null;
    }


    public List<Contact> listContacts() throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table;
        PreparedStatement ps = conn.prepareStatement(sql);
        List<Contact> list = rows(find(ps));
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

    public boolean update(Contact contact) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE "+table+" SET `name`=?, `email`=?, `phone`=?, `subject`=?,`message`=?,`is_new`=? WHERE `id`=? LIMIT 1";
        PreparedStatement ps= conn.prepareStatement(sql);
        setPreparedStatement(contact,ps);
        ps.setInt(7,contact.getId());
        return affect(ps);
    }

    public List<Contact> rows(ResultSet rs) throws SQLException {
        if(rs!=null){
            List<Contact> list=new ArrayList<Contact>();
            while (rs.next()){
                list.add(row(rs));
            }
            return list;
        }
        return null;
    }
    public Contact row(ResultSet rs) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("id"));
        contact.setName(rs.getString("name"));
        contact.setEmail(rs.getString("email"));
        contact.setPhone(rs.getString("phone"));
        contact.setSubject(rs.getString("subject"));
        contact.setMessage(rs.getString("message"));
        contact.setNew(rs.getBoolean("is_new"));
        return contact;
    }
    private void setPreparedStatement(Contact contact, PreparedStatement ps) throws SQLException {
        ps.setString(1, contact.getName());
        ps.setString(2, contact.getEmail());
        ps.setString(3, contact.getPhone());
        ps.setString(4, contact.getSubject());
        ps.setString(5, contact.getMessage());
        ps.setBoolean(6,contact.isNew());
    }

    public List<Contact> getAllUnReadMessage() throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table+" WHERE `is_new`=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1,true);
        List<Contact> list = rows(find(ps));
        close();
        if(list ==null)return null;
        else return list;
    }
    public List<Contact> getReadMessage() throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table+" WHERE `is_new`=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1,false);
        List<Contact> list = rows(find(ps));
        close();
        if(list ==null)return null;
        else return list;
    }

    public Contact get(int id) throws SQLException, InstantiationException, IllegalAccessException {
        if(makeAsRead(id)){
            connect();
            String sql = "SELECT * FROM "+table+" WHERE `id`=? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            List<Contact> list = rows(find(ps));
            close();
            if(list ==null || list.size()==0)return null;
            else return list.get(0);
        }
        return null;
    }

    public boolean makeAsRead(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE "+table+" SET `is_new`=? WHERE `id`=? LIMIT 1";
        PreparedStatement ps= conn.prepareStatement(sql);
        ps.setBoolean(1, false);
        ps.setInt(2, id);
        boolean rs =affect(ps);
        return rs;
    }
}
