/*
Explanation: Modification of the Floyd's algorithm
For each pair of nodes i and j, consider every intermediate node k. 
A path from i to j either passes through or not through k. The path through k 
would have a bandwidth of min(g[i][k], g[k][j]). The path not through k would 
have a bandwidth of g[i][j]. Hence, to find the max bandwidth let 
graph[i][j] = maxOf(graph[i][j], minOf(graph[i][k], graph[k][j])). 

Note: algorithm relies on no edges between nodes having a value of 0 in the matrix.
*/

fun findGreatestBandwidths(graph: Array<IntArray>): Array<IntArray> {
    val n = graph.size
    
    for (k in 0..<n) {
        for (i in 0..<n) {
            for (j in 0..<n) {
            	graph[i][j] = maxOf(graph[i][j], minOf(graph[i][k], graph[k][j]))
        	}
        }
    }
    
    return graph
}

fun main() {
    val graph = arrayOf(
        intArrayOf(0, 5, 3, 0),
        intArrayOf(0, 0, 4, 6), 
        intArrayOf(0, 0, 0, 2), 
        intArrayOf(0, 0, 7, 0)  
    )
    
    println(findGreatestBandwidths(graph).joinToString("\n") { it.joinToString(" ") })
}
