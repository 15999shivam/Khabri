package shivam.example.khabri;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private News khabar;
    private OnNewsSelect mOnNewsSelect;

    public NewsAdapter(News khabar,OnNewsSelect onNewsSelect){
        this.khabar =  khabar;
        this.mOnNewsSelect=onNewsSelect;
    }
    @Override
    public NewsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new NewsViewHolder(view,mOnNewsSelect);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        List<Article>   khabare =  khabar.getArticles();
        String title = khabare.get(position).getTitle();
        holder.textTitle.setText(title);
//        Log.i("urlimage",khabare.get(position).getTitle());
        Glide.with(holder.itemView.getContext()).load(khabare.get(position).getUrlToImage()).into(holder.imageIcon);
//        Log.i("urlimage",khabare.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        List<Article>   khabare =  khabar.getArticles();
        return khabare.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageIcon;
        TextView textTitle;
        OnNewsSelect onNewsSelect;
        public NewsViewHolder(View itemView,OnNewsSelect onNewsSelect){
            super( itemView);
            imageIcon = itemView.findViewById(R.id.img);
            textTitle = itemView.findViewById(R.id.textTitle);

            this.onNewsSelect=onNewsSelect;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try{
                onNewsSelect.onNewsClick(getAdapterPosition());
            }catch(Exception E)
            {
                Log.i("exception",E.getMessage());
            }

        }
    }
    public interface OnNewsSelect{
        void onNewsClick(int position);
    }
}
