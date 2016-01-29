package it.jaschke.alexandria.activity.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.jaschke.alexandria.R;
import it.jaschke.alexandria.data.AlexandriaContract;
import it.jaschke.alexandria.services.DownloadImage;

/**
 * Created by Vitor on 29/01/2016.
 */
public class HolderBookView extends FrameLayout {

    @Bind(R.id.book_title)
    protected TextView mTitle;

    @Bind(R.id.book_subtitle)
    protected TextView mSubtitle;

    @Bind(R.id.authors)
    protected TextView mAuthors;

    @Bind(R.id.book_cover)
    protected ImageView mBookCover;

    @Bind(R.id.categories)
    protected TextView mCategories;


    public HolderBookView(Context context) {
        super(context);
        init(context);
    }

    public HolderBookView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HolderBookView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HolderBookView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context){
        View.inflate(context, R.layout.holder_book, this);
        ButterKnife.bind(this);
    }


    public void bind(Cursor data){
        String bookTitle = data.getString(data.getColumnIndex(AlexandriaContract.BookEntry.TITLE));
        mTitle.setText(bookTitle);

        String bookSubTitle = data.getString(data.getColumnIndex(AlexandriaContract.BookEntry.SUBTITLE));
        mSubtitle.setText(bookSubTitle);

        String authors = data.getString(data.getColumnIndex(AlexandriaContract.AuthorEntry.AUTHOR));
        String[] authorsArr = authors.split(",");
        mAuthors.setLines(authorsArr.length);
        mAuthors.setText(authors.replace(",", "\n"));

        String imgUrl = data.getString(data.getColumnIndex(AlexandriaContract.BookEntry.IMAGE_URL));
        if(Patterns.WEB_URL.matcher(imgUrl).matches()){
            new DownloadImage(mBookCover).execute(imgUrl);
            mBookCover.setVisibility(View.VISIBLE);
        }

        String categories = data.getString(data.getColumnIndex(AlexandriaContract.CategoryEntry.CATEGORY));
        mCategories.setText(categories);


    }

    public void clear(){

        mTitle.setText("");
        mSubtitle.setText("");
        mAuthors.setText("");
        mCategories.setText("");
        mBookCover.setVisibility(View.INVISIBLE);

    }


}
