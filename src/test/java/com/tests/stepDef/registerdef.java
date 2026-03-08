package com.tests.stepDef;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class registerdef {


    @When("jj:")
    public void jj(DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(dataTable.asMap(String.class,String.class).toString());

    }

    @Given("dd")
    public void dd() {
        System.out.println("first");
    }
}
