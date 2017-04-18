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
	 
	 Point2D.Double pnt = new Point2D.Double();
	 
	 public Point2D.Double getPnt() {
		return pnt;
	}

	 public void setPnt(Point2D.Double pnt) {
		this.pnt = pnt;
	}



	public Cluster(int i)
	 {
		 clusName = i;
	 }
	 
	 
	
	public int getClusName() {
		return clusName;
	}
	public void setClusName(int clusName) {
		this.clusName = clusName;
	}
	
	
	
}
