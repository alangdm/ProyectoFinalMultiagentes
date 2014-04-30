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
public class NavState {
    private Coord2D taxi;

    public Coord2D getT() {
        return t;
    }
    private Coord2D t;

    public NavState(Coord2D taxi, Coord2D t) {
        this.taxi = taxi;
        this.t = t;
    }

    public Coord2D getTaxi() {
        return taxi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.taxi);
        hash = 13 * hash + Objects.hashCode(this.t);
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
        final NavState other = (NavState) obj;
        if (!Objects.equals(this.taxi, other.taxi)) {
            return false;
        }
        if (!Objects.equals(this.t, other.t)) {
            return false;
        }
        return true;
    }

}
