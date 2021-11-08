// I am the sole author of the work in this repository

/**
* This class parses a hydrocarbon in the form a string and stores whether the compound
* contains oxygen atoms
*/
public class HydrocarbonReader {
  private String compound;
  private boolean hasOxygens;

  /**
  * @param String representing the hydrocarbon
  */
  public HydrocarbonReader(String hydrocarbon) {
    compound = hydrocarbon.toLowerCase();
    hasOxygens = compound.contains("o");
  }

  /**
  * @return int number of carbons in the molecule
  */
  public int getCarbons() {
    int c = compound.indexOf("c");
    int h = compound.indexOf("h");
    if (h - c == 1) {
      return 1;
    } else {
      return Integer.parseInt(compound.substring(c + 1, h));
    }
  }

  /**
  * @return int number of hydrogens in the molecule
  */
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

  /*
  * @return int the number of oxygens in the molecule
  */
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
    // HydrocarbonReader hc = new HydrocarbonReader(args[0]);
    // System.out.println(hc.getCarbons() + " " + hc.getHydrogens() + " " + hc.getOxygens());
    // System.out.println(hc);
  }

}
