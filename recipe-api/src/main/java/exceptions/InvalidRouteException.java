package exceptions;

import org.omg.CORBA.DynAnyPackage.Invalid;

public class InvalidRouteException extends RuntimeException{
    public InvalidRouteException(String message){
        super(message);
    }
}
