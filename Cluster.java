import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.geom.* ;
import java.io.*;


public class Cluster implements Comparable<Cluster> {
	
	 int clusName ;
	 
	 ArrayList<Point2D.Double> pntsInCluster = new ArrayList<Point2D.Double>();
	 
	 Point2D.Double centroid = new Point2D.Double();
	 
	 double dmin = Double.POSITIVE_INFINITY;
	 
	 int closestCluster = -1;//As the index will never be -ve.
	 
	 public int getClosestCluster() {
		return closestCluster;
	}

	public void setClosestCluster(int closestCluster) {
		this.closestCluster = closestCluster;
	}

	public double getDmin() {
		return dmin;
	}

	public void setDmin(double dmin) {
		this.dmin = dmin;
	}

	public Cluster(int i)
	 {
		 clusName = i;
	 }
	 
	 public void addpnts(Point2D.Double p)
	 {
		 pntsInCluster.add(p);
		 updatecentroid();
		 
	 }
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
	 
	public int getClusName() {
		return clusName;
	}
	public void setClusName(int clusName) {
		this.clusName = clusName;
	}
	public ArrayList<Point2D.Double> getPntsInCluster() {
		return pntsInCluster;
	}
	public Point2D.Double getCentroid() {
		return centroid;
	}
	public void getStatus()
	{
		System.out.println(clusName +" "+ pntsInCluster +" "+ dmin + " " + closestCluster + " "+ centroid);
	}

	public int compareTo(Cluster cl2) {
		
		final int BEFORE = -1;
	    final int EQUAL = 0;
	    final int AFTER = 1;

	    if (this.getDmin() == cl2.getDmin()) return EQUAL;
	    else if (this.getDmin()  < cl2.getDmin()) return BEFORE;
	    else if (this.getDmin() > cl2.getDmin()) return AFTER;
	    else return 0;
	}
}
