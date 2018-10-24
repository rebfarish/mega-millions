package deepdive.cnm.edu.megamillions.view;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import deepdive.cnm.edu.megamillions.R;
import deepdive.cnm.edu.megamillions.controller.MainActivity;
import deepdive.cnm.edu.megamillions.model.entity.PickNumber;
import deepdive.cnm.edu.megamillions.model.pojo.PickAndNumbers;
import java.util.Arrays;
import java.util.List;

public class PickAdapter extends RecyclerView.Adapter<PickAdapter.Holder> {

  private Context context;
  private List<PickAndNumbers> picks;

  public PickAdapter(Context context, List<PickAndNumbers> picks) {
    this.context = context;
    this.picks = picks;

  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.pick_item, viewGroup, false);
    return new Holder(view);
  }


  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind();
    int background = (position % 2 == 0)
        ? ContextCompat.getColor(context, R.color.pickBackground)
        : ContextCompat.getColor(context, R.color.pickBackgroundAlternate);
    holder.itemView.setBackgroundColor(background);
  }

  @Override
  public int getItemCount() {
    return picks.size();
  }

  public class Holder extends RecyclerView.ViewHolder
      implements View.OnCreateContextMenuListener {

    private static final int PICK_LENGTH = 6;

    private PickAndNumbers pick;
    private TextView[] numbers;

    public Holder(@NonNull View view) {
      super(view);
      view.setOnCreateContextMenuListener(this);
      numbers = new TextView[PICK_LENGTH];
      for (int i = 0; i < PICK_LENGTH; i++){
        int id = context.getResources().getIdentifier("num_" + i, "id", context.getPackageName());
        numbers[i] = view.findViewById(id);
      }

    }

    private void bind() {
      // TODO Use PickWithNumbers instance.
      pick = picks.get(getAdapterPosition());
      List<PickNumber> numbers = pick.getNumbers();
      int index = 0;
      for (PickNumber pickNumber : numbers) {
        this.numbers[index++]
            .setText(context.getString(R.string.pick_number_format, pickNumber.getValue()));
      }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
      menu.add(R.string.delete_pick).setOnMenuItemClickListener((item) -> {
        ((MainActivity) context).deletePick(getAdapterPosition(),pick.getPick());
        return true;
      });
    }
  }
}
