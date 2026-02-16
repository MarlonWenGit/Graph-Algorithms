/*
Explanation: regular Bellman-Held-Karp algorithm with a small tweak. The weight of the arc between the start node and the final node in the shortest path is added to the final value.

Note: algorithm relies on no edges between nodes having a value of inf in the matrix.
*/

fun findShortestHamiltonian(graph: Array<IntArray>, start: Int): Int {
    val inf = Int.MAX_VALUE / 2
    
    val n = graph.size
    val nodes = (0..<n).toMutableSet()
    val costMap = mutableMapOf<Pair<Set<Int>, Int>, Int>()
    val nodesWithoutStart = nodes - start
    
    nodesWithoutStart.forEach {
        costMap[emptySet<Int>() to it] = graph[start][it]
    }
    
    (1..n).forEach { size ->
        getSubsetsOfSizeN(size, nodesWithoutStart).forEach { S ->
            (nodesWithoutStart - S).forEach { x ->
                costMap[S to x] = inf
                S.forEach { y ->
                    costMap[S to x] = minOf(costMap[(S - y) to y]!! + graph[y][x]!!, costMap[S to x]!!)
                }
            }
        }
    }

    
    var opt = inf
    nodesWithoutStart.forEach {
        val cost = costMap[(nodesWithoutStart - it) to it]!!
        opt = minOf(opt, cost + graph[it][start])
    }
    
    return opt
}

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
    val inf = Int.MAX_VALUE / 2
    
    val graph = arrayOf(
        intArrayOf(0, 2, 3, 5),
        intArrayOf(2, 0, inf, 4), 
        intArrayOf(3, inf, 0, 6), 
        intArrayOf(5, 4, 6, 0)  
    )
   	
    var shortestPathWeight = inf
    (0..<graph.size).forEach {
        shortestPathWeight = minOf(shortestPathWeight, findShortestHamiltonian(graph, it))
    }
    
    println(shortestPathWeight)
}
