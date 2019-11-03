/*
 * This file is generated by jOOQ.
 */
package me.leslie.generals.server.persistence.jooq.tables.daos;


import me.leslie.generals.server.persistence.jooq.tables.Army;
import me.leslie.generals.server.persistence.jooq.tables.records.ArmyRecord;
import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;

import javax.annotation.processing.Generated;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.12.2"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ArmyDao extends DAOImpl<ArmyRecord, me.leslie.generals.core.entity.pojos.Army, Record2<Integer, Integer>> {

    /**
     * Create a new ArmyDao without any configuration
     */
    public ArmyDao() {
        super(Army.ARMY, me.leslie.generals.core.entity.pojos.Army.class);
    }

    /**
     * Create a new ArmyDao with an attached configuration
     */
    public ArmyDao(Configuration configuration) {
        super(Army.ARMY, me.leslie.generals.core.entity.pojos.Army.class, configuration);
    }

    @Override
    public Record2<Integer, Integer> getId(me.leslie.generals.core.entity.pojos.Army object) {
        return compositeKeyRecord(object.getHq(), object.getTroop());
    }

    /**
     * Fetch records that have <code>HQ BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<me.leslie.generals.core.entity.pojos.Army> fetchRangeOfHq(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Army.ARMY.HQ, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>HQ IN (values)</code>
     */
    public List<me.leslie.generals.core.entity.pojos.Army> fetchByHq(Integer... values) {
        return fetch(Army.ARMY.HQ, values);
    }

    /**
     * Fetch records that have <code>TROOP BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<me.leslie.generals.core.entity.pojos.Army> fetchRangeOfTroop(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Army.ARMY.TROOP, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>TROOP IN (values)</code>
     */
    public List<me.leslie.generals.core.entity.pojos.Army> fetchByTroop(Integer... values) {
        return fetch(Army.ARMY.TROOP, values);
    }
}
