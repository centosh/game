package com.hatbazar.services;

import com.hatbazar.dao.ItemSoldDao;
import com.hatbazar.domains.ItemSold;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/28/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemSoldService {
    public ItemSold get(int id) throws InstantiationException, IllegalAccessException, SQLException {
        ItemSoldDao itemSoldDao = new ItemSoldDao();
        return itemSoldDao.get(id);
    }

    public boolean save(int item, int reqBy) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        return  new ItemSoldDao().create(item,reqBy);
    }
    public List<ItemSold> findAll() throws IllegalAccessException, SQLException, InstantiationException {
        ItemSoldDao itemSoldDao = new ItemSoldDao();
        return itemSoldDao.all();
    }
}
