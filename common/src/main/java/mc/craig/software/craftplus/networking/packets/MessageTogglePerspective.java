package mc.craig.software.craftplus.networking.packets;

import mc.craig.software.craftplus.networking.VCNetwork;
import mc.craig.software.craftplus.util.ClientUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.threetag.palladiumcore.network.MessageContext;
import net.threetag.palladiumcore.network.MessageS2C;
import net.threetag.palladiumcore.network.MessageType;
import org.jetbrains.annotations.NotNull;

public class MessageTogglePerspective extends MessageS2C {

    public static String FIRST_PERSON = "FIRST_PERSON";
    public static String THIRD_PERSON_BACK = "THIRD_PERSON_BACK";
    public static String THIRD_PERSON_FRONT = "THIRD_PERSON_FRONT";

    private String pointOfView = "";

    public MessageTogglePerspective(String pointOfView) {
        this.pointOfView = pointOfView;
    }

    public MessageTogglePerspective(FriendlyByteBuf friendlyByteBuf) {
        pointOfView = friendlyByteBuf.readUtf(32767);
    }

    @NotNull
    @Override
    public MessageType getType() {
        return VCNetwork.POV_MESSAGE;
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.pointOfView);
    }

    @Override
    public void handle(MessageContext context) {
        ClientUtil.setPlayerPerspective(this.pointOfView);
    }


}
