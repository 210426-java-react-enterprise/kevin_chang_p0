package daos;

import models.AppUser;
import models.Recipe;
import util.ArrayList;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    //Returns an int[] array of ingredient_id's of the ingredients that were sent through the method
    public int[] saveIngredients(ArrayList<String> ingredientArray){
        //Stores the ingredient_id of every ingredient that has been added, or is duplicate, into this array
        //This array will be used to persist into the FK table
        int[] ingredientIDArray = new int[ingredientArray.size()];

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertIngredient = "insert into ingredients ( ingredient ) values (?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertIngredient, new String[] { "ingredient_id" });;

            for (int i = 0; i < ingredientArray.size(); i++) {
                if(!isIngredientDuplicate(ingredientArray.get(i))){
                    pstmt.setString(1, ingredientArray.get(i));
                    pstmt.executeUpdate();
                }

                ingredientIDArray[i] = getIngredientId(ingredientArray.get(i));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //ultimately should return an int[] of all the ingredient_id's of the ingredients put here
        return ingredientIDArray;
    }

    public int getIngredientId(String ingredient){
        int ingredient_id = 0;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select ingredient_id from ingredients where ingredient = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ingredient);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                ingredient_id = rs.getInt("ingredient_id");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


        return ingredient_id;
    }

    public boolean isIngredientDuplicate(String ingredient){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlQueryDuplicates = "select * from ingredients where ingredient = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlQueryDuplicates);
            pstmt.setString(1, ingredient);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    //Returns an int[] array of recipe_id's of the recipes that were sent through the method
    public int[] saveRecipes(ArrayList<Recipe> recipeArray){
        //Stores the ingredient_id of every ingredient that has been added, or is duplicate, into this array
        //This array will be used to persist into the FK table
        int[] recipeIDArray = new int[recipeArray.size()];

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertRecipe = "insert into recipes ( recipe_name, recipe_url ) values (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertRecipe, new String[] { "recipe_id" });

            for (int i = 0; i < recipeArray.size(); i++) {
                if(!isRecipeDuplicate(recipeArray.get(i).getName(), recipeArray.get(i).getUrl())){
                    pstmt.setString(1, recipeArray.get(i).getName());
                    pstmt.setString(2, recipeArray.get(i).getUrl());
                    pstmt.executeUpdate();
                }

                recipeIDArray[i] = getRecipeId(recipeArray.get(i).getName(), recipeArray.get(i).getUrl());
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //ultimately should return an int[] of all the ingredient_id's of the ingredients put here
        return recipeIDArray;
    }

    public boolean isRecipeDuplicate(String name, String url){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlQueryDuplicates = "select * from recipes where recipe_name = ? and recipe_url = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sqlQueryDuplicates);
            pstmt.setString(1, name);
            pstmt.setString(2, url);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public int getRecipeId(String name, String url){
        int recipe_id = 0;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select recipe_id from recipes where recipe_name = ? and recipe_url = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, url);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                recipe_id = rs.getInt("recipe_id");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


        return recipe_id;
    }

    public void persistFKToRecipeIngredientTable(int[] recipeIdArray, int[] ingredientIdArray){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into recipe_ingredient_table (recipe_id, ingredient_id) values (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < recipeIdArray.length; i++) {
                pstmt.setInt(1, recipeIdArray[i]);
                for (int j = 0; j < ingredientIdArray.length; j++) {
                    pstmt.setInt(2, ingredientIdArray[j]);

                    //checks to ensure that the key is not already in the table
                    if(!isRecipeIngredientKeyDuplicate(recipeIdArray[i], ingredientIdArray[j])) {
                        pstmt.executeUpdate();
                    }
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean isRecipeIngredientKeyDuplicate(int recipe_id, int ingredient_id){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlQueryDuplicates = "select * from recipe_ingredient_table where recipe_id = ? and ingredient_id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sqlQueryDuplicates);
            pstmt.setInt(1, recipe_id);
            pstmt.setInt(2, ingredient_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public AppUser saveUser(AppUser newUser) {

        //the connection is constructed as a Singleton of the ConnectionFactory class, in a static context
        //Connection exists and is accessed only through the Class
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertUser = "insert into users ( first_name , last_name, username , password , email ) values (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] { "user_id" });
            pstmt.setString(1,newUser.getFirstName());
            pstmt.setString(2,newUser.getLastName());
            pstmt.setString(3,newUser.getUsername());
            pstmt.setString(4,newUser.getPassword());
            pstmt.setString(5,newUser.getEmail());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setUser_id(rs.getInt("user_id"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return newUser;
    }

    public boolean  isUsernameAvailable(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    public boolean isEmailAvailable(String email) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from users where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public AppUser findUserByUsernameAndPassword(String username, String password) {

        AppUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new AppUser();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

}
