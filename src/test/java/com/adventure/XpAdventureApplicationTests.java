package com.adventure;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XpAdventureApplicationTests {

    @Test
    public void loginTest() {
        Login login = new Login("super", "super");
        login.verifyUser();
        String redirect = login.redirect();
        Assert.assertEquals("owner_page", redirect);
        Assert.assertEquals("super", login.getUsername());
        Assert.assertEquals(1, login.getAccessLevel());

    }


    @Test
    public void accessTest(){

        Login login = new Login("super", "super");
        login.verifyUser();
        String redirect = login.redirect();
        Assert.assertEquals("owner_page", redirect);
        Assert.assertEquals("sdfsdfsuper", login.getUsername());
        Assert.assertEquals(1, login.getAccessLevel());
    }


    @Test
    public void getProductbyId() {
        Product product = new Product();
        product = product.getProductById(5);
        System.out.println(product.toString());
    }
}
