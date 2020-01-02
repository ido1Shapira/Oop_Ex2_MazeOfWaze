package Tests;

import java.util.ArrayList;
import java.util.Random;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;
import utils.StdDraw;

public class idoTest {

	public final static int numberOfVertexs = 5;
	public final static int numberOfEdge = 15;
	public static int seed = 4;
	public static void main(String[] args) {
//		SPtest();
//		isConnectedTest();
//		edutesting();
//		save_init_test();
//		idotesting();
//		connectest();
	   	emptyGraphTest();
	}
	private static void emptyGraphTest() {
		DGraph g = new DGraph();		
		Graph_GUI gui = new Graph_GUI();

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
		g.addNode(v5);
		g.connect(v1.getKey(), v2.getKey(), 10);
		g.connect(v2.getKey(), v3.getKey(), 15);
		g.connect(v3.getKey(), v1.getKey(), 20);
		g.connect(v2.getKey(), v4.getKey(), 10);
		g.connect(v4.getKey(), v5.getKey(), 10);
		g.connect(v5.getKey(), v2.getKey(), 15);
		g.connect(v4.getKey(), v1.getKey(), 20);
		g.connect(v5.getKey(), v1.getKey(), 10);
		Graph_GUI gui = new Graph_GUI(g);
		Graph_Algo al= new Graph_Algo();
		al.init(g);
		System.out.println("true= "+al.isConnected());
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
		g.connect(v2.getKey(), v4.getKey(), 5);
		g.connect(v2.getKey(), v1.getKey(), 10);
		g.connect(v3.getKey(), v1.getKey(), 15);
		g.connect(v4.getKey(), v1.getKey(), 20);
		g.connect(v5.getKey(), v1.getKey(), 20);
		Graph_Algo al=new Graph_Algo();
		al.init(g);
		Graph_GUI gui = new Graph_GUI(g);
		System.out.println("false= " +al.isConnected());
		System.out.println("path 1-4 =(15) "+al.shortestPathDist(1, 4));
		System.out.println("object address trip (3 expected) "+al.shortestPath(1, 4));
		ArrayList<Integer> check= new ArrayList<Integer>();
		check.add(1);
		check.add(2);
		check.add(3);
		check.add(4);
		check.add(5);
		System.out.println(al.TSP(check));
		ArrayList <node_data> listi= (ArrayList<node_data>) al.TSP(check);
		System.out.println("expected 5 1 2 4 1 3");
		for (int i = 0; i < listi.size(); i++) {
				System.out.print(listi.get(i).getKey());
			}
		System.out.println();
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
		g.connect(v4.getKey(), v1.getKey(), 3);
		g.connect(v1.getKey(), v2.getKey(), 3);
		g.connect(v3.getKey(), v4.getKey(), 6);
		g.connect(v1.getKey(), v4.getKey(), 2.33);
		g.connect(v3.getKey(), v2.getKey(), 8);
		Graph_Algo al=new Graph_Algo();
		al.init(g);
		al.save("edut");
		Graph_Algo al1=new Graph_Algo();
		al1.init("edut");
		g.removeNode(1);
		System.out.println("edge size(2)= "+g.edgeSize());
		Graph_GUI gui = new Graph_GUI(g);
	}
	private static void edutesting() {
		DGraph g = new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1), 1);
		Vertex v2=new Vertex(new Point3D(10,80,1), 2);
		Vertex v3=new Vertex(new Point3D(10,20,1), 3);
		Vertex v4=new Vertex(new Point3D(7,65,1), 4);
		g.addNode(v1);g.addNode(v2);g.addNode(v3);g.addNode(v4);
		g.connect(v1.getKey(), v2.getKey(), 3);
		g.connect(v2.getKey(), v4.getKey(), 6);
		g.connect(v4.getKey(), v3.getKey(), 2.33);
		g.connect(v3.getKey(), v2.getKey(), 8);
		g.connect(v2.getKey(), v3.getKey(), 8);
		g.removeEdge(1, 2);
		g.removeNode(1);
		Graph_Algo ga=new Graph_Algo();
		ga.init(g);
		Graph_GUI gui = new Graph_GUI(g);
		System.out.println("conncted true expected= "+ga.isConnected());
		System.out.println("shortest path null expected= "+ ga.shortestPath(1, 2));
		System.out.println("8 expected= "+ga.shortestPathDist(2, 3));		
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
		Graph_GUI gui = new Graph_GUI(g);
		Graph_Algo al= new Graph_Algo();
		al.init(g);
		ArrayList<Integer> targets= new ArrayList<Integer>();
		targets.add(3);
		targets.add(8);
		targets.add(4);
		targets.add(2);
		System.out.println("random something");
		System.out.println(g.getNode(8));
		System.out.println(al.TSP(targets));
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
		Graph_GUI gui = new Graph_GUI(dg);
		dg.connect(v4.getKey(), v5.getKey(), 10);
		System.out.println("true expected= "+al.isConnected());
		al.init(dg);
		Graph_GUI gui1 = new Graph_GUI(dg);
	}
}
