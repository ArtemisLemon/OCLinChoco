package org.oclinchoco.types;
import org.oclinchoco.types.Collection;
import org.chocosolver.solver.variables.IntVar;

public interface Sequence extends Collection{
    public IntVar first();
}
