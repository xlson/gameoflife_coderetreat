def b = new Board()

def startingCells = [      [1,0],
                                 [2,1],
                     [0,2],[1,2],[2,2]]

startingCells.each { b.makeAlive(*it) }

def before = System.currentTimeMillis()
def ticks = 50
ticks.times {
    printBoard(b)
    b = b.tick()
}
def durationMs = System.currentTimeMillis() - before
println "duration: ${durationMs} ms"
println "time per tick: ${durationMs / ticks} ms"


def printBoard(board) {
    for(y in -10..10) {
        for(x in -10..10) {
            if(board.isAlive(x,y)) {
                print "O"
            } else {
                print "_"
            }
        }
        println()
    }
    println()
}