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
    //boolean contains (T data);
    int size();


}
