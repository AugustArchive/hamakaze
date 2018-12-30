package xyz.auguwu.http;

import xyz.auguwu.http.enums.HTTPRequestType;
import org.json.JSONObject;
import okhttp3.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class HTTPRequest {
    private String uri;
    private HTTPRequestType type;
    private OkHttpClient client = new OkHttpClient();
    private Map<String, Object> params = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private Request.Builder builder = new Request.Builder();

    public HTTPRequest(String uri) {
        this(uri, HTTPRequestType.GET);
    }

    public HTTPRequest(String uri, HTTPRequestType type) {
        this.uri = uri;
        this.type = type;

        headers.put("User-Agent", "Hamakaze/DiscordBot (https://github.com/auguwu/Hamakaze)");
    }

    public HTTPRequest addHeader(String key, String val) {
        headers.put(key, val);
        return this;
    }

    public HTTPRequest addParamater(String k, Object v) {
        params.put(k, v);
        return this;
    }

    public void executeGET(Consumer success, Consumer<Throwable> failed) {
        try {
            builder.url(this.buildURI()).get();
            for (Map.Entry<String, String> entry: headers.entrySet())
                builder.addHeader(entry.getKey(), entry.getValue());
            success.accept(new HTTPResponse(client.newCall(builder.build()).execute()));
        } catch(Exception ex) {
            failed.accept(ex);
        }
    }

    public void executePOST(JSONObject e, Consumer success, Consumer<Throwable> fail) {
        try {
            builder.url(this.buildURI()).post(RequestBody.create(MediaType.parse("application/json"), e.toString()));
            for (Map.Entry<String, String> entry: headers.entrySet())
                builder.addHeader(entry.getKey(), entry.getValue());
            success.accept(new HTTPResponse(client.newCall(builder.build()).execute()));
        } catch(Exception ex) {
            fail.accept(ex);
        }
    }

    private URL buildURI() throws MalformedURLException {
        String built = uri + (uri.contains("?") ? "" : "?") + buildURIParams();
        return new URL(built);
    }

    private String buildURIParams() {
        return params
                    .entrySet()
                    .stream()
                    .map((item) -> {
                        try {
                            return String.format("%s=%s", item.getKey(), URLEncoder.encode(item.getValue().toString(), "UTF-8"));
                        } catch(UnsupportedEncodingException ex) {
                            ex.printStackTrace();
                            return String.format("%s=%s", item.getKey(), "invalid-format");
                        }
                    }).collect(Collectors.joining("&"));
    }
}