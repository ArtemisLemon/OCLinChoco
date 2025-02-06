package org.oclinchoco.nodecsp;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.*;

public class VariableExpNode implements PtrSource {
    IntVar[] self;

    public VariableExpNode(CSP csp, int self){
        int[] tmp = {self};
        this.self = csp.model().intVarArray(1, tmp);
    }

    @Override
    public int maxCard() {return 1;}

    @Override
    public IntVar[] pointers() {return self;}

}
