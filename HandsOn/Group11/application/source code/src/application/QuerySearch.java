package application;

import java.util.LinkedList;

import javafx.scene.image.Image;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

import javafx.scene.image.Image;
import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

public class QuerySearch {

	public static String createQuery(String name, String district, boolean free, boolean lon, boolean mo, boolean tu,
			boolean we, boolean th, boolean fr, boolean sa, boolean su) {
		
		//Add PREFIX
		String prefix = "PREFIX mv:  <http://datos.madrid_evento.es/group11/resources/property#> .\n";
		
		//Begin of the query
		String query = "" + prefix;
		String dias = "";
		
		//Check of Name  
		query = query + "SELECT ?name, ?description, ?location WHERE {";
		query = query + "?instance mv:name ?name . ";
		
		//Info we want
		query = query + "?instance mv:description ?description . ";
		query = query + "?instance mv:location ?location . ";
		
		//Check of district empty
		if(!(district == null)){
			query = query + "?instance mv:district ?district . ";
		}
		
		//Check of Free event
		if(free){
			query = query + "?instance mv:isFree \"1\" . ";
		}else {
			query = query + "?instance mv:isFree \"0\" . ";
		}
		
		//Check of Long event
		if(lon){
			query = query + "?instance mv:isLong \"1\" . ";
		}else {
			query = query + "?instance mv:isLong \"0\" . ";
		}
		
		//Check of Days
		if(mo)
			dias = "L";
		if(tu) {
			if(dias.isEmpty())
				dias = "M";
			else
				dias = dias + ",M";
		}
		if(we) {
			if(dias.isEmpty())
				dias = "X";
			else
				dias = dias + ",X";
		}
		if(th){
			if(dias.isEmpty())
				dias = "J";
			else
				dias = dias + ",J";
		}
		if(fr){
			if(dias.isEmpty())
				dias = "V";
			else
				dias = dias + ",V";
		}
		if(sa){
			if(dias.isEmpty())
				dias = "S";
			else
				dias = dias + ",S";
		}
		if(su){
			if(dias.isEmpty())
				dias = "D";
			else
				dias = dias + ",D";
		}
		if(!dias.isEmpty()){
			query = query + "?instance mv:daysOfTheWeek \"" + dias + "\" .";
		}
		
		if(name!=null)
			query += "FILTER( regex(str(?name), \""+name+"\" ) ";
		if(name!=null && district!=null)
			query += "&& regex(str(?district), \""+district+"\" ) ";
		else
			if(district != null)
				query += "FILTER( regex(str(?district), \""+district+"\" ) ";
		if(name!=null || district != null)
			query += " )";
		
		query = query + " }";
		
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
		//TODO
		return null;
	}


}
