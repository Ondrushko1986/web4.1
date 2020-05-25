package DAO;

import model.Car;
import org.hibernate.Session;

import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public List<Car> getAllCars() {

    }

}
