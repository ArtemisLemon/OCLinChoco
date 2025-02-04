# OCL in Choco
AIMT : OCL 2 CSP 4 ATLc

**OCL nodes** modeled as **CSPs** using **Choco-Solver**
And models for **UML Object Properties** to solve **OCL Constraints** upon.

## How get Lib
```bash
# download latest version
git submodule add git@github.com:ArtemisLemon/OCLinChoco.git OCLinChoco
# update
git submodule update --remote
```
```groovy
//add to settings.gradle
include('OCLinChoco:lib')

//add to build.gradle
dependencies {
    implementation project(':OCLinChoco:lib')
}
```
examples:
- [example project using lib](https://github.com/ArtemisLemon/OCLinChoco_Tester)
- [ZooSolver](https://github.com/ArtemisLemon/ZooSolver) building CSPs from .xcore and .atl

## Variables as Pointers
The foundation of these CSP models is encoding references with integer variables lists.
- See [ReferenceTable](https://github.com/ArtemisLemon/OCLinChoco/blob/master/lib/src/main/java/org/oclinchoco/ReferenceTable.java) for model of UML (.xcore)
- See [NavCSP](https://github.com/ArtemisLemon/OCLinChoco/blob/master/lib/src/main/java/org/oclinchoco/NavCSP.java) for navigation model, key to modeling ocl queries and structural constraints upon them.
<!-- ## OCL support

- [ ] Collections
  - [ ] c->size()
  - [ ] c->includes(o), c->excludes(o),
  - [ ] c->count(o)
  - [ ] c->includesAll(c2), c->excludesAll(c2)
  - [ ] c->isEmpty(), c->notEmpty()
  - [ ] c->max(), c->min(), c->sum()
  - [ ] c->select(it|pd), c->reject(it|pd)
  - [ ] Sequence
    - [ ] c->asSequence()
    - [ ] sq->
    - [ ] sq->first(), sq->last()
    - [ ] sq->at(i), sq->indexOf(o)
    - [ ] sq->including(e),sq->excluding(e)
    - [ ] sq->subSequence(i1,i2), sq->reverse()
  - [ ] OrderedSet -->
