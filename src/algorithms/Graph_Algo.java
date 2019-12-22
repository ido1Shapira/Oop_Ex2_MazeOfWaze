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
		int i=1;
		while (this.myGraph.getNode(i) == null) i++;
		node_data mySrc= this.myGraph.getNode(i);
		return this.myGraph.nodeSize()<=this.tagMyChilds(mySrc) 
				&& this.myGraph.nodeSize()<=this.tagMyFathers(mySrc);
	}

	private int tagMyFathers(node_data grandSon) {
		for (Iterator<Integer> iterator = this.NeighborsToVertex.get(grandSon.getKey()).iterator(); iterator.hasNext();) {
			Integer sonKey = (Integer) iterator.next();
			node_data grandpa = this.myGraph.getNode(sonKey);
			grandSon.setTag(2);
			if(grandpa.getTag()==1)
				return 1+tagMyFathers(grandpa) ;
		}
		return 1;
	}

	private int tagMyChilds(node_data father) {
		for (Iterator<Integer> iterator = this.vertexToNeighbors.get(father.getKey()).iterator(); iterator.hasNext();) {
			Integer sonKey = (Integer) iterator.next();
			node_data son = this.myGraph.getNode(sonKey);
			father.setTag(1);
			if(son.getTag()==0)
				return 1+tagMyChilds(son) ;
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
		try {
			this.myGraph.getNode(src).setTag(0);// starting point
			this.myGraph.getNode(src).setInfo(""+src);// starting point
			this.myGraph.getNode(src).setWeight(0);
		}
		catch (NullPointerException e) {
			System.out.println("Vertex "+ src + " does not exist");
		}

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
					node_data son = ((graph) this.myGraph).getNode(sonKey);
					double edgeWeight = ((graph) this.myGraph).getEdge(currentKey, sonKey).getWeight();
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
	private List<node_data> string2list(String path){
		try {
			String [] pathSplit= path.split(" ");
			ArrayList <node_data> list= new ArrayList<node_data>();
			for (int i = 0; i < pathSplit.length; i++) {
				int toAdd= Integer.parseInt(pathSplit[i]);
				list.add(this.myGraph.getNode(toAdd));
			}
			return list;
		}
		catch(Exception e) {
			return null;
		}
	}
	public String ShortestPathString(int src, int dest) {
		this.shortPathGraph(src);
		String path = this.myGraph.getNode(dest).getInfo();
		String[] split= path.split(" ", 2);
		if(split.length==1)
			return "";
		return split[1];
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
			return null;
		}
	}
	public double [] [] drawTable () {
		graph dg= (graph)this.myGraph;
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
				table[i][j]=this.shortestPathDist(out.getKey(), in.getKey());
				j++;
			}
			i++;
		}
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				System.out.print(table[a][b] + "\t \t");
			}
			System.out.println();
		}
		return table;
	}

	public Object [] tsp(ArrayList<node_data> unvisited,  double cost, int currPosKey, ArrayList<node_data> listTillNow) {

		if (unvisited.size()==1) {
			int togoKey=unvisited.get(0).getKey();
			listTillNow.addAll(this.shortestPath(currPosKey, togoKey));
			Object [] ans=new Object [2];
			ans[0]=cost+this.shortestPathDist(currPosKey, togoKey);
			ans[1]=listTillNow;
			return ans;
		}
		else {
			double min=Integer.MAX_VALUE;
			node_data minV= null;
			for (int i = 0; i < unvisited.size(); i++) {
				ArrayList<node_data> unvisitedcopy=new ArrayList<node_data>();
				unvisitedcopy=(ArrayList<node_data>) unvisited.clone();
				ArrayList<node_data> ListCopy=new ArrayList<node_data>();
				ListCopy= (ArrayList<node_data>) listTillNow.clone();
				node_data togo=unvisited.get(i);
				int togoKey=togo.getKey();
				unvisitedcopy.remove(togo);
				double currPathWeight=this.shortestPathDist(currPosKey, togoKey);
				ListCopy.add(togo);
				double thiscost=
						(double) this.tsp(unvisitedcopy, cost+currPathWeight, togoKey, ListCopy)[0];
				if (thiscost<min) {
					min=thiscost;
				}
			}
			return null; //min;
		}
	}
	//	public ArrayList<node_data> Tsp(ArrayList<node_data> unvisited,  double cost, int currPosKey, ArrayList<node_data> listTillNow) {
	//		System.out.println("in tsp");
	//		if (unvisited.size()==1) {
	//			System.out.println("nherv emv");
	//			int togoKey=unvisited.get(0).getKey();
	//			listTillNow.addAll(this.shortestPath(currPosKey, togoKey));
	//			System.out.println("one path to add "+listTillNow);
	//			return listTillNow;
	//		}
	//		else {
	//			double min=Integer.MAX_VALUE;
	//			node_data minV= null;
	//			ArrayList<node_data> ListCopy=new ArrayList<node_data>();
	//			for (int i = 0; i < unvisited.size(); i++) {
	//				node_data togo=unvisited.get(i);
	//				int togoKey=togo.getKey();
	//				ArrayList<node_data> unvisitedcopy=new ArrayList<node_data>();
	//				unvisitedcopy=(ArrayList<node_data>) unvisited.clone();
	//				ListCopy= (ArrayList<node_data>) listTillNow.clone();
	//				unvisitedcopy.remove(togo);
	//				double currEdge=this.shortestPathDist(currPosKey, togoKey);
	//				ListCopy.add(togo);
	//				double thiscost=
	//					this.tsp(unvisitedcopy, cost+currEdge, togoKey, ListCopy);
	//				if (thiscost<min) {
	//					min=thiscost;
	//					minV=togo;
	//				//	this.Tsp(unvisitedcopy, thiscost, currPosKey, listTillNow);
	//				}	
	//			}
	//			listTillNow.addAll(this.shortestPath(currPosKey, minV.getKey()));
	//			System.out.println("minV= "+minV.getKey());
	//		//	System.out.println(this.shortestPath(currPosKey, minV.getKey()));
	//			ListCopy.addAll(this.shortestPath(currPosKey, minV.getKey()));
	//			return ListCopy;
	//		}
	//	}

	public TSPObj mytsp (ArrayList<node_data> unvisited,  double cost, int currPosKey, String tillNow) {
		if(unvisited.size()==1) {
			String s=tillNow+" "+this.ShortestPathString(currPosKey, unvisited.get(0).getKey());
			double d= cost+this.shortestPathDist(currPosKey, unvisited.get(0).getKey());
			TSPObj ans = new TSPObj(d, s);
			return ans;
		}
		else {
			TSPObj ans= new TSPObj(Integer.MAX_VALUE, "");
			for (Iterator iterator = unvisited.iterator(); iterator.hasNext();) {
				node_data nodeToGo = (node_data) iterator.next();
				ArrayList<node_data> listcopy=new ArrayList<node_data>();
				listcopy = (ArrayList<node_data>) unvisited.clone();
				listcopy.remove(nodeToGo);
				int nodeToGoKey= nodeToGo.getKey();
				TSPObj currAns= this.mytsp(listcopy, 
						cost+this.shortestPathDist(currPosKey, nodeToGoKey),nodeToGoKey,
						tillNow+" "+this.ShortestPathString(currPosKey, nodeToGoKey));
				if(currAns.getWeight()<ans.getWeight())
					ans=currAns;
			}
			return ans;
		}
	}


	@Override
	public List<node_data> TSP(List<Integer> targets) {
		ArrayList <node_data> targetNodes= new ArrayList<node_data>();
		for (Iterator it = targets.iterator(); it.hasNext();) {	//Shallow copy from key list to node list
			Integer i= (Integer) it.next();
			targetNodes.add(this.myGraph.getNode(i));
		}
		TSPObj ans= new TSPObj(Integer.MAX_VALUE, "");
		for (Iterator iterator = targetNodes.iterator(); iterator.hasNext();) {
			node_data start = (node_data) iterator.next();
			ArrayList<node_data> listcopy= new ArrayList<node_data>();
			listcopy=(ArrayList<node_data>) targetNodes.clone();
			listcopy.remove(start);
			TSPObj currAns= this.mytsp(listcopy, 0, start.getKey(), ""+start.getKey());
			if(currAns.getWeight()<ans.getWeight())
				ans=currAns;
		}
		System.out.println(ans.getPath());
		System.out.println(ans.getWeight());
		return this.string2list(ans.getPath());
	}
}