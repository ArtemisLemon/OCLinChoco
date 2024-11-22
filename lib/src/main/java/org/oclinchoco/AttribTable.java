package org.oclinchoco;
import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;

public class AttribTable implements PropertyTable, NavTable {
    int rows,cols;
    int ub; int lb; int ob;

    Model csp;
    IntVar[][] matrix;
    IntVar[] nullptrs;
    IntVar obvar;
    
    public AttribTable(CSP m, int rows, int cols){
        csp = m.csp;
        this.rows=rows;this.cols=cols;
        this.ub=IntVar.MAX_INT_BOUND/2; this.lb=IntVar.MIN_INT_BOUND/2; this.ob=this.lb-1;

        this.matrix = csp.intVarMatrix(rows, cols, ob,ub);
        this.obvar = csp.intVar(ob);
        
        this.nullptrs = new IntVar[cols];
    }

    public IntVar[] navTable(){
        for(int i=0;i<cols;i++) nullptrs[i] = obvar;
        return ArrayUtils.concat(nullptrs, ArrayUtils.flatten(matrix));
    }

    public void loadData(int[][] data){
        for(int i=0;i<rows;i++) for(int j=0;j<cols;j++){
            csp.arithm(matrix[i][j], "=", data[i][j]).post();
        }
    }
}