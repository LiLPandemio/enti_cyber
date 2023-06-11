-- DROP DATABASE IF EXISTS
DROP DATABASE IF EXISTS evilcorp;

-- CREATE DATABASE
CREATE DATABASE evilcorp;

-- USE DATABASE
USE evilcorp;

-- DROP PROCEDURE IF EXISTS
DROP PROCEDURE IF EXISTS Asesinador;

-- DROP FUNCTION IF EXISTS
DROP FUNCTION IF EXISTS ObtenerIndividuoAleatorio;

-- DROP VIEW IF EXISTS
DROP VIEW IF EXISTS contador_personas_por_planeta;
DROP VIEW IF EXISTS gasto_medicamentos_por_planeta;

-- DROP TABLE IF EXISTS
DROP TABLE IF EXISTS CompromisedData;
DROP TABLE IF EXISTS Conspirations;
DROP TABLE IF EXISTS contador_personas_por_planeta;
DROP TABLE IF EXISTS Crimes;
DROP TABLE IF EXISTS DangerousTechnology;
DROP TABLE IF EXISTS gasto_medicamentos_por_planeta;
DROP TABLE IF EXISTS PotentialVictims;
DROP TABLE IF EXISTS Propiedades;
DROP TABLE IF EXISTS SocialProfiles;
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS UserRelations;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Planetas;

-- TABLES

CREATE TABLE `Planetas` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(100) NOT NULL,
    `diametro_km` decimal(10, 2) DEFAULT NULL,
    `periodo_rotacion_horas` decimal(10, 2) DEFAULT NULL,
    `periodo_orbital_dias` decimal(10, 2) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Users` (
    `ID` int (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` varchar(255) DEFAULT NULL,
    `password_hash_SHA256` varchar(255) DEFAULT NULL,
    `born_date` date DEFAULT NULL,
    `govnif` varchar(20) DEFAULT NULL,
    `skin_color` varchar(50) DEFAULT NULL,
    `bank_savings` decimal(10, 2) DEFAULT NULL,
    `id_planeta_origen` int (11) NOT NULL,
    `dead` tinyint (1) DEFAULT 0,
    FOREIGN KEY (`id_planeta_origen`) REFERENCES `Planetas` (`id`)
);


CREATE TABLE `UserRelations` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `User_ID` int(11) DEFAULT NULL,
    `RelatedUser_ID` int(11) DEFAULT NULL,
    `RelationType` varchar(100) DEFAULT NULL,
    `StartDate` date DEFAULT NULL,
    PRIMARY KEY (`ID`),
    FOREIGN KEY (`User_ID`) REFERENCES `Users` (`ID`),
    FOREIGN KEY (`RelatedUser_ID`) REFERENCES `Users` (`ID`)
);

CREATE TABLE `CompromisedData` (
    `ID_Data` int(11) NOT NULL AUTO_INCREMENT,
    `Human_ID` int(11) DEFAULT NULL,
    `SocialSecurityNumber` varchar(20) DEFAULT NULL,
    `FinancialInfo` varchar(100) DEFAULT NULL,
    `MedicalHistory` varchar(100) DEFAULT NULL,
    `FamilySecrets` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`ID_Data`),
    KEY `Human_ID` (`Human_ID`),
    FOREIGN KEY (`Human_ID`) REFERENCES `Users` (`ID`)
);

CREATE TABLE `Conspirations` (
    `Conspiration_ID` int(11) NOT NULL AUTO_INCREMENT,
    `Conspirationist` int(11) DEFAULT NULL,
    `Description` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`Conspiration_ID`),
    KEY `Conspirationist` (`Conspirationist`),
    FOREIGN KEY (`Conspirationist`) REFERENCES `Users` (`ID`)
);

