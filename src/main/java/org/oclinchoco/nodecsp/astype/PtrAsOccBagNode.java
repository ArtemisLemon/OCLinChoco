package org.oclinchoco.nodecsp.astype;

import java.util.stream.IntStream;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;

public class PtrAsOccBagNode extends AsBagNode implements OccSource{
    IntVar[] occs;
    int max;

    public PtrAsOccBagNode(CSP csp, PtrSource src){
        max = src.size();
        int[] values = IntStream.range(0,src.ub()+1).toArray();
        occs = csp.model().intVarArray(src.ub()+1, 0, src.size());

        csp.model().globalCardinality(src.pointers(), values, occs, true).post();
    }

    public PtrAsOccBagNode(CSP csp, OccSource src){
        max = src.maxCard();
        occs=src.occurences();
    }


    @Override
    public int size() { return occs.length; }

    @Override
    public IntVar[] occurences() { return occs; }

    @Override
    public int maxCard() { return max; }
}
