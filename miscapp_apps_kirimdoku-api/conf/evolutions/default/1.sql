# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table audit_logs (
  id                        bigint not null,
  created_time              timestamp not null,
  source                    integer not null,
  user_id                   bigint,
  tag                       varchar(24) not null,
  message                   varchar(255) not null,
  data                      text,
  data2                     text,
  constraint pk_audit_logs primary key (id))
;

create table banks (
  id                        varchar(255) not null,
  code                      varchar(12),
  name                      varchar(255),
  city                      varchar(255),
  constraint pk_banks primary key (id))
;

create table channels (
  code                      varchar(2) not null,
  name                      varchar(255),
  constraint pk_channels primary key (code))
;

create table cities (
  id                        bigint not null,
  name                      varchar(255),
  country_code              varchar(3),
  constraint pk_cities primary key (id))
;

create table corporates (
  code                      varchar(3) not null,
  name                      varchar(64) not null,
  trade_name                varchar(64),
  business_type             varchar(64),
  license_type              varchar(64),
  license_number            varchar(64),
  country_code              varchar(3),
  currency_code             varchar(3),
  city_name                 varchar(64),
  address                   varchar(255),
  postal_code               varchar(10),
  phone_number              varchar(20),
  fax_number                varchar(20),
  email                     varchar(64),
  status                    integer not null,
  credit_limit              decimal(38) not null,
  credit_alert_limit        decimal(38) not null,
  customer_send_limit       decimal(38) not null,
  permission                integer not null,
  encryption_key            varchar(16),
  operation_id              integer,
  finance_id                integer,
  settlement_id             integer,
  configuration_id          integer,
  constraint ck_corporates_status check (status in (0,1)),
  constraint pk_corporates primary key (code))
;

create table corporate_configuration (
  id                        integer not null,
  theme                     varchar(255),
  logo_path                 varchar(255),
  primary_color             varchar(255),
  secondary_color           varchar(255),
  header_color              varchar(255),
  allow_high_value_transfer boolean,
  constraint pk_corporate_configuration primary key (id))
;

create table corporate_finance (
  id                        integer not null,
  name                      varchar(255),
  phone_number              varchar(255),
  mobile_number             varchar(255),
  fax_number                varchar(255),
  email_address             varchar(255),
  city                      varchar(255),
  address                   varchar(255),
  postal_code               varchar(255),
  constraint pk_corporate_finance primary key (id))
;

create table corporate_operation (
  id                        integer not null,
  name                      varchar(255),
  phone_number              varchar(255),
  mobile_number             varchar(255),
  fax_number                varchar(255),
  email_address             varchar(255),
  city                      varchar(255),
  address                   varchar(255),
  postal_code               varchar(255),
  cs_phone_number           varchar(255),
  cs_fax_number             varchar(255),
  toll_phone_number         varchar(255),
  cs_email_address          varchar(255),
  constraint pk_corporate_operation primary key (id))
;

create table corporate_settlement (
  id                        integer not null,
  bank_name                 varchar(255),
  account_number            varchar(255),
  account_name              varchar(255),
  swift_code                varchar(255),
  name                      varchar(255),
  phone_number              varchar(255),
  fax_number                varchar(255),
  email_address             varchar(255),
  bank_address              varchar(255),
  postal_code               varchar(255),
  province                  varchar(255),
  city                      varchar(255),
  settlement_period_day     integer,
  agreement_date            timestamp,
  agreement_year            integer,
  agreement_expired_date    timestamp,
  agreement_reminder_date   timestamp,
  constraint pk_corporate_settlement primary key (id))
;

create table countries (
  code                      varchar(3) not null,
  name                      varchar(255),
  currency_code             varchar(3) not null,
  constraint pk_countries primary key (code))
;

create table currencies (
  code                      varchar(3) not null,
  constraint pk_currencies primary key (code))
;

create table customers (
  id                        bigint not null,
  agent_id                  bigint,
  first_name                varchar(64) not null,
  last_name                 varchar(64) not null,
  country_code              varchar(3) not null,
  city_name                 varchar(64),
  address                   varchar(255),
  gender                    integer,
  birth_date                DATE,
  personal_id_type          varchar(16),
  personal_id               varchar(32),
  personal_id_country_code  varchar(3),
  personal_id_issue_date    date,
  personal_id_expire_date   date,
  phone_number              varchar(20),
  email                     varchar(64),
  postal_code               varchar(10),
  tax_id                    varchar(32),
  occupation                varchar(64),
  constraint ck_customers_gender check (gender in (0,1)),
  constraint pk_customers primary key (id))
