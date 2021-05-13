
import org.junit.*;
import util.ArrayList;
import util.LinkedList;

import java.util.NoSuchElementException;

public class LinkedListTest {

    private LinkedList<String> sut;

    @Before
    public void setUpTest() {
        sut = new LinkedList<>();
    }

    @After
    public void tearDownTest() {
        sut = null;
    }

    @Test
    public void test_addWithNonNullValue() {
        // Arrange (prepare the test)
        int expectedSize = 1;

        // Act (do the test)
        sut.add("data");

        // Assert (ensure the results)
        int actualSize = sut.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = Exception.class)
    public void test_addWithNullValue() {
        // Arrange
        // sometimes blank if there's nothing in particular to do

        // Act
        sut.add(null);

        // Assert
        // sometimes blank, especially if you expect an exception to be thrown
    }

    @Test
    public void test_pollWithEmptyList() {
        // Arrange
        // nothing to do here...

        // Act
        String actualResult = sut.poll();

        // Assert
        Assert.assertNull(actualResult);
    }

    @Test
    public void test_pollWithPopulatedList() {
        // Arrange
        sut.add("test data 1");
        sut.add("test data 2");
        String expectedResult = "test data 1";
        int expectedSize = 1;

        // Act
        String actualResult = sut.poll();

        // Assert
        int actualSize = sut.size();
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getNodeWithInvalidIndex(){
        //Arrange
        sut.add("test data");

        //Act
        sut.get(-1);

    }

    @Test
    public void test_getNodeWithValidIndex(){
        //Arrange
        String expected = "test data";
        String expected2 = "test data 2";
        sut.add(expected);
        sut.add(expected2);


        //Act
        String result = sut.get(0);
        String result2 = sut.get(1);

        //Assert
        Assert.assertEquals(expected, result);
        Assert.assertEquals(expected2, result2);


    }

    @Test
    public void test_getNodeWithNullList(){
        //Assert
        Assert.assertNull(sut.get(0));
    }


//    // TODO: (Associate task) implement this method!
//    @Test
//    public void test_peekWithEmptyList() {
//
//    }
//
//    // TODO: (Associate task) implement this method!
//    @Test
//    public void test_peekWithPopulatedList() {
//
//    }


    @Test
    public void test_containsWithEmptyList() {
        //Arrange
        LinkedList<String> sut = new LinkedList<>();

        //Assert
        Assert.assertFalse(sut.contains("any String"));
    }


    @Test
    public void test_containsWithPopulatedList() {
//Arrange
        LinkedList<String> sut = new LinkedList<>();
        sut.add("hello");
        sut.add("world");
        sut.add("i am");
        sut.add("kevin");

        //Assert
        Assert.assertTrue(sut.contains("i am"));
        Assert.assertFalse(sut.contains("nothing"));
    }

    @Test
    public void test_removeWithExistingData(){
        //Arrange
        LinkedList<String> sut = new LinkedList<>();
        sut.add("hello");
        sut.add("world");
        sut.add("bye");
        sut.add("earth");

        //Assert
        Assert.assertTrue(sut.remove("world"));
    }

    @Test
    public void test_removeWithNonExistingData(){
        //Arrange
        LinkedList<String> sut = new LinkedList<>();
        sut.add("hello");
        sut.add("world");

        //Assert
        Assert.assertFalse(sut.remove("non existent"));
    }

    @Test
    public void test_iteratorHasNextWithFilledList(){
        LinkedList<String> sut = new LinkedList<>();
        sut.add("hello");
        sut.add("my");
        sut.add("beautiful");
        sut.add("world");

        String expectedString = "hello";

        Assert.assertTrue(sut.iterator().hasNext());
        Assert.assertEquals(expectedString, sut.iterator().next());

    }

    @Test(expected = NoSuchElementException.class)
    public void test_iteratorNextWithEmptyList(){
        LinkedList<String> sut = new LinkedList<>();
        sut.iterator().next();
    }



}
