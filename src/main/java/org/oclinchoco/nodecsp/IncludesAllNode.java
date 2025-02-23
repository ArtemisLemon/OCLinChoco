package org.oclinchoco.nodecsp;

import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.nodecsp.astype.AsBagNode;
import org.oclinchoco.nodecsp.astype.PtrAsOccBagNode;
import org.oclinchoco.nodecsp.astype.PtrAsSetNode;
import org.oclinchoco.nodecsp.astype.VarsAsSetNode;
import org.oclinchoco.source.BoolSource;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;
import org.oclinchoco.source.VarsSource;

public class IncludesAllNode implements BoolSource {
    BoolVar var;

    public static IncludesAllNode create(CSP csp, Source left, Source right){
        if(left instanceof OccSource && right instanceof OccSource)
            return new IncludesAllNode(csp, (OccSource)left, (OccSource)right);
        if(left instanceof PtrSource && right instanceof PtrSource)
            return new IncludesAllNode(csp, new PtrAsOccBagNode(csp, (PtrSource)left), new PtrAsOccBagNode(csp, (PtrSource)right));
        if(left instanceof OccSource && right instanceof PtrSource)
            return new IncludesAllNode(csp, (OccSource)left, new PtrAsOccBagNode(csp, (PtrSource)right));
        if(left instanceof PtrSource && right instanceof OccSource)
            return new IncludesAllNode(csp, new PtrAsOccBagNode(csp, (PtrSource)left), (OccSource)right);

        if(left instanceof PtrSource && right instanceof PtrSource)
            return new IncludesAllNode(csp, (PtrSource)left, (PtrSource)right);
        if(left instanceof VarsSource && right instanceof VarsSource)
            return new IncludesAllNode(csp, (VarsSource)left, (VarsSource)right);


        throw new UnsupportedOperationException("can't apply inclusion between " + left + " and "+right);
    }

    public IncludesAllNode(CSP csp, OccSource left, OccSource right){
        IntVar[] lvars = left.occurences();
        IntVar[] rvars = right.occurences();
        
        for(int i=0;i<left.size();i++){
            csp.ZeroIFZero(rvars[i], lvars[i]);
        }

        var = csp.model().boolVar(true);
    }

    public IncludesAllNode(CSP csp, PtrSource left, PtrSource right){
        PtrAsSetNode sleft = new PtrAsSetNode(csp, left);
        PtrAsSetNode sright = new PtrAsSetNode(csp, right);
        for(int i=0;i<sright.size();i++){
            sright.pointers()[i].eq(sleft.pointers()[i]).post();
        }

        var = csp.model().boolVar(true);
    }

    public IncludesAllNode(CSP csp, VarsSource left, VarsSource right){
        VarsAsSetNode sleft = new VarsAsSetNode(csp, left);
        VarsAsSetNode sright = new VarsAsSetNode(csp, right);
        for(int i=0;i<sright.size();i++){
            sright.vars()[i].eq(sleft.vars()[i]).post();
        }

        var = csp.model().boolVar(true);
    }

    @Override
    public BoolVar var() { return var;}

}
