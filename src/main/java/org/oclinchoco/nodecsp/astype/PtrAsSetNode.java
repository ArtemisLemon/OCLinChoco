package org.oclinchoco.nodecsp.astype;
import java.util.Arrays;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;

public class PtrAsSetNode extends AsSetNode implements OccSource {
    IntVar[] occ;

    public PtrAsSetNode(CSP csp, OccSource src){
        occ = SetModel(csp, src.occurences().length);
        for(int i=0;i<occ.length;i++) csp.ZeroIFFZero(occ[i], src.occurences()[i]);
        nullptrcount(csp);
    }

    @Override
    public int size() {return occ.length-1;} //-1 because we don't need to count the null ptr collum //why?
    @Override
    public IntVar[] occurences() { return occ;}


    void nullptrcount(CSP csp){
        IntVar[] notnullcount = Arrays.copyOfRange(occ, 1, occ.length);
        csp.model().count(0, notnullcount, occ[0]).post();
    }

    // Probably should go somewhere else
    private static IntVar[] SetModel(CSP csp, int length){
        IntVar[] out = csp.model().intVarArray(length, 0, length-1);
        try{ //maybe it just this part that needs to go somewhere else
            for(int i=1;i<out.length;i++){
                out[i].updateUpperBound(1, null); //max occurences for everyting but nullptr is 1
            }
        } catch (Exception e){};


        // System.out.println("Made asSet variables");
        // for(IntVar v : out) System.out.println(v);
        return out;
    }
}
