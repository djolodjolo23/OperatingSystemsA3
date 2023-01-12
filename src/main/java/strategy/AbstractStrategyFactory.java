package strategy;

import java.io.IOException;
import model.Memory;
import model.RegistryReader;

public interface AbstractStrategyFactory {

  void getFcfsAllocationRule(RegistryReader registryReader, Memory memory) throws IOException;

  void getScanAllocationRule(RegistryReader registryReader, Memory memory) throws IOException;

  void getCScanAllocationRule(RegistryReader registryReader, Memory memory) throws IOException;
}



