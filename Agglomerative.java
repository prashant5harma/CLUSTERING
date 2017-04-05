import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.geom.Point2D;

import java.awt.geom.* ;

public class Agglomerative
{
	static long startTime = System.nanoTime();
	//ArrayList<InterPoints> arrIPforProcessing = new ArrayList<InterPoints>();
	static ArrayList<Cluster> arrClusters = new ArrayList<Cluster>();
	
	private static final String fileNameOutput = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/agglomerativeOutput.txt";
	private static final String fileNameOutputCent = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/agglomerativeOutputCent.txt";
	private static final String fileName = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/input.txt";

	public static void main(String []  args)
	{
		 long startTime = System.nanoTime();
        
		
		try 
		{
			// Reading input File
			FileReader fileReader;
	        String line=null;
			fileReader = new FileReader(fileName);
			
	        BufferedReader br = new BufferedReader(fileReader);
	        int i=0;
	        while((line = br.readLine()) != null) 
	        {
	        		//arrClusters.add(new Cluster(i));
	        	    //Cluster c = arrClusters.get(i);
		        	Cluster c = new Cluster(i);
		        	arrClusters.add(c);
	        	    
	            String[] splits = line.toString().split(" ");
	            
	            Point2D.Double p1 = new Point2D.Double(
	                    Double.parseDouble(splits[0]),
	                    Double.parseDouble(splits[1])) ;
	            c.addpnts(p1);
	           ++i;
	        }
	        br.close();
	        
	        //First Pass -itr iterations
	        for(int itr=0;itr<999;++itr)
	        {
	        		//System.out.println(itr);
		        for(Cluster c : arrClusters)
				{
		        		c.setDmin(Double.POSITIVE_INFINITY);
		        		for(Cluster c2 : arrClusters)
		        		{
		        			if(c.getClusName()!=c2.getClusName())
		        			{
		        				double d = c.getCentroid().distance(c2.getCentroid());
		        				if(d<c.getDmin())
		        				{
		        					c.setDmin(d);
		        					c.setClosestCluster(c2.getClusName());
		        				}
		        			}
		        		}
				}
		        /*// To get Status of Clusters
		        for(Cluster ca : arrClusters)
		        {
		        		ca.getStatus();
		        }
		        */
		        
		        
		        //Finding Closest points
		        int ctmw=-1;// as index will never be -ve.
		        Cluster ctoBeMerged = null;
		        Cluster ctoBeMergedWith = null;
		        double dleast = Double.POSITIVE_INFINITY;
		        
		        for(Cluster c : arrClusters)
		        {
		        		if(c.getClusName()==ctmw)
		        		{
		        			ctoBeMergedWith=c;
		        		}
		        		if(c.getDmin()<dleast)
		        		{   dleast = c.getDmin();
		        		    ctoBeMerged = c ;
		        			ctmw = c.getClosestCluster();
		        		}
		        	}
		        
		        //Merging Clusters
		        for(Point2D.Double p : ctoBeMergedWith.getPntsInCluster() )
				{
		        		//System.out.println("Merging point: " + ctoBeMerged.getPntsInCluster() + " with " + p);
		        		ctoBeMerged.addpnts(p);
				}
		        arrClusters.remove(ctoBeMergedWith);
		        
		        //System.out.println("new cluster : "+ctoBeMerged.getPntsInCluster());
		        /*
		        for(Cluster ya : arrClusters )
		        {
		        	System.out.println("new cluster Array : "+ ya.getPntsInCluster());
		        }*/
	        }
	        //Print to file
	        BufferedWriter bw = null;
			FileWriter fw = null;
			fw = new FileWriter(fileNameOutput);
			bw = new BufferedWriter(fw);
			
			for(Cluster x : arrClusters)
			{
				for(Point2D.Double y : x.getPntsInCluster())
				{
				bw.write(x.getClusName()+" "+y.getX()+" "+y.getY());
				bw.newLine();
				}
			}
			bw.close();
			BufferedWriter bw2 = null;
			FileWriter fw2 = null;
			fw2 = new FileWriter(fileNameOutputCent);
			bw2 = new BufferedWriter(fw2);
			for(Cluster x : arrClusters)
			{
				
				bw2.write(x.getClusName()+" "+x.getCentroid().getX()+" "+x.getCentroid().getY());
				bw2.newLine();
				
			}
			bw2.close();
			long endTime = System.nanoTime();
			System.out.println("Took "+(endTime - startTime) + " ns"); 
			
	        
	        
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERRORRRRRRRRRRRRRRRRRR");
		}

        
	}
	
}
