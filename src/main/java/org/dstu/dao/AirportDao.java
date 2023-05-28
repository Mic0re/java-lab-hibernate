package org.dstu.dao;

import org.dstu.domain.Airport;

public class AirportDao extends BaseDaoImpl<Airport, Integer> {
    public AirportDao() {
        super(Airport.class);
    }
}
