/*
 * This source file was generated by the Gradle 'init' task
 */
package org.oclinchoco;
import org.oclinchoco.types.Source;
import org.oclinchoco.types.NavTable;
import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;

import java.util.stream.IntStream;

public class ReferenceTable implements NavTable {
    int rows,cols,domain;
    int minCard, maxCard; boolean has_nulls;

    Model csp;
    IntVar[][] ptr_matrix;
    IntVar[][] occ_matrix;
    IntVar[] nullptrs;

    public ReferenceTable(CSP m, int n, int nn, int c, int d){
        csp = m.csp;
        rows=n; cols=nn; domain=d;
        minCard = c; maxCard=cols;
        has_nulls = !(minCard==maxCard);

        // Variables
        if (has_nulls) ptr_matrix = csp.intVarMatrix(rows, cols, 0,domain);
            else ptr_matrix = csp.intVarMatrix(rows, cols, 1,domain);
        occ_matrix = csp.intVarMatrix(rows, domain+1, 0, cols);
        
        nullptrs = new IntVar[nn];
        for(int i=0;i<nn;i++) nullptrs[i] = m.nullptr;

        // Constraints
        int[] values = IntStream.range(0, d+1).toArray();
        for(int i=0;i<n;i++) 
            csp.globalCardinality(ptr_matrix[i], values, occ_matrix[i], true).post();
    }

    //navTable Methods
    public IntVar[] navTable(){
        return ArrayUtils.concat(nullptrs, ArrayUtils.flatten(ptr_matrix));
    }
    public int cols(){return cols;}
    public int lb(){
        if(!has_nulls) return 1;
        return 0;
    }
    public int ub(){
        return domain;
    }

    class AdjList implements Source {
        IntVar[] vars;
        private AdjList(IntVar[] vars){
            this.vars=vars;
        }
        public IntVar[] srcVars(){return vars;}
    }
    public AdjList adjList(int objId){
        return new AdjList(ptr_matrix[objId-1]);
    }
}