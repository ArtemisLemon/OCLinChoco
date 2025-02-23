package org.oclinchoco.nodecsp.astype;

import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarsSource;

public class VarsAsSetNode extends AsSetNode implements VarsSource{
    IntVar[] vars;

    public VarsAsSetNode(CSP csp, VarsSource src){
        VarsAsBagNode bag = new VarsAsBagNode(csp, src);
        IntVar[] bagwithnull = ArrayUtils.concat(bag.vars(), csp.nullattrib());
        IntVar[] varswithnull = csp.model().intVarArray(bagwithnull.length, CSP.MIN_BOUND,CSP.MAX_BOUND);
        IntVar[] setpos = new IntVar[bagwithnull.length];
        setpos[0]= csp.model().intVar(0);
        bagwithnull[0].eq(varswithnull[0]).post();
        for(int i=1;i<bagwithnull.length;i++){
            setpos[i]=setpos[i-1].add(bagwithnull[i].ne(bagwithnull[i-1]).intVar()).intVar();
            csp.model().element(bagwithnull[i], varswithnull, setpos[i],0).post();;
            varswithnull[i].le(varswithnull[i-1]).post();
        }
        vars = new IntVar[bag.size()];
        for(int i=0;i<bag.size();i++){
            vars[i] = varswithnull[i];
        }
    }

    @Override
    public int size() {return vars.length;}

    @Override
    public IntVar[] vars() {return vars;}

}
