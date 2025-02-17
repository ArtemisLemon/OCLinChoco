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

        try{
            // System.out.println("removing null pointer from variable domain");
            for(int i=0;i<rows;i++)for(int j=0;j<minCard;j++) matrix[i][j].updateLowerBound(CSP.MIN_BOUND+1, null); //remove null attribute from the vars < minCard
        } catch(Exception e){System.out.println("Contradiction when removing null attributes");}
    }


    @Override //NavTable
    public int lb() { return CSP.MIN_BOUND; }

    @Override //NavTable
    public int ub() { return CSP.MAX_BOUND; }

    public class AttributeTableRow extends IntTableRow implements VarsSource{
        public AttributeTableRow(int objId){
            super(objId);
        }

        @Override //VarsSource
        public int size() {return cols;}

        // @Override
        // public void loadData(int[] data){
        //     // System.out.println("Object ID: "+objId);
        //     for(int i=0;i<cols;i++){
        //         if(data[i]!=CSP.MIN_BOUND){
        //             // System.out.println("AdjList LoadData");
        //             try{vars()[i].updateBounds(data[i], data[i], null);}
        //             catch(Exception e){System.out.println("Contradiction when loading data:\nVariable Domain: "+vars()[i]+"\nData:"+data[i] );}

        //         }
        //     }
        // }
    }

    public AttributeTableRow getAttribute(int i){
        return new AttributeTableRow(i);
    }

}
