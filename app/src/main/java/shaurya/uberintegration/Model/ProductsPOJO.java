package shaurya.uberintegration.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maver_000 on 4/5/2016.
 */
public class ProductsPOJO {
    public String display_name;
    public String description;
    public ProductsPOJO()
    {

    }

    public String getDisplay_name(){
        return display_name;
    }
    public String getDescription(){
        return description;
    }
}
