package org.oclinchoco.nodecsp;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarSource;

public class VarNode implements VarSource{
    IntVar var;

    public VarNode(CSP csp, int i){
        this.var = csp.model().intVar(i);
    }

    public VarNode(CSP csp, int lb, int ub){
        this.var = csp.model().intVar(lb, ub);
    }

    @Override
    public IntVar var() {return var;}
}
