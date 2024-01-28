package org.traccar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
public class ElbSpecies extends ExtendedModel {
    @JsonIgnore
    public static final HashMap<Short, ElbSpecies> elbSpecies = new HashMap<Short, ElbSpecies>() {{
        put((short) 1, new ElbSpecies() {{ setCode("TRS"); setType(1); setNameBG("Речна (балканска) пъстърва"); setNameGB("Brown trout"); }});
        put((short) 2, new ElbSpecies() {{ setCode("TRR"); setType(1); setNameBG("Дъгова пъстърва"); setNameGB("Rainbow trout"); }});
        put((short) 3, new ElbSpecies() {{ setCode("SVF"); setType(1); setNameBG("Сивен"); setNameGB("Brook trout"); }});
        put((short) 4, new ElbSpecies() {{ setCode("HUC"); setType(1); setNameBG("Дунавска пъстърва"); setNameGB("Huchen (Danube trout)"); }});
        put((short) 5, new ElbSpecies() {{ setCode("PLN"); setType(1); setNameBG("Чудски сиг"); setNameGB("Common whitefish"); }});
        put((short) 6, new ElbSpecies() {{ setCode("WHF"); setType(1); setNameBG("Пелед"); setNameGB("Peled"); }});
        put((short) 7, new ElbSpecies() {{ setCode("TLV"); setType(1); setNameBG("Липан"); setNameGB("Grayling"); }});
        put((short) 8, new ElbSpecies() {{ setCode("FPI"); setType(1); setNameBG("Щука"); setNameGB("Pike"); }});
        put((short) 9, new ElbSpecies() {{ setCode("LUH"); setType(1); setNameBG("Речен кефал"); setNameGB("Chub"); }});
        put((short) 10, new ElbSpecies() {{ setCode("FID"); setType(1); setNameBG("Мъздруга"); setNameGB("Ide"); }});
        put((short) 11, new ElbSpecies() {{ setCode("ASU"); setType(1); setNameBG("Распер"); setNameGB("Asp"); }});
        put((short) 12, new ElbSpecies() {{ setCode("FTE"); setType(1); setNameBG("Лин"); setNameGB("Tench"); }});
        put((short) 13, new ElbSpecies() {{ setCode("NUC"); setType(1); setNameBG("Брияна (облез, уклей)"); setNameGB("Danube bleak"); }});
        put((short) 14, new ElbSpecies() {{ setCode("ALR"); setType(1); setNameBG("Уклей"); setNameGB("Bleak"); }});
        put((short) 15, new ElbSpecies() {{ setCode("FBM"); setType(1); setNameBG("Платика"); setNameGB("Carp bream"); }});
        put((short) 16, new ElbSpecies() {{ setCode(""); setType(1); setNameBG("Немски косат"); setNameGB("White-eye bream"); }});
        put((short) 17, new ElbSpecies() {{ setCode("FBR"); setType(1); setNameBG("Чил косат"); setNameGB("Zope"); }});
        put((short) 18, new ElbSpecies() {{ setCode("VIV"); setType(1); setNameBG("Морунаш"); setNameGB("Vimba"); }});
        put((short) 19, new ElbSpecies() {{ setCode("VIM"); setType(1); setNameBG("Маришки морунаш (карабалък)"); setNameGB("Macedonian vimba"); }});
        put((short) 20, new ElbSpecies() {{ setCode("FSC"); setType(1); setNameBG("Сабица"); setNameGB("Ziege"); }});
        put((short) 21, new ElbSpecies() {{ setCode("HON"); setType(1); setNameBG("Скобар"); setNameGB("Undermouth"); }});
        put((short) 22, new ElbSpecies() {{ setCode("PTB"); setType(1); setNameBG("Бяла мряна"); setNameGB("Barbel"); }});
        put((short) 23, new ElbSpecies() {{ setCode("BUD"); setType(1); setNameBG("Черна (балканска) мряна"); setNameGB("Mediterranean barbel"); }});
        put((short) 24, new ElbSpecies() {{ setCode("BBZ"); setType(1); setNameBG("Маришка мряна"); setNameGB("Barbel"); }});
        put((short) 25, new ElbSpecies() {{ setCode("FCP"); setType(1); setNameBG("Шаран"); setNameGB("Common carp"); }});
        put((short) 26, new ElbSpecies() {{ setCode("FC"); setType(1); setNameBG("Златиста каракуда"); setNameGB("Crucian carp"); }});
        put((short) 27, new ElbSpecies() {{ setCode("CGO"); setType(1); setNameBG("Сребриста каракуда"); setNameGB("Prussian carp"); }});
        put((short) 28, new ElbSpecies() {{ setCode("SVC"); setType(1); setNameBG("Бял толстолоб"); setNameGB("Silver carp"); }});
        put((short) 29, new ElbSpecies() {{ setCode("BIC"); setType(1); setNameBG("Пъстър толстолоб"); setNameGB("Bighead carp"); }});
        put((short) 30, new ElbSpecies() {{ setCode("FCG"); setType(1); setNameBG("Бял амур"); setNameGB("Grass carp"); }});
        put((short) 31, new ElbSpecies() {{ setCode("BKC"); setType(1); setNameBG("Черен амур"); setNameGB("Black carp"); }});
        put((short) 32, new ElbSpecies() {{ setCode("BUF"); setType(1); setNameBG("Буфало"); setNameGB("Buffalo"); }});
        put((short) 33, new ElbSpecies() {{ setCode("SOM"); setType(1); setNameBG("Сом"); setNameGB("Wels catfish"); }});
        put((short) 34, new ElbSpecies() {{ setCode("ITP"); setType(1); setNameBG("Канален (американски) сом"); setNameGB("Channel catfish"); }});
        put((short) 35, new ElbSpecies() {{ setCode("FBU"); setType(1); setNameBG("Михалца"); setNameGB("Burbot"); }});
        put((short) 36, new ElbSpecies() {{ setCode("FPP"); setType(1); setNameBG("Бяла риба (сулка)"); setNameGB("Pike perch"); }});
        put((short) 37, new ElbSpecies() {{ setCode("FPE"); setType(1); setNameBG("Речен костур"); setNameGB("European perch"); }});
        put((short) 38, new ElbSpecies() {{ setCode("ZIZ"); setType(1); setNameBG("Голяма вретенарка"); setNameGB("Zingel"); }});
        put((short) 39, new ElbSpecies() {{ setCode("APR"); setType(1); setNameBG("Чига"); setNameGB("Sterlet"); }});
        put((short) 40, new ElbSpecies() {{ setCode("HUH"); setType(2); setNameBG("Моруна"); setNameGB("Beluga"); }});
        put((short) 41, new ElbSpecies() {{ setCode("APE"); setType(2); setNameBG("Пъструга"); setNameGB("Starry sturgeon"); }});
        put((short) 42, new ElbSpecies() {{ setCode("APG"); setType(2); setNameBG("Руска есетра"); setNameGB("Russian sturegon"); }});
        put((short) 43, new ElbSpecies() {{ setCode("ELE"); setType(2); setNameBG("Европейска речна змиорка"); setNameGB("Eel"); }});
        put((short) 44, new ElbSpecies() {{ setCode("SHC"); setType(2); setNameBG("Карагьоз, дунавска скумрия"); setNameGB("Shad"); }});
        put((short) 45, new ElbSpecies() {{ setCode("DGS"); setType(3); setNameBG("Черноморска бодлива акула"); setNameGB("Piked dogfish"); }});
        put((short) 46, new ElbSpecies() {{ setCode("SPR"); setType(3); setNameBG("Цаца (копърка, трицона, шпрот)"); setNameGB("European sprat"); }});
        put((short) 47, new ElbSpecies() {{ setCode("WHG"); setType(3); setNameBG("Черноморски меджид"); setNameGB("Whiting"); }});
        put((short) 48, new ElbSpecies() {{ setCode("HKE"); setType(3); setNameBG("Мерлуза"); setNameGB("European hake"); }});
        put((short) 49, new ElbSpecies() {{ setCode("MUF"); setType(3); setNameBG("Морски кефал"); setNameGB("Flathead mullet"); }});
        put((short) 50, new ElbSpecies() {{ setCode("MYZ"); setType(3); setNameBG("Кефал пелингас"); setNameGB("So-iuy mullet"); }});
        put((short) 51, new ElbSpecies() {{ setCode("MGA"); setType(3); setNameBG("Платерина"); setNameGB("Golden grey mullet"); }});
        put((short) 52, new ElbSpecies() {{ setCode("LZS"); setType(3); setNameBG("Илария"); setNameGB("Leaping mullet"); }});
        put((short) 53, new ElbSpecies() {{ setCode("SIL"); setType(3); setNameBG("Атерина"); setNameGB("Silverside"); }});
        put((short) 54, new ElbSpecies() {{ setCode("BSS"); setType(3); setNameBG("Лаврак"); setNameGB("European seabass"); }});
        put((short) 55, new ElbSpecies() {{ setCode("BLU"); setType(3); setNameBG("Лефер"); setNameGB("Bluefish"); }});
        put((short) 56, new ElbSpecies() {{ setCode("HMM"); setType(3); setNameBG("Сафрид"); setNameGB("Mediterranean horse mackerel"); }});
        put((short) 57, new ElbSpecies() {{ setCode("PIC"); setType(3); setNameBG("Смарид"); setNameGB("Picarel"); }});
        put((short) 58, new ElbSpecies() {{ setCode("MUT"); setType(3); setNameBG("Барбуня"); setNameGB("Red mullet"); }});
        put((short) 59, new ElbSpecies() {{ setCode("MUR"); setType(3); setNameBG("Барбуня"); setNameGB("Red mullet"); }});
        put((short) 60, new ElbSpecies() {{ setCode("MAC"); setType(3); setNameBG("Черноморска скумрия"); setNameGB("Mackerel"); }});
        put((short) 61, new ElbSpecies() {{ setCode("BON"); setType(3); setNameBG("Паламуд"); setNameGB("Atlantic bonito"); }});
        put((short) 62, new ElbSpecies() {{ setCode("GPA"); setType(3); setNameBG("Попчета"); setNameGB("Gobies"); }});
        put((short) 63, new ElbSpecies() {{ setCode("TUR"); setType(3); setNameBG("Калкан"); setNameGB("Turbot"); }});
        put((short) 64, new ElbSpecies() {{ setCode("FLE"); setType(3); setNameBG("Писия"); setNameGB("Flounder"); }});
        put((short) 65, new ElbSpecies() {{ setCode("SOL"); setType(3); setNameBG("Морски език"); setNameGB("Common sole"); }});
        put((short) 66, new ElbSpecies() {{ setCode("RJC"); setType(3); setNameBG("Морска лисица"); setNameGB("Thornback ray"); }});
        put((short) 67, new ElbSpecies() {{ setCode("JDP"); setType(3); setNameBG("Морска котка"); setNameGB("Common stingray"); }});
        put((short) 68, new ElbSpecies() {{ setCode("ALB"); setType(3); setNameBG("Дългопер тон"); setNameGB("Albacore"); }});
        put((short) 69, new ElbSpecies() {{ setCode("BET"); setType(3); setNameBG("Тон"); setNameGB("Bigeye tuna"); }});
        put((short) 70, new ElbSpecies() {{ setCode("BFT"); setType(3); setNameBG("Тон"); setNameGB("Bluefin tuna"); }});
        put((short) 71, new ElbSpecies() {{ setCode("WHB"); setType(3); setNameBG("Путасу"); setNameGB("Blue whiting"); }});
        put((short) 72, new ElbSpecies() {{ setCode("SBG"); setType(3); setNameBG("Ципура"); setNameGB("Gilt-head seabream"); }});
        put((short) 73, new ElbSpecies() {{ setCode("ANF"); setType(3); setNameBG("Морски дявол"); setNameGB("Monkfish"); }});
        put((short) 74, new ElbSpecies() {{ setCode("SWO"); setType(3); setNameBG("Риба меч"); setNameGB("Swordfish"); }});
        put((short) 75, new ElbSpecies() {{ setCode("PIL"); setType(3); setNameBG("Сардина"); setNameGB("Sardine"); }});
        put((short) 76, new ElbSpecies() {{ setCode("FGI"); setType(4); setNameBG("Голяма водна жаба"); setNameGB("Marsh frog"); }});
        put((short) 77, new ElbSpecies() {{ setCode(""); setType(4); setNameBG("Обикновени скариди"); setNameGB("Deepwater prawn"); }});
        put((short) 78, new ElbSpecies() {{ setCode("CSH"); setType(4); setNameBG("Пясъчна скарида"); setNameGB("Common shrimp"); }});
        put((short) 79, new ElbSpecies() {{ setCode("AYS"); setType(4); setNameBG("Езерен рак"); setNameGB("Crayfish"); }});
        put((short) 80, new ElbSpecies() {{ setCode("EIK"); setType(4); setNameBG("Пагур"); setNameGB(""); }});
        put((short) 81, new ElbSpecies() {{ setCode("CRG"); setType(4); setNameBG("Обикновен (зелен) крив рак"); setNameGB("Common shore crab"); }});
        put((short) 82, new ElbSpecies() {{ setCode(""); setType(4); setNameBG("Миди речни, седефки"); setNameGB("Freshwater mussel"); }});
        put((short) 83, new ElbSpecies() {{ setCode(""); setType(4); setNameBG("Миди речни, беззъбки"); setNameGB("Duck mussel"); }});
        put((short) 84, new ElbSpecies() {{ setCode("MSM"); setType(4); setNameBG("Черна морска мида"); setNameGB("Black mussel"); }});
        put((short) 85, new ElbSpecies() {{ setCode("CLS"); setType(4); setNameBG("Бяла пясъчна мида"); setNameGB("Soft-shelled clam"); }});
        put((short) 86, new ElbSpecies() {{ setCode("RPN"); setType(4); setNameBG("Рапан"); setNameGB("Rapana"); }});
        put((short) 87, new ElbSpecies() {{ setCode("ALF"); setType(3); setNameBG("Берикс"); setNameGB("Beryx spp"); }});
        put((short) 88, new ElbSpecies() {{ setCode("ANE"); setType(3); setNameBG("Хамсия"); setNameGB("Engraulis encrasicholus"); }});
        put((short) 89, new ElbSpecies() {{ setCode("ANF"); setType(3); setNameBG("Морски дявол"); setNameGB("Lophiidae"); }});
        put((short) 90, new ElbSpecies() {{ setCode("ANI"); setType(3); setNameBG("Антарктическа ледена риба"); setNameGB("Champsocephalus gunnari"); }});
        put((short) 91, new ElbSpecies() {{ setCode("ARU"); setType(3); setNameBG("Атлантическа аргентина"); setNameGB("Argentina silus"); }});
        put((short) 92, new ElbSpecies() {{ setCode("BLI"); setType(3); setNameBG("Синя молва"); setNameGB("Molva dypterygia"); }});
        put((short) 93, new ElbSpecies() {{ setCode("BLL"); setType(3); setNameBG("Средиземноморски калкан"); setNameGB("Scophthalmus rhombus"); }});
        put((short) 94, new ElbSpecies() {{ setCode("BSF"); setType(3); setNameBG("Афанопус"); setNameGB("Aphanopus carbo"); }});
        put((short) 95, new ElbSpecies() {{ setCode("BUM"); setType(3); setNameBG("Атлантически син марлин"); setNameGB("Makaira nigricans"); }});
        put((short) 96, new ElbSpecies() {{ setCode("CAP"); setType(3); setNameBG("Мойва"); setNameGB("Mallotus villosus"); }});
        put((short) 97, new ElbSpecies() {{ setCode("COD"); setType(3); setNameBG("Атлантическа треска"); setNameGB("Gadus morhua"); }});
        put((short) 98, new ElbSpecies() {{ setCode("DAB"); setType(3); setNameBG("Лиманда"); setNameGB("Limanda limanda"); }});
        put((short) 99, new ElbSpecies() {{ setCode("GFB"); setType(3); setNameBG("Брадата мерлуза"); setNameGB("Phycis blennoides"); }});
        put((short) 100, new ElbSpecies() {{ setCode("GHL"); setType(3); setNameBG("Гренландска камбала"); setNameGB("Reinhardtius hippoglossoides"); }});
        put((short) 101, new ElbSpecies() {{ setCode("HAD"); setType(3); setNameBG("Пикша"); setNameGB("Melanogrammus aeglefinus"); }});
        put((short) 102, new ElbSpecies() {{ setCode("HAL"); setType(3); setNameBG("Атлантическа камбала"); setNameGB("Hippoglossus hippoglossus"); }});
        put((short) 103, new ElbSpecies() {{ setCode("HER"); setType(3); setNameBG("Херинга"); setNameGB("Clupea harengus"); }});
        put((short) 104, new ElbSpecies() {{ setCode("HKE"); setType(3); setNameBG("Мерлуза"); setNameGB("Merluccius merluccius"); }});
        put((short) 105, new ElbSpecies() {{ setCode("HKW"); setType(3); setNameBG("Бяла мерлуза"); setNameGB("Urophycis tenuis"); }});
        put((short) 106, new ElbSpecies() {{ setCode("JAX"); setType(3); setNameBG("Сафрид друг"); setNameGB("Trachurus spp"); }});
        put((short) 107, new ElbSpecies() {{ setCode("KRI"); setType(3); setNameBG("Антарктически крил"); setNameGB("Euphausia superba"); }});
        put((short) 108, new ElbSpecies() {{ setCode("LEM"); setType(3); setNameBG("Малоуста писия"); setNameGB("Microstomus kitt"); }});
        put((short) 109, new ElbSpecies() {{ setCode("LEZ"); setType(3); setNameBG("Мегрим"); setNameGB("Lepidorhombus spp"); }});
        put((short) 110, new ElbSpecies() {{ setCode("LIC"); setType(3); setNameBG("Ледена риба-еднорог"); setNameGB("Channichthys rhinoceratus"); }});
        put((short) 111, new ElbSpecies() {{ setCode("LIN"); setType(3); setNameBG("Морска щука"); setNameGB("Molva molva"); }});
        put((short) 112, new ElbSpecies() {{ setCode("NEP"); setType(3); setNameBG("Норвежки омар"); setNameGB("Nephrops norvegicus"); }});
        put((short) 113, new ElbSpecies() {{ setCode("NOG"); setType(3); setNameBG("Гърбава скална треска"); setNameGB("Notothenia gibberifrons"); }});
        put((short) 114, new ElbSpecies() {{ setCode("NOP"); setType(3); setNameBG("Норвежки паут"); setNameGB("Trisopterus esmarkii"); }});
        put((short) 115, new ElbSpecies() {{ setCode("NOR"); setType(3); setNameBG("Мраморна скална треска"); setNameGB("Notothenia rossii"); }});
        put((short) 116, new ElbSpecies() {{ setCode("ORY"); setType(3); setNameBG("Атлантически големоглав"); setNameGB("Hoplostethus atlanticus"); }});
        put((short) 117, new ElbSpecies() {{ setCode("PCR"); setType(3); setNameBG("Снежен краб"); setNameGB("Chionoecetes spp"); }});
        put((short) 118, new ElbSpecies() {{ setCode("PEN"); setType(3); setNameBG("Розова скарида"); setNameGB("Penaeus spp."); }});
        put((short) 119, new ElbSpecies() {{ setCode("PLE"); setType(3); setNameBG("Морска писия"); setNameGB("Pleuronectes platessa"); }});
        put((short) 120, new ElbSpecies() {{ setCode("POK"); setType(3); setNameBG("Сайда"); setNameGB("Pollachius virens"); }});
        put((short) 121, new ElbSpecies() {{ setCode("POL"); setType(3); setNameBG("Сребриста сайда"); setNameGB("Pollachius pollachius"); }});
        put((short) 122, new ElbSpecies() {{ setCode("PRA"); setType(3); setNameBG("Северна скарида"); setNameGB("Pandalus borealis"); }});
        put((short) 123, new ElbSpecies() {{ setCode("RED"); setType(3); setNameBG("Морски бибани"); setNameGB("Sebastes spp"); }});
        put((short) 124, new ElbSpecies() {{ setCode("RHG"); setType(3); setNameBG("Дългоопашата риба"); setNameGB("Macrourus berglax"); }});
        put((short) 125, new ElbSpecies() {{ setCode("RNG"); setType(3); setNameBG("Гренадир"); setNameGB("Coryphaenoides rupestris"); }});
        put((short) 126, new ElbSpecies() {{ setCode("SAN"); setType(3); setNameBG("Пясъчна змиорка"); setNameGB("Ammodytes spp"); }});
        put((short) 127, new ElbSpecies() {{ setCode("SBR"); setType(3); setNameBG("Северен пагел"); setNameGB("Pagellus bogaraveo"); }});
        put((short) 128, new ElbSpecies() {{ setCode("SDH"); setType(3); setNameBG("Остроноса акула"); setNameGB("Deania histricosa"); }});
        put((short) 129, new ElbSpecies() {{ setCode("SDU"); setType(3); setNameBG("Дълбоководна акула"); setNameGB("Deania profundorum"); }});
        put((short) 130, new ElbSpecies() {{ setCode("SGI"); setType(3); setNameBG("Южноджорджийска ледена риба"); setNameGB("Pseudochaenichthys georgianus"); }});
        put((short) 131, new ElbSpecies() {{ setCode("SQI"); setType(3); setNameBG("Късоопашат калмар"); setNameGB("Illex illecebrosus"); }});
        put((short) 132, new ElbSpecies() {{ setCode("SQS"); setType(3); setNameBG("Калмар"); setNameGB("Martialia hyadesi"); }});
        put((short) 133, new ElbSpecies() {{ setCode("SRX"); setType(3); setNameBG("Скатови"); setNameGB("Rajidae"); }});
        put((short) 134, new ElbSpecies() {{ setCode("TOP"); setType(3); setNameBG("Патагонски кликач"); setNameGB("Dissostichus eleginoides"); }});
        put((short) 135, new ElbSpecies() {{ setCode("USK"); setType(3); setNameBG("Менек"); setNameGB("Brosme brosme"); }});
        put((short) 136, new ElbSpecies() {{ setCode("WHM"); setType(3); setNameBG("Бял копиеносец"); setNameGB("Tetrapturus albidus"); }});
        put((short) 137, new ElbSpecies() {{ setCode("WIT"); setType(3); setNameBG("Червена писия"); setNameGB("Glyptocephalus cynoglossus"); }});
        put((short) 138, new ElbSpecies() {{ setCode("YEL"); setType(3); setNameBG("Жълтоопашата лиманда"); setNameGB("Limanda ferruginea"); }});
        put((short) 139, new ElbSpecies() {{ setCode("GAR"); setType(3); setNameBG("Зарган"); setNameGB(""); }});
    }};

    private String nameBG = "NA";
    public String getNameBG() {
        return nameBG;
    }
    public void setNameBG(String value) {
        this.nameBG = value;
    }
    private String nameGB = "NA";
    public String getNameGB() {
        return nameGB;
    }
    public void setNameGB(String value) {
        nameGB = value;
    }
    private int type = -1;
    public int getType() {
        return type;
    }
    public void setType(int i) {
        type = i;
    }
    private String code = "NA";
    public String getCode() {
        return code;
    }
    public void setCode(String str) {
        code = str.trim().toUpperCase();
    }
    private String presentation = "NA";
    public void setPresentation(String presentation) {
        this.presentation = presentation.trim().toUpperCase();
    }
    public String getPresentation() {
        return presentation;
    }



    public ElbSpecies getSpecies(short sequence) {
        return elbSpecies.getOrDefault(sequence, new ElbSpecies());
    }



}

