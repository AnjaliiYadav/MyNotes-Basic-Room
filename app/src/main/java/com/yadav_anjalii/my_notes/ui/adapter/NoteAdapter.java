package com.yadav_anjalii.my_notes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yadav_anjalii.my_notes.databinding.NoteItemBinding;
import com.yadav_anjalii.my_notes.model.Note;
import com.yadav_anjalii.my_notes.util.Utils;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    List<Note> notes;
    private ItemClickListener itemClickListener;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        NoteItemBinding binding= NoteItemBinding.inflate(inflater);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.binding.setNotes(note);
        holder.binding.setCurrentDateTime(Utils.getCurrentDateTime(note.getCreatedAt()));
        if (note.isEncrypt()){
            holder.binding.noteItemDesc.setVisibility(View.GONE);
            holder.binding.imageLock.setVisibility(View.VISIBLE);
        }
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (notes != null)
            return notes.size();
        return 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        NoteItemBinding binding;
        public NoteViewHolder(@NonNull NoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.itemOnClick(view, notes.get(getAdapterPosition()));
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void itemOnClick(View view, Note note);
    }

}
