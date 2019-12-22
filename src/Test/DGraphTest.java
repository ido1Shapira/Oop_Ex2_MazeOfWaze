package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;

public class DGraphTest {
	
	public static DGraph g = new DGraph();
	public final static int numberOfVertexs = 100;
	public final static int numberOfEdges = 100;

//	@Before
//	public static void createGraph() throws Exception {
//	
//	}
//	@Test
//	public void testDGraph() {
//		for(int i = 1; i<=numberOfVertexs;i++) {
////			g.addNode((node_data) new Vertex(new Point3D(i,i+1,i+2),Math.random()*100));
//		}
//		for(int i = 1; i<=numberOfVertexs;i++) {
//			int v1 = (int)Math.random()*numberOfVertexs;
//			int v2 = (int)Math.random()*numberOfVertexs;
//			g.connect(v1, v2, Math.random()*100);
//		}
//		int expectedVertexs = numberOfVertexs;
//		int actuelVertexs = g.nodeSize();
////		assertEquals(expectedVertexs, actuelVertexs , "number of vertexs suppose to be equels");
//		int expectedEdges = numberOfVertexs;
//		int actuelEdges = g.edgeSize();
////		assertEquals(expectedEdges, actuelEdges , "number of edges suppose to be equels");
//	}
//
//	@Test
//	public void testAddNode() {
//		
//	}
//
	@Test
	public void testConnect() {
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
		Graph_Algo al= new Graph_Algo();
		al.init(dg);
		StdDraw.paint(dg);
		System.out.println(al.isConnected());
		if(al.isConnected()) {
			System.out.println("fail");
			fail("isConnected failed");
		}
		dg.connect(v4.getKey(), v5.getKey(), 10);
		System.out.println(al.isConnected());
		if(!al.isConnected()) {
			System.out.println("fail2");

			fail("isConnected failed");
	}
	}
//
//	@Test
//	public void testGetV() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetE() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveNode() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveEdge() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testNodeSize() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEdgeSize() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetMC() {
//		fail("Not yet implemented");
//	}

}