;

create table customer_accounts (
  id                        bigint not null,
  bank_id                   varchar(255) not null,
  number                    varchar(255) not null,
  name                      varchar(255) not null,
  city                      varchar(255),
  address                   varchar(255),
  constraint pk_customer_accounts primary key (id))
;

create table customer_bans (
  id                        bigint not null,
  country_code              varchar(3) not null,
  first_name                varchar(255) not null,
  last_name                 varchar(255) not null,
  birth_date                DATE,
  reason                    varchar(255),
  customer_id               bigint,
  created                   timestamp not null,
  modified                  timestamp,
  constraint pk_customer_bans primary key (id))
;

create table fees (
  id                        integer not null,
  corporate_code            varchar(3) not null,
  sender_country_code       varchar(3) not null,
  beneficiary_country_code  varchar(3) not null,
  channel_code              varchar(2) not null,
  from_amount               decimal(16,6) not null,
  to_amount                 decimal(16,6) not null,
  amount                    decimal(12,2) not null,
  currency_code             varchar(3) not null,
  description               varchar(255),
  domain_corporate_code     varchar(3),
  constraint pk_fees primary key (id))
;

create table forex (
  id                        bigint not null,
  corporate_code            varchar(3) not null,
  origin_code               varchar(3) not null,
  destination_code          varchar(3) not null,
  rate                      float not null,
  spread                    float not null,
  created_time              timestamp not null,
  constraint pk_forex primary key (id))
;

create table forex_references (
  id                        bigint not null,
  forex_id                  bigint not null,
  initial_rate              float not null,
  rate                      float not null,
  created_time              timestamp not null,
  constraint pk_forex_references primary key (id))
;

create table forex_setting (
  id                        bigint not null,
  threshold                 float,
  spread                    float,
  created_time              timestamp,
  constraint pk_forex_setting primary key (id))
;

create table labels (
  id                        integer not null,
  name                      varchar(255) not null,
  description               varchar(255) not null,
  type                      integer not null,
  constraint ck_labels_type check (type in (0,1,2,3)),
  constraint pk_labels primary key (id))
;

create table news (
  id                        bigint not null,
  content                   text,
  published                 boolean not null,
  corporate_code            varchar(3) not null,
  constraint pk_news primary key (id))
;

create table groups (
  role                      varchar(255) not null,
  description               varchar(255),
  constraint pk_groups primary key (role))
;

create table settlements (
  id                        bigint not null,
  created                   timestamp,
  constraint pk_settlements primary key (id))
;

create table transactions (
  id                        bigint not null,
  corporate_code            varchar(3) not null,
  agent_id                  bigint,
  channel_code              varchar(2) not null,
  created_time              timestamp not null,
  modified_time             timestamp,
  status                    integer not null,
  cash_in_time              timestamp not null,
  cash_out_time             timestamp,
  sender_id                 bigint not null,
  sender_country_code       varchar(3) not null,
  sender_currency_code      varchar(3) not null,
  sender_amount             decimal(16,2) not null,
  beneficiary_id            bigint not null,
  beneficiary_country_code  varchar(3) not null,
  beneficiary_currency_code varchar(3) not null,
  beneficiary_amount        decimal(16,2) not null,
  forex_reference_id        bigint not null,
  beneficiary_city          varchar(255),
  auth1                     bytea,
  auth2                     bytea,
  sender_note               varchar(255),
  beneficiary_agent_id      bigint,
  beneficiary_account_id    bigint,
  send_trx_id               varchar(255),
  receive_trx_id            varchar(255),
  settlement_id             bigint,
  constraint uq_transactions_1 unique (corporate_code,send_trx_id),
  constraint uq_transactions_2 unique (corporate_code,receive_trx_id),
  constraint pk_transactions primary key (id))
;

create table transaction_fees (
  id                        bigint not null,
  transaction_id            bigint not null,
  fee_id                    integer,
  amount                    decimal(12,2) not null,
  currency_code             varchar(3) not null,
  description               varchar(255),
  domain_corporate_code     varchar(3),
  constraint pk_transaction_fees primary key (id))
;

create table transaction_inquiries (
  id                        bigint not null,
  type_id                   integer not null,
  user_id                   bigint,
  request_time              timestamp not null,
  update_time               timestamp,
  transaction_id            bigint,
  validation_id             varchar(255),
  collect_id                varchar(255),
  invalid_auth_count        int default 0 not null,
  constraint ck_transaction_inquiries_type_id check (type_id in (0,1,2,3,4,5,6)),
  constraint pk_transaction_inquiries primary key (id))
