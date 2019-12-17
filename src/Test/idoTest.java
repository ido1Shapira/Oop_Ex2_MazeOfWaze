package Test;

import java.util.Iterator;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;

public class idoTest {

	public final static int numberOfVertexs = 4;
	public final static int numberOfEdge = 10;

	public static void main(String[] args) {
	idotesting();
//	edutesting();
	algotest();
	}
	private static void algotest() {
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
		al.save("start");
		Graph_Algo al2=new Graph_Algo();
//		al2.init("start");
		
	//	al2.myGraph.paint();
	}
	private static void edutesting() {
		DGraph g = new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1), 0);
		Vertex v2=new Vertex(new Point3D(10,80,1), 0);
		g.addNode(v1);
		g.addNode(v2);
		g.connect(v1.getKey(), v2.getKey(), 3);
		Vertex v3=new Vertex(new Point3D(10,20,1), 0);
		Vertex v4=new Vertex(new Point3D(7,65,1), 0);
		g.addNode(v3);
		g.addNode(v4);
		g.connect(v3.getKey(), v4.getKey(), 6);
		g.connect(v1.getKey(), v4.getKey(), 2.33);
		g.connect(v3.getKey(), v2.getKey(), 888);
		g.paint();
		System.out.println("before="+g.edgeSize());
		System.out.println(g.getEdge(v1.getKey(), v2.getKey()).getWeight());
		g.removeNode(v1.getKey());
		g.paint();
		System.out.println("after="+g.edgeSize());
		System.out.println(g.getMC());
		System.out.println(g.getEdge(v1.getKey(), v2.getKey()));
	}
	private static void idotesting() {
		long startTime = System.currentTimeMillis();
		DGraph g = new DGraph();
		for(int i = 1; i<=numberOfVertexs;i++) {
			g.addNode((node_data) new Vertex(new Point3D(Math.random()*100,Math.random()*100,0),Math.random()*100)/*random weight*/);
		}
		for(int i = 1; i<=numberOfEdge;i++) {
			int v1 = (int)(Math.random()*numberOfVertexs) +1;
			int v2;
			do {  
				v2 = (int)(Math.random()*numberOfVertexs) +1;
			}while(v1 == v2);
			g.connect(v1, v2, g.getNode(v1).getLocation().distance2D(g.getNode(v2).getLocation()));
		}
		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		//				nodeSizeTest(g);
		//				edgeSizeTest(g);
		//		g.connect(2, 3, 1);
		//		removeEdge(g,2,3);
		//		removeNodeTest(g,4);
		g.paint();
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
