package org.oclinchoco.nodecsp.astype;

import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;
import org.oclinchoco.CSP;
import org.oclinchoco.source.PtrSource;

public class PtrAsSetNode extends AsSetNode implements PtrSource{
    IntVar[] vars;
    int size,ub;

    public PtrAsSetNode(CSP csp, PtrSource src){
        ub=src.ub();
        PtrAsBagNode bag = new PtrAsBagNode(csp, src);
        IntVar[] bagwithnull = ArrayUtils.concat(bag.pointers(), csp.nullptr());
        IntVar[] varswithnull = csp.model().intVarArray(bagwithnull.length, 0, src.ub());
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
    public IntVar[] pointers() {return vars;}

    @Override
    public int ub() {return ub;}
}
