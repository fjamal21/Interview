package com.interset.DataIntegrationTask;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskRunner {
  private static final ObjectMapper objMapper = new ObjectMapper();

  public static void main(String args[]) {
    // Check arguments
    if (args.length != 2) {
      System.out
          .println("We currently only expect 2 arguments! A path to a JSON file to read, and a path for a CSV file to write.");
      System.exit(1);
    }

    // Read arguments
    Path jsonFile = null;

    try {
      jsonFile = Paths.get(args[0]);
    } catch (InvalidPathException e) {
      System.err.println("Couldn't convert JSON file argument [" + args[0] + "] into a path!");
      throw e;
    }

    Path csvFile = null;

    try {
      csvFile = Paths.get(args[1]);
    } catch (InvalidPathException e) {
      System.err.println("Couldn't convert CSV file argument [" + args[1] + "] into a path!");
      throw e;
    }

    // Validate arguments
    if (!Files.exists(jsonFile)) {
      System.err.println("JSON file [" + jsonFile.toString() + "] doesn't exist!");
      System.exit(1);
    }

    if (!Files.isWritable(csvFile.getParent())) {
      System.err.println("Can't write to the directory [" + csvFile.getParent().toString()
          + "] to create the CSV file! Does directory exist?");
      System.exit(1);
    }

    // Create the CSV file
    System.out.println("Reading file [" + jsonFile.toString() + "], and writing to file ["
        + csvFile.toString() + "].");

    parseJsonFileAndCreateCsvFile(jsonFile, csvFile);
  }

  public static void parseJsonFileAndCreateCsvFile(final Path jsonFile, final Path csvFile) {
    List<Metadata> metadatas = new ArrayList<>();
    try (BufferedReader reader =
        Files.newBufferedReader(jsonFile, Charset.forName(StandardCharsets.UTF_8.displayName()))) {
      String line = null;
      while ((line = reader.readLine()) != null) {
        Metadata metadata = objMapper.readValue(line.trim(), Metadata.class);
        metadatas.add(metadata);
      }
      CSVStatsUtils.writeAndGenerateStats(csvFile, metadatas);
    } catch (Exception ex) {
      System.out.println("error= " + ExceptionUtils.getStackTrace(ex));
    }
  }
}
