package com.example.reminder.View;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reminder.R;
import com.example.reminder.data.Cal;

import java.util.ArrayList;
import java.util.List;

public class CalAdapter extends RecyclerView.Adapter<CalAdapter.CalViewHolder> {

    private final CalAdapter.CalAdapterEventListener eventListener;

    private List<Cal> calList;

    public CalAdapter(CalAdapter.CalAdapterEventListener eventListener){
        this.eventListener = eventListener;
        this.calList = new ArrayList<>();
    }


    @NonNull
    @Override
    public CalAdapter.CalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cal_row, parent,false);

        return new CalViewHolder(layout, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CalViewHolder holder, int position) {
        Cal cal = this.calList.get(position);

        holder.setName(cal.getCalName());
        holder.setText(cal.getCalText());
        holder.setCalDate(cal.getCalDate());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onCalClicked(cal.getCalId());

            }
        });

        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                eventListener.onCalLongClicked(cal.getCalId());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.calList.size();
    }
    public void updateList(List<Cal> allCals){
        this.calList = allCals;
        notifyDataSetChanged();

    }

    public class CalViewHolder extends  RecyclerView.ViewHolder {
        private View rootView;
        private TextView calName;
        private TextView calText;
        private TextView calDate;
        private Context context;

        public CalViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.calName = rootView.findViewById(R.id.nameCal);
            this.calText = rootView.findViewById(R.id.descCal);
            this.calDate = rootView.findViewById(R.id.dateCal);
        }

        public void setName(String name) {this.calName.setText(name);}

        public void setText(String text){this.calText.setText(text);}

        public void setCalDate(String date) {this.calDate.setText(date);}
    }

    public interface CalAdapterEventListener {
        void onCalClicked(long calId);
        void onCalLongClicked(long calId);
    }
}
