fun existsHamiltonianFromNode(graph: Array<Array<Boolean>>, start: Int): Boolean {
    val n = graph.size
    val nodes = (0..<n).toMutableSet()
    val pathMap = mutableMapOf<Pair<Set<Int>, Int>, Boolean>()
    val nodesWithoutStart = nodes - start
    
    nodesWithoutStart.forEach {
        pathMap[emptySet<Int>() to it] = graph[start][it]
    }
    
    (1..<n).forEach { size ->
        getSubsetsOfSizeN(size, nodesWithoutStart).forEach { S ->
            (nodesWithoutStart - S).forEach { x ->
                pathMap[S to x] = S.any { y ->
                    (pathMap[(S - y) to y]!! && graph[y][x])
                }
            }
        }
    }
	
    return nodesWithoutStart.any {
        pathMap[(nodesWithoutStart - it) to it]!! && graph[it][start]
    }
}

fun existsHamiltonian(graph: Array<Array<Boolean>>): Boolean =
    (0..<graph.size).any { existsHamiltonianFromNode(graph, it) }

fun getSubsetsOfSizeN(n: Int, nodes: Set<Int>): List<Set<Int>> {
    if (n == 0) { return listOf(emptySet()) }
    
    val subsets = mutableListOf<Set<Int>>()
    nodes.forEach { node ->
        getSubsetsOfSizeN(n - 1, nodes - node).forEach { subset ->
            subsets.add(subset + node)
        }
    }
    
    return subsets
}

fun main() {
    val graphWithHCycle = arrayOf(
        arrayOf(false, true,  true,  false),
        arrayOf(true,  false, false, true), 
        arrayOf(true,  false, false, true), 
        arrayOf(true,  true, true,  false)
    )
    
    val graphWithoutHCycle = arrayOf(
        arrayOf(false, true,  true,  false),
        arrayOf(true,  false, false, false), 
        arrayOf(true,  false, false, true), 
        arrayOf(true,  false, true,  false)
    )
   	
    println(existsHamiltonian(graphWithHCycle))
    println(existsHamiltonian(graphWithoutHCycle))
}
