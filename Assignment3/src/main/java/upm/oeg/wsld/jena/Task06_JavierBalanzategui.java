package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;

/**
 * Task 06: Modifying ontologies (RDFs)
 * @author elozano
 * @author isantana
 *
 */
public class Task06
{
	public static String ns = "http://somewhere#";
	public static String foafNS = "http://xmlns.com/foaf/0.1/";
	public static String foafEmailURI = foafNS+"email";
	public static String foafKnowsURI = foafNS+"knows";
	public static String stringTypeURI = "http://www.w3.org/2001/XMLSchema#string";
	public static String vcardRdf= "http://somewhere#http://www.w3.org/2001/vcard-rdf/3.0/";
	public static void main(String args[])
	{
		String filename = "resources/example5.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		//model.write(System.out);
		// Create a new class named "Researcher"
		OntClass researcher = model.createClass(ns+"Researcher");
		
		// ** TASK 6.1: Create a new class named "University" **
		OntClass university = model.createClass(ns+"University");
		
		// ** TASK 6.2: Add "Researcher" as a subclass of "Person" **
		researcher.addProperty(RDFS.subClassOf , FOAF.Person );
        
		// ** TASK 6.3: Create a new property named "worksIn" **
		Property worksin = model.createProperty(foafNS+"worksIn");
		
		// ** TASK 6.4: Create a new individual of Researcher named "Jane Smith" **
		Resource resear = model.getResource(researcher.getURI());
		Resource Jane = model.createIndividual(ns+"JaneSmith", resear);
		
		// ** TASK 6.5: Add to the individual JaneSmith the fullName, given and family names **
		Property pFN = model.getProperty(vcardRdf+"FN");
		Property pGiven = model.getProperty(vcardRdf+"Given");
		Property pFamily = model.getProperty(vcardRdf+"Family");

		Jane.addLiteral(pFN, "Jane Smith");
		Jane.addLiteral(pGiven, "Jane");
		Jane.addLiteral(pFamily, "Smith");
		
		// ** TASK 6.6: Add UPM as the university where John Smith works **
			//Crear universidad
		Resource uni_poli = model.getResource(university.getURI());
		model.createIndividual(ns+"UPM",uni_poli);
		
			//Agregar prop a JohnSmith
		Resource John = model.getResource(ns+"JohnSmith");
		Resource upm = model.getResource(ns+"UPM");
		John.addProperty(worksin ,upm);

		
		model.write(System.out, "RDF/XML-ABBREV");
	}
}
