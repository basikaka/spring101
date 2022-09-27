package org.hogrider.learnaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Helper {

    /**
     * 切点
     */
    @Pointcut("execution( public void org.hogrider.learnaop.Alien.show())")
    public void alienPointCut(){}

    @Before( "alienPointCut()")
    public void beforeShow(){
        System.out.println("before: Hello big show. --- 前");
    }

    @After( "alienPointCut()")
    public void afterShow(){
        System.out.println("after: The show end. --- 后");
    }

    @AfterReturning( "alienPointCut()")
    public void afterReturnShow(){
        System.out.println("returning: Orderly leaving. --- 正常返回");
    }

    @Around( "alienPointCut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[Aspect1] around advise 1");
        pjp.proceed();
        System.out.println("[Aspect1] around advise2");

    }
}
