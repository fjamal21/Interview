package com.interset.DataIntegrationTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;

/**
 * A simple utility that generates CSV and stats basis on the logic we have.
 * 
 */
public final class CSVStatsUtils {
  private static final ObjectMapper objMapper = new ObjectMapper();
  private static final char DEFAULT_SEPARATOR = ',';
  private static final Map<String, String> ACTION_MAPPINGS =
      ImmutableMap.<String, String>builder().put("createdDoc", "ADD").put("addedText", "ADD")
          .put("changedText", "ADD").put("deletedDoc", "REMOVE").put("deletedText", "REMOVE")
          .put("archived", "REMOVE").put("viewedDoc", "ACCESSED").build();

  private CSVStatsUtils() {
    // restrict instantiation
  }

  /**
   * Write into CSV file basis on conditions and generate stats accordingly.
   * 
   * @param csvFile
   * @param metadatas
   * @throws IOException
   */
  public static void writeAndGenerateStats(Path csvFile, List<Metadata> metadatas)
      throws IOException {
    writeAndGenerateStats(csvFile, metadatas, DEFAULT_SEPARATOR);
  }

  /**
   * Write into CSV file basis on conditions and generate stats accordingly.
   * 
   * @param csvFile
   * @param metadatas
   * @param separators
   * @throws IOException
   */
  public static void writeAndGenerateStats(Path csvFile, List<Metadata> metadatas, char separators)
      throws IOException {
    Multiset<String> multiset = HashMultiset.create();
    int noMapping = 0;
    int duplicate = 0;
    Set<String> usernames = new HashSet<>();
    Set<String> files = new HashSet<>();
    Set<Long> eventIds = new HashSet<>();
    List<OffsetDateTime> timestamp = new ArrayList<>();
    boolean first = true;
    if (!Files.exists(csvFile, LinkOption.NOFOLLOW_LINKS)) {
      Files.createFile(csvFile);
    }
    FileWriter writer = new FileWriter(csvFile.toFile());
    if (separators == ' ') {
      separators = DEFAULT_SEPARATOR;
    }

    StatsFilter stats = new StatsFilter();
    stats.setLinesRead(Long.valueOf(metadatas.size()));
    StringBuilder sb = new StringBuilder();
    for (Metadata value : metadatas) {
      if (first) {
        sb.append("TIMESTP").append(separators).append("ACTION").append(separators).append("USER")
            .append(separators).append("FOLDER").append(separators).append("FILENE")
            .append(separators).append("IP");
        sb.append("\n");
        first = false;
      }
      usernames.add(value.getUser());
      files.add(value.getFile());
      timestamp.add(formatTimestamp(value.getTimestamp(), value.getTimeOffset()));
      String mapping = ACTION_MAPPINGS.get(value.getActivity());
      if (Strings.isNullOrEmpty(mapping)) {
        noMapping++;
        System.out.println("cannot find action mappings for metadata activity: "
            + value.getActivity() + ", metadata= " + value);
        continue;
      }
      if (!eventIds.add(value.getEventId())) {
        duplicate++;
        System.out.println("duplicate metadata objects: " + value);
        continue;
      }
      multiset.add(mapping);
      String user = value.getUser().contains("@") ? value.getUser().split("@")[0] : value.getUser();
      sb.append(formatTimestamp(value.getTimestamp(), value.getTimeOffset())).append(separators)
          .append(mapping).append(separators).append(StringEscapeUtils.escapeCsv(user))
          .append(separators)
          .append(StringEscapeUtils.escapeCsv(new File(value.getFile()).getParent()))
          .append(separators)
          .append(StringEscapeUtils.escapeCsv(new File(value.getFile()).getName()))
          .append(separators).append(StringEscapeUtils.escapeCsv(value.getIpAddr()));
      sb.append("\n");
    }
    writer.append(sb.toString());
    writer.flush();
    writer.close();
    System.out.println();
    System.out.println("csv file generation complete");
    System.out.println();

    DroppedEvents events = new DroppedEvents();
    events.setNoActionMapping(Long.valueOf(noMapping));
    events.setDuplicates(Long.valueOf(duplicate));
    stats.setDroppedEventsCounts(Long.valueOf(duplicate + noMapping));
    stats.setUniqueFiles(Long.valueOf(files.size()));
    stats.setUniqueUsers(Long.valueOf(usernames.size()));
    Actions actions = new Actions();
    actions.setADD(Long.valueOf(multiset.count("ADD")));
    actions.setACCESSED(Long.valueOf(multiset.count("ACCESSED")));
    actions.setREMOVE(Long.valueOf(multiset.count("REMOVE")));
    stats.setActions(actions);
    stats.setDroppedEvents(events);
    stats.setStartDate(Collections.min(timestamp).toString());
    stats.setEndDate(Collections.max(timestamp).toString());
    System.out.println("Metrics Output:");
    System.out.println(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stats));
  }

  /**
   * Using newer java.time as it works very well with ISO 8601 strings. If offset is not present
   * then using UTC offset by default.
   * 
   * @param timestamp
   * @param offset
   * @return
   */
  private static OffsetDateTime formatTimestamp(final String timestamp, final String offset) {
    LocalDateTime dateTime =
        LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("MM/dd/uuuu hh:mm:ssa"));
    ZoneOffset zoneOffset = Strings.isNullOrEmpty(offset) ? ZoneOffset.UTC : ZoneOffset.of(offset);
    OffsetDateTime formattedTimestamp = dateTime.atOffset(zoneOffset);
    return formattedTimestamp;
  }
}
