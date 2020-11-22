CREATE DATABASE IF NOT EXISTS theta_db;

USE theta_db;

CREATE TABLE IF NOT EXISTS model (
  modelId CHAR(36) NOT NULL PRIMARY KEY,
  modelType VARCHAR(255),
  fileName VARCHAR(255),
  fileSize BIGINT,
  creationTime TIMESTAMP);

CREATE TABLE IF NOT EXISTS model (
  modelId CHAR(36) NOT NULL PRIMARY KEY,
  modelType VARCHAR(255),
  fileName VARCHAR(255),
  fileSize BIGINT,
  creationTime TIMESTAMP);