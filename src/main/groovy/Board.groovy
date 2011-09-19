class Board {
    def aliveCells = [] as Set

    def makeAlive(int x, int y) {
        aliveCells << new Cell(x:x, y:y)
    }

    def tick() {
        def bornCells = findNextGeneration()

        def nextGeneration = aliveCells.inject(bornCells) { cellColl, cell ->
            if(survives(cell)) {
                cellColl << cell
            }
            cellColl
        }
        new Board(aliveCells: nextGeneration)
    }

    def findNextGeneration() {
        def candidates = findNextGenerationCandidates() - aliveCells
        candidates.findAll { isBorn(it) }
    }

    def isBorn(cell) {
        countLiveNeighbors(cell) == 3
    }

    def findNextGenerationCandidates() {
        aliveCells.collect{ neighborsOf(it.x, it.y) }.flatten() as Set
    }

    boolean survives(cell) {
        countLiveNeighbors(cell) in 2..3
    }

    def countLiveNeighbors(cell) {
        neighborsOf(cell.x, cell.y)
            .count { isAlive(it.x, it.y) }
    }

    boolean isAlive(int x, int y) {
        aliveCells.contains(new Cell(x:x, y:y))
    }

    def neighborsOf(int x, int y) {
        def neighbors = [] as Set
        def modifiers = -1..1
        for(xMod in modifiers) {
            for(yMod in modifiers) {
                if(xMod == 0 && yMod == 0) continue
                neighbors << new Cell(x:x+xMod, y:y+yMod)
            }
        }
        neighbors
    }
}
