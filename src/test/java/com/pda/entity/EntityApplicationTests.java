package com.pda.entity;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class EntityApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Test
	public void testClass() {
		File file = new File(
				getClass().getClassLoader().getResource("templates/MemberAttestation.html").getFile());

		System.out.println(file.exists());

	}
}
