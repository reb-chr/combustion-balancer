// I am the sole author of the work in this repository

public class HydrocarbonReader {
  private String compound;
  private boolean hasOxygens;

  public HydrocarbonReader(String hydrocarbon) {
    compound = hydrocarbon.toLowerCase();
    hasOxygens = compound.contains("o");
  }

  public int getCarbons() {
    int c = compound.indexOf("c");
    int h = compound.indexOf("h");
    if (h - c == 1) {
      return 1;
    } else {
      return Integer.parseInt(compound.substring(c + 1, h));
    }
  }

  public int getHydrogens() {
    int h = compound.indexOf("h");
    if (hasOxygens) {
      int o = compound.indexOf("o");
      if (o - h == 1) {
        return 1;
      }
      return Integer.parseInt(compound.substring(h + 1, o));
    } else if (h == compound.length() - 1) {
      return 1;
    } else {
      return Integer.parseInt(compound.substring(h + 1));
    }
  }

  public int getOxygens() {
    if (hasOxygens) {
      int o = compound.indexOf("o");
      if (o == compound.length() - 1) {
        return 1;
      } else {
        return Integer.parseInt(compound.substring(o + 1));
      }
    }
    return 0;
  }

  public String toString() {
    return compound.toUpperCase();
  }

  public static void main(String[] args) {
    // assert args.length == 1
    HydrocarbonReader hc = new HydrocarbonReader(args[0]);
    System.out.println(hc.getCarbons() + " " + hc.getHydrogens() + " " + hc.getOxygens());
    System.out.println(hc);
  }

}
