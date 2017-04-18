import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SSE {
	private static final String fileName = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/agglolarge.txt";
	static ArrayList<Cluster> arrClusters = new ArrayList<Cluster>();
    static Point2D.Double[] arrcentroid = new Point2D.Double[4];
	static double tSSE = 0;
	public static void main(String []  args)
	{
        	
		try 
		{
			// Reading input File
			FileReader fileReader;
	        String line=null;
			fileReader = new FileReader(fileName);
			
	        BufferedReader br = new BufferedReader(fileReader);
	        
	        while((line = br.readLine()) != null) 
	        {
	        		//String[] splits = line.toString().split(" ");
	        		String[] splits = line.toString().split("\\s+");
	        		
		        	Cluster c = new Cluster(Integer.parseInt(splits[0]));
		        	arrClusters.add(c);
	        	    Point2D.Double p1 = new Point2D.Double(
	                    Double.parseDouble(splits[1]),
	                    Double.parseDouble(splits[2])) ;
	            c.setPnt(p1);
	          
	        }
	        br.close();
	        
	        //iterate through points to find centroids
	        
	    	for (int i = 0; i<4; ++i)
	    	{
	    		double centx = 0;
		    	double centy = 0;
		    	int totalpts=0;
	    		for (Cluster c : arrClusters)
	    		{
	    			if(c.getClusName()==i)
	    			{
	    				++totalpts;
	    				centx+= c.getPnt().getX();
	    				centy+= c.getPnt().getY();
	    			}
	    		}
	    		//System.out.println(centx/totalpts);
	    		//System.out.println(centy/totalpts);
	    		Point2D.Double p2 = new Point2D.Double( centx/totalpts,centy/totalpts) ;
	    		arrcentroid[i] = p2; 
	    		
	    		
	    	}
	  
	        //SSECalculation
	        
	        
	        for(int i = 0 ; i<4;++i)
	        {
	        		Point2D.Double cent = arrcentroid[i];
		        for(Cluster c : arrClusters)
		        {
		        		if(c.getClusName()==i)
		        		{
			        		tSSE+= (Math.pow(c.getPnt().distance(cent), 2));
			        	}
		        }
	        }
	        
	        System.out.println("WC: " + tSSE);
	        
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERRORRRRRRRRRRRRRRRRRR");
		}
	}
}
	        
