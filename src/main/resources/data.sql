INSERT INTO wild_user (id, name, phone, email, active)
VALUES ('7c9e6679-7425-40de-944b-e07fc1f90ae7', 'Super Admin', '1234567890', 'john@example.com', 1),
       ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'Jane Smith', '9876543210', 'jane@example.com', 1),
       ('7638419a-7a9f-4dca-a9ba-e1a5f0ebef62', 'Bob Johnson', '5555555555', 'bob@example.com', 1),
       ('e7073c8f-f5f8-4e24-83c1-d86abdedcfda', 'Alice Brown', '1112223333', 'alice@example.com', 1),
       ('1b2e0fb8-3949-4b58-9f6a-54f9a2e17fc0', 'Mike Williams', '9998887777', 'mike@example.com', 0),
       ('5a00d3f5-1265-41b3-a6e2-07053bdf5e72', 'Katie Snow', '5551234567', 'katie@example.com', 1),
       ('a8d99b2b-d7c1-47c3-92fe-11f55e3c24ef', 'Adam Stone', '2223334444', 'adam@example.com', 1);
INSERT INTO coordinate (id, latitude, longitude)
VALUES ('7c9e6679-7425-40de-944b-e07fc1f90ae7', 52.257939, 21.023690),
       ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 52.255194, 21.022596),
       ('7638419a-7a9f-4dca-a9ba-e1a5f0ebef62', 52.256600, 21.021051),
       ('e7073c8f-f5f8-4e24-83c1-d86abdedcfda', 52.257769, 21.022081),
       ('1b2e0fb8-3949-4b58-9f6a-54f9a2e17fc0', 52.260172, 21.018605),
       ('5a00d3f5-1265-41b3-a6e2-07053bdf5e72', 52.257664, 21.026158),
       ('e537546b-4b5f-4d29-85e7-91624e059457', 52.259910, 21.022574),
       ('7a985f4c-cf3e-4d1a-9cb4-f8e8f9a3a2f7', 52.258911, 21.022939);
INSERT INTO map (id, coordinate_id, zoom, bearing, current) VALUES
    ('7c75ac6f-4c91-3f4d-b6c1-694b71f63491', '7a985f4c-cf3e-4d1a-9cb4-f8e8f9a3a2f7', 15.0, -33, true);
