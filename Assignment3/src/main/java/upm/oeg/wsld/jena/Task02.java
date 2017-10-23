package upm.oeg.wsld.jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
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
		Property vcardfn = model.createProperty("http://www.w3.org/2001/vcard-rdf/3.0#FN");
		johnSmith.addLiteral(vcardfn, fullName);
		
		System.out.println("vcard:"+VCARD.FN.toString());
		
		// ** TASK 2.2: Create a new resource for Jane Smith, specifying her full name and email **
		Resource janeSmith = model.createResource(ns+"JaneSmith");
		janeSmith.addLiteral(VCARD.FN, "Jane Smith");
		janeSmith.addLiteral(VCARD.EMAIL, "jSmith@somewhere.com");
		
		// ** TASK 2.3: Add Jane as a person who John knows through an object property from the FOAF vocabulary**
//				Property knows = model.createProperty(foafNS+"knows");		
//				johnSmith.addProperty(knows, janeSmith);
		
		johnSmith.addProperty(FOAF.knows, janeSmith);
		
		model.write(System.out, "RDF/XML-ABBREV");
	}
}
