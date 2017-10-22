package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

/**
 * Task 03: Statement-based query
 * @author elozano
 * @author isantana
 *
 */
public class Task03
{
	public static String ns = "http://somewhere#";
	public static String foafNS = "http://xmlns.com/foaf/0.1/";
	public static String foafEmailURI = foafNS+"email";
	public static String foafKnowsURI = foafNS+"knows";
	
	public static void main(String args[])
	{
		String filename = "resources/example3.rdf";
		
		// Create an empty model
		Model model = ModelFactory.createDefaultModel();
				
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);

		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");

		// Read the RDF/XML file
		model.read(in, null);
		
		// List all the resources with the property "vcard:FN"
		ResIterator rIter = model.listSubjectsWithProperty(VCARD.FN);
		
		while (rIter.hasNext())
		{
		    Resource r = rIter.nextResource();
		    System.out.println("Subject: "+r.getURI());
		}
		
		// ** TASK 3.1: List all the resources with the property "vcard:FN" and their full names **
		
		
		// ** TASK 3.2: Query all the resources with the family name "Smith" **
		
		
		// ** TASK 3.3: Query all the resources with an email  **
		
		
		// ** TASK 3.4 (advanced): Query all the subjects knowing "Jane Smith" and list their given names  **
		
		
		
	}
}
