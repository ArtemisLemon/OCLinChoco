package org.oclinchoco.nodecsp.astype;

import java.util.stream.IntStream;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;

public class PtrAsSequenceNode extends AsSequenceNode implements PtrSource{
    IntVar[] vars;
    int ub;

    public PtrAsSequenceNode(CSP csp, OccSource occs){
        int[] values = IntStream.range(0,occs.size()+1).toArray();
        vars = csp.model().intVarArray(occs.maxCard(), 0, occs.size());
        csp.model().globalCardinality(vars, values, occs.occurences(), true);       
    }

    public PtrAsSequenceNode(CSP csp, PtrSource ptr){
        vars=ptr.pointers();
        ub=ptr.ub();

        // Apply AFD
    }

    @Override
    public int size() { return vars.length; }

    @Override
    public IntVar[] pointers() {return vars;}

    @Override
    public int ub() { return ub;}

}
