package org.oclinchoco.nodecsp;
import java.util.stream.IntStream;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;

public class AsSetNode implements OccSource {
    IntVar[] occ;

    public AsSetNode(CSP csp, OccSource src){
        //Variables
        occ = SetModel(csp, src.occurences().length, src.maxCard());
        //Constraints
        for(int i=1;i<occ.length;i++) csp.ZeroIFFZero(occ[i], src.occurences()[i]);
    }

    public AsSetNode(CSP csp, PtrSource src){
        //Intermediate Variables and Constraints to complete Src Model
        int[] values = IntStream.range(0,src.pointers()[0].getUB()+1).toArray(); //Using src.pointers()[0].getUB because normally at this point (while building the model), we haven't removed any values from the domain
        IntVar[] src_occ = csp.model().intVarArray(values.length, 0,src.maxCard());
        csp.model().globalCardinality(src.pointers(), values, src_occ, true);

        //Variables
        occ = SetModel(csp, values.length, src.pointers().length);
        //Constraints
        for(int i=1;i<occ.length;i++) csp.ZeroIFFZero(occ[i], src_occ[i]);

    }

    @Override
    public int maxCard() {return occ.length-1;} //-1 because we don't need to count the null ptr collum
    @Override
    public IntVar[] occurences() { return occ;}




    // Probably should go somewhere else
    private static IntVar[] SetModel(CSP csp, int length, int nullptr_maxcount){
        IntVar[] out = csp.model().intVarArray(length, 0, nullptr_maxcount);
        try{ //maybe it just this part that needs to go somewhere else
            for(int i=1;i<out.length;i++){
                out[i].updateUpperBound(1, null); //max occurences for everyting but nullptr is 1
            }
        } catch (Exception e){};

        return out;
    }
}
