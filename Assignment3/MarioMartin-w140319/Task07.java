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
 *
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
		System.out.println("\n\nTASK 7.1:LIST ALL INDIVIDUALS OF PERSON\n");
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator instances = person.listInstances();

		while (instances.hasNext())
		{
			Individual inst = (Individual) instances.next();
			System.out.println("Instance of Person: "+inst.getURI());
		}

		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\n\nTASK 7.2:LIST ALL SUBCLASSES OF PERSON\n");
		ExtendedIterator subclasses = person.listSubClasses();

		while (subclasses.hasNext())
		{
			OntClass subclass = (OntClass) subclasses.next();
			System.out.println("Subclass of Person: "+subclass.getURI());
		}

		
		// ** TASK 7.3: Make the necessary changes to get as well indirect
				// instances and subclasses. TIP: you need some inference... **
				System.out.println("\n\nTASK 7.3:LIST INDIRECT\n");
				OntModel modeliNF =ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model);
				OntClass pF = modeliNF.getOntClass(ns+"Person");
				ExtendedIterator inference = pF.listInstances();
				while (inference.hasNext()) {
					Individual next_inf = (Individual) inference.next();
					System.out.println("Instance of Person: " +next_inf.getURI());
				}
				ExtendedIterator <OntClass> inference2 = pF.listSubClasses();
				while (inference2.hasNext()) {
					OntClass next = inference2.next();
					System.out.println("Subclass of Person:" + next.getURI());

				}
		}
}
