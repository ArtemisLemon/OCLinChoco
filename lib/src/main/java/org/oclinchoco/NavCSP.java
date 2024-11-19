package org.oclinchoco;
import org.oclinchoco.types.Source;
import org.oclinchoco.types.NavTable;
import org.oclinchoco.types.Sequence;
import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.IntVar;

public class NavCSP implements Source, Sequence {
    IntVar[] vars;
    IntVar[] occ_vars;

    IntVar size;
    IntVar min, max;
    
    public NavCSP(CSP m, Source src, NavTable table){
        int n = src.srcVars().length;
        int nn = table.cols();
        int nnn = n*nn;
        vars = m.csp.intVarArray(nnn,table.lb(), table.ub());
        // occ_vars = m.csp.intVarArray(-table.lb()+table.ub()+1, 0,nnn);
        occ_vars = m.csp.intVarArray(table.ub()+1, 0,nnn);

        int k=0;
        for(int i=0;i<n;i++) for(int j=0;j<nn;j++){ 
            IntVar ptr = src.srcVars()[i].mul(nn).add(j).intVar(); // = pointer arithm
            m.csp.element(vars[k++],table.navTable(),ptr,0).post();
        }

        m.csp.globalCardinality(vars,table.domain_values(),occ_vars,true);


        // Collection Attribute Constraints
        size = vars[0].mul(-1).add(vars.length).intVar();
        min = m.csp.intVar(table.lb(),table.ub());
        m.csp.min(min,vars).post();
        max = m.csp.intVar(table.lb(),table.ub());
        m.csp.max(max,vars).post();
    }

    //Source
    public IntVar[] srcVars (){return vars;}

    //Sequence & Collection
    public IntVar first(){return vars[0];} //ToDo AFD Constraints!!!!!
    public IntVar size(){return size;}
    public IntVar max(){return max;} //Todo
    public IntVar min(){return min;} //Todo
}
