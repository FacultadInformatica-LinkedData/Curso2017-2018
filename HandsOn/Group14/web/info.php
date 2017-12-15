<html>
<head>
  <meta charset="UTF-8">
  <title>Group 14</title>
  <base href="/">

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/x-icon" href="favicon.ico">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="/web">Turismo en Madrid</a>
  </div>
</nav>
  <body>
 
  <?php
  /* ARC2 static class inclusion */ 
  include_once('semsol/ARC2.php');  
  $label = $_GET['label'];
  $dbpconfig = array(
  "remote_store_endpoint" => "http://localhost:8890/sparql/",
   );
 
  $store = ARC2::getRemoteStore($dbpconfig); 
 
  if ($errs = $store->getErrors()) {
     echo "<h1>getRemoteSotre error<h1>" ;
  }
 
  $query = '
  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
  PREFIX nsclass: <http://www.wsdlfiupm.es/resources/group14/class/>
  PREFIX nsprop: <http://www.wsdlfiupm.es/resources/group14/properties/>
  
  SELECT ?instances ?image ?label WHERE {?instances rdfs:label "'.$label.'" .
  ?instances nsprop:image ?image .
  ?instances rdfs:label ?label .}';
  
  /* execute the query */
  $rows = $store->query($query, 'rows'); 
 
    if ($errs = $store->getErrors()) {
       echo "Query errors" ;
       print_r($errs);
    }

    /* loop for each returned row */
    $row = $rows[0];
	?>	
	<div class="card bg-blue" style="width: 80%;
	   left: 10%;
	   margin-top: 2%">
	  <img class="card-img-top" src="<?php echo $row['image']?>" alt="Imagen monumento" style='cursor:pointer; width: auto; max-height: 15em; align-self: center;' 
		onclick="window.open('<?php echo $row['image']?>', '_blank');">
	  <div class="card-body">
		<h4 class="card-title"><?php echo $row['label']?></h4>
		<?php
		$query = '
		  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
		  PREFIX nsclass: <http://www.wsdlfiupm.es/resources/group14/class/>
		  PREFIX nsprop: <http://www.wsdlfiupm.es/resources/group14/properties/>
		  
		  SELECT ?instances ?description WHERE {?instances rdfs:label "'.$label.'" .
		  ?instances nsprop:description ?description .}';
		  
		/* execute the query */
		$rows = $store->query($query, 'rows'); 

		if ($errs = $store->getErrors()) {
		   echo "Query errors" ;
		   print_r($errs);
		}

		if ($rows) {
			$row = $rows[0];
			echo '<p class="card-text">Descripción: '.$row['description'].'</p>';
		}
		
		$query = '
		  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
		  PREFIX nsclass: <http://www.wsdlfiupm.es/resources/group14/class/>
		  PREFIX nsprop: <http://www.wsdlfiupm.es/resources/group14/properties/>
		  
		  SELECT ?instances ?schedule WHERE {?instances rdfs:label "'.$label.'" .
		  ?instances nsprop:schedule ?schedule .}';
		  
		/* execute the query */
		$rows = $store->query($query, 'rows'); 

		if ($errs = $store->getErrors()) {
		   echo "Query errors" ;
		   print_r($errs);
		}

		if ($rows) {
			$row = $rows[0];
			echo '<p class="card-text">Horario: '.$row['schedule'].'</p>';
		}
		
		$query = '
		  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
		  PREFIX nsclass: <http://www.wsdlfiupm.es/resources/group14/class/>
		  PREFIX nsprop: <http://www.wsdlfiupm.es/resources/group14/properties/>
		  
		  SELECT ?instances ?location ?street WHERE {?instances rdfs:label "'.$label.'" .
		  ?instances nsprop:location ?location .
		  ?location nsprop:street ?street .}';
		  
		/* execute the query */
		$rows = $store->query($query, 'rows'); 

		if ($errs = $store->getErrors()) {
		   echo "Query errors" ;
		   print_r($errs);
		}

		if ($rows) {
			$row = $rows[0];
			echo '<p class="card-text">Dirección: '.$row['street'].'</p>';
		}
		
		$query = '
		  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
		  PREFIX nsclass: <http://www.wsdlfiupm.es/resources/group14/class/>
		  PREFIX nsprop: <http://www.wsdlfiupm.es/resources/group14/properties/>
		  
		  SELECT ?instances ?location ?district WHERE {?instances rdfs:label "'.$label.'" .
		  ?instances nsprop:location ?location .
		  ?location nsprop:district ?district .}';
		  
		/* execute the query */
		$rows = $store->query($query, 'rows'); 

		if ($errs = $store->getErrors()) {
		   echo "Query errors" ;
		   print_r($errs);
		}

		if ($rows) {
			$row = $rows[0];
			echo '<p class="card-text">Distrito: '.$row['district'].'</p>';
		}
		?>	
	  </div>
		<a href="/web/index.php" class="btn btn-primary">Volver</a>
	</div>
  </body>
</html>