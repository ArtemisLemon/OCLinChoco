# OCL in Choco
AIMT : OCL 2 CSP 4 ATLc

**OCL AST nodes** modeled as **CSPs** using **Choco-Solver**, and models for **UML Object Properties** to solve **OCL Constraints** upon.

Used as the interpretation sematics in [OOCP](https://github.com/ArtemisLemon/OOCP)


## How get Lib
```bash
# download latest version
git submodule add git@github.com:ArtemisLemon/OCLinChoco.git OCLinChoco
# update
git submodule update --remote
```
```groovy
//add to settings.gradle
include('OCLinChoco')

//add to build.gradle
dependencies {
    implementation project(':OCLinChoco')
}
```

## Variables as Pointers
The foundation of these CSP models is encoding references with integer variables lists.
- See [RefTable](https://github.com/ArtemisLemon/OCLinChoco/blob/master/src/main/java/org/oclinchoco/property/ReferenceTable.java) for model of references
- See [NavCSP](https://github.com/ArtemisLemon/OCLinChoco/blob/master/src/main/java/org/oclinchoco/navigation/NavCSP.java) for navigation model, key to modeling ocl queries and structural constraints upon them.

<img src="readme/overview.svg" alt="see diagrams" width=100%/>

## UML and OCP support
- UML
    - References with finite cardinality
    - integer attributes
- OCL
    - Navigation
    - Integer Arithmetic (+,-,*,etc..)
    - Integer Relationals (=,<>,<,etc..)
    - Collection Operations (size, asSet, see [nodecsp package](https://github.com/ArtemisLemon/OCLinChoco/tree/master/src/main/java/org/oclinchoco/nodecsp) for full list
