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
	//ArrayList<InterPoints> arrIPforProcessing = new ArrayList<InterPoints>();
	static ArrayList<Cluster> arrClusters = new ArrayList<Cluster>();
	
	private static final String fileNameOutput = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/agglomerativeOutput.txt";
	//private static final String fileNameOutputCent = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/agglomerativeOutputCent.txt";
	private static final String fileName = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/input.txt";
	static int cntOfClusters = 0 ;
	
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
	        
	        for(Cluster u:arrClusters)
	        {
	        		u.getStatus();
	        }
	        
	        
	        //First Pass -itr iterations --- Finding dmax for all points
	        for(int itr=0;itr<1;++itr)
	        {
	        		System.out.println(itr);
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
		        System.out.println("Status after distance calculation:");
		        //To get Status of Clusters
		        for(Cluster ca : arrClusters)
		        {
		        		ca.getStatus();
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
		        //Cluster ctoBeSplit = null;
		       // Cluster ctoBeSplitout = null;
		        //double dmost =0.0;
		        ArrayList<Cluster> arrsplitList = new ArrayList<Cluster>();
		        ArrayList<Cluster> arrsplitListWith = new ArrayList<Cluster>();
		        int prevcntOfClusters = cntOfClusters;
		        //For all the existing clusters
		        for(int l = 0;l<=prevcntOfClusters;++l)
		        {
		        	 for(Cluster c : arrClusters)
				        {
					        	if(c.getClusName()==l && c.getDmax()!=0.0)
				        		{
					        		if(!arrsplitList.contains(c) && !arrsplitListWith.contains(c))
					        		{
					        			if (arrsplitList.contains(c.getfarthestCluster()))
					        			{
					        				arrsplitListWith.add(c);
					        				c.setDmax(0.0);
					        				c.setfarthestCluster(null);
					        			}
					        			else if(arrsplitListWith.contains(c.getfarthestCluster()))
					        			{
					        				arrsplitList.add(c);
					        				c.setDmax(0.0);
					        				c.setfarthestCluster(null);
					        			}
					        			else
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
					        			}
					        			else if(arrsplitListWith.contains(c))
					        			{
					        				arrsplitList.add(c.getfarthestCluster());
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
			        			//cu.setDmax(0.0);
			        			//cu.setfarthestCluster(null);
			        		}
			        		arrsplitList.clear();
			        		arrsplitListWith.clear();
		        		}
		        	 
		        	 
		        	 	System.out.println("after new clusters formed");
		        	 for(Cluster ca : arrClusters)
				        {
				        		ca.getStatus();
				        }
		        	 
		        	 /*
		        	 
		        		System.out.println("In Cluster no. "+l);
		        		do{// till all the dMax are 0
		        	
		        			//Find the two point with the max distance apart
				        for(Cluster c : arrClusters)
				        {
					        	if(c.getClusName()==l && c != ctoBeSplitout )
				        		{
						        	if(c.getDmax()>dmost)
					        		{   
					        			dmost = c.getDmax();
					        		    ctoBeSplit = c ;
					        		    ctoBeSplitout = c.getfarthestCluster();
					        		}
				        		}
					        
				        	  } // Now we have the 2 points with max distance
				        System.out.println("2 points:  "+ctoBeSplit.getPnt()+ ctoBeSplitout.getPnt()+" "+ dmost);
				        
				        //Add the points to be split in array
				        if(!arrsplitListWith.contains(ctoBeSplit))
				        {
				        		arrsplitList.add(ctoBeSplit);
				        		ctoBeSplit.setDmax(0.0);
				        		ctoBeSplitout.setDmax(0.0);
				        }
				        arrsplitListWith.add(ctoBeSplitout);
				        System.out.println();
				        if(dmost!=0.0)
				        {
				        dmost=-1.0;// distance is never negative
				        }
		        		} while (dmost!= 0.0);
		        		
		        		// create a new clustername and add one list to that cluster
		        		++cntOfClusters;//new cluster
		        		for(Cluster cu: arrsplitList)
		        		{
		        			cu.setClusName(cntOfClusters);
		        		}
		        		arrsplitList.clear();
		        		arrsplitListWith.clear();
		        		*/
		        }
		        
		    }
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
			/*
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
			*/
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
