/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package State;

import java.util.Objects;

/**
 *
 * @author Luis Ricardo
 */
public class Double2DCoord {
    private Coord2D a;
    private Coord2D b;

    public Coord2D getA() {
        return a;
    }

    public Coord2D getB() {
        return b;
    }

    public Double2DCoord(Coord2D a, Coord2D b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.a);
        hash = 29 * hash + Objects.hashCode(this.b);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Double2DCoord other = (Double2DCoord) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.b, other.b)) {
            return false;
        }
        return true;
    }
}
