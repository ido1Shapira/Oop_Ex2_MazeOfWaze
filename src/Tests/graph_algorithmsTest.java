//package Tests;
//
//import static org.junit.Assert.*;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.Test;
//import algorithms.Graph_Algo;
//import dataStructure.DGraph;
//import dataStructure.Vertex;
//import dataStructure.graph;
//import dataStructure.node_data;
//import utils.Point3D;
//public class graph_algorithmsTest {
//
//	@Test
//	public void testInitGraph() {
//		DGraph g = new DGraph();
//		Vertex v1=new Vertex(new Point3D(50,5,1),1,0);
//		Vertex v2=new Vertex(new Point3D(10,80,1),2,0);
//		g.addNode(v1);
//		g.connect(v1.getKey(), v2.getKey(), 10);
//		g.connect(v2.getKey(), v1.getKey(), 15);
//		Graph_Algo al= new Graph_Algo();
//		al.init(g);
//		g.addNode(v2);
//		if(!g.equals(al.myGraph))
//			fail("init failed");
//		if(!al.myGraph.getV().equals(g.getV()))
//			fail("init failed");
//	}
//
//	@Test
//	public void testCopy() {
//		DGraph g = new DGraph();
//		graph g2 = new DGraph();
//		Vertex v1=new Vertex(new Point3D(50,5,1),1,0);
//		Vertex v2=new Vertex(new Point3D(10,80,1),2,0);
//		g.addNode(v1);
//		Graph_Algo al= new Graph_Algo();
//		al.init(g);
//		g2 = al.copy();
//		g2.addNode(v2);
//		g2.connect(v1.getKey(), v2.getKey(), 10);
//		g2.connect(v2.getKey(), v1.getKey(), 15);
//		if(g2.nodeSize()==al.myGraph.nodeSize())
//			fail("copy falied");
//	}
//
//	@Test
//	public void testInitSave() {
//		DGraph g = new DGraph();
//		Vertex v1=new Vertex(new Point3D(50,5,1),1,0);
//		Vertex v2=new Vertex(new Point3D(10,80,1),2,0);
//		g.addNode(v1);
//		g.addNode(v2);
//		g.connect(v1.getKey(), v2.getKey(), 10);
//		g.connect(v2.getKey(), v1.getKey(), 15);
//		Graph_Algo al= new Graph_Algo();
//		al.init(g);
//		al.save("myTest.txt");
//		Graph_Algo al2= new Graph_Algo();
//		al2.init("myTest.txt");
//		if(al.myGraph.nodeSize()!=al2.myGraph.nodeSize())
//			fail("init/ save failed");
//		if(al.myGraph.edgeSize()!=al2.myGraph.edgeSize())
//			fail("init/ save failed");
//
//	}
//
//	@Test
//	public void testIsConnected() {
//		DGraph g = new DGraph();
//		Vertex v1=new Vertex(new Point3D(50,5,1),1,10);
//		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
//		Vertex v3=new Vertex(new Point3D(10,20,1),3,30);
//		Vertex v4=new Vertex(new Point3D(7,65,1),4,40);
//		Vertex v5=new Vertex(new Point3D(80,10,1),5,50);
//		g.addNode(v1);
//		g.addNode(v2);
//		g.addNode(v3);
//		g.addNode(v4);
//		g.addNode(v5);
//		g.connect(v1.getKey(), v2.getKey(), 10);
//		g.connect(v2.getKey(), v3.getKey(), 15);
//		g.connect(v3.getKey(), v1.getKey(), 20);
//		g.connect(v5.getKey(), v4.getKey(), 10);
//		g.connect(v4.getKey(), v5.getKey(), 10);
//		Graph_Algo al= new Graph_Algo();
//		al.init(g);
//		if(al.isConnected())
//			fail("isConnected failed");
//		g.connect(v1.getKey(), v4.getKey(), 10);
//		g.connect(v5.getKey(), v2.getKey(), 10);
//		al.init(g);
//		if(!al.isConnected())
//			fail("isConnected failed");
//	}
//
//	@Test
//	public void testShortestPathDist() {
//		DGraph g = new DGraph();
//		Vertex v1=new Vertex(new Point3D(50,5,1),1,10);
//		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
//		Vertex v3=new Vertex(new Point3D(10,20,1),3,30);
//		Vertex v4=new Vertex(new Point3D(7,65,1),4,40);
//		g.addNode(v1);
//		g.addNode(v2);
//		g.addNode(v3);
//		g.addNode(v4);
//		g.connect(v1.getKey(), v2.getKey(), 30);
//		g.connect(v1.getKey(), v3.getKey(), 10);
//		g.connect(v3.getKey(), v4.getKey(), 5);
//		g.connect(v4.getKey(), v2.getKey(), 7);
//		Graph_Algo al= new Graph_Algo();
//		al.init(g);
//		if(al.shortestPathDist(v1.getKey(), v2.getKey())!=22)
//			fail("shortest path failed");
//		if(al.shortestPathDist(v2.getKey(), v1.getKey())!=Integer.MAX_VALUE-1)
//			fail("shortest path failed"); 
//		System.out.println("2 ERROR massages expected:");
//		if(al.shortestPathDist(1, 5)!=-1)
//			fail("shortest path failed");
//		if(al.shortestPathDist(5, 5)!=-1)
//			fail("shortest path failed");
//		if(al.shortestPathDist(4, 4)!=0)
//			fail("shortest path failed");
//	}
//
//	@Test
//	public void testShortestPath() {
//		DGraph g = new DGraph();
//		Vertex v1=new Vertex(new Point3D(50,5,1),1,10);
//		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
//		Vertex v3=new Vertex(new Point3D(10,20,1),3,30);
//		Vertex v4=new Vertex(new Point3D(7,65,1),4,40);
//		g.addNode(v1);
//		g.addNode(v2);
//		g.addNode(v3);
//		g.addNode(v4);
//		g.connect(v1.getKey(), v2.getKey(), 30);
//		g.connect(v1.getKey(), v3.getKey(), 10);
//		g.connect(v3.getKey(), v4.getKey(), 5);
//		g.connect(v4.getKey(), v2.getKey(), 7);
//		Graph_Algo al= new Graph_Algo();
//		al.init(g);
//		if(al.shortestPath(v1.getKey(), v2.getKey()).size()!=4)//v1 -> v3 -> v4 ->v2
//			fail("shortest path failed");
//	}
//
//	@Test
//	public void testTSP() {
//		DGraph g = new DGraph();
//		Vertex v1=new Vertex(new Point3D(50,5,1),1,10);
//		Vertex v2=new Vertex(new Point3D(10,80,1),2,20);
//		Vertex v3=new Vertex(new Point3D(10,20,1),3,30);
//		Vertex v4=new Vertex(new Point3D(7,65,1),4,40);
//		g.addNode(v1);
//		g.addNode(v2);
//		g.addNode(v3);
//		g.addNode(v4);
//		g.connect(v1.getKey(), v2.getKey(), 30);
//		g.connect(v1.getKey(), v3.getKey(), 10);
//		g.connect(v3.getKey(), v4.getKey(), 5);
//		g.connect(v4.getKey(), v2.getKey(), 7);
//		Graph_Algo al= new Graph_Algo();
//		al.init(g);
//		ArrayList<Integer> list= new ArrayList<Integer>();
//		list.add(1); list.add(2);list.add(2);list.add(4);list.add(5);list.add(1);list.add(4);
//		List<node_data> ans= al.TSP(list);
//		String path="";
//		for (int i = 0; i < ans.size(); i++) {
//			path= path+ans.get(i).getKey()+" ";
//		}
//		if(!path.equalsIgnoreCase("1 3 4 2 "))
//			fail("TSP failed");
//	}
//}
package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Vertex;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

