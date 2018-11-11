package com.flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WinnerFlightAppApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	private RestTemplate template;
	
	public void testCheckTicket() throws Exception {
			
			HttpHeaders headers = new HttpHeaders();
			
			URL DataURl = new URL("http://localhost:" + 8080 + "/winner//check_ticket/1234");
			
			ResponseEntity<String> response = this.template.exchange(DataURl.toString(), HttpMethod.GET, new HttpEntity<byte[]>(headers), String.class);
				
			assertNotNull(response);
			assertTrue(Boolean.getBoolean(response.getBody()));
			assertEquals(HttpStatus.OK,response.getStatusCode());
		}

}