INSERT INTO location (id, title, map_id, description, coordinate_id)
VALUES ('36317681-9c3c-434c-9ea4-055a1f127e0a', 'Elephant Pavilion', '7c75ac6f-4c91-3f4d-b6c1-694b71f63491', 'A spacious exhibit where elephants roam freely. Visitors can watch these majestic creatures up close.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '7c9e6679-7425-40de-944b-e07fc1f90ae7'),
       ('5c4c37ea-0a4c-49f9-9ae5-e81c7852ca59', 'Arctic Menagerie', '7c75ac6f-4c91-3f4d-b6c1-694b71f63491', 'Experience the chilling beauty of the Arctic region with polar bears, seals, and arctic birds.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
       ('e6f29eb9-9c50-44c1-8ebc-1dced4d12c09', 'Aquarium', '7c75ac6f-4c91-3f4d-b6c1-694b71f63491', 'Dive into the mesmerizing underwater world with a variety of colorful fish and marine life.', '7638419a-7a9f-4dca-a9ba-e1a5f0ebef62'),
       ('78f2e590-5aa7-414e-baad-7d4a3ff7120f', 'Bird Cages', '7c75ac6f-4c91-3f4d-b6c1-694b71f63491', 'Witness the beauty and grace of exotic birds from different corners of the world.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'e7073c8f-f5f8-4e24-83c1-d86abdedcfda'),
       ('c2a78481-719b-4bf7-ae3d-013a89d1eb44', 'Lion"s Den', '7c75ac6f-4c91-3f4d-b6c1-694b71f63491', 'Step into the territory of the king of the jungle and observe these powerful felines in action.', '1b2e0fb8-3949-4b58-9f6a-54f9a2e17fc0'),
       ('29b77468-c687-4565-bf9f-0cb6c5db9183', 'Monkey Jungle', '7c75ac6f-4c91-3f4d-b6c1-694b71f63491', 'A lively habitat where monkeys swing from trees and entertain visitors with their antics.', '5a00d3f5-1265-41b3-a6e2-07053bdf5e72'),
       ('99474b78-09d8-418e-8d39-3c5a3a0c4a43', 'Savannah Plains', '7c75ac6f-4c91-3f4d-b6c1-694b71f63491', 'Watch zebras, giraffes, and antelopes grazing together in an environment resembling the African savannah.', 'e537546b-4b5f-4d29-85e7-91624e059457');
INSERT INTO event (id, title, description, location_id, open_to_public, starts_at, ends_at)
VALUES ('e0c43b5a-1a79-4324-86b2-b91b3496c22b', 'Summer Safari', 'Join us for an exciting safari adventure in the heart of the jungle.', '36317681-9c3c-434c-9ea4-055a1f127e0a', 1, '2023-09-24 10:00:00', '2023-09-24 12:00:00'),
       ('e54d6a7b-d8d8-4729-a09a-ea143c99e451', 'Penguin Parade', 'Witness the adorable penguin parade at our Arctic Menagerie exhibit.', '5c4c37ea-0a4c-49f9-9ae5-e81c7852ca59', 1, '2023-09-24 14:30:00', '2023-09-24 16:30:00'),
       ('2a3b946e-0ca9-423f-bb6b-2b9941221253', 'Underwater World', 'Dive into the mesmerizing underwater world with a variety of colorful fish and marine life.', 'e6f29eb9-9c50-44c1-8ebc-1dced4d12c09', 1, '2023-09-24 17:00:00','2023-09-24 19:00:00'),
       ('10fc437e-0b4f-4c1e-8a5f-1e13d2b232eb', 'Birdwatching Tour', 'Discover bird cages exhibit.', '78f2e590-5aa7-414e-baad-7d4a3ff7120f', 1, '2023-09-25 11:00:00', '2023-09-25 12:00:00'),
       ('cbbf67d6-d77f-40e2-9447-df28e4f8d4d0', 'Lion"s Roar', 'Experience the thrilling sight of lions roaring in their den at the Lion"s Den.','c2a78481-719b-4bf7-ae3d-013a89d1eb44', 1,'2023-09-25 14:00:00', '2023-09-25 16:00:00'),
       ('9303d99d-18ec-4ec3-b8bb-1d4e1cdedf6b', 'Monkey Madness', 'Join our playful monkeys for a fun-filled day in the Monkey Jungle exhibit.', '29b77468-c687-4565-bf9f-0cb6c5db9183', 1,'2023-09-26 09:30:00', '2023-09-26 12:00:00'),
       ('3cd9ca32-5249-4c5a-8a8b-3a083ed5e1d1', 'Savannah Adventure', 'Embark on a safari adventure and encounter zebras and giraffes in the Savannah Plains.', '99474b78-09d8-418e-8d39-3c5a3a0c4a43', 1, '2023-09-26 15:30:00', '2023-09-26 18:30:00'),
       ('2c625202-09ac-4541-a00f-ec13a7a76859', 'Dolphin Delight', 'Experience the magic of dolphins in an enchanting show at the Dolphin Cove. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'e6f29eb9-9c50-44c1-8ebc-1dced4d12c09', 1, '2023-09-27 22:30:00', '2023-09-27 22:30:00'),
       ('f2f90502-99e7-481a-b9ca-6eef84c220cd', 'Butterfly Garden', 'Enter Butterfly Garden exhibit.', '78f2e590-5aa7-414e-baad-7d4a3ff7120f', 1, '2023-09-27 16:00:00', '2023-09-27 19:30:00'),
       ('c7946d57-2c27-4bc7-9f34-0217cd9b934a', 'Jungle Trek', 'Embark on a thrilling jungle trek to explore the hidden wonders of the rainforest.', '29b77468-c687-4565-bf9f-0cb6c5db9183', 1, '2023-09-28 11:00:00', '2023-09-28 14:00:00'),
       ('7c0270b9-9444-41df-924a-6c5d37c465c6', 'Arctic Adventure', 'Experience the Arctic wilderness and encounter polar bears at the Arctic Menagerie.', '5c4c37ea-0a4c-49f9-9ae5-e81c7852ca59', 1, '2023-09-28 15:30:00', '2023-09-28 17:30:00'),
       ('6bc819a3-d361-45ca-99cd-2d150274f79a', 'Zebra painting', 'Discover the wonders of the Savannah and its diverse wildlife.', '99474b78-09d8-418e-8d39-3c5a3a0c4a43', 1, '2023-09-29 10:00:00', '2023-09-29 12:00:00'),
       ('8dbd8124-1f97-4d72-9389-6471db2b7b8d', 'Giant Pandas Encounter', 'Meet the adorable giant pandas and learn about their conservation efforts.', '29b77468-c687-4565-bf9f-0cb6c5db9183', 1, '2023-09-29 14:30:00','2023-09-29 16:00:00'),
       ('dd4a2059-97f2-4162-9f8d-4b95ed13d2f3', 'Frozen Adventure', 'Embark on a frozen adventure and experience the beauty of the Arctic Menagerie. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '5c4c37ea-0a4c-49f9-9ae5-e81c7852ca59', 1, '2023-09-30 09:30:00', '2023-09-30 11:45:00'),
       ('4be3a159-82eb-4c1e-86b7-083cd63e2099', 'Feathered Spectacle', 'Be mesmerized by a spectacular showcase of colorful birds in flight. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '78f2e590-5aa7-414e-baad-7d4a3ff7120f', 1, '2023-09-30 14:00:00', '2023-09-30 15:30:00');
INSERT INTO wild_user_location (wild_user_id, location_id)
VALUES ('7c9e6679-7425-40de-944b-e07fc1f90ae7', 'c2a78481-719b-4bf7-ae3d-013a89d1eb44'), -- John Doe to Lion's Den
       ('7c9e6679-7425-40de-944b-e07fc1f90ae7', '78f2e590-5aa7-414e-baad-7d4a3ff7120f'), -- John Doe to Elephant Pavilion
       ('f47ac10b-58cc-4372-a567-0e02b2c3d479', '5c4c37ea-0a4c-49f9-9ae5-e81c7852ca59'), -- Jane Smith to Arctic Menagerie
       ('7638419a-7a9f-4dca-a9ba-e1a5f0ebef62', 'e6f29eb9-9c50-44c1-8ebc-1dced4d12c09'), -- Bob Johnson to Aquarium
       ('7638419a-7a9f-4dca-a9ba-e1a5f0ebef62', '29b77468-c687-4565-bf9f-0cb6c5db9183'), -- Bob Johnson to Monkey Jungle
       ('e7073c8f-f5f8-4e24-83c1-d86abdedcfda', '99474b78-09d8-418e-8d39-3c5a3a0c4a43'), -- Alice Brown to Savannah Plains
       ('e7073c8f-f5f8-4e24-83c1-d86abdedcfda', '78f2e590-5aa7-414e-baad-7d4a3ff7120f'), -- Alice Brown to Bird Cages
       ('5a00d3f5-1265-41b3-a6e2-07053bdf5e72', '5c4c37ea-0a4c-49f9-9ae5-e81c7852ca59'), -- Katie Snow to Arctic Menagerie
       ('5a00d3f5-1265-41b3-a6e2-07053bdf5e72', 'e6f29eb9-9c50-44c1-8ebc-1dced4d12c09'), -- Katie Snow to Aquarium
       ('5a00d3f5-1265-41b3-a6e2-07053bdf5e72', '99474b78-09d8-418e-8d39-3c5a3a0c4a43'), -- Katie Snow to Savannah Plains
       ('a8d99b2b-d7c1-47c3-92fe-11f55e3c24ef', '78f2e590-5aa7-414e-baad-7d4a3ff7120f'), -- Adam Stone to Bird Cages
       ('a8d99b2b-d7c1-47c3-92fe-11f55e3c24ef', '29b77468-c687-4565-bf9f-0cb6c5db9183'), -- Adam Stone to Monkey Jungle
       ('a8d99b2b-d7c1-47c3-92fe-11f55e3c24ef', '5c4c37ea-0a4c-49f9-9ae5-e81c7852ca59'); -- Adam Stone to Elephant Pavilion
INSERT INTO event_organizer (event_organized_id, organizer_id) VALUES
        ('e0c43b5a-1a79-4324-86b2-b91b3496c22b', '7c9e6679-7425-40de-944b-e07fc1f90ae7'), -- John Doe to Summer Safari
        ('cbbf67d6-d77f-40e2-9447-df28e4f8d4d0', '7c9e6679-7425-40de-944b-e07fc1f90ae7'), -- John Doe to Lion's Roar
        ('e54d6a7b-d8d8-4729-a09a-ea143c99e451', 'f47ac10b-58cc-4372-a567-0e02b2c3d479'), -- Jane Smith to Penguin Parade
        ('7c0270b9-9444-41df-924a-6c5d37c465c6', 'f47ac10b-58cc-4372-a567-0e02b2c3d479'), -- Jane Smith to Arctic Adventure
        ('dd4a2059-97f2-4162-9f8d-4b95ed13d2f3', 'f47ac10b-58cc-4372-a567-0e02b2c3d479'), -- Jane Smith to Frozen Adventure
        ('9303d99d-18ec-4ec3-b8bb-1d4e1cdedf6b', '7638419a-7a9f-4dca-a9ba-e1a5f0ebef62'), -- Bob Johnson to Monkey Madness
        ('c7946d57-2c27-4bc7-9f34-0217cd9b934a', '7638419a-7a9f-4dca-a9ba-e1a5f0ebef62'), -- Bob Johnson to Jungle Trek
        ('8dbd8124-1f97-4d72-9389-6471db2b7b8d', '7638419a-7a9f-4dca-a9ba-e1a5f0ebef62'), -- Bob Johnson to Giant Pandas Encounter
        ('2a3b946e-0ca9-423f-bb6b-2b9941221253', '7638419a-7a9f-4dca-a9ba-e1a5f0ebef62'), -- Bob Johnson to Underwater World
        ('2c625202-09ac-4541-a00f-ec13a7a76859', '7638419a-7a9f-4dca-a9ba-e1a5f0ebef62'), -- Bob Johnson to Dolphin Delight
        ('3cd9ca32-5249-4c5a-8a8b-3a083ed5e1d1', 'e7073c8f-f5f8-4e24-83c1-d86abdedcfda'), -- Alice Brown to Savannah Adventure
        ('10fc437e-0b4f-4c1e-8a5f-1e13d2b232eb', 'e7073c8f-f5f8-4e24-83c1-d86abdedcfda'), -- Alice Brown to Birdwatching Tour
        ('f2f90502-99e7-481a-b9ca-6eef84c220cd', 'e7073c8f-f5f8-4e24-83c1-d86abdedcfda'), -- Alice Brown to Butterfly Garden
        ('4be3a159-82eb-4c1e-86b7-083cd63e2099', 'e7073c8f-f5f8-4e24-83c1-d86abdedcfda'), -- Alice Brown to Feathered Spectacle
        ('10fc437e-0b4f-4c1e-8a5f-1e13d2b232eb', 'e7073c8f-f5f8-4e24-83c1-d86abdedcfda'), -- Alice Brown to Zebra painting
        ('6bc819a3-d361-45ca-99cd-2d150274f79a', '5a00d3f5-1265-41b3-a6e2-07053bdf5e72'), -- Katie Snow to Arctic Adventure
        ('10fc437e-0b4f-4c1e-8a5f-1e13d2b232eb', '5a00d3f5-1265-41b3-a6e2-07053bdf5e72'), -- Katie Snow to Zebra painting
        ('c7946d57-2c27-4bc7-9f34-0217cd9b934a', 'a8d99b2b-d7c1-47c3-92fe-11f55e3c24ef'), -- Adam Stone to Jungle Trek
        ('4be3a159-82eb-4c1e-86b7-083cd63e2099', 'a8d99b2b-d7c1-47c3-92fe-11f55e3c24ef'); -- Adam Stone to Feathered Spectacle
INSERT INTO role (id, name) VALUES
        ('7b75ac6f-5c91-4f4d-b6c2-694b71f63215', 'MY-EVENTS'),
        ('ddb98ed3-0765-4c70-9d02-2be7d18d86d2', 'EVENT-MANAGEMENT'),
        ('e8f461a2-82c4-4d1c-9c0b-aa61f1b3c786', 'MAP-CONFIGURATION'),
        ('3a9c8e47-ea25-4322-9d7f-b0f48c349ce5', 'EMPLOYEE-MANAGEMENT');
INSERT INTO wild_user_role (role_id, wild_user_id) VALUES
        ('7b75ac6f-5c91-4f4d-b6c2-694b71f63215','7c9e6679-7425-40de-944b-e07fc1f90ae7'),
        ('ddb98ed3-0765-4c70-9d02-2be7d18d86d2','7c9e6679-7425-40de-944b-e07fc1f90ae7'),
        ('e8f461a2-82c4-4d1c-9c0b-aa61f1b3c786','7c9e6679-7425-40de-944b-e07fc1f90ae7'),
        ('3a9c8e47-ea25-4322-9d7f-b0f48c349ce5','7c9e6679-7425-40de-944b-e07fc1f90ae7');

