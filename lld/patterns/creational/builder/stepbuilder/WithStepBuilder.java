package stepbuilder;
import java.util.HashMap;
import java.util.Map;

// Required field: `url` is handled with interface
interface UrlStep{
    MethodStep withUrl(String url);
}
// Required field: `method` is handled with interface
interface MethodStep{
    OptionalStep withMethod(String method);
}

// Optional fields: remaining are handled at once here
interface OptionalStep{
    OptionalStep withBody(String body);
    OptionalStep withHeader(String key, String value);
    OptionalStep withQueryParam(String key, String value);
    OptionalStep withTimeout(int timeout);
    HttpRequest build();
}
class HttpRequest{
   
    private String url, method, body;
    private Map<String, String> headers, queryParams;
    private int timeout;

    // Private Constructor
    private HttpRequest() {
        this.headers = new HashMap<>();
        this.queryParams = new HashMap<>();
    }

    static class HttpRequestStepBuilder implements UrlStep, MethodStep, OptionalStep{
        private HttpRequestStepBuilder() {
            this.req = new HttpRequest();
        }
        private HttpRequest req;

        @Override 
        public MethodStep withUrl(String url){
            this.req.url = url;
            return this;
        }
        @Override
        public OptionalStep withBody(String body){
            this.req.body = body;
            return this;
        }
        @Override
        public OptionalStep withMethod(String method){
            this.req.method = method;
            return this;
        }
        @Override
        public OptionalStep withHeader(String key, String value){
            this.req.headers.put(key, value);
            return this;
        }
        @Override
        public OptionalStep withQueryParam(String key, String value){
            this.req.queryParams.put(key, value);
            return this;
        }
        @Override
        public OptionalStep withTimeout(int timeout){
            this.req.timeout = timeout;
            return this;
        }
        public HttpRequest build(){
            // Validations
            if(this.req.url.isBlank()){
                throw new RuntimeException("Url cannot be blank");
            }
            /*
            More validations here...
            */
            return this.req;
        }
    }

    public static UrlStep getBuilder(){
        return new HttpRequestStepBuilder();
    }

    public void execute(){
        
        StringBuilder sb = new StringBuilder();
        sb.append(this.url);

        if(this.queryParams.size() > 0){
            sb.append("?");
            for(Map.Entry<String, String> param: this.queryParams.entrySet()){
                sb.append(param.getKey()+"="+param.getValue()+"&");
            }
            sb.deleteCharAt(sb.length()-1);
        }

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


public class WithStepBuilder {
    public static void main(String[] args) {

        String url = "http://localhost/api";
        String body = "{\n\"name\": Pradeep\n}";
        HttpRequest request = HttpRequest.getBuilder()
                .withUrl(url)
                .withMethod("POST")
                .withBody(body)
                .withHeader("Accept", "*")
                .withHeader("Content-Type", "application/json")
                .withQueryParam("utm_source", "vscode")
                .withTimeout(20)
                .build();
        
        request.execute();
    }
}
