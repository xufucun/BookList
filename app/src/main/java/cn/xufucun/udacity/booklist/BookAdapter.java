package cn.xufucun.udacity.booklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MayiSenlin on 2017/11/25.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    private static final String TAG = "BookAdapter";

    public BookAdapter(@NonNull Context context, @NonNull List<Book> objects) {
        super(context,0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item,parent,false);
        }

        Book book = getItem(position);

        TextView bookName = view.findViewById(R.id.tv_book_name);
        TextView bookAuthor = view.findViewById(R.id.tv_book_author);
        TextView firstWord = view.findViewById(R.id.tv_first_name);

        if (book != null){

            String mBookName = book.getBookName();
            String mBookAuthor = book.getBookAuthor();

            if (mBookName.length() != 0){  //这是为了防止豆瓣有时候抽风

                Log.d(TAG, "getView: "+book.getBookName());
                String mFirstWord = String.valueOf(mBookName.charAt(0));

                bookName.setText(mBookName);
                bookAuthor.setText(mBookAuthor);
                firstWord.setText(mFirstWord);
            }

        }

        return view;
    }
}
