package com.mysqlex;

import com.sun.org.apache.xalan.internal.utils.XMLSecurityPropertyManager;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import static com.mysqlex.DBinit.buildSqlSessionFactory;

public class MainSQLTest {
     Mapper mapper;
     SqlSessionFactory factory;
     SqlSession session;

     @BeforeClass
     public static void try_init_DB(){
          DBinit.init();
          DBinit.buildSqlSessionFactory();

     }

     @Test
     public void is_user_added(){

          factory = buildSqlSessionFactory();
          session = factory.openSession();
          mapper = session.getMapper(Mapper.class);

          DAO dao_expected = new DAO();
          dao_expected.setCity_name("Казань");
          DAO dao_actual = mapper.findCity(dao_expected);
          System.out.println(dao_expected.getCity_name());
          System.out.println(dao_actual.getCity_name());
          Assert.assertEquals(dao_expected.getCity_name(), dao_actual.getCity_name());
          session.close();
     }

     @Test
     public void get_all_cities(){

          factory = buildSqlSessionFactory();
          session = factory.openSession();
          mapper = session.getMapper(Mapper.class);

          Map<Integer, DAO> map = mapper.getAllGorods();
          for(DAO note : map.values()){
               System.out.println("Город: "+note.getCity_name()+", регион: "+note.getRegion_id()+", население: "+note.getNaselenie());
          }

          session.close();
     }

     @Test
     public void get_cities_for_specified_region(){
          factory = buildSqlSessionFactory();
          session = factory.openSession();
          mapper = session.getMapper(Mapper.class);

          DAO dao = new DAO();
          dao.setRegion_id(1);
          List<DAO> daoList = mapper.getCitiesOrderedByPopulationForSpecifiedRegion(dao);
          for(DAO daoListUnit : daoList){
               System.out.println("City name: "+daoListUnit.getCity_name()+" Population: "+daoListUnit.getNaselenie());
          }

          session.close();

     }


     @Test
     public void get_city_with_max_people(){
          factory = buildSqlSessionFactory();
          session = factory.openSession();
          mapper = session.getMapper(Mapper.class);

          DAO dao = mapper.getCityWithMaxNaselenie();
          System.out.println("Город "+dao.getCity_name()+", население: "+dao.getNaselenie());
          session.close();
     }

     @Test
     public void check_number_unpopulated_cities(){
          factory = buildSqlSessionFactory();
          session = factory.openSession();
          mapper = session.getMapper(Mapper.class);
          int number_of_cities = mapper.getNumberOfUnpopulatedCities();
          System.out.println("Число ненаселенных городов: "+number_of_cities);
          DAO dao1 = new DAO(9, "Пустой город 1", "", 1 );
          DAO dao2 = new DAO(10, "Пустой город 2", "", 2);

          mapper.addCity(dao1);
          mapper.addCity(dao2);

          Map<Integer, DAO> map = mapper.getAllGorods();
          for(DAO note : map.values()){
               System.out.println("Город: "+note.getCity_name()+", регион: "+note.getRegion_id()+", население: "+note.getNaselenie());
          }
          System.out.println("=====================================================================");
          number_of_cities = mapper.getNumberOfUnpopulatedCities();
          System.out.println("Число ненаселенных городов теперь: "+number_of_cities);

          session.close();
     }

     @Test
     public void delete_cities_with_specified_population(){
          factory = buildSqlSessionFactory();
          session = factory.openSession();
          mapper = session.getMapper(Mapper.class);

          Map<Integer, DAO> map = mapper.getAllGorods();
          for(DAO note : map.values()){
               System.out.println("Город: "+note.getCity_name()+", регион: "+note.getRegion_id()+", население: "+note.getNaselenie());
          }

          mapper.deleteLowerSpecifiedPopulation(400000);
          System.out.println("=====================================================================");
          map = mapper.getAllGorods();
          for(DAO note : map.values()){
               System.out.println("Город: "+note.getCity_name()+", регион: "+note.getRegion_id()+", население: "+note.getNaselenie());
          }
          session.close();

     }

     @Test
     public void change_population(){
          factory = buildSqlSessionFactory();
          session = factory.openSession();
          mapper = session.getMapper(Mapper.class);

          Map<Integer, DAO> map = mapper.getAllGorods();
          for(DAO note : map.values()){
               System.out.println("Город: "+note.getCity_name()+", регион: "+note.getRegion_id()+", население: "+note.getNaselenie());
          }

          DAO dao = new DAO();
          dao.setRegion_id(1);
          dao.setNaselenie(200000);
          mapper.updatePopulationsInSpecificRegion(dao);
          System.out.println("=====================================================================");
          map = mapper.getAllGorods();
          for(DAO note : map.values()){
               System.out.println("Город: "+note.getCity_name()+", регион: "+note.getRegion_id()+", население: "+note.getNaselenie());
          }
          session.close();

     }

     @Test
     public void numberCitiesForSpecRegion(){
          factory = buildSqlSessionFactory();
          session = factory.openSession();
          mapper = session.getMapper(Mapper.class);

          Map<Integer, DAO> map = mapper.getAllGorods();
          for(DAO note : map.values()){
               System.out.println("Город: "+note.getCity_name()+", регион: "+note.getRegion_id()+", население: "+note.getNaselenie());
          }

          //List<DAO> list = mapper.findNumberCitiesForSpecifiedRegion();
          List<DAOcounter> list = mapper.findNumberCitiesForSpecifiedRegion();
          System.out.println("=====================================================================");
          for(DAOcounter dao : list){
               System.out.println("ID "+dao.getId()+" region: "+dao.getRegionName()+" occurrences: "+dao.getCount());
          }

          session.close();

     }

     @Test
     public void find_cities_started_with_Letter(){
          factory = buildSqlSessionFactory();
          session = factory.openSession();
          mapper = session.getMapper(Mapper.class);

          Map<Integer, DAO> map = mapper.getAllCitiesStartedWithLetter();
          for(DAO note : map.values()){
               System.out.println("Город: "+note.getCity_name()+", регион: "+note.getRegion_id()+", население: "+note.getNaselenie());
          }

          session.close();

     }

}
