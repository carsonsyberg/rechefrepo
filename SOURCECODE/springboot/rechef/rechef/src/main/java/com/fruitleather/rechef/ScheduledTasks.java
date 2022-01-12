package com.fruitleather.rechef;

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

    @Scheduled(cron = "0 0 0 25 12 ?") //for demo purposes
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        try {
            // Run the scrapy script first
            log.info("scraping");
            ProcessBuilder pba = new ProcessBuilder("/home/gigabyte/anaconda3/bin/python3.8","./spiders/__init__.py");
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
            ProcessBuilder pbb = new ProcessBuilder("/home/gigabyte/anaconda3/bin/python3.8","json_to_sql.py");
            //look at errors
            pbb.redirectErrorStream(true);
            //working dir
            pbb.directory(new File("/home/gigabyte/Desktop/software_dev/CSCI-3308-Fall21-013-05/SOURCECODE/springboot/scrapyIntegration/scraping/simply_scraper/"));
            log.info("working dir " + pbb.directory());
            //do it
            /*Map<String, String> envMap = pbb.environment();
            // checking map view of environment
            for (Map.Entry<String, String> entry :
                    envMap.entrySet()) {
                // checking key and value separately
                log.info("Key = " + entry.getKey()
                        + ", Value = "
                        + entry.getValue());
            }*/
            Process pb = pbb.start();
            BufferedReader bfr1 = new BufferedReader(new InputStreamReader(pb.getInputStream()));
            String line1 = "";
            log.info("Running Python starts: " + line1);
            int exitCode1 = pb.waitFor();
            log.info("Exit Code : "+exitCode1);
            line1 = bfr1.readLine();
            log.info("First Line: " + line1);
            while ((line1 = bfr1.readLine()) != null){
                log.info("Python Output: " + line1);
            }
            log.info("finished converting");

         /*   log.info("testing touch");
            ProcessBuilder pbb = new ProcessBuilder("touch","testing.py");
            //look at errors
            pbb.redirectErrorStream(true);
            //working dir
            pbb.directory(new File("/home/gigabyte/Desktop/software_dev/CSCI-3308-Fall21-013-05/SOURCECODE/springboot/scrapyIntegration/scraping/simply_scraper/"));
            log.info("working dir " + pbb.directory());
            //do it
            Process pb = pbb.start();
            pb.waitFor();
            log.info("touched");*/
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
// "0 0/2 * * * ?"
// https://www.geeksforgeeks.org/java-lang-processbuilder-class-java/
// https://stackoverflow.com/questions/26171862/java-processbuilder-not-able-to-run-python-script-in-java
// https://frontbackend.com/linux/cron-every-5-minutes
// https://dzone.com/articles/running-on-time-with-springs-scheduled-tasks