;

create table users (
  id                        bigint not null,
  corporate_code            varchar(3) not null,
  first_name                varchar(64) not null,
  last_name                 varchar(64) not null,
  email                     varchar(64),
  password                  varchar(128),
  country_code              varchar(3),
  city_name                 varchar(255),
  address                   varchar(255),
  gender                    integer not null,
  birth_date                DATE,
  personal_id_type          varchar(16),
  personal_id               varchar(32),
  phone_number              varchar(24),
  mobile_number             varchar(24),
  postal_code               varchar(10),
  supervisor_id             bigint,
  is_admin                  boolean not null,
  photo                     varchar(64),
  status                    integer not null,
  last_login_time           date,
  credit_limit              decimal(38) not null,
  constraint ck_users_gender check (gender in (0,1)),
  constraint ck_users_status check (status in (0,1,2)),
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id))
;

create table user_requests (
  id                        bigint not null,
  request_time              timestamp,
  username                  varchar(255),
  request_id                varchar(255),
  request_data              varchar(255),
  result_data               varchar(255),
  constraint pk_user_requests primary key (id))
;


create table transactions_labels (
  transactions_id                bigint not null,
  labels_id                      integer not null,
  constraint pk_transactions_labels primary key (transactions_id, labels_id))
;

create table users_groups (
  users_id                       bigint not null,
  groups_role                    varchar(255) not null,
  constraint pk_users_groups primary key (users_id, groups_role))
;
create sequence audit_logs_seq;

create sequence banks_seq;

create sequence channels_seq;

create sequence cities_seq;

create sequence corporates_seq;

create sequence corporate_configuration_seq;

create sequence corporate_finance_seq;

create sequence corporate_operation_seq;

create sequence corporate_settlement_seq;

create sequence countries_seq;

create sequence currencies_seq;

create sequence customers_seq;

create sequence customer_accounts_seq;

create sequence customer_bans_seq;

create sequence fees_seq;

create sequence forex_seq;

create sequence forex_references_seq;

create sequence forex_setting_seq;

create sequence labels_seq;

create sequence news_seq;

create sequence groups_seq;

create sequence settlements_seq;

create sequence transactions_seq;

create sequence transaction_fees_seq;

create sequence transaction_inquiries_seq;

create sequence users_seq;

create sequence user_requests_seq;

