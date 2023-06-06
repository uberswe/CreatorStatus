package com.uberswe.creatorstatus.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.uberswe.creatorstatus.CreatorStatus;
import com.uberswe.creatorstatus.client.StatusData;
import com.uberswe.creatorstatus.event.ModEvents;
import com.uberswe.creatorstatus.networking.ModMessages;
import com.uberswe.creatorstatus.networking.packet.StatusC2SPacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class StatusCommand {

    private static String MESSAGE_NONE = "message.creatorstatus.none";
    private static String MESSAGE_STREAMING = "message.creatorstatus.streaming";
    private static String MESSAGE_RECORDING = "message.creatorstatus.recording";
    private static String MESSAGE_DNR = "message.creatorstatus.donotrecord";
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("status").
                then(Commands.literal("none").executes(StatusCommand::executeNone)).
                then(Commands.literal("streaming").executes(StatusCommand::executeStreaming)).
                then(Commands.literal("recording").executes(StatusCommand::executeRecording)).
                then(Commands.literal("do-not-record").executes(StatusCommand::executeDNR)));
    }

    // TODO could get the command input and make the execute functions into a single function
    private static int executeNone(CommandContext<CommandSourceStack> command){
        if(command.getSource().getEntity() instanceof Player player){
            player.sendSystemMessage(Component.translatable(MESSAGE_NONE));
            StatusData.setPlayerStatus(player.getStringUUID(), CreatorStatus.NONE);
            ModEvents.broadcastStatusUpdate();
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int executeStreaming(CommandContext<CommandSourceStack> command){
        if(command.getSource().getEntity() instanceof Player player){
            player.sendSystemMessage(Component.translatable(MESSAGE_STREAMING));
            StatusData.setPlayerStatus(player.getStringUUID(), CreatorStatus.STREAMING);
            ModEvents.broadcastStatusUpdate();
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int executeRecording(CommandContext<CommandSourceStack> command){
        if(command.getSource().getEntity() instanceof Player player){
            player.sendSystemMessage(Component.translatable(MESSAGE_RECORDING));
            StatusData.setPlayerStatus(player.getStringUUID(), CreatorStatus.RECORDING);
            ModEvents.broadcastStatusUpdate();
        }
        return Command.SINGLE_SUCCESS;
    }
    private static int executeDNR(CommandContext<CommandSourceStack> command){
        if(command.getSource().getEntity() instanceof Player player){
            player.sendSystemMessage(Component.translatable(MESSAGE_DNR));
            StatusData.setPlayerStatus(player.getStringUUID(), CreatorStatus.DO_NOT_RECORD);
            ModEvents.broadcastStatusUpdate();
        }
        return Command.SINGLE_SUCCESS;
    }
}
