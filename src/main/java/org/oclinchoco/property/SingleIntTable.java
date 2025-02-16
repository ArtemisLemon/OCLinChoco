package org.oclinchoco.property;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarSource;

public class SingleIntTable extends IntTable {

    IntVar[] vars;
    IntVar nullattrib;
    Model csp;

    public SingleIntTable(CSP m, int r){
        super(m, r, 0, 1);
        csp = m.model();
        nullattrib = m.nullattrib();

        vars = csp.intVarArray(r, CSP.MIN_BOUND, CSP.MAX_BOUND);
    }

    @Override
    public IntVar[] navTable() {
        return ArrayUtils.concat(ArrayUtils.toArray(nullattrib),vars);
    }

    @Override
    public int cols() { return 1; }

    @Override
    public int lb() { return CSP.MIN_BOUND; }

    @Override
    public int ub() { return CSP.MAX_BOUND; }


    public class SingleIntAttribute extends IntTableRow implements VarSource {
        private SingleIntAttribute(int objId){
            super(objId);
            System.out.println("made single int attribute fir obj "+objId);
        }

        @Override //Source
        public int size() {return 1;}

        @Override //VarSource
        public IntVar var() {return vars[objId-1];}

        @Override
        public void loadData(int[] data){
            System.out.println("Loading single in of obj"+objId);
            try{var().updateBounds(data[0], data[0], null);}
            catch(Exception e){System.out.println("Contradiction when loading data:\nVariable Domain: "+var()+"\nData:"+data[0] );}
        }

        // public int[] getData(){
        //     int[] out = new int[1];
        //     out[0] = var().getValue();
        //     return out;
        // }
        
    }

    public SingleIntAttribute singleattribute(int objId){
        return new SingleIntAttribute(objId);
    }

    @Override
    public String toString() {
        String out = "";
        for(IntVar v : vars){
            out+=v+", ";
        }
        return out;
    }
}
