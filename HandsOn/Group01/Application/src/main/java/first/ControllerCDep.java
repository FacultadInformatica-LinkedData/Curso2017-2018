package first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ControllerCDep {
    @RequestMapping("/")
    public ModelAndView greeting() {

        return new ModelAndView("index");
    }


    @RequestMapping("/RecupForm")
    public ModelAndView ansForm(@RequestParam String distrito) {

        CentrosDeportivos obt = new CentrosDeportivos();
        ArrayList<CentroDeportivo> centros = obt.nombres(distrito);

        //System.out.println(distrito);
        return new ModelAndView( "resul" ).addObject("centros", centros);
    }

    @RequestMapping("/inicio")
    public ModelAndView returnIndex() {

        return new ModelAndView("index");
    }
}
