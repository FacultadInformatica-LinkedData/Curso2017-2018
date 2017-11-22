package upm.oeg.wsld.jena;

import java.io.InputStream;

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
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.VCARD;

/**
 * Task10: Reasoning with rules 
 * 
 * @author isantana
 *
 */
public class Task10
{
	
	public static void main(String args[])
	{
		String file1 = "resources/data05.rdf";
		
		// Create an empty model
		OntModel model5 = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in1 = FileManager.get().open(file1);
	
		if (in1 == null)
			throw new IllegalArgumentException("File: "+file1+" not found");
	
		// Read the RDF/XML file
		model5.read(in1,null);
		
		// ** TASK 10.1: Custom inference rules. **
		// ** Data05.rdf contains data about parents and siblings. **
		// ** Define custom rules to infer when someone has an uncle or aunt **
		// ** Someone has an uncle or aunt when his/her parent has a sibling **
		// ** That is, if <Tim, parent, Sara> and <Sara, sibling, John> then <Tim, hasUncleOrAunt, John> **
		// ** Pointers: https://jena.apache.org/documentation/inference/#rules **
		// **           http://tutorial-academy.com/jena-reasoning-with-rules/ **
		
		// ** TASK 10.2: Define rules so that the sibling property works both ways **
		// ** That is, if <Jack, parent, John> and <Sara, sibling, John> then <Jack, hasUncleOrAunt, Sara> **
		
		// ** TASK 10.3: Define rules so that 2 step siblings are also siblings **
		// ** That is, if <Sara, sibling, John> and <John, sibling, Peter> then <Sara, hasUncleOrAunt, Peter> **
		
	}
}
