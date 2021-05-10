package screens;

abstract public class Screen {
    protected String name;
    protected String route;

    public Screen(String name, String route){
        this.name = name;
        this.route = route;
    }

    public String getName(){
        return name;

    }

    public String getRoute(){
        return route;
    }
    //unlike interfaces, you need to implictly declare abstract methods
    public abstract void render();

}
