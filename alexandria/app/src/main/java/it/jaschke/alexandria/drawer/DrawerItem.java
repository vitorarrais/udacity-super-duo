package it.jaschke.alexandria.drawer;

/**
 * Created by Vitor on 27/01/2016.
 */
public class DrawerItem {

    protected int iconResId;

    protected String title;

    public DrawerItem(int iconResId, String title) {
        this.iconResId = iconResId;
        this.title = title;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
