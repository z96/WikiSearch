/*September 21st, 2015*/
package wikiSearch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;


public class WikipediaParser {
	  private final String baseUrl; 

	  
	  /**
	   * @param lang allows for choosing different languages. Hardcoded to english ("en") in WikiSearch.java
	   */
	  
	  public WikipediaParser(String lang) {
	    this.baseUrl = String.format("http://%s.wikipedia.org/wiki/", lang);
	  }  
	  
	  
	  
	  /**
	   * 
	   * Used to connect to Wikipedia using the users query
	   * and find the first paragraph of the article found using 
	   * said query.
	   * Once this first paragraph is found, the function fetchFirstParagraph
	   * returns it for use in text form.
	   * 
	   * @param article The article to be searched for on Wikipedia. Given by user, then formatted for use in url
	   * @return returns the text of the first paragraph found in article
	   * @throws IOException
	   */
	  
	  public String fetchFirstParagraph(String article) throws IOException {
		    String url = baseUrl + article;			  								//baseUrl = http://en.wikipedia.org/wiki/<article>		    
		    Document doc = Jsoup.connect(url).get();  								//connects to given URL, grabs html, and places into a document
		    Elements paragraphs = doc.select(".mw-content-ltr p");					//selects mediawiki paragraphs and places them into 'paragraphs'

		    Element firstParagraph = paragraphs.first();							//grabs the only desired paragraph (the first paragraph)
		    return firstParagraph.text();											//and returns it allow us to print it to terminal
		  }
	  
}