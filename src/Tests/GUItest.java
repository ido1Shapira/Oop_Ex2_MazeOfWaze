package Tests;

import java.util.Random;

import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.node_data;
import gui.Graph_GUI;
import utils.Point3D;

public class GUItest {

	public static void main(String[] args) throws InterruptedException {
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

		g.connect(v1.getKey(), v2.getKey(), 10);
		g.connect(v2.getKey(), v3.getKey(), 15);
		g.connect(v3.getKey(), v1.getKey(), 20);
		g.connect(v4.getKey(), v3.getKey(), 20);

		
		Graph_GUI gui = new Graph_GUI(g);
		
        Thread.sleep(1000);
        
        final int numberOfVertexs = 10;
        final int numberOfEdge = 10;
        final int seed = 1;
		Random r = new Random(seed);
        for(int i = 1; i<=numberOfVertexs;i++) {
			g.addNode((node_data) new Vertex(new Point3D(r.nextInt(300),r.nextInt(300),0)));
//	        Thread.sleep(100);
        }
		for(int i = 1; i<=numberOfEdge;i++) {
			int keyv1 = r.nextInt(numberOfVertexs) +1;
			int keyv2;
			do {  
				keyv2 = r.nextInt(numberOfVertexs) +1;
			}while(v1 == v2);
			g.connect(keyv1, keyv2, g.getNode(keyv1).getLocation().distance2D(g.getNode(keyv2).getLocation()));
//	        Thread.sleep(100);
		}
	}

}
