package org.oclinchoco.navigation;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.PtrSource;

public class Nav2Ptr extends NavCSP implements PtrSource {
    IntVar[] alignedVars;


    public Nav2Ptr(CSP m, PtrSource src, NavTable table) {
        super(m, src, table);

        // alignedVars = m.model().intVarArray(src.size()*table.cols(),table.lb(), table.ub());
        // m.elementAlignedCopy(vars,alignedVars,table.lb());
        // m.ptrNullsAtEnd(alignedVars).post();
    }

    @Override
    public IntVar[] pointers() {return vars;}

    @Override
    public int size() { return vars.length; }

    @Override
    public int ub() {return target_table.ub();}

}
