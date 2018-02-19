package hu.pemik.dcs.restserver;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 * @author pekmil
 */
public class ApplicationMain {

    public static void main(String[] args) {
        try{
            ServiceServer server = new ServiceServer();
            
        }
        catch(Throwable t) {
            System.err.println("Unhandled runtime exception:\n" + ExceptionUtils.getStackTrace(t));
        }
    }
}
