package controller;

import java.io.IOException;
import model.Memory;
import model.RegistryReader;
import strategy.AbstractStrategyFactory;

public class Controller {

  private final RegistryReader registryReader;

  public Controller(RegistryReader registryReader) {
    this.registryReader = registryReader;
  }

  public void run(AbstractStrategyFactory abstractStrategyFactory, Memory memory) throws IOException {
    registryReader.loadFile();
    abstractStrategyFactory.getFcfsAllocationRule(registryReader, memory);
    abstractStrategyFactory.getScanAllocationRule(registryReader, memory);
  }
}
