var responseJSON;

$(document).ready(function(){
    // peticion htttp get 
    var url = "http://ec2-34-239-122-250.compute-1.amazonaws.com/sparql?default-graph-uri=http%3A%2F%2Fec2-34-239-122-250.compute-1.amazonaws.com%3A80%2FDAV&query=SELECT+DISTINCT+%3FNombreBiblioteca%0D%0AWHERE+%7B%0D%0A++++%3Fa+a+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fgroup08%2Fresources%2FLibrary%3E+.%0D%0A++++%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasName%3E+%3FNombreBiblioteca+.%0D%0A+++%7D%0D%0AORDER+BY+%3Fa&should-sponge=&format=application%2Fsparql-results%2Bjson&timeout=0&debug=on"
    
    $.get(url, function (data, status){
        responseJSON = data;
        var items = [];
        
           $.each(responseJSON.results.bindings, function(i, item) {
                  items.push('<li><a href="'+getBibliotecaHTML_URL(item.NombreBiblioteca.value)+'">' + item.NombreBiblioteca.value + '</a></li>');
           });  // close each()
        
           $('#bilbiotecas').append( items.join('') );


    })

    function getBibliotecaHTML_URL(biblioteca){ 
        var toGoUrl = "http://ec2-34-239-122-250.compute-1.amazonaws.com/sparql?default-graph-uri=&query=SELECT+DISTINCT+%3FNombreBiblioteca+%3FProgramaBiblioteca+%3FDireccionBiblioteca+%3FTelefonoBiblioteca+%3FEmailbiblioteca+%3FNombreActividad+%3FFechaInicioActividad+%3FFechaFinActividad+%3FURLActividad%0D%0AWHERE+%7B%0D%0A++++%3Fa+a+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fgroup08%2Fresources%2FLibrary%3E+.%0D%0A++++%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasName%3E+%22"
		+biblioteca.replace(/ /g,'+')
        +"%22+.%0D%0A++++%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasName%3E+%3FNombreBiblioteca++.%0D%0A++++OPTIONAL+%7B+%3Fa+%3Chttp%3A%2F%2Fwww.semanticweb.es%2Fgroup08%2Fontology%23hasSchedule%3E+%3FProgramaBiblioteca+%7D.%0D%0A++++OPTIONAL+%7B+%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasAddress%3E+%3FDireccionBiblioteca+%7D.%0D%0A++++OPTIONAL+%7B+%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasTelephone%3E+%3FTelefonoBiblioteca+%7D.%0D%0A++++OPTIONAL+%7B+%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasEmail%3E+%3FEmailbiblioteca+%7D.%0D%0A++++%3Fb+a+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fgroup08%2Fresources%2FActivity%3E+.%0D%0A++++%3Fb+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23fn%3E+%3FNombreActividad+.%0D%0A++++OPTIONAL+%7B+%3Fb+%3Chttp%3A%2F%2Fwww.w3.org%2F2012%2F12%2Fcal%23dtstart%3E+%3FFechaInicioActividad+%7D.%0D%0A%09OPTIONAL+%7B+%3Fb+%3Chttp%3A%2F%2Fwww.w3.org%2F2012%2F12%2Fcal%23dtend%3E+%3FFechaFinActividad+%7D.++++%0D%0A++++OPTIONAL+%7B+%3Fb+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasUrl%3E+%3FURLActividad+%7D.%0D%0A+++%7D%0D%0AORDER+BY+%3FNombreActividad&should-sponge=&format=text%2Fhtml&timeout=0&debug=on"

        return toGoUrl;
    }

    function getBibliotecaJSON_URL(biblioteca){ 
        var toGoUrl = "http://ec2-34-239-122-250.compute-1.amazonaws.com/sparql?default-graph-uri=http%3A%2F%2Fec2-34-239-122-250.compute-1.amazonaws.com%3A80%2FDAV&query=SELECT+DISTINCT+%3FNombreBiblioteca+%3FProgramaBiblioteca+%3FDireccionBiblioteca+%3FTelefonoBiblioteca+%3FNombreActividad+%3FFechaInicioActividad+%3FFechaFinActividad+%3FURLActividad%0D%0AWHERE+%7B%0D%0A++++%3Fa+a+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fgroup08%2Fresources%2FLibrary%3E+.%0D%0A++++%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasName%3E+%22"+
        biblioteca.replace(/ /g,'+')
        +"%22+.%0D%0A++++%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasName%3E+%3FNombreBiblioteca++.%0D%0A++++%3Fa+%3Chttp%3A%2F%2Fwww.semanticweb.es%2Fgroup08%2Fontology%23hasSchedule%3E+%3FProgramaBiblioteca+.%0D%0A++++%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasAddress%3E+%3FDireccionBiblioteca+.%0D%0A++++%3Fa+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasTelephone%3E+%3FTelefonoBiblioteca.%0D%0A++++%3Fb+a+%3Chttp%3A%2F%2Fwww.semanticweb.org%2Fgroup08%2Fresources%2FActivity%3E+.%0D%0A++++%3Fb+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23fn%3E+%3FNombreActividad+.%0D%0A++++%3Fb+%3Chttp%3A%2F%2Fwww.w3.org%2F2012%2F12%2Fcal%23dtstart%3E+%3FFechaInicioActividad+.%0D%0A%09%3Fb+%3Chttp%3A%2F%2Fwww.w3.org%2F2012%2F12%2Fcal%23dtend%3E+%3FFechaFinActividad+.++++%0D%0A++++%3Fb+%3Chttp%3A%2F%2Fwww.w3.org%2F2006%2Fvcard%2Fns%23hasUrl%3E+%3FURLActividad+.%0D%0A+++%7D%0D%0AORDER+BY+%3Fa&should-sponge=&format=application%2Fsparql-results%2Bjson&timeout=0&debug=on"
    
        return toGoUrl;
    }
});

