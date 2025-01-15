package org.oclinchoco.nodecsp;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.Source;
import org.oclinchoco.source.VarSource;

public class SizeNode implements VarSource{
    IntVar size;

    public SizeNode(CSP csp, Source s){

    }

    @Override
    public IntVar var() {return size;}
}
