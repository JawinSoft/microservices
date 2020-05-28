package  com.spring.boot.msk.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Slf4j
public class LogMethodExecutionTimeAspect {
	
	

	@Around("withMobileServiceAllMethods() || withMobileAccessoriesServiceAllMethods()")
	public Object  logEachMethodExecutionTime(ProceedingJoinPoint pjp) {
		
		long startTime = System.currentTimeMillis();
		
		Object returnValue = null;
		try {
			returnValue = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			
			throw new RuntimeException(e);
		}
		log.info("-");
		log.info("The Total Time take to process "+pjp.getSignature().toLongString()+" Method is  : "+(System.currentTimeMillis() - startTime) +" MilliSeconds");
		log.info("- ");
		return returnValue;
		
	}
	
	
	@Pointcut("within(com.spring.boot.msk.mobile..*)")
	public void withMobileServiceAllMethods() {}
	
	
	@Pointcut("within(com.spring.boot.msk.accessories..*)")
	public void withMobileAccessoriesServiceAllMethods() {}
	
	
	//@Pointcut("@annotation(com.spring.boot.msk.mobile.aop.LogMethodExecutionTime)")
	//public void logMethodExecutionTimePointCut() {}
	
}
