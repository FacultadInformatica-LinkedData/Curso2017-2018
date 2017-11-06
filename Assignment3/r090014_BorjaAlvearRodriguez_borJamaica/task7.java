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
*
* @author Borja Alvear Rodríguez
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
			
				if (in == null) throw new IllegalArgumentException("File: "+filename+" not found");
			
				// Read the RDF/XML file
				model.read(in, null);
				
				// ** TASK 7.1: List all individuals of "Person" **
				System.out.println("Individuals of Person");
				
				@SuppressWarnings("rawtypes")
				ExtendedIterator Iter_indv = model.getOntClass(ns+"Person").listInstances();
				while(Iter_indv.hasNext())
					{
						Individual Indv = (Individual) Iter_indv.next();
						System.out.println(Indv.getURI());
					}
				
				// ** TASK 7.2: List all subclasses of "Person" **

				@SuppressWarnings("rawtypes")
				ExtendedIterator Iter_subcls = model.getOntClass(ns+"Person").listSubClasses();
				while(Iter_subcls.hasNext())
					{
						OntClass subcls = (OntClass) Iter_subcls.next();
						System.out.println(subcls.getURI());
					}
				
				
				// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
				
				OntModel indirect_Model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
				System.out.println("Indirect and Direct Individuals of Model");
				
				@SuppressWarnings("rawtypes")
				ExtendedIterator Iter_indv_indrct = indirect_Model.getOntClass(ns+"Person").listInstances();
				@SuppressWarnings("rawtypes")
				ExtendedIterator Iter_subcls_indrct = indirect_Model.getOntClass(ns+"Person").listSubClasses();
				
				while(Iter_indv_indrct.hasNext())
					{
						Individual indv_indrct = (Individual) Iter_indv_indrct.next();
						System.out.println(indv_indrct.getURI());
					}
				
				while(Iter_subcls_indrct.hasNext())
					{
						OntClass subcls_indrct = (OntClass) Iter_subcls_indrct.next();
						System.out.println(subcls_indrct.getURI());
					}
			
			}
	}	