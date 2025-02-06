package org.oclinchoco.nodecsp;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarSource;

public class ArithmNode {
    // BoolVar reifed;
    
    public ArithmNode(CSP csp, VarSource left, VarSource right, String op){
        csp.model().arithm(left.var(), op, right.var()).post();
    }
}
