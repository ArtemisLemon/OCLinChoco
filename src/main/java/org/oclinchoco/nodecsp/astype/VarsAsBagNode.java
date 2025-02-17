package org.oclinchoco.nodecsp.astype;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarsSource;

public class VarsAsBagNode extends AsBagNode implements VarsSource{
    IntVar[] vars;

    public VarsAsBagNode(CSP csp, VarsSource src){
        vars = csp.model().intVarArray(src.size(), CSP.MIN_BOUND, CSP.MAX_BOUND);
        csp.model().sort(src.vars(), vars);
    }

    @Override
    public IntVar[] vars() { return vars; }

    @Override
    public int size() { return vars.length; }

}
