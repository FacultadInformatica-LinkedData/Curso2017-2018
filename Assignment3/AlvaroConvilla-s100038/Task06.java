package upm.oeg.wsld.jena; 
 
import java.io.InputStream; 
 
import org.apache.jena.ontology.Individual; 
import org.apache.jena.ontology.OntClass; 
import org.apache.jena.ontology.OntModel; 
import org.apache.jena.ontology.OntModelSpec; 
import org.apache.jena.rdf.model.ModelFactory; 
import org.apache.jena.rdf.model.Property; 
import org.apache.jena.util.FileManager; 
import org.apache.jena.vocabulary.VCARD; 
 
/** 
 * Task 06: Modifying ontologies (RDFs) 
 * 
 * @author elozano 
 * @author isantana 
 * @author AlvaroConvilla 
 */ 
public class Task06 { 
    public static String ns = "http://somewhere#"; 
    public static String foafNS = "http://xmlns.com/foaf/0.1/"; 
    public static String foafEmailURI = foafNS + "email"; 
    public static String foafKnowsURI = foafNS + "knows"; 
    public static String stringTypeURI = "http://www.w3.org/2001/XMLSchema#string"; 
 
    public static void main(String args[]) { 
        String filename = "resources/example5.rdf"; 
 
        // Create an empty model 
        OntModel modelo = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM); 
 
        // Use the FileManager to find the input file 
        InputStream inputfile = FileManager.get().open(filename); 
 
        if (inputfile == null) 
            throw new IllegalArgumentException("File: " + filename + " not found"); 
 
        // Read the RDF/XML file 
        modelo.read(inputfile, null); 
 
        // ** TASK 6.1: Create a new class named "University" ** 
        OntClass university = modelo.createClass(ns + "University"); 
 
        // ** TASK 6.2: Add "Researcher" as a subclass of "Person" ** 
        // Create a new class named "Researcher" 
        OntClass researcher = modelo.createClass(ns + "Researcher"); 
        modelo.getOntClass(ns + "Person").addSubClass(researcher); 
 
        // ** TASK 6.3: Create a new property named "worksIn" ** 
        Property worksIn = modelo.createProperty(ns + "worksIn"); 
 
        // ** TASK 6.4: Create a new individual of Researcher named "Jane Smith" ** 
        Individual newIndividual = modelo.createIndividual(ns + "Jane Smith", researcher); 
 
        // ** TASK 6.5: Add to the individual JaneSmith the fullName, given and family names ** 
        newIndividual.addLiteral(VCARD.FN, "Jane Smith"); 
        newIndividual.addLiteral(VCARD.Given, "Jane"); 
        newIndividual.addLiteral(VCARD.Family, "Smith"); 
 
        // ** TASK 6.6: Add UPM as the university where John Smith works ** 
        Individual johnSmith = modelo.getIndividual(ns + "JohnSmith"); 
        Individual upm = university.createIndividual(ns + "UPM"); 
        johnSmith.addProperty(worksIn, upm); 
 
 
        modelo.write(System.out, "RDF/XML-ABBREV"); 
    } 
}
