package com.vsga.app.finalprojectvsga.restapi.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vsga.app.finalprojectvsga.R;


public class EmployeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    TextView name, position, salary;
    ImageView poster;

    OnEmployeListener onEmployeListener;

    public EmployeViewHolder(@NonNull View itemView, OnEmployeListener onEmployeListener) {
        super(itemView);
        this.onEmployeListener = onEmployeListener;

        name = itemView.findViewById(R.id.name);
        position = itemView.findViewById(R.id.position);
        salary = itemView.findViewById(R.id.salary);
        poster = itemView.findViewById(R.id.poster);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onEmployeListener.onEmployeClick();
    }

    @Override
    public boolean onLongClick(View view) {
        onEmployeListener.onEmployeLongClick(getLayoutPosition());
        return false;
    }
}
