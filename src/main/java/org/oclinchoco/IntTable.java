package org.oclinchoco;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;
import org.oclinchoco.property.NavTable;
import org.oclinchoco.source.VarsSource;

public class IntTable implements NavTable {
    final int rows,cols; //number of squares and possible numbers in them
    final int minCard, maxCard; boolean has_nulls; //Reference Cardinality

    Model csp; //Choco Model
    IntVar[][] matrix;
    IntVar[] nullattribs;

    public IntTable(CSP m, int r, int min, int max){
        csp=m.model();
        rows=r; cols=max;
        minCard=min; maxCard=max; has_nulls = !(min==max);

        matrix = csp.intVarMatrix(rows, cols, CSP.MIN_BOUND, CSP.MAX_BOUND);

        nullattribs = new IntVar[cols];
        for(int i=0;i<cols;i++) nullattribs[i] = m.nullattrib;
    }


    @Override
    public IntVar[] navTable() {
        return ArrayUtils.concat(nullattribs, ArrayUtils.flatten(matrix));
    }

    @Override
    public int cols() {return cols;}

    @Override
    public int lb() { return CSP.MIN_BOUND; }

    @Override
    public int ub() { return CSP.MAX_BOUND; }


    public class IntAttribute implements VarsSource {
        int objId;
        IntTable table;
        private IntAttribute(int objId, IntTable table){
            this.objId=objId;
            this.table=table;
        }

        @Override //Source
        public int maxCard() {return table.maxCard;}

        @Override //VarsSource
        public IntVar[] vars() {return table.matrix[objId-1];}

        public void loadData(int[] data){
            // System.out.println("Object ID: "+objId);
            for(int i=0;i<table.cols;i++){
                if(data[i]!=-1){
                    // System.out.println("AdjList LoadData");
                    try{vars()[i].updateBounds(data[i], data[i], null);}
                    catch(Exception e){System.out.println("Contradiction when loading data:\nVariable Domain: "+vars()[i]+"\nData:"+data[i] );}

                }
            }
        }

        public int[] getData(){
            int[] out = new int[table.cols];
            for(int i=0;i<cols;i++) out[i] = vars()[i].getValue();
            return out;
        }
        
    }

    @Override
    public String toString() {
        String out = "";
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                out += matrix[i][j] + " ";
            }
            out += "\n";
        }
        return out;
    }
}
