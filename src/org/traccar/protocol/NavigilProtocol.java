package org.traccar.protocol;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.traccar.BaseProtocol;
import org.traccar.TrackerServer;
import org.traccar.protocol.commands.CommandTemplate;
import org.traccar.http.commands.CommandType;

import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;

public class NavigilProtocol extends BaseProtocol {

    public NavigilProtocol() {
        super("navigil");
    }

    @Override
    protected void loadCommandsTemplates(Map<CommandType, CommandTemplate> templates) {
        
    }

    @Override
    public void addTrackerServersTo(List<TrackerServer> serverList) {
        TrackerServer server = new TrackerServer(new ServerBootstrap(), this.getName()) {
            @Override
            protected void addSpecificHandlers(ChannelPipeline pipeline) {
                pipeline.addLast("frameDecoder", new NavigilFrameDecoder());
                pipeline.addLast("objectDecoder", new NavigilProtocolDecoder(NavigilProtocol.this));
            }
        };
        server.setEndianness(ByteOrder.LITTLE_ENDIAN);
        serverList.add(server);
    }
}
