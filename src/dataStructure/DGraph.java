package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a directional weighted graph.
 */
public class DGraph implements graph{

	private HashMap<Integer,node_data> idToVertex;
	private HashMap<node_data,ArrayList<node_data>> vertexTohisNeighbors;
	private HashMap<node_data,ArrayList<edge_data>> edgesOfVertex;
	private HashMap<List<Integer>,edge_data> idToEdge;
	private int mc;

	/////////Default contractor//////////
	public DGraph() {
		this.idToVertex = new HashMap<Integer,node_data>();
		this.idToEdge = new HashMap<List<Integer>,edge_data>();
		this.vertexTohisNeighbors = new HashMap<node_data,ArrayList<node_data>>();
		this.edgesOfVertex = new HashMap<node_data,ArrayList<edge_data>>();

	}
	//////copy contractor//////////////
//	public DGraph(DGraph g) {
//		this.idToVertex = new HashMap<Integer,node_data>();
//		for (Iterator<node_data> iterator = g.getV().iterator(); iterator.hasNext();) {
//			node_data node = (node_data) iterator.next();
//			this.idToVertex.put(node.getKey(),node);
//		}
//
//		this.vertexTohisNeighbors = new HashMap<node_data,ArrayList<node_data>>();
//		this.vertexTohisNeighbors.putAll(g.vertexTohisNeighbors);
//
//		this.idToEdge = new HashMap<List<Integer>,edge_data>();
//		this.idToEdge.putAll(g.idToEdge);
//
//		this.mc = g.mc;
//	}

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
		List<Integer> id = new ArrayList<Integer>();
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
		if(!this.idToVertex.containsKey(n.getKey())) {
			this.idToVertex.put(n.getKey(),n);
		}
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
		List<Integer> id = new ArrayList<Integer>();
		id.add(src);
		id.add(dest);
		if(!this.idToEdge.containsKey(id)) {
			this.idToEdge.put(id,new Edge(src,dest,w)); //ask edut if this ok
		}
		if(this.vertexTohisNeighbors.isEmpty()) {
			this.vertexTohisNeighbors.put(this.getNode(src),(new ArrayList<node_data>()));
		}
		this.vertexTohisNeighbors.get(this.getNode(src)).add(this.getNode(dest));
		if(this.edgesOfVertex.isEmpty()) {
			this.edgesOfVertex.put(this.getNode(src),(new ArrayList<edge_data>()));
		}
		this.edgesOfVertex.get(this.getNode(src)).add(this.idToEdge.get(id));
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
		return this.edgesOfVertex.get(this.getNode(node_id));
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
			List<Integer> id = new ArrayList<Integer>();
			id.add(key);
			for (Iterator<node_data> iterator = this.vertexTohisNeighbors.get(nodeToRemove).iterator(); iterator.hasNext();) {
				node_data node = (node_data) iterator.next();
				id.add(node.getKey());
				this.idToEdge.remove(id);
				id.remove(node.getKey());
			}
			this.vertexTohisNeighbors.remove(nodeToRemove);
			this.idToVertex.remove(key);
			this.edgesOfVertex.remove(this.getNode(key));
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
			List<Integer> key = new ArrayList<Integer>();
			key.add(src);
			key.add(dest);
			this.idToEdge.remove(key);
			this.edgesOfVertex.get(this.getNode(src)).remove(edgeToRemove);
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

	/**
	 * return the Mode Count - for testing changes in the graph.
	 * @return
	 */
	@Override
	public int getMC() {
		return this.mc;
	}

}
