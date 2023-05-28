package org.dstu.dao;

import org.dstu.domain.Ship;
import org.hibernate.query.Query;

import java.util.List;

public class ShipDao extends BaseDaoImpl<Ship, Integer> {
    public ShipDao() {
        super(Ship.class);
    }

    public List<Ship> getOverCargo(int cargo) {
        Query q = getSession().createQuery("FROM Ship WHERE max_cargo > " + cargo);
        return q.list();
    }
}
