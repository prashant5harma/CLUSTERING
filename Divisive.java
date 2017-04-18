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

public class Divisive
{
	static long startTime = System.nanoTime();
	static ArrayList<Cluster> arrClusters = new ArrayList<Cluster>();
	private static final String fileName = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/small.txt";
	private static final String fileNameOutput = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/divisivetest.txt";
	// Total number of clusters formed
	static int cntOfClusters = 0 ;
	
	public static void main(String []  args)
	{
		 long startTime = System.nanoTime();
		
		 try 
		{
			// Reading input File
			FileReader fileReader;
	        String line = null;
			fileReader = new FileReader(fileName);
		    BufferedReader br = new BufferedReader(fileReader);
	        
		    while((line = br.readLine()) != null) 
	        {
	        		
		        	Cluster c = new Cluster(0);
		        	arrClusters.add(c);
	        	    
	            String[] splits = line.toString().split(" ");
	            
	            Point2D.Double p1 = new Point2D.Double(
	                    Double.parseDouble(splits[0]),
	                    Double.parseDouble(splits[1])) ;
	            c.setPnt(p1);
	           
	        }
	        br.close();
	        
	       //First Pass -itr iterations --- Finding dmax for all points
	        for(int itr=0;itr<4;++itr)
	        {
	        		System.out.println("Iteration number : "+itr);
		        for(Cluster c : arrClusters)
				{
		        		c.setDmax(0.0);
		        		for(Cluster c2 : arrClusters)
		        		{
		        			
		        			if(c!=c2 && c.getClusName()==c2.getClusName())
		        			{
		        				double d = c.getPnt().distance(c2.getPnt());
		        				if(d>c.getDmax())
		        				{
		        					c.setDmax(d);
		        					c.setfarthestCluster(c2);
		        				}
		        			}
		        		}
				 }
		        
		        
		     // Sorting
		        Collections.sort(arrClusters, new Comparator<Cluster>() {
		                @Override
		                public int compare(Cluster cl1, Cluster cl2)
		                {
		                		return  cl1.compareTo(cl2);
		                }
		            });
		    
		        System.out.println("Status after sorting : ");
		        for(Cluster ca : arrClusters)
		        {
		        		ca.getStatus();
		        }
		        
		        
		        
		        //Finding Farthest points
		        
		        ArrayList<Cluster> arrsplitList = new ArrayList<Cluster>();
		        ArrayList<Cluster> arrsplitListWith = new ArrayList<Cluster>();
		        int prevcntOfClusters = cntOfClusters;
		        //For all the existing clusters
		        for(int l = 0;l<=prevcntOfClusters;++l)
		        {
		        	 for(Cluster c : arrClusters)
				        {
		        		 		//if the point is in the same cluster and Dmax
					        	if(c.getClusName()==l && c.getDmax()!=0.0)
				        		{
					        		if(!arrsplitList.contains(c) && !arrsplitListWith.contains(c))
					        		{
					        			if (arrsplitList.contains(c.getfarthestCluster()) && !arrsplitListWith.contains(c.getfarthestCluster()))
					        			{
					        				arrsplitListWith.add(c);
					        				c.setDmax(0.0);
					        				c.setfarthestCluster(null);
					        			}
					        			else if(arrsplitListWith.contains(c.getfarthestCluster()) && !arrsplitList.contains(c.getfarthestCluster()))
					        			{
					        				arrsplitList.add(c);
					        				c.setDmax(0.0);
					        				c.setfarthestCluster(null);
					        			}
					        			else if(!arrsplitList.contains(c.getfarthestCluster()) && !arrsplitListWith.contains(c.getfarthestCluster()))
					        			{
					        				arrsplitList.add(c);
					        				arrsplitListWith.add(c.getfarthestCluster());
					        				c.setDmax(0.0);
					        				c.setfarthestCluster(null);
					        				
					        			}
					        		}
					        		else if(!arrsplitList.contains(c.getfarthestCluster()) && !arrsplitListWith.contains(c.getfarthestCluster()))
					        		{
					        			if (arrsplitList.contains(c))
					        			{
					        				arrsplitListWith.add(c.getfarthestCluster());
					        				c.setDmax(0.0);
					        				c.setfarthestCluster(null);
					        			}
					        			else if(arrsplitListWith.contains(c))
					        			{
					        				arrsplitList.add(c.getfarthestCluster());
					        				c.setDmax(0.0);
					        				c.setfarthestCluster(null);
					        			}
					        		}
					        		c.setDmax(0.0);
					        		c.setfarthestCluster(null);
				        		}
				        }
		        	// create a new clustername and add one list to that cluster
		        		if(!arrsplitList.isEmpty())
		        		{
			        	    ++cntOfClusters;//new cluster
			        		for(Cluster cu: arrsplitList)
			        		{
			        			cu.setClusName(cntOfClusters);
			        			
			        		}
			        		arrsplitList.clear();
			        		arrsplitListWith.clear();
		        		}
		        	 
		        	 /*
		        	 	System.out.println("after new clusters formed");
		        	 for(Cluster ca : arrClusters)
				        {
				        		ca.getStatus();
				        }
		        	 */
		      
		        }
		        
		    }
	        
	        System.out.println(cntOfClusters);
	        //Print to file
	        BufferedWriter bw = null;
			FileWriter fw = null;
			fw = new FileWriter(fileNameOutput);
			bw = new BufferedWriter(fw);
			
			for(Cluster x : arrClusters)
			{
				
				bw.write(x.getClusName()+" "+x.getPnt().getX()+" "+x.getPnt().getY());
				bw.newLine();
				
			}
			bw.close();
			
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
