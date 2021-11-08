// I am the sole author of the work in this repository

/**
* This class parses a hydrocarbon in the form a string
*/
public class HydrocarbonReader {
  private String compound;

  /**
  * @param String representing the hydrocarbon
  */
  public HydrocarbonReader(String hydrocarbon) {
    compound = hydrocarbon.toLowerCase();
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
    if (h == compound.length() - 1) {
      return 1;
    } else {
      return Integer.parseInt(compound.substring(h + 1));
    }
  }

  public String toString() {
    return compound.toUpperCase();
  }

  public static void main(String[] args) {
    // HydrocarbonReader hc = new HydrocarbonReader(args[0]);
    // System.out.println(hc.getCarbons() + " " + hc.getHydrogens());
    // System.out.println(hc);
  }

}
