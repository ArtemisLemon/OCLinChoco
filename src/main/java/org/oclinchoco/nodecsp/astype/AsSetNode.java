package org.oclinchoco.nodecsp.astype;

import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;
import org.oclinchoco.source.VarsSource;

public class AsSetNode {
    static public AsSetNode asSet(CSP csp, Source src){
        return switch(src){
            case PtrSource ptr -> new PtrAsSetNode(csp, new PtrAsBagNode(csp, ptr));
            case OccSource occ -> new PtrAsSetNode(csp, occ);
            case VarsSource vars -> new VarsAsSetNode(csp, vars);
            default -> throw new UnsupportedOperationException("No semantics for AsSet on " + src);
        };
    }
}
