package algorithms;
import dataStructure.*;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	public graph myGraph;

	@Override
	public void init(graph g) {
		myGraph= g;
	}

	@Override
	public void init(String file_name) {
		Gson gson = new Gson();
		try 
		{
			FileReader reader = new FileReader(file_name+".json");
			graph gr = (graph) gson.fromJson(reader,DGraph.class);
			this.myGraph=gr;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	@Override
	public graph copy() {
		graph gcopy = new DGraph();
		//vertex deep copy
		for (Iterator<node_data> iterator = this.myGraph.getV().iterator(); iterator.hasNext();) {
			node_data v = (node_data) iterator.next();
			gcopy.addNode(v);
			//edge deep copy
			for (Iterator<edge_data> iterator2 = this.myGraph.getE(v.getKey()).iterator(); iterator.hasNext();) {
				edge_data e = (edge_data) iterator2.next();
				gcopy.connect(e.getSrc(),e.getDest(),e.getWeight());
			}
		}
		return gcopy;
	}

	@Override
	public void save(String file_name) {
		//Make JSON!!
		Gson gson = new Gson();
		String json = gson.toJson(this.myGraph);
		System.out.println(json);

		//Write JSON to file
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file_name+".json"));
			pw.write(json);
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

	}
	// A function used by DFS 
	void DFSUtil(int v,boolean visited[],DGraph dg) 
	{ 
		// Mark the current node as visited and print it 
		visited[v] = true; 
		//System.out.print(v+" "); 

		// Recur for all the vertices adjacent to this vertex 
		Iterator<Integer> i = dg.getVertexTohisNeighbors().get(v).listIterator(); 
		while (i.hasNext()) 
		{ 
			int n = i.next(); 
			if (!visited[n]) 
				DFSUtil(n, visited,dg); 
		} 
	} 

	// The function to do DFS traversal. It uses recursive DFSUtil() 
	void DFS(int v,graph g) 
	{ 
		// Mark all the vertices as not visited(set as 
		// false by default in java) 
		boolean visited[] = new boolean[g.nodeSize()]; 
		DGraph dg = null;
		if(g instanceof DGraph) {
			dg = (DGraph) g;
		}
		// Call the recursive helper function to print DFS traversal 
		DFSUtil(v, visited,dg); 
	} 

	@Override
	public boolean isConnected() {
		if(!this.checkLegal()) return false;
		DGraph dg = null;
		if(this.myGraph instanceof DGraph) {
			dg = (DGraph) this.copy();

		}
		this.DFS(1,dg);
		return false;
	}

	private boolean checkLegal () {
		int count = 0;
		DGraph dg = null;
		if(this.myGraph instanceof DGraph) {
			dg = (DGraph) this.copy();
		}
		for (Iterator<node_data> init = this.myGraph.getV().iterator(); init.hasNext();) {
			node_data v = (node_data) init.next();
			v.setTag(0);
		}
		for (Iterator<node_data> iterator = this.myGraph.getV().iterator(); iterator.hasNext();) {
			node_data v = (node_data) iterator.next();
			if(!dg.getVertexTohisNeighbors().isEmpty()) {
				count++;
				for (Iterator<Integer> iterator2 = dg.getVertexTohisNeighbors().get(v.getKey()).iterator(); iterator2.hasNext();) {
					node_data vb = dg.getNode(((Integer) iterator2.next()));
					vb.setTag(1);
				}
			}
		}
		if(count != dg.nodeSize()) return false;
		for (Iterator<node_data> iterator = this.myGraph.getV().iterator(); iterator.hasNext();) {
			node_data v = (node_data) iterator.next();
			if(v.getTag() == 0) return false;
		}

		return true;

	}
	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}
}
