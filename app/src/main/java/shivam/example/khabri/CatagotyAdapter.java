package shivam.example.khabri;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CatagotyAdapter extends RecyclerView.Adapter<CatagotyAdapter.CatagoryHolder> {


    private String[] data;
    private OnCatagorySelect mOnCatagorySelect;
    private int[] images = {R.drawable.newspaper,R.drawable.heart,R.drawable.artificialintelligence,R.drawable.soccer,R.drawable.projectmanagement,R.drawable.money,R.drawable.clapperboard};
   static public int index = 0;

    public CatagotyAdapter(String[] data,OnCatagorySelect onCatagorySelect){
        this.data =  data;
        this.mOnCatagorySelect = onCatagorySelect;
    }

    @Override
    public CatagoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.catagory_item_layout,parent,false);
        return new CatagoryHolder(view,mOnCatagorySelect);
    }

    @Override
    public void onBindViewHolder(@NonNull final CatagoryHolder holder, final int position) {
         final String title = data[position];
//        Log.i("errorapni",title);
        holder.textTitle.setText(title);
        holder.imageIcon.setImageResource(images[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index=position;
                mOnCatagorySelect.onCatagoryClick(position);
                notifyDataSetChanged();
            }
        });
        if(index==position){
            holder.itemView.setBackgroundResource(R.drawable.catagory_active_layout);

        }
        else
        {
            holder.itemView.setBackgroundResource(R.drawable.catagorybackround);
        }

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class CatagoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageIcon;
        TextView textTitle;

        OnCatagorySelect onCatagorySelect;

        public CatagoryHolder(View itemView,OnCatagorySelect onCatagorySelect){
            super( itemView);
            imageIcon = itemView.findViewById(R.id.imgCatagory);
            textTitle = itemView.findViewById(R.id.TextCatagory);
            this.onCatagorySelect = onCatagorySelect;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCatagorySelect.onCatagoryClick(getAdapterPosition());
        }
    }

    public interface OnCatagorySelect{
        void onCatagoryClick(int position);
    }
}
