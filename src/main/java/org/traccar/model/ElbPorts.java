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

    public static final HashMap<Long, ElbPorts> elbPortsHashMap = new HashMap<Long, ElbPorts>() {{
        put((long) 1, new ElbPorts() {{ setPortId(1); setCode("BGADB"); setCountry("BGR"); setName("Ada Bahcha"); setLatitude(43.12859); setLongitude(27.932804); }});
        put((long) 2, new ElbPorts() {{ setPortId(2);  setCode("BGAHE"); setCountry("BGR"); setName("Aheloy"); setLatitude(42.636665); setLongitude(27.652222); }});
        put((long) 3, new ElbPorts() {{ setPortId(3);  setCode("BGAKH"); setCountry("BGR"); setName("Akhtopol"); setLatitude(42.099495); setLongitude(27.94431); }});
        put((long) 4, new ElbPorts() {{ setPortId(4);  setCode("BGALB"); setCountry("BGR"); setName("Albena"); setLatitude(43.378258); setLongitude(28.098877); }});
        put((long) 5, new ElbPorts() {{ setPortId(5);  setCode("BGAMO"); setCountry("BGR"); setName("Amonal"); setLatitude(43.185555); setLongitude(27.840555); }});
        put((long) 6, new ElbPorts() {{ setPortId(6);  setCode("BGANT"); setCountry("BGR"); setName("Anton Ivanov"); setLatitude(43.19612); setLongitude(27.8953); }});
        put((long) 7, new ElbPorts() {{ setPortId(7);  setCode("BGARK"); setCountry("BGR"); setName("Arkutino"); setLatitude(42.336388); setLongitude(27.732779); }});
        put((long) 8, new ElbPorts() {{ setPortId(8);  setCode("BGASP"); setCountry("BGR"); setName("Asparuhovo Keya"); setLatitude(43.183887); setLongitude(27.900278); }});
        put((long) 9, new ElbPorts() {{ setPortId(9);  setCode("BGBAL"); setCountry("BGR"); setName("Balchik"); setLatitude(43.404358); setLongitude(28.166162); }});
        put((long) 10, new ElbPorts() {{ setPortId(10); setCode("BGBL1"); setCountry("BGR"); setName("Beloslav 1"); setLatitude(43.19505); setLongitude(27.699759); }});
        put((long) 11, new ElbPorts() {{ setPortId(11); setCode("BGBL2"); setCountry("BGR"); setName("Beloslav 2 (Feribotna)"); setLatitude(43.18682); setLongitude(27.679943); }});
        put((long) 12, new ElbPorts() {{ setPortId(12); setCode("BGBOL"); setCountry("BGR"); setName("Bolata"); setLatitude(43.3819); setLongitude(28.4708); }});
        put((long) 13, new ElbPorts() {{ setPortId(13); setCode("BGBHZ"); setCountry("BGR"); setName("Buhta zapad"); setLatitude(43.197422); setLongitude(27.894777); }});
        put((long) 14, new ElbPorts() {{ setPortId(14); setCode("BGBNA"); setCountry("BGR"); setName("Buna 101"); setLatitude(43.204723); setLongitude(27.933332); }});
        put((long) 15, new ElbPorts() {{ setPortId(15); setCode("BGBN2"); setCountry("BGR"); setName("Buna 102"); setLatitude(43.2083); setLongitude(27.9402); }});
        put((long) 16, new ElbPorts() {{ setPortId(16); setCode("BGBN3"); setCountry("BGR"); setName("Buna 103"); setLatitude(43.20972); setLongitude(27.949167); }});
        put((long) 17, new ElbPorts() {{ setPortId(17); setCode("BGBOJ"); setCountry("BGR"); setName("Burgas"); setLatitude(42.483334); setLongitude(27.45); }});
        put((long) 18, new ElbPorts() {{ setPortId(18); setCode("BGBYL"); setCountry("BGR"); setName("Byala"); setLatitude(42.85366); setLongitude(27.897017); }});
        put((long) 19, new ElbPorts() {{ setPortId(19); setCode("BGCHA"); setCountry("BGR"); setName("Chayka Byala"); setLatitude(42.854008); setLongitude(27.896904); }});
        put((long) 20, new ElbPorts() {{ setPortId(20); setCode("BGCHN"); setCountry("BGR"); setName("Cherno more"); setLatitude(43.209442); setLongitude(27.874166); }});
        put((long) 21, new ElbPorts() {{ setPortId(21); setCode("BGCHM"); setCountry("BGR"); setName("Chernomorets"); setLatitude(42.449703); setLongitude(27.640503); }});
        put((long) 22, new ElbPorts() {{ setPortId(22); setCode("BGCHO"); setCountry("BGR"); setName("Chukalya"); setLatitude(42.45639); setLongitude(27.53861); }});
        put((long) 23, new ElbPorts() {{ setPortId(23); setCode("BGDAL"); setCountry("BGR"); setName("Dalboka"); setLatitude(43.403545); setLongitude(28.388834); }});
        put((long) 24, new ElbPorts() {{ setPortId(24); setCode("BGDKO"); setCountry("BGR"); setName("Durankulak"); setLatitude(43.698593); setLongitude(28.570835); }});
        put((long) 25, new ElbPorts() {{ setPortId(25); setCode("BGEZE"); setCountry("BGR"); setName("Ezerets"); setLatitude(43.592213); setLongitude(28.570854); }});
        put((long) 26, new ElbPorts() {{ setPortId(26); setCode("BGEZR"); setCountry("BGR"); setName("Ezerovo"); setLatitude(43.196667); setLongitude(27.776943); }});
        put((long) 27, new ElbPorts() {{ setPortId(27); setCode("BGFRE"); setCountry("BGR"); setName("Frenkliman"); setLatitude(43.313057); setLongitude(28.057629); }});
        put((long) 28, new ElbPorts() {{ setPortId(28); setCode("BGIRA"); setCountry("BGR"); setName("Irakli"); setLatitude(42.751026); setLongitude(27.889341); }});
        put((long) 29, new ElbPorts() {{ setPortId(29); setCode("BGKAM"); setCountry("BGR"); setName("Kamchia"); setLatitude(43.023415); setLongitude(27.888538); }});
        put((long) 30, new ElbPorts() {{ setPortId(30); setCode("BGKRT"); setCountry("BGR"); setName("Karantinata"); setLatitude(43.17366); setLongitude(27.916668); }});
        put((long) 31, new ElbPorts() {{ setPortId(31); setCode("BGKVN"); setCountry("BGR"); setName("Kavarna"); setLatitude(43.413483); setLongitude(28.353296); }});
        put((long) 32, new ElbPorts() {{ setPortId(32); setCode("BGKBA"); setCountry("BGR"); setName("Kavarna Buna 1"); setLatitude(43.41111); setLongitude(28.356667); }});
        put((long) 33, new ElbPorts() {{ setPortId(33); setCode("BGKB3"); setCountry("BGR"); setName("Kavarna Buna 3"); setLatitude(43.41139); setLongitude(28.349722); }});
        put((long) 34, new ElbPorts() {{ setPortId(34); setCode("BGKZO"); setCountry("BGR"); setName("Kazashko"); setLatitude(43.19611); setLongitude(27.818333); }});
        put((long) 35, new ElbPorts() {{ setPortId(35); setCode("BGKIT"); setCountry("BGR"); setName("Kiten"); setLatitude(42.23389); setLongitude(27.78139); }});
        put((long) 36, new ElbPorts() {{ setPortId(36); setCode("BGKOK"); setCountry("BGR"); setName("Kokodiva"); setLatitude(43.24866); setLongitude(28.02886); }});
        put((long) 37, new ElbPorts() {{ setPortId(37); setCode("BGKON"); setCountry("BGR"); setName("Konstantinovo"); setLatitude(43.1869); setLongitude(27.7671); }});
        put((long) 38, new ElbPorts() {{ setPortId(38); setCode("BGKRA"); setCountry("BGR"); setName("Kranevo"); setLatitude(43.32722); setLongitude(28.06889); }});
        put((long) 39, new ElbPorts() {{ setPortId(39); setCode("BGKRP"); setCountry("BGR"); setName("Krapets"); setLatitude(43.62278); setLongitude(28.572779); }});
        put((long) 40, new ElbPorts() {{ setPortId(40); setCode("BGKRE"); setCountry("BGR"); setName("Kraymorie"); setLatitude(42.444553); setLongitude(27.495659); }});
        put((long) 41, new ElbPorts() {{ setPortId(41); setCode("BGLES"); setCountry("BGR"); setName("Lesport"); setLatitude(43.201942); setLongitude(27.79889); }});
        put((long) 42, new ElbPorts() {{ setPortId(42); setCode("BGLZC"); setCountry("BGR"); setName("Lozenets"); setLatitude(42.209167); setLongitude(27.813334); }});
        put((long) 43, new ElbPorts() {{ setPortId(43); setCode("BGMCH"); setCountry("BGR"); setName("Malka Chayka"); setLatitude(43.195824); setLongitude(27.874065); }});
        put((long) 44, new ElbPorts() {{ setPortId(44); setCode("BGMEC"); setCountry("BGR"); setName("Mecheto"); setLatitude(43.19755); setLongitude(27.681196); }});
        put((long) 45, new ElbPorts() {{ setPortId(45); setCode("BGNES"); setCountry("BGR"); setName("Nessebar"); setLatitude(42.65704); setLongitude(27.729404); }});
        put((long) 46, new ElbPorts() {{ setPortId(46); setCode("BGOBZ"); setCountry("BGR"); setName("Obzor"); setLatitude(42.808887); setLongitude(27.888332); }});
        put((long) 47, new ElbPorts() {{ setPortId(47); setCode("BGOTM"); setCountry("BGR"); setName("Otmanli"); setLatitude(42.43722); setLongitude(27.527222); }});
        put((long) 48, new ElbPorts() {{ setPortId(48); setCode("BGPAN"); setCountry("BGR"); setName("Panorama"); setLatitude(43.30263); setLongitude(28.053532); }});
        put((long) 49, new ElbPorts() {{ setPortId(49); setCode("BGPOM"); setCountry("BGR"); setName("Pomorie"); setLatitude(42.55145); setLongitude(27.64055); }});
        put((long) 50, new ElbPorts() {{ setPortId(50); setCode("BGPRI"); setCountry("BGR"); setName("Primorsko"); setLatitude(42.26361); setLongitude(27.758333); }});
        put((long) 51, new ElbPorts() {{ setPortId(51); setCode("BGRAV"); setCountry("BGR"); setName("Ravda"); setLatitude(42.636776); setLongitude(27.676722); }});
        put((long) 52, new ElbPorts() {{ setPortId(52); setCode("BGREZ"); setCountry("BGR"); setName("Rezovo"); setLatitude(41.983055); setLongitude(28.028055); }});
        put((long) 53, new ElbPorts() {{ setPortId(53); setCode("BGRDA"); setCountry("BGR"); setName("Rodopa 1"); setLatitude(43.196945); setLongitude(27.894722); }});
        put((long) 54, new ElbPorts() {{ setPortId(54); setCode("BGRD2"); setCountry("BGR"); setName("Rodopa 2"); setLatitude(43.19611); setLongitude(27.894167); }});
        put((long) 55, new ElbPorts() {{ setPortId(55); setCode("BGRUS"); setCountry("BGR"); setName("Rusalka"); setLatitude(43.41507); setLongitude(28.512896); }});
        put((long) 56, new ElbPorts() {{ setPortId(56); setCode("BGSAR"); setCountry("BGR"); setName("Sarafovo"); setLatitude(42.55417); setLongitude(27.515278); }});
        put((long) 57, new ElbPorts() {{ setPortId(57); setCode("BGSBA"); setCountry("BGR"); setName("SBA"); setLatitude(43.403637); setLongitude(28.233397); }});
        put((long) 58, new ElbPorts() {{ setPortId(58); setCode("BGSEK"); setCountry("BGR"); setName("Sever Eksport"); setLatitude(43.185497); setLongitude(27.903372); }});
        put((long) 59, new ElbPorts() {{ setPortId(59); setCode("BGSHA"); setCountry("BGR"); setName("Shabla"); setLatitude(43.541298); setLongitude(28.607492); }});
        put((long) 60, new ElbPorts() {{ setPortId(60); setCode("BGSHK"); setCountry("BGR"); setName("Shkorpilovtsi"); setLatitude(42.9582); setLongitude(27.898489); }});
        put((long) 61, new ElbPorts() {{ setPortId(61); setCode("BGSIN"); setCountry("BGR"); setName("Sinemorets"); setLatitude(42.062572); setLongitude(27.98207); }});
        put((long) 62, new ElbPorts() {{ setPortId(62); setCode("BGSLK"); setCountry("BGR"); setName("Slivkata"); setLatitude(43.186043); setLongitude(27.798834); }});
        put((long) 63, new ElbPorts() {{ setPortId(63); setCode("BGSOZ"); setCountry("BGR"); setName("Sozopol"); setLatitude(42.422466); setLongitude(27.691425); }});
        put((long) 64, new ElbPorts() {{ setPortId(64); setCode("BGSTR"); setCountry("BGR"); setName("Strashimirovo"); setLatitude(43.1965); setLongitude(27.7401); }});
        put((long) 65, new ElbPorts() {{ setPortId(65); setCode("BGSVV"); setCountry("BGR"); setName("Sveti Vlas"); setLatitude(42.706112); setLongitude(27.76639); }});
        put((long) 66, new ElbPorts() {{ setPortId(66); setCode("BGTRK"); setCountry("BGR"); setName("Trakata"); setLatitude(43.218006); setLongitude(27.980469); }});
        put((long) 67, new ElbPorts() {{ setPortId(67); setCode("BGTRV"); setCountry("BGR"); setName("Tsarevo"); setLatitude(42.170704); setLongitude(27.857597); }});
        put((long) 68, new ElbPorts() {{ setPortId(68); setCode("BGTUZ"); setCountry("BGR"); setName("Tuzlata"); setLatitude(43.39979); setLongitude(28.215683); }});
        put((long) 69, new ElbPorts() {{ setPortId(69); setCode("BGTYL"); setCountry("BGR"); setName("Tyulenovo"); setLatitude(43.494537); setLongitude(28.5848); }});
        put((long) 70, new ElbPorts() {{ setPortId(70); setCode("BGVAR"); setCountry("BGR"); setName("Varna"); setLatitude(43.183613); setLongitude(27.900557); }});
        put((long) 71, new ElbPorts() {{ setPortId(71); setCode("BGVRV"); setCountry("BGR"); setName("Varvara"); setLatitude(42.118996); setLongitude(27.908222); }});
        put((long) 72, new ElbPorts() {{ setPortId(72); setCode("BGVES"); setCountry("BGR"); setName("Veslets"); setLatitude(43.191666); setLongitude(27.862223); }});
        put((long) 73, new ElbPorts() {{ setPortId(73); setCode("BGYVA"); setCountry("BGR"); setName("Yahtklub Varna"); setLatitude(43.19528); setLongitude(27.896389); }});
        put((long) 74, new ElbPorts() {{ setPortId(74); setCode("BGZEL"); setCountry("BGR"); setName("Zelenka"); setLatitude(43.38223); setLongitude(28.44054); }});
        put((long) 75, new ElbPorts() {{ setPortId(75); setCode("BGZVE"); setCountry("BGR"); setName("Zvezditsa"); setLatitude(43.184); setLongitude(27.804); }});
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

    private long portId = 0;

    public long getPortId() {
        return portId;
    }

    public void setPortId(long portId) {
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