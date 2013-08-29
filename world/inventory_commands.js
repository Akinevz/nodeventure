command('get', 'Pick up an item from the current room.', function (rest, player, game) {
  var item = player.getCurrentRoom().getItem(rest);
    if (item) {
    if (item.gettable !== false) {
    // remove item from room & add to player inventory
        player.write("You pick up the " + rest);
        player.getCurrentRoom().broadcast(player.name + ' picks up the ' + rest, player);
        player.getCurrentRoom().items = _.without(player.getCurrentRoom().items, item);
        game.emit("invget:"+item.name);
        player.inventory.push(item);
    } else {
      player.write("You can not get the " + rest);
    }
    } else {
      player.write("Sorry, the item: " + rest + ", is not here.");
    }
});

command('take', function(rest, player, game) {
  game.execute(player, 'get ' + rest);
});

command('drop', 'Leave an item from your inventory in the current room.', function (rest, player, game) {
  _.map(player.inventory, function(item) {
    if (item.name === rest) {
      // remove item from player inventory & add to current room
      player.write("You drop the " + rest);
      player.getCurrentRoom().broadcast(player.name + ' drops the ' + rest, player);
      player.inventory = _.without(player.inventory, item);
      player.getCurrentRoom().items.push(item);
        game.emit("invdrop:"+item.name, rest, player, game);
    } else {
      player.write("The item: " + rest + ", is not in your inventory.");
    }
  });
});

command('inventory', "Display a list of all the items you're carrying.", function (rest, player, game) {
  player.inventory = player.inventory || [];
  console.log(player.inventory);
  if (player.inventory.length == 0) {
    player.write("You aren't carrying anything. Travel light!");
  }
  _.each(player.inventory, function (item) {
    player.write('You are carrying ' + (item.short || item.name));
  });
});

command('i', "Display a list of all the items you're carrying.", function (rest, player, game) {
  player.execute('inventory');
});

command('use', 'Example: use lemon',function (rest, player, item) {
  player.write("Can't use " + rest);
});
