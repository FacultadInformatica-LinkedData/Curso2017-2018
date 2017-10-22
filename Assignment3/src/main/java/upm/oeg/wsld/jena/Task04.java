package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

/**
 * Task 04: Graph querying
 * @author elozano
 * @author isantana
 *
 */
public class Task04
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
		String queryString = 
				"PREFIX vcard: <" + VCARD.getURI() + "> " +
				"SELECT ?Subject "+
				"WHERE { ?Subject vcard:FN ?FullName.} ";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		ResultSet results = qexec.execSelect() ;
		
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource subj = (Resource) binding.get("Subject");
		    System.out.println("Subject: "+subj.getURI());
		}
		
		// ** TASK 4.1: List all the resources with the property "vcard:FN" and their full names **
		
		
		// ** TASK 4.2: Query all the resources with the family name "Smith" **
		
		
		// ** TASK 4.3: Query all the resources with an email  **
		
		
		// ** TASK 4.4 (advanced): Query all the subjects knowing "Jane Smith" and list their given names  **

		
	}
}
