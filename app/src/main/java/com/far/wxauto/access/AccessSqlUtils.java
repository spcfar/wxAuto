package com.far.wxauto.access;

/**
 * Created by god on 2018/9/7.
 */

public class AccessSqlUtils {

//    public SQLiteDatabase db;
//    public static final String TABLE_NAME = "msg_list";
//
//    public AccessSqlUtils(Context mContext, String dbName) {
////        try {
////            db = SQLiteDatabase.openOrCreateDatabase(FileUtils.getMsgDir() + dbName, null);
////        } catch (Exception e) {
////            LogUtils.Logd("=eagle_access=", "=openOrCreateDatabase=eee=" + e);
////        }
//    }
//
//    //创建表
//    public void createTable() {
////        try {
////            String sql = "create table " + TABLE_NAME + " (_id integer primary key autoincrement,msg text)";
////            LogUtils.Logd("eagle_access", "=createTable=" + sql);
////            db.execSQL(sql);
////        } catch (Exception e) {
////            LogUtils.Logd("eagle_access", "=createTable=Exception=" + e);
////        }
//    }
//
//    //删除表
//    public void drop() {
//        String sql = "DROP TABLE stu_table";
//        db.execSQL(sql);
//    }
//
//    //增加数据
//    public void insert(String msg) {
//        try {
////            if (!tabbleIsExist()) {
////                createTable();
////            }
//            String insert_sql = "insert into " + TABLE_NAME + "(msg) values('" + msg + "')";
////        LogUtils.Logd("eagle_access", "=insert_sql=" + insert_sql);
////            db.execSQL(insert_sql);
////        } catch (Exception e) {
////            LogUtils.Logd("eagle_access", "=insert=Exception=" + e);
////        }
//    }
//
//    //查询数据
//    public boolean query(String str) {
////        if (!tabbleIsExist()) {
//////            LogUtils.Logd("eagle_access", tableName + "=不表存在=");
////            return false;
////        }
////        Cursor cursor = null;
////        try {
////            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
////            while (cursor != null && !cursor.isClosed() && cursor.moveToNext()) {
////                int msgIndex = cursor.getColumnIndex("msg");
////                String msg = cursor.getString(msgIndex);
//////            LogUtils.Logd("eagle_access", "=query=" + msg);
////                if (str.equals(msg)) {
////                    cursor.close();
////                    return true;
////                }
////            }
////            cursor.close();
////        } catch (Exception e) {
////            LogUtils.Logd("eagle_access", "=query=Exception=" + e);
////            if (cursor != null)
////                cursor.close();
////        }
//////        LogUtils.Logd("eagle_access", tableName + "=查询无数据=");
////        return false;
//    }
//
//    //删除数据
//    public void delete() {
//        String sql = "delete from stu_table where _id = 6";
//        db.execSQL(sql);
//    }
//
//    //改数据
//    public void update() {
//        String sql = "update stu_table set snumber = 654321 where id = 1";
//        db.execSQL(sql);
//    }
//
//    public boolean tabbleIsExist() {
//        boolean result = false;
//        Cursor cursor = null;
//        try {
//            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + TABLE_NAME + "' ";
//            cursor = db.rawQuery(sql, null);
//            if (cursor.moveToNext()) {
//                int count = cursor.getInt(0);
//                if (count > 0) {
//                    result = true;
//                }
//            }
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            LogUtils.Logd("eagle_access", "=tabbleIsExist=ee=" + e);
//        }
//        return result;
//    }

}
