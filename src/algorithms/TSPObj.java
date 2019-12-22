package algorithms;

public class TSPObj {
	private double weight;
	private String path;
	public TSPObj(double d, String s) {
		weight=d;
		path=s;
	}
	public double getWeight() {
		return this.weight;
	}
	public String getPath() {
		return this.path;
	}
}
