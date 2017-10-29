package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
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
	
	@SuppressWarnings("unchecked")
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
		ExtendedIterator<? extends OntResource> instances = person.listInstances();		
		while (instances.hasNext()){
			Individual inst = (Individual) instances.next();
			System.out.println("Instancia de 'Person': "+inst.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator<OntClass> subclasses = person.listSubClasses();		
		while (subclasses.hasNext()){
			OntClass subclass = (OntClass) subclasses.next();
			System.out.println("Subclase de 'Person': "+subclass.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		ExtendedIterator<OntClass> listaSubclases = person.listSubClasses();
		while (listaSubclases.hasNext()){
	        OntClass clases = (OntClass) listaSubclases.next();
	        ExtendedIterator<Individual> listaInstancias = (ExtendedIterator<Individual>) clases.listInstances();
	        System.out.println("Subclase: "+ clases.getURI());	   			   		
	   		while(listaInstancias.hasNext()){
	   			System.out.println("Instancia: "+ listaInstancias.next().getURI());
	   		}			
		}	
	}
}
