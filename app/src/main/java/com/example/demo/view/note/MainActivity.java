package com.example.demo.view.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.example.demo.R;
import com.example.demo.adapter.NoteAdapter;
import com.example.demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private NoteAdapter adapter;
    private InputMethodManager inputMethodManager;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        setRecyclerView();
        setObserver();
    }

    private void setRecyclerView() {
        adapter = new NoteAdapter(this, viewModel.getNoteList().getValue());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.recyclerviewNotes.setLayoutManager(layoutManager);
        binding.recyclerviewNotes.setAdapter(adapter);
    }

    private void setObserver() {
        viewModel.getNoteList().observe(this, list ->{
            viewModel.getNoteContents().setValue("");
            adapter.notifyItemInserted(list.size());
            inputMethodManager.hideSoftInputFromWindow(binding.etContents.getWindowToken(), 0);
        });
    }
}