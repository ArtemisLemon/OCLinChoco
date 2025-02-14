package org.oclinchoco.source;

import org.chocosolver.solver.variables.IntVar;

public interface PtrSource extends Source{
    IntVar[] pointers();
    int ub();
}
