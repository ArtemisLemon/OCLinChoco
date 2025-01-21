package org.oclinchoco.nodecsp;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.VarSource;

public class SizeNode implements VarSource{
    IntVar size;
    IntVar ezis;

    public SizeNode(CSP csp, PtrSource s){
        ezis = csp.model().count("",csp.nullptr().getValue(), s.pointers());
        size = ezis.mul(-1).add(s.maxCard()).intVar();
    }

    public SizeNode(CSP csp, OccSource s){
        ezis = s.occurences()[csp.nullptr().getValue()];
        size = csp.model().intVar(s.maxCard()).sub(ezis).intVar();
    }

    @Override
    public IntVar var() {return size;}
}
