package org.traccar.protocol;

import java.nio.ByteOrder;

import org.junit.Test;
import org.traccar.ProtocolTest;

public class TramigoProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        TramigoProtocolDecoder decoder = new TramigoProtocolDecoder(new TramigoProtocol());

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000c426b000a6000101c557037598050d5c8a595472616d69676f3a204d6f76696e672c20302e3132206b6d2045206f66204c617275742054696e2049736c616d6963205072696d617279205363686f6f6c2c2054616970696e672c20506572616b2c204d592c20342e38333134392c203130302e37333038352c204e572077697468207370656564203130206b6d2f682c2030303a34393a30382041756720392020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000c526b000a6000101f17d03759805115c8a595472616d69676f3a204d6f76696e672c20302e3133206b6d205345206f66204c617275742054696e2049736c616d6963205072696d617279205363686f6f6c2c2054616970696e672c20506572616b2c204d592c20342e38333132322c203130302e37333037382c204e4520776974682073706565642039206b6d2f682c2030303a34383a35332041756720392020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000d426b0009f00010184f20375980593638a595472616d69676f3a204d6f76696e672c20302e3039206b6d204e57206f66204a616c616e2053696d70616e672042617475204d61726b65742c2054616970696e672c20506572616b2c204d592c20342e38333034332c203130302e37323230342c20532077697468207370656564203130206b6d2f682c2030313a32313a31322041756720392020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000d626b0007f0001013c0b037598051d648a595472616d69676f3a2053746f707065642c206174204a616c616e2053696d70616e672042617475204d61726b65742c2054616970696e672c20506572616b2c204d592c20342e38323937322c203130302e37323233322c2030313a32323a34342041756720392020454f46"));

        verifyNull(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "80003d1ac0001c00010100000367152b13bc1d5970696e6720454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000d316b000860001018f8703771bee11fdf2585472616d69676f3a205061726b65642c20302e3131206b6d2053206f6620492e452e532e2050756572746120426f6e6974612c204361726162616e6368656c2c204d61647269642c2045532c2034302e33373736362c202d332e37333833352c2030353a3131204170722031362020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "80009e08b00086000101bc1803778a59c58dea57546573742054323320547261636b65723a204d6f76696e672c20312e3639206b6d204e57206f66205574656b6f6e2c2045646f2c204e472c20362e34363137302c20352e36313938322c20452077697468207370656564203333206b6d2f682c2031363a3138205365702032372020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000853eb000b8000101fcff032f14665a89e2564176656e7369732053797353657276653a2049676e6974696f6e206f6e2064657465637465642c206d6f76696e672c20302e3135206b6d205357206f66204261626120416e696d61736861756e205374726565742d426f64652054686f6d61732053742e2c20537572756c6572652c204c61676f7320436974792c204e472c20362e34383736352c20332e33343735352c2031303a3031204d6172203131202020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000973eb000b90001012128032f14667794e2564176656e7369732053797353657276653a2049676e6974696f6e206f6e2064657465637465642c2073746f707065642c20302e3134206b6d205357206f66204261626120416e696d61736861756e205374726565742d426f64652054686f6d61732053742e2c20537572756c6572652c204c61676f7320436974792c204e472c20362e34383736372c20332e33343737332c2031303a3438204d6172203131202020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000b73eb000ad000101fdd2032f1466c9cbe2564176656e7369732053797353657276653a2049676e6974696f6e206f6e2064657465637465642c206d6f76696e672c20302e3131206b6d2045206f6620416c68616a69204d6173686120526f616420466f6f746272696467652c20537572756c6572652c204c61676f7320436974792c204e472c20362e35303031342c20332e33353434332c2031343a3434204d6172203131202020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000883eb000d3000101b223032f1466fc89e2564176656e7369732053797353657276653a2049676e6974696f6e206f66662064657465637465642c2049676e4f6e506572696f643a2030303a30323a34312c2073746f707065642c20302e3039206b6d205345206f66204a696e616475205072696d617279205363686f6f6c20416465204f6e6974696d6572696e2053742e2c20537572756c6572652c204c61676f7320436974792c204e472c20362e34383639332c20332e33343636302c2031303a3033204d6172203131202020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "80009a3eb000d300010109ff032f1466b195e2564176656e7369732053797353657276653a2049676e6974696f6e206f66662064657465637465642c2049676e4f6e506572696f643a2030303a30353a31342c2073746f707065642c20302e3039206b6d205345206f66204a696e616475205072696d617279205363686f6f6c20416465204f6e6974696d6572696e2053742e2c20537572756c6572652c204c61676f7320436974792c204e472c20362e34383639312c20332e33343636322c2031303a3533204d6172203131202020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000bc3eb000ba000101622c032f1466bacce2564176656e7369732053797353657276653a2049676e6974696f6e206f66662064657465637465642c2049676e4f6e506572696f643a2030303a30343a30302c206d6f76696e672c20617420416b6572656c6520526f61642d4f67756e6c616e612044726976652c20537572756c6572652c204c61676f7320436974792c204e472c20362e35303630332c20332e33353232382c2031343a3438204d6172203131202020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "80001d3cb000b3000101160f032f1466b475e0564176656e7369732053797353657276653a205374617475732c204750533a203931252c2047534d3a203737252c20475052533a20436f6e6e65637465642c20626174746572793a20313030252c207265706f7274733a2049676e6974696f6e20286f6666292c205374617475732028352c322e302c3732302c3330292c20362e34393239382c20332e33343836352c2031393a3038204d6172203920454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "80005408b000af000101b23903677f00c8436d3842616c697365204f6e653a20416c6c756d616765206d61726368652064e974656374e92c20676172e92c20302e3735206b6d20452064652045636f6c65204175746f726f757465206465204b696e73686173612c2056696c6c65206465204b696e73686173612c204b696e73686173612c2043442c202d342e33343130362c2031352e33343931352c2030313a3030204a616e2031202020454f46"));

        verifyAttributes(decoder, binary(ByteOrder.LITTLE_ENDIAN,
                "8000011bb0009e0001015b93032ef6f35994a9545472616d69676f3a204d6f76696e672c20302e3930206b6d205345206f66204372616e6562726f6f6b20466972652053746174696f6e2c2050656e726974682c205379646e65792c2041552c202d33332e37303732322c203135302e37313735392c2053452077697468207370656564203337206b6d2f682c2031393a3438204a616e20342020454f46"));

    }

}
