package com.vsga.app.finalprojectvsga.restapi.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vsga.app.finalprojectvsga.R;
import com.vsga.app.finalprojectvsga.restapi.models.EmployeModel;

import java.util.List;

public class EmployeRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final OnEmployeListener onEmployeListener;
    private List<EmployeModel> employeList;

    public EmployeRecycleView(OnEmployeListener onEmployeListener) {
        this.onEmployeListener = onEmployeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employe_list, parent, false);
        return new EmployeViewHolder(view, onEmployeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String salary = String.valueOf(employeList.get(position).getSalary());
        ((EmployeViewHolder) holder).name.setText(employeList.get(position).getName());
        ((EmployeViewHolder) holder).position.setText(employeList.get(position).getPosition());
        ((EmployeViewHolder) holder).salary.setText(String.format("Rp%s", salary));
        Glide.with(holder.itemView.getContext()).load("https://picsum.photos/200").into(((EmployeViewHolder) holder).poster);

    }

    @Override
    public int getItemCount() {
        if (employeList != null) {
            return employeList.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setEmploye(List<EmployeModel> list) {
        this.employeList = list;
        notifyDataSetChanged();
    }

    public EmployeModel getSelectedEmploye(int pos) {
        if (employeList != null) {
            if (employeList.size() > 0) {
                return employeList.get(pos);
            }
        }
        return null;
    }
}
