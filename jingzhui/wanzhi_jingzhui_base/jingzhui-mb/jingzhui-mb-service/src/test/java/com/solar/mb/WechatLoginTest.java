package com.solar.mb;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class WechatLoginTest {

	
	
	
	
	public static void main(String []args) throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();
		
		
		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("client_id", "7d10f3d6648e565d2122988cd65fdc5d2c680c0b");
        postParameters.add("client_secret", "2af1c2c2208aa045a66b495dc6d07f7c2480997e");
        postParameters.add("auth_type", "wechat_mini");
        postParameters.add("password", "081zLeM40EeuUK1V6vL40DpiM40zLeMD");
        postParameters.add("username", "1");
        postParameters.add("grant_type", "password");
		
        postParameters.add("iv", "i0vJrZAB+06EVb+5mFf8aQ==");
        postParameters.add("encryptedData", "v6+4tx8Swwqe1hEY7kRo+YWET0SzDWbM9KYic+AbqmfpkoVBXXtHO0G3sKcMetQJB53wdc+VeX+29VlIXd/IlGrMHoIuQ33lnIc9tpnJGPdaKDI3LMQenlPsIgkrYEh5MEDwXB9XATJUuLyG5ly6lcGpFXr/Bf3VqkZyVaO4XT8QByrqbvsgzVNxO85njybcp5uv5oTcLkSe3avr/JJfv7PVVxNu+W1zqY0cKSIO4vjCr0A8AXjmrV6JECKr6HDqRUOCKFjAbOGmRM5C8fUmY3Dwr37yAPWTsl1MgJNiyHZpOJw+TcupiIpMyO3FJoSYkHLvjUJuaz68U16J2h3wNVRFgncl0Hvd1lj5prQiAnKr6EvSqbC/6t70UwWKR+gFI6ykLHfPMaMxcZ16Slki+7iqqDr7/07XEjwOH3nvxmo3jXu+aLNG8usEKh1kobueTsCrorjYwmDTeuom9YLHng==");
       
        postParameters.add("parameters", "");
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);

        //String data = restTemplate.postForObject("http://app.sxracloud.com/user/token", r, String.class);
        String data= restTemplate.postForObject("http://localhost:8083/security/user/token", r, String.class);
        System.out.println(data);
        
        Thread.sleep(10000);
	}
}
