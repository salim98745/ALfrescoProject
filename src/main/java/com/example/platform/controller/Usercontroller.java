package com.example.platform.controller;

import com.example.platform.entity.User;
import org.alfresco.core.handler.PeopleApi;
import org.alfresco.core.model.PersonBodyCreate;
import org.alfresco.core.model.PersonEntry;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class Usercontroller {

    @Autowired
    PeopleApi peopleApi;







    @PostMapping("adduser")
    public String adduser(@RequestBody User user) throws IOException {

        /*HttpEntity httpEntity = (HttpEntity) user;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri+"/people");
        httpPost.setEntity(httpEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        httpClient.close();*/
        PersonBodyCreate personBodyCreate = new PersonBodyCreate();
        personBodyCreate.setId(user.getId());
        personBodyCreate.setFirstName(user.getFirstName());
        personBodyCreate.setLastName(user.getLastName());
        personBodyCreate.setEmail(user.getEmail());
        personBodyCreate.setPassword(user.getPassword());
        PersonEntry person = peopleApi.createPerson(personBodyCreate,null).getBody();
        return "user added";
    }
}
