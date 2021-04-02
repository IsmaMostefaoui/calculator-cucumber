package calculator;

public enum Unit {

    m("length", "m",  1),
    ft("length", "ft",  0.3048),
    in("length", "in",  0.0254),
    mi("length", "mi",  1609.334),
    yd("length", "yd", 0.9144),


    m2("area", "m^2",  1),
    km2("area", "km^2", 1000000),


    W("power", "W", 1),
    hp("power", "hp",  745.7),


    J("energy", "J",  1),
    eV("energy", "eV",  1.6021774e-19),
    ftlbf("energy", "ftlbf",  1.355818),
    hph("energy", "hph",  2_684_519.54),


    Pa("pressure", "Pa",  1),
    bar("pressure", "bar",  100000),
    kPa("pressure", "kPa",  1000),
    MPa("pressure", "MPa",  1000000),
    psi("pressure", "psi",  6894.76),


    mps("speed", "mps",  1),
    kmph("speed", "kph",  0.2777777777778),
    mph("speed", "mph",  0.44704),
    kts("speed", "kts",  0.514444444444444),
    ftps("speed", "ftps",  0.3048),


    ms("time", "ms",  0.001),
    s("time", "s",  1),
    min("time", "m", 60),
    h("time", "h",  3600),
    day("time", "day",  86400),


    m3("volume", "m^3",  1),
    ft3("volume", "ft^3",  0.02831685),
    in3("volume", "in^3",  0.000016387064),
    cm3("volume", "cm^3", 0.000001),
    l("volume", "l",  0.001),


    N("weight", "N",  1),
    lbf("weight", "lbf",  4.448222),
    dyn("weight", "dyn",  0.00001),
    pdl("weight", "pdl",  0.1382550),


    lb("mass", "lb",  0.4535924),
    g("mass", "g",  0.001),
    kg("mass", "kg", 1),
    t("mass", "t", 1000);


    final String quantity;
    final String symbol;
    final double multiplier;


    Unit(String quantity, String symbol, double multiplier) {
        this.quantity = quantity;
        this.symbol = symbol;
        this.multiplier = multiplier;
    }


    public static Unit findBySymbol(String symbol) {
        for (Unit value : values()) {
            if(value.symbol.equals(symbol)) {
                return value;
            }
        }
        return null;
    }

    public static Unit getBase(String quantity) {
        for (Unit value : values()) {
            if(value.quantity.equals(quantity) && value.multiplier == 1) {
                return value;
            }
        }

        return null;
    }

}
