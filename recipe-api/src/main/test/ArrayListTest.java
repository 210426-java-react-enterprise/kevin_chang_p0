
import org.junit.Assert;
import org.junit.Test;
import models.Recipe;
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
        Recipe recipe = new Recipe("testname", "testlink.com");
        String expectedString = "testname testlink.com";

        //Act
        recipeSut.add(recipe);

        //Assert
        System.out.println("Recipe data: " + recipe.toString());
        Assert.assertEquals(expectedString, (recipe.getName() + " " + recipe.getUrl()));

    }

    @Test
    public void test_ArrayListZeroCapacityConstructor(){
        //Arrange
        ArrayList<String> sut = new ArrayList<>(0);

        int expectedSize = 0;

        //Act

        //Assert
        Assert.assertEquals(expectedSize, (sut.size()));

    }

    @Test
    public void test_ArrayListContains(){
        //Arrange
        ArrayList<String> stringSut = new ArrayList<>();
        //Act
        stringSut.add("hello world");
        stringSut.add("bye world");
        stringSut.add("good friend");
        stringSut.add("bad friend");
        stringSut.add("null value");

        //Act

        //Assert
        Assert.assertEquals(true, stringSut.contains("hello world"));
        Assert.assertEquals(true, stringSut.contains("null value"));
        Assert.assertEquals(false, stringSut.contains("incorrect"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ArrayListInvalidCapacity(){
        //Arrange
        ArrayList<String> sut = new ArrayList<>(-1);


    }

    @Test
    public void test_refactorStorageArrayWithNullValueInTheMiddle(){
        //Arrange
        ArrayList<String> sut = new ArrayList<>();
        sut.add("hello");
        sut.add("my");
        sut.add("beautiful");
        sut.add("world");

        int expectedSize = 3;
        String expectedString = "world";

        //Act
        sut.remove("beautiful");

        //Assert
        Assert.assertEquals(expectedSize, sut.size());
        Assert.assertEquals(expectedString, sut.get(2));
    }


}
