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
 
  $dbpconfig = array(
  "remote_store_endpoint" => "http://localhost:8890/sparql/",
   );
 
  $store = ARC2::getRemoteStore($dbpconfig); 
 
  if ($errs = $store->getErrors()) {
     echo "<h1>getRemoteSotre error<h1>" ;
  }
 
  $query = '
  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX foaf: <http://xmlns.com/foaf/0.1/>
  PREFIX nsclass: <http://www.wsdlfiupm.es/resources/group14/class/>
  PREFIX nsprop: <http://www.wsdlfiupm.es/resources/group14/properties/>

  SELECT ?instances ?image ?label WHERE {?instances a nsclass:Monuments_Museums_Organizations .
  ?instances nsprop:image ?image .
  ?instances rdfs:label ?label .}';
  
  /* execute the query */
  $rows = $store->query($query, 'rows'); 
 
    if ($errs = $store->getErrors()) {
       echo "Query errors" ;
       print_r($errs);
    }

    /* loop for each returned row */
	echo '<div style="margin-left: 4%; display: grid; grid-template-columns: 0.5fr repeat(2, 0.5fr) 0.5fr; grid-template-rows: 1fr 1fr;">';
    foreach( $rows as $row ) { 
	?>	
	<div class="card bg-blue" style="width: 20em; margin-top: 6%">
	  <img class="card-img-top" src="<?php echo $row['image']?>" alt="Imagen monumento" style='width: auto; max-height: 15em; align-self: center;'>
	  <div class="card-body">
		<h4 class="card-title"><?php echo $row['label']?></h4>
		<a href="web/info.php?label=<?php echo $row['label']?>" class="btn btn-primary">Mas información</a>
	  </div>
	</div>
	<?php }?>
	</div>
  </body>
</html>