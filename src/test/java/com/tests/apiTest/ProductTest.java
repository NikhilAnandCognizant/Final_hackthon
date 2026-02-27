package com.tests.apiTest;

import io.restassured.response.Response;
import org.example.apis.endpoints.ProductsApi;
import org.example.apis.model.Product;
import org.example.apis.payloads.ProductPayloads;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest {

    @Test
    public void testGetAllProducts(){


        ProductsApi api = new ProductsApi();
          Response res = api.getAllProducts();

        Assert.assertEquals(res.getStatusCode() ,200);



    }

    @Test
    public void testCreateAProduct(){
        Product payLoad = ProductPayloads.getDummyProductPayload();

        ProductsApi api = new ProductsApi();
        Response res = api.addProduct(payLoad);



        Assert.assertEquals(res.getStatusCode() ,201);



    }


    @Test
    public void testGetProductByID(){


        ProductsApi api = new ProductsApi();
        Response res = api.getProductById(1);



        Assert.assertEquals(res.getStatusCode() ,200);



    }



}
