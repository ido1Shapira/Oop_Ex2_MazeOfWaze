package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import utils.Point3D;
import utils.StdDraw;

/**
 * This class represents a directional weighted graph.
 */
public class DGraph implements graph{

	private HashMap<Integer,node_data> idToVertex;
	private HashMap<Integer,ArrayList<Integer>> vertexTohisNeighbors;
	private HashMap<Integer,ArrayList<edge_data>> edgesOfVertex;
	private HashMap<List<Integer>,edge_data> idToEdge;
	private int mc;

	/////////Default contractor//////////
	public DGraph() {
		this.idToVertex = new HashMap<Integer,node_data>();
		this.idToEdge = new HashMap<List<Integer>,edge_data>();
		this.vertexTohisNeighbors = new HashMap<Integer,ArrayList<Integer>>();
		this.edgesOfVertex = new HashMap<Integer,ArrayList<edge_data>>();
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
			this.idToVertex.put(n.getKey(),new Vertex(n));
		}
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
		List<Integer> id = new ArrayList<Integer>();
		id.add(src);
		id.add(dest);
		if(!this.idToEdge.containsKey(id)) {
			this.idToEdge.put(id,new Edge(src,dest,w)); //ask edut if this ok
		}
		if(!this.vertexTohisNeighbors.containsKey(src)) {
			this.vertexTohisNeighbors.put(src,(new ArrayList<Integer>()));
		}
		this.vertexTohisNeighbors.get(src).add(dest);
		if(!this.edgesOfVertex.containsKey(src)) {
			this.edgesOfVertex.put(src,new ArrayList<edge_data>());
		}
		this.edgesOfVertex.get(src).add(this.idToEdge.get(id));
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
			List<Integer> id = new ArrayList<Integer>();
			id.add(key);
			for (Iterator<Integer> iterator = this.vertexTohisNeighbors.get(key).iterator(); iterator.hasNext();) {
				int id_node = (int) iterator.next();
				id.add(id_node);
				this.idToEdge.remove(id);
				id.remove(1); //id_node
			}
			this.vertexTohisNeighbors.remove(key);
			this.idToVertex.remove(key);
			this.edgesOfVertex.remove(key);
		}
		this.mc++;
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
			this.edgesOfVertex.get(src).remove(edgeToRemove);
		}
		this.mc++;
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
	public class Edge implements edge_data {	
		private int idSrc;
		private int idDest;
		private double weight;
		private String info;
		private int tag;
		///////////////////constructors/////////////////////
		public Edge(int idSrc, int idDest, double weight, String info, int tag) {
			this.idSrc = idSrc;
			this.idDest = idDest;
			if(weight >= 0) this.weight = weight;
			else throw new RuntimeException("weight must be a positive value");
			this.setInfo(info);
			this.setTag(tag);
		}
		public Edge(int idSrc, int idDest, double weight, String info) {
			this.idSrc = idSrc;
			this.idDest = idDest;
			if(weight >= 0) this.weight = weight;
			else throw new RuntimeException("weight must be a positive value");
			this.setInfo(info);
			this.tag = 0;
		}
		public Edge(int idSrc, int idDest, double weight) {
			this.idSrc = idSrc;
			this.idDest = idDest;
			if(weight >= 0) this.weight = weight;
			else throw new RuntimeException("weight must be a positive value");
			this.info = "";
			this.tag = 0;
		}
		/**
		 * The id of the source node of this edge.
		 * @return
		 */
		@Override
		public int getSrc() {
			return this.idSrc;
		}
		/**
		 * The id of the destination node of this edge
		 * @return
		 */
		@Override
		public int getDest() {
			return this.idDest;
		}
		/**
		 * @return the weight of this edge (positive value).
		 */
		@Override
		public double getWeight() {
			return this.weight;
		}
		/**
		 * return the remark (meta data) associated with this edge.
		 * @return
		 */
		@Override
		public String getInfo() {
			return this.info;
		}
		/**
		 * Allows changing the remark (meta data) associated with this edge.
		 * @param s
		 */
		@Override
		public void setInfo(String s) {
			this.info = s;
		}
		/**
		 * Temporal data (aka color: e,g, white, gray, black) 
		 * which can be used be algorithms 
		 * @return
		 */
		@Override
		public int getTag() {
			return this.tag;
		}
		/** 
		 * Allow setting the "tag" value for temporal marking an edge - common 
		 * practice for marking by algorithms.
		 * @param t - the new value of the tag
		 */
		@Override
		public void setTag(int t) {
			if (t >= 0) this.tag = t;
		}
	}
	/**
	 * This class represents a node (vertex) in a (directional) weighted graph.
	 * @author ido shapira & edut cohen
	 */
	private static int key=1;
	public class Vertex implements node_data {
		private int id;
		private Point3D location;
		private double weight;
		private String info;
		private int tag;
		///////////////////constructors/////////////////////
		public Vertex(Point3D location, double weight ,String info,int tag) {
			this.id = key++;
			this.setLocation(location);
			this.setWeight(weight);
			this.setInfo(info);
			this.setTag(tag);
		}
		public Vertex(Point3D location, double weight,String info) {
			this.id = key++;
			this.setLocation(location);
			this.setWeight(weight);
			this.setInfo(info);
			this.tag = 0;
		}
		public Vertex(Point3D location, double weight) {
			this.id = key++;
			this.setLocation(location);
			this.setWeight(weight);
			this.info = "";
			this.tag = 0;
		}
		public Vertex(node_data n) {
			this.id = n.getKey();
			this.info = this.getInfo();
			this.location = new Point3D(n.getLocation());
			this.weight = n.getWeight();
			this.tag = n.getTag();
		}
		/**
		 * Return the key (id) associated with this node.
		 * @return
		 */
		@Override
		public int getKey() {
			return this.id;
		}
		/** Return the location (of applicable) of this node, if
		 * none return null.
		 * @return
		 */
		@Override
		public Point3D getLocation() {
			return this.location;
		}
		/** Allows changing this node's location.
		 * @param p - new new location  (position) of this node.
		 */
		@Override
		public void setLocation(Point3D p) {
			this.location = new Point3D(p);
		}
		/**
		 * Return the weight associated with this node.
		 * @return
		 */
		@Override
		public double getWeight() {
			return this.weight;
		}
		/**
		 * Allows changing this node's weight.
		 * @param w - the new weight
		 * @throws Exception 
		 */
		@Override
		public void setWeight(double w) {
			if (w >= 0) {
				this.weight = w;
			}
			else
				System.out.println("weight must be positive");
		}
		/**
		 * return the remark (meta data) associated with this node.
		 * @return
		 */
		@Override
		public String getInfo() {
			return this.info;
		}
		/**
		 * Allows changing the remark (meta data) associated with this node.
		 * @param s
		 */
		@Override
		public void setInfo(String s) {
			this.info = s;
		}
		/**
		 * Temporal data (aka color: e,g, white, gray, black) 
		 * which can be used be algorithms 
		 * @return
		 */
		@Override
		public int getTag() {
			return this.tag;
		}
		/** 
		 * Allow setting the "tag" value for temporal marking an node - common 
		 * practice for marking by algorithms.
		 * @param t - the new value of the tag
		 */
		@Override
		public void setTag(int t) {
			if(t >=0) {
				this.tag = t;
			}
		}
	}
}
