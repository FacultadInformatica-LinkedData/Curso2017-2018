package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontmodelo;
import org.apache.jena.ontology.OntmodeloSpec;
import org.apache.jena.rdf.modelo.modeloFactory;
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
	public static String aux = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "resources/example6.rdf";
		
		// Create an empty modelo
		Ontmodelo modelo = modeloFactory.createOntologymodelo(OntmodeloSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		modelo.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass person = modelo.getOntClass(aux+"Person");
		ExtendedIterator iauxtances = person.listAux();
		
		while (iauxtances.hasNext())
		{
			Individual iauxt = (Individual) iauxtances.next();
			System.out.println("Iauxtance of Person: "+iauxt.getURI());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		
		while (subclasses.hasNext())
		{
			OntClass subclass = (OntClass) subclasses.next();
			System.out.println("Subclass of Person: "+subclass.getURI());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect iauxtances and subclasses. TIP: you need some inference... **
		ExtendedIterator<OntClass> listSubclass = person.listSubClasses();
		ExtendedIterator<Individual> listAux;

		//Imprimiremos las  y las iauxtancias de cada subclase
		while (listSubclass.hasNext()){
	        OntClass classes = (OntClass) listSubclass.next();
	        listAux = (ExtendedIterator<Individual>) classes.listAux();
	        System.out.println("Iauxtances: "+ classes.getURI());
	   		
	   		
	   		while(listAux.hasNext()){
	   			System.out.println("Iauxtances: "+ listAux.next().getURI());
	   		}

			
		}
	
	}
}