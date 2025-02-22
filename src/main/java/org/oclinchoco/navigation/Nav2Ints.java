package org.oclinchoco.navigation;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.VarsSource;

public class Nav2Ints extends NavCSP implements VarsSource{
    IntVar[] alignedVars;

    public Nav2Ints(CSP m, PtrSource src, NavTable table) {
        super(m, src, table);

        alignedVars = m.model().intVarArray(src.size()*table.cols(),table.lb(), table.ub());
        m.elementAlignedCopy(vars,alignedVars,table.lb());
        // m.varNullsAtEnd(alignedVars);
    }

    @Override
    public IntVar[] vars() {return alignedVars;}

    @Override
    public int size() { return vars.length; }

}
