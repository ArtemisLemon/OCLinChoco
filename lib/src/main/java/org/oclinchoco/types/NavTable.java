package org.oclinchoco.types;
import org.chocosolver.solver.variables.IntVar;

public interface NavTable {
    public IntVar[] navTable();
    public int cols();
    public int lb();
    public int ub();
}
