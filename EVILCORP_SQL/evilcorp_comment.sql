-- Este script SQL está pensado para comentar cómo funciona cada línea de código mostrando solo 2 tablas para que no sea demasiado largo

-- Base de datos: `evilcorp`

-- Procedimientos

-- Antes de nada para que los ; de dentro del procedimiento no alteren el final del procedimiento (END;). Establecemos que $$ sera el nuevo ;.
DELIMITER $$
CREATE DEFINER=`admin`@`%` PROCEDURE `Asesinador` (IN `p_username` VARCHAR(255))
BEGIN
  -- Declaración de variables locales
  DECLARE v_user_id INT;
  DECLARE v_user_name VARCHAR(255);
  
  -- Obtener el ID y el nombre del usuario
  SELECT ID, username INTO v_user_id, v_user_name
  FROM Users
  WHERE username = p_username;
  
  -- Comprobar si el usuario no existe
  IF v_user_id IS NULL THEN
    SELECT CONCAT('No existe el usuario "', p_username, '".') AS Resultado;
  ELSE
    -- Verificar si el usuario ya está marcado como muerto
    IF EXISTS (
      SELECT 1
      FROM Users
      WHERE ID = v_user_id AND dead = TRUE
    ) THEN
      -- El usuario ya está marcado como muerto
      SELECT CONCAT('No puedes matar otra vez a "', v_user_name, '".') AS Resultado;
    ELSE
      -- Marcar al usuario como muerto en la tabla de usuarios
      UPDATE Users
      SET dead = TRUE
      WHERE ID = v_user_id;
      
      -- Mostrar mensaje de que el usuario ha muerto
      SELECT CONCAT('HA MUERTO "', v_user_name, '".') AS Resultado;
    END IF;
  END IF;
END$$


-- Funciones

-- Antes de nada para que los ; de dentro de la función no alteren el final de la función (END;). Establecemos que $$ sera el nuevo ;.
DELIMITER $$
CREATE DEFINER=`admin`@`%` FUNCTION `ObtenerIndividuoAleatorio` (`planeta_nombre` VARCHAR(255)) RETURNS VARCHAR(255)
CHARSET utf8mb4 COLLATE utf8mb4_general_ci
BEGIN
  -- Declaración de variables locales
  DECLARE individuo_nombre VARCHAR(255);
  
  -- Obtener un individuo aleatorio del planeta especificado
  SELECT username INTO individuo_nombre
  FROM Users u
  JOIN Planetas p ON u.id_planeta_origen = p.id
  WHERE p.nombre = planeta_nombre
  ORDER BY RAND()
  LIMIT 1;
  
  -- Devolver el nombre del individuo aleatorio
  RETURN individuo_nombre;
END$$

-- Restaurar el delimitador original
DELIMITER ;


