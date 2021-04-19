package org.traccar.protocol;

import org.junit.Test;
import org.traccar.ProtocolTest;
import org.traccar.model.Position;

public class ItsProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        var decoder = new ItsProtocolDecoder(null);

        verifyNull(decoder, text(
                "$,LGN,MARK,000000000,358980100077446,V0.0.1,AIS140,19.804487,N,75.225876,E,*"));

        verifyNull(decoder, text(
                "$LGN,,869867037009679,3.2AIH,9.99546000,N,76.35886167,E"));

        verifyAttribute(decoder, text(
                "$,C,CTPL,4.0.0,NR,01,L,869247045166383,NA00000000,1,12032020,144453,30.452524,N,077.610351,E,1.4,34.8,14,384.19,1.8,0.8,IDEA P,1,1,14.2,4.17,0,C,22,404,82,0FB1,3B26,516B,0FB1,18,3B25,0FB1,15,5169,0FB1,14,3B27,0FB1,13,0000,00,8083,194.9,0B,*,IP=106.67.5.173"),
                Position.KEY_ODOMETER, 194900.0);

        verifyPosition(decoder, text(
                "$,EPB,SEM,868997031721531,NM,14072020112020,A,28.359959,N,076.927566,E,260.93,0.1,0.0,G,NA00000000,N.A0000000,*"));

        verifyPosition(decoder, text(
                "$,EPB,EMR,868997031721531,NM,14072020111520,A,28.359959,N,076.927553,E,261.41,0.1,0.0,G,NA00000000,N.A0000000,*"));

        verifyPosition(decoder, text(
                "$RLP,N.A,2.1.3,NR,01,L,861359032310722,N.A,14072020,180150,1,29.949376,N,078.060980,E,0.0,119.3,11,310.6,1.84,0.92,VODAFONE I,0,1,23.9,4.01,0,C,24,404,56,1b1,B6F1,0001,00,31024,1.5,00.0,0.63,-0.28,0.76,15,38,5A,*"));

        verifyPosition(decoder, text(
                "$RLP,N.A,2.1.3,NR,01,L,861359032295600,N.A,14072020,180212,1,29.949271,N,078.061241,E,15.0,25.30,9,288.2,1.44,1.10,VODAFONE I,0,1,24.0,3.99,0,C,15,404,56,1b1,B6F1,0001,00,18646,29.2,00.0,-0.46,0.33,-0.81,161,153,4B,*"));

        verifyPosition(decoder, text(
                "$RLP,N.A,2.1.1,NR,01,L,869867030158879,N.A,14042020,204826,1,28.593055,N,076.970035,E,0.6,313.4,16,169.0,0.99,0.64,VODAFONE I,0,1,24.8,4.19,0,C,18,404,11,415,A20A,a20b,415,0,d1db,413,0,43f1,413,0,43ef,413,0,0001,00,150689,1076.9,20,*"));

        verifyPosition(decoder, text(
                "$NRM,ROADRPA,3.5AIS,NR,01,L,869867036940288,,1,04012020,094901,23.18731933,N,77.45079633,E,0.0,0.00,13,545.6,1.10,0.60,Idea Cellular Ltd,0,1,13.2,4.1,0,C,31,404,78,62E1,5799,29,62E1,579B,23,62E1,52EB,22,62E1,52EA,21,62E1,2FF1,0100,00,0,0,001926,8252.226,-,-,-,-,5_5_5_5_0,171B56E1*"));

        verifyPosition(decoder, text(
                "$NMP,TRAXSMART,1.7.7,NR,1,L,862818043908237,0000,0,04012020,104208,28.6183987,N,77.3888474,E,001.0,000.00,00,000.0,0.0,0.0,Idea P,0,1,13.3,4.0,0,C,22,404,30,0089,2793,x,x,x,x,x,x,x,x,x,x,x,x,0002,00,000591,00.4,00.4,0,(0,0,0)*FC"));

        verifyPosition(decoder, text(
                "$NRM,,3.2AIH,NR,01,L,869867037003854,,1,02122019,074801,9.99553167,N,76.35911000,E,0.0,125.73,18,103.7,0.10,0.10,CellOne Kerala,0,1,13.1,4.2,0,C,19,404,72,08FE,0940,13,08FE,093E,11,08FE,7DA7,07,08FE,093F,07,08FE,7DA6,0100,00,0,0,003372,6897.214,,,,,5_5_3_5_0,B74BBD72*"));

        verifyPosition(decoder, text(
                "$,ID01,SAT,1.0.0,NR,1,L,868345034056903,DL3CAB1021,1,27052019,040234,28.359895,N,76.927879,E,0.0,285.6,12,254.9,1.4,0.7,IDEA,1,1,12.6,3.8,0,25,404,04,0138,0927,4ECD,0138,41,1C2B,0138,37,D77A,0138,34,D843,0138,33,0000,00,0.03,0.00,000091,A3,*"));

        verifyPosition(decoder, text(
                "$NMP,GPSBOX,1.6.8,NR,H,868997035844834,0000,1,220519,035419,28.6291409,N,77.3928299,E,015.2,157.40,07,197.86,2.1,1.0,airtel,1,1,13.3,4.1,0,O,31,404,10,0099,79b4,(-57,0099,334c,x,x,x,x,x,x,x,x,x),0012,00,000348,2,08.4,00.3,(0,0,0),CD*"));

        verifyPosition(decoder, text(
                "$RLP,N.A,2.0.2,NR,01,L,869867030181814,N.A,28022019,180155,1,28.688226,N,076.993570,E,0.0,80.26,17,201.0,0.89,0.60,VODAFONE I,0,1,25.0,4.20,0,C,14,404,11,415,F34A,51f7,415,13,840b,415,8,a3f7,0c2,5,ef77,415,5,0001,00,17888,47,*"));

        verifyAttribute(decoder, text(
                "$TEL123,Teltonika,03.18.16,NR,1,L,352093085223096,KA09X6945,1,24122018,055749,12.303873,N,76.690697,E,0.0,349.00,10,795.0,0.50,0.40,Airtel,0,1,14.6,4.1,0,,28,404,45,625A,116E,29,28DF,03A3,28,9A5C,0923,26,116F,625A,25,2A51,03A3,0010,00,000042,0.1,0.1,0,()*7B"),
                Position.PREFIX_ADC + 2, 0.1);

        verifyPosition(decoder, text(
                "$Header,iTriangle,1_37T02B0164MAIS,BR,6,L,861693034634154,KA01I2000,1,09112017,160702,12.976593,N,77.549782,E,25.1,344,15,911.0,1.04,0.68,Airtel,1,1,11.8,3.8,1,C,24,404,45,61b4,9ad9,31,9adb,61b4,35,ffff,0000,33,ffff,0000,31,ffff,0000,0001,00,000014,0.0,0.1,4,()*1E"));

        verifyAttribute(decoder, text(
                "$EPB,EMR,869867036066035,NM,03042019,192008,V,000.00000000,N,000.00000000,E,0000000000.0,0000.0,00.000,G,,0,404,22,ECFB,36EF*226F7BD1"),
                Position.KEY_ALARM, Position.ALARM_SOS);

        verifyPosition(decoder, text(
                "$,CP,ATL,1.4D3_AIS140_1.0,EA,10,H,868728037717441,,1,31032019,140054,28.533699,N,77.269020,E,0.0,188.00,14,76.0,1.3,0.0,Idea,0,1,12.7,3.9,1,O,22,404,11,69,979c,fc1,69,18,fbf,69,15,e36e,69,14,ba2f,3ff,13,0111,00,249404,"));

        verifyPosition(decoder, text(
                "$NRM,ROADRPA,1.8AIS,EA,01,L,869867036341099,,1,11032019,231048,19.25166667,N,73.04615167,E,0.0,230.21,17,12.8,0.80,0.80,airtel,0,1,13.5,4.2,0,O,22,404,90,0CC9,EBC8,19,0CC9,8992,16,0CC9,AB49,15,0CC9,AB44,14,0CC9,F03C,0000,00,002080,C9FBBF99"));

        verifyPosition(decoder, text(
                "$NRM,ROADRPA,1.7AIS,NR,01,L,869867036345389,,1,25022019,051716,25.12891000,N,75.85587833,E,7.6,350.00,14,284.8,1.00,1.00,AIRTEL,1,1,28.0,4.2,0,C,13,404,70,4E3B,3C84,11,4E3B,39B8,08,4E3B,3965,07,4E3B,48B5,07,4E3B,3C85,0000,00,000551,71978C6B"));

        verifyPosition(decoder, text(
                "$,03,XYZ123,0.0.1,TA,16,L,869867035297185,MH12AB1234,1,20,02,2019,10,59,13,023.482630,N,086.399673,E,000.1,015.19,21,212.3,01.12,00.58,NOSERV,0,1,00.0,4.6,1,C,11,404,75,082a,db3a,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,01,000013,01dbd51f,"));

        verifyPosition(decoder, text(
                "$NRM,ROADRPA,1.7AIS,NR,01,L,869867036345389,,1,25022019,051716,25.12891000,N,75.85587833,E,7.6,350.00,14,284.8,1.00,1.00,AIRTEL,1,1,28.0,4.2,0,C,13,404,70,4E3B,3C84,11,4E3B,39B8,08,4E3B,3965,07,4E3B,48B5,07,4E3B,3C85,0000,00,000551,71978C6B"));

        verifyPosition(decoder, text(
                "$,1,CHVTS,CHVTS1.0,DT,16,L,861359039868243,861359039868243,1,05022019,071225,19.965062,N,73.736088,E,0,050,03,0632,6.67,6.75,Idea Cel,1,1,23.96,4.0,0,W,28,404,004,4e2b,49e,4e2bea86727ab3d6704e2bea7714e2be9d72,0000,00,001133,232"));

        verifyPosition(decoder, text(
                "$,04,XYZ123,0.0.1,TA,16,L,861359034100626,MH12AB1234,1,12,11,2018,08,53,08,018.489645,N,073.855972,E,000.0,220.04,12,593.0,01.13,00.75,AIRTEL,1,1,00.0,4.1,1,C,18,404,90,0c23,781a,5169,0c23,-093,0000,0000,0000,0000,0000,0000,0000,0000,0000,1000,01,000006,f906c65c,"));

        verifyPosition(decoder, text(
                "$,A,MFR,7.0,NR,01,L,869026034780985,PJ09BU1234,1,12112018,121953,12.756974,N,077.800025,E,000.0,318.03,15,794.0,001.3,000.7,TAMIL NAD,0,1,13.08,04.13,0,O,22,404,80,0919,71C1,0919,7168,19,0919,71c3,17,0919,71c2,11,0919,7167,09,0011,00,000173,D8,000000.00,,,"));

        verifyPosition(decoder, text(
                "$,04,XYZ123,0.0.1,TA,16,L,861359034100626,MH12AB1234,1,14,10,2018,04,50,52,018.489624,N,073.855980,E,000.0,039.86,13,584.1,01.11,00.75,AIRTEL,1,1,00.0,4.1,1,C,15,404,90,0c23,781a,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,1000,01,000005,13b75499,"));

        verifyNull(decoder, text(
                "$,01,XYZ123,0.0.1,861359034137271,MH12AB1234,"));

        verifyNull(decoder, text(
                "$,02,XYZ123,0.0.1,861359034137271,100,30,00.0,00005,00600,1000,01,00.1,00.0,"));

        verifyPosition(decoder, text(
                "$,EPB,EMR,861359034100626,SP,00,00,0000,00,00,00,V,000.000000,N,000.000000,E,000.0,000.0,000.00,N,MH12AB1234,0000000000000,d34679e1,"));

        verifyPosition(decoder, text(
                "$,03,XYZ123,0.0.1,TA,16,L,861359034137271,MH12AB1234,0,00,00,0000,00,00,00,000.000000,N,000.000000,E,000.0,000.00,00,000.0,00.00,00.00,IDEAIN,1,1,00.0,4.0,1,O,16,404,22,2797,11b7,11b9,2797,-087,11b8,2797,-093,11b4,2797,-106,0000,0000,0000,1000,01,000032,8173e058,"));

        verifyPosition(decoder, text(
                "$,04,XYZ123,0.0.1,BR,06,L,861359034137271,MH12AB1234,0,00,00,0000,00,00,00,000.000000,N,000.000000,E,000.0,000.00,00,000.0,00.00,00.00,IDEAIN,1,1,00.0,3.8,1,O,17,404,22,2797,11b7,11b9,2797,-093,11b8,2797,-098,0000,0000,0000,0000,0000,0000,1000,00,000006,abd26284,"));

        verifyNull(decoder, text(
                "$Header,nliven,EMR,861693034634154,NM,09112017155133,A,12.976495,N,77.549713,E,906.0,0.0,23,G,KA01I2000,+919844098440*4B"));

        verifyNull(decoder, text(
                "$EPB,iTriangle1,EMR,864495034445822,SP,03082018110730,A,22.829292,N,75.935806,E,543.0,0.0,0,G,KA01G1234,+9164061023*13"));

        verifyPosition(decoder, text(
                "$Header,iTriangle,1_37T02B0164MAIS_2,NR,1,L,864495034490141,KA01I2000,1,19042018,102926,22.846401,N,75.948952,E,0.0,311,5,578.0,3.80,3.67,AirTel,0,1,12.5,4.3,1,C,14,404,93,0456,16db,29,ebd8,0458,28,3843,18ab,25,072e,18ab,22,35da,0458,0000,00,031181,0.0,0.0,0,()*34"));

        verifyPosition(decoder, text(
                "$Header,nliven,1_37T02B0164MAIS,BR,6,L,861693034634154,KA01I2000,1,09112017,160702,12.976593,N,77.549782,E,25.1,344,15,911.0,1.04,0.68,Airtel,1,1,11.8,3.8,1,C,24,404,45,61b4,9ad9,31,9adb,61b4,35,ffff,0000,33,ffff,0000,31,ffff,0000,0001,00,000014,0.0,0.1,4,()*1E"));

        verifyPosition(decoder, text(
                "$Header,iTriangle,1_37T02B0164MAIS_2,NR,1,L,864495034490141,KA01I2000,1,31032018,122247,22.845999,N,75.949005,E,0.0,44,16,545.0,1.19,0.65,AirTel,1,1,12.0,4.3,0,C,13,404,93,0456,16db,27,16dd,0456,22,3843,18ab,19,ebd8,0458,14,072c,18ab,0101,00,003735,0.0,0.0,0,()*48"));

        verifyNull(decoder, text(
                "$Header,nliven,KA01I2000,861693034634154,1_37T02B0164MAIS,AIS140,12.976545,N,77.549759,E*50"));

    }

}
