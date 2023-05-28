package org.dstu.dao;

import org.dstu.domain.Airplane;

public class AirplaneDao extends BaseDaoImpl <Airplane, Integer> {
    public AirplaneDao() {
        super(Airplane.class);
    }
}
