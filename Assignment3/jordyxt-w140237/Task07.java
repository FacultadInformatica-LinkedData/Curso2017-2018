
import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

public class Task07{
	public static String ns = "http://somewhere#";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String args[]){
		
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
		while (instances.hasNext()){
			Individual inst = (Individual) instances.next();
			System.out.println("Instance of Person: "+inst.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();	
		while (subclasses.hasNext()){
			OntClass subclass = (OntClass) subclasses.next();
			System.out.println("Subclass of Person: "+subclass.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel modelinf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF,model);
		OntClass personinf = modelinf.getOntClass(ns + "Person");
		ExtendedIterator<OntClass> listSubclasses = personinf.listSubClasses();
		ExtendedIterator<Individual> listInstances = (ExtendedIterator<Individual>) personinf.listInstances();
		System.out.println("Class: "+ personinf.getURI());
		while(listInstances.hasNext()){
   			System.out.println("Instance: "+ listInstances.next().getURI());
   		}
		//Imprimiremos las subclases y las instancias de cada subclase
		while (listSubclasses.hasNext()){
	        OntClass classes = (OntClass) listSubclasses.next();
	        listInstances = (ExtendedIterator<Individual>) classes.listInstances();
	        System.out.println("SubClass: "+ classes.getURI());
	   		while(listInstances.hasNext()){
	   			System.out.println("Instance: "+ listInstances.next().getURI());
	   		}
		}
			
	}
}
