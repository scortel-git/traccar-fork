/*
 * Copyright 2017 Anton Tananaev (anton@traccar.org)
 * Copyright 2017 Andrey Kunitsyn (andrey@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;



public class ElbPorts extends ExtendedModel {

    public static final HashMap<Short, ElbPorts> elbPortsHashMap = new HashMap<Short, ElbPorts>() {{
        put((short) 1, new ElbPorts() {{ setCode("BGADB"); setCountry("BGR"); setName("Ada Bahcha"); setLatitude(43.12859); setLongitude(27.932804); }});
        put((short) 2, new ElbPorts() {{ setCode("BGAHE"); setCountry("BGR"); setName("Aheloy"); setLatitude(42.636665); setLongitude(27.652222); }});
        put((short) 3, new ElbPorts() {{ setCode("BGAKH"); setCountry("BGR"); setName("Akhtopol"); setLatitude(42.099495); setLongitude(27.94431); }});
        put((short) 4, new ElbPorts() {{ setCode("BGALB"); setCountry("BGR"); setName("Albena"); setLatitude(43.378258); setLongitude(28.098877); }});
        put((short) 5, new ElbPorts() {{ setCode("BGAMO"); setCountry("BGR"); setName("Amonal"); setLatitude(43.185555); setLongitude(27.840555); }});
        put((short) 6, new ElbPorts() {{ setCode("BGANT"); setCountry("BGR"); setName("Anton Ivanov"); setLatitude(43.19612); setLongitude(27.8953); }});
        put((short) 7, new ElbPorts() {{ setCode("BGARK"); setCountry("BGR"); setName("Arkutino"); setLatitude(42.336388); setLongitude(27.732779); }});
        put((short) 8, new ElbPorts() {{ setCode("BGASP"); setCountry("BGR"); setName("Asparuhovo Keya"); setLatitude(43.183887); setLongitude(27.900278); }});
        put((short) 9, new ElbPorts() {{ setCode("BGBAL"); setCountry("BGR"); setName("Balchik"); setLatitude(43.404358); setLongitude(28.166162); }});
        put((short) 10, new ElbPorts() {{ setCode("BGBL1"); setCountry("BGR"); setName("Beloslav 1"); setLatitude(43.19505); setLongitude(27.699759); }});
        put((short) 11, new ElbPorts() {{ setCode("BGBL2"); setCountry("BGR"); setName("Beloslav 2 (Feribotna)"); setLatitude(43.18682); setLongitude(27.679943); }});
        put((short) 12, new ElbPorts() {{ setCode("BGBOL"); setCountry("BGR"); setName("Bolata"); setLatitude(43.3819); setLongitude(28.4708); }});
        put((short) 13, new ElbPorts() {{ setCode("BGBHZ"); setCountry("BGR"); setName("Buhta zapad"); setLatitude(43.197422); setLongitude(27.894777); }});
        put((short) 14, new ElbPorts() {{ setCode("BGBNA"); setCountry("BGR"); setName("Buna 101"); setLatitude(43.204723); setLongitude(27.933332); }});
        put((short) 15, new ElbPorts() {{ setCode("BGBN2"); setCountry("BGR"); setName("Buna 102"); setLatitude(43.2083); setLongitude(27.9402); }});
        put((short) 16, new ElbPorts() {{ setCode("BGBN3"); setCountry("BGR"); setName("Buna 103"); setLatitude(43.20972); setLongitude(27.949167); }});
        put((short) 17, new ElbPorts() {{ setCode("BGBOJ"); setCountry("BGR"); setName("Burgas"); setLatitude(42.483334); setLongitude(27.45); }});
        put((short) 18, new ElbPorts() {{ setCode("BGBYL"); setCountry("BGR"); setName("Byala"); setLatitude(42.85366); setLongitude(27.897017); }});
        put((short) 19, new ElbPorts() {{ setCode("BGCHA"); setCountry("BGR"); setName("Chayka Byala"); setLatitude(42.854008); setLongitude(27.896904); }});
        put((short) 20, new ElbPorts() {{ setCode("BGCHN"); setCountry("BGR"); setName("Cherno more"); setLatitude(43.209442); setLongitude(27.874166); }});
        put((short) 21, new ElbPorts() {{ setCode("BGCHM"); setCountry("BGR"); setName("Chernomorets"); setLatitude(42.449703); setLongitude(27.640503); }});
        put((short) 22, new ElbPorts() {{ setCode("BGCHO"); setCountry("BGR"); setName("Chukalya"); setLatitude(42.45639); setLongitude(27.53861); }});
        put((short) 23, new ElbPorts() {{ setCode("BGDAL"); setCountry("BGR"); setName("Dalboka"); setLatitude(43.403545); setLongitude(28.388834); }});
        put((short) 24, new ElbPorts() {{ setCode("BGDKO"); setCountry("BGR"); setName("Durankulak"); setLatitude(43.698593); setLongitude(28.570835); }});
        put((short) 25, new ElbPorts() {{ setCode("BGEZE"); setCountry("BGR"); setName("Ezerets"); setLatitude(43.592213); setLongitude(28.570854); }});
        put((short) 26, new ElbPorts() {{ setCode("BGEZR"); setCountry("BGR"); setName("Ezerovo"); setLatitude(43.196667); setLongitude(27.776943); }});
        put((short) 27, new ElbPorts() {{ setCode("BGFRE"); setCountry("BGR"); setName("Frenkliman"); setLatitude(43.313057); setLongitude(28.057629); }});
        put((short) 28, new ElbPorts() {{ setCode("BGIRA"); setCountry("BGR"); setName("Irakli"); setLatitude(42.751026); setLongitude(27.889341); }});
        put((short) 29, new ElbPorts() {{ setCode("BGKAM"); setCountry("BGR"); setName("Kamchia"); setLatitude(43.023415); setLongitude(27.888538); }});
        put((short) 30, new ElbPorts() {{ setCode("BGKRT"); setCountry("BGR"); setName("Karantinata"); setLatitude(43.17366); setLongitude(27.916668); }});
        put((short) 31, new ElbPorts() {{ setCode("BGKVN"); setCountry("BGR"); setName("Kavarna"); setLatitude(43.413483); setLongitude(28.353296); }});
        put((short) 32, new ElbPorts() {{ setCode("BGKBA"); setCountry("BGR"); setName("Kavarna Buna 1"); setLatitude(43.41111); setLongitude(28.356667); }});
        put((short) 33, new ElbPorts() {{ setCode("BGKB3"); setCountry("BGR"); setName("Kavarna Buna 3"); setLatitude(43.41139); setLongitude(28.349722); }});
        put((short) 34, new ElbPorts() {{ setCode("BGKZO"); setCountry("BGR"); setName("Kazashko"); setLatitude(43.19611); setLongitude(27.818333); }});
        put((short) 35, new ElbPorts() {{ setCode("BGKIT"); setCountry("BGR"); setName("Kiten"); setLatitude(42.23389); setLongitude(27.78139); }});
        put((short) 36, new ElbPorts() {{ setCode("BGKOK"); setCountry("BGR"); setName("Kokodiva"); setLatitude(43.24866); setLongitude(28.02886); }});
        put((short) 37, new ElbPorts() {{ setCode("BGKON"); setCountry("BGR"); setName("Konstantinovo"); setLatitude(43.1869); setLongitude(27.7671); }});
        put((short) 38, new ElbPorts() {{ setCode("BGKRA"); setCountry("BGR"); setName("Kranevo"); setLatitude(43.32722); setLongitude(28.06889); }});
        put((short) 39, new ElbPorts() {{ setCode("BGKRP"); setCountry("BGR"); setName("Krapets"); setLatitude(43.62278); setLongitude(28.572779); }});
        put((short) 40, new ElbPorts() {{ setCode("BGKRE"); setCountry("BGR"); setName("Kraymorie"); setLatitude(42.444553); setLongitude(27.495659); }});
        put((short) 41, new ElbPorts() {{ setCode("BGLES"); setCountry("BGR"); setName("Lesport"); setLatitude(43.201942); setLongitude(27.79889); }});
        put((short) 42, new ElbPorts() {{ setCode("BGLZC"); setCountry("BGR"); setName("Lozenets"); setLatitude(42.209167); setLongitude(27.813334); }});
        put((short) 43, new ElbPorts() {{ setCode("BGMCH"); setCountry("BGR"); setName("Malka Chayka"); setLatitude(43.195824); setLongitude(27.874065); }});
        put((short) 44, new ElbPorts() {{ setCode("BGMEC"); setCountry("BGR"); setName("Mecheto"); setLatitude(43.19755); setLongitude(27.681196); }});
        put((short) 45, new ElbPorts() {{ setCode("BGNES"); setCountry("BGR"); setName("Nessebar"); setLatitude(42.65704); setLongitude(27.729404); }});
        put((short) 46, new ElbPorts() {{ setCode("BGOBZ"); setCountry("BGR"); setName("Obzor"); setLatitude(42.808887); setLongitude(27.888332); }});
        put((short) 47, new ElbPorts() {{ setCode("BGOTM"); setCountry("BGR"); setName("Otmanli"); setLatitude(42.43722); setLongitude(27.527222); }});
        put((short) 48, new ElbPorts() {{ setCode("BGPAN"); setCountry("BGR"); setName("Panorama"); setLatitude(43.30263); setLongitude(28.053532); }});
        put((short) 49, new ElbPorts() {{ setCode("BGPOM"); setCountry("BGR"); setName("Pomorie"); setLatitude(42.55145); setLongitude(27.64055); }});
        put((short) 50, new ElbPorts() {{ setCode("BGPRI"); setCountry("BGR"); setName("Primorsko"); setLatitude(42.26361); setLongitude(27.758333); }});
        put((short) 51, new ElbPorts() {{ setCode("BGRAV"); setCountry("BGR"); setName("Ravda"); setLatitude(42.636776); setLongitude(27.676722); }});
        put((short) 52, new ElbPorts() {{ setCode("BGREZ"); setCountry("BGR"); setName("Rezovo"); setLatitude(41.983055); setLongitude(28.028055); }});
        put((short) 53, new ElbPorts() {{ setCode("BGRDA"); setCountry("BGR"); setName("Rodopa 1"); setLatitude(43.196945); setLongitude(27.894722); }});
        put((short) 54, new ElbPorts() {{ setCode("BGRD2"); setCountry("BGR"); setName("Rodopa 2"); setLatitude(43.19611); setLongitude(27.894167); }});
        put((short) 55, new ElbPorts() {{ setCode("BGRUS"); setCountry("BGR"); setName("Rusalka"); setLatitude(43.41507); setLongitude(28.512896); }});
        put((short) 56, new ElbPorts() {{ setCode("BGSAR"); setCountry("BGR"); setName("Sarafovo"); setLatitude(42.55417); setLongitude(27.515278); }});
        put((short) 57, new ElbPorts() {{ setCode("BGSBA"); setCountry("BGR"); setName("SBA"); setLatitude(43.403637); setLongitude(28.233397); }});
        put((short) 58, new ElbPorts() {{ setCode("BGSEK"); setCountry("BGR"); setName("Sever Eksport"); setLatitude(43.185497); setLongitude(27.903372); }});
        put((short) 59, new ElbPorts() {{ setCode("BGSHA"); setCountry("BGR"); setName("Shabla"); setLatitude(43.541298); setLongitude(28.607492); }});
        put((short) 60, new ElbPorts() {{ setCode("BGSHK"); setCountry("BGR"); setName("Shkorpilovtsi"); setLatitude(42.9582); setLongitude(27.898489); }});
        put((short) 61, new ElbPorts() {{ setCode("BGSIN"); setCountry("BGR"); setName("Sinemorets"); setLatitude(42.062572); setLongitude(27.98207); }});
        put((short) 62, new ElbPorts() {{ setCode("BGSLK"); setCountry("BGR"); setName("Slivkata"); setLatitude(43.186043); setLongitude(27.798834); }});
        put((short) 63, new ElbPorts() {{ setCode("BGSOZ"); setCountry("BGR"); setName("Sozopol"); setLatitude(42.422466); setLongitude(27.691425); }});
        put((short) 64, new ElbPorts() {{ setCode("BGSTR"); setCountry("BGR"); setName("Strashimirovo"); setLatitude(43.1965); setLongitude(27.7401); }});
        put((short) 65, new ElbPorts() {{ setCode("BGSVV"); setCountry("BGR"); setName("Sveti Vlas"); setLatitude(42.706112); setLongitude(27.76639); }});
        put((short) 66, new ElbPorts() {{ setCode("BGTRK"); setCountry("BGR"); setName("Trakata"); setLatitude(43.218006); setLongitude(27.980469); }});
        put((short) 67, new ElbPorts() {{ setCode("BGTRV"); setCountry("BGR"); setName("Tsarevo"); setLatitude(42.170704); setLongitude(27.857597); }});
        put((short) 68, new ElbPorts() {{ setCode("BGTUZ"); setCountry("BGR"); setName("Tuzlata"); setLatitude(43.39979); setLongitude(28.215683); }});
        put((short) 69, new ElbPorts() {{ setCode("BGTYL"); setCountry("BGR"); setName("Tyulenovo"); setLatitude(43.494537); setLongitude(28.5848); }});
        put((short) 70, new ElbPorts() {{ setCode("BGVAR"); setCountry("BGR"); setName("Varna"); setLatitude(43.183613); setLongitude(27.900557); }});
        put((short) 71, new ElbPorts() {{ setCode("BGVRV"); setCountry("BGR"); setName("Varvara"); setLatitude(42.118996); setLongitude(27.908222); }});
        put((short) 72, new ElbPorts() {{ setCode("BGVES"); setCountry("BGR"); setName("Veslets"); setLatitude(43.191666); setLongitude(27.862223); }});
        put((short) 73, new ElbPorts() {{ setCode("BGYVA"); setCountry("BGR"); setName("Yahtklub Varna"); setLatitude(43.19528); setLongitude(27.896389); }});
        put((short) 74, new ElbPorts() {{ setCode("BGZEL"); setCountry("BGR"); setName("Zelenka"); setLatitude(43.38223); setLongitude(28.44054); }});
        put((short) 75, new ElbPorts() {{ setCode("BGZVE"); setCountry("BGR"); setName("Zvezditsa"); setLatitude(43.184); setLongitude(27.804); }});
    }};
    private String name = "N/A";

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    private String code = "N/A";
    public String getCode() {
        return code;
    }
    public  void setCode(String str) {
        this.code = str.trim();
    }
    private int type = -1;

    public int getType() {
        return type;
    }

    public void setType(int value) {
        this.type = value;
    }

    private short portId = 0;

    public short getPortId() {
        return portId;
    }

    public void setPortId(short portId) {
        this.portId = portId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double value) {
        this.latitude = value;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double value) {
        this.longitude = value;
    }

    private double latitude = 0.00;
    private double longitude = 0.00;
    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        country = value;
    }

    private String country = "NA";




}
//    setCode("BGZVE"); setCountry(BGR); setName("Zvezditsa"); setLatitude(43.184); setLongitude(27.804); "}");