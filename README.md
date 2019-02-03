# Conway's Game of Life in Java with basic Swing GUI

TDD of the logic of Conway's Game of Life with a quick gui for fun.

#### Note: 
 - The grid is wrapping.
 - The colors are arbitrary.
![](https://raw.githubusercontent.com/beplaya/conwaysjavalife/master/conwaysgol.png)

The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead, (or populated and unpopulated, respectively). Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:

 - Any live cell with fewer than two live neighbors dies, as if by underpopulation.
 - Any live cell with two or three live neighbors lives on to the next generation.
 - Any live cell with more than three live neighbors dies, as if by overpopulation.
 - Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 
The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed; births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick. Each generation is a pure function of the preceding one. The rules continue to be applied repeatedly to create further generations.

###### source: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
