package Tests;

import dataStructure.DGraph;
import dataStructure.Vertex;
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
		Thread t1 = new Thread(gui);
		t1.start();
        Thread.sleep(2000);
        
		g.connect(v1.getKey(), v4.getKey(), 20);
		g.addNode(v5);
		g.connect(v5.getKey(), v4.getKey(), 20);
		g.connect(v4.getKey(), v5.getKey(), 20);


	}

}
