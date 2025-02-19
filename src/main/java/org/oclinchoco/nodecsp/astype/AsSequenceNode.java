package org.oclinchoco.nodecsp.astype;

import org.oclinchoco.CSP;
import org.oclinchoco.source.OccSource;
import org.oclinchoco.source.PtrSource;
import org.oclinchoco.source.Source;

public abstract class AsSequenceNode implements Source {
    static public AsSequenceNode create(CSP csp, Source src){
        return switch(src){
            case OccSource occ -> new PtrAsSequenceNode(csp, occ);
            case PtrSource ptr -> new PtrAsSequenceNode(csp, ptr);
            default -> throw new UnsupportedOperationException("No semantics for asSequence on " + src);
        };
    }
}
