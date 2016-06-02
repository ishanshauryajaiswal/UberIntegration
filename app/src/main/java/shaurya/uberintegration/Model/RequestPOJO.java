package shaurya.uberintegration.Model;

/**
 * Created by Ishan Shaurya Jaiswal.
 */
public class RequestPOJO {

    public String request_id;
    public String status;
    public int eta;
    public String product_id;


    public String getRequest_id() {
        return request_id;
    }

    public String getStatus() {
        return status;
    }

    public int getEta() {
        return eta;
    }

    public String getProduct_id() {
        return product_id;
    }
}
