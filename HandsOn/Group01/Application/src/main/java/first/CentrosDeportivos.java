package first;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;

public class CentrosDeportivos {

    public static String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    public static String owl = "http://www.w3.org/2002/07/owl#";
    public static String xsd = "http://www.w3.org/2001/XMLSchema#";
    public static String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
    public static String foaf = "http://xmlns.com/foaf/0.1/";


    public ArrayList<CentroDeportivo> nombres(String nomDistri){

        ArrayList<CentroDeportivo> listCentros = new ArrayList<CentroDeportivo>();

        //leo la ttl
        String filename = "polideportivos.ttl";

        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(filename);

        if (in == null)
            throw new IllegalArgumentException("File: " + filename + " not found");

        model.read(in, null, "TTL");


        //Property
        Property Name1 = model.createProperty(foaf + "Name");
        Property Distr = model.createProperty(foaf + "District");
        Property calle = model.createProperty(foaf + "Street");
        Property cp = model.createProperty(foaf + "PostalCode");
        Property barrio = model.createProperty(foaf + "Neighborhood");


        StmtIterator stIter = model.listStatements(null, Distr, nomDistri);

        //StmtIterator stIter = model.listStatements(null, Distr, "PUENTE DE VALLECAS");

        while (stIter.hasNext()) {

            CentroDeportivo centro = new CentroDeportivo();

            Statement st = stIter.next();
            Resource subj = st.getSubject();
            RDFNode fn = st.getObject();


            //System.out.println(subj.getLocalName() + "  ---  --- " + fn.asLiteral());
            //System.out.println(subj.getURI() + " " + VCARD.FN.getURI() + " " + fn.asLiteral());



            //1
            Statement name2 = subj.getProperty(Name1);

            System.out.println(" El nombre es:  " + name2.getObject().asLiteral());
            centro.setNombre(name2.getObject().asLiteral().toString());

            //2
            Statement cp2 = subj.getProperty(cp);

            System.out.println(" El cp es:  " + cp2.getObject().asLiteral());
            centro.setCp(cp2.getObject().asLiteral().toString());

            //3
            Statement calle2 = subj.getProperty(calle);

            System.out.println(" La calle es:  " + calle2.getObject().asLiteral());
            centro.setCalle(calle2.getObject().asLiteral().toString());

            //4
            Statement barrio2 = subj.getProperty(barrio);

            System.out.println(" El barrio es:  " + barrio2.getObject().asLiteral());
            centro.setBarrio(barrio2.getObject().asLiteral().toString());


            //////final
            listCentros.add(centro);

        }

        return listCentros;
    }

    public static void main (String args[]) {

        String filename = "polideportivos.ttl";

        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(filename);

        if (in == null)
            throw new IllegalArgumentException("File: " + filename + " not found");

        model.read(in, null, "TTL");

        //Property
        Property Name1 = model.createProperty(foaf + "Name");
        Property Distr = model.createProperty(foaf + "District");

        //Property ass	 = model.createProperty(owl+"sameAs");

        StmtIterator stIter = model.listStatements(null, Distr, "PUENTE DE VALLECAS");

        while (stIter.hasNext()) {

            Statement st = stIter.next();
            Resource subj = st.getSubject();
            RDFNode fn = st.getObject();


            System.out.println(subj.getLocalName() + "  ---  --- " + fn.asLiteral());

            Statement name2 = subj.getProperty(Name1);
            System.out.println(" El nombre es:  " + name2.getObject().asLiteral());

        }

    }
}