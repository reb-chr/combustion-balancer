// I am the sole author of the work in this repository

/**
* Reads in a hydrocarbon and outputs the balanced equation for a complete combustion reaction.
* For example, an input of 'C4H6' would yield '2 C4H6 + 11 O2 --> 8 CO2 + 6 H2O'
*/
public class EquationBalancer {
  private HydrocarbonReader compound;
  private int numCompound;
  private int numO2;
  private int numCO2;
  private int numH2O;

  /**
  * @param String a hydrocarbon
  * Creates an EquationBalancer object which stores information about the hydrocarbon
  */
  public EquationBalancer(String compound) {
    this.compound = new HydrocarbonReader(compound);
    numCompound = 1;
    numO2 = 0;
    numCO2 = 0;
    numH2O = 0;
    ensureCompound(this.compound);
  }

  /**
  * @param HydrocarbonReader a compound
  * Prints a message and exits program if the compound is not a possible hydrocarbon,
  * i.e. if the number of hydrogens and oxygens exceeds 2n+2, where n is the number of carbons
  */
  private void ensureCompound(HydrocarbonReader compound) {
    int maxOtherAtoms = (compound.getCarbons() * 2) + 2;
    if (compound.getHydrogens() + compound.getOxygens() > maxOtherAtoms) {
      System.out.println("Please enter a valid hydrocarbon");
      System.exit(0);
    }
  }

  /**
  * Determines the number of the original compound, CO2s, H2Os, and O2s needed to
  * balance a combustion reaction.
  */
  public void balance() {
    if (compound.getHydrogens() % 4 == 0) {
      numCompound = 1;
      numCO2 = compound.getCarbons();
      numH2O = compound.getHydrogens() / 2;
      numO2 = numCO2 + (numH2O / 2);
    } else if (compound.getHydrogens() % 2 == 0) {
      numCompound = 2;
      numCO2 = compound.getCarbons() * 2;
      numH2O = compound.getHydrogens();
      numO2 = numCO2 + (numH2O / 2);
    } else {
      numCompound = 4;
      numCO2 = compound.getCarbons() * 4;
      numH2O = compound.getHydrogens() * 2;
      numO2 = numCO2 + (numH2O / 2);
    }
    balanceOxygens();
    reduce();
  }

  /**
  * Balances the number of oxygens in the compound
  */
  private void balanceOxygens() {
    if (compound.getOxygens() != 0) {
      if (compound.getOxygens() % 2 == 0) {
        numO2 = numO2 - (numCompound * compound.getOxygens() / 2);
      } else {
        numCompound *= 2;
        numCO2 *= 2;
        numH2O *= 2;
        numO2 = (numO2 * 2) - (numCompound * compound.getOxygens() / 2);
      }
    }
  }

  /**
  * Reduces each factor by 2, if applicable
  */
  private void reduce() {
    if (numCompound % 2 == 0 && numCO2 % 2 == 0 && numH2O % 2 == 0 && numO2 % 2 == 0) {
      numCompound /= 2;
      numCO2 /= 2;
      numH2O /= 2;
      numO2 /= 2;
    }
  }
  
  public String toString() {
    return numCompound + " " + compound.toString() + " + " + numO2 + " O2 -> "
      + numCO2 + " CO2 + " + numH2O + " H2O";
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java EquationBalancer CxHyOz");
      System.exit(0);
    } else {
      EquationBalancer equation = new EquationBalancer(args[0]);
      equation.balance();
      System.out.println(equation);
    }
  }

}
