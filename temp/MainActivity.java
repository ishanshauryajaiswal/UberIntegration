package shaurya.ngenassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
/*
        Cuisine cuisine1= new Cuisine(),cuisine2= new Cuisine(),cuisine3= new Cuisine(),cuisine4 = new Cuisine();
        cuisine1.setId(1);
        cuisine1.setName("Indian");
        cuisine2.setId(2);
        cuisine2.setName("Mexican");
        cuisine3.setId(3);
        cuisine3.setName("Italian");
        cuisine4.setId(4);
        cuisine4.setName("Chinese");
        databaseHelper.addCuisine(cuisine1);
        databaseHelper.addCuisine(cuisine2);
        databaseHelper.addCuisine(cuisine3);
        databaseHelper.addCuisine(cuisine4);


        Dish dish1 = new Dish(1,1,"Chicken1","200");
        Dish dish2 = new Dish(2,1,"Chicken2","100");
        Dish dish3 = new Dish(3,1,"Chicken3","100");
        Dish dish4 = new Dish(4,1,"Chicken4","200");
        Dish dish5 = new Dish(5,2,"TACO","150");
        Dish dish6 = new Dish(6,3,"LASAGNE","500");
        Dish dish7 = new Dish(7,4,"CHOWMEIN","50");
        databaseHelper.addDish(dish1);databaseHelper.addDish(dish2);databaseHelper.addDish(dish3);databaseHelper.addDish(dish4);
        databaseHelper.addDish(dish5);databaseHelper.addDish(dish6);databaseHelper.addDish(dish7);
*/
        List<Cuisine> cuisines = databaseHelper.getCuisine();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_cuisine);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SimpleRVAdapter(cuisines));
        Log.d("2",databaseHelper.getDish(1).get(0).getName());
    }
}
