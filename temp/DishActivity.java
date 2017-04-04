package shaurya.ngenassignment;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ishan Shaurya Jaiswal.
 */

public class DishActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = Integer.parseInt(getIntent().getExtras().get("CUISINEID").toString());
        String name = getIntent().getExtras().get("CUISINENAME").toString();
        setContentView(R.layout.activity_dish);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        List<Dish> dishes = databaseHelper.getDish(id);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_dish);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DishRVAdapter(dishes));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_cart)
            Toast.makeText(getApplicationContext(),"Order",Toast.LENGTH_SHORT);
        return super.onOptionsItemSelected(item);
    }
}
