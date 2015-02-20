﻿DROP VIEW IF EXISTS `automated`.`cultural_overall_tab`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cultural_overall_tab` AS select `p`.`poi` AS `poi`,`op`.`OP_Percentage` AS `OP_Percentage`,((`p`.`poi` * `op`.`OP_Percentage`) / 100) AS `divi` from (`cultural_view_point_not_tab` `p` join `overall_points` `op`) group by `p`.`poi`;