
package SingletonLog;

/**
 * Clase que contiene las instancias a dos Log singletons.
 * 
 */
public class Log {
    
    public static Logger log = Logger.getInstance("log");
    public static LoggerBd logBd = LoggerBd.getInstance("logBd");
    
}
