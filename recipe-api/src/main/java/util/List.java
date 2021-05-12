package util;

//Java implements parametric polymorphism
//We don't know what parameters List will implement so we
//Simply write T (it could be anything) to represent anything
public interface List<T> extends Collection<T>{

    //interfaces do not have constructors

    //all fields declared within interfaces are implicitly
    //public, static and final

    //all method stubs declared within interfaces are implicitly
    //public and abstract


    T get(int index);

    //interfaces can have methods with implementations
    //in the form of other static or default methods

    //static methods cannot be overridden (but you can shadow it)
    static void staticMethod(){
        System.out.println("This is a static method declared within an interface." );
    }

    //since Java 8 you can declare default methods
    //which provide a, well, default implementation
    //but can be overridden by lower level classes

    //You can override this method
    default void defaultMethod(){
        System.out.println("This is a default method that can be overridden!");
    }
}
