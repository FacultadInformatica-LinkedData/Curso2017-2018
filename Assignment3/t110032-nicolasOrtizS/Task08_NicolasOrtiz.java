package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
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

/**
 * Task08: Completing missing data 
 * 
 * @author isantana
 *
 */
public class Task08
{

	public static void main(String args[])
	{
		String file1 = "resources/data01.rdf";
		String file2 = "resources/data02.rdf";

		// Create an empty model
		OntModel model1 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);

		// Use the FileManager to find the input file
		InputStream in1 = FileManager.get().open(file1);
		InputStream in2 = FileManager.get().open(file2);

		if (in1 == null)
			throw new IllegalArgumentException("File: "+file1+" not found");

		if (in2 == null)
			throw new IllegalArgumentException("File: "+file2+" not found");

		// Read the files
		model1.read(in1,null);
		model2.read(in2,null);


		// ** TASK 8.1: List all the Person (http://data.org#Person) resources in Model1 looking for missing properties **
		// ** Each resource should have a given name, a family name and an email **
		// ** Try to complete this information using the data from Data02 (Model2) **
		// ** In both datasets the same URIs are used. You can use either iterators or SPARQL (or both...) **
		// ** As a result, every individual in Model1 should have all properties properly filled **
		StmtIterator stIter = model2.listStatements(null, VCARD.FN, (RDFNode)null);
		StmtIterator stIter2 = model2.listStatements(null, VCARD.Family, (RDFNode)null);
		StmtIterator stIter3 = model2.listStatements(null, VCARD.EMAIL, (RDFNode)null);
		
	
		OntClass person = model1.getOntClass("http://data.org#Person");
		ExtendedIterator<?> instances = person.listInstances();
		
		while (stIter.hasNext() && instances.hasNext())
		{
			Statement st = stIter.next();
			Resource subj = st.getSubject();
			RDFNode fn = st.getObject();
			
			Statement st2 = stIter2.next();
			Resource subj2 = st2.getSubject();
			RDFNode family = st2.getObject();
			
			Statement st3 = stIter3.next();
			Resource subj3 = st3.getSubject();
			RDFNode email = st3.getObject();
			
			Individual inst = (Individual) instances.next();
			
			inst.setPropertyValue(VCARD.FN, fn);
			inst.setPropertyValue(VCARD.Family, family);
			inst.setPropertyValue(VCARD.EMAIL, email);
			
			System.out.println(inst.getURI());
			System.out.println(subj.getURI()+" "+VCARD.FN.getURI()+" "+fn.asLiteral());
			System.out.println(subj2.getURI()+" "+VCARD.Family.getURI()+" "+family.asLiteral());
			System.out.println(subj3.getURI()+" "+VCARD.EMAIL.getURI()+" "+email.asLiteral());
		}
	}
}