class Test_Graph_Algo {
	private Vertex srcNode;
	private Vertex destNode;
	private Vertex nd;
	private Point3D pSrc;
	private Point3D pDest;
	private DGraph graph;
	private Graph_Algo gr;

	@BeforeEach
	void buildGraph(){
		graph=new DGraph();
		this.pSrc=new Point3D(1,1);
		this.pDest=new Point3D(3,3);
		this.srcNode=new Vertex(pSrc);
		this.destNode=new Vertex(pDest);
		graph.addNode(srcNode);
		graph.addNode(destNode);
		graph.connect(srcNode.getKey(), destNode.getKey(), 12);
		this.nd=new Vertex(new Point3D(2,2));
		this.graph.addNode(nd);
		this.graph.connect(destNode.getKey(),nd.getKey(), 3);
		this.graph.connect(nd.getKey(),srcNode.getKey(), 4);
		gr = new Graph_Algo();

	}

	@Test
	void testIsConnected() {
		this.gr.init(graph);
		assertTrue(gr.isConnected());
	}

	@Test
	void testShortestPathAndDist() {

		this.graph.connect(srcNode.getKey(), nd.getKey(), 2);
		this.gr.init(graph);
		ArrayList<node_data>List = new ArrayList<node_data>();
		List=(ArrayList<node_data>) gr.shortestPath(srcNode.getKey(), nd.getKey());
		if (List.size()!=2)
			fail("shortestPath algorithm is not working");
		double dist=gr.shortestPathDist(srcNode.getKey(), nd.getKey());
		if (dist!=2)
			fail("shortestPathDist algorithm is not working");
	}

