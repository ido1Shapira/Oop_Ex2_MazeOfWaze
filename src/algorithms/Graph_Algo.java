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
			graph gr = gson.fromJson(reader,DGraph.class);
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

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
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
