package com.zbao.news.greendao.utils;


import com.zbao.news.greendao.dao.PatientDao;
import com.zbao.news.greendao.service.PatientService;

/**
 * 工具类获得service
 * Created by zhangYB on 2016/5/5.
 */
public class DBUtils {

    private static PatientService patientService;


    private static PatientDao getPatientDao() {
        PatientDao dao = DBCore.getDaoSession().getPatientDao();
        return dao;
    }

    public static PatientService getPatietService() {
        if (patientService == null) {
            patientService = new PatientService(getPatientDao());
        }
        return patientService;
    }
}
