package upm.oeg.wsld.jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;



/**
 * Task 02: Managing statements
 * @author elozano
 * @author isantana
 *
 */
public class Task02
{
	public static String ns = "http://somewhere#";
	public static String foafNS = "http://xmlns.com/foaf/0.1/";
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		// Create an empty model
		Model model = ModelFactory.createDefaultModel();
				
		String fullName = "John Smith";
		String johnURI = ns+"JohnSmith";
		
		// ** TASK 2.1: Create the resource John Smith
		Resource johnSmith = null;

		// Add to johnSmith the datatype property full name (from the VCARD vocabulary)
		
		
		// ** TASK 2.2: Create a new resource for Jane Smith, specifying her full name and email **
		
		
		// ** TASK 2.3: Add Jane as a person who John knows through an object property from the FOAF vocabulary**
		
		
		model.write(System.out, "RDF/XML-ABBREV");
	}
}
