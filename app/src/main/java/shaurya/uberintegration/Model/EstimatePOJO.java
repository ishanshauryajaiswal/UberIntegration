package shaurya.uberintegration.Model;

/**
 * Created by Ishan Shaurya Jaiswal.
 */
public class EstimatePOJO {

    public String display_name;
    public String estimate;
    public float surge_multiplier;
    public int duration;
    public String product_id;

    public String getProduct_id() {
        return product_id;
    }

    public int getDuration() {
        return duration;
    }

    public String getEstimate() {
        return estimate;
    }

    public float getSurge_multiplier() {
        return surge_multiplier;
    }

    public String getDisplay_name() {

        return display_name;
    }


}
