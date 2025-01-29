INSERT INTO users (username, password, role)
VALUES
('Viktor', '$2a$10$oRlrPjWzAFKI9s5ywAB3LOOohARBFuq2TnDB709/aKlttTTDtQHL2', 'ADMIN'),
('Ryze', '$2a$10$BhGTQ01ROvG/jqwTnyvxvOujbpIvXGw0ssftKwWAimPJrNdWttQYK', 'USER'),
('Orianna', '$2a$10$BhGTQ01ROvG/jqwTnyvxvOujbpIvXGw0ssftKwWAimPJrNdWttQYK', 'USER'),
('Powder', '$2a$10$BhGTQ01ROvG/jqwTnyvxvOujbpIvXGw0ssftKwWAimPJrNdWttQYK', 'USER'),
('Vi', '$2a$10$BhGTQ01ROvG/jqwTnyvxvOujbpIvXGw0ssftKwWAimPJrNdWttQYK', 'USER'),
('Jayce', '$2a$10$BhGTQ01ROvG/jqwTnyvxvOujbpIvXGw0ssftKwWAimPJrNdWttQYK', 'USER');

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
('Grocery shopping', 'Buy oil.', '2025-01-31', 2, 3),
('Warwick', 'Heal WW.', '2025-01-31', 2, 4),
('Cait', 'Find Cupcake.', '2025-01-31', 3, 5),
('Punch', 'Do a fistfight.', '2025-01-31', 1, 5),
('Home', 'Go back home', '2025-01-31', 1, 6),
('Hextech', 'Create object', '2025-01-31', 3, 6);
