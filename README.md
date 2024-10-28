# OCL in Choco
AIMT : OCL 2 CSP 4 ATLc

**OCL nodes** modeled as **CSPs** using **Choco-Solver**

## Compile & Run
```bash
# build
./gradlew build

# run
./gradlew run
```

## Variables as Pointers
The foundation of these CSP models is encoding references with integer variables lists.


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