/*September 21st, 2015*/
package wikiSearch;
import java.util.Scanner;
import org.jsoup.HttpStatusException;
import java.io.IOException;

/**
 * @author Zachary Corse
 */

public class WikiSearch {	
	
	
	/**
	 * Formats the query provided by the user by replacing 
	 * spaces with underscores.
	 * 
	 * Example: userQuery = "Mad Max" would be turned into "Mad_Max"
	 * 
	 * @param userQuery query provided by user to be formatted
	 * @return returns string formatted with underscores
	 */
	public static String QueryFormat(String userQuery){			
		String replacedSpaces = userQuery.replace(' ', '_');
		return replacedSpaces;
	}
	
	
	
	
	/**
	 * Creates a parser. Then runs the function "fetchFirstParagraph" using said parser.
	 * "fetchFirstParagrah" uses formattedQuery to search wikipedia.
	 * If an article is not found (returning a 404 error) using formattedQuery, returns "Not found."
	 * If an article is found, it is displayed to the user.
	 * 
	 * If wikipedia returns multiple articles for which it believes may be
	 * suitable for the users query, the program will return something similar to
	 * "MAD or Mad may refer to :" (for the query "mad")
	 * 
	 * @param formattedQuery query that has been formatted by QueryFormat that will be used to search Wikipedia
	 * @throws IOException
	 */
	public static void WikipediaSearch(String formattedQuery) throws IOException{
		WikipediaParser parser = new WikipediaParser("en");
	    String firstParagraph = null;
	    
		try{ firstParagraph = parser.fetchFirstParagraph(formattedQuery); } 
		catch(HttpStatusException e){
			if(e.getStatusCode() == 404){ firstParagraph = "Not found."; }
		}

	    System.out.println(firstParagraph);
	}
	
	
	
	/**
	 * Asks the user for a query. Until the user inputs a non blank query,
	 * the program will continue to ask for a query.
	 *  
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String query = null;
		
		//Ask for query. Until an input is given that is
		//not blank, the program will keep asking for query
		while(true){
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter a (non blank) query: ");
			query = input.nextLine();									//Set 'query' as the users input
			
			if (!(query.trim().isEmpty())) {							//if the query is NOT blank, continue
				break;													//this makes sure the users input is not blank
			}
		}
		
		//Format the users query to make sure spaces are replaced with underscores
		String formattedQuery = QueryFormat(query);
		
		//Take the users already formatted query and search using the parser
		WikipediaSearch(formattedQuery);
		
	}
}
