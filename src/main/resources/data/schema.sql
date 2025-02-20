--#########################################################
--####                                                 ####
--####  Author: Dountio                                ####
--####  Date: February 17th, 2025                      ####
--####  Version: 1.0                                   ####
--####  License: sidof LLC                             ####
--####                                                 ####
--#########################################################

--CREATE SCHEMA IF NOT EXISTS afrilanddb;


--SET NAMES 'UTF8MB4';

--USE afrilanddb;

CREATE TABLE IF NOT EXISTS test_users
(
    id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(200) NOT NULL
);
SELECT 1 + 1 as addtionTwoNumber;