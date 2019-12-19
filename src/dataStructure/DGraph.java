package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import utils.StdDraw;

/**
 * This class represents a directional weighted graph.
 */
public class DGraph implements graph, Serializable{

	private HashMap<Integer,node_data> idToVertex;
	private HashMap<ArrayList<Integer>,edge_data> idToEdge;
	private HashMap<Integer,HashSet<Integer>> vertexToNeighbors; // v1--->V
	private HashMap<Integer,HashSet<Integer>> NeighborsToVertex; // V--->v1
	private HashMap<Integer,HashSet<edge_data>> edgesOfVertex;
	private int mc;

	/////////Default contractor//////////
	public DGraph() {
		this.idToVertex = new HashMap<Integer,node_data>();
		this.idToEdge = new HashMap<ArrayList<Integer>,edge_data>();
		this.vertexToNeighbors = new HashMap<Integer,HashSet<Integer>>();
		this.NeighborsToVertex = new HashMap<Integer,HashSet<Integer>>();
		this.edgesOfVertex = new HashMap<Integer,HashSet<edge_data>>();
	}
	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	@Override
	public node_data getNode(int key) {
		return this.idToVertex.get(key);
	}

	/**
	 * return the data of the edge (src,dest), null if none.
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		ArrayList<Integer> id = new ArrayList<Integer>();
		id.add(src);
		id.add(dest);
		return this.idToEdge.get(id);
	}

	/**
	 * add a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n
	 */
	@Override
	public void addNode(node_data n) {
		this.idToVertex.put(n.getKey(),new Vertex(n));
		this.mc++;
	}

	/**
	 * Connect an edge with weight w between node src to node dest.
	 * * Note: this method should run in O(1) time.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 */
	@Override
	public void connect(int src, int dest, double w) {
		ArrayList<Integer> id = new ArrayList<Integer>();
		id.add(src);
		id.add(dest);
		this.idToEdge.put(id,new Edge(src,dest,w));
		try {
			this.vertexToNeighbors.get(src).add(dest);
		}
		catch(NullPointerException e){
			this.vertexToNeighbors.put(src,(new HashSet<Integer>()));			this.vertexToNeighbors.get(src).add(dest);
			this.vertexToNeighbors.get(src).add(dest);
		}
		try {
			this.NeighborsToVertex.get(dest).add(src);
		}
		catch(NullPointerException e){
			this.NeighborsToVertex.put(dest,(new HashSet<Integer>()));			this.vertexToNeighbors.get(src).add(dest);
			this.NeighborsToVertex.get(dest).add(src);
		}
		try {
			this.edgesOfVertex.get(src).add(this.getEdge(src, dest));
		}
		catch(NullPointerException e){
			this.edgesOfVertex.put(src,new HashSet<edge_data>());	
			this.edgesOfVertex.get(src).add(this.getEdge(src, dest));
		}
		this.mc++;
	}
	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method should run in O(1) time.
	 * @return Collection<node_data>
	 */
	@Override
	public Collection<node_data> getV() {
		return this.idToVertex.values();
	}
	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the edges getting out of 
	 * the given node (all the edges starting (source) at the given node). 
	 * Note: this method should run in O(1) time.
	 * @return Collection<edge_data>
	 */
	@Override
	public Collection<edge_data> getE(int node_id) {
		return this.edgesOfVertex.get(node_id);
	}
	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key
	 */
	@Override
	public node_data removeNode(int key) {
		node_data nodeToRemove = this.idToVertex.get(key);
		if(nodeToRemove != null) {
			for (Iterator<Integer> iterator = this.idToVertex.keySet().iterator(); iterator.hasNext();) {
				Integer dest = (Integer) iterator.next();
				if(key != dest) {
					this.removeEdge(key, dest);
					this.removeEdge(dest, key);
				}
			}
			this.NeighborsToVertex.remove(key);
			this.vertexToNeighbors.remove(key);
			this.edgesOfVertex.remove(key);
			this.idToVertex.remove(key);

			this.mc++;
		}
		return nodeToRemove;
	}
	/**
	 * Delete the edge from the graph, 
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */
	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data edgeToRemove = this.getEdge(src, dest);
		if(edgeToRemove != null) {
			ArrayList<Integer> key = new ArrayList<Integer>();
			key.add(src);
			key.add(dest);
			this.edgesOfVertex.get(src).remove(edgeToRemove);
			this.vertexToNeighbors.get(src).remove((Integer)dest); ///fix me
			this.NeighborsToVertex.get(dest).remove((Integer)src); ///fix me
			this.idToEdge.remove(key);

			this.mc++;
		}
		return edgeToRemove;
	}
	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time. 
	 * @return
	 */
	@Override
	public int nodeSize() {
		return idToVertex.size();
	}
	/** 
	 * return the number of edges (assume directional graph).
	 * Note: this method should run in O(1) time.
	 * @return
	 */
	@Override
	public int edgeSize() {
		return idToEdge.size();
	}
	public HashMap<Integer, HashSet<Integer>> getVertexToNeighbors() {
		return vertexToNeighbors;
	}
	public HashMap<Integer, HashSet<edge_data>> getEdgesOfVertex() {
		return edgesOfVertex;
	}
	public HashMap<Integer, HashSet<Integer>> getNeighborsToVertex() {
		return NeighborsToVertex;
	}
	/**
	 * return the Mode Count - for testing changes in the graph.
	 * @return
	 */
	@Override
	public int getMC() {
		return this.mc;
	}
	
}
