package org.oclinchoco.types;
import org.chocosolver.solver.variables.IntVar;

public interface Collection {
    public IntVar size();
    public IntVar max();
    public IntVar min();
}

// public interface  CollectionOperation {
//     public IntVar size();
// }
// Because the other way, navCSP forexample needs to hold the variable that results from this operation
// which means all size calls would see the same variable
// but it also means we are building the information regardless of it's eventual use
// Probably there's another way, maybe singleton like, first call to size() builds the variable for the navCSP node, and subsequence calls reuses that same variable.