package org.oclinchoco;
import java.util.HashMap;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.source.Source;

public class CSP{
    Model csp;
    IntVar nullptr;

    HashMap<String,Source> nodes;

    public CSP(){
        csp = new Model();
        nullptr = csp.intVar(0);
    }

    public Model model(){return csp;}
}
