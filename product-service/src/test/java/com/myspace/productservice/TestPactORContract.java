package com.myspace.productservice;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;

@RunWith(PactRunner.class) // Say JUnit to run tests with custom Runner
@Provider("ProductServiceProvider") // Set up name of tested provider
@PactFolder("../pacts") // Point where to find pacts (See also section Pacts source in documentation)
//@PactBroker(host = "localhost", port = "80")
public class TestPactORContract {
	
	private static ConfigurableApplicationContext application;

    @TestTarget
    public final Target target = new HttpTarget(8282);


    /*@BeforeClass
    public static void startSpring(){
        application = SpringApplication.run(ProductServiceApplication.class);
    }*/

    @State("Product Code")
    public void toDefaultState() {
        System.out.println("Now service in default state");
    }


//    @State("extra")
//    public void toExtraState() {
//        System.out.println("Now service in extra state");
//    }

   /* @AfterClass
    public static void kill(){
        application.stop();
    }*/

}
