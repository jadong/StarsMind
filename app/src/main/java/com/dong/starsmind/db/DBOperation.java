package com.dong.starsmind.db;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengwendong on 16/10/27.
 */
public class DBOperation {

    private static final String DB_NAME = "stars_mind";
    private static DbManager dbManager;
    private static DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName(DB_NAME)
            .setDbVersion(1)
            .setDbUpgradeListener(new SMDbUpgradeListener());

    static {
        dbManager = x.getDb(daoConfig);
    }

    public static void saveOrUpdate(Object object) {
        try {
            dbManager.saveOrUpdate(object);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void deleteById(Class<?> entityType, Object idValue) {
        try {
            dbManager.deleteById(entityType, idValue);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> findAll(Class<T> entityType, int offset, int limit) {
        try {
            return dbManager.selector(entityType).offset(offset).limit(limit).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static <T> List<T> findAll(Class<T> entityType, int offset, int limit, String orderByColumnName, boolean desc) {
        try {
            return dbManager.selector(entityType).offset(offset).limit(limit).orderBy(orderByColumnName, desc).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
