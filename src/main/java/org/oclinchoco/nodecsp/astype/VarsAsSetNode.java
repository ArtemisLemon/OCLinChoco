package org.oclinchoco.nodecsp.astype;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarsSource;

public class VarsAsSetNode extends AsSetNode implements VarsSource{
    IntVar[] vars;

    public VarsAsSetNode(CSP csp, VarsSource vars){
        VarsAsBagNode bag = new VarsAsBagNode(csp, vars);
        csp.allDiffExceptNullAttrib(bag.vars()).post();
    }

    @Override
    public int size() {return vars.length;}

    @Override
    public IntVar[] vars() {return vars;}

}
