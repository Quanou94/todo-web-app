INSERT INTO users (username, password, role)
VALUES
('Viktor', 'toto', 'ADMIN'),
('Ryze', 'tata', 'USER'),
('Orianna', 'titi', 'USER'),
('Powder', 'tito', 'USER');
('Vi', 'test', 'USER');
('Jayce', 'test', 'USER');

INSERT INTO category (name, description)
VALUES
('Work', 'Work on something'),
('Personal', 'Do something'),
('Hobby', 'Play something');

INSERT INTO task (name, description, deadline, category_id, user_id)
VALUES
('Glorious evolution', 'Complete the glorious evolution.', '2025-01-31', 1, 1),
('Jayce', 'Find Jayce.', '2025-01-31', 2, 1),
('Runes shopping', 'Find runes.', '2025-01-31', 1, 2),
('Grocery shopping', 'Buy oil.', '2025-01-31', 2, 3);
('Warwick', 'Heal WW.', '2025-01-31', 2, 4),
('Cait', 'Find Cupcake.', '2025-01-31', 3, 5),
('Punch', 'Do a fistfight.', '2025-01-31', 1, 5),
('Home', 'Go back home', '2025-01-31', 1, 6);
('Hextech', 'Create object', '2025-01-31', 3, 6);
