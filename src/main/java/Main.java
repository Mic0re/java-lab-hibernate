import org.dstu.dao.AirportDao;
import org.dstu.dao.ShipDao;
import org.dstu.dao.AirplaneDao;
import org.dstu.domain.Airport;
import org.dstu.domain.Ship;
import org.dstu.domain.Airplane;
import org.dstu.util.HibernateUtil;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(final String[] args) throws Exception {
        final Session session = HibernateUtil.getSession();
        ShipDao shipDao = new ShipDao();
        //  insert
        Ship ship = new Ship();
        ship.setBrand("A.P. Moller-Maersk Group");
        ship.setModel("STX Europe");
        ship.setMaxCargo(100000);
        shipDao.save(ship);

        AirportDao airportDao = new AirportDao();

        Airport airport = new Airport();
        airport.setName("Platov");
        airport.setCountry("Russia");
        airport.setCity("Rostov-on-Don");
        airport.setShortName("PLT");

        int savedId = airportDao.save(airport);
        System.out.println(savedId);

        AirplaneDao airplaneDao = new AirplaneDao();
        Airplane airplane = new Airplane();
        airplane.setBrand("Sukhoi");
        airplane.setModel("SuperJet");
        airplane.setNumberOfSeats(96);
        airplane.setAirport(airport);

        airplaneDao.save(airplane);

        Airplane airplane2 = new Airplane();
        airplane2.setBrand("Boeing");
        airplane2.setModel("737-800");
        airplane2.setAirport(airport);
        airplaneDao.save(airplane2);

        // print all db entities rows
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }

        // update
        final Session updateSession = HibernateUtil.getSession();

        List<Ship> ships = shipDao.getOverCargo(90000);
        ships.forEach(foundShip -> System.out.println(foundShip.getBrand() + "\t" + foundShip.getModel() + "\t" + foundShip.getMaxCargo()));
        ships.forEach(ship1 -> ship1.setMaxCargo(90000));
        ships.forEach(shipDao::save);
        updateSession.close();

        final Session conditionalSelectSession = HibernateUtil.getSession();
        // conditional joined select
        Integer minNumberOfSeats = 100;
        Query query =  conditionalSelectSession.createQuery("Select new Map(apt.shortName, apn.brand, apn.model) FROM Airplane apn INNER JOIN apn.airport apt WHERE apt.shortName = 'PLT'");
        List<Map<String,String>> results = query.list();
        results.forEach((result) -> {
            System.out.println(result.get("0") + "\t" + result.get("1") + "\t" + result.get("2"));
        });

        conditionalSelectSession.close();
    }
}