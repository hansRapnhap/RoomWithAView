package be.rapnhap.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewItemActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditItemView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        mEditItemView = findViewById(R.id.edit_item);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditItemView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String item = mEditItemView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, item);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
