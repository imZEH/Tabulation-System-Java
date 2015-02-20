DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`select_sports_ranks` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `select_sports_ranks`()
BEGIN
     SELECT * FROM sports_ranking_points order by SRP_Points desc;
END $$

DELIMITER ;