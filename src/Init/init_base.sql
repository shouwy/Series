SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `series`
--
CREATE DATABASE IF NOT EXISTS `series` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `series`;

-- --------------------------------------------------------

--
-- Structure de la table `episode`
--

DROP TABLE IF EXISTS `episode`;
CREATE TABLE IF NOT EXISTS `episode` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Titre` varchar(256) COLLATE utf8_bin NOT NULL,
  `Synopsis` longtext COLLATE utf8_bin NOT NULL,
  `IdSaison` int(11) NOT NULL,
  `DateSortie` date NOT NULL,
  `idEtatPersonnel` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Saison` (`IdSaison`),
  UNIQUE KEY `EtatPersonnel` (`idEtatPersonnel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `etat`
--

DROP TABLE IF EXISTS `etat`;
CREATE TABLE IF NOT EXISTS `etat` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(256) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `etatpersonnel`
--

DROP TABLE IF EXISTS `etatpersonnel`;
CREATE TABLE IF NOT EXISTS `etatpersonnel` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(256) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `saison`
--

DROP TABLE IF EXISTS `saison`;
CREATE TABLE IF NOT EXISTS `saison` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(256) COLLATE utf8_bin NOT NULL,
  `AnneeProduction` int(4) NOT NULL,
  `IdSerie` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Serie` (`IdSerie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `series`
--

DROP TABLE IF EXISTS `series`;
CREATE TABLE IF NOT EXISTS `series` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(256) COLLATE utf8_bin NOT NULL,
  `Synopsis` longtext COLLATE utf8_bin NOT NULL,
  `IdType` int(11) NOT NULL,
  `IdEtat` int(11) NOT NULL,
  `IdEtatPersonnel` int(11) NOT NULL,
  `Image` varchar(256) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Type` (`IdType`),
  UNIQUE KEY `Etat` (`IdEtat`),
  UNIQUE KEY `EtatPersonnel` (`IdEtatPersonnel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(256) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `episode`
--
ALTER TABLE `episode`
  ADD CONSTRAINT `episode_ibfk_1` FOREIGN KEY (`IdSaison`) REFERENCES `saison` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `episode_ibfk_2` FOREIGN KEY (`idEtatPersonnel`) REFERENCES `etatpersonnel` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `saison`
--
ALTER TABLE `saison`
  ADD CONSTRAINT `saison_ibfk_1` FOREIGN KEY (`IdSerie`) REFERENCES `series` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `series`
--
ALTER TABLE `series`
  ADD CONSTRAINT `series_ibfk_1` FOREIGN KEY (`IdType`) REFERENCES `type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `series_ibfk_2` FOREIGN KEY (`IdEtat`) REFERENCES `etat` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `series_ibfk_3` FOREIGN KEY (`IdEtatPersonnel`) REFERENCES `etatpersonnel` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
