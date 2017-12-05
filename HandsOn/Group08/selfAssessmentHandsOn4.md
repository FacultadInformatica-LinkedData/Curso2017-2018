#Hands-on assignment 4 - Self assessment#

##Checklist##

ontology-updated.owl file

[X]Uses the .owl extension
[X]Is serialized in the Turtle format
[X]Follows the resource naming strategy
[x]Contains at least one class
[X]Contains at least one object property (where the value of the property is a resource)
[x]Contains at least one datatype property (where the value of the property is a string literal, usually typed)
[x]Defines the domain of all the properties (the origin of the property)
[x]Defines the range of all the properties (the destination of the property)
[x]Defines all class names starting with a capital letter
[x]Defines all property names starting with a non-capital letter
[x]Does not mix labels in different languages (e.g., Spanish and English)
[x]Does not define multiple domains or multiple ranges in properties
[x]Contains at least one class that will be used to link to other entities

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
