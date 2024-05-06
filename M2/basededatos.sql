CREATE TABLE civilization_stats
(
  civilization_id NUMBER(10) NOT NULL PRIMARY KEY,
  name VARCHAR2(50),
  wood_amount NUMBER(10) ,
  iron_amount NUMBER(10) ,
  food_amount NUMBER(10) ,
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
  unit_id VARCHAR2(50) NOT NULL,
  type_unit VARCHAR2(10), 
  armor NUMBER(10),
  base_damage NUMBER(10), 
  experience NUMBER(10),
  sanctified VARCHAR2(3), 
  FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id),
  CONSTRAINT pk_civi_unit PRIMARY KEY (civilization_id, unit_id),
  CONSTRAINT tipesunit CHECK(type_unit IN('Swordsman', 'Spearman', 'Crossbow', 'Cannon'))
);

CREATE TABLE special_units_stats
(
  civilization_id NUMBER(10) NOT NULL, 
  unit_id VARCHAR2(50),
  type_unit VARCHAR2(10), 
  armor NUMBER(10),
  base_damage NUMBER(10), 
  experience NUMBER(10),
  FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id),
  CONSTRAINT pk_primary_special PRIMARY KEY (civilization_id, unit_id),
  CONSTRAINT typeunitspecial CHECK(type_unit IN('Magican', 'Priest'))

);

CREATE TABLE defense_unit_stats
(
  civilization_id NUMBER(10) NOT NULL, 
  unit_id VARCHAR2(50),
  type_unit VARCHAR2(10), 
  armor NUMBER(10),
  base_damage NUMBER(10), 
  experience NUMBER(10),
  sanctified VARCHAR2(3),
  FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id),
  CONSTRAINT pk_primary_defense PRIMARY KEY (civilization_id, unit_id),
  CONSTRAINT type_unit_defense CHECK (type_unit IN('ArrowTower', 'Catapult','RocketLauncherTower'))
);