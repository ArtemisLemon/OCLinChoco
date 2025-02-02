package org.oclinchoco;
import java.util.HashMap;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class CSP{
    Model csp;
    IntVar nullptr;

    public CSP(){
        csp = new Model();
        nullptr = csp.intVar(0);
    }

    public Model model(){return csp;}
    public IntVar nullptr(){return nullptr;}

    //To Move?
    public void ZeroIFFZero(IntVar x, IntVar y){ //x=0 <-> y=0
        csp.ifOnlyIf(csp.arithm(x, "=", 0), csp.arithm(y, "=", 0));
    }

    public void ZeroIFZero(IntVar x, IntVar y){ //x=0 <- y=0
        csp.ifThen(csp.arithm(y, "=", 0), csp.arithm(x, "=", 0));
    }
}
