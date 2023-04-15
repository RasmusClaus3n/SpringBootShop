--SET FOREIGN_KEY_CHECKS = 0;
SET REFERENTIAL_INTEGRITY FALSE;

INSERT INTO product_genres (product_id, genre)
VALUES
    (1, 'Action'),
    (1, 'Horror'),
    (2, 'Action'),
    (2, 'Adventure'),
	(3, 'Adventure'),
    (3, 'RPG'),
	(4, 'Action'),
    (4, 'Adventure'),
	(5, 'Adventure'),
    (5, 'RPG'),
	(6, 'Action'),
    (6, 'Adventure'),
	(7, 'Adventure'),
    (7, 'RPG'),
    (8, 'Plattformer'),
    (8, 'Action'),
    (9, 'Horror'),
    (10, 'Plattformer'),
    (10, 'Adventure'),
    (11, 'Adventure'),
    (11, 'Plattformer'),
    (12, 'Action'),
    (12, 'RPG');

INSERT INTO product(name, description, price, image_url, platform, category)
VALUES (
'Resident Evil 4',
'Survival is just the beginning.\nSix years have passed since the biological disaster in Raccoon City.Agent Leon S. Kennedy, one of the survivors of the incident, has been sent to rescue the presidents kidnapped daughter. He tracks her to a secluded European village, where there is something terribly wrong with the locals. And the curtain rises on this story of daring rescue and grueling horror where life and death, terror and catharsis intersect.\nFeaturing modernized gameplay, a reimagined storyline, and vividly detailed graphics,Resident Evil 4 marks the rebirth of an industry juggernaut.\nRelive the nightmare that revolutionized survival horror.',
59.99,
'https://cdn.mobygames.com/covers/16977235-resident-evil-4-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'Metriod Prime: Remastered',
'Metroid Prime: Remastered is a remastered version of the original Metroid Prime first released on the GameCube in 2002. This is the first version of the game for the Nintendo Switch and it remastered with revamped high-definition visuals and modernized audio. The game supports a modern dual-analog control setup as well as a more traditional setup inspired by the release of the game for the GameCube and Metroid Prime Trilogy for Nintendo Wii. There is a new casual difficulty option that reduces the amount of damage Samus takes in combat. Bonus content includes concept and development art for both the original game and the remaster as well as a soundtrack gallery and 3D character gallery.',
59.99,
'https://cdn.mobygames.com/covers/15551820-metroid-prime-remastered-nintendo-switch-front-cover.jpg',
'Switch',
'Video game'
),
(
'Elden Ring',
'THE NEW FANTASY ACTION RPG.\nRise, Tarnished, and be guided by grace to brandish the power of the Elden Ring and become an Elden Lord in the Lands Between.',
49.99,
'https://cdn.mobygames.com/covers/10722526-elden-ring-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'God of War: Ragnarök',
'To play this game on PS5, your system may need to be updated to the latest system software. Although this game is playable on PS5, some features available on PS4 may be absent. See PlayStation.com/bc for more details. Buy the God of War Ragnarök digital version for PS4™ from PlayStation™Store and when ready, upgrade to the God of War Ragnarök digital version for PS5™ for an additional cost. See God of War Ragnarök for PS5™ for price details.',
59.99,
'https://cdn.mobygames.com/covers/11182321-god-of-war-ragnarok-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'Demon''s Souls',
'Demon''s Souls is an action RPG focusing on brutal hack-and-slash gameplay in a fantasy world and a high difficulty level with few introductions. It is a remake of the 2009 game of the same name. It stays faithful to the original vision, only adding smaller additions and changes to the gameplay, but visually redone entirely including new motion capture animations. The game can be run in a Cinematic mode with 30FPS and a native 4K resolution or a Performance mode with 60FPS and a dynamic 4K resolution.',
39.99,
'https://cdn.mobygames.com/covers/9216528-demons-souls-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'Horizon Forbidden West',
'To play this game on PS5, your system may need to be updated to the latest system software. Although this game is playable on PS5, some features available on PS4 may be absent. See PlayStation.com/bc for more details.',
49.99,
'https://cdn.mobygames.com/covers/10495868-horizon-ii-forbidden-west-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'Stranger of Paradise Final Fantasy Origin',
'To play this game on PS5, your system may need to be updated to the latest system software. Although this game is playable on PS5, some features available on PS4 may be absent. See PlayStation.com/bc for more details.',
29.99,
'https://cdn.mobygames.com/covers/10851376-stranger-of-paradise-final-fantasy-origin-playstation-4-front-co.jpg',
'PS4',
'Video game'
),
(
'Marvel Spider-Man: Miles Morales',
'Marvel''s Spider-Man: Miles Morales is the second game in the PlayStation exclusive Marvel''s Spider-Man series, following Marvel''s Spider-Man (2018). It is centered around Miles Morales, a fictional comic book superhero who becomes Spider-Man as a Black teenager of Puerto Rican ancestry on his mother''s side, following the death of Peter Parker. The game handles it differently compared to the original storyline in the comic books, continuing the narrative of the first game (and especially the DLC The City That Never Sleeps) where Miles Morales is bitten by a genetically-enhanced spider and gained powers similar to Peter Parker''s. Miles trains under Parker and is still integrating in the role of a Spider-Man. In the game Parker does not die, but leaves to join his girlfriend Mary Jane Watson to cover a civil war, leaving Miles as the only webslinger in New York.',
49.99,
'https://cdn.mobygames.com/covers/11143607-marvel-spider-man-miles-morales-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'Dead Space',
'The sci-fi survival-horror classic Dead Space™ returns, completely rebuilt from the ground up to offer a deeper, more immersive experience. This remake brings jaw-dropping visual fidelity, suspenseful atmospheric audio, and improvements to gameplay while staying faithful to the original game’s thrilling vision. ',
59.99,
'https://cdn.mobygames.com/covers/11298642-dead-space-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'Sackboy: A Big Adventure',
'Sackboy: A Big Adventure is a 3D platformer starring Sackboy, the character first introduced in the LittleBigPlanet games. One day the evil jester Vex invades Sackboy''s planet Craftworld, part of the Imagisphere where the Sackfolk live, by literally tearing the sky and climbing inside. He uses a machine called the Topsy Turver to suck up all inhabitants. Sackboy is the only one who can escape in a space ship, stealing Vex''s plans for what he calls the Uproar that will remove all creativity from the world. Order needs be restored by completing five large words. They have different themes such as ''jungle'', ''retro space-age'', ''countryside'' or ''underwater''. The main goal is to collect orbs that dispel Vex''s barriers so Sackboy can reach the top of a mountain.',
34.99,
'https://cdn.mobygames.com/covers/11106241-sackboy-a-big-adventure-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'Ratchet & Clank: Rift Apart',
'Ratchet and Clank are back! Help them stop a robotic emperor intent on conquering cross-dimensional worlds, with their own universe next in the firing line. Witness the evolution of the dream team as they’re joined by Rivet – a Lombax resistance fighter from another dimension. ',
39.99,
'https://cdn.mobygames.com/covers/8652739-ratchet-clank-rift-apart-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'Ghost of Tsushima: Director''s Cut',
'To play this game on PS5, your system may need to be updated to the latest system software. Although this game is playable on PS5, some features available on PS4 may be absent. See PlayStation.com/bc for more details. If you already own Ghost of Tsushima on PS4™, you can upgrade to the Ghost of Tsushima DIRECTOR’S CUT on PS5™ for $29.99 from 20 August 2021.',
69.99,
'https://cdn.mobygames.com/covers/9713917-ghost-of-tsushima-directors-cut-playstation-5-front-cover.jpg',
'PS5',
'Video game'
),
(
'Forza Horizon 4',
'Forza Horizon 4 is an open world racing game and the fourth game in the arcade spin-off series of the main Forza Racing series. This edition is set in a fictitious representation of the United Kingdom. The game features hundreds of cars from numerous licensed manufacturers. Players experience the UK in all four seasons of the year using a dynamic weather system, with each season bringing its own challenge through weather, terrain and specific vehicles.',
29.99,
'https://cdn.mobygames.com/covers/8401290-forza-horizon-4-xbox-one-front-cover.jpg',
'XboxOne',
'Video game'
),
(
'Devil May Cry 5',
'The Devil you know returns in this brand new entry in the over-the-top action series available on the PC. Prepare to get downright demonic with this signature blend of high-octane stylized action and otherworldly & original characters the series is known for. Director Hideaki Itsuno and the core team have returned to create the most insane, technically advanced and utterly unmissable action experience of this generation!',
39.99,
'https://cdn.mobygames.com/covers/7622168-devil-may-cry-5-xbox-one-front-cover.jpg',
'XboxOne',
'Video game'
),
(
'Red Dead Redemption II',
'After a robbery goes badly wrong in the western town of Blackwater, Arthur Morgan and the Van der Linde gang are forced to flee. With federal agents and the best bounty hunters in the nation massing on their heels, the gang must rob, steal and fight their way across the rugged heartland of America in order to survive. As deepening internal divisions threaten to tear the gang apart, Arthur must make a choice between his own ideals and loyalty to the gang who raised him.',
49.99,
'https://cdn.mobygames.com/covers/11283278-red-dead-redemption-ii-xbox-one-front-cover.jpg',
'XboxOne',
'Video game'
),
(
'Metal Gear Solid V: The Phantom Pain',
'Metal Gear Solid V: The Phantom Pain is a third-person action game with a focus on stealth and the final game in the Metal Gear series. Canonically it is the fifth game in the series, but it is the eleventh main installment overall. It is the sequel to Metal Gear Solid V: Ground Zeroes released the year before and a prequel to the original Metal Gear. The gameplay borrows and expands upon most of the mechanics introduced in Ground Zeroes, which was a smaller slice in content in anticipation of this title. The game is set in 1984 with Big Boss also known as Punished "Venom" Snake as the protagonist.',
49.99,
'https://cdn.mobygames.com/covers/1677467-metal-gear-solid-v-the-phantom-pain-xbox-one-front-cover.png',
'XboxOne',
'Video game'
),
(
'The Legend of Zelda: Breath of the Wild',
'Forget everything you know about The Legend of Zelda games. Step into a world of discovery, exploration, and adventure in The Legend of Zelda: Breath of the Wild, a boundary-breaking new game in the acclaimed series. Travel across vast fields, through forests, and to mountain peaks as you discover what has become of the kingdom of Hyrule In this stunning Open-Air Adventure. Now on Nintendo Switch, your journey is freer and more open than ever. Take your system anywhere, and adventure as Link any way you like.',
49.99,
'https://cdn.mobygames.com/covers/8437180-the-legend-of-zelda-breath-of-the-wild-nintendo-switch-front-cov.jpg',
'Switch',
'Video game'
);

--SET FOREIGN_KEY_CHECKS = 1;
SET REFERENTIAL_INTEGRITY TRUE;