package com.samuelTI.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.samuelTI.api.domain.FirebaseStorageService;
import com.samuelTI.api.upload.UploadInput;
import com.samuelTI.api.upload.UploadOutput;

//Classe de teste upload
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarApiWebserviceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadAPITest {

	
	@Autowired
    protected TestRestTemplate rest;

    @Autowired
    private FirebaseStorageService service;

    private TestRestTemplate basicAuth() {
        return rest.withBasicAuth("admin","admin");
    }

    private UploadInput getUploadInput() {
        UploadInput upload = new UploadInput();
        upload.setFileName("nome.txt");
        // Base64 de samuel oliveira
        upload.setBase64("c2FtdWVsIG9saXZlaXJh");
        upload.setMimeType("text/plain");
        return upload;
    }

    @Test
    public void testUploadFirebase() {
        String url = service.upload(getUploadInput());

        // Faz o Get na URL
        ResponseEntity<String> urlResponse = rest.getForEntity(url, String.class);
        System.out.println(urlResponse);
        assertEquals(HttpStatus.OK, urlResponse.getStatusCode());
    }

    @Test
    public void testUploadAPI() {

        UploadInput upload = getUploadInput();

        // Insert
        ResponseEntity<UploadOutput> response = basicAuth().postForEntity("/api/v1/upload", upload, UploadOutput.class);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.OK, response.getStatusCode());

        UploadOutput out = response.getBody();
        assertNotNull(out);
        System.out.println(out);

        String url = out.getUrl();

        // Faz o Get na URL
        ResponseEntity<String> urlResponse = rest.getForEntity(url, String.class);
        System.out.println(urlResponse);
        assertEquals(HttpStatus.OK, urlResponse.getStatusCode());
    }
}
