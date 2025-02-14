package org.oclinchoco.navigation;
import org.oclinchoco.CSP;
import org.oclinchoco.property.ReferenceTable;
import org.oclinchoco.property.SingleIntTable;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;

import org.chocosolver.solver.variables.IntVar;

public class NavCSP implements Source {
    IntVar[] vars;
    NavTable target_table;

    public NavCSP(CSP m, PtrSource src, NavTable table){
        target_table=table;
        int n = src.pointers().length;
        int nn = table.cols();
        int nnn = n*nn;
        vars = m.model().intVarArray(nnn,0, table.ub());

        int k=0;
        for(int i=0;i<n;i++) for(int j=0;j<nn;j++){ 
            IntVar ptr = src.pointers()[i].mul(nn).add(j).intVar(); // = pointer arithm
            m.model().element(vars[k++],table.navTable(),ptr,0).post();
        }
    }

    public static NavCSP makeNavCSP(CSP csp, PtrSource src, NavTable table){
        return switch(table){
            case ReferenceTable refs -> new Nav2Ptr(csp, src, refs);
            case SingleIntTable att -> new Nav2Int(csp, src, att);
            default -> throw new UnsupportedOperationException("can't navigate to " + table);
        };
    }

    @Override
    public int size() { return vars.length; }

    public NavTable getTable(){ return target_table;}
}
