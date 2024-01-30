import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {

    @Pointcut("execution(* MathService.*(..))")
    public void mathServiceMethods() {
    }

    @Before("mathServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before " + joinPoint.getSignature().toShortString());
    }

    @After("mathServiceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "mathServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("AfterReturning " + joinPoint.getSignature().toShortString() + ", Result: " + result);
    }

    @AfterThrowing(pointcut = "mathServiceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("AfterThrowing " + joinPoint.getSignature().toShortString() + ", Exception: " + exception.getMessage());
    }

    @Around("mathServiceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around (Before) " + joinPoint.getSignature().toShortString());

        // Proceed with the original method invocation
        Object result = joinPoint.proceed();

        System.out.println("Around (After) " + joinPoint.getSignature().toShortString() + ", Result: " + result);
        return result;
    }
}
