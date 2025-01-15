package org.oclinchoco.nodecsp;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;

public class AsSetNode {
    IntVar[] vars;
    IntVar[] occs;
    IntVar[] occSet;
    
    public AsSetNode(CSP m, PtrSource src){
        int n = src.pointers().length; 
        int nn;
        // //ToDo
        // // init Vars, needs some more info about src
        // vars = m.intVarArray(n,0,100);
        // // occs = m.intVarArray(nn,0,100);
        // // occSet = m.intVarArray(nn,0,100);

        // //apply AllDiff execpt nullptr/outbound to vars
        // m.allDifferentExcept0(vars); //asSet()
        // //srcVars include vars //vars include srcVars
        // for(int i=0;i<nn;i++) m.ifOnlyIf(m.arithm(occs[i],"!=",0),m.arithm(occSet[i],"!=",0));

        // // Connect Vars with OCCs GCC
        // // m.globalCardinality(src.srcVars(),src.values(),occs);
        // // m.globalCardinality(vars,src.values(),occSet);
    }
}
