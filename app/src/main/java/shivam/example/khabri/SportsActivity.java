package shivam.example.khabri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SportsActivity extends AppCompatActivity {
    public GsonBuilder gsonBuilder= new GsonBuilder();
    public Gson gson = gsonBuilder.create();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        try{
            Intent intent = getIntent();
            String response = intent.getStringExtra("data");
            int position = intent.getIntExtra("int",0);
            Log.i("daata",response);
            News khabar = gson.fromJson(response,News.class);
            ImageView img = findViewById(R.id.Image);
            TextView title = findViewById(R.id.title);
            TextView description = findViewById(R.id.description);
            TextView content = findViewById(R.id.content);
            List<Article> khabare = khabar.getArticles();
            Glide.with(this).load(khabare.get(position).getUrlToImage()).into(img);
            title.setText(khabare.get(position).getTitle());
            description.setText(khabare.get(position).getDescription());
            content.setText(khabare.get(position).getContent());
            Log.i("content",khabare.get(position).getContent());
        }catch(Exception E ){
            Log.i("Activity",E.toString());
        }

    }
}
