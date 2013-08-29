command('go', 'use an exit, for example "go north"', function (rest, player, game) {
  var currentRoom = player.getCurrentRoom(),
      direction = rest.toLowerCase(),
      destination = currentRoom.getExit(direction);
  if (destination) {
    player.setCurrentRoom(destination);
    player.write("You go " + rest);
    player.execute("look");
  } else {
    player.write("There is no " + rest + " exit");
  }
});

command('n', 'Shortcut for going north.',function (rest, player, game) {
  game.execute(player, "go north");
});

command('s','Shortcut for going south.', function (rest, player, game) {
  game.execute(player, "go south");
});

command('e', 'Shortcut for going east.', function (rest, player, game) {
  game.execute(player, "go east");
});

command('w','Shortcut for going west.',function (rest, player, game) {
  game.execute(player, "go west");
});

command('u', 'Shortcut for going up.', function (rest, player, game) {
  game.execute(player, "go up");
});

command('d','Shortcut for going down.',function (rest, player, game) {
  game.execute(player, "go down");
});

command('exits',function (rest, player, game) {
  var exits = Object.keys(player.getCurrentRoom().exits);
  player.write("Exits: " + exits.join(","));
});

command('teleport', '"teleport <player>" teleports you to the location of another player', function (rest, player, game) {
  var destplayer = game.getPlayer(rest), destination;
  if (destplayer) {
    destination = destplayer.getCurrentRoom();
  } else {
    destination = game.rooms[rest];
  }
  if (destination) {
    player.getCurrentRoom().broadcast(player.name + ' disappears in a flash of science');
    player.setCurrentRoom(destination);
    player.getCurrentRoom().broadcast(player.name + ' appears in a flash of science');
    player.execute('look');
  } else {
    player.write("Can't find that player");
  }
});
