package mc.craig.software.craftplus.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import mc.craig.software.craftplus.common.entities.VenturePlayerData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

public class SkillPointsCommand {

    private static final SimpleCommandExceptionType ERROR_SET_POINTS_INVALID = new SimpleCommandExceptionType(
            Component.translatable("commands.venturecraft.skillpoints.set.xp.invalid")
    );

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> literalCommandNode = dispatcher.register(
                (LiteralArgumentBuilder<CommandSourceStack>) Commands.literal("skillpoints")
                        .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                        .then(
                                Commands.literal("add")
                                        .then(
                                                Commands.argument("targets", EntityArgument.players())
                                                        .then(
                                                                ((RequiredArgumentBuilder) Commands.argument("amount", IntegerArgumentType.integer())
                                                                        .executes(
                                                                                commandContext -> addExperience(
                                                                                        commandContext.getSource(),
                                                                                        EntityArgument.getPlayers(commandContext, "targets"),
                                                                                        IntegerArgumentType.getInteger(commandContext, "amount"),
                                                                                        Type.XP
                                                                                )
                                                                        ))
                                                                        .then(
                                                                                Commands.literal("points")
                                                                                        .executes(
                                                                                                commandContext -> addExperience(
                                                                                                        commandContext.getSource(),
                                                                                                        EntityArgument.getPlayers(commandContext, "targets"),
                                                                                                        IntegerArgumentType.getInteger(commandContext, "amount"),
                                                                                                        Type.XP
                                                                                                )
                                                                                        )
                                                                        )
                                                                        .then(
                                                                                Commands.literal("levels")
                                                                                        .executes(
                                                                                                commandContext -> addExperience(
                                                                                                        commandContext.getSource(),
                                                                                                        EntityArgument.getPlayers(commandContext, "targets"),
                                                                                                        IntegerArgumentType.getInteger(commandContext, "amount"),
                                                                                                        Type.POINTS
                                                                                                )
                                                                                        )
                                                                        )
                                                        )
                                        )
                        )
                        .then(
                                Commands.literal("set")
                                        .then(
                                                Commands.argument("targets", EntityArgument.players())
                                                        .then(
                                                                ((RequiredArgumentBuilder) Commands.argument("amount", IntegerArgumentType.integer(0))
                                                                        .executes(
                                                                                commandContext -> setExperience(
                                                                                        commandContext.getSource(),
                                                                                        EntityArgument.getPlayers(commandContext, "targets"),
                                                                                        IntegerArgumentType.getInteger(commandContext, "amount"),
                                                                                        Type.XP
                                                                                )
                                                                        ))
                                                                        .then(
                                                                                Commands.literal("xp")
                                                                                        .executes(
                                                                                                commandContext -> setExperience(
                                                                                                        commandContext.getSource(),
                                                                                                        EntityArgument.getPlayers(commandContext, "targets"),
                                                                                                        IntegerArgumentType.getInteger(commandContext, "amount"),
                                                                                                        Type.XP
                                                                                                )
                                                                                        )
                                                                        )
                                                                        .then(
                                                                                Commands.literal("points")
                                                                                        .executes(
                                                                                                commandContext -> setExperience(
                                                                                                        commandContext.getSource(),
                                                                                                        EntityArgument.getPlayers(commandContext, "targets"),
                                                                                                        IntegerArgumentType.getInteger(commandContext, "amount"),
                                                                                                        Type.POINTS
                                                                                                )
                                                                                        )
                                                                        )
                                                        )
                                        )
                        )
                        .then(
                                Commands.literal("query")
                                        .then(
                                                Commands.argument("targets", EntityArgument.player())
                                                        .then(
                                                                Commands.literal("xp")
                                                                        .executes(
                                                                                commandContext -> queryExperience(commandContext.getSource(), EntityArgument.getPlayer(commandContext, "targets"), Type.XP)
                                                                        )
                                                        )
                                                        .then(
                                                                Commands.literal("points")
                                                                        .executes(
                                                                                commandContext -> queryExperience(commandContext.getSource(), EntityArgument.getPlayer(commandContext, "targets"), Type.POINTS)
                                                                        )
                                                        )
                                        )
                        )
        );
        dispatcher.register(Commands.literal("xp").requires(commandSourceStack -> commandSourceStack.hasPermission(2)).redirect(literalCommandNode));
    }

    private static int queryExperience(CommandSourceStack source, ServerPlayer player, Type type) {
        int i = type.query.applyAsInt(player);
        source.sendSuccess(Component.translatable("commands.venturecraft.skillpoints.query." + type.name, player.getDisplayName(), i), false);
        return i;
    }

    private static int addExperience(CommandSourceStack source, Collection<? extends ServerPlayer> targets, int amount, Type type) {
        for (ServerPlayer serverPlayer : targets) {
            type.add.accept(serverPlayer, amount);
        }

        if (targets.size() == 1) {
            source.sendSuccess(
                    Component.translatable("commands.venturecraft.skillpoints.add." + type.name + ".success.single", amount, ((ServerPlayer) targets.iterator().next()).getDisplayName()),
                    true
            );
        } else {
            source.sendSuccess(Component.translatable("commands.venturecraft.skillpoints.add." + type.name + ".success.multiple", amount, targets.size()), true);
        }

        return targets.size();
    }

    private static int setExperience(CommandSourceStack source, Collection<? extends ServerPlayer> targets, int amount, Type type) throws CommandSyntaxException {
        int i = 0;

        for (ServerPlayer serverPlayer : targets) {
            if (type.set.test(serverPlayer, amount)) {
                ++i;
            }
        }

        if (i == 0) {
            throw ERROR_SET_POINTS_INVALID.create();
        } else {
            if (targets.size() == 1) {
                source.sendSuccess(
                        Component.translatable("commands.venturecraft.skillpoints.set." + type.name + ".success.single", amount, targets.iterator().next().getDisplayName()),
                        true
                );
            } else {
                source.sendSuccess(Component.translatable("commands.venturecraft.skillpoints.set." + type.name + ".success.multiple", amount, targets.size()), true);
            }

            return targets.size();
        }
    }

    enum Type {
        XP("xp", (player, xpPoints) -> {
            VenturePlayerData.get(player).ifPresent(data -> data.addSkillXP(xpPoints));
        }, (serverPlayer, integer) -> {
            var data = VenturePlayerData.get(serverPlayer).orElseThrow();
            if (integer >= data.getNeededXPForNextPoint()) {
                return false;
            } else {
                data.setSkillXP(integer);
                return true;
            }
        }, serverPlayer -> {
            var data = VenturePlayerData.get(serverPlayer).orElseThrow();
            return data.getSkillXP();
        }),
        POINTS("points", (player, points) -> {
            var data = VenturePlayerData.get(player).orElseThrow();
            data.addSkillPoints(points);
        }, (serverPlayer, integer) -> {
            var data = VenturePlayerData.get(serverPlayer).orElseThrow();
            data.setSkillPoints(integer);
            return true;
        }, serverPlayer -> {
            var data = VenturePlayerData.get(serverPlayer).orElseThrow();
            return data.getSkillPoints();
        });

        public final BiConsumer<ServerPlayer, Integer> add;
        public final BiPredicate<ServerPlayer, Integer> set;
        public final String name;
        final ToIntFunction<ServerPlayer> query;

        Type(
                String string2, BiConsumer<ServerPlayer, Integer> biConsumer, BiPredicate<ServerPlayer, Integer> biPredicate, ToIntFunction<ServerPlayer> toIntFunction
        ) {
            this.add = biConsumer;
            this.name = string2;
            this.set = biPredicate;
            this.query = toIntFunction;
        }
    }

}
