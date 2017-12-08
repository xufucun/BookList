package cn.xufucun.udacity.booklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText bookKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookKeyword = findViewById(R.id.et_book_keyword);
    }


    public void btnSearch(View view) {
        String keyWords = bookKeyword.getText().toString();

        //trim()可以去掉首尾的空格
        if (  keyWords.trim().isEmpty()) {
            Toast.makeText(MainActivity.this, "请输入关键词", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, BookListActivity.class);
        intent.putExtra("keyWords", keyWords);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;

        }
       return super.onOptionsItemSelected(item);
    }

}
