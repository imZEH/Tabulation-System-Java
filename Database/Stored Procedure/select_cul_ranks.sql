DELIMITER $$

DROP PROCEDURE IF EXISTS `automated`.`select_cul_ranks` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `select_cul_ranks`()
BEGIN
  SELECT * FROM cultural_ranking_points order by RP_Point desc;
END $$

DELIMITER ;