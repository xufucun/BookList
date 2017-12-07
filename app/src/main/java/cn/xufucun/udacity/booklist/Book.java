package cn.xufucun.udacity.booklist;

/**
 * Created by MayiSenlin on 2017/11/25.
 */

public class Book {

    private String bookName;
    private String bookAuthor;
    private String bookUrl;

    public Book(String name, String author, String url){
        bookName = name;
        bookAuthor = author;
        bookUrl = url;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookUrl() {
        return bookUrl;
    }


}
