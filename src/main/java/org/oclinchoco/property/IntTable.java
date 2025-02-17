package org.oclinchoco.property;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;
import org.oclinchoco.CSP;
import org.oclinchoco.navigation.NavTable;

// abstract so you can't make it without variables and constraints
public abstract class IntTable implements NavTable {
    final int rows,cols; //number of squares and possible numbers in them
    final int minCard, maxCard; boolean has_nulls; //Reference Cardinality

    Model csp; //Choco Model
    IntVar[][] matrix;
    IntVar[] nullrow;

    public IntTable(CSP m, int r, int min, int max){
        csp=m.model();
        rows=r; cols=max;
        minCard=min; maxCard=max; has_nulls = !(min==max);

        // matrix = csp.intVarMatrix(rows, cols, CSP.MIN_BOUND, CSP.MAX_BOUND);

        // nullrow = new IntVar[cols];
        // for(int i=0;i<cols;i++) nullrow[i] = m.nullattrib();
    }


    @Override
    public IntVar[] navTable() {
        return ArrayUtils.concat(nullrow, ArrayUtils.flatten(matrix));
    }

    @Override
    public int cols() {return cols;}

    public class IntTableRow {
        int objId;
        protected IntTableRow(int objId){
            this.objId=objId;
        }

        public IntVar[] vars() {return matrix[objId-1];}

        public void loadData(int[] data){
            // System.out.println("IntTableRow LoadData");
            // System.out.println("Object ID: "+objId);
            for(int i=0;i<cols;i++){
                if(data[i]!=nullrow[0].getValue()-1){
                    // System.out.println(data[i]+", ");
                    try{vars()[i].updateBounds(data[i], data[i], null);}
                    catch(Exception e){System.out.println("Contradiction when loading data:\nVariable Domain: "+vars()[i]+"\nData:"+data[i] );}

                }
            }
        }

        public int[] getData(){
            int[] out = new int[cols];
            for(int i=0;i<cols;i++) out[i] = vars()[i].getValue();
            return out;
        }        
    }

    public IntTableRow intproperty(int objId){
        return new IntTableRow(objId);
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
