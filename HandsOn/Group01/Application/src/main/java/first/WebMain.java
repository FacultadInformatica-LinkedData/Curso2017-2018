package first;


import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class WebMain extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }
}
