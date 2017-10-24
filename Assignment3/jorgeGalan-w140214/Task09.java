package upm.oeg.wsld.jena;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.VCARD;
import org.apache.xerces.util.SynchronizedSymbolTable;


/**
 * Task09: Data linking
 * 
 * @author isantana
 *
 */
public class Task09
{
	public static void main(String args[])
	{
		String file1 = "resources/data03.rdf";
		String file2 = "resources/data04.rdf";

		// Create an empty model
		OntModel model3 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		OntModel model4 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		OntModel modelSameAs = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);

		// Use the FileManager to find the input file
		InputStream in1 = FileManager.get().open(file1);
		InputStream in2 = FileManager.get().open(file2);

		if (in1 == null)
			throw new IllegalArgumentException("File: "+file1+" not found");

		if (in2 == null)
			throw new IllegalArgumentException("File: "+file2+" not found");

		// Read the RDF/XML file
		model3.read(in1,null);
		model4.read(in2,null);

		// ** TASK 9.1: Data Linking. Look for individuals that are the same in both datasets  and link them by owl:sameAs (OWL.sameAs)**
		// ** We consider that two individuals are the same when they have the same Given and Family names **
		// ** You can assume that all individuals have both properties filled **
		// ** Be aware that in both datasets the URIs are different. You can use either iterators or SPARQL (or both...) **
		// ** As a result, modelSameAs should contain the linking triples <r1, owl:sameAs, r2>  **
		String queryString = 
				"PREFIX vcard: <" + VCARD.getURI() + "> " +
						"SELECT ?Subject ?Family ?Given "+
						"WHERE { ?Subject vcard:FN ?Family."
						+ 		"?Subject vcard:Given ?Given.} ";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec3 = QueryExecutionFactory.create(query, model3);
		ResultSet results3 = qexec3.execSelect(); //Lista con todos los recursos con las 2 propiedades
		QueryExecution qexec4 = QueryExecutionFactory.create(query, model3);
		ResultSet results4 = qexec4.execSelect(); //Lista con todos los recursos con las 2 propiedades
		List<QuerySolution> bindingList = new ArrayList<QuerySolution>();
		while(results4.hasNext()) {
			bindingList.add(results4.nextSolution());
		}
		while(results3.hasNext()) {
			QuerySolution binding = results3.nextSolution();
			Literal fn = binding.getLiteral("Family"); 
			Literal gv = binding.getLiteral("Given");
			for(QuerySolution binding4:bindingList) {
				Literal fn4 = binding4.getLiteral("Family");
				Literal gv4 = binding4.getLiteral("Given");
				if(fn.equals(fn4) && gv.equals(gv4)) {
					Resource subj = (Resource) binding.get("Subject");
					Resource subj4 = (Resource) binding4.get("Subject");
					Resource r3 = modelSameAs.createResource(subj);
					r3.addProperty(OWL.sameAs, subj4);
				}
			}
		}
	}
}

