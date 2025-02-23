package org.oclinchoco.nodecsp.astype;

import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;
import org.oclinchoco.CSP;
import org.oclinchoco.source.PtrSource;

public class PtrAsBagNode extends AsBagNode implements PtrSource{
    IntVar[] vars;
    int size,ub;

    public PtrAsBagNode(CSP csp, PtrSource src){
        ub=src.ub();
        vars = csp.model().intVarArray(src.size(), 0, src.ub());
        IntVar[] srav = vars.clone();
        ArrayUtils.reverse(srav);
        csp.model().sort(src.pointers(), srav).post();
    }

    @Override
    public int size() {return vars.length;}

    @Override
    public IntVar[] pointers() {return vars;}

    @Override
    public int ub() {return ub;}

}
