package jureczko.page.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut(value = "@annotation(ForLogging)")
    public void doLogging(){
    }

    @Before("doLogging()")
    public void logMethodCall(JoinPoint joinPoint){
        StringBuilder message = new StringBuilder("LOGGER method: ");
        message.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if(null!=args){
            message.append(" args=[ | ");
            Arrays.asList(args).forEach( a ->{
                message.append(a).append(" | ");
            });
        }
        LOGGER.info(message.toString());
    }


}
