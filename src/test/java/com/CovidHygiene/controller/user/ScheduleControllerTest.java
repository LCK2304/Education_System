package com.CovidHygiene.controller.user;

import com.CovidHygiene.entity.Schedule;
import com.CovidHygiene.factory.ScheduleFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.junit.Assert.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScheduleControllerTest {

    private static Schedule schedule= ScheduleFactory.buildSchedule(30, LocalTime.of(10,30),LocalTime.of(16,30),true,false);

    @Autowired
    private TestRestTemplate restTemplate = null;
    private String baseURL = "http://localhost:8080/schedule/";

    @Test
    public void a_create() {
        String url = baseURL + "create";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + schedule);
        ResponseEntity<Schedule> postResponse = restTemplate.postForEntity(url, schedule, Schedule.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        schedule = postResponse.getBody();
        System.out.println("Saved data: " + schedule);
        assertEquals(schedule.getClassroomNum(), postResponse.getBody().getClassroomNum());
    }

    @Test
    public void d_list() {
        String url = baseURL + "all";
        System.out.println("URL: " + url);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println(response);
        System.out.println(response.getBody());
    }

    @Test
    public void b_read() {
        String url = baseURL + "read/" + schedule.getClassroomNum();
        System.out.println("URL: " + url);
        ResponseEntity<Schedule> response = restTemplate.getForEntity(url, Schedule.class);
        assertEquals(schedule.getClassroomNum(), response.getBody().getClassroomNum());
    }

    @Test
    public void c_update() {
        Schedule update = new Schedule.ScheduleBuilder().copy(schedule).
                setBookedForTeach(false).build();

        String url = baseURL + "update";
        System.out.println("URL: " + url);
        System.out.println("Post data: " + update);
        ResponseEntity<Schedule> response = restTemplate.postForEntity(url, update, Schedule.class);
        assertNotEquals(schedule, response.getBody());
    }

    @Test
    public void e_delete() {
        String url = baseURL + "delete/" + schedule.getClassroomNum();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }

}