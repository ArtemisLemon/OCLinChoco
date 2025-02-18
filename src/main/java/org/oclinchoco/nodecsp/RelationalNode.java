package org.oclinchoco.nodecsp;
import org.chocosolver.solver.variables.BoolVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.BoolSource;
import org.oclinchoco.source.VarSource;

public class RelationalNode implements BoolSource {
    BoolVar bool;
    
    public RelationalNode(CSP csp, VarSource left, VarSource right, String op){
        bool = csp.model().arithm(left.var(), op, right.var()).reify();
    }

    @Override
    public BoolVar bool() {
        return bool;
    }
}
