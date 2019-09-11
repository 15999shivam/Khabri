package shivam.example.khabri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity implements CatagotyAdapter.OnCatagorySelect, NewsAdapter.OnNewsSelect {

   public String [] news = {"hardik pajj gya","hardik ne bra pali","hardik shi ch dick hai","sdddddddddddddd","zdssssssssssss"};
   public String [] catagory = {"General","Health","Science","Sports","Technology","Business","Entertainment"};
    public RecyclerView newsList;

    public RecyclerView Catagory;
    private static final String URL1 = "https://newsapi.org/v2/top-headlines?country=in&category=";
    public static final String URL2 = "&apiKey=ENTER_YOUR_API_KEY";
    public static String URL = URL1+"General"+URL2;
   public StringRequest request;
   public RequestQueue queue;
   public GsonBuilder gsonBuilder= new GsonBuilder();
   public Gson gson = gsonBuilder.create();
   public String jadaKhabar="";
    public News khabar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsList = findViewById(R.id.newslist);
        newsList.setLayoutManager(new LinearLayoutManager(this));


        Catagory= findViewById(R.id.Catagory_View);
        Catagory.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Catagory.setAdapter(new CatagotyAdapter(catagory,this));

        request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("fromRequest",response);
                 khabar = gson.fromJson(response,News.class);
                 jadaKhabar = response;
                Log.i("khabar",khabar.getTotalResults().toString());
                setkhabar(khabar);
                //newsList.setAdapter(new NewsAdapter(khabar,this));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        queue = Volley.newRequestQueue(this);
        queue.add(request);



    }
    public void setkhabar(News khabar){
        newsList.setAdapter(new NewsAdapter(khabar,this));
    }


    @Override
    public void onCatagoryClick(int position) {
        Log.i("FromMain",catagory[position]);

        URL = URL1+catagory[position].toLowerCase()+URL2;

        request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jadaKhabar = response;
                Log.i("fromRequest1",response);
                News khabar = gson.fromJson(response,News.class);
                Log.i("khabar",khabar.getTotalResults().toString());
                setkhabar(khabar);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        queue = Volley.newRequestQueue(this);
        queue.add(request);

        //Catagory.setAdapter(new CatagotyAdapter(news,this));
    }

    @Override
    public void onNewsClick(int position) {
        try {
            Intent intent = new Intent(this, SportsActivity.class);
            intent.putExtra("data", jadaKhabar);
            intent.putExtra("int", position);

            startActivity(intent);
        }
        catch(Exception E)
        {
            Log.i("IntentError",E.getMessage());
        }
    }
}
