import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SSE {
	private static final String fileName = "/Users/prashantsharma/Documents/CLUSTERINGPROJECT/Programs/data/agglomerativeOutput.txt";
	static ArrayList<Cluster> arrClusters = new ArrayList<Cluster>();
	
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
	        		String[] splits = line.toString().split(" ");
		        	Cluster c = new Cluster(Integer.parseInt(splits[0]));
		        	arrClusters.add(c);
	        	    Point2D.Double p1 = new Point2D.Double(
	                    Double.parseDouble(splits[1]),
	                    Double.parseDouble(splits[2])) ;
	            c.addpnts(p1);
	          
	        }
	        br.close();
	        
	        
	        
	        //SSECalculation
	        double tSSE = 0;
	        for(Cluster c : arrClusters)
	        {
	        		Point2D.Double cent = c.getCentroid();
	        		for(Point2D.Double p : c.getPntsInCluster())
	        		{
	        			tSSE+= (Math.pow(p.distance(cent), 2));
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
	        
