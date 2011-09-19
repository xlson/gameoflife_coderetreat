import spock.lang.Specification

class GameSpec extends Specification {

    def "Rules of game of life"() {
        setup:
        def b = new Board()
        for(cell in liveCells) {
            b.makeAlive(*cell)
        }

        when:
        def nextGen = b.tick()

        then:
        nextGen.isAlive(*cellToCheck) == isAlive


        where:
        liveCells                           | cellToCheck       | isAlive
        // Any live cell with fewer than two live neighbours dies, as if caused by under-population
        [[1,1]]                             | [1,1]             | false
        [[1,1], [1,2]]                      | [1,1]             | false

        // Any live cell with two or three live neighbours lives on to the next generation
        [[1,0], [1,1], [1,2]]               | [1,1]             | true
        [[1,0], [1,1], [1,2], [0,1]]        | [1,1]             | true

        // Any live cell with more than three live neighbours dies, as if by overcrowding
        [[1,0], [1,1], [1,2], [0,1], [2,1]] | [1,1]             | false

        // Any dead cell with exactly three live neighbours
        // becomes a live cell, as if by reproduction
        [[1,0], [1,2], [0,1]]               | [1,1]             | true
    }

}
