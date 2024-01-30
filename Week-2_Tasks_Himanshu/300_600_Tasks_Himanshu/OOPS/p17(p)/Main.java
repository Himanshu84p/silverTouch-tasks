import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LoggingAspect.class, MathService.class);

        MathService mathService = context.getBean(MathService.class);

        
        mathService.add(5, 3);
        try {
            mathService.divide(10, 2);
            mathService.divide(10, 0); 
        } catch (Exception e) {
            
        }

        context.close();
    }
}