alter table audit_logs add constraint fk_audit_logs_user_1 foreign key (user_id) references users (id);
create index ix_audit_logs_user_1 on audit_logs (user_id);
alter table cities add constraint fk_cities_country_2 foreign key (country_code) references countries (code);
create index ix_cities_country_2 on cities (country_code);
alter table corporates add constraint fk_corporates_country_3 foreign key (country_code) references countries (code);
create index ix_corporates_country_3 on corporates (country_code);
alter table corporates add constraint fk_corporates_currency_4 foreign key (currency_code) references currencies (code);
create index ix_corporates_currency_4 on corporates (currency_code);
alter table corporates add constraint fk_corporates_operation_5 foreign key (operation_id) references corporate_operation (id);
create index ix_corporates_operation_5 on corporates (operation_id);
alter table corporates add constraint fk_corporates_finance_6 foreign key (finance_id) references corporate_finance (id);
create index ix_corporates_finance_6 on corporates (finance_id);
alter table corporates add constraint fk_corporates_settlement_7 foreign key (settlement_id) references corporate_settlement (id);
create index ix_corporates_settlement_7 on corporates (settlement_id);
alter table corporates add constraint fk_corporates_configuration_8 foreign key (configuration_id) references corporate_configuration (id);
create index ix_corporates_configuration_8 on corporates (configuration_id);
alter table countries add constraint fk_countries_currency_9 foreign key (currency_code) references currencies (code);
create index ix_countries_currency_9 on countries (currency_code);
alter table customers add constraint fk_customers_agent_10 foreign key (agent_id) references users (id);
create index ix_customers_agent_10 on customers (agent_id);
alter table customers add constraint fk_customers_country_11 foreign key (country_code) references countries (code);
create index ix_customers_country_11 on customers (country_code);
alter table customers add constraint fk_customers_personalIdCountr_12 foreign key (personal_id_country_code) references countries (code);
create index ix_customers_personalIdCountr_12 on customers (personal_id_country_code);
alter table customer_accounts add constraint fk_customer_accounts_bank_13 foreign key (bank_id) references banks (id);
create index ix_customer_accounts_bank_13 on customer_accounts (bank_id);
alter table customer_bans add constraint fk_customer_bans_country_14 foreign key (country_code) references countries (code);
create index ix_customer_bans_country_14 on customer_bans (country_code);
alter table customer_bans add constraint fk_customer_bans_customer_15 foreign key (customer_id) references customers (id);
create index ix_customer_bans_customer_15 on customer_bans (customer_id);
alter table fees add constraint fk_fees_corporate_16 foreign key (corporate_code) references corporates (code);
create index ix_fees_corporate_16 on fees (corporate_code);
alter table fees add constraint fk_fees_senderCountry_17 foreign key (sender_country_code) references countries (code);
create index ix_fees_senderCountry_17 on fees (sender_country_code);
alter table fees add constraint fk_fees_beneficiaryCountry_18 foreign key (beneficiary_country_code) references countries (code);
create index ix_fees_beneficiaryCountry_18 on fees (beneficiary_country_code);
alter table fees add constraint fk_fees_channel_19 foreign key (channel_code) references channels (code);
create index ix_fees_channel_19 on fees (channel_code);
alter table fees add constraint fk_fees_currency_20 foreign key (currency_code) references currencies (code);
create index ix_fees_currency_20 on fees (currency_code);
alter table fees add constraint fk_fees_domainCorporate_21 foreign key (domain_corporate_code) references corporates (code);
create index ix_fees_domainCorporate_21 on fees (domain_corporate_code);
alter table forex add constraint fk_forex_corporate_22 foreign key (corporate_code) references corporates (code);
create index ix_forex_corporate_22 on forex (corporate_code);
alter table forex add constraint fk_forex_origin_23 foreign key (origin_code) references currencies (code);
create index ix_forex_origin_23 on forex (origin_code);
alter table forex add constraint fk_forex_destination_24 foreign key (destination_code) references currencies (code);
create index ix_forex_destination_24 on forex (destination_code);
alter table forex_references add constraint fk_forex_references_forex_25 foreign key (forex_id) references forex (id);
create index ix_forex_references_forex_25 on forex_references (forex_id);
alter table news add constraint fk_news_corporate_26 foreign key (corporate_code) references corporates (code);
create index ix_news_corporate_26 on news (corporate_code);
alter table transactions add constraint fk_transactions_corporate_27 foreign key (corporate_code) references corporates (code);
create index ix_transactions_corporate_27 on transactions (corporate_code);
alter table transactions add constraint fk_transactions_agent_28 foreign key (agent_id) references users (id);
create index ix_transactions_agent_28 on transactions (agent_id);
alter table transactions add constraint fk_transactions_channel_29 foreign key (channel_code) references channels (code);
create index ix_transactions_channel_29 on transactions (channel_code);
alter table transactions add constraint fk_transactions_sender_30 foreign key (sender_id) references customers (id);
create index ix_transactions_sender_30 on transactions (sender_id);
alter table transactions add constraint fk_transactions_senderCountry_31 foreign key (sender_country_code) references countries (code);
create index ix_transactions_senderCountry_31 on transactions (sender_country_code);
alter table transactions add constraint fk_transactions_senderCurrenc_32 foreign key (sender_currency_code) references currencies (code);
create index ix_transactions_senderCurrenc_32 on transactions (sender_currency_code);
alter table transactions add constraint fk_transactions_beneficiary_33 foreign key (beneficiary_id) references customers (id);
create index ix_transactions_beneficiary_33 on transactions (beneficiary_id);
alter table transactions add constraint fk_transactions_beneficiaryCo_34 foreign key (beneficiary_country_code) references countries (code);
create index ix_transactions_beneficiaryCo_34 on transactions (beneficiary_country_code);
alter table transactions add constraint fk_transactions_beneficiaryCu_35 foreign key (beneficiary_currency_code) references currencies (code);
create index ix_transactions_beneficiaryCu_35 on transactions (beneficiary_currency_code);
alter table transactions add constraint fk_transactions_forexReferenc_36 foreign key (forex_reference_id) references forex_references (id);
create index ix_transactions_forexReferenc_36 on transactions (forex_reference_id);
alter table transactions add constraint fk_transactions_beneficiaryAg_37 foreign key (beneficiary_agent_id) references users (id);
create index ix_transactions_beneficiaryAg_37 on transactions (beneficiary_agent_id);
alter table transactions add constraint fk_transactions_beneficiaryAc_38 foreign key (beneficiary_account_id) references customer_accounts (id);
create index ix_transactions_beneficiaryAc_38 on transactions (beneficiary_account_id);
alter table transactions add constraint fk_transactions_settlement_39 foreign key (settlement_id) references settlements (id);
create index ix_transactions_settlement_39 on transactions (settlement_id);
alter table transaction_fees add constraint fk_transaction_fees_transacti_40 foreign key (transaction_id) references transactions (id);
create index ix_transaction_fees_transacti_40 on transaction_fees (transaction_id);
alter table transaction_fees add constraint fk_transaction_fees_fee_41 foreign key (fee_id) references fees (id);
create index ix_transaction_fees_fee_41 on transaction_fees (fee_id);
alter table transaction_fees add constraint fk_transaction_fees_currency_42 foreign key (currency_code) references currencies (code);
create index ix_transaction_fees_currency_42 on transaction_fees (currency_code);
alter table transaction_fees add constraint fk_transaction_fees_domainCor_43 foreign key (domain_corporate_code) references corporates (code);
create index ix_transaction_fees_domainCor_43 on transaction_fees (domain_corporate_code);
alter table transaction_inquiries add constraint fk_transaction_inquiries_user_44 foreign key (user_id) references users (id);
create index ix_transaction_inquiries_user_44 on transaction_inquiries (user_id);
alter table transaction_inquiries add constraint fk_transaction_inquiries_tran_45 foreign key (transaction_id) references transactions (id);
create index ix_transaction_inquiries_tran_45 on transaction_inquiries (transaction_id);
alter table users add constraint fk_users_corporate_46 foreign key (corporate_code) references corporates (code);
create index ix_users_corporate_46 on users (corporate_code);
alter table users add constraint fk_users_country_47 foreign key (country_code) references countries (code);
create index ix_users_country_47 on users (country_code);
alter table users add constraint fk_users_supervisor_48 foreign key (supervisor_id) references users (id);
create index ix_users_supervisor_48 on users (supervisor_id);



