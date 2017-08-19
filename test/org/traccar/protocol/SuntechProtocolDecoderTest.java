package org.traccar.protocol;

import org.junit.Test;
import org.traccar.ProtocolTest;

public class SuntechProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecodeTemperature() throws Exception {

        SuntechProtocolDecoder decoder = new SuntechProtocolDecoder(new SuntechProtocol());

        decoder.setHbm(true);
        decoder.setIncludeAdc(true);
        decoder.setIncludeTemp(true);

        /*verifyPosition(decoder, text(
                "ST300STT;205170303;12;561;20170816;09:10:34;173f53;+19.082370;-098.214287;006.776;000.00;0;0;52982186;12.75;100000;2;6328;155747;4.2;1;0.00;0;0.00;0.00;00000000000000;0;28F2B7600600005D:+5.2;:;:"));*/

        verifyPosition(decoder, text(
                "ST300STT;205173382;07;564;20160322;23:23:18;232e19;+19.288278;-099.128750;000.122;000.00;9;1;478391;11.53;000100;1;9498;079324;4.3;1;0.00;0.00;0.00;00000000000000;0;2898E16006000058:-20.8;2861626006000039:+2.5;:"));

        verifyPosition(decoder, text(
                "ST300EVT;205173382;07;564;20160322;23:23:18;232e19;+19.288278;-099.128750;000.122;000.00;9;1;478391;11.53;000100;2;1;9498;079324;4.3;1;0.00;0.00;0.00;00000000000000;0;2898E16006000058:-20.8;2861626006000039:+2.5;:"));

    }

    @Test
    public void testDecode() throws Exception {

        SuntechProtocolDecoder decoder = new SuntechProtocolDecoder(new SuntechProtocol());

        verifyPosition(decoder, text(
                "ST300STT;205170303;12;561;20170816;09:10:34;173f53;+19.082370;-098.214287;006.776;000.00;0;0;52982186;12.75;100000;2;6328;155747;4.2;1;0.00;0;0.00;0.00;00000000000000;0;28F2B7600600005D:+5.2;:;:"));

        verifyPosition(decoder, text(
                "ST910;Location;205576803;500;20170319;12:18:17;-22.846014;-046.322176;000.000;000.00;0;3.8;0;1;9159"));

        verifyPosition(decoder, text(
                "ST910;Emergency;205576803;500;20170319;12:15:22;-22.846014;-046.322176;000.000;000.00;0;2"));

        verifyPosition(decoder, text(
                "ST910;Location;205576803;500;20170312;12:56:52;-22.846014;-046.322176;000.000;000.00;0;3.8;0;0;0019"));

        verifyPosition(decoder, text(
                "ST300STT;100850000;01;010;20081017;07:41:56;00100;+37.478519;+126.886819;000.012;000.00;9;1;0;15.30;001100;1;0072;0;4.5;1;1750;012497F1160000;1;004f001454;450;00;-320;20;255;1"));

        verifyPosition(decoder, text(
                "ST300STT;205589913;05;527;20170304;02:21:33;be139;-25.398868;-049.325636;000.476;000.00;6;1;427;12.57;100000010;1;0172;017.159;0;002.327;12;4.0"));

        verifyPosition(decoder, text(
                "SA200STT;638947;803;20170117;07:40:44;5d309;-01.287213;-047.917462;000.035;000.00;10;1;2036194;12.57;000000;1;0376;010360;4.2;1"));

        verifyPosition(decoder, text(
                "ST300ALT;205174410;14;712;20110101;00:00:07;00000;+20.593923;-100.336716;000.000;000.00;0;0;0;16.57;000000;81;000000;4.0;0;0.00;0000;0000;0;0"));

        verifyNull(decoder, text(
                "SA200ALV;317652"));
        
        verifyPosition(decoder, text(
                "ST910;Alert;123456;410;20141018;18:30:12;+37.478774;+126.889690;000.000;000.00;0;4.0;1;6002"),
                position("2014-10-18 18:30:12.000", false, 37.47877, 126.88969));

        verifyPosition(decoder, text(
                "ST910;Alert;123456;410;20141018;18:30:12;+37.478774;+126.889690;000.000;000.00;0;4.0;1;6002;02;0;0310000100;450;01;-282;70;255;3;0"));

        verifyPosition(decoder, text(
                "SA200STT;317652;042;20120718;15:37:12;16d41;-15.618755;-056.083241;000.024;000.00;8;1;41548;12.17;100000;2;1979"),
                position("2012-07-18 15:37:12.000", true, -15.61876, -56.08324));

        verifyPosition(decoder, text(
                "SA200STT;317652;042;20120721;19:04:30;16d41;-15.618743;-056.083221;000.001;000.00;12;1;41557;12.21;000000;1;3125"));

        verifyPosition(decoder, text(
                "SA200STT;317652;042;20120722;00:24:23;4f310;-15.618767;-056.083214;000.011;000.00;11;1;41557;12.21;000000;1;3205"));

        verifyPosition(decoder, text(
                "SA200STT;315198;042;20120808;20:37:34;3fac25;-15.618731;-056.083216;000.007;000.00;12;1;48;0.00;000000;1;0127"));

        verifyPosition(decoder, text(
                "SA200STT;315198;042;20120809;13:43:34;4f310;-15.618709;-056.083223;000.025;000.00;8;1;49;12.10;100000;2;0231"));

        verifyPosition(decoder, text(
                "SA200EMG;317652;042;20120718;15:35:41;16d41;-15.618740;-056.083252;000.034;000.00;8;1;41548;12.17;110000;1"));

        verifyPosition(decoder, text(
                "SA200ALT;317652;042;20120829;14:25:58;16d41;-15.618770;-056.083242;000.029;000.00;0;0;2404240;0.00;000000;10"));

        verifyPosition(decoder, text(
                "SA200STT;430070;133;20130615;22:22:32;151347;+02.860514;-060.653351;000.003;000.00;12;1;0;12.39;000000;1;0208"));

        verifyPosition(decoder, text(
                "ST910;Location;344506;017;20130727;14:10:00;-25.398714;-049.296818;000.187;000.00;1;4.32;1;1;0001"));

        verifyPosition(decoder, text(
                "ST300STT;205027329;03;374;20150108;17:54:42;177b38;-23.566052;-046.477588;000.000;000.00;0;0;0;12.11;000000;1;0312"));
        
        verifyPosition(decoder, text(
                "ST910;Emergency;205283272;500;20150716;19:12:01;-23.659019;-046.695403;000.602;000.00;0;4.2;1;1;02;10820;2fdb090736;724;05;0;2311;255;0;100"));

        decoder.setProtocolType(1);

        verifyPosition(decoder, text(
                "ST910;Alert;485195;20170409;22:37:41;3be0133057;+24.882410;-107.509152;000.070;000.00;1;286734;72;02;295;05;-415;4912;255;10;10"));

        verifyPosition(decoder, text(
                "ST910;Location;485195;528;20170410;01:18:57;f1dd134840;+24.787139;-107.434679;000.020;000.00;1;286734;100;1;0;0188;02;295;05;-339;4936;255;4;74"));

        verifyPosition(decoder, text(
                "ST910;Location;560266;500;20161207;21:33:11;af910be101;-25.504234;-049.278003;000.080;000.00;1;10054889;70;1;1;1311;02;724;06;-317;3041;2;10;92"));

        verifyPosition(decoder, text(
                "ST910;Emergency;238569;528;20170403;00:02:09;7574160020;+19.661292;-099.144473;000.176;000.00;1;228638;1"));

    }

}
