package cn.xufucun.udacity.booklist;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

    private static final String TAG = "BookListActivity";

    private static String DOUBAN_SERARCH_BOOK_URL = "https://api.douban.com/v2/book/search";
    private static String GOOGLE_SEARCH_BOOK_API = "https://www.googleapis.com/books/v1/volumes";

    private static final int SEARCH_LOADER_ID = 1;

    private BookAdapter mAdapter;
    private TextView emptyTextView;
    private String keyWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        keyWords = getIntent().getStringExtra("keyWords");

        ListView listView = findViewById(R.id.list);
        emptyTextView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyTextView);
        mAdapter = new BookAdapter(this,new ArrayList<Book>());
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book book = mAdapter.getItem(position);
                Uri uri = Uri.parse(book.getBookUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(SEARCH_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            emptyTextView.setText("无网络链接");
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String maxCount = sharedPrefs.getString(getString(R.string.settings_max_count_key), getString(R.string.settings_max_count_default));
        String searchBy = sharedPrefs.getString(getString(R.string.settings_search_by_key), getString(R.string.settings_search_by_default));

        if (searchBy.equals("douban")){
            Uri baseUri = Uri.parse(DOUBAN_SERARCH_BOOK_URL);
            Uri.Builder uriBuilder = baseUri.buildUpon();

            uriBuilder.appendQueryParameter("q", keyWords);
            uriBuilder.appendQueryParameter("maxResults", maxCount);
            Log.d(TAG, "onCreateLoader: "+ uriBuilder.toString());
            return new DoubanBookLoader(this, uriBuilder.toString());
        }else{
            Uri baseUri = Uri.parse(GOOGLE_SEARCH_BOOK_API);
            Uri.Builder uriBuilder = baseUri.buildUpon();

            uriBuilder.appendQueryParameter("q", keyWords);
            uriBuilder.appendQueryParameter("count", maxCount);
            Log.d(TAG, "onCreateLoader: "+ uriBuilder.toString());
            return new GoogleBookLoader(this, uriBuilder.toString());
        }

    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        emptyTextView.setText("无数据");
        mAdapter.clear();
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }

}
