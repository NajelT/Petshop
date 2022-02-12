package com.buseduc.javacourse.petshop.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateConfig {
    private static HibernateConfig instance;
    private SessionFactory factoryObj;
    public void doConfig() {
        Configuration cfgObj = new Configuration(); // Hibernate Configuration Object
        cfgObj.configure("hibernate.cfg.xml");
        factoryObj = cfgObj.buildSessionFactory(); // Session Factory Object
    }

    public static HibernateConfig getInstance() {
        if (instance == null) {
            instance = new HibernateConfig();
        }
        return instance;
    }

    public SessionFactory getFactoryObj() {
        return factoryObj;
    }
}