-- Estructura de tabla para la tabla `CompromisedData`
CREATE TABLE `CompromisedData` (
    `ID_Data` int(11) NOT NULL,
    `Human_ID` int(11) DEFAULT NULL,
    `SocialSecurityNumber` varchar(20) DEFAULT NULL,
    `FinancialInfo` varchar(100) DEFAULT NULL,
    `MedicalHistory` varchar(100) DEFAULT NULL,
    `FamilySecrets` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `Conspirations`
CREATE TABLE `Conspirations` (
    `Conspiration_ID` int(11) NOT NULL,
    `Conspirationist` int(11) DEFAULT NULL,
    `Description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura Stand-in para la vista `contador_personas_por_planeta`
CREATE TABLE `contador_personas_por_planeta` (
    `planeta` varchar(100),
    `contador` bigint(21)
);

-- Estructura de tabla para la tabla `Crimes`
CREATE TABLE `Crimes` (
    `ID_Crime` int(11) NOT NULL,
    `Criminal` int(11) DEFAULT NULL,
    `Alias` varchar(50) DEFAULT NULL,
    `CriminalRecord` varchar(100) DEFAULT NULL,
    `Affiliations` varchar(100) DEFAULT NULL,
    `Rewards` decimal(14, 2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `DangerousTechnology`
CREATE TABLE `DangerousTechnology` (
    `ID_Technology` int(11) NOT NULL,
    `Crime` int(11) DEFAULT NULL,
    `Name` varchar(50) DEFAULT NULL,
    `Description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura Stand-in para la vista `gasto_medicamentos_por_planeta`
CREATE TABLE `gasto_medicamentos_por_planeta` (
    `planeta` varchar(100),
    `gasto_total_medicamentos` decimal(36, 2)
);

-- Estructura de tabla para la tabla `Planetas`
CREATE TABLE `Planetas` (
    `id` int(11) NOT NULL,
    `nombre` varchar(100) NOT NULL,
    `diametro_km` decimal(10, 2) DEFAULT NULL,
    `periodo_rotacion_horas` decimal(10, 2) DEFAULT NULL,
    `periodo_orbital_dias` decimal(10, 2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcado de datos para la tabla `Planetas`
INSERT INTO `Planetas` (`id`, `nombre`, `diametro_km`, `periodo_rotacion_horas`, `periodo_orbital_dias`) VALUES
(1, 'Aurora', 12000.00, 24.00, 365.00),
(2, 'Zephyr', 9500.00, 18.00, 400.00),
(3, 'Nova Prime', 18000.00, 32.00, 500.00),
(4, 'Gaia', 14000.00, 28.00, 450.00),
(5, 'Terra Nova', 11000.00, 20.00, 380.00),
(6, 'Elysium', 16000.00, 30.00, 520.00),
(7, 'Stellaris', 13000.00, 26.00, 420.00),
(8, 'Celestia', 10000.00, 16.00, 360.00),
(9, 'Nebula', 15000.00, 34.00, 550.00),
(10, 'Lumina', 17000.00, 36.00, 580.00);
-- Estructura de tabla para la tabla `PotentialVictims`
CREATE TABLE `PotentialVictims` (
    `ID_Victim` int(11) NOT NULL,
    `Crime` int(11) DEFAULT NULL,
    `Human_ID` int(11) DEFAULT NULL,
    `DailyRoutine` varchar(2000) DEFAULT NULL,
    `LocationsFrequent` varchar(200) DEFAULT NULL,
    `PersonalRelationships` varchar(200) DEFAULT NULL,
    `EmotionalWeaknesses` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `Propiedades`
CREATE TABLE `Propiedades` (
    `ID` int(11) NOT NULL,
    `Direccion` varchar(100) DEFAULT NULL,
    `Metros_cuadrados` int(11) DEFAULT NULL,
    `Propietario` int(11) DEFAULT NULL,
    `build_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `SocialProfiles`
CREATE TABLE `SocialProfiles` (
    `ID_Profile` int(11) NOT NULL,
    `Human_ID` int(11) DEFAULT NULL,
    `SocialURL` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `Transactions`
CREATE TABLE `Transactions` (
    `ID_Transaction` int(11) NOT NULL,
    `from_user` int(11) DEFAULT NULL,
    `to_user` int(11) DEFAULT NULL,
    `amount` decimal(14, 2) DEFAULT NULL,
    `Concept` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcado de datos para la tabla `Transactions`
INSERT INTO `Transactions` (`ID_Transaction`, `from_user`, `to_user`, `amount`, `Concept`) VALUES
(1, 13, 13, 205.90, 'Compra de medicamento 30'),
(2, 21, 16, 815.66, 'Compra de medicamento 91'),
(3, 4, 20, 94.03, 'Compra de medicamento 78'),
(4, 14, 18, 184.30, 'Compra de medicamento 47'),
(5, 18, 14, 599.25, 'Compra de medicamento 19'),
(8, 4, 6, 614.06, 'Compra aleatoria 41'),
(9, 6, 21, 83.35, 'Compra aleatoria 53'),
(10, 9, 10, 849.37, 'Compra aleatoria 1'),
(11, 12, 14, 583.73, 'Compra aleatoria 99'),
(12, 6, 6, 420.90, 'Compra aleatoria 40');

-- Estructura de tabla para la tabla `UserRelations`
CREATE TABLE `UserRelations` (
    `ID` int(11) NOT NULL,
    `User_ID` int(11) DEFAULT NULL,
    `RelatedUser_ID` int(11) DEFAULT NULL,
    `RelationType` varchar(100) DEFAULT NULL,
    `StartDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Estructura de tabla para la tabla `Users`
CREATE TABLE `Users` (
    `ID` int(11) NOT NULL,
    `username` varchar(255) DEFAULT NULL,
    `password_hash_SHA256` varchar(255) DEFAULT NULL,
    `born_date` date DEFAULT NULL,
    `govnif` varchar(20) DEFAULT NULL,
    `skin_color` varchar(50) DEFAULT NULL,
    `bank_savings` decimal(10, 2) DEFAULT NULL,
    `id_planeta_origen` int(11) NOT NULL,
    `dead` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
--
-- Volcado de datos para la tabla `Users`
--
INSERT INTO `Users` (
  `ID`, `username`, `password_hash_SHA256`, 
  `born_date`, `govnif`, `skin_color`, 
  `bank_savings`, `id_planeta_origen`, 
  `dead`
) 
VALUES 
  (
    1, 'root', '2f8cc8e617a3b8b37a7875bbd04517082baf415ff8b82d308e67b8004e6925c2', 
    '1971-06-28', 'X1234567A', 'white', 
    0.00, 0, 0
  ), 
  (
    2, 'Emma Smith', '', '1990-05-15', 
    '123456789', 'Blanco', 5000.00, 1, 
    0
  ), 
  (
    3, 'Liam Johnson', '', '1992-08-22', 
    '987654321', 'Negro', 8000.00, 1, 
    0
  ), 
  (
    4, 'Olivia Brown', '', '1985-07-20', 
    '246813579', 'Muy blanco', 3000.00, 
    2, 0
  ), 
  (
    5, 'Noah Davis', '', '1988-11-12', 
    '975318642', 'Muy negro', 6000.00, 
    2, 0
  ), 
  (
    6, 'Sophia Wilson', '', '1994-05-28', 
    '531289764', 'Amarillo', 4500.00, 
    3, 0
  ), 
  (
    7, 'William Johnson', '', '1996-09-03', 
    '879645123', 'Azul', 5500.00, 3, 0
  ), 
  (
    8, 'Isabella Taylor', '', '1982-04-17', 
    '365489712', 'Blanco', 7000.00, 4, 
    0
  ), 
  (
    9, 'James Lee', '', '1984-08-25', '947123658', 
    'Negro', 9000.00, 4, 0
  ), 
  (
    10, 'Sophia Martin', '', '1991-03-07', 
    '213456789', 'Muy negro', 4000.00, 
    5, 0
  ), 
  (
    11, 'Logan Anderson', '', '1993-06-19', 
    '987654321', 'Muy blanco', 7500.00, 
    5, 0
  ), 
  (
    12, 'Mia Martinez', '', '1995-02-11', 
    '135792468', 'Amarillo', 5500.00, 
    6, 0
  ), 
  (
    13, 'Benjamin Thompson', '', '1997-07-29', 
    '864209753', 'Blanco', 7000.00, 6, 
    0
  ), 
  (
    14, 'Ava Harris', '', '1989-10-01', 
    '987654321', 'Muy blanco', 4000.00, 
    7, 0
  ), 
  (
    15, 'Henry Clark', '', '1991-12-14', 
    '135792468', 'Negro', 8000.00, 7, 
    0
  ), 
  (
    16, 'Charlotte Turner', '', '1983-09-25', 
    '864209753', 'Muy negro', 6000.00, 
    8, 0
  ), 
  (
    17, 'Daniel Hill', '', '1986-01-05', 
    '123456789', 'Azul', 5500.00, 8, 0
  ), 
  (
    18, 'Amelia Murphy', '', '1992-06-10', 
    '135792468', 'Muy blanco', 4500.00, 
    9, 0
  ), 
  (
    19, 'Alexander Cooper', '', '1994-09-23', 
    '987654321', 'Invisible', 7000.00, 
    9, 0
  ), 
  (
    20, 'Harper Moore', '', '1996-04-05', 
    '123456789', 'Blanco', 5000.00, 10, 
    0
  ), 
  (
    21, 'Jackson Stewart', '', '1998-07-18', 
    '864209753', 'Negro', 8000.00, 10, 
    0
  ), 
  (
    22, 'usuario1', '', '1990-01-01', '123456789', 
    'Blanco', 1000.00, 1, 0
  ), 
  (
    23, 'usuario2', '', '1991-02-02', '987654321', 
    'Negro', 1500.00, 1, 0
  ), 
  (
    24, 'usuario3', '', '1992-03-03', '456789123', 
    'Muy blanco', 2000.00, 1, 0
  ), 
  (
    25, 'usuario4', '', '1993-04-04', '321654987', 
    'Amarillo', 1200.00, 1, 0
  ), 
  (
    26, 'usuario5', '', '1994-05-05', '789123456', 
    'Azul', 1800.00, 1, 0
  ), 
  (
    27, 'usuario6', '', '1995-06-06', '654987321', 
    'Invisible', 2500.00, 1, 0
  ), 
  (
    28, 'usuario7', '', '1996-07-07', '987123654', 
    'Blanco', 1700.00, 1, 1
  ), 
  (
    29, 'usuario8', '', '1997-08-08', '456321789', 
    'Negro', 1400.00, 1, 0
  ), 
  (
    30, 'usuario9', '', '1998-09-09', '321789654', 
    'Muy negro', 2200.00, 1, 0
  ), 
  (
    31, 'usuario10', '', '1999-10-10', 
    '789456123', 'Muy blanco', 1900.00, 
    1, 0
  );
-- --------------------------------------------------------
--
-- Estructura para la vista `contador_personas_por_planeta`
--
DROP 
  TABLE IF EXISTS `contador_personas_por_planeta`;
CREATE ALGORITHM = UNDEFINED DEFINER = `admin` @`%` SQL SECURITY DEFINER VIEW `contador_personas_por_planeta` AS 
SELECT 
  `pl`.`nombre` AS `planeta`, 
  count(0) AS `contador` 
FROM 
  (
    `Users` `u` 
    join `Planetas` `pl` on(
      `u`.`id_planeta_origen` = `pl`.`id`
    )
  ) 
GROUP BY 
  `pl`.`nombre`;
-- --------------------------------------------------------
--
-- Estructura para la vista `gasto_medicamentos_por_planeta`
--
DROP 
  TABLE IF EXISTS `gasto_medicamentos_por_planeta`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root` @`localhost` SQL SECURITY DEFINER VIEW `gasto_medicamentos_por_planeta` AS 
SELECT 
  `p`.`nombre` AS `planeta`, 
  sum(`t`.`amount`) AS `gasto_total_medicamentos` 
FROM 
  (
    (
      `Planetas` `p` 
      join `Users` `u` on(
        `p`.`id` = `u`.`id_planeta_origen`
      )
    ) 
    join `Transactions` `t` on(
      `u`.`ID` = `t`.`from_user` 
      or `u`.`ID` = `t`.`to_user`
    )
  ) 
WHERE 
  `t`.`Concept` like '%medicamento%' 
GROUP BY 
  `p`.`id`;
--
-- Índices para tablas volcadas
--
--
-- Indices de la tabla `CompromisedData`
--
ALTER TABLE 
  `CompromisedData` 
ADD 
  PRIMARY KEY (`ID_Data`), 
ADD 
  KEY `Human_ID` (`Human_ID`);
--
-- Indices de la tabla `Conspirations`
--
ALTER TABLE 
  `Conspirations` 
ADD 
  PRIMARY KEY (`Conspiration_ID`), 
ADD 
  KEY `Conspirationist` (`Conspirationist`);
--
-- Indices de la tabla `Crimes`
--
ALTER TABLE 
  `Crimes` 
ADD 
  PRIMARY KEY (`ID_Crime`), 
ADD 
  KEY `Criminal` (`Criminal`);
--
-- Indices de la tabla `DangerousTechnology`
--
ALTER TABLE 
  `DangerousTechnology` 
ADD 
  PRIMARY KEY (`ID_Technology`), 
ADD 
  KEY `Crime` (`Crime`);
--
-- Indices de la tabla `Planetas`
--
ALTER TABLE 
  `Planetas` 
ADD 
  PRIMARY KEY (`id`);
--
-- Indices de la tabla `PotentialVictims`
--
ALTER TABLE 
  `PotentialVictims` 
ADD 
  PRIMARY KEY (`ID_Victim`), 
ADD 
  KEY `Crime` (`Crime`), 
ADD 
  KEY `Human_ID` (`Human_ID`);
--
-- Indices de la tabla `Propiedades`
--
ALTER TABLE 
  `Propiedades` 
ADD 
  PRIMARY KEY (`ID`), 
ADD 
  KEY `Propietario` (`Propietario`);
--
-- Indices de la tabla `SocialProfiles`
--
ALTER TABLE 
  `SocialProfiles` 
ADD 
  PRIMARY KEY (`ID_Profile`), 
ADD 
  KEY `Human_ID` (`Human_ID`);
--
-- Indices de la tabla `Transactions`
--
ALTER TABLE 
  `Transactions` 
ADD 
  PRIMARY KEY (`ID_Transaction`), 
ADD 
  KEY `from_user` (`from_user`), 
ADD 
  KEY `to_user` (`to_user`);
--
-- Indices de la tabla `UserRelations`
--
ALTER TABLE 
  `UserRelations` 
ADD 
  PRIMARY KEY (`ID`), 
ADD 
  KEY `User_ID` (`User_ID`), 
ADD 
  KEY `RelatedUser_ID` (`RelatedUser_ID`);
--
-- Indices de la tabla `Users`
--
ALTER TABLE 
  `Users` 
ADD 
  PRIMARY KEY (`ID`), 
ADD 
  KEY `id_planeta_origen` (`id_planeta_origen`);
--
-- AUTO_INCREMENT de las tablas volcadas
--
--
-- AUTO_INCREMENT de la tabla `CompromisedData`
--
ALTER TABLE 
  `CompromisedData` MODIFY `ID_Data` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Conspirations`
--
ALTER TABLE 
  `Conspirations` MODIFY `Conspiration_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Crimes`
--
ALTER TABLE 
  `Crimes` MODIFY `ID_Crime` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `DangerousTechnology`
--
ALTER TABLE 
  `DangerousTechnology` MODIFY `ID_Technology` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Planetas`
--
ALTER TABLE 
  `Planetas` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, 
  AUTO_INCREMENT = 11;
--
-- AUTO_INCREMENT de la tabla `PotentialVictims`
--
ALTER TABLE 
  `PotentialVictims` MODIFY `ID_Victim` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Propiedades`
--
ALTER TABLE 
  `Propiedades` MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `SocialProfiles`
--
ALTER TABLE 
  `SocialProfiles` MODIFY `ID_Profile` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Transactions`
--
ALTER TABLE 
  `Transactions` MODIFY `ID_Transaction` int(11) NOT NULL AUTO_INCREMENT, 
  AUTO_INCREMENT = 15;
--
-- AUTO_INCREMENT de la tabla `UserRelations`
--
ALTER TABLE 
  `UserRelations` MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Users`
--
ALTER TABLE 
  `Users` MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, 
  AUTO_INCREMENT = 32;
--
-- Restricciones para tablas volcadas
--
--
-- Filtros para la tabla `CompromisedData`
--
ALTER TABLE 
  `CompromisedData` 
ADD 
  CONSTRAINT `CompromisedData_ibfk_1` FOREIGN KEY (`Human_ID`) REFERENCES `Users` (`ID`);
--
-- Filtros para la tabla `Conspirations`
--
ALTER TABLE 
  `Conspirations` 
ADD 
  CONSTRAINT `Conspirations_ibfk_1` FOREIGN KEY (`Conspirationist`) REFERENCES `Users` (`ID`);
--
-- Filtros para la tabla `Crimes`
--
ALTER TABLE 
  `Crimes` 
ADD 
  CONSTRAINT `Crimes_ibfk_1` FOREIGN KEY (`Criminal`) REFERENCES `Users` (`ID`);
--
-- Filtros para la tabla `DangerousTechnology`
--
ALTER TABLE 
  `DangerousTechnology` 
ADD 
  CONSTRAINT `DangerousTechnology_ibfk_1` FOREIGN KEY (`Crime`) REFERENCES `Crimes` (`ID_Crime`);
--
-- Filtros para la tabla `PotentialVictims`
--
ALTER TABLE 
  `PotentialVictims` 
ADD 
  CONSTRAINT `PotentialVictims_ibfk_1` FOREIGN KEY (`Crime`) REFERENCES `Crimes` (`ID_Crime`), 
ADD 
  CONSTRAINT `PotentialVictims_ibfk_2` FOREIGN KEY (`Human_ID`) REFERENCES `Users` (`ID`);
--
-- Filtros para la tabla `Propiedades`
--
ALTER TABLE 
  `Propiedades` 
ADD 
  CONSTRAINT `Propiedades_ibfk_1` FOREIGN KEY (`Propietario`) REFERENCES `Users` (`ID`);
--
-- Filtros para la tabla `SocialProfiles`
--
ALTER TABLE 
  `SocialProfiles` 
ADD 
  CONSTRAINT `SocialProfiles_ibfk_1` FOREIGN KEY (`Human_ID`) REFERENCES `Users` (`ID`);
--
-- Filtros para la tabla `Transactions`
--
ALTER TABLE 
  `Transactions` 
ADD 
  CONSTRAINT `Transactions_ibfk_1` FOREIGN KEY (`from_user`) REFERENCES `Users` (`ID`), 
ADD 
  CONSTRAINT `Transactions_ibfk_2` FOREIGN KEY (`to_user`) REFERENCES `Users` (`ID`);
--
-- Filtros para la tabla `UserRelations`
--
ALTER TABLE 
  `UserRelations` 
ADD 
  CONSTRAINT `UserRelations_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `Users` (`ID`), 
ADD 
  CONSTRAINT `UserRelations_ibfk_2` FOREIGN KEY (`RelatedUser_ID`) REFERENCES `Users` (`ID`);
--
-- Filtros para la tabla `Users`
--
ALTER TABLE 
  `Users` 
ADD 
  CONSTRAINT `FK_Users_Planetas` FOREIGN KEY (`id_planeta_origen`) REFERENCES `Planetas` (`id`);
COMMIT;
