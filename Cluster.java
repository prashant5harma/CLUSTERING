import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.awt.geom.* ;
import java.io.*;


public class Cluster {
	
	 int clusName ;
	 
	 ArrayList<Point2D.Double> pntsInCluster = new ArrayList<Point2D.Double>();
	 
	 Point2D.Double centroid = new Point2D.Double();
	 
	 double dmin = Double.POSITIVE_INFINITY;
	 
	 
	 
	
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
	
}
