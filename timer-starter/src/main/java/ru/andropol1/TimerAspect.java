package ru.andropol1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
@RequiredArgsConstructor
public class TimerAspect {
	private final TimerProperties timerProperties;
		@Pointcut("within(@ru.andropol1.Timer *)")
	public void annotatedClass() {
	}

	@Pointcut("@annotation(ru.andropol1.Timer)")
	public void annotatedMethod() {
	}

	@Around("annotatedClass() || annotatedMethod()")
	public Object timerImpl(ProceedingJoinPoint joinPoint) throws Throwable {
		Long start = System.currentTimeMillis();
		try {
			Object res = joinPoint.proceed();
			Long end = System.currentTimeMillis() - start;
			log.info("Название класса: {}, название метода: {}, время выполнения: {}",
					joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), end);
			return res;
		} catch (Throwable e) {
			log.info(e.getMessage());
			throw e;
		}

	}
}
