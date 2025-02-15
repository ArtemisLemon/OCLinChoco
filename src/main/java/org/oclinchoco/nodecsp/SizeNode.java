package org.oclinchoco.nodecsp;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;
import org.oclinchoco.source.VarSource;
import org.oclinchoco.source.VarsSource;

public class SizeNode implements VarSource{
    IntVar size;
    IntVar ezis;

    public static SizeNode create(CSP c, Source s){
        return switch(s){
            case PtrSource ptr -> new SizeNode(c, ptr);
            case OccSource occ -> new SizeNode(c, occ);
            case VarsSource vars -> new SizeNode(c, vars);
            default -> throw new UnsupportedOperationException("can't get size of " + s);
        };
    }

    private SizeNode(CSP csp, PtrSource s){
        ezis = csp.model().count("",csp.nullptr().getValue(), s.pointers());
        size = ezis.mul(-1).add(s.size()).intVar();
    }

    private SizeNode(CSP csp, VarsSource s){
        ezis = csp.model().count("",csp.nullattrib().getValue(), s.vars());
        size = ezis.mul(-1).add(s.size()).intVar();
    }

    private SizeNode(CSP csp, OccSource s){
        ezis = s.occurences()[csp.nullptr().getValue()];
        size = csp.model().intVar(s.size()).sub(ezis).intVar();
    }

    @Override
    public IntVar var() {return size;}
}
