package API.RequestMaker;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RequestMaker {

    private static RequestMaker instance=null;

    public static RequestMaker getInstance(){
        if(instance == null){
            instance=new RequestMaker();
        }
        return instance;
    }

    private RequestMaker(){}

    public HttpEntity crearGET(String url) throws IOException {
        HttpGet get = new HttpGet(url);
        return this.crearRequestGenerico(url,get);
    }

    public HttpEntity crearPOST(String url,String json) throws IOException {
        HttpPost post = new HttpPost(url);
        StringEntity entity= new StringEntity(json,"application/json","UTF-8");
        post.setEntity(entity);
        return this.crearRequestGenerico(url,post);
    }

    public HttpEntity crearDELETE(String url) throws IOException {
        HttpDelete delete = new HttpDelete(url);
        return this.crearRequestGenerico(url,delete);
    }

    public HttpEntity crearPATCH(String url) throws IOException {
        HttpPatch patch = new HttpPatch(url);
        return this.crearRequestGenerico(url,patch);
    }

    public HttpEntity crearPUT(String url) throws IOException {
        HttpPut put = new HttpPut(url);
        return this.crearRequestGenerico(url,put);
    }

    private HttpEntity crearRequestGenerico(String pedido, HttpUriRequest verbHTTP) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse resp = null;
        resp = client.execute(verbHTTP);
        return resp.getEntity();
    }





}
