package org.oclinchoco.nodecsp;

import org.chocosolver.solver.variables.IntVar;
import org.oclinchoco.CSP;
import org.oclinchoco.source.VarSource;

public class ArithmeticNode implements VarSource{
    IntVar var;

    public ArithmeticNode(CSP csp, VarSource left, VarSource right, String op){
        switch (op) {
            case "+":
                var = left.var().add(right.var()).intVar();
                break;
            case "-":
                var = left.var().sub(right.var()).intVar();
                break;
            case "*":
                var = left.var().mul(right.var()).intVar();
                break;
            case "/":
                var = left.var().div(right.var()).intVar();
                break;
            case "min":
                var = left.var().min(right.var()).intVar();
                break;
            case "max":
                var = left.var().max(right.var()).intVar();
                break;
            case "mod":
                var = left.var().mod(right.var()).intVar();
                break;
            case "pow":
                var = left.var().pow(right.var()).intVar();
                break;
            case "dist":
                var = left.var().dist(right.var()).intVar();
                break;
        
            default:
                throw new UnsupportedOperationException("Can't model "+op);
        }
    }

    public ArithmeticNode(CSP csp, VarSource source, String op){
        switch (op) {
            case "abs":
                var = source.var().abs().intVar();
                break;
            // case "sqr":
            //     var = source.var().sqr().intVar();
            //     break;
            case "neg":
                var = source.var().neg().intVar();
                break;
            default:
                throw new UnsupportedOperationException("Can't model "+op);
        }
    }

    @Override
    public IntVar var() {return var;}

}
