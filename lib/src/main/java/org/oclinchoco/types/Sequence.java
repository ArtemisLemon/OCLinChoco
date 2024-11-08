package org.oclinchoco;
import org.chocosolver.solver.variables.IntVar;

public interface Sequence extends Collection{
    public IntVar first();
}
