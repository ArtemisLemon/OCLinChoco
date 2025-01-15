package org.oclinchoco;
import org.oclinchoco.property.NavTable;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;
import org.chocosolver.solver.variables.IntVar;

public class NavCSP implements Source {
    IntVar[] vars;
    
    public NavCSP(CSP m, PtrSource src, NavTable table){
        int n = src.pointers().length;
        int nn = table.cols();
        int nnn = n*nn;
        vars = m.csp.intVarArray(nnn,table.lb(), table.ub());

        int k=0;
        for(int i=0;i<n;i++) for(int j=0;j<nn;j++){ 
            IntVar ptr = src.pointers()[i].mul(nn).add(j).intVar(); // = pointer arithm
            m.csp.element(vars[k++],table.navTable(),ptr,0).post();
        }
    }
}
