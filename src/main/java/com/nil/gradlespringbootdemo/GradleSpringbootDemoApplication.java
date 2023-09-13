package com.nil.gradlespringbootdemo;

import org.gradle.tooling.internal.protocol.BuildResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.gradle.tooling.*;

import java.io.ByteArrayOutputStream;
import java.io.File;

@SpringBootApplication
public class GradleSpringbootDemoApplication {

	public static void main(String[] args) {

		String projectDirectory = System.getProperty("user.dir");

		// Print the absolute path of the current working directory
		System.out.println("Project Absolute Path: " + projectDirectory);

		// Initialize the GradleConnector
		GradleConnector connector = GradleConnector.newConnector();
		connector.forProjectDirectory(new File(projectDirectory));

		// Establish a connection to the Gradle project
		ProjectConnection connection = connector.connect();

		try {
			// Create a BuildLauncher for the "dependencies" task
			BuildLauncher buildLauncher = connection.newBuild();
			buildLauncher.forTasks("dependencies");

			// Add a listener to capture the output
			ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
			buildLauncher.setStandardOutput(outputBuffer);

			// Build the project to execute the "dependencies" task
			buildLauncher.run();

			// Print the output
			System.out.println("Gradle Dependencies:");
			System.out.println(outputBuffer.toString());


		} finally {
			// Close the Gradle connection
			connection.close();
		}




		SpringApplication.run(GradleSpringbootDemoApplication.class, args);
	}

}

