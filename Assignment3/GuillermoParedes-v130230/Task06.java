package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

public class Task06 {

	public static String ns = "http://somewhere#";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		OntClass researcher = model.createClass(ns+"Researcher");
		
		
		// ** Task 6.1: Create a new class name "University" **
		OntClass university = model.createClass(ns+"University");
		
		// ** Task 6.2: Add "Researcher" as a subclass of "Person" **
		model.createClass(ns+"Person").addSubClass(researcher);
		
		// ** Task 6.3: Create a new property named "worksIn" **
		OntProperty worksIn = model.createOntProperty(ns+"worksIn");
		
		// ** Task 6.4: Create a new individual of Researcher name "Jane Smith" **
		Individual janeSmith= model.createIndividual(ns+"JaneSmith", researcher);
		
		// ** Task 6.5: Add to the individual JaneSmith the fullName, given and family names **
		janeSmith.addLiteral(VCARD.FN,"Jane Smith");
		janeSmith.addLiteral(VCARD.Given, "Jane");
		janeSmith.addLiteral(VCARD.Family, "Smith");

		
		// ** Task 6.6: Add UPM as the university where John Smith works **
		Individual upm = model.createIndividual(ns+"UPM", university);
		Individual johnSmith = model.getIndividual(ns+"JohnSmith");
		model.write(System.out, "RDF/XML-ABBREV");
	}	
	

}
