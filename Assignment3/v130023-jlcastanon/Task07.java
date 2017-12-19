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
 *
 */
public class Task07 {
	public static String ns = "http://somewhere#";

	public static void main(String args[]) {
		
		String filename = "resources/example6.rdf";
		
	
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
				
		InputStream in = FileManager.get().open(filename);
			
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
			
		model.read(in, null);
		
		System.out.println("----------Task7.1----------");
		
		ExtendedIterator<Individual> it = model.listIndividuals();
		OntClass person = model.getOntClass(ns + "Person");
				
		while (it.hasNext()) {
			Individual ind = it.next();
			if (ind.hasOntClass(person))
				System.out.println("|-- Individual: " + ind.getURI());			
		}
		
		System.out.println("----------Task7.2----------");
		
		ExtendedIterator<OntClass> it2 = person.listSubClasses();
		
		while (it2.hasNext()) {
			OntClass ind2 = it2.next();
			System.out.println("|-- Subclass: " + ind2.getURI());
		}
		
		System.out.println("----------Task7.3----------");
		
		OntModel modelo = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF,model);
		OntClass persona = modelo.getOntClass(ns+"Person");
		ExtendedIterator<OntClass> it3 = persona.listSubClasses();
		ExtendedIterator<Individual> it4 = modelo.listIndividuals(persona);
		while (it3.hasNext()) {
			OntClass ind3 = it3.next();
			System.out.println("|-- Subclasses: " + ind3.getURI());
		}
		
		while (it4.hasNext()) {
			Individual indiv_instance = (Individual) it4.next();
			System.out.println("|-- Instances: " + indiv_instance.getURI());
		}

	}
}