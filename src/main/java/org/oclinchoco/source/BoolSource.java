package org.oclinchoco.source;

import org.chocosolver.solver.variables.BoolVar;

public interface BoolSource extends Source {
    BoolVar var();

    @Override
    default int size(){return 1;}
}