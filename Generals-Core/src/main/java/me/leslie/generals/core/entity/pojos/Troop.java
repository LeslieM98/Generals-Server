/*
 * This file is generated by jOOQ.
 */
package me.leslie.generals.core.entity.pojos;


import me.leslie.generals.core.entity.interfaces.ITroop;

import javax.annotation.processing.Generated;
import java.util.Objects;


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
public class Troop implements ITroop {

    private static final long serialVersionUID = -469900385;

    private Integer id;
    private Integer currentHealth;
    private Integer maxHealth;
    private Double posX;
    private Double posY;
    private Double normalSpeed;
    private Double streetSpeed;
    private Double difficultTerrainSpeed;
    private Double closeCombatRange;
    private Double rangedCombatRange;
    private Double normalViewDistance;
    private Double disadvantagedViewDistance;
    private Double advantagedViewDistance;

    public Troop() {
    }

    public Troop(ITroop value) {
        this.id = value.getId();
        this.currentHealth = value.getCurrentHealth();
        this.maxHealth = value.getMaxHealth();
        this.posX = value.getPosX();
        this.posY = value.getPosY();
        this.normalSpeed = value.getNormalSpeed();
        this.streetSpeed = value.getStreetSpeed();
        this.difficultTerrainSpeed = value.getDifficultTerrainSpeed();
        this.closeCombatRange = value.getCloseCombatRange();
        this.rangedCombatRange = value.getRangedCombatRange();
        this.normalViewDistance = value.getNormalViewDistance();
        this.disadvantagedViewDistance = value.getDisadvantagedViewDistance();
        this.advantagedViewDistance = value.getAdvantagedViewDistance();
    }

    public Troop(
            Integer id,
            Integer currentHealth,
            Integer maxHealth,
            Double posX,
            Double posY,
            Double normalSpeed,
            Double streetSpeed,
            Double difficultTerrainSpeed,
            Double closeCombatRange,
            Double rangedCombatRange,
            Double normalViewDistance,
            Double disadvantagedViewDistance,
            Double advantagedViewDistance
    ) {
        this.id = id;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.posX = posX;
        this.posY = posY;
        this.normalSpeed = normalSpeed;
        this.streetSpeed = streetSpeed;
        this.difficultTerrainSpeed = difficultTerrainSpeed;
        this.closeCombatRange = closeCombatRange;
        this.rangedCombatRange = rangedCombatRange;
        this.normalViewDistance = normalViewDistance;
        this.disadvantagedViewDistance = disadvantagedViewDistance;
        this.advantagedViewDistance = advantagedViewDistance;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getCurrentHealth() {
        return this.currentHealth;
    }

    public void setCurrentHealth(Integer currentHealth) {
        this.currentHealth = currentHealth;
    }

    @Override
    public Integer getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public Double getPosX() {
        return this.posX;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }

    @Override
    public Double getPosY() {
        return this.posY;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    @Override
    public Double getNormalSpeed() {
        return this.normalSpeed;
    }

    public void setNormalSpeed(Double normalSpeed) {
        this.normalSpeed = normalSpeed;
    }

    @Override
    public Double getStreetSpeed() {
        return this.streetSpeed;
    }

    public void setStreetSpeed(Double streetSpeed) {
        this.streetSpeed = streetSpeed;
    }

    @Override
    public Double getDifficultTerrainSpeed() {
        return this.difficultTerrainSpeed;
    }

    public void setDifficultTerrainSpeed(Double difficultTerrainSpeed) {
        this.difficultTerrainSpeed = difficultTerrainSpeed;
    }

    @Override
    public Double getCloseCombatRange() {
        return this.closeCombatRange;
    }

    public void setCloseCombatRange(Double closeCombatRange) {
        this.closeCombatRange = closeCombatRange;
    }

    @Override
    public Double getRangedCombatRange() {
        return this.rangedCombatRange;
    }

    public void setRangedCombatRange(Double rangedCombatRange) {
        this.rangedCombatRange = rangedCombatRange;
    }

    @Override
    public Double getNormalViewDistance() {
        return this.normalViewDistance;
    }

    public void setNormalViewDistance(Double normalViewDistance) {
        this.normalViewDistance = normalViewDistance;
    }

    @Override
    public Double getDisadvantagedViewDistance() {
        return this.disadvantagedViewDistance;
    }

    public void setDisadvantagedViewDistance(Double disadvantagedViewDistance) {
        this.disadvantagedViewDistance = disadvantagedViewDistance;
    }

    @Override
    public Double getAdvantagedViewDistance() {
        return this.advantagedViewDistance;
    }

    public void setAdvantagedViewDistance(Double advantagedViewDistance) {
        this.advantagedViewDistance = advantagedViewDistance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Troop (");

        sb.append(id);
        sb.append(", ").append(currentHealth);
        sb.append(", ").append(maxHealth);
        sb.append(", ").append(posX);
        sb.append(", ").append(posY);
        sb.append(", ").append(normalSpeed);
        sb.append(", ").append(streetSpeed);
        sb.append(", ").append(difficultTerrainSpeed);
        sb.append(", ").append(closeCombatRange);
        sb.append(", ").append(rangedCombatRange);
        sb.append(", ").append(normalViewDistance);
        sb.append(", ").append(disadvantagedViewDistance);
        sb.append(", ").append(advantagedViewDistance);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    public void from(ITroop from) {
        setId(from.getId());
        setCurrentHealth(from.getCurrentHealth());
        setMaxHealth(from.getMaxHealth());
        setPosX(from.getPosX());
        setPosY(from.getPosY());
        setNormalSpeed(from.getNormalSpeed());
        setStreetSpeed(from.getStreetSpeed());
        setDifficultTerrainSpeed(from.getDifficultTerrainSpeed());
        setCloseCombatRange(from.getCloseCombatRange());
        setRangedCombatRange(from.getRangedCombatRange());
        setNormalViewDistance(from.getNormalViewDistance());
        setDisadvantagedViewDistance(from.getDisadvantagedViewDistance());
        setAdvantagedViewDistance(from.getAdvantagedViewDistance());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ITroop)) return false;
        ITroop troop = (ITroop) o;
        return Objects.equals(getId(), troop.getId()) &&
                Objects.equals(getCurrentHealth(), troop.getCurrentHealth()) &&
                Objects.equals(getMaxHealth(), troop.getMaxHealth()) &&
                Objects.equals(getPosX(), troop.getPosX()) &&
                Objects.equals(getPosY(), troop.getPosY()) &&
                Objects.equals(getNormalSpeed(), troop.getNormalSpeed()) &&
                Objects.equals(getStreetSpeed(), troop.getStreetSpeed()) &&
                Objects.equals(getDifficultTerrainSpeed(), troop.getDifficultTerrainSpeed()) &&
                Objects.equals(getCloseCombatRange(), troop.getCloseCombatRange()) &&
                Objects.equals(getRangedCombatRange(), troop.getRangedCombatRange()) &&
                Objects.equals(getNormalViewDistance(), troop.getNormalViewDistance()) &&
                Objects.equals(getDisadvantagedViewDistance(), troop.getDisadvantagedViewDistance()) &&
                Objects.equals(getAdvantagedViewDistance(), troop.getAdvantagedViewDistance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCurrentHealth(), getMaxHealth(), getPosX(), getPosY(), getNormalSpeed(), getStreetSpeed(), getDifficultTerrainSpeed(), getCloseCombatRange(), getRangedCombatRange(), getNormalViewDistance(), getDisadvantagedViewDistance(), getAdvantagedViewDistance());
    }
}
