package Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;

public class idoTest {

	public final static int numberOfVertexs = 10;
	public final static int numberOfEdge = 15;
	public static int seed = 4;
	public static void main(String[] args) {
//		SPtest();
//		isConnectedTest();
//		edutesting();
//		save_init_test();
		idotesting();
//		connectest();
	}
	private static void isConnectedTest() {
		DGraph g = new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1),1,10);
		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
		Vertex v3=new Vertex(new Point3D(10,20,1),3,30);
		Vertex v4=new Vertex(new Point3D(7,65,1),4,40);
		Vertex v5=new Vertex(new Point3D(80,10,1),5,50);

		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);
	//	g.addNode(v5);
		g.connect(v1.getKey(), v2.getKey(), 10);
		g.connect(v2.getKey(), v3.getKey(), 15);
		g.connect(v3.getKey(), v1.getKey(), 20);
		g.connect(v2.getKey(), v4.getKey(), 10);
		g.connect(v4.getKey(), v5.getKey(), 10);
//		g.connect(v5.getKey(), v2.getKey(), 15);
//		g.connect(v4.getKey(), v1.getKey(), 20);
//		g.connect(v5.getKey(), v1.getKey(), 10);
		StdDraw.paint(g);
		Graph_Algo al= new Graph_Algo();
		al.init(g);
		System.out.println(al.isConnected());
	}


	private static void SPtest() {
		DGraph g = new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1),1,10);
		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
		Vertex v3=new Vertex(new Point3D(10,20,1),3,30);
		Vertex v4=new Vertex(new Point3D(7,65,1),4,40);
		Vertex v5=new Vertex(new Point3D(80,10,1),5,50);

		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);
		g.addNode(v5);
		g.connect(v1.getKey(), v2.getKey(), 10);
		g.connect(v1.getKey(), v3.getKey(), 15);
		g.connect(v1.getKey(), v4.getKey(), 20);
		g.connect(v2.getKey(), v1.getKey(), 10);
		//		g.connect(v2.getKey(), v3.getKey(), 35);
		//		g.connect(v2.getKey(), v4.getKey(), 25);
		g.connect(v3.getKey(), v1.getKey(), 15);
		//		g.connect(v3.getKey(), v2.getKey(), 35);
		//		g.connect(v3.getKey(), v4.getKey(), 30);
		g.connect(v4.getKey(), v1.getKey(), 20);
		//		g.connect(v4.getKey(), v2.getKey(), 25);
		//		g.connect(v4.getKey(), v3.getKey(), 30);
		g.connect(v5.getKey(), v1.getKey(), 20);



		Graph_Algo al=new Graph_Algo();
		al.init(g);
		StdDraw.paint(g);
		//		System.out.println(al.isConnected());
		//		System.out.println("path 1-4 ="+al.shortestPathDist(1, 4));
		//	System.out.println("object address trip "+al.shortestPath(1, 4));
		//	al.drawTable();
		ArrayList<node_data> list= new ArrayList<node_data>();
		//	list.add(v1);
		list.add(v2);
		list.add(v3);
		//	System.out.println(al.mytsp(list, 0, 1, "").getPath());
		ArrayList<Integer> check= new ArrayList<Integer>();
		check.add(1);
		check.add(2);
		check.add(3);
		check.add(4);
		check.add(5);
		System.out.println(al.TSP(check));
		//	list.add(v4);
		//	ArrayList<node_data> listi= al.mytsp(list, 0, 1, new ArrayList<node_data>());
		//	System.out.println(listi.size());
		//	for (int i = 0; i < listi.size(); i++) {
		//		System.out.println(listi.get(i).getKey());
		//	}
	}


	private static void save_init_test() {
		DGraph g = new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1), 1);
		Vertex v2=new Vertex(new Point3D(10,80,1), 2);
		Vertex v3=new Vertex(new Point3D(10,20,1), 3);
		Vertex v4=new Vertex(new Point3D(7,65,1), 4);
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
		graph gcopy = al.copy();
		g.removeNode(1);

	}
	private static void edutesting() {

		DGraph g = new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1), 1);
		Vertex v2=new Vertex(new Point3D(10,80,1), 2);
		Vertex v3=new Vertex(new Point3D(10,20,1), 3);
		Vertex v4=new Vertex(new Point3D(7,65,1), 4);
		Vertex v5=new Vertex(new Point3D(44,24,1), 5);
		g.addNode(v1);g.addNode(v2);g.addNode(v3);g.addNode(v4);//g.addNode(v5);
		g.connect(v1.getKey(), v2.getKey(), 3);
		g.connect(v2.getKey(), v4.getKey(), 6);
		g.connect(v4.getKey(), v3.getKey(), 2.33);
		g.connect(v3.getKey(), v2.getKey(), 8);
		g.removeEdge(1, 2);
		g.removeNode(1);
		Graph_Algo ga=new Graph_Algo();
		ga.init(g);
		StdDraw.paint(g);
		System.out.println("conncted "+ga.isConnected());
		System.out.println(ga.shortestPathDist(1, 2));
		System.out.println(ga.shortestPathDist(1, 3));
		algotest(g);

	}
	private static void algotest(graph g) {
		Graph_Algo al = new Graph_Algo();	
		al.init(g);
		System.out.println(al.isConnected());
	}
	private static void idotesting() {
		long startTime = System.currentTimeMillis();
		Random r = new Random(seed);
		DGraph g = new DGraph();
		for(int i = 1; i<=numberOfVertexs;i++) {
			g.addNode((node_data) new Vertex(new Point3D(r.nextInt(300),r.nextInt(300),0)));
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
		StdDraw.paint(g);
		Graph_Algo al= new Graph_Algo();
		al.init(g);
		ArrayList<Integer> targets= new ArrayList<Integer>();
		targets.add(3);
		targets.add(8);
		targets.add(4);
		targets.add(2);

		System.out.println(al.TSP(targets));
	}
	private static void removeEdge(DGraph g, int src, int dest) {
		int sizebefore = g.edgeSize();
		edge_data edge = g.removeEdge(src, dest);
		if (sizebefore -1 == g.edgeSize() &&  edge != null)
			System.out.println("remove edge succsess");
	}
	private static void connectest() {
		DGraph dg= new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1),1,10);
		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
		Vertex v3=new Vertex(new Point3D(10,20,1),3,30);
		Vertex v4=new Vertex(new Point3D(7,65,1),4,40);
		Vertex v5=new Vertex(new Point3D(80,10,1),5,50);
		dg.addNode(v1);
		dg.addNode(v2);
		dg.addNode(v3);
		dg.addNode(v4);
		dg.addNode(v5);
		dg.connect(v1.getKey(), v2.getKey(), 10);
		dg.connect(v2.getKey(), v3.getKey(), 15);
		dg.connect(v3.getKey(), v4.getKey(), 20);
		dg.connect(v4.getKey(), v1.getKey(), 10);
		dg.connect(v5.getKey(), v1.getKey(), 10);
		dg.connect(v4.getKey(), v5.getKey(), 10);
		Graph_Algo al= new Graph_Algo();
		al.init(dg);
		StdDraw.paint(dg);
		//		System.out.println(al.isConnected());
		//		if(al.isConnected()) {
		//			System.out.println("fail");
		//		//	fail("isConnected failed");
		//		}
		dg.connect(v4.getKey(), v5.getKey(), 10);
		System.out.println(al.isConnected());
		al.init(dg);
		StdDraw.paint(dg);
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
