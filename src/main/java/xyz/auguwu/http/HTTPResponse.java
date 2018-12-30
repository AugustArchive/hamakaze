package xyz.auguwu.http;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class HTTPResponse {
    private final Response res;

    public HTTPResponse(Response r) {
        this.res = r;
    }

    public Response getResponse() {
        return res;
    }

    public JSONObject toJSON() {
        return new JSONObject(this.toString());
    }

    @Override
    public String toString() {
        try {
            try (ResponseBody b = res.body()) {
                if (b != null)
                    return b.string();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}