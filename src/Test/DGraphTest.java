package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;

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
	public final static int numberOfVertexs = 1000000;
	public final static int numberOfEdge = 10000000;
	public static int seed = 5;

		@Test
		public void testDGraph() {
			long startTime = System.currentTimeMillis();
			Random r = new Random(seed);
			DGraph g = new DGraph();
			for(int i = 1; i<=numberOfVertexs;i++) {
				g.addNode((node_data) new Vertex(new Point3D(r.nextInt(),r.nextInt(),0)));
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
			
//			StdDraw.paint(g);
		}
	
	@Test
	public void testAddNode() {
		DGraph dg= new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1));
		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
		Vertex v3=new Vertex(new Point3D(10,20,1),3,30);
		Vertex v4=new Vertex(new Point3D(7,65,1),4,40);
		Vertex v5=new Vertex(new Point3D(80,10,1),5,50);
		dg.addNode(v1);
		dg.addNode(v2);
		dg.addNode(v3);
		dg.addNode(v4);
		if(dg.nodeSize()!=4)
			fail("add node failed");
		dg.addNode(v5);
		if(dg.nodeSize()!=5)
			fail("add node failed");
		dg.removeNode(5);
		if(dg.nodeSize()!=4)
			fail("add node failed");
		dg.addNode(v1);
		if(dg.nodeSize()!=5)
			fail("add node failed");
	}

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
		if(al.isConnected()) {
			fail("isConnected failed");
		}
		dg.connect(v4.getKey(), v5.getKey(), 10);
		al.init(dg);
		if(!al.isConnected()) {
			fail("isConnected failed");
		}
	}

	@Test
	public void testGetV() {
		DGraph dg= new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1),1,10);
		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
		Vertex v3=new Vertex(new Point3D(10,20,1),3,30);
		Vertex v4=new Vertex(new Point3D(7,65,1),4,40);
		Vertex v5=new Vertex(new Point3D(80,10,1),5,50);
		Vertex v6=new Vertex(new Point3D(4, 6),6,34);
		dg.addNode(v1);
		dg.addNode(v2);
		dg.addNode(v3);
		dg.addNode(v4);
		dg.addNode(v5);
		if(dg.getV().size()!=5)
			fail("getV failed");
		dg.addNode(v6);
		if(dg.getV().size()!=6)
			fail("getV failed");

	}


	@Test
	public void testRemoveNode() {
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
		dg.removeNode(1);
		if(dg.nodeSize()!=4)
			fail("remove node failed");
		if(dg.edgeSize()!=2)
			fail("remove node failed");
	}

	@Test
	public void testRemoveEdge() {
		DGraph dg= new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1),1,0);
		Vertex v2=new Vertex(new Point3D(10,80,1),2,0);
		dg.addNode(v1);
		dg.addNode(v2);
		dg.connect(v1.getKey(), v2.getKey(), 10);
		dg.connect(v2.getKey(), v1.getKey(), 10);
		dg.removeEdge(1, 2);
		if(dg.edgeSize()!=1)
			fail("fail remove edge");
		dg.removeEdge(2, 1);
		if(dg.edgeSize()!=0)
			fail("fail remove edge");
	}


	@Test
	public void testEdgeSize() {
		DGraph dg= new DGraph();
		Vertex v1=new Vertex(new Point3D(50,5,1),1,10);
		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
		Vertex v3=new Vertex(new Point3D(10,80,1),3,54);
		dg.addNode(v1);
		dg.addNode(v2);
		dg.addNode(v3);
		dg.connect(v1.getKey(), v2.getKey(), 10);
		dg.connect(v1.getKey(), v3.getKey(), 10);
		dg.connect(v2.getKey(), v3.getKey(), 10);
		dg.connect(v3.getKey(), v1.getKey(), 10);
		if(dg.edgeSize()!=4)
			fail("fail edgesize");
		dg.removeEdge(v1.getKey(), v2.getKey());
		if(dg.edgeSize()!=3)
			fail("fail edgesize");
		dg.removeEdge(v2.getKey(), v3.getKey());
		if(dg.edgeSize()!=2)
			fail("fail edgesize");
		dg.removeEdge(v1.getKey(), v3.getKey());
		if(dg.edgeSize()!=1)
			fail("fail edgesize");
	}


}
