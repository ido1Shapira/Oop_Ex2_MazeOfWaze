package gui;

import java.util.Random;

import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;
/**
 * Every Graph-GUI object is a thread
 * that repaint the graph every 0.5 second if necessary.
 * the tread checkes if there is a need to repaint
 * the graph by chacking the mc field within the graph.
 */
public class Graph_GUI implements Runnable {
	private graph g;
	private int mc;
	/**
	 * draw an empty graph
	 */
	public Graph_GUI() {
		this.g = new DGraph();
		this.mc = 0;
		Thread toPaint = new Thread(this);
		toPaint.start();
		StdDraw.paint(g);
	}
	/**
	 * draw g
	 * @param g a graph
	 */
	public Graph_GUI(graph g) {
		this.g = g;
		this.mc = g.getMC();
		Thread toPaint = new Thread(this);
		toPaint.start();
		StdDraw.paint(g);
	} 
	@Override
	public void run() {
		while(true) {
			synchronized(this) {
				if(this.mc != this.g.getMC()) {
					this.mc = this.g.getMC();
					StdDraw.paint(g);
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

	/**
	 * Test client.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[]args) {
		//draws a random graph with "numberOfVertexs" vertices and "numberOfEdge" edges on random seed "seed".

		/*long startTime = System.currentTimeMillis();*/ //we can check how much time that it took
		Graph_GUI gui = new Graph_GUI(createRandomGraph(5,15,4));
		/*long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");*/

	}
	public static graph createRandomGraph(final int numberOfVertexs, final int numberOfEdge, final int seed) {
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
		return g;
	}
}
