<?php
header('Access-Control-Allow-Origin: *'); 
set_time_limit(300);

$month = isset($_GET['mes']) ? $_GET['mes'] : '09';
//$month = '09';
$fecha = '2017-'.$month.'-01';

require 'vendor/autoload.php';
//$graph = new EasyRdf_Graph(); 
//$graph->parseFile("datos.ttl"); 


EasyRdf_Namespace::set('re', 'https://www.linkeddataupm.es/group19/resource/resource/');
EasyRdf_Namespace::set('msr', 'http://www.telegraphis.net/ontology/measurement/measurement#');
EasyRdf_Namespace::set('swpo', 'http://sw-portal.deri.org/ontologies/swportal#');
EasyRdf_Namespace::set('lsweb', 'http://ontology.cybershare.utep.edu/ELSEWeb/elseweb-data.owl#');
EasyRdf_Namespace::set('identity', 'http://www.identity.org/ontologies/identity.owl#');
EasyRdf_Namespace::set('rdf', 'http://www.w3.org/1999/02/22-rdf-syntax-ns#');
EasyRdf_Namespace::set('owl', 'http://www.w3.org/2002/07/owl#');
EasyRdf_Namespace::set('rdfs', 'http://www.w3.org/2000/01/rdf-schema#');
EasyRdf_Namespace::set('voc', 'https://www.linkeddataupm.es/group19/resource/vocabulary#');

$sparql = new EasyRdf_Sparql_Client('http://localhost:3030/ruidos/sparql');

$string2 = 'SELECT ?name  ?date  ?Ld ?Le ?Ln ?LAeq24 ?LAS01 ?LAS10 ?LAS50 ?LAS90 ?LAS99 
WHERE {?location identity:hasName ?name;
voc:hasMeasurement ?code.
?measurement voc:hasCode ?code;
lsweb:hasDate ?date;
voc:pLd ?Ld;
voc:pLe ?Le;
voc:pLn ?Ln;
voc:pLAeq24 ?LAeq24;
voc:pLAS01 ?LAS01;
voc:pLAS10 ?LAS10;
voc:pLAS50 ?LAS50;
voc:pLAS90 ?LAS90;
voc:pLAS99 ?LAS99.}';

$string3 = "SELECT ?name ?cont  WHERE {
?location identity:hasName ?name;
voc:hasMeasurement ?code.
?measurement voc:hasCode ?code;
lsweb:hasDate ?date;
voc:pLAeq24 ?cont.
FILTER regex(str(?date), '".$fecha."')  }ORDER BY DESC (?cont) LIMIT 1";

$string4 = "SELECT ?name ?cont  WHERE {
?location identity:hasName ?name;
voc:hasMeasurement ?code.
?measurement voc:hasCode ?code;
lsweb:hasDate ?date;
voc:pLAeq24 ?cont.
FILTER regex(str(?date), '".$fecha."')  }ORDER BY  (?cont) LIMIT 1";


$result = $sparql->query($string2);

$result2 = $sparql->query($string3);
$result3 = $sparql->query($string4);

!isset($_GET['estat']) ? $response = '<table id="tabla1" class="w3-table w3-border w3-bordered w3-centered w3-table-all infoTable">' : $response = '<table id="tabla3" class="w3-table w3-border w3-bordered w3-centered w3-table-all infoTable">';

if(!isset($_GET['estat'])){
  $response .='<thead><tr>
                <th> Nombre </th>
                <th> Fecha </th>
                <th title="Nivel sonoro medio a largo plazo ponderado A, determinado a lo largo de todos los
                  períodos día (de 7 a 19 horas) del mes. 
                  "> Ld </th>
                <th title=" Nivel sonoro medio a largo plazo ponderado A, determinado a lo largo de todos los
                  períodos tarde (de 19 a 23 horas) del mes. 
                  " > Le </th>
                <th title=" Nivel sonoro medio a largo plazo ponderado A, definido determinado a lo largo de todos
                  los períodos noche (de 23 a 7 horas) del mes. 
                  " > Ln </th>
                 </tr></thead>'; 
}
else{
  $response .='<thead><tr>
                <th> Nombre </th>
                <th> Fecha </th>
                <th title="  Nivel sonoro medio a largo plazo ponderado A, determinado a lo largo de todos los
                  períodos diarios (24 horas) del mes. 
                  " > LAeq24 </th>
                <th title="  Es el nivel de presión sonora con ponderación frecuencial A y ponderación temporal
                  Slow, que se sobrepasa durante el 1% del tiempo de observación. 
                   " > LAS01 </th>
                <th title=" Es el nivel de presión sonora con ponderación frecuencial A y ponderación temporal
                  Slow, que se sobrepasa durante el 10% del tiempo de observación. 
               " > LAS10 </th>
                <th title=" Es el nivel presión sonora con ponderación frecuencial A y ponderación temporal Slow,
                  que se sobrepasa durante el 50% del tiempo de observación
                  " > LAS50 </th>
                <th title=" Es el nivel de presión sonora con ponderación frecuencial A y ponderación temporal
                  Slow, que se sobrepasa durante el 90% del tiempo de observación.  " > LAS90 </th>
                <th title=" Es nivel de presión sonora con ponderación frecuencial A y ponderación temporal
                  Slow, que se sobrepasa durante el 99% del tiempo de observación.  " > LAS99 </th>
                </tr></thead><tbody>';
}
$num=0;
$total=0;
foreach ($result as $row) {
  if(!isset($_GET['estat'])){
    if($row->date == $fecha){
      $response .= "<tr><td>". 
        $row->name . '</td> <td>' . 
        $row->date . '</td> <td>';
      $response .= 
        $row->Ld . '</td> <td>' . 
        $row->Le .'</td> <td>' . 
        $row->Ln . '</td> </tr>';

    }
  }
  else{
    if($row->date == $fecha){
      $response .= "<tr><td>". 
        $row->name . '</td> <td>' . 
        $row->date . '</td> <td>';
      $response .= 
        $row->LAeq24 . '</td> <td>' . 
        $row->LAS01 . '</td> <td>' . 
        $row->LAS10 . '</td> <td>' . 
        $row->LAS50 .'</td> <td>' . 
        $row->LAS90 .'</td> <td>' . 
        $row->LAS99 .'</td></tr>';
	$num+=1;
	$total+=(int) (string)$row->LAeq24;
	
    }
  }
}

$response .='</tbody></table>';
$response2='<table id="tabla4" class="w3-table w3-border w3-bordered w3-centered w3-table-all infoTable"><thead><tr>
                <th> Barrio más ruidoso del mes</th><th> Contaminación   diaria </th></thead></tr><tbody><tr><td>';
$response3='<table id="tabla4" class="w3-table w3-border w3-bordered w3-centered w3-table-all infoTable"><thead><tr>
                <th> Barrio menos ruidoso del mes </th><th> Contaminación   diaria </th></thead></tr><tbody><tr><td>';

foreach ($result2 as $row) {
$response2.= $row->name.'</td> <td>'.$row->cont.'</td></tr><tbody></table>';
  }
foreach ($result3 as $row) {
$response3.= $row->name.'</td> <td>'.$row->cont.'</td></tr><tbody></table>';
  }

if(isset($_GET['estat'])){
echo '<table id="tabla4" class="w3-table w3-border w3-bordered w3-centered w3-table-all infoTable"><thead><tr>
                <th> Media ruido diario del mes</th></tr><tbody><tr><td>'.round($total/$num,1).'</td></tr><tbody></table>';
echo $response2;
echo $response3;
}
echo $response;


?>
