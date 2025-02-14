package org.oclinchoco.navigation;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.PtrSource;

public class Nav2Ptr extends NavCSP implements PtrSource {

    public Nav2Ptr(CSP m, PtrSource src, NavTable table) {
        super(m, src, table);
    }

    @Override
    public IntVar[] pointers() {return vars;}

    @Override
    public int size() { return vars.length; }

    @Override
    public int ub() {return target_table.ub();}

}
