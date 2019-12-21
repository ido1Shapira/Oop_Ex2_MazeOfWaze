package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import utils.Point3D;

/**
 * This class represents a directional weighted graph.
 */
public class DGraph implements graph, Serializable{

	private int id;
	private HashMap<Integer,node_data> idToVertex;
	private HashMap<Integer,HashMap<Integer,edge_data>> idToEdge; //key = 0 all vertex key >0 list of edges
	private int mc;

	/////////Default contractor//////////
	public DGraph() {
		this.id =0;
		this.idToVertex = new HashMap<Integer,node_data>();
		this.idToEdge = new HashMap<Integer,HashMap<Integer,edge_data>>();
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
		return this.idToEdge.get(src).get(dest);
	}

	/**
	 * add a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n
	 */
	@Override
	public void addNode(node_data n) {
		id++;
		int sizeBefore = this.nodeSize();
		this.idToVertex.put(id,new Vertex(new Point3D(n.getLocation()),id));
		this.idToEdge.put(id,new HashMap<Integer,edge_data>());	
		if(this.nodeSize() -1 == sizeBefore) {this.mc++;}
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
		try {
			this.idToEdge.get(src).put(dest, new Edge(src,dest,w));
		}
		catch(NullPointerException e){
			this.idToEdge.put(src,new HashMap<Integer,edge_data>());	
			this.idToEdge.get(src).put(dest, new Edge(src,dest,w));
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
		return this.idToEdge.get(node_id).values();
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
		node_data nodeToRemove = this.getNode(key);
		if(nodeToRemove != null) {
			for (Iterator<HashMap<Integer,edge_data>> iter = this.idToEdge.values().iterator(); iter.hasNext();)
			{
				HashMap<Integer,edge_data> hash = (HashMap<Integer,edge_data>) iter.next();
				for (Iterator<Integer>iter2 = hash.keySet().iterator(); iter2.hasNext();)
				{
					int dest = (int) iter2.next();
					this.removeEdge(dest, key);

				}
			}
			this.idToVertex.remove(key);
			this.idToEdge.remove(key);
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
				this.idToEdge.get(src).remove(dest);
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