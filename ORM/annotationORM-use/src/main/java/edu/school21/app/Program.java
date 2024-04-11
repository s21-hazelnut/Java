package edu.school21.app;

import edu.school21.classes.*;
import edu.school21.repositories.*;
import edu.school21.*;
import com.zaxxer.hikari.HikariDataSource;
import org.reflections.Reflections;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.sql.SQLException;

public class Program {


    public static void main(String[] args) {
        List<Class<?>> listIsAnnotationsOrmEntity = getClassesMarkedWithOrmEntity();
                HikariDataSource dataSource = new DataSourceConnectionConfig().getDataSource();
        OrmManager ormManager;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            ormManager = new OrmManager(connection);
            ormManager.initialize(listIsAnnotationsOrmEntity);
            System.out.println();
            User user0 = new User(null, "LastName1", 150);
            User user1 = new User("User_2", null, 306);
            User user2 = new User("User_3", "LastName3", 0);
            Car car0 = new Car("BMW", "525",  "RED", true , 12);
            Car car1 = new Car("LADA", "2101",  "GREEN", false, 1971);
            Car car2 = new Car("KIA", "sonata", "WHITE", true,  1999);

            ormManager.save(user0);
            ormManager.save(user1);
            ormManager.save(user2);
            System.out.println();

            ormManager.save(car0);
            ormManager.save(car1);
            ormManager.save(car2);
            System.out.println();

            user0.setFirstName("User_1");
            user1.setLastName("LastName2");
            user2.setHeight(200);
            System.out.println();

            ormManager.update(user0);
            ormManager.update(user1);
            ormManager.update(user2);
            System.out.println();

            car0.setYear(2000);
            car1.setColor("BLUE");
            car2.setModel("K5");
            ormManager.update(car0);
            ormManager.update(car1);
            ormManager.update(car2);
            System.out.println();

            User idUser = ormManager.findById(3L, User.class);
            Car idCar = ormManager.findById(3L, Car.class);
            System.out.println(idUser);
            System.out.println(idCar);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        dataSource.close();
    }

    public static List<Class<?>> getClassesMarkedWithOrmEntity() {
        Reflections reflections = new Reflections("edu.school21");
        Set<Class<?>> setClassEntity = reflections.getTypesAnnotatedWith(OrmEntity.class);
        return new LinkedList<>(setClassEntity);
    }

}
