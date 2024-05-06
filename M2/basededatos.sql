CREATE TABLE civilization_stats
(
  civilization_id NUMBER(10) NOT NULL PRIMARY KEY,
  name VARCHAR2(50) NOT NULL,
  wood_amount NUMBER(10) NOT NULL,
  iron_amount NUMBER(10) NOT NULL,
  food_amount NUMBER(10) NOT NULL,
  mana_amount NUMBER(10), 

  magicTower_counter NUMBER(10), 
  church_counter NUMBER(10), 
  farm_counter NUMBER(10), 
  smithy_counter NUMBER(10), 
  carpentry_counter NUMBER(10), 

  technology_defense_level NUMBER(3),
  technology_attack_level NUMBER(10), 
  battles_counter NUMBER(10) 
);

CREATE TABLE attack_units_stats
(
  civilization_id NUMBER(10) NOT NULL, 
  unit_id VARCHAR2(50) NOT NULL PRIMARY KEY,
  type ENUM('Swordsman', 'Spearman', 'Crossbow', 'Cannon'), 
  armor NUMBER(10),
  base_damage NUMBER(10), 
  experience NUMBER(10),
  sanctified VARCHAR2(3), 
  FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id) 
);

CREATE TABLE special_units_Stats
(
  civilization_id NUMBER(10) NOT NULL, 
  unit_id VARCHAR2(50) NOT NULL PRIMARY KEY,
  type ENUM('Magican', 'Priest'), 
  armor NUMBER(10),
  base_damage NUMBER(10), 
  experience NUMBER(10),
  FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id) 
);

CREATE TABLE defense_unit_stats
(
  civilization_id NUMBER(10) NOT NULL, 
  unit_id VARCHAR2(50) NOT NULL PRIMARY KEY,
  type ENUM('ArrowTower', 'Catapult','RocketLauncherTower'), 
  armor NUMBER(10),
  base_damage NUMBER(10), 
  experience NUMBER(10),
  sanctified VARCHAR2(3),
  FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id) 
);