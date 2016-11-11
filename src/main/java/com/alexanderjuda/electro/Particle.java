package com.alexanderjuda.electro;

import java.util.List;

/**
 * Created by alex on 07/11/2016.
 */
public class Particle {
    private List<Double> position;
    private Double charge;
    private int dimensionsCount;

    public Particle(List<Double> position, Double charge) {
        this.position = position;
        this.dimensionsCount = position.size();
        this.charge = charge;
    }

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        if (position.size() != dimensionsCount) {
            // make sure position lists are always of the same size
            throw new IllegalArgumentException("Attempting to set particle's position to another dimensions." +
                    "Was "+dimensionsCount+", now "+position.size());
        }
        this.position = position;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public String toString() {
        return "x=" + position.toString() + " q=" + charge.toString();
    }


    public boolean equals(Object o) {
        if (o == null) return false;
        if(! (o instanceof Particle)) return false;

        Particle other = (Particle) o;
        if (! position.equals(other.position)) return false;
        if (! charge.equals(other.charge)) return false;

        return true;
    }

    public int hashCode() {
        return charge.intValue();
    }
}
