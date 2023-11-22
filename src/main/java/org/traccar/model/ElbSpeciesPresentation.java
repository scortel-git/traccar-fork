package org.traccar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class ElbSpeciesPresentation {

    @JsonIgnore
    private static final HashMap<Short, ElbSpeciesPresentation> presentations = new HashMap<Short, ElbSpeciesPresentation>() {{
        put((short) 1, new ElbSpeciesPresentation() {{ setCode("CBF"); setPresent("Филе пеперуда от треска - HEA, с кожа, гръбнак, опашка"); }});
        put((short) 2, new ElbSpeciesPresentation() {{ setCode("CLA"); setPresent("Щипки - Само щипки"); }});
        put((short) 3, new ElbSpeciesPresentation() {{ setCode("DWT"); setPresent("Код на ICCAT - Без хриле, изкормени, отстранена е част от главата, отстранени са перките"); }});
        put((short) 4, new ElbSpeciesPresentation() {{ setCode("FIL"); setPresent("Филетирани - HEA+GUT+TLD, с отстранени кости, от всяка риба се получават две филета, които не са свързани помежду си"); }});
        put((short) 5, new ElbSpeciesPresentation() {{ setCode("FIS"); setPresent("Филета без кожа - FIL+SKI От всяка риба се получават две филета, които не са свързани помежду си"); }});
        put((short) 6, new ElbSpeciesPresentation() {{ setCode("FSB"); setPresent("Филета с кожа + кости - Филета с кожа и кости"); }});
        put((short) 7, new ElbSpeciesPresentation() {{ setCode("FSP"); setPresent("Филета без кожа с гръдни кости - Филета без кожа и гръдни кости"); }});
        put((short) 8, new ElbSpeciesPresentation() {{ setCode("GHT"); setPresent("Изкормени, без глави и опашки - GUH+TLD"); }});
        put((short) 9, new ElbSpeciesPresentation() {{ setCode("GUG"); setPresent("Изкормени и без хриле - Отстранени са вътрешностите и хрилете"); }});
        put((short) 10, new ElbSpeciesPresentation() {{ setCode("GUH"); setPresent("Изкормени и без глави - Отстранени са вътрешностите и главата"); }});
        put((short) 11, new ElbSpeciesPresentation() {{ setCode("GUL"); setPresent("Изкормени със запазен черен дроб - GUT без отстраняване на части от черния дроб"); }});
        put((short) 12, new ElbSpeciesPresentation() {{ setCode("GUS"); setPresent("Изкормени, без глави и кожа - GUH+SKI"); }});
        put((short) 13, new ElbSpeciesPresentation() {{ setCode("GUT"); setPresent("Изкормени - Отстранени са всички вътрешности"); }});
        put((short) 14, new ElbSpeciesPresentation() {{ setCode("HEA"); setPresent("Без глави - Отстранени са главите"); }});
        put((short) 15, new ElbSpeciesPresentation() {{ setCode("JAP"); setPresent("Японско нарязване - Напречно нарязване с отстраняване на всички части от главата до опашката"); }});
        put((short) 16, new ElbSpeciesPresentation() {{ setCode("JAT"); setPresent("Японско нарязване без опашки - Японско нарязване с отстранена опашка"); }});
        put((short) 17, new ElbSpeciesPresentation() {{ setCode("LAP"); setPresent("На парчета - Двойно филе, HEA, с кожа, опашка и перки"); }});
        put((short) 18, new ElbSpeciesPresentation() {{ setCode("LVR"); setPresent("Черен дроб - Само черен дроб, при колективно представяне да се използва код LVR-C"); }});
        put((short) 19, new ElbSpeciesPresentation() {{ setCode("OTH"); setPresent("Други"); }});
        put((short) 20, new ElbSpeciesPresentation() {{ setCode("ROE"); setPresent("Хайвер(и) - Само хайвер(и), при колективно представяне да се използва код ROE-C"); }});
        put((short) 21, new ElbSpeciesPresentation() {{ setCode("ROE-C"); setPresent("Хайвер(и) - Колективно представяне"); }});
        put((short) 22, new ElbSpeciesPresentation() {{ setCode("SAD"); setPresent("Осолена и изсушена риба - С отстранена глава, с кожа, с гръбнак, с опашка, директно осолена"); }});
        put((short) 23, new ElbSpeciesPresentation() {{ setCode("SAL"); setPresent("Леко осолена риба в саламура - CBF+осолена"); }});
        put((short) 24, new ElbSpeciesPresentation() {{ setCode("SGH"); setPresent("Осолени, изкормени и без глава - GUH+осолени"); }});
        put((short) 25, new ElbSpeciesPresentation() {{ setCode("SGT"); setPresent("Осолени, изкормени - GUT+осолени"); }});
        put((short) 26, new ElbSpeciesPresentation() {{ setCode("SKI"); setPresent("Без кожа - С отстранена кожа"); }});
        put((short) 27, new ElbSpeciesPresentation() {{ setCode("SUR"); setPresent("Сурими"); }});
        put((short) 28, new ElbSpeciesPresentation() {{ setCode("TAL"); setPresent("Опашки - Само опашки"); }});
        put((short) 29, new ElbSpeciesPresentation() {{ setCode("TLD"); setPresent("Без опашки - Опашките са отстранени"); }});
        put((short) 30, new ElbSpeciesPresentation() {{ setCode("TNG"); setPresent("Език - Само език. В случай на колективно представяне да се използва код TNG-C"); }});
        put((short) 31, new ElbSpeciesPresentation() {{ setCode("TNG-C"); setPresent("Език - Колективно представяне"); }});
        put((short) 32, new ElbSpeciesPresentation() {{ setCode("TUB"); setPresent("Само туби - Само туби (калмари)"); }});
        put((short) 33, new ElbSpeciesPresentation() {{ setCode("WHL"); setPresent("Цели - Без преработка"); }});
        put((short) 34, new ElbSpeciesPresentation() {{ setCode("WNG"); setPresent("Криле - Само криле"); }});
    }};

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    private String code;
    private String present;

    @JsonIgnore
    public static ElbSpeciesPresentation getSpeciesPresentation(short sequence) {
        return presentations.getOrDefault(sequence, new ElbSpeciesPresentation(){{setCode("N/A"); setPresent("Not available record"); }});

    }

}
