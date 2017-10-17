package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

/**
 * Task 00: Testing Jena
 * 
 * This app is designed as an small test to check that Jena
 * is working properly. It add one triple to a model and prints
 * it. It then uses an iterator to retrieve a literal ("Hola Mundo!)
 * and prints it on the screen.
 * 
 * @author elozano
 * @author isantana
 * 
 */
public class Task00
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		
		// Create an empty model
		Model model = ModelFactory.createDefaultModel();
		
		//create an statement
		Resource app = model.createResource("http://example.org/App/this");
		Property says = model.createProperty("http://example.org/App/says");
		RDFNode hm = model.createLiteral("Hola Mundo!");
		//Add the statement to the model
		model.add(app,says, hm);
		
		// Write it to standard out
		model.write(System.out, "N-QUADS");
		
		//List all the objects of a property
		NodeIterator it = model.listObjectsOfProperty(says);
		
		while (it.hasNext())
		{
		    RDFNode node = it.nextNode();
		    System.out.println(node.toString());
		}
		
		//This should print the following: 
		//    <http://example.org/App/this> <http://example.org/App/says> "Hola Mundo!" .
		//    Hola Mundo!
		
		
	}
}
