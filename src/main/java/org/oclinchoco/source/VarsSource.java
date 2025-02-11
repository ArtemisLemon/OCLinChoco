package org.oclinchoco.source;

import org.chocosolver.solver.variables.IntVar;

public interface VarsSource extends Source {
    IntVar[] vars();
}
