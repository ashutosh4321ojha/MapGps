 package com.example.ashutosh.mapgps.adapter;

/**
 * Created by ashutosh on 1/28/2016.
 */

 import android.app.Activity;
 import android.content.Intent;
 import android.support.v7.widget.RecyclerView;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.TextView;

 import com.example.ashutosh.mapgps.R;
 import com.example.ashutosh.mapgps.activity.MainActivity;
 import com.example.ashutosh.mapgps.model.autocomplete.Prediction;

 import java.util.List;


 public class RecyclerAdapterUserData extends RecyclerView.Adapter<RecyclerAdapterUserData.ViewHolder> {
     Activity context;
     List<Prediction> locationList;



     public RecyclerAdapterUserData(Activity context, List<Prediction> data) {

         this.context=context;
        locationList=data;
     }




     @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

       View itemLayoutView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.recycler_view_custom_list, null);


        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
         return viewHolder;
     }

     @Override
     public void onBindViewHolder(final ViewHolder viewHolder, int position) {



        viewHolder.tvCustomName.setText(locationList.get(position).getDescription());


         viewHolder.pos=position;
         viewHolder.itemView.setTag(viewHolder);
         viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                ViewHolder viewHolder=(ViewHolder)v.getTag();
                // MainActivity.place= locationList.get(viewHolder.pos).getDescription();
                 Log.d("Place in adapter", String.valueOf(locationList.get(viewHolder.pos).getDescription()));
                 Intent intent=new Intent(context,MainActivity.class);
                 intent.putExtra("place",locationList.get(viewHolder.pos).getDescription());
                 context.startActivity(intent);


             }
         });

     }

     public class ViewHolder extends RecyclerView.ViewHolder {

        int pos;
         TextView tvCustomName;


         public ViewHolder(View itemLayoutView) {
             super(itemLayoutView);

            tvCustomName= (TextView) itemLayoutView.findViewById(R.id.custom_list_name);



         }
     }


     @Override
     public int getItemCount() {
         return locationList.size() ;
     }

 }