CREATE TABLE `PotentialVictims` (
    `ID_Victim` int(11) NOT NULL AUTO_INCREMENT,
    `CrimeID` int(11) DEFAULT NULL,
    `Human_ID` int(11) DEFAULT NULL,
    `DailyRoutine` varchar(2000) DEFAULT NULL,
    `LocationsFrequent` varchar(200) DEFAULT NULL,
    `PersonalRelationships` varchar(200) DEFAULT NULL,
    `EmotionalWeaknesses` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`ID_Victim`),
    FOREIGN KEY (`Human_ID`) REFERENCES `Users` (`ID`)
);

CREATE TABLE `Crimes` (
    `ID_Crime` int(11) NOT NULL AUTO_INCREMENT,
    `Criminal` int(11) DEFAULT NULL,
    `Alias` varchar(50) DEFAULT NULL,
    `CriminalRecord` varchar(100) DEFAULT NULL,
    `Affiliations` varchar(100) DEFAULT NULL,
    `Rewards` decimal(14, 2) DEFAULT NULL,
    PRIMARY KEY (`ID_Crime`),
    FOREIGN KEY (`Criminal`) REFERENCES `Users` (`ID`)
);

CREATE TABLE `DangerousTechnology` (
    `ID_Technology` int(11) NOT NULL AUTO_INCREMENT,
    `Crime` int(11) DEFAULT NULL,
    `Name` varchar(50) DEFAULT NULL,
    `Description` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`ID_Technology`),
    FOREIGN KEY (`Crime`) REFERENCES `Crimes` (`ID_Crime`)
);

CREATE TABLE `Propiedades` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `Direccion` varchar(100) DEFAULT NULL,
    `Metros_cuadrados` int(11) DEFAULT NULL,
    `Propietario` int(11) DEFAULT NULL,
    `build_date` date DEFAULT NULL,
    PRIMARY KEY (`ID`),
    FOREIGN KEY (`Propietario`) REFERENCES `Users` (`ID`)
);

CREATE TABLE `SocialProfiles` (
    `ID_Profile` int(11) NOT NULL AUTO_INCREMENT,
    `Human_ID` int(11) DEFAULT NULL,
    `SocialURL` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`ID_Profile`),
    FOREIGN KEY (`Human_ID`) REFERENCES `Users` (`ID`)
);

CREATE TABLE `Transactions` (
    `ID_Transaction` int(11) NOT NULL AUTO_INCREMENT,
    `from_user` int(11) DEFAULT NULL,
    `to_user` int(11) DEFAULT NULL,
    `amount` decimal(14, 2) DEFAULT NULL,
    `Concept` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`ID_Transaction`),
    FOREIGN KEY (`from_user`) REFERENCES `Users` (`ID`),
    FOREIGN KEY (`to_user`) REFERENCES `Users` (`ID`)
);

-- INSERTS
INSERT INTO `Planetas` (`id`, `nombre`, `diametro_km`, `periodo_rotacion_horas`, `periodo_orbital_dias`) VALUES
    (NULL, 'Aurora', 12000.00, 24.00, 365.00),
    (NULL, 'Zephyr', 9500.00, 18.00, 400.00),
    (NULL, 'Nova Prime', 18000.00, 32.00, 500.00),
    (NULL, 'Gaia', 14000.00, 28.00, 450.00),
    (NULL, 'Terra Nova', 11000.00, 20.00, 380.00),
    (NULL, 'Elysium', 16000.00, 30.00, 520.00),
    (NULL, 'Stellaris', 13000.00, 26.00, 420.00),
    (NULL, 'Celestia', 10000.00, 16.00, 360.00),
    (NULL, 'Nebula', 15000.00, 34.00, 550.00),
    (NULL, 'Lumina', 17000.00, 36.00, 580.00);

