package com.dong.starsmind.db;

import org.xutils.DbManager;

/**
 * Created by zengwendong on 16/10/27.
 */
public class SMDbUpgradeListener implements DbManager.DbUpgradeListener {

    @Override
    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
        // TODO 数据库升级操作
        // db.addColumn(...);
        // db.dropTable(...);
    }
}
