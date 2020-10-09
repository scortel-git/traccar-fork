package org.traccar.protocol;

import org.junit.Test;
import org.traccar.ProtocolTest;

public class AtrackFrameDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        AtrackFrameDecoder decoder = new AtrackFrameDecoder();

        verifyFrame(
                binary("4052698c032a924f000147027fe5d7425f642e56060f031847bb68cb500719e26752c25bebc11c7fddce2b8ed4eff4ed863b187cc6653b5b1c1fc6803884d21aeeedae2ec6e72781d97e95b965610c1d107e5400cd5a7b7b3b592e676091c6a5893d80af9b3c63ae4de20d6e5bc60440bf2c299fbabfe268039d558e4b8589dd5173c926b7f51b916ba29f21d46ff9170793fe450072d691896e114fddce4fd29f7f2f9b74e41ca83814015e8a00ffd1f9bd475e2a44624e074a009455ab5628e39fce8036a09368cf1d2ba0d2653b979c0a00e9edc82335a56d1ee6071401d468b0f4cd761a743d011401d15b4636015721870dd0500695b2edabeabf2f4a00a514645cc83a739ad165f320c1ed401617a0a2800a2803ffd2faa68a004660aa598e00acd8f4d866d54eab3c7994284881fe11ebf5e68034e8a0086e674850927f0ae2bc4dafa5844659674451d39e49a00e1dae23d76ed67bb72211d109e4d5bd756da3b68a4b755021e30076a00cf31431a064e41e6a19a68d5396518f7a00f1ff008bfe27f31068766dbb7e1a723d3fbbf8d79aeb764748b489662be7ccbbb6820e07d4500734caa727765aa32ac0720e28026b4bb9ed7ccf2594798bb5b2a0f1f8f4a82800a2803fffd3f9b97352ae02e45004c808e4f7a997823bd005e86600618f26b7b4a9cab819fa500767a749b9403cd74162b903de803acd1e3c28aebf4d4c81401d05a4441fad682444738a00b712f2055f03e502802b14c5dffbc2ac106343ed4012a905411e94b40051401fffd4faa6992488980c793d0773400d54676df2f1e8b9e054b400564ebb77750c463b442d2119247f08f53e9401e7da85d6a12cd221d427217a856c60fe15caea9689292f2832bfac8777f3a00e67538ef150ff00665d9b4b95fba4aee46f623fa8ae26fbe24f88b49b87d3b5bd2a12e38ca3950e3d41e7228008be2ac02d423dadc09071c1047e791fcab96d77c79acdf92969279113f1c1cb7e7401876c4c939b8ba73230e5998e49ac4d66ee6bebd796462ddb9f4ed40140a12339e9dea225b1824d0025140051401fffd5f9bc676f6a7ae4af6e280255cf5c7153a7b0a0052d8715bba64bf32f39a00ed74694902bb1d306e65c500763a5afca2baed2"),
                decoder.decode(null, null, binary("4052698c032a924f000147027fe5d7425f642e56060f031847bb68cb500719e26752c25bebc11c7fddce2b8ed4eff4ed863b187cc6653b5b1c1fc6803884d21aeeedae2ec6e72781d97e95b965610c1d107e5400cd5a7b7b3b592e676091c6a5893d80af9b3c63ae4de20d6e5bc60440bf2c299fbabfe268039d558e4b8589dd5173c926b7f51b916ba29f21d46ff9170793fe450072d691896e114fddce4fd29f7f2f9b74e41ca83814015e8a00ffd1f9bd475e2a44624e074a009455ab5628e39fce8036a09368cf1d2ba0d2653b979c0a00e9edc82335a56d1ee6071401d468b0f4cd761a743d011401d15b4636015721870dd0500695b2edabeabf2f4a00a514645cc83a739ad165f320c1ed401617a0a2800a2803ffd2faa68a004660aa598e00acd8f4d866d54eab3c7994284881fe11ebf5e68034e8a0086e674850927f0ae2bc4dafa5844659674451d39e49a00e1dae23d76ed67bb72211d109e4d5bd756da3b68a4b755021e30076a00cf31431a064e41e6a19a68d5396518f7a00f1ff008bfe27f31068766dbb7e1a723d3fbbf8d79aeb764748b489662be7ccbbb6820e07d4500734caa727765aa32ac0720e28026b4bb9ed7ccf2594798bb5b2a0f1f8f4a82800a2803fffd3f9b97352ae02e45004c808e4f7a997823bd005e86600618f26b7b4a9cab819fa500767a749b9403cd74162b903de803acd1e3c28aebf4d4c81401d05a4441fad682444738a00b712f2055f03e502802b14c5dffbc2ac106343ed4012a905411e94b40051401fffd4faa6992488980c793d0773400d54676df2f1e8b9e054b400564ebb77750c463b442d2119247f08f53e9401e7da85d6a12cd221d427217a856c60fe15caea9689292f2832bfac8777f3a00e67538ef150ff00665d9b4b95fba4aee46f623fa8ae26fbe24f88b49b87d3b5bd2a12e38ca3950e3d41e7228008be2ac02d423dadc09071c1047e791fcab96d77c79acdf92969279113f1c1cb7e7401876c4c939b8ba73230e5998e49ac4d66ee6bebd796462ddb9f4ed40140a12339e9dea225b1824d0025140051401fffd5f9bc676f6a7ae4af6e280255cf5c7153a7b0a0052d8715bba64bf32f39a00ed74694902bb1d306e65c500763a5afca2baed2")));

        verifyFrame(
                binary("40502c414246342c3532362c3939312c3335363936313037353933313136352c313533343338313635362c313533343338313635392c313533343338313635392c2d38383432393138382c34343237313232352c37302c322c3230303536332c392c312c302c302c302c2c323030302c323030302c1a2c25434925434525434e25475125475325464c254d4c25564e25504425464325454c254554254344254154254d46254d5625425625434d25445425474c25474e254756254c43254d4525524c25525025534125534d255452254941254d502c302c3331303236302c31382c392c302c302c3254314b523332453238433730363138352c302c302c302c35342c383930313236303838313231353234373735392c3235322c36302c3132322c34312c3331303236303838313532343737352c302c687474703a2f2f6d6170732e676f6f676c652e636f6d2f6d6170733f713d34342e3237313232352c2d38382e343239313834201a2c3030393830303442303346343030393830303442303346343030393830303442303346333030393830303442303346343030393830303442303346343030393930303442303346343030393830303442303346343030393830303442303346343030393830303442303346343030393830303442303346331a2c3030343846463130303345381a2c302c3335363936313037353933313136352c302c3938302c31322c302c31382c35302c300d0a"),
                decoder.decode(null, null, binary("40502c414246342c3532362c3939312c3335363936313037353933313136352c313533343338313635362c313533343338313635392c313533343338313635392c2d38383432393138382c34343237313232352c37302c322c3230303536332c392c312c302c302c302c2c323030302c323030302c1a2c25434925434525434e25475125475325464c254d4c25564e25504425464325454c254554254344254154254d46254d5625425625434d25445425474c25474e254756254c43254d4525524c25525025534125534d255452254941254d502c302c3331303236302c31382c392c302c302c3254314b523332453238433730363138352c302c302c302c35342c383930313236303838313231353234373735392c3235322c36302c3132322c34312c3331303236303838313532343737352c302c687474703a2f2f6d6170732e676f6f676c652e636f6d2f6d6170733f713d34342e3237313232352c2d38382e343239313834201a2c3030393830303442303346343030393830303442303346343030393830303442303346333030393830303442303346343030393830303442303346343030393930303442303346343030393830303442303346343030393830303442303346343030393830303442303346343030393830303442303346331a2c3030343846463130303345381a2c302c3335363936313037353933313136352c302c3938302c31322c302c31382c35302c300d0a")));

        verifyFrame(
                binary("40502c383732442c3731322c36353232312c3335373239383037303432363439382c313533343731353836322c313533343731353836322c313533343732363437342c2d38383531303939352c34343236303637362c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32392c31302c3331303236302c373838383637342c3134342c31360d0a313533343731353932312c313533343731353932322c313533343732363437342c2d38383531313032332c34343236303636382c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32382c31302c3331303236302c373838383637342c3134342c31360d0a313533343731353938322c313533343731353938322c313533343732363437342c2d38383531313034362c34343236303636382c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32392c392c3331303236302c373838383637342c3134342c31360d0a313533343731363034312c313533343731363034322c313533343732363437342c2d38383531313031312c34343236303636332c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32392c31302c3331303236302c373838383637342c3134342c31360d0a313533343731363130322c313533343731363130322c313533343732363437342c2d38383531303938382c34343236303637362c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32392c31302c3331303236302c373838383637342c3134352c31360d0a"),
                decoder.decode(null, null, binary("40502c383732442c3731322c36353232312c3335373239383037303432363439382c313533343731353836322c313533343731353836322c313533343732363437342c2d38383531303939352c34343236303637362c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32392c31302c3331303236302c373838383637342c3134342c31360d0a313533343731353932312c313533343731353932322c313533343732363437342c2d38383531313032332c34343236303636382c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32382c31302c3331303236302c373838383637342c3134342c31360d0a313533343731353938322c313533343731353938322c313533343732363437342c2d38383531313034362c34343236303636382c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32392c392c3331303236302c373838383637342c3134342c31360d0a313533343731363034312c313533343731363034322c313533343732363437342c2d38383531313031312c34343236303636332c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32392c31302c3331303236302c373838383637342c3134342c31360d0a313533343731363130322c313533343731363130322c313533343732363437342c2d38383531303938382c34343236303637362c3137362c322c35363832372c362c312c302c302c302c2c323030302c323030302c1a2c25434925475125475325434e254345254d562553412c32392c31302c3331303236302c373838383637342c3134352c31360d0a")));

        verifyFrame(
                binary("40502c373542332c3132302c37393737392c3335383930313034383039313535342c32303138303431323134323531342c32303138303431323134323531342c32303138303431333030303635352c31363432333338392c34383137383730302c3130382c322c362e352c392c302c302c302c302c302c323030302c323030302c1a0d0a"),
                decoder.decode(null, null, binary("40502c373542332c3132302c37393737392c3335383930313034383039313535342c32303138303431323134323531342c32303138303431323134323531342c32303138303431333030303635352c31363432333338392c34383137383730302c3130382c322c362e352c392c302c302c302c302c302c323030302c323030302c1a0d0a")));

        verifyFrame(
                binary("244F4B0D0A"),
                decoder.decode(null, null, binary("244F4B0D0A")));

        verifyFrame(
                binary("fe0200014104d8f196820001"),
                decoder.decode(null, null, binary("fe0200014104d8f196820001")));

        verifyFrame(
                binary("40501e58003301e000014104d8f19682525ecd5d525ee344525ee35effc88815026ab4d70000020000104403de01000b0000000007d007d000"),
                decoder.decode(null, null, binary("40501e58003301e000014104d8f19682525ecd5d525ee344525ee35effc88815026ab4d70000020000104403de01000b0000000007d007d000")));

    }

}
