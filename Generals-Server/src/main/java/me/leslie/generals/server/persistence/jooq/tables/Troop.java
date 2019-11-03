/*
 * This file is generated by jOOQ.
 */
package me.leslie.generals.server.persistence.jooq.tables;


import me.leslie.generals.server.persistence.jooq.DefaultSchema;
import me.leslie.generals.server.persistence.jooq.Indexes;
import me.leslie.generals.server.persistence.jooq.Keys;
import me.leslie.generals.server.persistence.jooq.tables.records.TroopRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import javax.annotation.processing.Generated;
import java.util.Arrays;
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
public class Troop extends TableImpl<TroopRecord> {

    private static final long serialVersionUID = 1612660161;

    /**
     * The reference instance of <code>TROOP</code>
     */
    public static final Troop TROOP = new Troop();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TroopRecord> getRecordType() {
        return TroopRecord.class;
    }

    /**
     * The column <code>TROOP.ID</code>.
     */
    public final TableField<TroopRecord, Integer> ID = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>TROOP.CURRENT_HEALTH</code>.
     */
    public final TableField<TroopRecord, Integer> CURRENT_HEALTH = createField(DSL.name("CURRENT_HEALTH"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>TROOP.MAX_HEALTH</code>.
     */
    public final TableField<TroopRecord, Integer> MAX_HEALTH = createField(DSL.name("MAX_HEALTH"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>TROOP.POS_X</code>.
     */
    public final TableField<TroopRecord, Double> POS_X = createField(DSL.name("POS_X"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>TROOP.POS_Y</code>.
     */
    public final TableField<TroopRecord, Double> POS_Y = createField(DSL.name("POS_Y"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>TROOP.NORMAL_SPEED</code>.
     */
    public final TableField<TroopRecord, Double> NORMAL_SPEED = createField(DSL.name("NORMAL_SPEED"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>TROOP.STREET_SPEED</code>.
     */
    public final TableField<TroopRecord, Double> STREET_SPEED = createField(DSL.name("STREET_SPEED"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>TROOP.DIFFICULT_TERRAIN_SPEED</code>.
     */
    public final TableField<TroopRecord, Double> DIFFICULT_TERRAIN_SPEED = createField(DSL.name("DIFFICULT_TERRAIN_SPEED"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>TROOP.CLOSE_COMBAT_RANGE</code>.
     */
    public final TableField<TroopRecord, Double> CLOSE_COMBAT_RANGE = createField(DSL.name("CLOSE_COMBAT_RANGE"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>TROOP.RANGED_COMBAT_RANGE</code>.
     */
    public final TableField<TroopRecord, Double> RANGED_COMBAT_RANGE = createField(DSL.name("RANGED_COMBAT_RANGE"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>TROOP.NORMAL_VIEW_DISTANCE</code>.
     */
    public final TableField<TroopRecord, Double> NORMAL_VIEW_DISTANCE = createField(DSL.name("NORMAL_VIEW_DISTANCE"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>TROOP.DISADVANTAGED_VIEW_DISTANCE</code>.
     */
    public final TableField<TroopRecord, Double> DISADVANTAGED_VIEW_DISTANCE = createField(DSL.name("DISADVANTAGED_VIEW_DISTANCE"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>TROOP.ADVANTAGED_VIEW_DISTANCE</code>.
     */
    public final TableField<TroopRecord, Double> ADVANTAGED_VIEW_DISTANCE = createField(DSL.name("ADVANTAGED_VIEW_DISTANCE"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * Create a <code>TROOP</code> table reference
     */
    public Troop() {
        this(DSL.name("TROOP"), null);
    }

    /**
     * Create an aliased <code>TROOP</code> table reference
     */
    public Troop(String alias) {
        this(DSL.name(alias), TROOP);
    }

    /**
     * Create an aliased <code>TROOP</code> table reference
     */
    public Troop(Name alias) {
        this(alias, TROOP);
    }

    private Troop(Name alias, Table<TroopRecord> aliased) {
        this(alias, aliased, null);
    }

    private Troop(Name alias, Table<TroopRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Troop(Table<O> child, ForeignKey<O, TroopRecord> key) {
        super(child, key, TROOP);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PRIMARY_KEY_4);
    }

    @Override
    public Identity<TroopRecord, Integer> getIdentity() {
        return Keys.IDENTITY_TROOP;
    }

    @Override
    public UniqueKey<TroopRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_4;
    }

    @Override
    public List<UniqueKey<TroopRecord>> getKeys() {
        return Arrays.<UniqueKey<TroopRecord>>asList(Keys.CONSTRAINT_4);
    }

    @Override
    public Troop as(String alias) {
        return new Troop(DSL.name(alias), this);
    }

    @Override
    public Troop as(Name alias) {
        return new Troop(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Troop rename(String name) {
        return new Troop(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Troop rename(Name name) {
        return new Troop(name, null);
    }

    // -------------------------------------------------------------------------
    // Row13 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row13<Integer, Integer, Integer, Double, Double, Double, Double, Double, Double, Double, Double, Double, Double> fieldsRow() {
        return (Row13) super.fieldsRow();
    }
}