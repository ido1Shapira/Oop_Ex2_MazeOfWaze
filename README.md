# assignment 2
## DGraph

our class represents a directed graph who is made of nodes and edges
every edge has a source node and a destenation node, which means this edge creates a path from source to destenation
(the oposite path may not exist)
every edge has a weight filed that represents the "cost" of using this edge during a path, 
we would like to think about this cost as the time it takes to get from source node to destenation node.
thats why nonpositve weight is not possible.
DGraph object:
* int id- represents the next available id for a node to be added.
* HashMap<Integer,node_data> idToVertex- a hash that gets an id of a node and connects it to the actual node who has that id.
* HashMap<Integer,HashMap<Integer,edge_data>>- a hashMap that represents the edges in the graph. main key is the id of the source node
values for a specific key is a hashMap where the keys are the id of all the nodes that has a direct edge that starts with this specific key and ends within them  
* int edgeNum- the number of edges in the graph
* int mc- mode counter- counts all the changes in thte graph 

DGraph implements graph interface because every directed graph is first of all a graph.

### main methods 

* getNode- return the node_data by the node's id-  O(1)

* getEdge- return the data of the edge (src,dest), null if edge does not exist- O(1)

* addNode- add a new node to the graph with the given node's data.- O(1)

* connect- connect an edge with the given weight between node src to node dest. O(1)

* getV- This method return a pointer (shallow copy) for the collection representing all the nodes in the graph. O(1)

* getE(int srcID)- This method return a pointer (shallow copy) for the collection representing all the edges getting out of the given node (all the edges starting at the given node). O(1)

* removeNode (int nodeID)- Delete the node (with the given ID) from the graph -and removes all edges which starts or ends at this node - O(n)

* removeEdge(int src, int dest)- delete the edge from the graph (the one from src to dest) O(1)

* nodeSize- return the number of nodes O(1)

* edgeSize- return the number of edges O(1)

* getMC- return the number of changes of the graph O(1)

