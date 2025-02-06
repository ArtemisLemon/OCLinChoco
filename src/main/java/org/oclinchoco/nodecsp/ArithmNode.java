package org.oclinchoco.nodecsp;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarSource;

public class ArithmNode implements VarSource {
    BoolVar reifed;
    
    public ArithmNode(CSP csp, VarSource left, VarSource right, String op){
        csp.model().arithm(left.var(), op, right.var()).post();
        
        //TODO don't always post
        reifed = csp.model().boolVar(true);
    }

    @Override
    public IntVar var() {
        return reifed.asIntVar();
    }
}
