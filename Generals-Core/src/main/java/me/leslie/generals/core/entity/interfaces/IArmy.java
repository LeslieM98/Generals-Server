/*
 * This file is generated by jOOQ.
 */
package me.leslie.generals.core.entity.interfaces;


import javax.annotation.processing.Generated;
import java.io.Serializable;


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
public interface IArmy extends Serializable {

    /**
     * Getter for <code>ARMY.HQ</code>.
     */
    public Integer getHq();

    /**
     * Getter for <code>ARMY.TROOP</code>.
     */
    public Integer getTroop();
}
