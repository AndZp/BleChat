package mobi.mateam.blechat.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import mobi.mateam.blechat.R;
import mobi.mateam.blechat.model.pojo.Message;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {

  //Compare by TimeStamp (long)
  private static final Comparator<Message> SORTING_COMPARATOR =
      (valLeft, valRight) -> valRight.time < valLeft.time ? -1 : valRight.time > valLeft.time ? 1 : 0;

  private final List<Message> data = new ArrayList<>();
  private OnAdapterItemClickListener onAdapterItemClickListener;
  private final View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {

      if (onAdapterItemClickListener != null) {
        onAdapterItemClickListener.onAdapterViewClick(v);
      }
    }
  };

  public ChatMessageAdapter() {
  }

  public void addMessages(List<Message> messages) {
    data.addAll(messages);
    Collections.sort(data, SORTING_COMPARATOR);
    notifyDataSetChanged();
  }

  public void addMessage(Message message) {
    data.add(message);
    Collections.sort(data, SORTING_COMPARATOR);
    notifyDataSetChanged();
  }

  public void clearScanResults() {
    data.clear();
    notifyDataSetChanged();
  }

  public Message getItemAtPosition(int childAdapterPosition) {
    return data.get(childAdapterPosition);
  }

  @Override public int getItemCount() {
    return data.size();
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    final Message message = data.get(position);


    String messageLine = message.senderName + ": " + message.text;
    String time = new SimpleDateFormat("HH:mm").format(new Date(message.time));
    holder.line1.setText(messageLine);
    holder.line2.setText(time);

    if (message.isIncoming){
      holder.line1.setGravity(Gravity.START);
      holder.line2.setGravity(Gravity.START);
    } else {
      holder.line1.setGravity(Gravity.END);
      holder.line2.setGravity(Gravity.END);
    }
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
    itemView.setOnClickListener(onClickListener);
    return new ViewHolder(itemView);
  }

  public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
    this.onAdapterItemClickListener = onAdapterItemClickListener;
  }

  public interface OnAdapterItemClickListener {

    void onAdapterViewClick(View view);
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(android.R.id.text1) public TextView line1;
    @BindView(android.R.id.text2) public TextView line2;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
