package ontologyapi;

import java.io.InputStream;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.Individual;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
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
		System.out.println("TASK 7.1: ");
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator<Individual> itIP = model.listIndividuals(person);
		
		while(itIP.hasNext()){
			Individual ind = itIP.next();
			System.out.println(ind.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("TASK 7.2: ");
		ExtendedIterator<OntClass> itSP = person.listSubClasses();
		
		while(itSP.hasNext()){
			OntClass subC = itSP.next();
			System.out.println(subC.getURI());
		}
	}
}
