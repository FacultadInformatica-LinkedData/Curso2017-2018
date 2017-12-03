#Hands-on assignment 3 – Self assessment

##Checklist

**Every RDF file:**

- [X] Uses the .ttl extension
- [X] Is serialized in the Turtle format
- [X] Follows the resource naming strategy

**Every URI in the RDF files:**

- [X] Is "readable" and has some meaning (e.g., it is not an auto-increased integer) 
- [X] Is not encoded as a string
- [X] Does not contain a double slash (i.e., "//")

**Every individual in the RDF files:**

- [X] Has a label with the name of the individual
- [X] Has a type
- [X] Every value in the RDF files:
- [X] Is not empty (i.e., “”)
- [X] Is trimmed
- [X] Is properly encoded (e.g., dates, booleans)
- [X] Includes its datatype
- [X] Uses the correct datatype (e.g., values of 0-1 may be booleans and not integers, not every string made of numbers is a number) 

##Comments on the self-assessment
El esquema JSON con los cambios necesarios a aplicar en el proyecto refine de todos los csv sera el mismo, es decir, todos los csv tienen el mismo formato, por lo tanto los cambios son los mismos en todos, salvo alguna variacion que solo acepta a algunos. Hemos dejado el cambio de todas esas variaciones, con lo cual el mismo json aplicado a todos los csv, los dejara preparados para el uso de nuestra aplicacion. Existe un caso especial para el csv de escuela de drama, arte y danza que es distinto al resto, por lo tanto dejaremos un json especial para este caso.