package deepdive.cnm.edu.megamillions.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

public class PickAdapter extends RecyclerView.Adapter<PickAdapter.Holder> {

  private AppCompatActivity context;
  private List<int[]> picks;

  public PickAdapter(AppCompatActivity context, List<int[]> picks) {
    this.context = context;
    this.picks = picks;

  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context)
        .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(picks.get(position));
  }

  @Override
  public int getItemCount() {
    return picks.size();
  }

  public class Holder extends RecyclerView.ViewHolder {

    private TextView view;

    public Holder(@NonNull View view) {
      super(view);
      this.view = (TextView) view;
    }
    private void bind(int[] numbers){
      view.setText(Arrays.toString(numbers));

    }

  }
}
