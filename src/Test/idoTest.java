package Test;

import java.awt.Window;
import java.util.Iterator;
import java.util.Random;

import algorithms.Graph_Algo;
import dataStructure.*;
import utils.*;

public class idoTest {

	public final static int numberOfVertexs = 10;
	public final static int numberOfEdge = 10;

	public static void main(String[] args) {
		idotesting();
//		edutesting();
//	 	save_init_test();
	}
	private static void save_init_test() {
		DGraph g = new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1), 0);
		Vertex v2=new Vertex(new Point3D(10,80,1), 0);
		Vertex v3=new Vertex(new Point3D(10,20,1), 0);
		Vertex v4=new Vertex(new Point3D(7,65,1), 0);
		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);
		g.connect(v1.getKey(), v2.getKey(), 3);
		g.connect(v3.getKey(), v4.getKey(), 6);
		g.connect(v1.getKey(), v4.getKey(), 2.33);
		g.connect(v3.getKey(), v2.getKey(), 8);
		Graph_Algo al=new Graph_Algo();
		al.init(g);
		al.save("edut");
		Graph_Algo al1=new Graph_Algo();
		al1.init("edut");
//		graph gcopy = al.copy();
//		((DGraph)al.myGraph).paint();
//		g.removeNode(1);
//		((DGraph) gcopy).paint();
//		g.paint();
		
	//	((DGraph)al2.myGraph).paint();
	}
	private static void edutesting() {

		DGraph g = new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1), 0);
		Vertex v2=new Vertex(new Point3D(10,80,1), 0);
		Vertex v3=new Vertex(new Point3D(10,20,1), 0);
		Vertex v4=new Vertex(new Point3D(7,65,1), 0);
		g.addNode(v1); g.addNode(v2); g.addNode(v3); g.addNode(v4);
		g.connect(v1.getKey(), v2.getKey(), 3);
		g.connect(v2.getKey(), v3.getKey(), 6);
		g.connect(v4.getKey(), v1.getKey(), 2.33);
		g.connect(v3.getKey(), v4.getKey(), 888);

		System.out.println(g.getNeighborsToVertex());
		System.out.println(g.getVertexToNeighbors());
	//	System.out.println(g.getEdgesOfVertex());
		//System.out.println(g.getV());
		Graph_Algo ga=new Graph_Algo();
		ga.init(g);
	//	((DGraph) ga.myGraph).paint();
		System.out.println(ga.checkLegal());
		System.out.println(ga.isConnected());
//		g.removeEdge(1, 2);
//		g.removeNode(1);	
//		System.out.println(g.getNeighborsToVertex());
//		System.out.println(g.getVertexTohisNeighbors());
//		System.out.println(g.getEdgesOfVertex());
//		System.out.println(g.getV());
//		algotest(g);
		//g.paint();
//		System.out.println("before="+g.edgeSize());
//		System.out.println(g.getEdge(v1.getKey(), v2.getKey()).getWeight());
//		g.paint();
//		System.out.println("after="+g.edgeSize());
//		System.out.println(g.getMC());
//		System.out.println(g.getEdge(v1.getKey(), v2.getKey()));
	}
	private static void algotest(DGraph g) {
		Graph_Algo al = new Graph_Algo();	
		al.init(g);
		System.out.println(al.isConnected());
	}
	private static void idotesting() {
		long startTime = System.currentTimeMillis();
		Random r = new Random(3);
		DGraph g = new DGraph();
		for(int i = 1; i<=numberOfVertexs;i++) {
			g.addNode((node_data) new Vertex(new Point3D(r.nextInt(100),r.nextInt(100),0),r.nextInt(100))/*random weight*/);
		}
		for(int i = 1; i<=numberOfEdge;i++) {
			int v1 = r.nextInt(numberOfVertexs) +1;
			int v2;
			do {  
				v2 = r.nextInt(numberOfVertexs) +1;
			}while(v1 == v2);
			g.connect(v1, v2, g.getNode(v1).getLocation().distance2D(g.getNode(v2).getLocation()));
		}
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		//				nodeSizeTest(g);
		//				edgeSizeTest(g);
		
		StdDraw.paint(g);
//		GUI_Window w = new GUI_Window((graph) g);
//		w.setVisible(true);
		algotest(g);
	}
	private static void removeEdge(DGraph g, int src, int dest) {
		int sizebefore = g.edgeSize();
		edge_data edge = g.removeEdge(src, dest);
		if (sizebefore -1 == g.edgeSize() &&  edge != null)
			System.out.println("remove edge succsess");
	}

	private static void removeNodeTest(DGraph g, int key) {
		int sizebefore = g.nodeSize();
		node_data node = g.removeNode(key);
		if (sizebefore -1 == g.nodeSize() &&   node != null)
			System.out.println("remove node succsess");

	}

	private static void edgeSizeTest(DGraph g) {
		int expectedEdges = numberOfEdge;
		int actuelEdges = g.edgeSize();
		for (Iterator<node_data> iterator = g.getV().iterator(); iterator.hasNext();) {
			node_data node = (node_data) iterator.next();
			System.out.println(node.getKey() + "edges:\n" + g.getE(node.getKey()));
		}
		//		if(expectedEdges != actuelEdges)
		//			System.out.println("number of edges suppose to be equels --> expected is "+ 
		//					expectedEdges + " but got " + actuelEdges);
		//		else {
		System.out.println("nodeSizeTest succsess");
		//		}
	}

	private static void nodeSizeTest(DGraph g) {
		int expectedVertexs = numberOfVertexs;
		int actuelVertexs = g.nodeSize();
		if(expectedVertexs != actuelVertexs)
			System.out.println("number of vertexs suppose to be equels --> expected is "+ 
					expectedVertexs + " but got " + actuelVertexs);
		else {
			System.out.println("edgeSizeTest succsess");
		}
		System.out.println("Vertex list:\n"+g.getV());
	}


}
