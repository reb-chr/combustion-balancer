// I am the sole author of the work in this repository

public class EquationBalancer {
  private HydrocarbonReader compound;
  private int numCompound;
  private int numO2;
  private int numCO2;
  private int numH2O;

  public EquationBalancer(String compound) {
    this.compound = new HydrocarbonReader(compound);
    numCompound = 1;
    numO2 = 0;
    numCO2 = 0;
    numH2O = 0;
  }

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
    assert args.length == 1 : "Usage: java EquationBalancer CxHyOz";
    EquationBalancer equation = new EquationBalancer(args[0]);
    equation.balance();
    System.out.println(equation);
  }

}
