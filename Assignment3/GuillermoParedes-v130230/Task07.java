package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;



public class Task07 {
	
	public static String ns = "http://somewhere#";
	
	public static void main(String[] args) {
	    
		// TODO Auto-generated method stub
		String filename = "example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
						
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
		
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
					
		// Read the RDF/XML file
		model.read(in, null);

		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass person =model.getOntClass(ns+"Person");
		ExtendedIterator <Individual> iterator1 = model.listIndividuals(person);
		
		while(iterator1.hasNext()){
			System.out.println(iterator1.next());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> iterator2 = person.listSubClasses();
		
		while(iterator2.hasNext()){
			System.out.println(iterator2.next());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. **
		
		// ** TIP: you need some inference... **
	}

}
