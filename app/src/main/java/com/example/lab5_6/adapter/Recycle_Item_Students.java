package com.example.lab5_6.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_6.Model.Students;
import com.example.lab5_6.R;
import com.example.lab5_6.handle.Item_Student_Handle;
import com.example.lab5_6.services.HttpRequest;

import java.util.ArrayList;

public class Recycle_Item_Students extends RecyclerView.Adapter<Recycle_Item_Students.viewHolep> {

    private Context context;
    private ArrayList<Students> list;
    private Item_Student_Handle handle;
    private HttpRequest httpRequest;

    public Recycle_Item_Students(Context context, ArrayList<Students> list, Item_Student_Handle handle) {
        this.context = context;
        this.list = list;
        this.handle = handle;
    }

    @NonNull
    @Override
    public viewHolep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle, parent, false);
        return new viewHolep(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolep holder, int position) {
        httpRequest = new HttpRequest();
        Students students = list.get(position);
        holder.txtMasv.setText(students.getMasv());
        holder.txtHoten.setText(students.getHoten());
        holder.txtDiem.setText(students.getDiem());
        holder.img_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xoá không")
                        .setCancelable(false)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                handle.Delete(students.getId());
                                Toast.makeText(context, "Xoá Thành Công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                handle.Update(students.getId(), students);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolep extends RecyclerView.ViewHolder {
        TextView txtMasv, txtHoten, txtDiem;
        ImageView img_Delete;

        public viewHolep(@NonNull View itemView) {
            super(itemView);
            txtMasv = itemView.findViewById(R.id.txtMasv);
            txtHoten = itemView.findViewById(R.id.txtHoten);
            txtDiem = itemView.findViewById(R.id.txtDiem);
            img_Delete = itemView.findViewById(R.id.img_delete);
        }
    }
}
