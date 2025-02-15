package org.oclinchoco.nodecsp;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;
import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;

public class AsSetNode implements OccSource {
    IntVar[] occ;

    public AsSetNode(CSP csp, OccSource src){
        //Variables
        occ = SetModel(csp, src.occurences().length);
        //Constraints
        for(int i=0;i<occ.length;i++) csp.ZeroIFFZero(occ[i], src.occurences()[i]);

        // nullptrcount(csp);
    }

    public AsSetNode(CSP csp, PtrSource src){
        //Intermediate Variables and Constraints to complete Src Model
        int[] values = IntStream.range(0,src.ub()+1).toArray();
        IntVar[] src_occ = csp.model().intVarArray(values.length, 0,src.size());
        // System.out.println("Made Occurence representation for a PtrSource");
        // for(IntVar v : src.pointers()) System.out.println(v);
        // for(IntVar v : src_occ) System.out.println(v);
        csp.model().globalCardinality(src.pointers(), values, src_occ, true).post();

        //Variables
        occ = SetModel(csp, values.length);
        //Constraints
        for(int i=1;i<occ.length;i++) csp.ZeroIFFZero(occ[i], src_occ[i]);
        
        nullptrcount(csp);
    }

    void nullptrcount(CSP csp){
        IntVar[] notnullcount = Arrays.copyOfRange(occ, 1, occ.length);
        csp.model().count(0, notnullcount, occ[0]).post();
    }

    @Override
    public int size() {return occ.length-1;} //-1 because we don't need to count the null ptr collum
    @Override
    public IntVar[] occurences() { return occ;}




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
