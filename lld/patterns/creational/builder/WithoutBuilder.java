import java.util.HashMap;
import java.util.Map;

class HttpRequest{
    // url, method, body, headers, queryParams, timeout
    private String url, method, body;
    private Map<String, String> headers, queryParams;
    private int timeout;

    // 1-field
    HttpRequest(String url){
        this.url = url;
        this.method = "GET";
        this.queryParams = new HashMap<>();
        this.headers = new HashMap<>();
    }

    // 2-fields
    HttpRequest(String url, String method){
        this.url = url;
        this.method = method;
        this.queryParams = new HashMap<>();
        this.headers = new HashMap<>();
    }

    // 3-fields
    HttpRequest(String url, String method, Map<String, String> headers){
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.queryParams = new HashMap<>();
        this.headers = new HashMap<>();
    }

    // 4-fields
    HttpRequest(String url, String method, 
        Map<String, String> headers, Map<String, String> queryParams){
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.queryParams = queryParams;
        this.queryParams = new HashMap<>();
        this.headers = new HashMap<>();
    }

    /*
    ... 
    and so on..
    */

    // Setters
    public void setUrl(String url){this.url = url;}
    public void setBody(String body){this.body = body;}
    public void setMethod(String method){this.method = method;}
    public void setHeader(String key, String value){
        this.headers.put(key, value);
    }
    public void setQueryParam(String key, String value){
        this.queryParams.put(key, value);
    }
    public void setTimeout(int timeout){this.timeout = timeout;}

    public void execute(){
        // Validations
        if(this.url.isBlank()){
            throw new RuntimeException("Url cannot be blank");
        }
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

public class WithoutBuilder {
    public static void main(String[] args) {
        HttpRequest req = new HttpRequest("http://localhost/api", "POST");
        req.setBody("{\n\"name\": Pradeep\n}");
        req.setTimeout(20);
        req.setHeader("Accept", "*");
        req.setHeader("Content-Type", "application/json");
        req.setQueryParam("utm_source", "vscode");

        req.execute();
    }
}
