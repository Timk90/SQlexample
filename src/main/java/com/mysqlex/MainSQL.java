package com.mysqlex;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

import static com.mysqlex.DBinit.buildSqlSessionFactory;
import static com.mysqlex.DBinit.init;

public class MainSQL {

    public static void main(String[] args) {

        Mapper mapper;
        SqlSessionFactory factory;
        SqlSession session;
        DBinit.init();
        factory = buildSqlSessionFactory();
        session = factory.openSession();
        mapper = session.getMapper(Mapper.class);
        List<DAO> list;

        DAO dao = new DAO();
        dao.setRegion_id(1); //Соответствует чувашской республике

        System.out.println("Задание №1");
        System.out.println("============Города относящиеся к Чувашской Республике (сортировка DESC)============");
        list  = mapper.getCitiesOrderedByPopulationForSpecifiedRegion(dao);
        for(DAO dao1 : list){
            System.out.println(dao1.toString());
        }
        System.out.println("===================================================================================");
        System.out.println();
        System.out.println("Задание №2");
        System.out.println("============Города для которых не указана численность населения ===================");
        int number  =  mapper.getNumberOfUnpopulatedCities();
        System.out.println("Число незаселенных городов = "+number);
        System.out.println("===================================================================================");

        System.out.println();
        System.out.println("Задание №3");
        System.out.println("============Город c наибольшей численностью населения =============================");
        DAO dao2  =  mapper.getCityWithMaxNaselenie();
        System.out.println(dao2.toString());
        System.out.println("===================================================================================");

        System.out.println();
        System.out.println("Задание №4");
        System.out.println("============Удаление таблиц с населением меньше 400000 =============================");
        Map<Integer, DAO> map = mapper.getAllGorods();
        System.out.println("НАЧАЛЬНЫЕ ГОРОДА");
        for (DAO daoTmp : map.values())
        {
            System.out.println(daoTmp.toString());
        }
        mapper.deleteLowerSpecifiedPopulation(400000);
        map = mapper.getAllGorods();
        System.out.println("ОСТАВШИЕСЯ ГОРОДА");
        for (DAO daoTmp : map.values())
        {
            System.out.println(daoTmp.toString());
        }
        System.out.println("===================================================================================");
        System.out.println();
        session.close();

        init();
        factory = buildSqlSessionFactory();
        session = factory.openSession();
        mapper = session.getMapper(Mapper.class);

        System.out.println();
        System.out.println("Задание №5");
        System.out.println("============Изменение населением Чувашской Республики =============================");
        map = null;
        map = mapper.getAllGorods();
        System.out.println("НАЧАЛЬНОЕ НАСЕЛЕНИЕ ЧУВШСКОЙ РЕСПУБЛИКИ ");
        for (DAO daoTmp : map.values())
        {
            System.out.println(daoTmp.toString());
        }
        DAO dao3 = new DAO();
        dao3.setRegion_id(1);
        dao3.setNaselenie(200000);
        mapper.updatePopulationsInSpecificRegion(dao3);
        map = mapper.getAllGorods();
        System.out.println("КОНЕЧНОЕ НАСЕЛЕНИЕ ЧУВШСКОЙ РЕСПУБЛИКИ ");
        for (DAO daoTmp : map.values())
        {
            System.out.println(daoTmp.toString());
        }
        System.out.println("===================================================================================");

        System.out.println();
        session.close();
        init();
        factory = buildSqlSessionFactory();
        session = factory.openSession();
        mapper = session.getMapper(Mapper.class);
        System.out.println();
        System.out.println("Задание №6");
        System.out.println("============Отображение городов, начинающихся на букву К =============================");
        map = mapper.getAllCitiesStartedWithLetter();
        for (DAO daoTmp : map.values())
        {
            System.out.println(daoTmp.toString());
        }
        System.out.println("===================================================================================");

        System.out.println();

        System.out.println();
        session.close();
        init();
        factory = buildSqlSessionFactory();
        session = factory.openSession();
        mapper = session.getMapper(Mapper.class);
        System.out.println();
        System.out.println("Задание №7");
        System.out.println("============Подсчет количества городов в регионах =================================");
        List<DAOcounter> daos = mapper.findNumberCitiesForSpecifiedRegion();
        for (DAOcounter daoTmp : daos)
        {
            System.out.println(daoTmp.toString());
        }
        System.out.println("===================================================================================");
        System.out.println();

        session.close();
        }



}
