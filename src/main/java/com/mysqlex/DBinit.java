package com.mysqlex;

import java.sql.*;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class DBinit {

    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String URL = "jdbc:derby:republicTat;create=true";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "pass1234";
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSessionFactory buildSqlSessionFactory() {
        DataSource dataSource = new PooledDataSource(DRIVER, URL, USERNAME, PASSWORD);
        Environment environment = new Environment("Development", new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);

        //declare our mapper here
        configuration.addMapper(Mapper.class);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(configuration);
        return factory;

    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static void init() {
        Connection conn = null;
        Statement stmt;

        String deleteTableGorodSQL = "DROP TABLE gorod";
        String deleteTableRegionSQL = "DROP TABLE region";

        String createSQLgorod =
                "CREATE TABLE gorod ("+
                        "id int not null primary key,"+
                        "city_name varchar(30) NOT NULL,"+
                        "region_id int NOT NULL,"+
                        "naselenie int )";

        String createSQLregion =
                "CREATE TABLE region  ("+
                        "id int not null primary key,"+
                        "region_name varchar(30) NOT NULL)";

        String populateSQLregion =
                "INSERT INTO region VALUES (1, 'Чувашская Республика'),(2, 'Республика Татарстан'),(3, 'Республика Марий Эл'),(4, 'Нижегородская область')";
        String populateSQLgorod =
                "INSERT INTO gorod VALUES (1, 'Чебоксары', 1, 400000), (2, 'Йошкар-Ола', 3, 300000), (3, 'Казань', 2, 1200000), (4, 'Нижний Новгород', 4, 1400000), (5, 'Канаш', 1, 58000), (6, 'Новочебоксарск', 1, null)";

        try {
            Driver derbyEmbeddedDriver = new EmbeddedDriver();
            DriverManager.registerDriver(derbyEmbeddedDriver);
            conn = DriverManager.getConnection("jdbc:derby:republicTat;create=true", "sa", "pass123");
            conn.setAutoCommit(false);

            stmt = conn.createStatement();

            try{
                stmt.execute(deleteTableGorodSQL);
                conn.commit();
                stmt.execute(deleteTableRegionSQL);
                conn.commit();
                stmt.execute(createSQLgorod);
                conn.commit();
                stmt.execute(createSQLregion);
                conn.commit();
                System.out.println("Tables created (1)...");
            }catch (SQLSyntaxErrorException e) {
                if(e.getMessage().contains("it does not exist")) {
                    stmt.execute(createSQLgorod);
                    conn.commit();
                    stmt.execute(createSQLregion);
                    conn.commit();
                    System.out.println("Tables created (2)...");
                }else{
                    System.out.println("Tables have not been created...");
                }
            }finally {

            }

            stmt.execute(populateSQLregion);
            conn.commit();
            System.out.println("Table region populated...");
            stmt.execute(populateSQLgorod);
            conn.commit();
            System.out.println("Table gorod populated...");

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("in connection to DB" + ex);
        }

//        SqlSessionFactory factory = buildSqlSessionFactory();
//        SqlSession session = factory.openSession();
//        Mapper mapper = session.getMapper(Mapper.class);
//        mapper.addUser(user1);
//        mapper.addUser(user2);
//        mapper.addUser(user3);
//        System.out.println("user was added to DB");
//        System.out.println(mapper.getAllUsers().toString());
//        session.commit();
//        session.close();
    }
}
