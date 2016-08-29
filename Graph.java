/** Graph Class
 * @author Finn
 * > Graph methods
 */

public class Graph 
{
      private boolean adjacencyMatrix[][];
      private int vertexCount;
 
      public Graph(int vC) 
      {
          vertexCount = vC; //Number of vertices
          adjacencyMatrix = new boolean[vertexCount][vertexCount]; //Initializes adj. matrix
      }

      /**
       * > Add edge between vertex i & vertex j
       */
      public void addEdge(int i, int j) 
      {
          if (i >= 0 && i < vertexCount && j > 0 && j < vertexCount) 
          {
        	  adjacencyMatrix[i][j] = true;
              adjacencyMatrix[j][i] = true;
          }
      }

      /**
       * > Remove edge between vertex i & vertex j
       */
      public void removeEdge(int i, int j) 
      {
          if (i >= 0 && i < vertexCount && j > 0 && j < vertexCount) 
          {
        	  adjacencyMatrix[i][j] = false;
              adjacencyMatrix[j][i] = false;
          }
      }
 
      /**
       * > Checks for edge between vertex i & vertex j
       */
      public boolean isEdge(int i, int j) 
      {
    	  if (i >= 0 && i < vertexCount && j >= 0 && j < vertexCount)
    		  return adjacencyMatrix[i][j];
          else
        	  return false;
      }
      
      /**
       * > Checks if vertex i & vertex j are two hop neighbors
       */
      public boolean areTwoHopsNeighbors(int i, int j) 
  	  {
    	  if(i==j || adjacencyMatrix[i][j]==true) 
    		  return false;
  		  else
  			  for(int k=0; k<vertexCount; k++)
  			  {
  				  if(adjacencyMatrix[i][k]==true && adjacencyMatrix[j][k]==true)
  					  return true;
  			  }
  		  return false;	
  	  }
      
      /**
       * > Counts friends of vertex j
       */
      public int countFriends(int j)
      {
    	  int count = 0;
    	  for(int i=0; i<vertexCount; i++)
    	  {
    		  if(adjacencyMatrix[j][i] == true)
    			  count++;    		  
    	  }
    	  return count;
      }
      
      /**
       * > Displays adj. matrix
       */
      public void displayMatrix()
      {
    	  System.out.println("   0     1    2    3    4    5     6    7    8    9");
    	  for(int i=0; i<vertexCount; i++)
    	  {
    		  System.out.print(i+" ");
    		  for(int j=0; j<vertexCount; j++)
    			  System.out.print(adjacencyMatrix[i][j]+" ");
    		  System.out.println();
    	  } 	
      }
}
