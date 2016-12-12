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

    public static <T> T findById(Class<T> entityType, Object idValue) {
        try {
            return dbManager.findById(entityType, idValue);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean saveOrUpdate(Object object) {
        try {
            dbManager.saveOrUpdate(object);
            return true;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteById(Class<?> entityType, Object idValue) {
        try {
            dbManager.deleteById(entityType, idValue);
            return true;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteAll(Class<?> entityType) {
        try {
            dbManager.delete(entityType);
            return true;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static long count(Class entityType) {
        try {
            return dbManager.selector(entityType).count();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static <T> DBPage<T> findAll(Class<T> entityType, DBPage<T> dbPage) {
        try {
            int offset = (dbPage.getPageNo() - 1) * dbPage.getPageSize();
            Selector<T> selector = dbManager.selector(entityType).where(dbPage.getWhereBuilder());

            //查询总行数
            long rows = selector.count();//(5+1)-5=1
            dbPage.setRows(rows);

            //分页查询
            selector.offset(offset).limit(dbPage.getPageSize());
            if (!TextUtils.isEmpty(dbPage.getColumnName())) {//排序
                selector.orderBy(dbPage.getColumnName(), dbPage.getDesc());
            }
            dbPage.setDataList(selector.findAll());
            return dbPage;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return dbPage;
    }

}
