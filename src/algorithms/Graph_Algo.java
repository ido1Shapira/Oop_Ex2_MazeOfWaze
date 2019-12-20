package algorithms;
import dataStructure.*;
import utils.Point3D;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	private graph myGraph;
	private HashMap<Integer,HashSet<Integer>> vertexToNeighbors; // v1--->V
	private HashMap<Integer,HashSet<Integer>> NeighborsToVertex; // V--->v1

	@Override
	public void init(graph g) {
		myGraph = g;
		this.vertexToNeighbors = new HashMap<Integer,HashSet<Integer>>();
		for (Iterator<node_data> Nodeiter = this.myGraph.getV().iterator(); Nodeiter.hasNext();) {
			node_data n = (node_data) Nodeiter.next();
			if(this.myGraph.getE(n.getKey()) != null) {
				for (Iterator<edge_data> Edgeiter = this.myGraph.getE(n.getKey()).iterator(); Edgeiter.hasNext();) {
					edge_data edge = (edge_data) Edgeiter.next();
					try {
						this.vertexToNeighbors.get(edge.getSrc()).add(edge.getDest());
					}
					catch(NullPointerException e){
						this.vertexToNeighbors.put(edge.getSrc(),(new HashSet<Integer>()));			
						this.vertexToNeighbors.get(edge.getSrc()).add(edge.getDest());
					}
				}
			}
		}
		this.NeighborsToVertex = new HashMap<Integer,HashSet<Integer>>();
		for (Iterator<node_data> Nodeiter = this.myGraph.getV().iterator(); Nodeiter.hasNext();) {
			node_data n = (node_data) Nodeiter.next();
			if(this.myGraph.getE(n.getKey()) != null) {
				for (Iterator<edge_data> Edgeiter = this.myGraph.getE(n.getKey()).iterator(); Edgeiter.hasNext();) {
					edge_data edge = (edge_data) Edgeiter.next();
					try {
						this.NeighborsToVertex.get(edge.getDest()).add(edge.getSrc());
					}
					catch(NullPointerException e){
						this.NeighborsToVertex.put(edge.getDest(),(new HashSet<Integer>()));			
						this.NeighborsToVertex.get(edge.getDest()).add(edge.getSrc());
					}
				}
			}
		}
	}

	@Override
	public void init(String file_name) {
		try
		{    
			FileInputStream file = new FileInputStream(!file_name.contains(".txt")? file_name+".txt" : file_name); 
			ObjectInputStream in = new ObjectInputStream(file); 
			this.myGraph = (graph)in.readObject(); 
			in.close(); 
			file.close(); 
		} 
		catch(IOException ex) 
		{ 
			ex.printStackTrace();
		} 
		catch(ClassNotFoundException ex) 
		{ 
			System.out.println("ClassNotFoundException is caught"); 
		} 
	}
	@Override
	public void save(String file_name) {
		try
		{    
			FileOutputStream file = new FileOutputStream(!file_name.contains(".txt")? file_name+".txt" : file_name); 
			ObjectOutputStream out = new ObjectOutputStream(file); 
			out.writeObject((graph) this.myGraph); 
			out.close(); 
			file.close(); 
		}   
		catch(IOException ex) 
		{
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			System.out.println("save- IOException is caught"); 
		}
	}
	@Override
	public graph copy() {
		Random r = new Random();
		String file_name = "file"+Math.abs(r.nextInt());
		this.save(file_name);
		Graph_Algo ga=new Graph_Algo();
		ga.init(file_name);
		File file = new File(file_name+".txt"); 
		file.delete(); 
		return ga.myGraph;
	}

	@Override
	public boolean isConnected() {
		infoTagWeightReset();
		if(!this.checkLegal()) return false;
		DGraph dg = null;
		if(this.myGraph instanceof DGraph) {
			dg=(DGraph)this.myGraph;
			int i=1;
			while (dg.getNode(i) == null) i++;
			node_data mySrc= dg.getNode(i);
			return dg.nodeSize()<=this.tagMyChilds(mySrc, dg) 
					&& dg.nodeSize()<=this.tagMyFathers(mySrc, dg);
		}
		return false;
	}

	private int tagMyFathers(node_data grandSon,DGraph dg) {
		for (Iterator<Integer> iterator = this.NeighborsToVertex.get(grandSon.getKey()).iterator(); iterator.hasNext();) {
			Integer sonKey = (Integer) iterator.next();
			node_data grandpa = dg.getNode(sonKey);
			grandSon.setTag(2);
			if(grandpa.getTag()==1)
				return 1+tagMyFathers(grandpa, dg) ;
		}
		return 1;
	}

	private int tagMyChilds(node_data father,DGraph dg) {
		for (Iterator<Integer> iterator = this.vertexToNeighbors.get(father.getKey()).iterator(); iterator.hasNext();) {
			Integer sonKey = (Integer) iterator.next();
			node_data son = dg.getNode(sonKey);
			father.setTag(1);
			if(son.getTag()==0)
				return 1+tagMyChilds(son, dg) ;
		}
		return 1;
	}
	private boolean checkLegal() {
		int count = 0;
		for (Iterator<node_data> it = this.myGraph.getV().iterator(); it.hasNext();) {
			node_data v = (node_data) it.next();
			if(!this.vertexToNeighbors.containsKey(v.getKey())) 
				count++;
			if(!this.NeighborsToVertex.containsKey(v.getKey())) 
				count++;
		}      
		if(count == 0) return true;
		return false;
	}
	private void infoTagWeightReset() {
		for (Iterator<node_data> init = this.myGraph.getV().iterator(); init.hasNext();) {
			node_data v = (node_data) init.next();
			v.setInfo("");
			v.setTag(0);
			v.setWeight(Integer.MAX_VALUE-1);
		}
	}
	private void shortPathGraph(int src) {
		infoTagWeightReset();
		this.myGraph.getNode(src).setTag(0);// starting point
		this.myGraph.getNode(src).setInfo(""+src);// starting point
		this.myGraph.getNode(src).setWeight(0);
		HashMap<Integer, node_data> hashCopy = new HashMap<Integer, node_data>();
		for (Iterator<node_data> init = this.myGraph.getV().iterator(); init.hasNext();) {
			node_data v = (node_data) init.next();
			hashCopy.put(v.getKey(), v);		//init copy list for all vertices we havent visited yet
		}
		for(int i=0; i<this.myGraph.nodeSize();i++) {
			node_data current =  this.findMin(hashCopy);
			int currentKey = current.getKey();
			hashCopy.remove(currentKey);
			if( this.vertexToNeighbors.get(currentKey) !=null) {
				for (Iterator<Integer> iterator = this.vertexToNeighbors.get(currentKey).iterator(); iterator.hasNext();) {
					Integer sonKey = (Integer) iterator.next();
					node_data son = ((DGraph) this.myGraph).getNode(sonKey);
					double edgeWeight = ((DGraph) this.myGraph).getEdge(currentKey, sonKey).getWeight();
					if(son.getWeight()>(current.getWeight()+edgeWeight) && son.getTag()==0) {
						son.setWeight(current.getWeight()+edgeWeight);
						son.setInfo(current.getInfo()+" "+sonKey);
					}
				}
			}
			current.setTag(1);
		}
	}

	public node_data findMin (HashMap<Integer, node_data> hashNotVisited) {
		node_data min=new Vertex(new Point3D(0,0,0), Integer.MAX_VALUE, Integer.MAX_VALUE);
		for (Iterator <node_data> it = hashNotVisited.values().iterator(); it.hasNext();) {
			node_data now = (node_data) it.next();
			if(now.getTag()==0 && now.getWeight() < min.getWeight())
				min = now;
		}
		return min;
	}
	@Override
	public double shortestPathDist(int src, int dest) {
		this.shortPathGraph(src);
		return this.myGraph.getNode(dest).getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest)  {
		try {
			this.shortPathGraph(src);
			String path = this.myGraph.getNode(dest).getInfo();
			String [] pathSplit= path.split(" ");
			ArrayList <node_data> list= new ArrayList<node_data>();
			for (int i = 0; i < pathSplit.length; i++) {
				int toAdd= Integer.parseInt(pathSplit[i]);
				list.add(this.myGraph.getNode(toAdd));
			}
			return list;
		}
		catch(Exception e) {
			System.out.println("src or dest does not exist");
			return null;
		}
	}
	public void drawTable () {
		DGraph dg= (DGraph)this.myGraph;
		int i=0;
		int j=0;
		int n= dg.nodeSize();
		node_data [] nodesByOrder= new node_data[n];
		double [][] table = new double[n][n];
		for (Iterator<node_data> iterator = dg.getV().iterator(); iterator.hasNext();) {
			node_data out = (node_data) iterator.next();
			nodesByOrder[i]=out;
			j=0;
			for (Iterator<node_data> it = dg.getV().iterator(); it.hasNext();) {
				node_data in = (node_data) it.next();
				//System.out.println(i +"   " +  j);
				table[i][j]=this.shortestPathDist(out.getKey(), in.getKey());
				j++;
		}
			i++;
	}
		for (int a = 0; a < n; a++) {
		    for (int b = 0; b < n; b++) {
		        System.out.print(table[a][b] + " ");
		    }
		    System.out.println();
		}
}
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}
}