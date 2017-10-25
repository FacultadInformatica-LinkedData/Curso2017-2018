/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task7;

import java.io.InputStream;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 *
 * @author 1511 IRON
 */
public class Task7 {

    /**
     * @param args the command line arguments
     */
    public static String ns = "http://somewhere#";

    public static void main(String args[]) {
        String filename = "example6.rdf";

        // Create an empty model
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF); // RDFS_MEM

        // Use the FileManager to find the input file
        InputStream in = FileManager.get().open(filename);

        if (in == null) {
            throw new IllegalArgumentException("File: " + filename + " not found");
        }

        // Read the RDF/XML file
        model.read(in, null);

        // ** TASK 7.1: List all individuals of "Person" **
        OntClass person = model.getOntClass(ns + "Person");
        ExtendedIterator instances = person.listInstances();

        while (instances.hasNext()) {
            Individual inst = (Individual) instances.next();
            System.out.println("Instance of Person: " + inst.getURI());
        }

        System.out.println("\n");

        // ** TASK 7.2: List all subclasses of "Person" **
        ExtendedIterator subclasses = person.listSubClasses();

        while (subclasses.hasNext()) {
            OntClass subclass = (OntClass) subclasses.next();
            System.out.println("Subclass of Person: " + subclass.getURI());
        }

    }

}
