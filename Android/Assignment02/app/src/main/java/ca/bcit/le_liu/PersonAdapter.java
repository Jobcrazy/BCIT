package ca.bcit.le_liu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<Person> mData;

    public PersonAdapter(ArrayList<Person> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtID.setText(mData.get(position).id);
        holder.txtGender.setText(mData.get(position).Sex);
        holder.txtAge.setText(mData.get(position).Age_Group);
        holder.txtHA.setText(mData.get(position).HA);
        holder.txtClass.setText(mData.get(position).Classification_Reported);
        holder.txtDate.setText(mData.get(position).Reported_Date);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtID;
        TextView txtAge;
        TextView txtGender;
        TextView txtHA;
        TextView txtClass;
        TextView txtDate;

        public ViewHolder(View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.id);
            txtAge = itemView.findViewById(R.id.age);
            txtGender = itemView.findViewById(R.id.gender);
            txtHA = itemView.findViewById(R.id.ha);
            txtClass = itemView.findViewById(R.id.classification);
            txtDate = itemView.findViewById(R.id.date);
        }
    }
}
