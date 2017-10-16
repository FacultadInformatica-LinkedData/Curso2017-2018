package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

/**
 * Task 05: Reading and writing ontologies (RDFs)
 * @author elozano
 * @author isantana
 *
 */
public class Task05
{
	public static void main(String args[])
	{
		String filename = "resources/example4.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// ** TASK 5.1: Read the ontology from the given file **
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		// ** TASK 5.2: Write the ontology **
		model.write(System.out, "RDF/XML-ABBREV");
		
	}
}
