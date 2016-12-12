package com.dong.starsmind.read.presenter;

import com.dong.starsmind.read.entity.Book;
import com.dong.starsmind.read.view.BookshelfView;
import com.dong.starsmind.db.DBOperation;
import com.dong.starsmind.db.DBPage;

import retrofit2.Retrofit;

/**
 * Created by zengwendong on 16/11/3.
 */
public class BookshelfPresenter {

    private BookshelfView bookshelfView;
    private DBPage<Book> dbPage = new DBPage<>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://dushu.m.baidu.com/")
            .build();

    public BookshelfPresenter(BookshelfView bookshelfView) {
        this.bookshelfView = bookshelfView;
    }

    public void loadBookshelf() {

        /*GetDataApi getDataApi = retrofit.create(GetDataApi.class);
        Call<ResponseBody> call = getDataApi.getBookList();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    RankDetailData rankDetailData = gson.fromJson(response.body().string(), new TypeToken<RankDetailData>() {
                    }.getType());
                    if (rankDetailData != null && rankDetailData.getData() != null) {
                        bookshelfView.refreshData(rankDetailData.getData().getNovelList());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/

        dbPage.setPageSize(100);
        //本地加载
        DBOperation.findAll(Book.class, dbPage);
        if (dbPage.getDataList() != null) {
            bookshelfView.refreshData(dbPage.getDataList());
        }
    }

    public void addBook(String name,String path){
        Book book = new Book();
        book.setCategory("local");
        book.setTitle(name);
        book.setPath(path);
        boolean bool = DBOperation.saveOrUpdate(book);
        if (bool) {
            bookshelfView.addBookSuccess();
        }
    }

}
