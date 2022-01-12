/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.lang.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(cron = "0 0/2 * * * ?") //every two minutes https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		try {
            // Run the scrapy script first
           	log.info("scraping");
            ProcessBuilder pba = new ProcessBuilder("python","./spiders/__init__.py");
            //look at errors
			 pba.redirectErrorStream(true);
			//set working (base) dir
			 pba.directory(new File("/home/gigabyte/Desktop/software_dev/CSCI-3308-Fall21-013-05/SOURCECODE/springboot/scrapyIntegration/scraping/simply_scraper"));
			 log.info("working dir " + pba.directory());
			 //do it
			 Process pa = pba.start();
			 BufferedReader bfr = new BufferedReader(new InputStreamReader(pa.getInputStream()));
			 String line = "";
			 log.info("Running Python starts: " + line);
			 int exitCode = pa.waitFor(); //wait for ensures that that process finishes
				log.info("Exit Code : "+exitCode);
			 line = bfr.readLine();
			 log.info("First Line: " + line);
			 while ((line = bfr.readLine()) != null){
				 log.info("Python Output: " + line);
			 }
			 log.info("finished scraping");


            // Then run the json_to_sql script
            //System.out.println("converting");
			log.info("converting");
          	ProcessBuilder pbb = new ProcessBuilder("python","json_to_sql.py");
			//look at errors
			 pbb.redirectErrorStream(true);
			//working dir
			pbb.directory(new File("/home/gigabyte/Desktop/software_dev/CSCI-3308-Fall21-013-05/SOURCECODE/springboot/scrapyIntegration/scraping/simply_scraper/"));
			log.info("working dir " + pbb.directory());
			//do it
            Process pb = pbb.start();
			BufferedReader bfr1 = new BufferedReader(new InputStreamReader(pb.getInputStream()));
            String line1 = "";
            log.info("Running Python starts: " + line1);
            int exitCode1 = pb.waitFor();
           	log.info("Exit Code : "+exitCode1);
            line1 = bfr1.readLine();
            log.info("First Line: " + line);
            while ((line1 = bfr1.readLine()) != null){
                log.info("Python Output: " + line1);

            }
			log.info("finished converting");
        }
        catch(Exception e) {
            //System.out.println(e);
			log.info("failed");
        }
	}
}

//look for enviroment issues:
/*
Map<String, String> envMap = pbb.environment();
			 // checking map view of environment
			 for (Map.Entry<String, String> entry :
			 	envMap.entrySet()) {
			 	// checking key and value separately
			 	log.info("Key = " + entry.getKey()
			 					+ ", Value = "
			 					+ entry.getValue());
			 }*/

//helpful articles
// https://www.geeksforgeeks.org/java-lang-processbuilder-class-java/
// https://stackoverflow.com/questions/26171862/java-processbuilder-not-able-to-run-python-script-in-java
// https://frontbackend.com/linux/cron-every-5-minutes
// https://dzone.com/articles/running-on-time-with-springs-scheduled-tasks