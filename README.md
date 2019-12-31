# OPP_ex2-4
DGraph
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
main methods 

