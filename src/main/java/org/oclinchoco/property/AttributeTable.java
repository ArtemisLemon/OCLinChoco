package org.oclinchoco.property;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarsSource;

public class AttributeTable extends IntTable {

    public AttributeTable(CSP m, int r, int min, int max) {
        super(m, r, min, max);

        matrix = csp.intVarMatrix(rows, cols, CSP.MIN_BOUND, CSP.MAX_BOUND);

        nullrow = new IntVar[cols];
        for(int i=0;i<cols;i++) nullrow[i] = m.nullattrib();
    }

    public class AttributeTableRow extends IntTable.IntTableRow implements VarsSource{
        public AttributeTableRow(int objId){
            super(objId);
        }

        @Override
        public int size() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'maxCard'");
        }

        @Override
        public void loadData(int[] data){
            // System.out.println("Object ID: "+objId);
            for(int i=0;i<cols;i++){
                if(data[i]!=CSP.MIN_BOUND){
                    // System.out.println("AdjList LoadData");
                    try{vars()[i].updateBounds(data[i], data[i], null);}
                    catch(Exception e){System.out.println("Contradiction when loading data:\nVariable Domain: "+vars()[i]+"\nData:"+data[i] );}

                }
            }
        }
    }

}
