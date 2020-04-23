INSERT INTO `category` (`id`, `name`, `shape_id`) VALUES
	(1, 'triangle', 1),
	(2, 'kite', 2),
	(2, 'parallelogram', 2),
	(2, 'rectangle', 2),
	(2, 'rhombus', 2),
	(2, 'square', 2),
	(2, 'trapezium', 2),
	(3, 'kite', 3),
	(3, 'parallelogram', 3),
	(3, 'rectangle', 3),
	(3, 'rhombus', 3),
	(3, 'square', 3),
	(3, 'trapezium', 3),
	(4, 'kite', 4),
	(4, 'parallelogram', 4),
	(4, 'rectangle', 4),
	(4, 'rhombus', 4),
	(4, 'square', 4),
	(4, 'trapezium', 4),
	(5, 'kite', 5),
	(5, 'parallelogram', 5),
	(5, 'rectangle', 5),
	(5, 'rhombus', 5),
	(5, 'square', 5),
	(5, 'trapezium', 5),
	(6, 'kite', 6),
	(6, 'parallelogram', 6),
	(6, 'rectangle', 6),
	(6, 'rhombus', 6),
	(6, 'square', 6),
	(6, 'trapezium', 6),
	(7, 'trapezium', 7),
	(8, 'circle', 8),
	(9, 'ellipse', 9);

	INSERT INTO `shape` (`id`, `name`) VALUES
	(1, 'triangle'),
	(2, 'square'),
	(3, 'rectangle'),
	(4, 'parallelogram'),
	(5, 'rhombus'),
	(6, 'kite'),
	(7, 'trapezium'),
	(8, 'circle'),
	(9, 'ellipse');

	INSERT INTO `requirement` (`id`, `width`,`height`,`high`) VALUES
	(1, 1,1,1),
	(2, 1,0,0),
	(3, 1,1,0),
	(4, 1,1,0),
	(5, 1,1,0),
	(6, 1,1,0),
	(7, 1,1,1),
	(8, 1,0,0),
	(9, 1,1,0);

INSERT INTO `user` (`id`, `age`, `firstname`, `lastname`, `password`, `username`) VALUES (1, 28, 'Son', 'Tran', '$2y$04$V7oN.kniFvZMbN1T8vMSEejVi4FLJGp2udIb7uX02RVRB3xD/6oOm', 'user1');
INSERT INTO `user` (`id`, `age`, `firstname`, `lastname`, `password`, `username`) VALUES (2, 30, 'Administrator', '', '$2y$04$V7oN.kniFvZMbN1T8vMSEejVi4FLJGp2udIb7uX02RVRB3xD/6oOm', 'admin');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES	(1, 'ROLE_USER');
INSERT INTO `user_roles` (`user_id`, `roles`) VALUES	(2, 'ROLE_ADMIN');









