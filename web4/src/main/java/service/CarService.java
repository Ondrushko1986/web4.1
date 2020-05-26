package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarService {

    private static CarService carService;

    private SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCars();
    }

    public List<Car> getAllCarOfBrand(String brand) {
        return new CarDao(sessionFactory.openSession()).getAllCarOfBrand(brand);
    }

    public boolean addCar(Car car) {
        if (countOFCars(car.getBrand()) <= 10) {
            new CarDao(sessionFactory.openSession()).addCar(car);
            return true;
        }
        return false;
    }

    public Car buyCar (Car car) {

    }

    public int countOFCars (String brand) {
        return new CarDao(sessionFactory.openSession()).countOFCars("brand");
    }

    public void delete () {
        new CarDao(sessionFactory.openSession()).deleteAllCars();
    }


}
