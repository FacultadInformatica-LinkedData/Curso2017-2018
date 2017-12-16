package application;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

public class QuerySearch {

	private static final String PREFIX = "PREFIX mv:  <http://datos.madrid_evento.es/group11/resources/property#>\n"
			+ "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n";
	
	public static String createQuery(String name, String district, boolean free, boolean lon, boolean mo, boolean tu,
			boolean we, boolean th, boolean fr, boolean sa, boolean su) {
		
		//Begin of the query
		String query = "" + PREFIX;
		
		//Check of Name  
		query = query + "SELECT ?name, ?description, ?location WHERE {\n";
		query = query + "?instance mv:name ?name . \n";
		
		//Info we want
		query = query + "?instance mv:description ?description . \n";
		query = query + "?instance geo:location ?location . \n";
		
		//Check of district empty
		if(!(district == null)){
			query = query + "?instance mv:district ?district . \n";
		}
		
		//Check of Free event
		query = query + "?instance mv:isFree "+Boolean.toString(free)+" .\n";
		
		//Check of Long event
		query = query + "?instance mv:isLong "+Boolean.toString(lon)+" .\n";
		
		if(mo||tu||we||th||fr||sa||su){
			query = query + "?instance mv:daysOfTheWeek ?days .\n";
			//Check of Days
			if(mo)
				query += "FILTER( regex(str(?name), \"L\" )) .\n";
			if(tu)
				query += "FILTER( regex(str(?name), \"M\" )) .\n";
			if(we)
				query += "FILTER( regex(str(?name), \"X\" )) .\n";
			if(th)
				query += "FILTER( regex(str(?name), \"J\" )) .\n";
			if(fr)
				query += "FILTER( regex(str(?name), \"V\" )) .\n";
			if(sa)
				query += "FILTER( regex(str(?name), \"S\" )) .\n";
			if(su)
				query += "FILTER( regex(str(?name), \"D\" )) .\n";
		}
		
		if(name!=null)
			query += "FILTER( regex(str(?name), \""+name+"\" )) .\n";
		if(district!=null)
			query += "FILTER( regex(str(?district), \""+district+"\" )) .\n";
		
		query = query + "}";
		
		return query;
	}

	public static LinkedList<Event> executeQuery(String query){
		LinkedList<Event> resultslist = new LinkedList<Event>();
		//La comprobacion se hizo en la connectToData
		//por tanto no hacemos un try-catch
		String url = "jdbc:virtuoso://localhost:1111";
		VirtGraph set = new VirtGraph("http://localhost:8890/datosevento",url, "dba", "dba");	
		Query sparql = QueryFactory.create(query);
		VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create (sparql, set);
		ResultSet results = vqe.execSelect();
		while (results.hasNext()) {
			QuerySolution result = results.nextSolution();
		    RDFNode name = result.get("name");
		    RDFNode description = result.get("description");
		    RDFNode uriToLocation = result.get("location");
		    resultslist.add(new Event(name.toString(), description.toString(), uriToLocation.toString())) ;
		}
		return resultslist;
	}
	
	public static Image getMapImage(String locationURI){
		Image image = null;
		String key = "";
		//TODO Query to get coordinates given the URI of the resource
		double latitud = 40.406863;
		double longitud = -3.711599;
		//API petition
		String petition = "https://maps.googleapis.com/maps/api/staticmap?";
		petition += "center="+latitud+","+longitud;
		petition += "&&zoom=17";
		petition += "&&size=400x400";
		//Read key from file
		String fileName = "resources/key.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       key = line;
		    }
		} catch (Exception e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
		petition += "&&key="+key;
		//Do Get petition
		try{
		URL url = new URL(petition);
	    BufferedImage img = ImageIO.read(url);
	    image = SwingFXUtils.toFXImage(img, null);
	    }catch (Exception e) {
			//handle exception
		}
		return image;
	}


}