	@Test
	void testTSP() {
		this.graph.connect(this.srcNode.getKey(), this.nd.getKey(), 2);
		Vertex n  = new Vertex(new Point3D(7,7,7));
		this.graph.addNode(n);
		this.gr.init(graph);
		int key = this.graph.nodeSize();
		List<Integer>list = new ArrayList<Integer>();
		for(int i=1;i<=key;i++) {
			list.add(this.graph.getNode(i).getKey());
		}
		List<node_data>tspList = gr.TSP(list);
		String res="";
		if(tspList!=null) 
			fail("TSP algorithm is not working");
		int key2 = this.graph.nodeSize();
		List<Integer>list2 = new ArrayList<Integer>();
		for(int i=1;i<key2;i++) {
			list2.add(this.graph.getNode(i).getKey());
		}
		List<node_data>tspList2 = gr.TSP(list2);
		for(int i=0;i<tspList2.size();i++) {
			if(i==tspList2.size()-1) {
				res+=""+tspList2.get(i).getKey();
			}
			else {
				res+=""+tspList2.get(i).getKey()+"->";
			}
		}
		if (!res.equals("1->2->3"))
			fail("TSP algorithm is not working");
	}

	@Test
	void testCopy() {
		this.gr.init(graph);
		dataStructure.graph g=new DGraph();
		g=this.gr.copy();
		if(this.graph.getNode(srcNode.getKey())!=g.getNode(srcNode.getKey()))
			fail("copy function is not working");
		if(this.graph.getNode(destNode.getKey())!=g.getNode(destNode.getKey()))
			fail("copy function is not working");
		if(this.graph.getEdge(srcNode.getKey(),destNode.getKey()).getSrc()!=g.getEdge(srcNode.getKey(),destNode.getKey()).getSrc())
			fail("copy function is not working");
		if(this.graph.getEdge(srcNode.getKey(),destNode.getKey()).getDest()!=g.getEdge(srcNode.getKey(),destNode.getKey()).getDest())
			fail("copy function is not working");
	}
	
	
	
	
	
	@Test
	void testSave() {
		
		gr.init(graph);
		gr.save("test.txt");
	
	}
	
}