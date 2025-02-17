package org.oclinchoco.property;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarSource;

public class SingleIntTable extends AttributeTable {
    public SingleIntTable(CSP m, int r){
        super(m, r, 0, 1);
    }

    public class SingleIntAttribute extends AttributeTableRow implements VarSource {
        private SingleIntAttribute(int objId){
            super(objId);
        }

        @Override //VarSource
        public IntVar var() {return matrix[objId-1][0];}
        
    }
}