INSERT INTO `Users` (`ID`, `username`, `password_hash_SHA256`, `born_date`, `govnif`, `skin_color`, `bank_savings`, `id_planeta_origen`, `dead`) VALUES
    (NULL, 'root', '2f8cc8e617a3b8b37a7875bbd04517082baf415ff8b82d308e67b8004e6925c2', '1971-06-28', 'X1234567A', 'white', 0.00, 1, 0),
    (NULL, 'Emma Smith', '', '1990-05-15', '123456789', 'Blanco', 5000.00, 1, 0),
    (NULL, 'Liam Johnson', '', '1992-08-22', '987654321', 'Negro', 8000.00, 2, 0),
    (NULL, 'Olivia Brown', '', '1985-07-20', '246813579', 'Muy blanco', 3000.00, 3, 0);

INSERT INTO `Transactions` (`ID_Transaction`, `from_user`, `to_user`, `amount`, `Concept`) VALUES
    (NULL, 3, 2, 205.90, 'Compra de medicamento 30'),
    (NULL, 3, 2, 815.66, 'Compra de medicamento 91'),
    (NULL, 3, 2, 94.03, 'Compra de medicamento 78'),
    (NULL, 3, 2, 184.30, 'Compra de medicamento 47'),
    (NULL, 3, 2, 599.25, 'Compra de medicamento 19'),
    (NULL, 3, 2, 614.06, 'Compra aleatoria 41'),
    (NULL, 3, 2, 83.35, 'Compra aleatoria 53'),
    (NULL, 3, 2, 849.37, 'Compra aleatoria 1'),
    (NULL, 3, 2, 583.73, 'Compra aleatoria 99'),
    (NULL, 3, 2, 420.90, 'Compra aleatoria 40');

-- VIEWS
CREATE VIEW contador_personas_por_planeta AS
SELECT p.nombre AS planeta, COUNT(u.ID) AS contador
FROM Planetas p
LEFT JOIN Users u ON u.id_planeta_origen = p.id
GROUP BY p.id;

CREATE VIEW gasto_medicamentos_por_planeta AS
SELECT p.nombre AS planeta, SUM(t.amount) AS gasto_total_medicamentos
FROM Planetas p
JOIN Users u ON u.id_planeta_origen = p.id
JOIN Transactions t ON t.to_user = u.ID AND t.Concept LIKE 'Compra de medicamento%'
GROUP BY p.id;


DELIMITER $$
CREATE PROCEDURE `Asesinador`(IN `p_username` VARCHAR(255))
BEGIN
    DECLARE v_user_id INT;
    DECLARE v_user_name VARCHAR(255);

    -- Obtener el ID y el nombre del usuario
    SELECT ID, username INTO v_user_id, v_user_name
    FROM Users
    WHERE username = p_username;

    IF v_user_id IS NULL THEN -- El usuario no existe
        SELECT CONCAT('No existe el usuario "', p_username, '".') AS Resultado;
    ELSE
        -- Verificar si el usuario ya está marcado como muerto
        IF EXISTS(SELECT 1 FROM Users WHERE ID = v_user_id AND dead = true) THEN
            SELECT CONCAT('No puedes matar otra vez a "', v_user_name, '".') AS Resultado;
        ELSE
            -- Marcar al usuario como muerto en la tabla de usuarios
            UPDATE Users SET dead = true WHERE ID = v_user_id;
            SELECT CONCAT('HA MUERTO "', v_user_name, '".') AS Resultado;
        END IF;
    END IF;
END$$
DELIMITER ;

-- CREATE FUNCTION
DELIMITER $$
CREATE FUNCTION ObtenerIndividuoAleatorio() RETURNS VARCHAR(255)
BEGIN
    DECLARE total INT;
    DECLARE random_username VARCHAR(255);
    
    -- Obtener el total de usuarios en la tabla Users
    SELECT COUNT(*) INTO total FROM Users;
    
    -- Generar un número aleatorio entre 1 y el total de usuarios
    SET @random_id := FLOOR(RAND() * total) + 1;
    
    -- Obtener el nombre de usuario del usuario seleccionado
    SELECT username INTO random_username FROM Users WHERE id = @random_id;
    
    -- Devolver el nombre de usuario seleccionado
    RETURN random_username;
END$$
DELIMITER ;
