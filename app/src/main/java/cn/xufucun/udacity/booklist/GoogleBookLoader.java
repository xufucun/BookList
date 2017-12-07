package cn.xufucun.udacity.booklist;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by MayiSenlin on 2017/12/7.
 */

public class GoogleBookLoader extends AsyncTaskLoader<List<Book>> {

    private String mUrl;

    public GoogleBookLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<Book> books = GoogleQueryUtils.fetchBookData(mUrl);
        return books;
    }
}
