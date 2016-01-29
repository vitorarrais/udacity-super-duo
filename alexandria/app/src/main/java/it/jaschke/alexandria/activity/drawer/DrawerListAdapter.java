package it.jaschke.alexandria.activity.drawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import it.jaschke.alexandria.R;

/**
 * Created by Vitor on 27/01/2016.
 */
public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {

    Context context;
    int layoutResourceId;
    DrawerItem data[] = null;

    public DrawerListAdapter(Context context, int layoutResourceId, DrawerItem[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public DrawerListAdapter(Context context, int layoutResourceId, int layoutResourceIdActive, DrawerItem[] data) {
        super(context, layoutResourceId, layoutResourceIdActive, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DrawerItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new DrawerItemHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.drawer_item_icon);
            holder.txtTitle = (TextView) row.findViewById(R.id.drawer_item_title);

            row.setTag(holder);
        } else {
            holder = (DrawerItemHolder) row.getTag();
        }

        DrawerItem item = data[position];
        holder.txtTitle.setText(item.getTitle());
        holder.imgIcon.setImageResource(item.getIconResId());

        return row;
    }

    static class DrawerItemHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
