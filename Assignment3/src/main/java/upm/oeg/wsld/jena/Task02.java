package upm.oeg.wsld.jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;


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
		Resource johnSmith = model.createResource(johnURI);

		// Add to johnSmith the datatype property full name (from the VCARD vocabulary)
		johnSmith.addProperty(VCARD.FN, fullName);
		
		// ** TASK 2.2: Create a new resource for Jane Smith, specifying her full name and email **
		Resource janeSmith = model.createResource(ns+"Jane Smith");
		janeSmith.addLiteral(VCARD.FN, "Jane Smith");
		janeSmith.addLiteral(VCARD.FN, "jane_smith@email.com");

		// ** TASK 2.3: Add Jane as a person who John knows through an object property from the FOAF vocabulary**
		Property knows = model.createProperty(foafNS+"knows");
		johnSmith.addProperty(knows, janeSmith);
		
		model.write(System.out, "RDF/XML-ABBREV");
	}
}
