package BusApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    AppService appService;

    @RequestMapping("/")
    public ModelAndView index()
    {
        List<Integer> stats = appService.estadisticas();
        return new ModelAndView("index").addObject("stats", stats);
    }

    @RequestMapping("/routes")
    public ModelAndView routes(){
        List<Route> rutas = appService.listarRutas();
        return new ModelAndView("routes").addObject("rutas", rutas);
    }

    @RequestMapping("/times")
    public ModelAndView times(){
        return new ModelAndView("times");
    }

    @RequestMapping("/stops")
    public ModelAndView stops(){
        return new ModelAndView("stops");
    }

    @RequestMapping("/timetable")
    public ModelAndView timetable(){
        return new ModelAndView("timetable");
    }

    @RequestMapping("/search")
    public ModelAndView searchBusStop(@RequestParam String idParada){
        BusStop busStop = appService.infoParada(idParada);
        if(busStop == null){
            return new ModelAndView("/error");
        }
        return new ModelAndView("/search").addObject("busStop",busStop);
    }
}