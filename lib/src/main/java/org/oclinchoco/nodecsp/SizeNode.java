package org.oclinchoco.nodecsp;
import org.oclinchoco.types.Collection;
import org.chocosolver.solver.variables.IntVar;

public class SizeNode {
    IntVar size;

    public SizeNode(Collection c){
        size=c.size();
    }
}
