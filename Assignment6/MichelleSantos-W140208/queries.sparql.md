## Assignment 6
#### Sparql queries

1. How many accidents were there in Madrid in 2013?
    ```
    PREFIX qb: <http://purl.org/linked-data/cube#> 
    PREFIX mv: <http://example.org/myVocabulary#> 
    select  (SUM(?n_accidentes ) AS ?total) where {
        ?obs a qb:Observation ;
             mv:numberOfAccidents ?n_accidentes .
    } 
    ```
1. Give me the number of accidents in Usera for each type of accident
    ```
    PREFIX qb: <http://purl.org/linked-data/cube#> 
    PREFIX mv: <http://example.org/myVocabulary#>
    select ?tipo ?n_accidentes where {
        ?obs a qb:Observation ;
             mv:relatedDistrict "USERA" ;
             mv:hasAccidentType ?tipo ;
             mv:numberOfAccidents ?n_accidentes .
    }
    ```
1. Give me the number of multiple collisions for each district
    ```
    PREFIX qb: <http://purl.org/linked-data/cube#> 
    PREFIX mv: <http://example.org/myVocabulary#>
    select ?distrito ?n_accidentes where {
        ?obs a qb:Observation ;
             mv:relatedDistrict ?distrito ;
             mv:hasAccidentType mv:MultipleCollision ;
             mv:numberOfAccidents ?n_accidentes .
    }
    ```
1. Which is the district were the number of bicycle falls was higher in 2013?
    ```
    PREFIX qb: <http://purl.org/linked-data/cube#> 
    PREFIX mv: <http://example.org/myVocabulary#>
    select ?distrito ?max where {
        ?obs a qb:Observation ;
             mv:relatedDistrict ?distrito ;
             mv:hasAccidentType mv:BicycleFall ;
             mv:numberOfAccidents ?max.
	    {
		    select  MAX(?n_accidentes) as ?max where {     
			    ?obs a qb:Observation ;
				     mv:relatedDistrict ?distrito ;
				     mv:hasAccidentType mv:BicycleFall ;
				     mv:numberOfAccidents ?n_accidentes .
		    }
	    }
    }
    ```
1. Give me the districts with more than 500 accidents in 2013
    ```
    PREFIX qb: <http://purl.org/linked-data/cube#> 
    PREFIX mv: <http://example.org/myVocabulary#>
    select ?distrito ?suma where {
	    {
		    select ?distrito (SUM(xsd:int(?accidentes)) AS ?suma) where {
			    ?obs a qb:Observation ;
				     mv:relatedDistrict ?distrito;
				     mv:numberOfAccidents ?accidentes.
		    } ORDER BY (?distrito) 
	    }
	    FILTER (<http://www.w3.org/2001/XMLSchema#integer> (?suma) > 500)
    }
    ```