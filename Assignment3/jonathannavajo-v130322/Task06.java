package ontologyapi;
import java.io.InputStream;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;
/**
 * Task 06: Modifying ontologies (RDFs)
 * @author elozano
 * @author isantana
 *
 */
public class Task06
{
	public static String aux = "http://somewhere#";
	public static String foafPr = "http://xmlaux.com/foaf/0.1/";
	public static String foafEm = foafPr+"email";
	public static String foafKn = foafPr+"knows";
	public static String stringURI = "http://www.w3.org/2001/XMLSchema#string";
	public static void main(String args[])
	{
		String filename = "example5.rdf";
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
		// Read the RDF/XML file
		model.read(in, null);
		// Create a new class named "Researcher"
		OntClass researcher = model.createClass(aux + "Researcher");
		// ** TASK 6.1: Create a new class named "University" **
		OntClass university = model.createClass(aux + "University");
		// ** TASK 6.2: Add "Researcher" as a subclass of "Person" **
		model.getOntClass(aux + "Person").addSubClass(researcher);
		// ** TASK 6.3: Create a new property named "worksIn" **
		Property worksIn = model.createProperty(aux + "worksIn");
		// ** TASK 6.4: Create a new individual of Researcher named "Jane Smith" **
		Individual janeSmith = model.createIndividual(aux + "JaneSmith", researcher);
		// ** TASK 6.5: Add to the individual JaneSmith the fullName, given and family names **
		janeSmith.addLiteral(VCARD.FN, "Jane Smith");
		janeSmith.addLiteral(VCARD.Given, "Jane");
		janeSmith.addLiteral(VCARD.Family, "Smith");
		// ** TASK 6.6: Add UPM as the university where John Smith works **
		Individual upm = model.createIndividual(aux + "UPM", university);
		model.getIndividual(aux + "Johauxmith").addProperty(worksIn, upm);
		model.write(System.out, "RDF/XML-ABBREV");
	}
}
