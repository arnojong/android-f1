package f1.arno.nl.f1;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class DriversAdapter extends BaseAdapter {

    private final Context mContext;
    private final Driver[] drivers;

    public DriversAdapter(Context context, Driver[] drivers){
        this.mContext = context;
        this.drivers = drivers;
    }

    @Override
    public int getCount() {
        return drivers.length;
    }

    @Override
    public Object getItem(int i) {
        return drivers[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if (view == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) view;
        }
        imageView.setImageResource(getStringIdentifier(mContext, drivers[i].getDriverId()));
        return imageView;
    }

    public static int getStringIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
