/**
 * Copyright (c) 2011, Jason Gardner
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package freecake.games.core.commands;

import freecake.games.core.AbstractGame;
import freecake.games.core.Command;
import freecake.games.core.model.Item;
import freecake.games.core.model.ItemType;

/*
 * ReadCommand - read an item.
 */
public class ReadCommand extends AbstractCommand {

    @Override
    public String[] getRecognizedCommands() {
        String commands[] = { "read" };
        return commands;
    }
    
    @Override
    public boolean execute(Command command) {
        Item item = AbstractGame.instance.getCurrentRoom().getItem(command.getArgsString());
        if (item == null) {
            item = AbstractGame.instance.getInventoryItem(command.getArgsString());
        }
        if (item != null) {
            if (item.getType() == ItemType.Literature) {
                System.out.println("\n\"" + item.examine() + "\"");
                System.out.println();
                AbstractGame.instance.addToInventory(item);
            } else {
                System.out.println("Nothing happened");
            }
        } else if (command.getArgsString().isEmpty()) {
            System.out.println("Read what?");
        } else {
            System.out.println("There's nothing to read here");
        }
        return false;
    }

}
