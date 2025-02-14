package org.oclinchoco.nodecsp;
import org.chocosolver.solver.variables.BoolVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.BoolSource;
import org.oclinchoco.source.VarSource;

public class RelationalNode implements BoolSource {
    BoolVar reifed;
    
    public RelationalNode(CSP csp, VarSource left, VarSource right, String op){
        csp.model().arithm(left.var(), op, right.var()).post();
        
        //TODO don't always post
        reifed = csp.model().boolVar(true);
    }

    @Override
    public BoolVar var() {
        return reifed;
    }
}
