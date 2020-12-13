package be.rapnhap.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ItemViewModel mItemViewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ItemListAdapter adapter = new ItemListAdapter(new ItemListAdapter.ItemDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        mItemViewModel.getAllItems().observe(this, items -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(items);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
            startActivityForResult(intent, NEW_ITEM_ACTIVITY_REQUEST_CODE);
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Item word = new Item(data.getStringExtra(NewItemActivity.EXTRA_REPLY));
            mItemViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}