/** FriendGraph Class
 * @author Finn
 * > Main method
 * > Parses input file
 * > Creates vertex[] of Users
 * > Adds edges to adjacency matrix
 * > Search for most popular user
 * > Search for non-friends with >2 friend in common
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FriendshipGraph 
{

	private static Graph graph;
	private static User[] vertex = new User[10];
	private static int count = 0;
	
	public static void main(String[] args) 
	{
		System.out.println("Friendship Graph");
		System.out.println("---------------------");
		String filename = "C:/Users/Finn/workspace/FriendshipGraph/src/userFile.txt"; //Input file
		parseFile(filename);
		//Change vertex[#] to desired vertex
		mostPopularUser(vertex[9]);
		commonFriends(vertex[0]); 
		
	}
	
	/**
     * > Parse input file
     * > Creates vertex[] of Users
     * > Adds edges to adjacency matrix
     */
	public static void parseFile(String filename)
	{
		String line;
		String[] friends;
		String[] id;
		try
	    {    
            BufferedReader in = new BufferedReader(new FileReader(filename));
            if (!in.ready())
                throw new IOException();
            while ((line = in.readLine()) != null) //Creates vertex[] of Users
            {
            	line.trim();
            	id = line.split(" ");
            	User person = new User(id[0]);
            	vertex[count] = person;
            	count++;
            }
			in.close();
			graph = new Graph(count);
			FileReader fr2 = new FileReader(filename); 
			BufferedReader br2 = new BufferedReader(fr2);
			
			while ((line = br2.readLine()) != null) // Adds edges to adj. matrix
			{
				friends = line.split(" ");
				String personId = friends[0];
				int vertexId = 0;
				for(int i=0; i<count; i++) //Finds vertex[] index of personId
				{
					if(vertex[i].getId().equals(personId))
					{
						vertexId = i;
						break;
					}
				}
				for(int i=1; i<friends.length; i++)
				{
					String friendId = friends[i];
					int friendVxId = 0;
					for(int j=0; j<count; j++)
					{
						if(vertex[j].getId().equals(friendId))
						{
							friendVxId = j;
							break;
						}
					}
					graph.addEdge(vertexId, friendVxId);
				}
			}
            in.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
	}
	
	/**
     * > Finds the most popular user among the inputted user's friends' friends 
     */
	public static void mostPopularUser(User u)
	{
		int vertexId = 0;
		int friendCount = 0;
		int highestCount = 0;
		String highestCountId = "";
		for(int i=0; i<count; i++) //Finds vertex[] index of inputted User
		{
			if(u == vertex[i])
			{
				vertexId = i;
				break;
			}
		}
		for(int j=0; j<count; j++)
		{
			if(graph.areTwoHopsNeighbors(vertexId, j))
			{
				friendCount = graph.countFriends(j);
				if(friendCount == highestCount)
				{
					highestCountId += ", " + vertex[j].getId();
				}
				if(friendCount>highestCount)
				{
					highestCount = friendCount;
					highestCountId = vertex[j].getId();
				}
			}
		}
		System.out.println("Most popular user (from " + u.getId() + "): " + highestCountId);	
	}
	
	/**
     * > Finds non-friends of inputted user who have >2 friends in common with inputted user
     */
	public static void commonFriends(User u)
	{
		System.out.print("Friends in common with user " + u.getId() + ": ");
		int vertexId = 0;
		int commonCount = 0;
		for(int i=0; i<count; i++) //Finds vertex[] index of inputted User
		{
			if(u == vertex[i])
			{
				vertexId = i;
				break;
			}
		}
		for(int i=0; i<count; i++)
		{
			commonCount = 0;
			if(i != vertexId) //Not checking itself
			{
				for(int j=0; j<count; j++)
				{
					if(graph.isEdge(vertexId, j) == true && graph.isEdge(i,j) == true) //If friends match
					{
						commonCount++; //Increment count
					}
				}
				if(commonCount > 2 && graph.isEdge(vertexId,i) == false) //If >2 friends in common & not a friend of inputted user
				{
					System.out.print(vertex[i].getId()+" ");
				}
			}
		}
	}
}
