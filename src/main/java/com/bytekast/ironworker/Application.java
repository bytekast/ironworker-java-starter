package com.bytekast.ironworker;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Application implements CommandLineRunner {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... strings) throws Exception {
    parseCommandline(strings);
  }

  private void parseCommandline(String[] args) {

    // Configure iron worker command line options for parsing
    Options options = new Options();
    options.addOption("id", true, ""); // worker id
    options.addOption("d", true, "");  // temp writable directory
    options.addOption("e", true, "");  // environment
    options.addOption("payload", true, ""); // payload file url

    CommandLineParser parser = new BasicParser();
    try {
      CommandLine cl = parser.parse(options, args);
      String payload = cl.getOptionValue("payload");
      String payloadString = FileUtils.readFileToString(new File(payload));

      //  Do something with the payload
      System.out.println(payloadString);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
