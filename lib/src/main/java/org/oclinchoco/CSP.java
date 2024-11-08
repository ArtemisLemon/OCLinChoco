package org.oclinchoco;
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
}
