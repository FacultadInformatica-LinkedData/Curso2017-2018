package upm.oeg.wsld.jena;

import java.io.InputStream;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

/**
 * Task 06: Modifying ontologies (RDFs)

 */

public class Task06 {
	public static String ns = "http://somewhere#";
	public static String foafNS = "http://xmlns.com/foaf/0.1/";
	public static String foafEmailURI = foafNS+"email";
	public static String foafKnowsURI = foafNS+"knows";
	public static String stringTypeURI = "http://www.w3.org/2001/XMLSchema#string";
	
	public static void main (String[]args){
		
		String filename = "resources/example5.rdf";		
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		InputStream in = FileManager.get().open(filename);
			
		if (in == null)
			throw new IllegalArgumentException("File: " + filename + " not found");
			
		model.read(in, null);
			
		OntClass researcher = model.createClass(ns+"Researcher");
				
		OntClass university = model.createClass(ns+"University");
		
		model.getOntClass(ns+"Person").addSubClass(researcher);

		Property worksIn = model.createProperty(ns + "worksIn");
		
		
		Individual janeSmith = researcher.createIndividual(ns + "Jane Smith");
		
		
		janeSmith.addProperty(VCARD.FN, "Jane Smith");
		janeSmith.addProperty(VCARD.Given, "Jane");
		janeSmith.addProperty(VCARD.Family, "Smith");
		
		
		Individual upm = university.createIndividual(ns + "UPM");
		Individual johnSmith = model.getIndividual(ns + "JohnSmith");
		johnSmith.addProperty(worksIn, upm);

		model.write(System.out, "RDF/XML-ABBREV");
	}
}
