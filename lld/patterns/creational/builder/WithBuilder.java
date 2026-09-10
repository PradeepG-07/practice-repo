import java.util.HashMap;
import java.util.Map;

class HttpRequest{
   
    private String url, method, body;
    private Map<String, String> headers, queryParams;
    private int timeout;

    // Private Constructor
    private HttpRequest() {
        this.headers = new HashMap<>();
        this.queryParams = new HashMap<>();
    }

    static class HttpRequestBuilder{
        private HttpRequestBuilder() {
            this.req = new HttpRequest();
        }
        private HttpRequest req;

        public HttpRequestBuilder withUrl(String url){
            this.req.url = url;
            return this;
        }
        public HttpRequestBuilder withBody(String body){
            this.req.body = body;
            return this;
        }
        public HttpRequestBuilder withMethod(String method){
            this.req.method = method;
            return this;
        }
        public HttpRequestBuilder withHeader(String key, String value){
            this.req.headers.put(key, value);
            return this;
        }
        public HttpRequestBuilder withQueryParam(String key, String value){
            this.req.queryParams.put(key, value);
            return this;
        }
        public HttpRequestBuilder withTimeout(int timeout){
            this.req.timeout = timeout;
            return this;
        }
        public HttpRequest build(){
            // Validations
            if(this.req.url.isBlank()){
                throw new RuntimeException("Url cannot be blank");
            }
            return this.req;
        }
    }

    public static HttpRequestBuilder getBuilder(){
        return new HttpRequestBuilder();
    }

    public void execute(){
        
        StringBuilder sb = new StringBuilder();
        sb.append(this.url);

        if(this.queryParams.size() > 0) sb.append("?");
        for(Map.Entry<String, String> param: this.queryParams.entrySet()){
            sb.append(param.getKey()+"="+param.getValue()+"&");
        }
        sb.deleteCharAt(sb.length()-1);

        // Print URI
        System.out.println(this.method+" "+sb.toString());
        
        // Print Headers
        System.out.println();
        for(Map.Entry<String, String> header: this.headers.entrySet()){
            System.out.println(header.getKey()+": "+header.getValue());
        }

        // Print Body
        System.out.println();
        System.out.println(this.body); 
        
        // Print timeout
        System.out.println("Request timeout is set to "+this.timeout);
    }
}

public class WithBuilder {
    public static void main(String[] args) {
        HttpRequest request = HttpRequest.getBuilder()
                .withUrl("http://localhost/api")
                .withMethod("POST")
                .withBody("{\n\"name\": Pradeep\n}")
                .withHeader("Accept", "*")
                .withHeader("Content-Type", "application/json")
                .withQueryParam("utm_source", "vscode")
                .withTimeout(20)
                .build();
        
        request.execute();
    }
}
