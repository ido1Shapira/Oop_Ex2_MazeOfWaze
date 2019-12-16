package Test;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.node_data;
import utils.Point3D;

public class DGraphTest {
	
	public static DGraph g = new DGraph();
	public final static int numberOfVertexs = 100;
	public final static int numberOfEdges = 100;

	@Before
	public static void createGraph() throws Exception {
	
	}
	@Test
	public void testDGraph() {
		for(int i = 1; i<=numberOfVertexs;i++) {
			g.addNode((node_data) new Vertex(new Point3D(i,i+1,i+2),Math.random()*100));
		}
		for(int i = 1; i<=numberOfVertexs;i++) {
			int v1 = (int)Math.random()*numberOfVertexs;
			int v2 = (int)Math.random()*numberOfVertexs;
			g.connect(v1, v2, Math.random()*100);
		}
		int expectedVertexs = numberOfVertexs;
		int actuelVertexs = g.nodeSize();
//		assertEquals(expectedVertexs, actuelVertexs , "number of vertexs suppose to be equels");
		int expectedEdges = numberOfVertexs;
		int actuelEdges = g.edgeSize();
//		assertEquals(expectedEdges, actuelEdges , "number of edges suppose to be equels");
	}
//
//	@Test
//	public void testAddNode() {
//		
//	}
//
//	@Test
//	public void testConnect() {
//		fail("Not yet implemented");
//	}
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
