import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.awt.geom.Point2D;
import java.awt.geom.* ;

public class Agglomerative
{
	static long startTime = System.nanoTime();
	static ArrayList<Cluster> arrClusters = new ArrayList<Cluster>();
	private static final String fileNameOutput = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/agglolarge.txt";
	private static final String fileNameOutputCent = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/agglolarge-centroids.txt";
	private static final String fileName = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/large.txt";
    static int cntOfClusters = 0;
    
	public static void main(String []  args)
	{
		 long startTime = System.nanoTime();
		 System.out.println("in");
		try 
		{
			// Reading input File
			FileReader fileReader;
	        String line = null;
			fileReader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fileReader);
	        while((line = br.readLine()) != null) 
	        {
	        		Cluster c = new Cluster(cntOfClusters);
		        	arrClusters.add(c);
	        	    String[] splits = line.toString().split(" ");
	            Point2D.Double p1 = new Point2D.Double(
	                    Double.parseDouble(splits[0]),
	                    Double.parseDouble(splits[1])) ;
	            c.addpnts(p1);
	           ++cntOfClusters;
	        }
	        br.close();
	        
	        //Finding closest points
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
		    
		    // Sorting based on dmin   
	        Collections.sort(arrClusters, new Comparator<Cluster>() {
                @Override
                public int compare(Cluster cl1, Cluster cl2)
                {
                		return  cl1.compareTo(cl2);
                }
            });
		    
		        
	        //Number of iterations -itr iterations
	        for(int itr=0;itr<49996;++itr)
	        {
		        	System.out.println("Iter :  " + itr);
		        	if(itr!=0)
		        	{
			        	Collections.sort(arrClusters, new Comparator<Cluster>() {
			                @Override
			                public int compare(Cluster cl1, Cluster cl2)
			                {
			                		return  cl1.compareTo(cl2);
			                }
			            });
		        	}
		        
		        int ctmw=-1;
		        Cluster ctoBeMerged = null;
		        Cluster ctoBeMergedWith = null;
		        ctoBeMerged = arrClusters.get(0);
		        ctmw = ctoBeMerged.getClosestCluster();
		        for(Cluster c : arrClusters)
		        {
		        		if(c.getClusName()==ctmw)
		        		{
		        			ctoBeMergedWith = c;
		        			 break;
		        		}
		        }
		        
		        
		        //Merging Clusters
		        for(Point2D.Double p : ctoBeMergedWith.getPntsInCluster() )
				{
		        		ctoBeMerged.addpnts(p);
				}
		        arrClusters.remove(ctoBeMergedWith);
		        
		        
		        //Finding dmin for the new cluster
		        ctoBeMerged.setDmin(Double.POSITIVE_INFINITY);
	        		for(Cluster c4 : arrClusters)
	        		{
	        			if(ctoBeMerged.getClusName()!=c4.getClusName())
	        			{
	        				double d2 = ctoBeMerged.getCentroid().distance(c4.getCentroid());
	        				if(d2<ctoBeMerged.getDmin())
	        				{
	        					ctoBeMerged.setDmin(d2);
	        					ctoBeMerged.setClosestCluster(c4.getClusName());
	        				}
	        			}
	        		}
				
	        		for(Cluster c5 : arrClusters)
	        		{
	        			if(ctoBeMerged.getClusName() == c5.getClosestCluster() || ctoBeMergedWith.getClusName() == c5.getClosestCluster())
	        			{
	        				c5.setDmin(Double.POSITIVE_INFINITY);
	        				for(Cluster c6 : arrClusters)
		    	        		{
		    	        			if(c5.getClusName()!=c6.getClusName())
		    	        			{
		    	        				double d5 = c5.getCentroid().distance(c6.getCentroid());
		    	        				if(d5<ctoBeMerged.getDmin())
		    	        				{
		    	        					c5.setDmin(d5);
		    	        					c5.setClosestCluster(c6.getClusName());
		    	        				}
		    	        			}
		    	        		}
	        			}
	        		}
		        
		       
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
			e.printStackTrace();
			System.out.println("ERRORRRRRRRRRRRRRRRRRR");
		}

        
	}
	
}
