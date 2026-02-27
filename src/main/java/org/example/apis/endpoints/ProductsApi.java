package org.example.apis.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.apis.model.Product;

import static io.restassured.RestAssured.*;
public class ProductsApi extends BaseRequest {

    public ProductsApi() {
        super("https://fakestoreapi.com/products");
    }
    public  Response getAllProducts(){
        return given().relaxedHTTPSValidation().contentType(ContentType.JSON).accept(ContentType.JSON).get(url);
    }
    public Response addProduct(Product pr){
        return given().relaxedHTTPSValidation().contentType(ContentType.JSON).accept(ContentType.JSON).body(pr).when().post(url);
    }
    public Response getProductById(int id){
        return given().relaxedHTTPSValidation().contentType(ContentType.JSON).accept(ContentType.JSON).get(url+"/"+id);

    }


}
