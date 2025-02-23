package org.oclinchoco.nodecsp.astype;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarsSource;

public class VarsAsSequenceNode extends AsSequenceNode implements VarsSource{
    IntVar[]vars;

    public VarsAsSequenceNode(CSP csp, VarsSource src){
        vars = csp.model().intVarArray(src.size(), CSP.MIN_BOUND, CSP.MAX_BOUND);
        csp.elementAlignedCopy(src.vars(), vars, CSP.MIN_BOUND);
    }

    @Override
    public int size() {return vars.length;}

    @Override
    public IntVar[] vars() {return vars;}

}
