<<<<<<< HEAD:Assignment3/buhorl/src/main/java/upm/oeg/wsld/jena/Task07.java
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

		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator instances = person.listInstances();
		
		while (instances.hasNext())
		{
			Individual inst = (Individual) instances.next();
			System.out.println("Instance of Person: "+inst.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		
		while (subclasses.hasNext())
		{
			OntClass subclass = (OntClass) subclasses.next();
			System.out.println("Subclass of Person: "+subclass.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect
		// instances and subclasses. TIP: you need some inference... **

		ExtendedIterator inst_and_subc = model.listClasses();
		while (inst_and_subc.hasNext()){
			OntClass cl = (OntClass) inst_and_subc.next();
			ExtendedIterator iinst = cl.listInstances();
			while (iinst.hasNext()) {
				Individual inst = (Individual) iinst.next();
				System.out.println("Instance of " + cl.getURI() + ": "+ inst.getURI());
			}
			ExtendedIterator isubc = cl.listSubClasses();
			while (isubc.hasNext())
			{
				OntClass subc = (OntClass) isubc.next();
				System.out.println("Subclass of " + cl.getURI() + ": " +subc.getURI());
			}
		}


	}
}
=======
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
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator instances = person.listInstances();
		
		while (instances.hasNext())
		{
			Individual inst = (Individual) instances.next();
			System.out.println("Instance of Person: "+inst.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		
		while (subclasses.hasNext())
		{
			OntClass subclass = (OntClass) subclasses.next();
			System.out.println("Subclass of Person: "+subclass.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		ExtendedIterator<OntClass> listSubclass = person.listSubClasses();
		ExtendedIterator<Individual> listInstances;

		//Imprimiremos las subclases y las instancias de cada subclase
		while (listSubclass.hasNext()){
	        OntClass classes = (OntClass) listSubclass.next();
	        listInstances = (ExtendedIterator<Individual>) classes.listInstances();
	        System.out.println("Instances: "+ classes.getURI());
	   		
	   		
	   		while(listInstances.hasNext()){
	   			System.out.println("Instances: "+ listInstances.next().getURI());
	   		}

			
		}
	
	}
}
>>>>>>> dbee99cf1c10ee002e5fb986970aa20635091478:Assignment3/src/main/java/upm/oeg/wsld/jena/Task07.java
