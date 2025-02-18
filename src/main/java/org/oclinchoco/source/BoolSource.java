package org.oclinchoco.source;

import org.chocosolver.solver.variables.BoolVar;

public interface BoolSource extends Source {
    BoolVar bool();

    @Override
    default int size(){return 1;}
}