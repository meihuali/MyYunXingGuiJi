package com.example.xiao.myappdemobaidu.sqlite;

import android.content.Context;

import com.example.anonymous.greendao.gen.DaoMaster;
import com.example.anonymous.greendao.gen.DaoSession;
import com.example.anonymous.greendao.gen.IdBeanDao;
import com.example.xiao.myappdemobaidu.entity.IdBean;

import java.util.List;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.sqlite
 * 文件名：MyGreenDao
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/6 0006 下午 1:47
 * 描述：TODO
 */
public class MyGreenDao {
    /*这个是保存数据*/
    public static IdBeanDao greendao(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "my-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        IdBeanDao userDao = daoSession.getIdBeanDao();
        return userDao;
    }

    /**
     * 查询用户列表 也可以说是取
     */
    public static List<IdBean> queryUserList(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "my-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        IdBeanDao userDao = daoSession.getIdBeanDao();

        List<IdBean> userList = userDao.queryBuilder()
                .where(IdBeanDao.Properties.Id.notEq(1))
//                .limit(5) //这里是限制 只取5条 最后五条
                .build().list();
        return userList;
    }

    /*这个是删除数据*/
    public static void deleData(Context context,IdBean name) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "my-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        IdBeanDao userDao = daoSession.getIdBeanDao();
            /*下面，的那个name 就是要删除的 数据*/
        //这里传入的直接就是 实体对象·也就是说·你删除什么内容·就传递什么内容
        userDao.delete(name);
    }


}
