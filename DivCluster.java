import java.io.BufferedReader;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.geom.* ;
import java.io.*;


public class Cluster implements Comparable<Cluster>{
	
	 int clusName ;
	 
	 //ArrayList<Point2D.Double> pntsInCluster = new ArrayList<Point2D.Double>();
	 
	 Point2D.Double pnt = new Point2D.Double();
	 
	 public Point2D.Double getPnt() {
		return pnt;
	}

	public void setPnt(Point2D.Double pnt) {
		this.pnt = pnt;
	}

	double dmax = 0;
	 
	 Cluster farthestCluster = null;//As the index will never be -ve.
	 
	 public Cluster getfarthestCluster() {
		return farthestCluster;
	}

	public void setfarthestCluster(Cluster fc) {
		this.farthestCluster = fc;
	}

	public double getDmax() {
		return dmax;
	}

	public void setDmax(double dmax) {
		this.dmax = dmax;
	}

	public Cluster(int i)
	 {
		 clusName = i;
	 }
	 
	/*
	 public void addpnts(Point2D.Double p)
	 {
		 pntsInCluster.add(p);
		 updatecentroid();
		 
	 }*/
	 ///may not be used
	 /*
	 public void updatecentroid()
	 {
		 double ttlx=0;
		 double ttly=0;
		 for(Point2D.Double p: pntsInCluster)
		 {
			 ttlx+= p.x;
			 ttly+= p.y;
		 }
			 centroid.x = (ttlx/pntsInCluster.size());
			 centroid.y = (ttly/pntsInCluster.size());
	}
	 */
	public int getClusName() {
		return clusName;
	}
	
	public void setClusName(int clusName) {
		this.clusName = clusName;
	}
	
	/*
	public ArrayList<Point2D.Double> getPntsInCluster() {
		return pntsInCluster;
	}
	
	public Point2D.Double getCentroid() {
		return centroid;
	}*/
	
	public void getStatus()
	{
		System.out.println(clusName +" "+ pnt +" "+ dmax + " "+ farthestCluster);
	}
	public void updateCluster(int n)
	{
		clusName = n;
	}

	@Override
	public int compareTo(Cluster cl2) {
		final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;

	    //this optimization is usually worthwhile, and can
	    //always be added
	    if (this.getDmax() == cl2.getDmax()) return EQUAL;
	    else if (this.getDmax()  < cl2.getDmax()) return AFTER;
	    else if (this.getDmax() > cl2.getDmax()) return BEFORE;
	    else return 0;
	}


}
