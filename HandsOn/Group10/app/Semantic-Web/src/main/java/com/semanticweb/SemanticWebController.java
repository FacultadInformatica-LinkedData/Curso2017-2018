package com.semanticweb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import model.Test;

@Controller
public class SemanticWebController {
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		return "index";
	}
	
	@RequestMapping(value = "/result", method=RequestMethod.POST)
    public String helloTwitter(Model model, @RequestParam(value = "text") String zone) {
		
		String zona = zone.toUpperCase();
		String nivelStr = null;
		String imgStr = null;
		String absStr = null;
		
		if(zona.contains("CALLE")) {
			
			/*************** Query Calle *******************************************/
			String queryCalle1 = "PREFIX g10: <htpp://www.contaminacion-acustica-madrid.org/group10/ontology#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
					"SELECT ?Barrio ?Distrito WHERE {\r\n" + 
					"   ?RecursoCalle g10:hasNameStreet \""+zona+"\"^^<http://www.w3.org/2001/XMLSchema#string> .\r\n" + 
					"   ?RecursoCalle g10:hasNumNeighborhoodStreet ?Barrio .\r\n" + 
					"   ?RecursoCalle g10:hasNumDistrictStreet ?Distrito .\r\n" +
					"}";
			String filename1 = "C:\\Users\\supra\\eclipse-workspace\\semantic_web\\Semantic-Web\\src\\main\\resources\\static\\rdf\\Contaminacion_Acustica_Mad_17-updated.ttl";
			String filename2 = "C:\\Users\\supra\\eclipse-workspace\\semantic_web\\Semantic-Web\\src\\main\\resources\\static\\rdf\\Callejero_Madrid-updated.ttl";
			
			System.out.println(queryCalle1);
			OntModel ontModel1 = ModelFactory.createOntologyModel();
			
			// Use the FileManager to find the input file
			InputStream in1 = FileManager.get().open(filename2);
		
			if (in1 == null)
				throw new IllegalArgumentException("File: "+filename2+" not found");
		
			// Read the RDF/XML file
			ontModel1.read(in1, null, "TTL");
	    	
			Query query1 = QueryFactory.create(queryCalle1);
			QueryExecution qexec1 = QueryExecutionFactory.create(query1, ontModel1) ;
			ResultSet results1 = qexec1.execSelect() ;
			String barrioString = null;
			String distritoString = null;
			while (results1.hasNext())
			{
				QuerySolution binding1 = results1.nextSolution();
				RDFNode barrio = binding1.get("Barrio");
				RDFNode distrito = binding1.get("Distrito");
				
				barrioString=barrio.toString();
				distritoString = distrito.toString();
			    System.out.println("Barrio: "+barrioString);
			    System.out.println("distrito: "+distritoString);
			}
			String dbpedia = null;
			String queryCalle2 = "PREFIX g10: <htpp://www.contaminacion-acustica-madrid.org/group10/ontology#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
					"SELECT ?Lv WHERE {\r\n" + 
					"   ?RecursoZona g10:hasNumNeighborhoodZone \""+barrioString+"\"^^<http://www.w3.org/2001/XMLSchema#string> .\r\n" + 
					"   ?RecursoZona g10:hasNumDistrictZone \""+distritoString+"\"^^<http://www.w3.org/2001/XMLSchema#string> .\r\n" + 
					"   ?RecursoZona g10:hasMedNoiseLv ?Lv .\r\n" + 
					"   ?RecursoZona owl:sameAs ?Db .\r\n" + 
					"} LIMIT 1";
			
			System.out.println(queryCalle2);
			OntModel ontModel2 = ModelFactory.createOntologyModel();
			
			// Use the FileManager to find the input file
			InputStream in2 = FileManager.get().open(filename1);
		
			if (in2 == null)
				throw new IllegalArgumentException("File: "+filename1+" not found");
		
			// Read the RDF/XML file
			ontModel2.read(in2, null, "TTL");
	    	
			Query query2 = QueryFactory.create(queryCalle2);
			QueryExecution qexec2 = QueryExecutionFactory.create(query2, ontModel2) ;
			ResultSet results2 = qexec2.execSelect() ;
			
			while (results2.hasNext())
			{
				QuerySolution binding2 = results2.nextSolution();
				RDFNode nivel = binding2.get("Lv");
				RDFNode db = binding2.get("Db");
				if(db !=null) {
					dbpedia = db.toString();
				}

				nivelStr = nivel.toString();
			    System.out.println("Nivel: "+nivelStr);
			    System.out.println(dbpedia);
			}
			
			if(dbpedia != null) {
				/************************************/
				String dbQueryStr = "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
						"SELECT ?im ?abstract WHERE {\r\n" + 
						"   <"+dbpedia+"> dbo:thumbnail ?im .\r\n" + 
						"   <"+dbpedia+"> dbo:abstract ?abstract .\r\n" + 
						"} LIMIT 1";
				
				 Query dbQuery = QueryFactory.create(dbQueryStr);

				
			        // Remote execution.
			        try ( QueryExecution qexecDb = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", dbQuery) ) {
			            // Set the DBpedia specific timeout.
			            //((Object) qexecDb).addParam("timeout", "10000") ;

			            // Execute.
			            ResultSet rs = qexecDb.execSelect();
			            //ResultSetFormatter.out(System.out, rs, dbQuery);
			            
			            while (rs.hasNext())
			    		{
			    			QuerySolution bindingDb = rs.nextSolution();
			    			RDFNode img = bindingDb.get("im");
			    			RDFNode abs = bindingDb.get("abstract");
			    			imgStr=img.toString();
			    			absStr=abs.toString();
			    		    System.out.println("img: "+imgStr);
			    		    System.out.println("abs: "+absStr);
			    		   
			    		    
			    		}
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
				
				/***********************************/
			}
			
			
			
			
			if(nivelStr == null) {
				String dbpedia2 = null;
				String queryExtra = "PREFIX g10: <htpp://www.contaminacion-acustica-madrid.org/group10/ontology#>\r\n" + 
						"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
						"PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
						"SELECT ?Lv ?Db WHERE {\r\n" + 
						"   ?RecursoZona g10:hasNumNeighborhoodZone \""+barrioString+"\"^^<http://www.w3.org/2001/XMLSchema#string> .  \r\n" + 
						"   ?RecursoZona g10:hasMedNoiseLv ?Lv .\r\n" + 
						"   ?RecursoZona owl:sameAs ?Db .\r\n" + 
						"}";
				Query queryextra = QueryFactory.create(queryExtra);
				QueryExecution qexecextra = QueryExecutionFactory.create(queryextra, ontModel2) ;
				ResultSet resultsextra = qexecextra.execSelect() ;
				String nivelStringextra = null;
				while (resultsextra.hasNext())
				{
					QuerySolution bindingextra = resultsextra.nextSolution();
					RDFNode nivelextra = bindingextra.get("Lv");
					RDFNode db = bindingextra.get("Db");
					if(db !=null) {
						dbpedia = db.toString();
					}
					
					nivelStr = nivelextra.toString();
				    System.out.println("Nivel: "+nivelStr);
				    System.out.println(dbpedia);
				}
				
				if(dbpedia !=null) {
					/************************************/
					String dbQueryStr2 = "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
							"SELECT ?im ?abstract WHERE {\r\n" + 
							"   <"+dbpedia+"> dbo:thumbnail ?im .\r\n" + 
							"   <"+dbpedia+"> dbo:abstract ?abstract .\r\n" + 
							"} LIMIT 1";
					
					 Query dbQuery2 = QueryFactory.create(dbQueryStr2);

					
				        // Remote execution.
				        try ( QueryExecution qexecDb = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", dbQuery2) ) {
				            // Set the DBpedia specific timeout.
				            //((Object) qexecDb).addParam("timeout", "10000") ;

				            // Execute.
				            ResultSet rs = qexecDb.execSelect();
				            //ResultSetFormatter.out(System.out, rs, dbQuery);
				            
				            while (rs.hasNext())
				    		{
				    			QuerySolution bindingDb = rs.nextSolution();
				    			RDFNode img = bindingDb.get("im");
				    			RDFNode abs = bindingDb.get("abstract");
				    			imgStr=img.toString();
				    			absStr=abs.toString();
				    		    System.out.println("img: "+imgStr);
				    		    System.out.println("abs: "+absStr);
				    		    
				    		}
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
					
					/***********************************/
				}
				
			}
			/* Se podría hacer la media de los niveles */
			/**********************************************************************/
			
		} else {
	
	    	
	    	/****************************** Queries zona ******************************/
	    	String filename = "C:\\Users\\supra\\eclipse-workspace\\semantic_web\\Semantic-Web\\src\\main\\resources\\static\\rdf\\Contaminacion_Acustica_Mad_17-updated.ttl";
	    	
			String queryString = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"SELECT ?Zone ?numero ?Db  WHERE {\r\n" + 
					"   ?Zone <htpp://www.contaminacion-acustica-madrid.org/group10/ontology#hasNumNeighborhoodZone> ?x.\r\n" + 
					"   ?Zone <htpp://www.contaminacion-acustica-madrid.org/group10/ontology#hasNameZone> \""+zona+"\"^^<http://www.w3.org/2001/XMLSchema#string>.\r\n" + 
					"   ?Zone <htpp://www.contaminacion-acustica-madrid.org/group10/ontology#hasMedNoiseLv> ?numero.\r\n" + 
					"   ?Zone owl:sameAs ?Db .\r\n" + 
					"} ";
			System.out.println(queryString);
			OntModel ontModel = ModelFactory.createOntologyModel();
			
			// Use the FileManager to find the input file
			InputStream in = FileManager.get().open(filename);
		
			if (in == null)
				throw new IllegalArgumentException("File: "+filename+" not found");
		
			// Read the RDF/XML file
			ontModel.read(in, null, "TTL");
	    	
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.create(query, ontModel) ;
			ResultSet results = qexec.execSelect() ;
			String res = null;
			String dbpedia = null;
			while (results.hasNext())
			{
				QuerySolution binding = results.nextSolution();
				RDFNode subj = binding.get("numero");
				RDFNode db = binding.get("Db");
				nivelStr=subj.toString();
			    System.out.println("numero: "+nivelStr);
			    System.out.println("db: "+db);
			    dbpedia = db.toString();
			    System.out.println(dbpedia);
			}
			
			String dbQueryStr = "PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
					"SELECT ?im ?abstract WHERE {\r\n" + 
					"   <"+dbpedia+"> dbo:thumbnail ?im .\r\n" + 
					"   <"+dbpedia+"> dbo:abstract ?abstract .\r\n" + 
					"} LIMIT 1";
			
			 Query dbQuery = QueryFactory.create(dbQueryStr);

			
		        // Remote execution.
		        try ( QueryExecution qexecDb = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", dbQuery) ) {
		            // Set the DBpedia specific timeout.
		            //((Object) qexecDb).addParam("timeout", "10000") ;

		            // Execute.
		            ResultSet rs = qexecDb.execSelect();
		            //ResultSetFormatter.out(System.out, rs, dbQuery);
		            
		            while (rs.hasNext())
		    		{
		    			QuerySolution bindingDb = rs.nextSolution();
		    			RDFNode img = bindingDb.get("im");
		    			RDFNode abs = bindingDb.get("abstract");
		    			imgStr=img.toString();
		    			absStr=abs.toString();
		    		    System.out.println("img: "+imgStr);
		    		    System.out.println("abs: "+absStr);
		    		    
		    		}
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			
			
	    	/****************************** Queries ******************************/
			
			
		}
    	
    	
    		model.addAttribute("descripcion", absStr);
    		model.addAttribute("nivel", nivelStr);
    		model.addAttribute("imagen", imgStr);
       
        return "welcome";
    }
}
