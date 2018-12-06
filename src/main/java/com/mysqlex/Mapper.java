package com.mysqlex;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface Mapper {

    @Select("SELECT COUNT(*) FROM gorod WHERE naselenie IS NULL ")
    int getNumberOfUnpopulatedCities();

    @Select("SELECT * FROM gorod WHERE naselenie = (SELECT MAX(naselenie) from gorod)")
    DAO getCityWithMaxNaselenie();

    @Select("SELECT * FROM gorod WHERE region_id = #{region_id} ORDER BY naselenie DESC")
    List<DAO> getCitiesOrderedByPopulationForSpecifiedRegion(DAO dao);

    @Select("select * from gorod ")
    @MapKey("id")
    Map<Integer, DAO> getAllGorods();

    @Select("select * from region ")
    @MapKey("id")
    Map<Integer, DAO> getAllRegions();

    @Select("select * from gorod WHERE city_name LIKE 'К%'")
    @MapKey("id")
    Map<Integer, DAO> getAllCitiesStartedWithLetter();

    @Select("select * from gorod where city_name = #{city_name}")
    DAO findCity(DAO dao);

    @Insert("INSERT INTO gorod (id, city_name, region_id, naselenie) VALUES (#{id}, #{city_name}, #{region_id}, #{naselenie})")
    void addCity(DAO dao);

    @Update("Update gorod set naselenie = #{naselenie} where region_id = #{region_id}")
    void updatePopulationsInSpecificRegion(DAO dao);

    @Delete("Delete from gorod where naselenie < #{population}")
    void deleteLowerSpecifiedPopulation(int population);

    @Select("SELECT r.id, r.region_name, g.cnt FROM region r LEFT JOIN (SELECT COUNT(region_id) as cnt, region_id FROM gorod GROUP BY region_id) g ON r.id = g.region_id")
    List<DAOcounter> findNumberCitiesForSpecifiedRegion();


}
