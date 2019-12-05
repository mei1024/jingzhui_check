package com.solar.job.scheduling.once;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.solar.job.enums.TimerOnceEnum;
import com.solar.job.service.JobOnceTimerService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class OnceTimerJob implements org.quartz.Job {

	protected static final Logger LOG = LoggerFactory.getLogger(OnceTimerJob.class);
	
	private static final String JOB_URL_PREFIX = "url:";
	
	@Autowired
	private JobOnceTimerService jobOnceTimerService;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		long exeStartTime = System.currentTimeMillis();
		LOG.info("Execute Once Timer JobDataMap={}", context.getJobDetail().getJobDataMap());
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
	
		String jobId = jobDataMap.getString("JOB_ID");
		String jobData = jobDataMap.getString("JOB_DATA");
		String jobName = jobDataMap.getString("JOB_NAME");
		String jobProc = jobDataMap.getString("JOB_PROC");
		
		 
		if (StringUtils.startsWith(jobProc, JOB_URL_PREFIX)) {
			String returnUrl = StringUtils.substringAfter(jobProc, JOB_URL_PREFIX);
			
			JSONObject postData = new JSONObject();
			postData.put("id", jobId);
			postData.put("data", jobData);
			postData.put("name", jobName);
			
			boolean ok = false;
			String resultBody = null;
			try {
				
				ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(returnUrl,
						Base64.getEncoder().encodeToString(postData.toJSONString().getBytes()), JSONObject.class);
				
				LOG.debug("Notify URL={}, Response={}", returnUrl, responseEntity.getBody());
				
				ok = responseEntity.getStatusCodeValue() == 200;
				resultBody = responseEntity.getBody().toString();
			} catch (Exception ex) {
				LOG.error("Notify URL={}", returnUrl, ex);
				resultBody = ex.getMessage();
			} finally {
				long exeEndTime = System.currentTimeMillis();

				if (ok) {
					jobOnceTimerService.updateTimerStatus(jobId, TimerOnceEnum.Status.SUCCESS, resultBody);
				} else {
					jobOnceTimerService.updateTimerStatusRetry(jobId, exeStartTime, exeEndTime, resultBody);
				}
			}
			
		}
	}
//	
//	
//	
//	public static void main(String []args) {
//		String jobProc = "url:http://127.0.0.1:8082/task/timer_notify";
//		String returnUrl = StringUtils.substringAfter(jobProc, JOB_URL_PREFIX);
//		
//		JSONObject json = new JSONObject();
//		json.put("id", "1");
//		json.put("data", "2");
//		
//		String base64Data = Base64.getEncoder().encodeToString(json.toJSONString().getBytes());
//		
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(returnUrl, base64Data+1, JSONObject.class);
//		
//	
//		System.out.println(responseEntity.getStatusCodeValue());
//		System.out.println(responseEntity.getBody());
//	}
//	
//	
	
}
