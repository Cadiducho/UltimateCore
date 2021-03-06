/*
 * This file is part of UltimateCore, licensed under the MIT License (MIT).
 *
 * Copyright (c) Bammerbom
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package bammerbom.ultimatecore.bukkit.commands;

import bammerbom.ultimatecore.bukkit.r;
import bammerbom.ultimatecore.bukkit.resources.classes.MobType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class CmdRemoveall implements UltimateCommand {

    @Override
    public String getName() {
        return "removeall";
    }

    @Override
    public String getPermission() {
        return "uc.removeall";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run(final CommandSender cs, String label, String[] args) {
        if (!r.isPlayer(cs)) {
            return;
        }
        if (!r.perm(cs, "uc.removeall", false, true)) {
            return;
        }
        Integer range = 500;
        if (r.checkArgs(args, 0) == true && r.isInt(args[0])) {
            range = Integer.parseInt(args[0]);
            if (range > 10000) {
                range = 10000;
            }
        } else if (r.checkArgs(args, 1) && r.isInt(args[1])) {
            range = Integer.parseInt(args[1]);
            if (range > 10000) {
                range = 10000;
            }
        }
        EntityType et = null;
        if (r.checkArgs(args, 0)) {
            if (EntityType.fromName(args[0].toUpperCase()) != null) {
                et = EntityType.fromName(args[0].toUpperCase());
            } else if (EntityType.fromName(args[0].replaceAll("_", "").toUpperCase()) != null) {
                et = EntityType.fromName(args[0].replaceAll("_", "").toUpperCase());
            } else if (MobType.fromName(args[0]) != null) {
                et = MobType.fromName(args[0]).getType();
            } else if (!r.isInt(args[0])) {
                r.sendMes(cs, "removeallEntityTypeNotFound", "%Type", args[0]);
                return;
            }

        }
        Player p = (Player) cs;
        Integer amount = 0;
        for (Entity en : p.getNearbyEntities(range, 256, range)) {
            if ((en instanceof Painting) || (en instanceof ItemFrame) || (en instanceof Player)) {
                continue;
            }
            if (et != null && !en.getType().equals(et)) {
                continue;
            }
            en.remove();
            amount++;
        }
        r.sendMes(cs, "removeallMessage", "%Amount", amount, "%Radius", range);
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        if (curn == 0) {
            ArrayList<String> s = new ArrayList<>();
            for (EntityType t : EntityType.values()) {
                if (MobType.fromBukkitType(t) != null) {
                    s.add(MobType.fromBukkitType(t).name());
                } else {
                    s.add(t.getName().toLowerCase());
                }
            }

        }
        return new ArrayList<>();
    }
}
