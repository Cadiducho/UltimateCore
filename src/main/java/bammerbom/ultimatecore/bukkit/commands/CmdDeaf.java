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

import bammerbom.ultimatecore.bukkit.api.UC;
import bammerbom.ultimatecore.bukkit.r;
import bammerbom.ultimatecore.bukkit.resources.utils.DateUtil;
import java.util.Arrays;
import java.util.List;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdDeaf implements UltimateCommand {

    @Override
    public String getName() {
        return "deaf";
    }

    @Override
    public String getPermission() {
        return "uc.deaf";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList();
    }

    @Override
    public void run(final CommandSender cs, String label, String[] args) {
        if (r.checkArgs(args, 0) == false) {
            r.sendMes(cs, "deafUsage");
            return;
        }
        OfflinePlayer banp = r.searchOfflinePlayer(args[0]);
        if (banp == null || (!banp.hasPlayedBefore() && !banp.isOnline())) {
            r.sendMes(cs, "playerNotFound", "%Player", args[0]);
            return;
        }
        Long time = 0L;
        //Info
        if (r.checkArgs(args, 1) == false) {
        } else if (DateUtil.parseDateDiff(args[1]) != -1) {
            time = DateUtil.parseDateDiff(args[1]);
        }
        //Permcheck
        if (!r.perm(cs, "uc.deaf.time", false, false) && !r.perm(cs, "uc.deaf", false, false) && time == 0L) {
            r.sendMes(cs, "noPermissions");
            return;
        }
        if (!r.perm(cs, "uc.deaf.perm", false, false) && !r.perm(cs, "uc.deaf", false, false) && time != 0L) {
            r.sendMes(cs, "noPermissions");
            return;
        }
        UC.getPlayer(banp).setDeaf(true, time);
        if (time == 0L) {
            r.sendMes(cs, "deafMessage", "%Player", banp.getName());
        } else {
            r.sendMes(cs, "deafMessageTime", "%Player", banp.getName(), "%Time", DateUtil.format(time));
        }
        if (banp.isOnline()) {
            Player banp2 = (Player) banp;
            r.sendMes(banp2, "deafTarget");
        }
        return;
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
