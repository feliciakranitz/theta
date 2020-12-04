CREATE DATABASE IF NOT EXISTS theta_db;

USE theta_db;

CREATE TABLE IF NOT EXISTS model (
  modelId CHAR(36) PRIMARY KEY,
  modelType VARCHAR(10),
  fileName VARCHAR(100),
  fileSize BIGINT,
  visualizedModel VARCHAR(60),
  creationTime TIMESTAMP);

CREATE TABLE IF NOT EXISTS configuration (
  configurationId CHAR(36) PRIMARY KEY,
  domainName VARCHAR(60),
  refinement VARCHAR(60),
  predSplit VARCHAR(60),
  errorLoc VARCHAR(60),
  precGranularity VARCHAR(60),
  search VARCHAR(60)
  encoding VARCHAR(60),
  maxEnum SMALLINT,
  initPrec VARCHAR(60),
  pruneStrategy VARCHAR(60),
  logLevel VARCHAR(60),
  dataStrategy VARCHAR(60),
  clockStrategy VARCHAR(60),
  property VARCHAR(60),
  cexFile BOOLEAN,
  stacktrace BOOLEAN,
  creationTime TIMESTAMP);

CREATE TABLE IF NOT EXISTS job (
  jobId CHAR(36) PRIMARY KEY,
  modelId CHAR(36),
  CONSTRAINT fk_model FOREIGN KEY (modelId) REFERENCES model(modelId),
  configId CHAR(36),
  CONSTRAINT fk_config FOREIGN KEY (configId) REFERENCES configuration(configurationId),
  configId CHAR(36),
  CONSTRAINT fk_benchmark FOREIGN KEY (benchmarkId) REFERENCES analysisBenchmark(benchmarkId),
  status SMALLINT NOT NULL,
  progress SMALLINT NOT NULL,
  isSafe BOOLEAN NOT NULL,
  outputFile VARCHAR(60),
  cexFile VARCHAR(60),
  notificationAddress VARCHAR(50),

  creationTime TIMESTAMP);

CREATE TABLE IF NOT EXISTS analysisBenchmark (
  benchmarkId CHAR(36) PRIMARY KEY,
  timeElapsed BIGINT NOT NULL,
  algorithmTimeMs BIGINT NOT NULL,
  abstractorTimeMs BIGINT NOT NULL,
  refinerTimeMs BIGINT NOT NULL,
  iterations SMALLINT NOT NULL,
  argSize BIGINT NOT NULL,
  argDepth BIGINT NOT NULL,
  argMeanBranchingFactor BIGINT NOT NULL,
  abstractorTimeMs VARCHAR(60),

  creationTime TIMESTAMP);