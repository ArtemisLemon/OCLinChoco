package org.oclinchoco.source;

import org.chocosolver.solver.variables.IntVar;

public interface OccSource extends Source {
    IntVar[] occurences();
    int maxCard();
}
