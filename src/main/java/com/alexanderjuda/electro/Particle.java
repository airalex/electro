package com.alexanderjuda.electro;

import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.PrimitiveMatrix;

import java.util.List;

/**
 * Created by alex on 07/11/2016.
 */
public class Particle {
    // column vector
    private BasicMatrix position;
    private Double functionValue;

    Particle(BasicMatrix position, double functionValue) {
        this.position = position;
        this.functionValue = functionValue;
    }

    Particle(double[] position, double functionValue) {
        BasicMatrix.Factory<PrimitiveMatrix> factory = PrimitiveMatrix.FACTORY;
        this.position = factory.columns(position);
        this.functionValue = functionValue;
    }

    public BasicMatrix getPosition() {
        return position;
    }

    public void setPosition(BasicMatrix position) throws IllegalArgumentException {
        if (position.count() != dimensionsCount()) {
            // make sure position lists are always of the same size
            throw new IllegalArgumentException("Attempting to set particle's position to another dimensions." +
                    "Was "+dimensionsCount()+", now "+position.countRows());
        }
        this.position = position;
    }

    public double getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(double functionValue) {
        this.functionValue = functionValue;
    }

    public String toString() {
        return "x=" + position.toString() + " q=" + functionValue.toString();
    }


    public boolean equals(Object o) {
        if (o == null) return false;
        if(! (o instanceof Particle)) return false;

        Particle other = (Particle) o;
        if (! position.equals(other.position)) return false;
        if (! functionValue.equals(other.functionValue)) return false;

        return true;
    }

    public int hashCode() {
        return functionValue.intValue();
    }

    private long dimensionsCount() {
        return position.count();
    }
}
