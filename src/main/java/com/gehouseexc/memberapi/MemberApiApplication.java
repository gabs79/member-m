package com.gehouseexc.memberapi;

import java.util.concurrent.TimeUnit;

import com.gehouseexc.memberapi.applistener.AppInitializer;
import com.gehouseexc.memberapi.service.MembersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MemberApiApplication {

    @Autowired
    MembersService membersService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MemberApiApplication.class);
        app.run(args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void executeETLProcess() {
		long startTime = System.currentTimeMillis();
        new AppInitializer(membersService).execute();
		logExecutionTime(startTime);
	}

	//create a AOP nice utility? or use an actual profiler?
	private void logExecutionTime(long startTime) {
		long endtime = System.currentTimeMillis();
		long millis= endtime-startTime;

		String time= String.format("%02d min, %02d sec",
			TimeUnit.MILLISECONDS.toMinutes(millis),
    		TimeUnit.MILLISECONDS.toSeconds(millis) - 
    		TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		log.info("MembersETLProcess took: " + time);
	}
}
