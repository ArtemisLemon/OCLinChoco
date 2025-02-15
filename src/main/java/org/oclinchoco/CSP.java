package org.oclinchoco;
import java.util.HashMap;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class CSP{
    Model csp;
    static final public int MIN_BOUND = IntVar.MIN_INT_BOUND/1000 -1;
    static final public int MAX_BOUND = IntVar.MAX_INT_BOUND/1000; 
    IntVar nullptr;
    IntVar nullattrib;

    public CSP(){
        csp = new Model();
        nullptr = csp.intVar(0);
        nullattrib = csp.intVar(MIN_BOUND);
    }

    public Model model(){return csp;}
    public IntVar nullptr(){return nullptr;}
    public IntVar nullattrib(){return nullattrib;}

    //To Move?
    public void ZeroIFFZero(IntVar x, IntVar y){ //x=0 <-> y=0
        csp.ifOnlyIf(csp.arithm(x, "=", 0), csp.arithm(y, "=", 0));
    }

    public void ZeroIFZero(IntVar x, IntVar y){ //x=0 <- y=0
        csp.ifThen(csp.arithm(y, "=", 0), csp.arithm(x, "=", 0));
    }
}
