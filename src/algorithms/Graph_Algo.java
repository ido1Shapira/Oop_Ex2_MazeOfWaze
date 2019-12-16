package algorithms;
import dataStructure.*;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	public DGraph myGraph;

	@Override
	public void init(graph g) {
		myGraph=(DGraph) g;
	}

	@Override
	public void init(String file_name) {
		Gson gson = new Gson();
		try 
		{
			FileReader reader = new FileReader(file_name+".json");
			DGraph gr = gson.fromJson(reader,DGraph.class);
			this.myGraph=gr;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

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

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
