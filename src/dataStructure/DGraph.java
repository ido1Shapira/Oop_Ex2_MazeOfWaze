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
	/**
	 * paint the gragh
	 */
	public void paint() {
		StdDraw.setCanvasSize(800,600);
		StdDraw.setXscale(0,100);
		StdDraw.setYscale(0,100);
		for (Iterator<node_data> iterator = this.getV().iterator(); iterator.hasNext();) {
			node_data node = (node_data) iterator.next();
			drawNode(node);
			if(this.getE(node.getKey()) != null) {
				for (Iterator<edge_data> iterator2 = this.getE(node.getKey()).iterator(); iterator2.hasNext();) {
					edge_data edge = (edge_data) iterator2.next();
					drawEdge(edge);
				}
			}
		}
	}
	private void drawEdge(edge_data edge) {
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.BLACK);
		node_data src = this.getNode(edge.getSrc());
		node_data dest = this.getNode(edge.getDest());
		StdDraw.line(src.getLocation().x(),src.getLocation().y(),dest.getLocation().x() , dest.getLocation().y());
		StdDraw.setPenRadius(0.02);
		StdDraw.setPenColor(StdDraw.ORANGE);
		double relativex=(src.getLocation().x()+5*dest.getLocation().x())/6;
		double relativey=(src.getLocation().y()+5*dest.getLocation().y())/6;
		//		double x[]= {relativex-1,relativex+1,relativex};
		//		double y[]= {relativey+1,relativey-1,relativey};
		//		StdDraw.filledPolygon(x, y);
		StdDraw.point(relativex, relativey);
		int round=(int)(edge.getWeight()*100);
		double roundafter=round;
		roundafter=roundafter/100;
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(relativex-1, relativey-1,""+roundafter);
	}
	private void drawNode(node_data node) {
		StdDraw.setPenRadius(0.03);
		StdDraw.setPenColor(StdDraw.CYAN);
		StdDraw.point(node.getLocation().x(), node.getLocation().y());
	}
}
