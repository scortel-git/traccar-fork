package org.traccar.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.junit.jupiter.api.Test;
import org.traccar.ProtocolTest;

import java.nio.charset.StandardCharsets;

public class ElbBaseProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        var decoder = inject(new ElbBaseProtocolDecoder(null));

        verifyNull(decoder,
                binary("024630013031343335343632534B5938413942F8A647280B210001F21524000000000000000000030000000000000000000101000100A647280B83BE27004FF61900010032001666585072755546614D5561512D304F746D6A726C577700000000000000001A00A8BB"));
//        verifyNull(decoder, binary(
//                "003B000502000000000820202020202030313130373030303035373032363720383A000000000D00508401358E640032B37700000367B00000A804"));
//02B140163031343335343736534B59433245314B565165515843595A557576582D52585672536C49770308803800000000002200000000210402000000000000015E0100000000C39B150B0000000C8038000000000022000000002104020000000000000151040000000058F2150B0000000A80380000000000220000000021040200000000000001860400000000ABF2150B58F2150B000000DCF9150B700D27005D6319000000310000000000000000001A0C382C363B382C363B38
//        verifyPosition(decoder, binary(
//                "007100040200202020202020202020382020202020202031323334353637383930313233343520313320244750524D432C3232333135322E30302C412C333530392E3836303539342C4E2C30333332322E3734333838372C452C302E302C302E302C3032303631322C2C2C412A35320D0A"),
//                position("2012-06-02 22:31:52.000", true, 35.16434, 33.37906));
//
//        verifyPosition(decoder, binary(
//                "007600040200202020202020202020382020202020202030313138393230303036303831383920313320244750524D432C3137313834312E30302C412C333530392E3835323431302C4E2C30333332322E3735393131332C452C302E302C302E302C3137303731322C332E342C572C412A32350D0A00"));
//
//        verifyPosition(decoder, binary(
//                "006a000a081000202020202020202020333320202020202038363130373430323137313936353620204750524d432c3136313234382e30302c412c333433322e36393231312c532c30353833312e30323231372c572c302e3034382c2c3232303831342c2c2c412a3734"));

    }

}
