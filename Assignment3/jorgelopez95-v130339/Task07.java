package ontologyapi;

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
 *
 */
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
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator<Individual> instances_it = (ExtendedIterator<Individual>) person.listInstances();

		System.out.println("");
		System.out.println("** List of all individual of Person **");
		while(instances_it.hasNext()){
			Individual list_ind = instances_it.next();
			System.out.println("Person instance: " + list_ind.getURI());
		}

		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> subclasses_it = person.listSubClasses();

		System.out.println("");
		System.out.println("** List of all subclasses of Person **");
		while(subclasses_it.hasNext()){
			OntClass list_sub = subclasses_it.next();
			System.out.println("Person subclass: " + list_sub.getURI());
		}


		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		OntClass person_inf = inference.getOntClass(ns+"Person");

		System.out.println("");
		System.out.println("** Direct and indirect instances and subclasses of Person **");
		ExtendedIterator instances =  person_inf.listInstances();
		ExtendedIterator<OntClass> subclasses = person_inf.listSubClasses();

		while(instances.hasNext()){
			Individual dir_inst = (Individual) instances.next();
			

			while (subclasses.hasNext()){
				OntClass ind_inst = subclasses.next();
				System.out.println("Indirect subclasses: " + ind_inst.getURI());
			}
			
			System.out.println("Direct instances: " + dir_inst.getURI());
		}

	}
}
