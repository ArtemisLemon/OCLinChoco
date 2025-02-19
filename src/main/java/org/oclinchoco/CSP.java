package org.oclinchoco;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.nary.alldifferent.conditions.Condition;
import org.chocosolver.solver.constraints.nary.automata.FA.FiniteAutomaton;
import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.property.IntTable;
import org.oclinchoco.source.Source;

public class CSP{
    Model csp;
    static final public int MIN_BOUND = IntVar.MIN_INT_BOUND/1000 -1;
    static final public int MAX_BOUND = IntVar.MAX_INT_BOUND/1000; 
    IntVar nullptr;
    IntVar nullattrib;
    FiniteAutomaton nullsatend_afd;

    List<String> table_names;
    HashMap<String,IntTable> tables;

    List<String> node_expression;
    HashMap<String, Source> nodes;

    public CSP(){
        csp = new Model();
        nullptr = csp.intVar(0);
        nullattrib = csp.intVar(MIN_BOUND);
        nullsatend_afd = new FiniteAutomaton("[1-<"+Integer.toString(MAX_BOUND-MIN_BOUND)+">]*0*");

        table_names = new ArrayList<>();
        tables = new HashMap<>();
    }

    public void addTable(String prop, IntTable table){
        table_names.add(prop);
        tables.put(prop, table);
    }
    public void addNode(String exp, Source node){
        node_expression.add(exp);
        nodes.put(exp, node);
    }

    public Model model(){return csp;}
    public IntVar nullptr(){return nullptr;}
    public IntVar nullattrib(){return nullattrib;}
    public IntTable getTable(String prop){return tables.get(prop);}
    public Source getNode(String exp){return nodes.get(exp);}

    public void printTables(){
        for(String table : table_names){
            System.out.println(table);
            System.out.println(tables.get(table));
        }
    }

    public void printNodes(){
        for(String node : node_expression){
            System.out.println(node);
            System.out.println(tables.get(node));
        }
    }

    //To Move?
    public void ZeroIFFZero(IntVar x, IntVar y){ //x=0 <-> y=0
        csp.ifOnlyIf(csp.arithm(x, "=", 0), csp.arithm(y, "=", 0));
    }

    public void ZeroIFZero(IntVar x, IntVar y){ //x=0 <- y=0
        csp.ifThen(csp.arithm(y, "=", 0), csp.arithm(x, "=", 0));
    }

    public Constraint allDiffExceptNullPtr(IntVar[] vars){
        return csp.allDifferentExcept0(vars);
    }

    public Constraint allDiffExceptNullAttrib(IntVar[] vars){
        return csp.allDifferentUnderCondition(vars, new Condition() {
            @Override
            public boolean holdOnVar(IntVar x) {
                return !x.contains(MIN_BOUND);
            }
        }, true);
    }

    public Constraint ptrNullsAtEnd(IntVar[] vars){
        return csp.regular(vars, nullsatend_afd);
    }

    public Constraint varNullsAtEnd(IntVar[] vars){
        int offset = -MIN_BOUND;
        IntVar[] offset_vars = csp.intVarArray(vars.length, 0, MAX_BOUND-MIN_BOUND);
        for(int i=0; i<vars.length; i++) offset_vars[i].eq(vars[i].add(offset)).post();

        return csp.regular(offset_vars, nullsatend_afd);
    }

    public void alignPtrMatrix(IntVar[][] matrix){
        if(matrix[0].length==1) return;
        for(int i=0;i<matrix.length;i++)
            ptrNullsAtEnd(matrix[i]).post();
    }
    public void alignVarMatrix(IntVar[][] matrix){
        if(matrix[0].length==1) return;
        for(int i=0;i<matrix.length;i++)
            varNullsAtEnd(matrix[i]).post();
    }
}
