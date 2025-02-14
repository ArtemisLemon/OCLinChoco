package org.oclinchoco.navigation;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.VarSource;

public class Nav2Int extends NavCSP implements VarSource {

    public Nav2Int(CSP m, PtrSource src, NavTable table) {
        super(m, src, table);
    }

    @Override
    public IntVar var() {
        return vars[0];
    }

}
