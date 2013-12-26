package com.hatbazar.dao;

import com.hatbazar.domains.ItemSold;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/28/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemSoldDao extends Mysql {
    String table="item_sold";
    public ItemSoldDao() throws IllegalAccessException, InstantiationException {
        this.connect();
    }
    public boolean create(int item, int reqBy) throws InstantiationException, IllegalAccessException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO "+table+" (`item`, `req_by`) VALUES (?,?)");
            ps.setInt(1, item);
            ps.setInt(2, reqBy);
            return affect(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ItemSold get(int id) throws InstantiationException, IllegalAccessException, SQLException {
        String sql = "SELECT * FROM "+table+" WHERE `id` =? LIMIT 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        List<ItemSold> list =rows(find(ps));
        close();
        if (list!=null) return list.get(0);
        return null;
    }


    public List<ItemSold> all() throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM "+table;
        PreparedStatement ps = conn.prepareStatement(sql);
        List<ItemSold> list = rows(find(ps));
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

    public List<ItemSold> rows(ResultSet rs) throws SQLException {
        if(rs!=null){
            List<ItemSold> list=new ArrayList<ItemSold>();
            while (rs.next()){
                list.add(row(rs));
            }
            return list;
        }
        return null;
    }
    public ItemSold row(ResultSet rs) throws SQLException {
        ItemSold itemSold = new ItemSold();
        itemSold.setId(rs.getInt("id"));
        itemSold.setItem(rs.getInt("item"));
        itemSold.setRequestBy(rs.getInt("req_by"));
        return itemSold;
    }

}
