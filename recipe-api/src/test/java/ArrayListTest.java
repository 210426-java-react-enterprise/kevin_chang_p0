import org.junit.Assert;
import org.junit.Test;
import recipes.Recipe;
import util.ArrayList;

public class ArrayListTest {


    @Test
    public void test_ArrayListAddString(){
        //Arrange
        ArrayList<String> stringSut = new ArrayList<>(2);
        String expectedString = "hello world";

        //Act
        stringSut.add("hello world");

        //Assert
        System.out.println(stringSut.get(0));
        System.out.println(stringSut.get(1));
        Assert.assertEquals(stringSut.get(0), expectedString);
    }

    @Test
    public void test_ArrayListDoubleStorageArray(){
        //Arrange
        ArrayList<String> stringSut = new ArrayList<>(2);
        int expectedSize = 5;

        //Act
        stringSut.add("hello world");
        stringSut.add("bye world");
        stringSut.add("good friend");
        stringSut.add("bad friend");
        stringSut.add("null value");


        //Assert
        Assert.assertEquals(expectedSize, stringSut.size());

    }

    @Test
    public void test_ArrayListAddRecipe(){
        //Arrange
        ArrayList<Recipe> recipeSut = new ArrayList<>();
        Recipe recipe =
        //Act

        //Assert

    }


}