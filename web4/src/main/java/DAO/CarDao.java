package DAO;

import model.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

//    public Car getCarByParametr(String brand, String model, String number) {
//        session.beginTransaction();
//        List list = session.createCriteria(Car.class).add(Restrictions.like("brand", brand))
//                .add(Restrictions.like("model", model))
//                .add(Restrictions.like("licensePlate", number)).list();
//        session.getTransaction().commit();
//        session.close();
//        return (Car) list.get(0);
//    }

    public Car getCarByParametr(String brand, String model, String number) {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("FROM Car where brand,model,number = '" + brand +  "'").list();
        List<Car> cars = session.createQuery("FROM Car where model = '" + model +  "'").list();
        List<Car> cars = session.createQuery("FROM Car where number = '" + number +  "'").list();
    }


    public List<Car> getCarByBrand(String brand) {
        Transaction transaction = session.beginTransaction();
        List<Car> cars = session.createQuery("FROM Car where brand = '" + brand + "'").list();
        transaction.commit();
        session.close();
        return cars;
    }

    public void addCar(Car car) {
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
        session.close();
    }

    public List<Car> getAllCar() {
        session.beginTransaction();
        List<Car> list = new ArrayList<>(session.createQuery("from Car").list());
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public void deleteCar(Car car) {
        session.beginTransaction();
        session.delete(car);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAllCars() {
        session.beginTransaction();
        session.createQuery("DELETE from Car").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