alter table transactions_labels add constraint fk_transactions_labels_transa_01 foreign key (transactions_id) references transactions (id);

alter table transactions_labels add constraint fk_transactions_labels_labels_02 foreign key (labels_id) references labels (id);

alter table users_groups add constraint fk_users_groups_users_01 foreign key (users_id) references users (id);

alter table users_groups add constraint fk_users_groups_groups_02 foreign key (groups_role) references groups (role);

# --- !Downs

drop table if exists audit_logs cascade;

drop table if exists banks cascade;

drop table if exists channels cascade;

drop table if exists cities cascade;

drop table if exists corporates cascade;

drop table if exists corporate_configuration cascade;

drop table if exists corporate_finance cascade;

drop table if exists corporate_operation cascade;

drop table if exists corporate_settlement cascade;

drop table if exists countries cascade;

drop table if exists currencies cascade;

drop table if exists customers cascade;

drop table if exists customer_accounts cascade;

drop table if exists customer_bans cascade;

drop table if exists fees cascade;

drop table if exists forex cascade;

drop table if exists forex_references cascade;

drop table if exists forex_setting cascade;

drop table if exists labels cascade;

drop table if exists news cascade;

drop table if exists groups cascade;

drop table if exists settlements cascade;

drop table if exists transactions cascade;

drop table if exists transactions_labels cascade;

drop table if exists transaction_fees cascade;

drop table if exists transaction_inquiries cascade;

drop table if exists users cascade;

drop table if exists users_groups cascade;

drop table if exists user_requests cascade;

drop sequence if exists audit_logs_seq;

drop sequence if exists banks_seq;

drop sequence if exists channels_seq;

drop sequence if exists cities_seq;

drop sequence if exists corporates_seq;

drop sequence if exists corporate_configuration_seq;

drop sequence if exists corporate_finance_seq;

drop sequence if exists corporate_operation_seq;

drop sequence if exists corporate_settlement_seq;

drop sequence if exists countries_seq;

drop sequence if exists currencies_seq;

drop sequence if exists customers_seq;

drop sequence if exists customer_accounts_seq;

drop sequence if exists customer_bans_seq;

drop sequence if exists fees_seq;

drop sequence if exists forex_seq;

drop sequence if exists forex_references_seq;

drop sequence if exists forex_setting_seq;

drop sequence if exists labels_seq;

drop sequence if exists news_seq;

drop sequence if exists groups_seq;

drop sequence if exists settlements_seq;

drop sequence if exists transactions_seq;

drop sequence if exists transaction_fees_seq;

drop sequence if exists transaction_inquiries_seq;

drop sequence if exists users_seq;

drop sequence if exists user_requests_seq;

