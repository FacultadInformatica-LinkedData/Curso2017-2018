

#Hands-on assignment 3 - Self assessment#

##Checklist##

Every RDF file

[x] Uses the ttl extension  
[x] Is serialized in the Turtle format  
[x] Follows the resource naming strategy  
[x] Uses class and property URIs that are the same as those used in the ontology  
  
Every URI in the RDF files  
  
[x] Is "readable" and has some meaning (e.g., it is not an auto-increased integer)  
[x] Is not encoded as a string  
[x] Does not contain a double slash (i.e., “//”)  

Every individual in the RDF files  
  
[x] Has a label with the name of the individual    
[x] Has a type  

Every value in the RDF files  

[x] Is not empty (i.e., “”)  
[x] Is trimmed  
[x] Is properly encoded (e.g., dates, booleans)  
[x] Includes its datatype  
[x] Uses the correct datatype (e.g., values of 0-1 may be booleansand not integers, not every string made of numbers is a number)   


##Comments on self-assessment##

Distribución de tareas:
- Pasar los csv a rdf con loadrefine: Javi
- Modificación de propiedades de la ontologia para usar propiedades publicadas por otras ontologias: Javi
