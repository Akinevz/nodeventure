var ninja = character('ninja', {
  location: 'beach',
  description: 'Shifty and near invisible.'
});

handler('tick', function () {
  // every 30 minutes on average
  if (Math.random() * 30 < 1) {
    var room = ninja.getCurrentRoom(),
        exits = _.keys(room.exits),
        i = Math.floor(Math.random()*exits.length),
        exit = exits[i];
    room.broadcast('The Ninja pads silently to the ' + exit + ' exit');
    ninja.execute('go ' + exit);
    if (ninja.getCurrentRoom().getPlayers().indexOf('pirate') !== -1) {
	  ninja('say I hate pirates');
      var action = Math.floor(Math.random()*2);
      if (action > 0) {
        ninja.execute('attack pirate');
      } else {
        var exits = _.keys(room.exits),
            i = Math.floor(Math.random()*exists.length),
            exit = exits[i];
        ninja.execute('shove pirate ' + exit);
      }
    }
  }
});
