# electro
Java implementation of electromagnetism-like algorithm for Travelling Salesman Problem.

## How to run
It's a IntelliJ project. There are unit tests for each module; the central one is called `Solver`. 
In order to run the algorithm on real data, the `Solver` class needs to be instantiated,
and fed with the data in form of matrices and params.

The project uses [ojAlgo](https://github.com/optimatika/ojAlgo) (a dependency via Maven) to represent matrices.

## Theoretical basis
The model is based on paper called "Efficient Constraint Handling in Electromagnetism-Like Algorithm for Traveling Salesman Problem with Time Windows" by A. Yurtkuran & E. Emel.

In particular:
- particle position representation (Random Key method)
- formula for the objective function that's being minimised (Objective Function Value, OFV)
- generating particles' starting position
- the algorithm's main pass with simulated annealing


