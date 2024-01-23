-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 23 jan. 2024 à 16:40
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `reservationresto`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `mail` varchar(250) NOT NULL,
  `nom` varchar(250) NOT NULL,
  `type` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `tel`, `mail`, `nom`, `type`) VALUES
(53, '0651242510', 'maeva@test.fr', 'test test', 'particulier'),
(54, '0651242510', 'mail@gmail.com', 'test test', 'particulier'),
(55, '123456789', 'john.doe@example.com', 'Doe John', 'particulier'),
(56, '987654321', 'jane.smith@example.com', 'Smith John', 'particulier'),
(57, '555111222', 'company@example.com', 'ABC Corp', 'entreprise'),
(58, '999888777', 'anothercompany@example.com', 'XYZ Ltd', 'entreprise'),
(59, '111222333', 'alice.jones@example.com', 'Jones Jones', 'particulier'),
(60, '444555666', 'company2@example.com', 'DEF Inc', 'entreprise'),
(61, '777888999', 'bob.anderson@example.com', 'Anderson Julian', 'particulier'),
(62, '333222111', 'company3@example.com', 'GHI Ltd', 'entreprise'),
(63, '666777888', 'sara.miller@example.com', 'Miller Mac', 'particulier'),
(64, '999000111', 'company4@example.com', 'JKL Co', 'entreprise'),
(65, '111222333', 'michael.wilson@example.com', 'Wilson Will', 'particulier'),
(66, '444555666', 'company5@example.com', 'MNO Corp', 'entreprise'),
(67, '777888999', 'emily.white@example.com', 'White Walter', 'particulier'),
(68, '333222111', 'company6@example.com', 'PQR Ltd', 'entreprise'),
(69, '666777888', 'ryan.thomas@example.com', 'Thomas TheTrain', 'particulier'),
(70, '999000111', 'company7@example.com', 'STU Co', 'entreprise'),
(71, '111222333', 'olivia.hall@example.com', 'Hall Malcolm', 'particulier'),
(72, '444555666', 'company8@example.com', 'VWX Corp', 'entreprise'),
(73, '777888999', 'david.clark@example.com', 'Clark Kent', 'particulier'),
(74, '333222111', 'company9@example.com', 'YZA Ltd', 'entreprise'),
(75, '666777888', 'natalie.brown@example.com', 'Brown James', 'particulier'),
(76, '999000111', 'company10@example.com', 'ABC Co', 'entreprise'),
(77, '111222333', 'alexander.wright@example.com', 'Wright Phoenix', 'particulier'),
(78, '444555666', 'company11@example.com', 'DEF Corp', 'entreprise'),
(79, '777888999', 'victoria.green@example.com', 'Green Golf', 'particulier'),
(80, '333222111', 'company12@example.com', 'GHI Ltd', 'entreprise'),
(81, '666777888', 'eric.ward@example.com', 'Ward FlashQ', 'particulier'),
(82, '999000111', 'company13@example.com', 'JKL Co', 'entreprise');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `nb_personne` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `is_validated` tinyint(1) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_service` int(11) NOT NULL,
  `date_reservation` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `nb_personne`, `type`, `is_validated`, `id_client`, `id_service`, `date_reservation`) VALUES
(75, 5, 'particulier', 1, 53, 10, '2024-01-20'),
(76, 2, 'entreprise', 1, 55, 11, '2024-01-20'),
(77, 3, 'particulier', 1, 54, 12, '2024-01-20'),
(78, 8, 'entreprise', 1, 56, 13, '2024-01-21'),
(79, 6, 'particulier', 1, 57, 10, '2024-01-21'),
(80, 4, 'entreprise', 1, 58, 11, '2024-01-21'),
(81, 2, 'particulier', 1, 59, 12, '2024-01-22'),
(82, 7, 'entreprise', 1, 60, 13, '2024-01-22'),
(83, 5, 'particulier', 1, 61, 10, '2024-01-22'),
(84, 3, 'entreprise', 1, 62, 11, '2024-01-23'),
(85, 4, 'particulier', 1, 63, 12, '2024-01-23'),
(86, 6, 'entreprise', 1, 64, 13, '2024-01-23'),
(87, 3, 'particulier', 1, 65, 10, '2024-01-24'),
(88, 5, 'entreprise', 1, 66, 11, '2024-01-24'),
(89, 7, 'particulier', 1, 67, 12, '2024-01-24'),
(90, 8, 'entreprise', 1, 68, 13, '2024-01-25'),
(91, 4, 'particulier', 1, 69, 10, '2024-01-25'),
(92, 6, 'entreprise', 1, 70, 11, '2024-01-25'),
(93, 5, 'particulier', 1, 71, 12, '2024-01-26'),
(94, 3, 'entreprise', 1, 72, 13, '2024-01-26'),
(95, 2, 'particulier', 1, 73, 10, '2024-01-26'),
(96, 7, 'entreprise', 1, 74, 11, '2024-01-27'),
(97, 4, 'particulier', 1, 75, 12, '2024-01-27'),
(98, 6, 'entreprise', 1, 76, 13, '2024-01-27'),
(99, 5, 'particulier', 1, 77, 10, '2024-01-28'),
(100, 6, 'entreprise', 1, 78, 11, '2024-01-28'),
(101, 3, 'particulier', 1, 79, 12, '2024-01-28'),
(102, 7, 'entreprise', 1, 80, 13, '2024-01-29'),
(103, 2, 'particulier', 1, 81, 10, '2024-01-29'),
(104, 4, 'entreprise', 1, 82, 11, '2024-01-29');

-- --------------------------------------------------------

--
-- Structure de la table `reservation_tables`
--

CREATE TABLE `reservation_tables` (
  `id` int(11) NOT NULL,
  `id_tables` int(11) NOT NULL,
  `id_reservation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE `service` (
  `id_service` int(11) NOT NULL,
  `ordre_service` int(11) NOT NULL,
  `description` text NOT NULL,
  `horaire_service` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`id_service`, `ordre_service`, `description`, `horaire_service`) VALUES
(10, 3, '1er service du soir', '19h00'),
(11, 4, '2eme service du soir', '20h30'),
(12, 1, '1er service du midi', '12h00'),
(13, 2, '2eme service du midi', '13h00');

-- --------------------------------------------------------

--
-- Structure de la table `tables`
--

CREATE TABLE `tables` (
  `id_tables` int(11) NOT NULL,
  `nb_chaise` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tables`
--

INSERT INTO `tables` (`id_tables`, `nb_chaise`) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `FK_Client` (`id_client`),
  ADD KEY `FK_Service` (`id_service`);

--
-- Index pour la table `reservation_tables`
--
ALTER TABLE `reservation_tables`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_tables` (`id_tables`),
  ADD KEY `FK_Reservation` (`id_reservation`);

--
-- Index pour la table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id_service`);

--
-- Index pour la table `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`id_tables`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT pour la table `reservation_tables`
--
ALTER TABLE `reservation_tables`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `service`
--
ALTER TABLE `service`
  MODIFY `id_service` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `tables`
--
ALTER TABLE `tables`
  MODIFY `id_tables` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_Client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `FK_Service` FOREIGN KEY (`id_service`) REFERENCES `service` (`id_service`);

--
-- Contraintes pour la table `reservation_tables`
--
ALTER TABLE `reservation_tables`
  ADD CONSTRAINT `FK_Reservation` FOREIGN KEY (`id_reservation`) REFERENCES `reservation` (`id_reservation`),
  ADD CONSTRAINT `FK_tables` FOREIGN KEY (`id_tables`) REFERENCES `tables` (`id_tables`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
