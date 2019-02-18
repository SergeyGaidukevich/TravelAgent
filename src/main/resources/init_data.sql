INSERT INTO travel_agent.countries (name) VALUES ('Belarus');
INSERT INTO travel_agent.countries (name) VALUES ('Russia');
INSERT INTO travel_agent.countries (name) VALUES ('England');
INSERT INTO travel_agent.countries (name) VALUES ('France');

INSERT INTO travel_agent.hotels (name, phone, country_id, stars)
	VALUES ('DoubleTree by Hilton Hotel Minsk', '+375443333333', 1, 5);
INSERT INTO travel_agent.hotels (name, phone, country_id, stars)
	VALUES ('Pushka Inn Hotel', '+3751743333333', 2, 5);
INSERT INTO travel_agent.hotels (name, phone, country_id, stars)
	VALUES ('The Petwood Hotel', '+441526352411', 3, 4);
INSERT INTO travel_agent.hotels (name, phone, country_id, stars)
	VALUES ('Hotel ibis Styles Paris Roissy CDG', '+33134293434', 4, 3);
    
INSERT INTO travel_agent.tours (photo, date, duration, country_id, hotel_id, type, description, cost)
	VALUES ('image', '2019-03-15', '3 weeks', 1, 1, 'tourist', 'relax', 6000);
INSERT INTO travel_agent.tours (photo, date, duration, country_id, hotel_id, type, description, cost)
	VALUES ('image', '2019-02-15', '1 day', 2, 2, 'tourist', 'relax', 700);
INSERT INTO travel_agent.tours (photo, date, duration, country_id, hotel_id, type, description, cost)
	VALUES ('image', '2019-03-03', '1 week', 3, 3, 'business', 'conversation', 3500);
INSERT INTO travel_agent.tours (photo, date, duration, country_id, hotel_id, type, description, cost)
	VALUES ('image', '2019-03-03', '5 days', 4, 4, 'business', 'conversation', 2100);
    
INSERT INTO travel_agent.users (login, password)
	VALUES ('Sergey231', '231231q');
INSERT INTO travel_agent.users (login, password)
	VALUES ('Vasya753', '123456789w');
    
INSERT INTO travel_agent.user_tours (user_id, tour_id)
	VALUES (1, 1);
INSERT INTO travel_agent.user_tours (user_id, tour_id)
	VALUES (1, 2);
INSERT INTO travel_agent.user_tours (user_id, tour_id)
	VALUES (2, 3);
INSERT INTO travel_agent.user_tours (user_id, tour_id)
	VALUES (1, 4);
    
INSERT INTO travel_agent.reviews (tour_id, user_id, content)
	VALUES (1, 1, 'great trip and hotel');
INSERT INTO travel_agent.reviews (tour_id, user_id, content)
	VALUES (1, 2, 'quiet and comfortable');