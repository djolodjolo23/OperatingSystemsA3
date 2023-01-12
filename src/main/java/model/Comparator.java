package model;

public class Comparator {

  public static java.util.Comparator<Memory> freeBlockSizeComparatorAscending = java.util.Comparator.comparingInt(o -> o.getRequestedCyllinderVisits().size());

  public static java.util.Comparator<Memory> freeBlockSizeComparatorDescending = (o1, o2) -> o2.getRequestedCyllinderVisits().size() - o1.getRequestedCyllinderVisits().size();


}
