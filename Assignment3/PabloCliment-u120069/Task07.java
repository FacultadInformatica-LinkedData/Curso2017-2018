package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author elozano
 * @author isantana
 * @author PabloCliment
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "resources/example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator exIter = person.listInstances();
		System.out.println("List all individuals of Person");
		while (exIter.hasNext()){
			Individual ind = (Individual) exIter.next();
			System.out.println(ind.getLocalName());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> exIter2 = person.listSubClasses();
		System.out.println("\nList all subclasses of Person");
		while (exIter2.hasNext()){
			OntClass subclass = (OntClass) exIter2.next();
			System.out.println(subclass.getLocalName());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass person2 = model2.getOntClass(ns+"Person");
		
		System.out.println("\nIndividuals of Person (Indirect):");
		ExtendedIterator exIter3 = person2.listInstances();
		while(exIter3.hasNext()){
			Individual ind = (Individual) exIter3.next();
			System.out.println(ind.getLocalName());
		}
		
		ExtendedIterator exIter4 = person2.listSubClasses();
		System.out.println("\nSubclasses of Person (Indirect):");
		while(exIter4.hasNext()){
			OntClass ont = (OntClass) exIter4.next();
			System.out.println(ont.getLocalName());
		}
	}
}
