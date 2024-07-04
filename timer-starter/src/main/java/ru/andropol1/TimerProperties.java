package ru.andropol1;

import lombok.Data;
import org.apache.logging.log4j.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("aspect")
@Data
public class TimerProperties {
	/**
	 * Включение/выключение логирования методов таймера
	 */
	private boolean timerSwitcher = false;
	/**
	 * Уровень логированмя
	 */
	private Level logLevel = Level.WARN;
}
