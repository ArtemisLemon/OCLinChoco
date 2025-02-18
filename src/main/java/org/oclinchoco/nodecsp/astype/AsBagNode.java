package org.oclinchoco.nodecsp.astype;

import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;
import org.oclinchoco.source.VarsSource;

public abstract class AsBagNode implements Source {
    static public AsBagNode create(CSP csp, Source src){
        return switch(src){
            case PtrSource ptr -> new PtrAsBagNode(csp, ptr);
            case OccSource occ -> new PtrAsBagNode(csp, occ);
            case VarsSource vars -> new VarsAsBagNode(csp, vars);
            default -> throw new UnsupportedOperationException("No semantics for AsBag on " + src);
        };
    }
}
