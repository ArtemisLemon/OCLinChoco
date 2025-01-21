package org.oclinchoco.source;

import org.chocosolver.solver.variables.IntVar;

public interface VarSource extends Source{
    IntVar var();

    @Override
    default int maxCard(){return 1;}
}
