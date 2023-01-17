package com.example.demo.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.data.entity.Note;
import com.example.demo.databinding.ItemNoteBinding;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private Context context;
    private List<Note> list;
    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public NoteAdapter(Context context, List<Note> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        private final ItemNoteBinding binding;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemNoteBinding.bind(itemView);
        }

        public void bind(Note item) {
            binding.textviewMemoDate.setText(DateFormat.format(DATE_FORMAT, item.getWriteDate()).toString());
            binding.textviewMemoContents.setText(item.getContents());
        }
    }
}
