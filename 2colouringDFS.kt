/*
The program works by traversing the graph using DFS and assigning each node a
colour that is different from the colour of the node it just came from.
*/

typealias Colour = Int
class Graph<T>(
  private val adjacencyList: List<LinkedList<Int>>
) {
  val colours = IntArray(adjacencyList.size) { -1 }
  private val visited = mutableSetOf<Int>()

  fun colouringDFS() {
     (0..<adjacencyList.size).forEach {
       if (colours[it] == -1) { colourNode(it, 0) }
     }
   }
  
  fun colourNode(
     currentNode: Int = 0,
     nextAssignedColour: Colour = 0,
  ) {
    visited.add(currentNode)
    colours[currentNode] = nextAssignedColour
    
    adjacencyList[currentNode].forEach {
      if (it !in visited) {
         this.colourNode(it, (nextAssignedColour + 1) % 2)
      }
    }
  }
}
