-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: galeb_api
-- ------------------------------------------------------
-- Server version	5.6.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `apitoken` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `resettoken` bit(1) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_account_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_details`
--

DROP TABLE IF EXISTS `account_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_details` (
  `account_id` bigint(20) NOT NULL,
  `details` varchar(255) DEFAULT NULL,
  `details_key` varchar(255) NOT NULL,
  PRIMARY KEY (`account_id`,`details_key`),
  CONSTRAINT `FKjlchm9h4od1gxeqrfsngfkj5b` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `balancepolicy`
--

DROP TABLE IF EXISTS `balancepolicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `balancepolicy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_balancepolicy_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `environment`
--

DROP TABLE IF EXISTS `environment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `environment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_environment_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `health_check_headers`
--

DROP TABLE IF EXISTS `health_check_headers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `health_check_headers` (
  `health_check_id` bigint(20) NOT NULL,
  `headers` varchar(255) DEFAULT NULL,
  `headers_key` varchar(255) NOT NULL,
  PRIMARY KEY (`health_check_id`,`headers_key`),
  CONSTRAINT `FKjdimg96xwb52gnt4pgmvdwdv1` FOREIGN KEY (`health_check_id`) REFERENCES `healthcheck` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `health_status`
--

DROP TABLE IF EXISTS `health_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `health_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `status_detailed` varchar(255) DEFAULT NULL,
  `target_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_health_status_source_target_id` (`source`,`target_id`),
  KEY `FK_healthstatus_target` (`target_id`),
  CONSTRAINT `FK_healthstatus_target` FOREIGN KEY (`target_id`) REFERENCES `target` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `healthcheck`
--

DROP TABLE IF EXISTS `healthcheck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `healthcheck` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `http_method` int(11) DEFAULT NULL,
  `http_status_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `tcp_only` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_healthcheck_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pool`
--

DROP TABLE IF EXISTS `pool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pool` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `hc_body` varchar(255) DEFAULT NULL,
  `hc_host` varchar(255) DEFAULT NULL,
  `hc_http_method` int(11) DEFAULT NULL,
  `hc_http_status_code` varchar(255) DEFAULT NULL,
  `hc_path` varchar(255) DEFAULT NULL,
  `hc_tcp_only` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `balancepolicy_id` bigint(20) NOT NULL,
  `environment_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pool_name_project_id` (`name`,`project_id`),
  KEY `FK_pool_balancepolicy` (`balancepolicy_id`),
  KEY `FK_pool_environment` (`environment_id`),
  KEY `FK_pool_project` (`project_id`),
  CONSTRAINT `FK_pool_balancepolicy` FOREIGN KEY (`balancepolicy_id`) REFERENCES `balancepolicy` (`id`),
  CONSTRAINT `FK_pool_environment` FOREIGN KEY (`environment_id`) REFERENCES `environment` (`id`),
  CONSTRAINT `FK_pool_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pool_hc_headers`
--

DROP TABLE IF EXISTS `pool_hc_headers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pool_hc_headers` (
  `pool_id` bigint(20) NOT NULL,
  `hc_headers` varchar(255) DEFAULT NULL,
  `hc_headers_key` varchar(255) NOT NULL,
  PRIMARY KEY (`pool_id`,`hc_headers_key`),
  CONSTRAINT `FKhl6i1wp155x5ovgxgp3kb17fr` FOREIGN KEY (`pool_id`) REFERENCES `pool` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pool_targets`
--

DROP TABLE IF EXISTS `pool_targets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pool_targets` (
  `pool_id` bigint(20) NOT NULL,
  `target_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pool_id`,`target_id`),
  KEY `FK_pool_target_id` (`target_id`),
  CONSTRAINT `FK_pool_target_id` FOREIGN KEY (`target_id`) REFERENCES `target` (`id`),
  CONSTRAINT `FK_target_pool_id` FOREIGN KEY (`pool_id`) REFERENCES `pool` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_project_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project_teams`
--

DROP TABLE IF EXISTS `project_teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_teams` (
  `project_id` bigint(20) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  PRIMARY KEY (`project_id`,`team_id`),
  KEY `FK_project_team_id` (`team_id`),
  CONSTRAINT `FK_project_team_id` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FK_team_project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rolegroup`
--

DROP TABLE IF EXISTS `rolegroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolegroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rolegroup_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rolegroup_accounts`
--

DROP TABLE IF EXISTS `rolegroup_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolegroup_accounts` (
  `rolegroup_id` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rolegroup_id`,`account_id`),
  KEY `FK_account_rolegroup_id` (`account_id`),
  CONSTRAINT `FK_account_rolegroup_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_rolegroup_account_id` FOREIGN KEY (`rolegroup_id`) REFERENCES `rolegroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rolegroup_projects`
--

DROP TABLE IF EXISTS `rolegroup_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolegroup_projects` (
  `rolegroup_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rolegroup_id`,`project_id`),
  KEY `FK_project_rolegroup_id` (`project_id`),
  CONSTRAINT `FK_project_rolegroup_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_rolegroup_project_id` FOREIGN KEY (`rolegroup_id`) REFERENCES `rolegroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rolegroup_roles`
--

DROP TABLE IF EXISTS `rolegroup_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolegroup_roles` (
  `rolegroup_id` bigint(20) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`rolegroup_id`,`role`),
  CONSTRAINT `FK_rolegroup_role_id` FOREIGN KEY (`rolegroup_id`) REFERENCES `rolegroup` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rolegroup_teams`
--

DROP TABLE IF EXISTS `rolegroup_teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolegroup_teams` (
  `rolegroup_id` bigint(20) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rolegroup_id`,`team_id`),
  KEY `FK_account_team_id` (`team_id`),
  CONSTRAINT `FK_account_team_id` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FK_rolegroup_team_id` FOREIGN KEY (`rolegroup_id`) REFERENCES `rolegroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rule`
--

DROP TABLE IF EXISTS `rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `global` bit(1) DEFAULT NULL,
  `matching` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rule_name_project_id` (`name`,`project_id`),
  KEY `FK_rule_project` (`project_id`),
  CONSTRAINT `FK_rule_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rule_pools`
--

DROP TABLE IF EXISTS `rule_pools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule_pools` (
  `rule_id` bigint(20) NOT NULL,
  `pool_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rule_id`,`pool_id`),
  KEY `FK_rule_pool_id` (`pool_id`),
  CONSTRAINT `FK_pool_rule_id` FOREIGN KEY (`rule_id`) REFERENCES `rule` (`id`),
  CONSTRAINT `FK_rule_pool_id` FOREIGN KEY (`pool_id`) REFERENCES `pool` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ruleordered`
--

DROP TABLE IF EXISTS `ruleordered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruleordered` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `rule_order` int(11) NOT NULL,
  `environment_id` bigint(20) NOT NULL,
  `rule_rule_ordered_id` bigint(20) NOT NULL,
  `virtualhostgroup_rule_ordered_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ruleordered_environment` (`environment_id`),
  KEY `FK_rule_rule_ordered` (`rule_rule_ordered_id`),
  KEY `FK_virtualhostgroup_rule_ordered` (`virtualhostgroup_rule_ordered_id`),
  CONSTRAINT `FK_rule_rule_ordered` FOREIGN KEY (`rule_rule_ordered_id`) REFERENCES `rule` (`id`),
  CONSTRAINT `FK_ruleordered_environment` FOREIGN KEY (`environment_id`) REFERENCES `environment` (`id`),
  CONSTRAINT `FK_virtualhostgroup_rule_ordered` FOREIGN KEY (`virtualhostgroup_rule_ordered_id`) REFERENCES `virtualhostgroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `target`
--

DROP TABLE IF EXISTS `target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `target` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_target_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_team_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team_accounts`
--

DROP TABLE IF EXISTS `team_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_accounts` (
  `team_id` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`team_id`,`account_id`),
  KEY `FK_account_id` (`account_id`),
  CONSTRAINT `FK_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK_team_id` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `virtualhost`
--

DROP TABLE IF EXISTS `virtualhost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virtualhost` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `virtualhostgroup_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_virtualhost_name` (`name`),
  KEY `FK_virtualhost_project` (`project_id`),
  KEY `FK_virtualhost_virtualhostgroup` (`virtualhostgroup_id`),
  CONSTRAINT `FK_virtualhost_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_virtualhost_virtualhostgroup` FOREIGN KEY (`virtualhostgroup_id`) REFERENCES `virtualhostgroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `virtualhost_environments`
--

DROP TABLE IF EXISTS `virtualhost_environments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virtualhost_environments` (
  `virtualhost_id` bigint(20) NOT NULL,
  `environment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`virtualhost_id`,`environment_id`),
  KEY `FK_environment_id` (`environment_id`),
  CONSTRAINT `FK_environment_id` FOREIGN KEY (`environment_id`) REFERENCES `environment` (`id`),
  CONSTRAINT `FK_virtualhost_id` FOREIGN KEY (`virtualhost_id`) REFERENCES `virtualhost` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `virtualhostgroup`
--

DROP TABLE IF EXISTS `virtualhostgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virtualhostgroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified_at` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `quarantine` bit(1) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-15 15:40:46