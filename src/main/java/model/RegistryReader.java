package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import model.AllocationType;
import model.Command;

public class RegistryReader implements IntegerChecker {


  private Memory memory;

  public RegistryReader(Memory memory) {
    this.memory = memory;
  }


  private String getInputPath() {
    Path path = Paths.get("Scenario1.in.txt");
    return path.toAbsolutePath().toString();
  }

  private Path getInputPathShort() {
    return Paths.get("Scenario1.in.txt");
  }

  private Path getOutputPath() {
    return Paths.get("Scenario1.out.txt");
  }

  public void loadFile() throws IOException {
    Scanner scanner = new Scanner(new FileReader(getInputPath(), Charset.defaultCharset()));
    while (scanner.hasNextLine()) {
      String[] line = scanner.nextLine().split(";");
      if (integerCheck(line[0])) {
        var command = new Command(Integer.parseInt(line[0]), memory);
        memory.addToAllCommands(command);
      } else {
        var command = new Command(String.valueOf(line[0]));
        memory.addToAllCommands(command);
      }
    }
  }



  public void saveFinalFile(int cylinderMovements, String sb, char fitType) throws IOException {
    try (PrintWriter pw = new PrintWriter(new FileWriter(getOutputPath().toString(), true))) {
      printAndFormat(pw, fitType, cylinderMovements, sb);
    }
  }

  private void printAndFormat(PrintWriter printWriter, char allocationType, int cylinderMovements, String sb) {
    if (allocationType == AllocationType.FCFS.getValue()) {
      printWriter.printf("FCFS%n");
    }
    if (allocationType == AllocationType.SCAN.getValue()) {
      printWriter.printf("SCAN%n");
    }
    if (allocationType == AllocationType.CSCAN.getValue()) {
      printWriter.printf("C-SCAN%n");
    }
    printWriter.printf(String.valueOf(cylinderMovements));
    printWriter.printf("%n");
    printWriter.printf(sb);
    printWriter.close();
  }

}


