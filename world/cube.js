room('spinning_cube', {
  description: "You're now in the spinning cube. Everything is black and white. Everything spins slower or faster.",
  exits: {
    west: 'alley',
    east: 'temple',
    down: 'basement'
  },
  image: 'http://cimota.com/blog/wp-content/uploads/2012/06/22-Cans-Curiosity-490x245.jpg'
});

item('spinning_cube', 'black_cube_fragment', {
  respawnTime: 120,
  short: 'A fragment of the ancient black cube...',
  description: 'The ancient black cube is said to endow its owner with misterious supernatural powers...'
});