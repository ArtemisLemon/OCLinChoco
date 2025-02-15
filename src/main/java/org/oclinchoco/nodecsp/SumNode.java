package org.oclinchoco.nodecsp;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarSource;
import org.oclinchoco.source.VarsSource;

public class SumNode implements VarSource{
    IntVar sum;

    public SumNode(CSP csp, VarsSource src){
        IntVar nullcount = csp.model().count("", csp.nullattrib(), src.vars());
        IntVar nullsum = csp.nullattrib().abs().mul(nullcount).intVar();
        IntVar rawsum = csp.model().sum("", src.vars());
        sum = rawsum.add(nullsum).intVar();
    }

    @Override
    public IntVar var() {return sum;}

}
