package org.oclinchoco.nodecsp;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;

public class AsSetNode implements OccSource {
    IntVar[] occ;
    public AsSetNode(CSP csp, OccSource src){
        //Variables
        occ = csp.model().intVarArray(src.occurences().length, 0, src.maxCard());
        try{
            for(int i=1;i<occ.length;i++){
                occ[i].updateUpperBound(1, null); //max occurences for everyting but nullptr is 1
            }
        } catch (Exception e){};

        //Constraints
        for(int i=1;i<occ.length;i++) csp.ZeroIFFZero(occ[i], src.occurences()[i]);

    }

    @Override
    public int maxCard() {return occ.length-1;} //-1 because we don't need to count the null ptr collum
    @Override
    public IntVar[] occurences() { return occ;}
}
