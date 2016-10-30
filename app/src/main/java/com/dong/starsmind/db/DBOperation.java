package com.dong.starsmind.db;

import android.text.TextUtils;

import org.xutils.DbManager;
import org.xutils.db.Selector;
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

    public static long count(Class entityType){
        try {
            return dbManager.selector(entityType).count();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static <T> List<T> findAll(Class<T> entityType, DBPage dbPage) {
        try {
            int offset = (dbPage.getPageNo()-1) * dbPage.getPageSize();
            Selector<T> selector = dbManager.selector(entityType).offset(offset).limit(dbPage.getPageSize());
            if (!TextUtils.isEmpty(dbPage.getColumnName())) {
                selector.orderBy(dbPage.getColumnName(), dbPage.getDesc());
            }
            long rows = selector.count();
            dbPage.setRows(rows);
            return selector.findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
