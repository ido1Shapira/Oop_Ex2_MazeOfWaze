package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import utils.Point3D;

/**
 * This class represents a directional weighted graph.
 */
public class DGraph implements graph, Serializable{

	public static final int FORBIDDEN_KEY = 0;
	private int id;
	private HashMap<ArrayList<Integer>,edge_data> idToEdge;
	private HashMap<Integer,Object> idToVertexEdge; //key = 0 all vertex key >0 list of edges
	private int mc;

	/////////Default contractor//////////
	public DGraph() {
		this.id =0;
		this.idToEdge = new HashMap<ArrayList<Integer>,edge_data>();
		this.idToVertexEdge = new HashMap<Integer,Object>();
		this.idToVertexEdge.put(0,new HashMap<Integer,node_data>());
	}
	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	@Override
	public node_data getNode(int key) {
		if(key <= FORBIDDEN_KEY)
		{throw new RuntimeException("FORBIDDEN_KEY exception - key cant be 0 , starting from 1");}
		return ((HashMap<Integer,node_data>)this.idToVertexEdge.get(0)).get(key);
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
		if(src <= FORBIDDEN_KEY || dest <= FORBIDDEN_KEY) 
		{throw new RuntimeException("FORBIDDEN_KEY exception - key cant be 0 , starting from 1");}
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
		id++;
		((HashMap<Integer,node_data>)this.idToVertexEdge.get(0)).put(id,new Vertex(new Point3D(n.getLocation()),id));

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
		if(src <= FORBIDDEN_KEY || dest <= FORBIDDEN_KEY) 
		{throw new RuntimeException("FORBIDDEN_KEY exception - key cant be 0 , starting from 1");}
		ArrayList<Integer> id = new ArrayList<Integer>();
		id.add(src);
		id.add(dest);
		this.idToEdge.put(id,new Edge(src,dest,w));
		try {
			((HashSet<edge_data>) this.idToVertexEdge.get(src)).add(this.getEdge(src, dest));
		}
		catch(NullPointerException e){
			this.idToVertexEdge.put(src,new HashSet<edge_data>());	
			((HashSet<edge_data>) this.idToVertexEdge.get(src)).add(this.getEdge(src, dest));
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
		return ((HashMap<Integer,node_data>)this.idToVertexEdge.get(0)).values();
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
		if(node_id <= FORBIDDEN_KEY)
		{throw new RuntimeException("FORBIDDEN_KEY exception - key cant be 0 , starting from 1");}
		return (Collection<edge_data>) this.idToVertexEdge.get(node_id);
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
		if(key <= FORBIDDEN_KEY) {throw new RuntimeException("FORBIDDEN_KEY exception - key cant be 0 , starting from 1");}
		node_data nodeToRemove = this.getNode(key);
		if(nodeToRemove != null) {
			for (Iterator<node_data> iterator = this.getV().iterator(); iterator.hasNext();) {
				node_data dest = (node_data) iterator.next();
				if(key != dest.getKey()) {
					this.removeEdge(key, dest.getKey());
					this.removeEdge(dest.getKey(), key);
				}
			}
			((HashMap<Integer,node_data>)this.idToVertexEdge.get(0)).remove(key);
			this.idToVertexEdge.remove(key);
			
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
		if(src <= FORBIDDEN_KEY || dest <= FORBIDDEN_KEY) 
		{throw new RuntimeException("FORBIDDEN_KEY exception - key cant be 0 , starting from 1");}
		edge_data edgeToRemove = this.getEdge(src, dest);
		if(edgeToRemove != null) {
			ArrayList<Integer> key = new ArrayList<Integer>();
			key.add(src);
			key.add(dest);
			((HashSet<edge_data>)this.idToVertexEdge.get(src)).remove(edgeToRemove);
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
		return id;
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