package cn.xufucun.udacity.booklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
        TextView firstName = view.findViewById(R.id.tv_first_name);

        bookName.setText(book.getBookName());
        bookAuthor.setText(book.getBookAuthor());

        String fn = book.getBookName();
        String fn1 = String.valueOf(fn.charAt(0));
        firstName.setText(fn1);

        return view;
    }
}
