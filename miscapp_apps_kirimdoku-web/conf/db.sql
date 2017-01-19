/*
 Navicat PostgreSQL Data Transfer

 Source Server         : devtest
 Source Server Version : 90104
 Source Host           : 192.168.88.4
 Source Database       : blitzkrieg
 Source Schema         : public

 Target Server Version : 90104
 File Encoding         : utf-8

 Date: 06/29/2012 16:46:59 PM
*/

-- ----------------------------
--  Table structure for "agents"
-- ----------------------------
DROP TABLE IF EXISTS "agents";
CREATE TABLE "agents" (
	"code" char(3) NOT NULL,
	"name" varchar NOT NULL,
	"corporate_code" char(2) NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "agents" OWNER TO "postgres";

-- ----------------------------
--  Records of "agents"
-- ----------------------------
BEGIN;
INSERT INTO "agents" VALUES ('AAA', 'Simulator Agent Jakarta Selatan', 'AA');
COMMIT;

-- ----------------------------
--  Table structure for "cities"
-- ----------------------------
DROP TABLE IF EXISTS "cities";
CREATE TABLE "cities" (
	"id" int4 NOT NULL,
	"country_iso_code" char(2) NOT NULL,
	"city" varchar NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "cities" OWNER TO "postgres";

-- ----------------------------
--  Table structure for "corporates"
-- ----------------------------
DROP TABLE IF EXISTS "corporates";
CREATE TABLE "corporates" (
	"code" char(2) NOT NULL,
	"name" varchar
)
WITH (OIDS=FALSE);
ALTER TABLE "corporates" OWNER TO "postgres";

-- ----------------------------
--  Records of "corporates"
-- ----------------------------
BEGIN;
INSERT INTO "corporates" VALUES ('AA', 'Simulator Co Ltd.');
COMMIT;

-- ----------------------------
--  Table structure for "countries"
-- ----------------------------
DROP TABLE IF EXISTS "countries";
CREATE TABLE "countries" (
	"iso_code" char(3) NOT NULL,
	"country" varchar NOT NULL,
	"msisdn_prefix" int2 NOT NULL,
	"status" int2 NOT NULL DEFAULT 0,
	"currency_code" char(3)
)
WITH (OIDS=FALSE);
ALTER TABLE "countries" OWNER TO "postgres";

-- ----------------------------
--  Records of "countries"
-- ----------------------------
BEGIN;
INSERT INTO "countries" VALUES ('MY ', 'Malaysia', '64', '0', null);
INSERT INTO "countries" VALUES ('ID ', 'Indonesia', '62', '0', null);
INSERT INTO "countries" VALUES ('SG ', 'Singapure', '61', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for "currencies"
-- ----------------------------
DROP TABLE IF EXISTS "currencies";
CREATE TABLE "currencies" (
	"code" char(3) NOT NULL,
	"currency" varchar NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "currencies" OWNER TO "postgres";

-- ----------------------------
--  Table structure for "customers"
-- ----------------------------
DROP TABLE IF EXISTS "customers";
CREATE TABLE "customers" (
	"id" int4,
	"source_agent_code" char(3)
)
WITH (OIDS=FALSE);
ALTER TABLE "customers" OWNER TO "postgres";

-- ----------------------------
--  Table structure for "groups"
-- ----------------------------
DROP TABLE IF EXISTS "groups";
CREATE TABLE "groups" (
	"id" int4 NOT NULL,
	"name" varchar NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "groups" OWNER TO "postgres";

-- ----------------------------
--  Records of "groups"
-- ----------------------------
BEGIN;
INSERT INTO "groups" VALUES ('1', 'Doku Admin');
INSERT INTO "groups" VALUES ('11', 'Agent Admin');
INSERT INTO "groups" VALUES ('12', 'Supervisor');
COMMIT;

-- ----------------------------
--  Table structure for "transaction_type"
-- ----------------------------
DROP TABLE IF EXISTS "transaction_type";
CREATE TABLE "transaction_type" (
	"id" int2 NOT NULL,
	"type" varchar NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "transaction_type" OWNER TO "postgres";

-- ----------------------------
--  Records of "transaction_type"
-- ----------------------------
BEGIN;
INSERT INTO "transaction_type" VALUES ('1', 'Cash to Cash');
INSERT INTO "transaction_type" VALUES ('2', 'Cash to Account');
COMMIT;

-- ----------------------------
--  Table structure for "transactions"
-- ----------------------------
DROP TABLE IF EXISTS "transactions";
CREATE TABLE "transactions" (
	"id" int4 NOT NULL,
	"agent_code" char(3)
)
WITH (OIDS=FALSE);
ALTER TABLE "transactions" OWNER TO "postgres";

-- ----------------------------
--  Table structure for "users"
-- ----------------------------
DROP TABLE IF EXISTS "users";
CREATE TABLE "users" (
	"id" int4 NOT NULL,
	"group_id" int4 NOT NULL,
	"username" varchar NOT NULL,
	"password" char(32) NOT NULL,
	"is_active" bool DEFAULT false
)
WITH (OIDS=FALSE);
ALTER TABLE "users" OWNER TO "postgres";

-- ----------------------------
--  Primary key structure for table "agents"
-- ----------------------------
ALTER TABLE "agents" ADD CONSTRAINT "agents_pkey" PRIMARY KEY ("code") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table "agents"
-- ----------------------------
CREATE UNIQUE INDEX "agents_code_key" ON "agents" USING btree(code ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table "cities"
-- ----------------------------
ALTER TABLE "cities" ADD CONSTRAINT "cities_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "corporates"
-- ----------------------------
ALTER TABLE "corporates" ADD CONSTRAINT "corporates_pkey" PRIMARY KEY ("code") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "countries"
-- ----------------------------
ALTER TABLE "countries" ADD CONSTRAINT "countries_pkey" PRIMARY KEY ("iso_code") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table "countries"
-- ----------------------------
CREATE UNIQUE INDEX "countries_iso_code_key" ON "countries" USING btree(iso_code ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table "currencies"
-- ----------------------------
ALTER TABLE "currencies" ADD CONSTRAINT "currencies_pkey" PRIMARY KEY ("code") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table "currencies"
-- ----------------------------
CREATE UNIQUE INDEX "currencies_code_key" ON "currencies" USING btree(code ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table "groups"
-- ----------------------------
ALTER TABLE "groups" ADD CONSTRAINT "groups_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "transaction_type"
-- ----------------------------
ALTER TABLE "transaction_type" ADD CONSTRAINT "transaction_type_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "transactions"
-- ----------------------------
ALTER TABLE "transactions" ADD CONSTRAINT "transactions_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "users"
-- ----------------------------
ALTER TABLE "users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

