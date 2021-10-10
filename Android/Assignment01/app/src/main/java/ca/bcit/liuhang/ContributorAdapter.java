package ca.bcit.liuhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ContributorAdapter extends RecyclerView.Adapter<ContributorAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    private Context context;
    private ArrayList<Contributor> contributors;

    public ContributorAdapter(Context context, ArrayList<Contributor> contributors) {
        this.context = context;
        this.contributors = contributors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(context)
                .inflate(R.layout.card_item, parent, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;

        Contributor currentItem = contributors.get(position);

        TextView login = cardView.findViewById(R.id.login);
        ImageView avatar = cardView.findViewById(R.id.avatar);

        login.setText(currentItem.getLogin());

        if (currentItem.getAvatar_url() != null) {
            Picasso.with(context)
                    .load(currentItem.getAvatar_url())
                    .fit()
                    .centerInside()
                    .into(avatar);
        }
    }

    @Override
    public int getItemCount() {
        return contributors.size();
    }
}
