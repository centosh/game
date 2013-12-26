package com.hatbazar.dao;

import com.hatbazar.domains.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Item: bsejawal
 * Date: 6/24/13
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class ItemDao extends Mysql {
    String table="item";
    public ItemDao() throws IllegalAccessException, InstantiationException {
        this.connect();
    }
    public boolean create(Item item) throws InstantiationException, IllegalAccessException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO "+table+" (`name`, `category`, `added_by`, `price`,`status`,`contact_person`,`contact_phone`,`details`) VALUES (?,?,?,?,?,?,?,?)");
            setPreparedStatement(item, ps);
            return affect(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Item getItem(int id) throws InstantiationException, IllegalAccessException, SQLException {
        String sql = "SELECT * FROM "+table+" WHERE `id` =? LIMIT 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        List<Item> list =rows(find(ps));
        close();
        if (list!=null) return list.get(0);
        return null;
    }


    public List<Item> all() throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table;
        PreparedStatement ps = conn.prepareStatement(sql);
        List<Item> list = rows(find(ps));
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

    public boolean update(Item item) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE "+table+" SET `name`=?,`category`=?,`added_by`=?,`price`=?,`status`=?,`contact_person`=?,`contact_phone`=?,`details`=? WHERE `id`=? LIMIT 1";
        PreparedStatement ps= conn.prepareStatement(sql);
        setPreparedStatement(item,ps);
        ps.setInt(9,item.getId());
        return affect(ps);
    }

    public List<Item> rows(ResultSet rs) throws SQLException {
        if(rs!=null){
            List<Item> list=new ArrayList<Item>();
            while (rs.next()){
                list.add(row(rs));
            }
            return list;
        }
        return null;
    }
    public Item row(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        item.setCategory(rs.getString("category"));
        item.setAddedBy(rs.getInt("added_by"));
        item.setPrice(rs.getDouble("price"));
        item.setStatus(rs.getString("status"));
        item.setContactPerson(rs.getString("contact_person"));
        item.setContactPhone(rs.getString("contact_phone"));
        item.setDetails(rs.getString("details"));
        return item;
    }
    private void setPreparedStatement(Item item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getName());
        ps.setString(2, item.getCategory());
        ps.setInt(3, item.getAddedBy());
        ps.setDouble(4, item.getPrice());
        ps.setString(5,item.getStatus());
        ps.setString(6,item.getContactPerson());
        ps.setString(7,item.getContactPhone());
        ps.setString(8,item.getDetails());
    }
    public List<Item> findByAdmin(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table+" WHERE `added_by` IN (SELECT `id` FROM `user` WHERE `added_by`=?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        List<Item> list = rows(find(ps));
        close();
        if(list ==null)return null;
        else return list;
    }
    public List<Item> findByUser(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table+" WHERE `added_by`=? ORDER BY `id` DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        List<Item> list = rows(find(ps));
        close();
        if(list ==null)return null;
        else return list;
    }

    public List<Item> getDefaultItems() throws SQLException, InstantiationException, IllegalAccessException {
        if(conn==null)connect();
        String sql ="SELECT * FROM "+table+" WHERE `status`='ACTIVE' ORDER BY `id` DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        List<Item> list = rows(find(ps));
        close();
        if(list ==null || list.size()==0)return null;
        else return list;

    }
    public List<Item> getAvailableItems(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql ="SELECT * FROM "+table+" WHERE `status`='ACTIVE' AND `added_by`<>? ORDER BY `id` DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        List<Item> list = rows(find(ps));
        close();
        if(list ==null)return null;
        else return list;
    }

    public List<Item> getReservedItems(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql ="SELECT * FROM "+table+" WHERE `status`='RESERVED' AND `reserved_by`=? ORDER BY `id` DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        List<Item> list = rows(find(ps));
        close();
        if(list ==null)return null;
        else return list;
    }
    public List<Item> getSoldItems(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql ="SELECT * FROM "+table+" WHERE `status`='SOLD' AND `reserved_by`=? ORDER BY `id` DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        List<Item> list = rows(find(ps));
        close();
        if(list ==null)return null;
        else return list;
    }

    public boolean reserve(int userId, int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE "+table+" SET `status`=?,`reserved_by`=? WHERE `id`=? LIMIT 1";
        PreparedStatement ps= conn.prepareStatement(sql);
        ps.setString(1,"RESERVED");
        ps.setInt(2,userId);
        ps.setInt(3,id);
        return affect(ps);
    }
    public boolean cancelReserved(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE "+table+" SET `status`='ACTIVE',`reserved_by`=0 WHERE `id`=? LIMIT 1";
        PreparedStatement ps= conn.prepareStatement(sql);
        ps.setInt(1,id);
        return affect(ps);
    }
    public boolean sold(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE "+table+" SET `status`='SOLD' WHERE `id`=? LIMIT 1";
        PreparedStatement ps= conn.prepareStatement(sql);
        ps.setInt(1,id);
        return affect(ps);
    }
    public List<Item> searchByName(String query) throws SQLException, InstantiationException, IllegalAccessException {
        query=query.toLowerCase();
        String sql ="SELECT * FROM "+table+" WHERE LOWER(`name`) like '%"+query+"%' AND `status`='ACTIVE' LIMIT 20";
        PreparedStatement ps = conn.prepareStatement(sql);
        List<Item> list = rows(find(ps));
        close();
        if(list ==null)return null;
        else return list;
    }
}
