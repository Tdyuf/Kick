package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ExampleMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ClientSendMessageEvents.ALLOW_CHAT_MESSAGE.register(message -> {
            if (message.equalsIgnoreCase(".kick")) {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client.getNetworkHandler() != null) {
                    client.getNetworkHandler().getConnection()
                            .disconnect(Text.literal("You kicked yourself."));
                }
                return false;
            }
            return true;
        });
    }
}

