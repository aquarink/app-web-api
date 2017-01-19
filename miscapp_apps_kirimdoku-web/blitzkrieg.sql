--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: channels; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE channels (
    code character varying(2) NOT NULL,
    name character varying(255)
);


ALTER TABLE public.channels OWNER TO admin;

--
-- Name: channels_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE channels_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.channels_seq OWNER TO admin;

--
-- Name: channels_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('channels_seq', 1, false);


--
-- Name: cities; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE cities (
    id bigint NOT NULL,
    name character varying(255),
    country_code character varying(3)
);


ALTER TABLE public.cities OWNER TO admin;

--
-- Name: cities_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE cities_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cities_seq OWNER TO admin;

--
-- Name: cities_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('cities_seq', 20, true);


--
-- Name: corporates; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE corporates (
    code character varying(3) NOT NULL,
    name character varying(255),
    style character varying(255),
    logo_path character varying(255)
);


ALTER TABLE public.corporates OWNER TO admin;

--
-- Name: corporates_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE corporates_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.corporates_seq OWNER TO admin;

--
-- Name: corporates_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('corporates_seq', 1, false);


--
-- Name: countries; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE countries (
    code character varying(3) NOT NULL,
    name character varying(255),
    currency_code character varying(3) NOT NULL
);


ALTER TABLE public.countries OWNER TO admin;

--
-- Name: countries_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE countries_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.countries_seq OWNER TO admin;

--
-- Name: countries_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('countries_seq', 1, false);


--
-- Name: currencies; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE currencies (
    code character varying(3) NOT NULL
);


ALTER TABLE public.currencies OWNER TO admin;

--
-- Name: currencies_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE currencies_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.currencies_seq OWNER TO admin;

--
-- Name: currencies_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('currencies_seq', 1, false);


--
-- Name: customers; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE customers (
    id bigint NOT NULL,
    first_name character varying(64) NOT NULL,
    last_name character varying(64) NOT NULL,
    gender character varying(255),
    address character varying(255) NOT NULL,
    city_id bigint NOT NULL,
    birth_date date,
    personal_id_type character varying(16),
    personal_id character varying(32),
    tax_id character varying(32),
    phone_number character varying(20),
    postal_code character varying(10),
    email character varying(64),
    agent_id bigint
);


ALTER TABLE public.customers OWNER TO admin;

--
-- Name: customers_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE customers_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customers_seq OWNER TO admin;

--
-- Name: customers_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('customers_seq', 20, true);


--
-- Name: fees; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE fees (
    id integer NOT NULL,
    corporate_code character varying(3) NOT NULL,
    sender_country_code character varying(3) NOT NULL,
    beneficiary_country_code character varying(3) NOT NULL,
    amount numeric(12,2) NOT NULL,
    currency_code character varying(3) NOT NULL,
    description character varying(255),
    domain_corporate_code character varying(3)
);


ALTER TABLE public.fees OWNER TO admin;

--
-- Name: fees_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE fees_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fees_seq OWNER TO admin;

--
-- Name: fees_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('fees_seq', 20, true);


--
-- Name: forex; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE forex (
    id bigint NOT NULL,
    origin_code character varying(3) NOT NULL,
    destination_code character varying(3) NOT NULL,
    rate double precision NOT NULL,
    created_time timestamp without time zone NOT NULL
);


ALTER TABLE public.forex OWNER TO admin;

--
-- Name: forex_references; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE forex_references (
    id bigint NOT NULL,
    forex_id bigint NOT NULL,
    initial_rate double precision NOT NULL,
    rate double precision NOT NULL,
    created_time timestamp without time zone NOT NULL
);


ALTER TABLE public.forex_references OWNER TO admin;

--
-- Name: forex_references_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE forex_references_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.forex_references_seq OWNER TO admin;

--
-- Name: forex_references_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('forex_references_seq', 20, true);


--
-- Name: forex_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE forex_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.forex_seq OWNER TO admin;

--
-- Name: forex_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('forex_seq', 20, true);


--
-- Name: forex_setting; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE forex_setting (
    id bigint NOT NULL,
    threshold double precision,
    spread double precision,
    created_time timestamp without time zone
);


ALTER TABLE public.forex_setting OWNER TO admin;

--
-- Name: forex_setting_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE forex_setting_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.forex_setting_seq OWNER TO admin;

--
-- Name: forex_setting_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('forex_setting_seq', 20, true);


--
-- Name: groups; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE groups (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.groups OWNER TO admin;

--
-- Name: groups_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE groups_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.groups_seq OWNER TO admin;

--
-- Name: groups_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('groups_seq', 1, false);


--
-- Name: news; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE news (
    id bigint NOT NULL,
    content text
);


ALTER TABLE public.news OWNER TO admin;

--
-- Name: news_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE news_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_seq OWNER TO admin;

--
-- Name: news_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('news_seq', 20, true);


--
-- Name: play_evolutions; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE play_evolutions (
    id integer NOT NULL,
    hash character varying(255) NOT NULL,
    applied_at timestamp without time zone NOT NULL,
    apply_script text,
    revert_script text,
    state character varying(255),
    last_problem text
);


ALTER TABLE public.play_evolutions OWNER TO admin;

--
-- Name: transaction_fees; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE transaction_fees (
    transaction_id bigint NOT NULL,
    fee_id integer,
    amount numeric(12,2) NOT NULL,
    currency_code character varying(3) NOT NULL,
    description character varying(255),
    domain_corporate_code character varying(3)
);


ALTER TABLE public.transaction_fees OWNER TO admin;

--
-- Name: transaction_inquiries; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE transaction_inquiries (
    id bigint NOT NULL,
    type_id integer NOT NULL,
    user_id bigint,
    request_time timestamp without time zone NOT NULL,
    update_time timestamp without time zone,
    transaction_id bigint,
    validation_id character varying(255),
    collect_id character varying(255)
);


ALTER TABLE public.transaction_inquiries OWNER TO admin;

--
-- Name: transaction_inquiries_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE transaction_inquiries_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_inquiries_seq OWNER TO admin;

--
-- Name: transaction_inquiries_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('transaction_inquiries_seq', 760, true);


--
-- Name: transactions; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE transactions (
    id bigint NOT NULL,
    corporate_code character varying(3) NOT NULL,
    user_id bigint,
    channel_code character varying(2) NOT NULL,
    created_time timestamp without time zone NOT NULL,
    status integer NOT NULL,
    cash_in_time timestamp without time zone,
    cash_out_time timestamp without time zone,
    sender_id bigint NOT NULL,
    sender_country_code character varying(3) NOT NULL,
    sender_currency_code character varying(3) NOT NULL,
    sender_amount numeric(16,2) NOT NULL,
    beneficiary_id bigint NOT NULL,
    beneficiary_country_code character varying(3) NOT NULL,
    beneficiary_currency_code character varying(3) NOT NULL,
    beneficiary_amount numeric(16,2) NOT NULL,
    forex_reference_id bigint NOT NULL,
    beneficiary_city_id bigint,
    auth1 character varying(255),
    auth2 character varying(255),
    fee_included boolean NOT NULL,
    sender_note character varying(255)
);


ALTER TABLE public.transactions OWNER TO admin;

--
-- Name: transactions_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE transactions_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_seq OWNER TO admin;

--
-- Name: transactions_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('transactions_seq', 720, true);


--
-- Name: user_permission; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE user_permission (
    id bigint NOT NULL,
    value character varying(255)
);


ALTER TABLE public.user_permission OWNER TO admin;

--
-- Name: user_permission_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE user_permission_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_permission_seq OWNER TO admin;

--
-- Name: user_permission_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('user_permission_seq', 1, false);


--
-- Name: user_requests; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE user_requests (
    request_time timestamp without time zone,
    username character varying(255),
    type_id integer,
    request_id character varying(255),
    request_data character varying(255),
    result_data character varying(255)
);


ALTER TABLE public.user_requests OWNER TO admin;

--
-- Name: users; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE users (
    id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    email character varying(255),
    password character varying(255),
    gender character varying(255),
    address character varying(255),
    country_code character varying(3),
    city_id bigint,
    birth_date date,
    personal_id_type character varying(16),
    personal_id character varying(255),
    phone_number character varying(255),
    postal_code character varying(255),
    is_admin boolean,
    supervisor_id bigint,
    corporate_code character varying(3)
);


ALTER TABLE public.users OWNER TO admin;

--
-- Name: users_groups; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE users_groups (
    users_id bigint NOT NULL,
    groups_id bigint NOT NULL
);


ALTER TABLE public.users_groups OWNER TO admin;

--
-- Name: users_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE users_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_seq OWNER TO admin;

--
-- Name: users_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('users_seq', 20, true);


--
-- Name: users_user_permission; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE users_user_permission (
    users_id bigint NOT NULL,
    user_permission_id bigint NOT NULL
);


ALTER TABLE public.users_user_permission OWNER TO admin;

--
-- Data for Name: channels; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY channels (code, name) FROM stdin;
02	Cash to Cash
11	Cash to Bank
\.


--
-- Data for Name: cities; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY cities (id, name, country_code) FROM stdin;
1	Jakarta	ID
2	Bandung	ID
3	Surabaya	ID
4	Kuala Lumpur	MY
5	Canberra	AU
6	Riyadh	SA
7	Abu Dhabi	AE
\.


--
-- Data for Name: corporates; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY corporates (code, name, style, logo_path) FROM stdin;
AAA	Simulator Agent	\N	\N
DOK	Doku	\N	\N
WAR	Warindo	\N	\N
\.


--
-- Data for Name: countries; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY countries (code, name, currency_code) FROM stdin;
ID	Indonesia	IDR
MY	Malaysia	MYR
AU	Australia	AUD
SA	Saudi Arabia	SAR
AE	United Arab Emirate	AED
\.


--
-- Data for Name: currencies; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY currencies (code) FROM stdin;
IDR
USD
AUD
MYR
SAR
AED
HKD
\.


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY customers (id, first_name, last_name, gender, address, city_id, birth_date, personal_id_type, personal_id, tax_id, phone_number, postal_code, email, agent_id) FROM stdin;
1	Bayu	Sembada	M	Jalan Merdeka Raya No.5	1	1984-02-12	CITIZENID	728127189	\N	62899272626111	12450	widhie.prihatna@mobiledoku.com	\N
2	Widhie	Prihatna	M	Jalan Bandung Cilegon 2	2	1980-06-17	CITIZENID	28192719827198	\N	6289927817291	12455	widhie@mobiledoku.com	\N
3	Faiq	Bharata	M	Jalan Ampera tujuh lima No 88	2	1977-04-04	DRIVERLICENSE	8000020222	\N	628772222221	12455	faiq@doku.com	\N
4	Fauzi	Assegaff	M	Jalan Makmur No. 35	4	1987-01-04	PASSPORTID	120009888001	\N	628998082898	12042	fauzi.assegaff@gmail.com	\N
5	Sigit	Nurseto	M	Jalan Ciledug Raya No 77	5	1987-08-08	CITIZENID	5555689222222	\N	628123488971	12041	xigitech@gmail.com	\N
\.


--
-- Data for Name: fees; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY fees (id, corporate_code, sender_country_code, beneficiary_country_code, amount, currency_code, description, domain_corporate_code) FROM stdin;
1	AAA	ID	ID	5000.00	IDR	Admin Fee	AAA
2	AAA	ID	AU	7000.00	IDR	Admin Fee	AAA
3	AAA	ID	AU	4000.00	IDR	Trans Fee	DOK
4	AAA	ID	MY	6000.00	IDR	Admin Fee	AAA
5	AAA	AU	ID	1.25	AUD	Admin Fee	AAA
\.


--
-- Data for Name: forex; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY forex (id, origin_code, destination_code, rate, created_time) FROM stdin;
1	IDR	IDR	1	2012-08-04 20:50:01
2	IDR	USD	0.00010562450489	2012-08-04 21:30:10
3	USD	IDR	9467.5	2012-08-04 21:30:10
4	IDR	AUD	0.00010107605568999999	2012-08-04 21:30:10
5	AUD	IDR	9893.5400000000009	2012-08-04 21:30:10
6	IDR	MYR	0.00033160786706999999	2012-08-04 21:30:10
7	MYR	IDR	3015.6100000000001	2012-08-04 22:10:10
\.


--
-- Data for Name: forex_references; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY forex_references (id, forex_id, initial_rate, rate, created_time) FROM stdin;
1	1	1	1	2012-08-10 07:00:00
2	2	0.00010562450489	0.00010552450488999999	2012-08-10 07:00:00
3	3	9467.5	9466.5	2012-08-10 07:00:00
4	4	0.00010107605568999999	0.00010106605569	2012-08-10 07:00:00
5	5	9893.5400000000009	9892.5400000000009	2012-08-10 07:00:00
6	6	0.00033160786706999999	0.00033060786707000002	2012-08-10 07:00:00
7	7	3015.6100000000001	3014.6100000000001	2012-08-10 07:00:00
\.


--
-- Data for Name: forex_setting; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY forex_setting (id, threshold, spread, created_time) FROM stdin;
1	2	1	2012-08-04 22:10:10
\.


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY groups (id, name) FROM stdin;
1	doku
21	main agent
31	supervisor
36	agent
\.


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY news (id, content) FROM stdin;
1	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut risus velit. Nam in nisi ipsum, quis aliquet magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam tortor augue, cursus vitae consectetur vitae, ullamcorper a lorem. Integer et urna nulla, eu sagittis enim. Maecenas ac ligula sed lacus molestie tempor. Donec id ultricies mi. Donec ornare, eros in consectetur gravida, sem elit ullamcorper mauris, sit amet scelerisque odio neque sit amet metus. Proin ac arcu sem, nec pulvinar dolor. Integer euismod posuere tellus, vel ultrices lacus dignissim a. Suspendisse vel metus nibh. Cras tincidunt pharetra lacinia. Integer ut sem enim, et blandit quam. Vestibulum in mauris eros. Vestibulum elit risus, suscipit ac placerat consequat, eleifend in lorem. Nulla facilisi.
2	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut risus velit. Nam in nisi ipsum, quis aliquet magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam tortor augue, cursus vitae consectetur vitae, ullamcorper a lorem. Integer et urna nulla, eu sagittis enim. Maecenas ac ligula sed lacus molestie tempor. Donec id ultricies mi. Donec ornare, eros in consectetur gravida, sem elit ullamcorper mauris, sit amet scelerisque odio neque sit amet metus. Proin ac arcu sem, nec pulvinar dolor. Integer euismod posuere tellus, vel ultrices lacus dignissim a. Suspendisse vel metus nibh. Cras tincidunt pharetra lacinia. Integer ut sem enim, et blandit quam. Vestibulum in mauris eros. Vestibulum elit risus, suscipit ac placerat consequat, eleifend in lorem. Nulla facilisi.
3	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut risus velit. Nam in nisi ipsum, quis aliquet magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam tortor augue, cursus vitae consectetur vitae, ullamcorper a lorem. Integer et urna nulla, eu sagittis enim. Maecenas ac ligula sed lacus molestie tempor. Donec id ultricies mi. Donec ornare, eros in consectetur gravida, sem elit ullamcorper mauris, sit amet scelerisque odio neque sit amet metus. Proin ac arcu sem, nec pulvinar dolor. Integer euismod posuere tellus, vel ultrices lacus dignissim a. Suspendisse vel metus nibh. Cras tincidunt pharetra lacinia. Integer ut sem enim, et blandit quam. Vestibulum in mauris eros. Vestibulum elit risus, suscipit ac placerat consequat, eleifend in lorem. Nulla facilisi.
4	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut risus velit. Nam in nisi ipsum, quis aliquet magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam tortor augue, cursus vitae consectetur vitae, ullamcorper a lorem. Integer et urna nulla, eu sagittis enim. Maecenas ac ligula sed lacus molestie tempor. Donec id ultricies mi. Donec ornare, eros in consectetur gravida, sem elit ullamcorper mauris, sit amet scelerisque odio neque sit amet metus. Proin ac arcu sem, nec pulvinar dolor. Integer euismod posuere tellus, vel ultrices lacus dignissim a. Suspendisse vel metus nibh. Cras tincidunt pharetra lacinia. Integer ut sem enim, et blandit quam. Vestibulum in mauris eros. Vestibulum elit risus, suscipit ac placerat consequat, eleifend in lorem. Nulla facilisi.
5	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut risus velit. Nam in nisi ipsum, quis aliquet magna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam tortor augue, cursus vitae consectetur vitae, ullamcorper a lorem. Integer et urna nulla, eu sagittis enim. Maecenas ac ligula sed lacus molestie tempor. Donec id ultricies mi. Donec ornare, eros in consectetur gravida, sem elit ullamcorper mauris, sit amet scelerisque odio neque sit amet metus. Proin ac arcu sem, nec pulvinar dolor. Integer euismod posuere tellus, vel ultrices lacus dignissim a. Suspendisse vel metus nibh. Cras tincidunt pharetra lacinia. Integer ut sem enim, et blandit quam. Vestibulum in mauris eros. Vestibulum elit risus, suscipit ac placerat consequat, eleifend in lorem. Nulla facilisi.
\.


--
-- Data for Name: play_evolutions; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY play_evolutions (id, hash, applied_at, apply_script, revert_script, state, last_problem) FROM stdin;
1	72b0c069d86d5fd6e52b8f8bb54d2fe5d653fc04	2012-08-04 00:00:00	create table channels (\ncode                      varchar(2) not null,\nname                      varchar(255),\nconstraint pk_channels primary key (code))\n;\n\ncreate table cities (\nid                        bigint not null,\nname                      varchar(255),\ncountry_code              varchar(3),\nconstraint pk_cities primary key (id))\n;\n\ncreate table corporates (\ncode                      varchar(3) not null,\nname                      varchar(255),\nstyle                     varchar(255),\nlogo_path                 varchar(255),\nconstraint pk_corporates primary key (code))\n;\n\ncreate table countries (\ncode                      varchar(3) not null,\nname                      varchar(255),\ncurrency_code             varchar(3) not null,\nconstraint pk_countries primary key (code))\n;\n\ncreate table currencies (\ncode                      varchar(3) not null,\nconstraint pk_currencies primary key (code))\n;\n\ncreate table customers (\nid                        bigint not null,\nfirst_name                varchar(64) not null,\nlast_name                 varchar(64) not null,\ngender                    varchar(255),\naddress                   varchar(255) not null,\ncity_id                   bigint not null,\nbirth_date                date,\npersonal_id_type          varchar(16),\npersonal_id               varchar(32),\ntax_id                    varchar(32),\nphone_number              varchar(20),\npostal_code               varchar(10),\nemail                     varchar(64),\nagent_id                  bigint,\nconstraint pk_customers primary key (id))\n;\n\ncreate table fees (\nid                        integer not null,\ncorporate_code            varchar(3) not null,\nsender_country_code       varchar(3) not null,\nbeneficiary_country_code  varchar(3) not null,\namount                    decimal(12,2) not null,\ncurrency_code             varchar(3) not null,\ndescription               varchar(255),\ndomain_corporate_code     varchar(3),\nconstraint pk_fees primary key (id))\n;\n\ncreate table forex (\nid                        bigint not null,\norigin_code               varchar(3) not null,\ndestination_code          varchar(3) not null,\nrate                      float not null,\ncreated_time              timestamp not null,\nconstraint pk_forex primary key (id))\n;\n\ncreate table forex_references (\nid                        bigint not null,\nforex_id                  bigint not null,\ninitial_rate              float not null,\nrate                      float not null,\ncreated_time              timestamp not null,\nconstraint pk_forex_references primary key (id))\n;\n\ncreate table forex_setting (\nid                        bigint not null,\nthreshold                 float,\nspread                    float,\ncreated_time              timestamp,\nconstraint pk_forex_setting primary key (id))\n;\n\ncreate table news (\nid                        bigint not null,\ncontent                   text,\nconstraint pk_news primary key (id))\n;\n\ncreate table groups (\nid                        bigint not null,\nname                      varchar(255),\nconstraint pk_groups primary key (id))\n;\n\ncreate table transactions (\nid                        bigint not null,\ncorporate_code            varchar(3) not null,\nuser_id                   bigint,\nchannel_code              varchar(2) not null,\ncreated_time              timestamp not null,\nstatus                    integer not null,\ncash_in_time              timestamp,\ncash_out_time             timestamp,\nsender_id                 bigint not null,\nsender_country_code       varchar(3) not null,\nsender_currency_code      varchar(3) not null,\nsender_amount             decimal(16,2) not null,\nbeneficiary_id            bigint not null,\nbeneficiary_country_code  varchar(3) not null,\nbeneficiary_currency_code varchar(3) not null,\nbeneficiary_amount        decimal(16,2) not null,\nforex_reference_id        bigint not null,\nbeneficiary_city_id       bigint,\nauth1                     varchar(255),\nauth2                     varchar(255),\nfee_included              boolean not null,\nsender_note               varchar(255),\nconstraint pk_transactions primary key (id))\n;\n\ncreate table transaction_fees (\ntransaction_id            bigint not null,\nfee_id                    integer,\namount                    decimal(12,2) not null,\ncurrency_code             varchar(3) not null,\ndescription               varchar(255),\ndomain_corporate_code     varchar(3))\n;\n\ncreate table transaction_inquiries (\nid                        bigint not null,\ntype_id                   integer not null,\nuser_id                   bigint,\nrequest_time              timestamp not null,\nupdate_time               timestamp,\ntransaction_id            bigint,\nvalidation_id             varchar(255),\ncollect_id                varchar(255),\nconstraint pk_transaction_inquiries primary key (id))\n;\n\ncreate table users (\nid                        bigint not null,\nfirst_name                varchar(255),\nlast_name                 varchar(255),\nemail                     varchar(255),\npassword                  varchar(255),\ngender                    varchar(255),\naddress                   varchar(255),\ncountry_code              varchar(3),\ncity_id                   bigint,\nbirth_date                date,\npersonal_id_type          varchar(16),\npersonal_id               varchar(255),\nphone_number              varchar(255),\npostal_code               varchar(255),\nis_admin                  boolean,\nsupervisor_id             bigint,\ncorporate_code            varchar(3),\nconstraint pk_users primary key (id))\n;\n\ncreate table user_permission (\nid                        bigint not null,\nvalue                     varchar(255),\nconstraint pk_user_permission primary key (id))\n;\n\ncreate table user_requests (\nrequest_time              timestamp,\nusername                  varchar(255),\ntype_id                   integer,\nrequest_id                varchar(255),\nrequest_data              varchar(255),\nresult_data               varchar(255))\n;\n\n\ncreate table users_groups (\nusers_id                       bigint not null,\ngroups_id                      bigint not null,\nconstraint pk_users_groups primary key (users_id, groups_id))\n;\n\ncreate table users_user_permission (\nusers_id                       bigint not null,\nuser_permission_id             bigint not null,\nconstraint pk_users_user_permission primary key (users_id, user_permission_id))\n;\ncreate sequence channels_seq;\n\ncreate sequence cities_seq;\n\ncreate sequence corporates_seq;\n\ncreate sequence countries_seq;\n\ncreate sequence currencies_seq;\n\ncreate sequence customers_seq;\n\ncreate sequence fees_seq;\n\ncreate sequence forex_seq;\n\ncreate sequence forex_references_seq;\n\ncreate sequence forex_setting_seq;\n\ncreate sequence news_seq;\n\ncreate sequence groups_seq;\n\ncreate sequence transactions_seq;\n\ncreate sequence transaction_inquiries_seq;\n\ncreate sequence users_seq;\n\ncreate sequence user_permission_seq;\n\nalter table cities add constraint fk_cities_country_1 foreign key (country_code) references countries (code);\ncreate index ix_cities_country_1 on cities (country_code);\nalter table countries add constraint fk_countries_currency_2 foreign key (currency_code) references currencies (code);\ncreate index ix_countries_currency_2 on countries (currency_code);\nalter table customers add constraint fk_customers_city_3 foreign key (city_id) references cities (id);\ncreate index ix_customers_city_3 on customers (city_id);\nalter table customers add constraint fk_customers_agent_4 foreign key (agent_id) references users (id);\ncreate index ix_customers_agent_4 on customers (agent_id);\nalter table fees add constraint fk_fees_corporate_5 foreign key (corporate_code) references corporates (code);\ncreate index ix_fees_corporate_5 on fees (corporate_code);\nalter table fees add constraint fk_fees_senderCountry_6 foreign key (sender_country_code) references countries (code);\ncreate index ix_fees_senderCountry_6 on fees (sender_country_code);\nalter table fees add constraint fk_fees_beneficiaryCountry_7 foreign key (beneficiary_country_code) references countries (code);\ncreate index ix_fees_beneficiaryCountry_7 on fees (beneficiary_country_code);\nalter table fees add constraint fk_fees_currency_8 foreign key (currency_code) references currencies (code);\ncreate index ix_fees_currency_8 on fees (currency_code);\nalter table fees add constraint fk_fees_domainCorporate_9 foreign key (domain_corporate_code) references corporates (code);\ncreate index ix_fees_domainCorporate_9 on fees (domain_corporate_code);\nalter table forex add constraint fk_forex_origin_10 foreign key (origin_code) references currencies (code);\ncreate index ix_forex_origin_10 on forex (origin_code);\nalter table forex add constraint fk_forex_destination_11 foreign key (destination_code) references currencies (code);\ncreate index ix_forex_destination_11 on forex (destination_code);\nalter table forex_references add constraint fk_forex_references_forex_12 foreign key (forex_id) references forex (id);\ncreate index ix_forex_references_forex_12 on forex_references (forex_id);\nalter table transactions add constraint fk_transactions_corporate_13 foreign key (corporate_code) references corporates (code);\ncreate index ix_transactions_corporate_13 on transactions (corporate_code);\nalter table transactions add constraint fk_transactions_user_14 foreign key (user_id) references users (id);\ncreate index ix_transactions_user_14 on transactions (user_id);\nalter table transactions add constraint fk_transactions_channel_15 foreign key (channel_code) references channels (code);\ncreate index ix_transactions_channel_15 on transactions (channel_code);\nalter table transactions add constraint fk_transactions_sender_16 foreign key (sender_id) references customers (id);\ncreate index ix_transactions_sender_16 on transactions (sender_id);\nalter table transactions add constraint fk_transactions_senderCountry_17 foreign key (sender_country_code) references countries (code);\ncreate index ix_transactions_senderCountry_17 on transactions (sender_country_code);\nalter table transactions add constraint fk_transactions_senderCurrenc_18 foreign key (sender_currency_code) references currencies (code);\ncreate index ix_transactions_senderCurrenc_18 on transactions (sender_currency_code);\nalter table transactions add constraint fk_transactions_beneficiary_19 foreign key (beneficiary_id) references customers (id);\ncreate index ix_transactions_beneficiary_19 on transactions (beneficiary_id);\nalter table transactions add constraint fk_transactions_beneficiaryCo_20 foreign key (beneficiary_country_code) references countries (code);\ncreate index ix_transactions_beneficiaryCo_20 on transactions (beneficiary_country_code);\nalter table transactions add constraint fk_transactions_beneficiaryCu_21 foreign key (beneficiary_currency_code) references currencies (code);\ncreate index ix_transactions_beneficiaryCu_21 on transactions (beneficiary_currency_code);\nalter table transactions add constraint fk_transactions_forexReferenc_22 foreign key (forex_reference_id) references forex_references (id);\ncreate index ix_transactions_forexReferenc_22 on transactions (forex_reference_id);\nalter table transactions add constraint fk_transactions_beneficiaryCi_23 foreign key (beneficiary_city_id) references cities (id);\ncreate index ix_transactions_beneficiaryCi_23 on transactions (beneficiary_city_id);\nalter table transaction_fees add constraint fk_transaction_fees_transacti_24 foreign key (transaction_id) references transactions (id);\ncreate index ix_transaction_fees_transacti_24 on transaction_fees (transaction_id);\nalter table transaction_fees add constraint fk_transaction_fees_fee_25 foreign key (fee_id) references fees (id);\ncreate index ix_transaction_fees_fee_25 on transaction_fees (fee_id);\nalter table transaction_fees add constraint fk_transaction_fees_currency_26 foreign key (currency_code) references currencies (code);\ncreate index ix_transaction_fees_currency_26 on transaction_fees (currency_code);\nalter table transaction_fees add constraint fk_transaction_fees_domainCor_27 foreign key (domain_corporate_code) references corporates (code);\ncreate index ix_transaction_fees_domainCor_27 on transaction_fees (domain_corporate_code);\nalter table transaction_inquiries add constraint fk_transaction_inquiries_user_28 foreign key (user_id) references users (id);\ncreate index ix_transaction_inquiries_user_28 on transaction_inquiries (user_id);\nalter table transaction_inquiries add constraint fk_transaction_inquiries_tran_29 foreign key (transaction_id) references transactions (id);\ncreate index ix_transaction_inquiries_tran_29 on transaction_inquiries (transaction_id);\nalter table users add constraint fk_users_country_30 foreign key (country_code) references countries (code);\ncreate index ix_users_country_30 on users (country_code);\nalter table users add constraint fk_users_city_31 foreign key (city_id) references cities (id);\ncreate index ix_users_city_31 on users (city_id);\nalter table users add constraint fk_users_supervisor_32 foreign key (supervisor_id) references users (id);\ncreate index ix_users_supervisor_32 on users (supervisor_id);\nalter table users add constraint fk_users_corporate_33 foreign key (corporate_code) references corporates (code);\ncreate index ix_users_corporate_33 on users (corporate_code);\n\n\n\nalter table users_groups add constraint fk_users_groups_users_01 foreign key (users_id) references users (id);\n\nalter table users_groups add constraint fk_users_groups_groups_02 foreign key (groups_id) references groups (id);\n\nalter table users_user_permission add constraint fk_users_user_permission_user_01 foreign key (users_id) references users (id);\n\nalter table users_user_permission add constraint fk_users_user_permission_user_02 foreign key (user_permission_id) references user_permission (id);	drop table if exists channels cascade;\n\ndrop table if exists cities cascade;\n\ndrop table if exists corporates cascade;\n\ndrop table if exists countries cascade;\n\ndrop table if exists currencies cascade;\n\ndrop table if exists customers cascade;\n\ndrop table if exists fees cascade;\n\ndrop table if exists forex cascade;\n\ndrop table if exists forex_references cascade;\n\ndrop table if exists forex_setting cascade;\n\ndrop table if exists news cascade;\n\ndrop table if exists groups cascade;\n\ndrop table if exists transactions cascade;\n\ndrop table if exists transaction_fees cascade;\n\ndrop table if exists transaction_inquiries cascade;\n\ndrop table if exists users cascade;\n\ndrop table if exists users_groups cascade;\n\ndrop table if exists users_user_permission cascade;\n\ndrop table if exists user_permission cascade;\n\ndrop table if exists user_requests cascade;\n\ndrop sequence if exists channels_seq;\n\ndrop sequence if exists cities_seq;\n\ndrop sequence if exists corporates_seq;\n\ndrop sequence if exists countries_seq;\n\ndrop sequence if exists currencies_seq;\n\ndrop sequence if exists customers_seq;\n\ndrop sequence if exists fees_seq;\n\ndrop sequence if exists forex_seq;\n\ndrop sequence if exists forex_references_seq;\n\ndrop sequence if exists forex_setting_seq;\n\ndrop sequence if exists news_seq;\n\ndrop sequence if exists groups_seq;\n\ndrop sequence if exists transactions_seq;\n\ndrop sequence if exists transaction_inquiries_seq;\n\ndrop sequence if exists users_seq;\n\ndrop sequence if exists user_permission_seq;	applied	
\.


--
-- Data for Name: transaction_fees; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY transaction_fees (transaction_id, fee_id, amount, currency_code, description, domain_corporate_code) FROM stdin;
1	2	7000.00	IDR	Admin Fee	AAA
1	3	4000.00	IDR	Trans Fee	DOK
26	4	6000.00	IDR	Admin Fee	AAA
27	4	6000.00	IDR	Admin Fee	AAA
28	5	1.25	AUD	Admin Fee	AAA
29	5	1.25	AUD	Admin Fee	AAA
30	5	1.25	AUD	Admin Fee	AAA
31	2	7000.00	IDR	Admin Fee	AAA
31	3	4000.00	IDR	Trans Fee	DOK
32	2	7000.00	IDR	Admin Fee	AAA
32	3	4000.00	IDR	Trans Fee	DOK
33	2	7000.00	IDR	Admin Fee	AAA
33	3	4000.00	IDR	Trans Fee	DOK
39	4	6000.00	IDR	Admin Fee	AAA
40	4	6000.00	IDR	Admin Fee	AAA
41	5	1.25	AUD	Admin Fee	AAA
42	5	1.25	AUD	Admin Fee	AAA
43	5	1.25	AUD	Admin Fee	AAA
44	2	7000.00	IDR	Admin Fee	AAA
44	3	4000.00	IDR	Trans Fee	DOK
45	2	7000.00	IDR	Admin Fee	AAA
45	3	4000.00	IDR	Trans Fee	DOK
46	2	7000.00	IDR	Admin Fee	AAA
46	3	4000.00	IDR	Trans Fee	DOK
52	4	6000.00	IDR	Admin Fee	AAA
53	4	6000.00	IDR	Admin Fee	AAA
54	5	1.25	AUD	Admin Fee	AAA
55	5	1.25	AUD	Admin Fee	AAA
56	5	1.25	AUD	Admin Fee	AAA
57	2	7000.00	IDR	Admin Fee	AAA
57	3	4000.00	IDR	Trans Fee	DOK
58	2	7000.00	IDR	Admin Fee	AAA
58	3	4000.00	IDR	Trans Fee	DOK
59	2	7000.00	IDR	Admin Fee	AAA
59	3	4000.00	IDR	Trans Fee	DOK
65	4	6000.00	IDR	Admin Fee	AAA
66	4	6000.00	IDR	Admin Fee	AAA
67	5	1.25	AUD	Admin Fee	AAA
68	5	1.25	AUD	Admin Fee	AAA
69	5	1.25	AUD	Admin Fee	AAA
70	2	7000.00	IDR	Admin Fee	AAA
70	3	4000.00	IDR	Trans Fee	DOK
71	2	7000.00	IDR	Admin Fee	AAA
71	3	4000.00	IDR	Trans Fee	DOK
72	2	7000.00	IDR	Admin Fee	AAA
72	3	4000.00	IDR	Trans Fee	DOK
78	4	6000.00	IDR	Admin Fee	AAA
79	4	6000.00	IDR	Admin Fee	AAA
80	5	1.25	AUD	Admin Fee	AAA
81	5	1.25	AUD	Admin Fee	AAA
82	5	1.25	AUD	Admin Fee	AAA
83	2	7000.00	IDR	Admin Fee	AAA
83	3	4000.00	IDR	Trans Fee	DOK
84	2	7000.00	IDR	Admin Fee	AAA
84	3	4000.00	IDR	Trans Fee	DOK
85	2	7000.00	IDR	Admin Fee	AAA
85	3	4000.00	IDR	Trans Fee	DOK
91	4	6000.00	IDR	Admin Fee	AAA
92	4	6000.00	IDR	Admin Fee	AAA
93	5	1.25	AUD	Admin Fee	AAA
94	5	1.25	AUD	Admin Fee	AAA
95	5	1.25	AUD	Admin Fee	AAA
96	2	7000.00	IDR	Admin Fee	AAA
96	3	4000.00	IDR	Trans Fee	DOK
97	2	7000.00	IDR	Admin Fee	AAA
97	3	4000.00	IDR	Trans Fee	DOK
98	2	7000.00	IDR	Admin Fee	AAA
98	3	4000.00	IDR	Trans Fee	DOK
104	4	6000.00	IDR	Admin Fee	AAA
105	4	6000.00	IDR	Admin Fee	AAA
106	5	1.25	AUD	Admin Fee	AAA
107	5	1.25	AUD	Admin Fee	AAA
108	5	1.25	AUD	Admin Fee	AAA
109	2	7000.00	IDR	Admin Fee	AAA
109	3	4000.00	IDR	Trans Fee	DOK
110	2	7000.00	IDR	Admin Fee	AAA
110	3	4000.00	IDR	Trans Fee	DOK
111	2	7000.00	IDR	Admin Fee	AAA
111	3	4000.00	IDR	Trans Fee	DOK
117	4	6000.00	IDR	Admin Fee	AAA
118	4	6000.00	IDR	Admin Fee	AAA
119	5	1.25	AUD	Admin Fee	AAA
120	5	1.25	AUD	Admin Fee	AAA
121	5	1.25	AUD	Admin Fee	AAA
122	2	7000.00	IDR	Admin Fee	AAA
122	3	4000.00	IDR	Trans Fee	DOK
123	2	7000.00	IDR	Admin Fee	AAA
123	3	4000.00	IDR	Trans Fee	DOK
124	2	7000.00	IDR	Admin Fee	AAA
124	3	4000.00	IDR	Trans Fee	DOK
130	4	6000.00	IDR	Admin Fee	AAA
131	4	6000.00	IDR	Admin Fee	AAA
132	5	1.25	AUD	Admin Fee	AAA
133	5	1.25	AUD	Admin Fee	AAA
139	4	6000.00	IDR	Admin Fee	AAA
140	4	6000.00	IDR	Admin Fee	AAA
141	5	1.25	AUD	Admin Fee	AAA
142	5	1.25	AUD	Admin Fee	AAA
143	5	1.25	AUD	Admin Fee	AAA
144	2	7000.00	IDR	Admin Fee	AAA
144	3	4000.00	IDR	Trans Fee	DOK
145	2	7000.00	IDR	Admin Fee	AAA
145	3	4000.00	IDR	Trans Fee	DOK
146	2	7000.00	IDR	Admin Fee	AAA
146	3	4000.00	IDR	Trans Fee	DOK
152	4	6000.00	IDR	Admin Fee	AAA
153	4	6000.00	IDR	Admin Fee	AAA
154	5	1.25	AUD	Admin Fee	AAA
155	5	1.25	AUD	Admin Fee	AAA
156	5	1.25	AUD	Admin Fee	AAA
157	2	7000.00	IDR	Admin Fee	AAA
157	3	4000.00	IDR	Trans Fee	DOK
158	2	7000.00	IDR	Admin Fee	AAA
158	3	4000.00	IDR	Trans Fee	DOK
159	2	7000.00	IDR	Admin Fee	AAA
159	3	4000.00	IDR	Trans Fee	DOK
165	4	6000.00	IDR	Admin Fee	AAA
166	4	6000.00	IDR	Admin Fee	AAA
167	5	1.25	AUD	Admin Fee	AAA
168	5	1.25	AUD	Admin Fee	AAA
169	5	1.25	AUD	Admin Fee	AAA
183	3	4000.00	IDR	Trans Fee	DOK
184	2	7000.00	IDR	Admin Fee	AAA
207	5	1.25	AUD	Admin Fee	AAA
208	5	1.25	AUD	Admin Fee	AAA
209	2	7000.00	IDR	Admin Fee	AAA
217	4	6000.00	IDR	Admin Fee	AAA
218	4	6000.00	IDR	Admin Fee	AAA
219	5	1.25	AUD	Admin Fee	AAA
220	5	1.25	AUD	Admin Fee	AAA
221	5	1.25	AUD	Admin Fee	AAA
222	2	7000.00	IDR	Admin Fee	AAA
230	4	6000.00	IDR	Admin Fee	AAA
170	2	7000.00	IDR	Admin Fee	AAA
184	3	4000.00	IDR	Trans Fee	DOK
196	3	4000.00	IDR	Trans Fee	DOK
197	2	7000.00	IDR	Admin Fee	AAA
222	3	4000.00	IDR	Trans Fee	DOK
223	2	7000.00	IDR	Admin Fee	AAA
235	3	4000.00	IDR	Trans Fee	DOK
236	2	7000.00	IDR	Admin Fee	AAA
170	3	4000.00	IDR	Trans Fee	DOK
171	2	7000.00	IDR	Admin Fee	AAA
185	2	7000.00	IDR	Admin Fee	AAA
198	3	4000.00	IDR	Trans Fee	DOK
211	3	4000.00	IDR	Trans Fee	DOK
171	3	4000.00	IDR	Trans Fee	DOK
172	2	7000.00	IDR	Admin Fee	AAA
185	3	4000.00	IDR	Trans Fee	DOK
197	3	4000.00	IDR	Trans Fee	DOK
198	2	7000.00	IDR	Admin Fee	AAA
210	2	7000.00	IDR	Admin Fee	AAA
224	3	4000.00	IDR	Trans Fee	DOK
236	3	4000.00	IDR	Trans Fee	DOK
237	2	7000.00	IDR	Admin Fee	AAA
172	3	4000.00	IDR	Trans Fee	DOK
210	3	4000.00	IDR	Trans Fee	DOK
211	2	7000.00	IDR	Admin Fee	AAA
237	3	4000.00	IDR	Trans Fee	DOK
178	4	6000.00	IDR	Admin Fee	AAA
179	4	6000.00	IDR	Admin Fee	AAA
180	5	1.25	AUD	Admin Fee	AAA
181	5	1.25	AUD	Admin Fee	AAA
182	5	1.25	AUD	Admin Fee	AAA
183	2	7000.00	IDR	Admin Fee	AAA
191	4	6000.00	IDR	Admin Fee	AAA
192	4	6000.00	IDR	Admin Fee	AAA
193	5	1.25	AUD	Admin Fee	AAA
194	5	1.25	AUD	Admin Fee	AAA
195	5	1.25	AUD	Admin Fee	AAA
196	2	7000.00	IDR	Admin Fee	AAA
204	4	6000.00	IDR	Admin Fee	AAA
205	4	6000.00	IDR	Admin Fee	AAA
206	5	1.25	AUD	Admin Fee	AAA
209	3	4000.00	IDR	Trans Fee	DOK
223	3	4000.00	IDR	Trans Fee	DOK
224	2	7000.00	IDR	Admin Fee	AAA
231	4	6000.00	IDR	Admin Fee	AAA
232	5	1.25	AUD	Admin Fee	AAA
233	5	1.25	AUD	Admin Fee	AAA
234	5	1.25	AUD	Admin Fee	AAA
235	2	7000.00	IDR	Admin Fee	AAA
243	4	6000.00	IDR	Admin Fee	AAA
244	4	6000.00	IDR	Admin Fee	AAA
245	5	1.25	AUD	Admin Fee	AAA
246	5	1.25	AUD	Admin Fee	AAA
252	4	6000.00	IDR	Admin Fee	AAA
253	4	6000.00	IDR	Admin Fee	AAA
254	5	1.25	AUD	Admin Fee	AAA
255	5	1.25	AUD	Admin Fee	AAA
256	5	1.25	AUD	Admin Fee	AAA
257	2	7000.00	IDR	Admin Fee	AAA
257	3	4000.00	IDR	Trans Fee	DOK
258	2	7000.00	IDR	Admin Fee	AAA
258	3	4000.00	IDR	Trans Fee	DOK
259	2	7000.00	IDR	Admin Fee	AAA
259	3	4000.00	IDR	Trans Fee	DOK
265	4	6000.00	IDR	Admin Fee	AAA
266	4	6000.00	IDR	Admin Fee	AAA
267	5	1.25	AUD	Admin Fee	AAA
268	5	1.25	AUD	Admin Fee	AAA
269	5	1.25	AUD	Admin Fee	AAA
270	2	7000.00	IDR	Admin Fee	AAA
270	3	4000.00	IDR	Trans Fee	DOK
271	2	7000.00	IDR	Admin Fee	AAA
271	3	4000.00	IDR	Trans Fee	DOK
272	2	7000.00	IDR	Admin Fee	AAA
272	3	4000.00	IDR	Trans Fee	DOK
278	4	6000.00	IDR	Admin Fee	AAA
279	4	6000.00	IDR	Admin Fee	AAA
280	5	1.25	AUD	Admin Fee	AAA
281	5	1.25	AUD	Admin Fee	AAA
282	5	1.25	AUD	Admin Fee	AAA
283	2	7000.00	IDR	Admin Fee	AAA
283	3	4000.00	IDR	Trans Fee	DOK
284	2	7000.00	IDR	Admin Fee	AAA
284	3	4000.00	IDR	Trans Fee	DOK
285	2	7000.00	IDR	Admin Fee	AAA
285	3	4000.00	IDR	Trans Fee	DOK
291	4	6000.00	IDR	Admin Fee	AAA
292	4	6000.00	IDR	Admin Fee	AAA
293	5	1.25	AUD	Admin Fee	AAA
294	5	1.25	AUD	Admin Fee	AAA
295	5	1.25	AUD	Admin Fee	AAA
296	2	7000.00	IDR	Admin Fee	AAA
296	3	4000.00	IDR	Trans Fee	DOK
297	2	7000.00	IDR	Admin Fee	AAA
297	3	4000.00	IDR	Trans Fee	DOK
298	2	7000.00	IDR	Admin Fee	AAA
298	3	4000.00	IDR	Trans Fee	DOK
304	4	6000.00	IDR	Admin Fee	AAA
305	4	6000.00	IDR	Admin Fee	AAA
306	5	1.25	AUD	Admin Fee	AAA
307	5	1.25	AUD	Admin Fee	AAA
308	5	1.25	AUD	Admin Fee	AAA
309	2	7000.00	IDR	Admin Fee	AAA
309	3	4000.00	IDR	Trans Fee	DOK
310	2	7000.00	IDR	Admin Fee	AAA
310	3	4000.00	IDR	Trans Fee	DOK
311	2	7000.00	IDR	Admin Fee	AAA
311	3	4000.00	IDR	Trans Fee	DOK
317	4	6000.00	IDR	Admin Fee	AAA
318	4	6000.00	IDR	Admin Fee	AAA
319	5	1.25	AUD	Admin Fee	AAA
320	5	1.25	AUD	Admin Fee	AAA
321	5	1.25	AUD	Admin Fee	AAA
322	2	7000.00	IDR	Admin Fee	AAA
322	3	4000.00	IDR	Trans Fee	DOK
323	2	7000.00	IDR	Admin Fee	AAA
323	3	4000.00	IDR	Trans Fee	DOK
324	2	7000.00	IDR	Admin Fee	AAA
324	3	4000.00	IDR	Trans Fee	DOK
330	4	6000.00	IDR	Admin Fee	AAA
331	4	6000.00	IDR	Admin Fee	AAA
332	5	1.25	AUD	Admin Fee	AAA
333	5	1.25	AUD	Admin Fee	AAA
334	5	1.25	AUD	Admin Fee	AAA
335	2	7000.00	IDR	Admin Fee	AAA
335	3	4000.00	IDR	Trans Fee	DOK
336	2	7000.00	IDR	Admin Fee	AAA
336	3	4000.00	IDR	Trans Fee	DOK
337	2	7000.00	IDR	Admin Fee	AAA
337	3	4000.00	IDR	Trans Fee	DOK
343	4	6000.00	IDR	Admin Fee	AAA
344	4	6000.00	IDR	Admin Fee	AAA
345	5	1.25	AUD	Admin Fee	AAA
346	5	1.25	AUD	Admin Fee	AAA
347	5	1.25	AUD	Admin Fee	AAA
348	2	7000.00	IDR	Admin Fee	AAA
348	3	4000.00	IDR	Trans Fee	DOK
349	2	7000.00	IDR	Admin Fee	AAA
349	3	4000.00	IDR	Trans Fee	DOK
350	2	7000.00	IDR	Admin Fee	AAA
350	3	4000.00	IDR	Trans Fee	DOK
356	4	6000.00	IDR	Admin Fee	AAA
357	4	6000.00	IDR	Admin Fee	AAA
358	5	1.25	AUD	Admin Fee	AAA
359	5	1.25	AUD	Admin Fee	AAA
365	4	6000.00	IDR	Admin Fee	AAA
366	4	6000.00	IDR	Admin Fee	AAA
372	2	7000.00	IDR	Admin Fee	AAA
378	4	6000.00	IDR	Admin Fee	AAA
384	3	4000.00	IDR	Trans Fee	DOK
393	5	1.25	AUD	Admin Fee	AAA
395	5	1.25	AUD	Admin Fee	AAA
408	5	1.25	AUD	Admin Fee	AAA
417	4	6000.00	IDR	Admin Fee	AAA
422	2	7000.00	IDR	Admin Fee	AAA
423	3	4000.00	IDR	Trans Fee	DOK
436	2	7000.00	IDR	Admin Fee	AAA
444	4	6000.00	IDR	Admin Fee	AAA
460	5	1.25	AUD	Admin Fee	AAA
461	3	4000.00	IDR	Trans Fee	DOK
462	3	4000.00	IDR	Trans Fee	DOK
469	4	6000.00	IDR	Admin Fee	AAA
472	5	1.25	AUD	Admin Fee	AAA
479	4	6000.00	IDR	Admin Fee	AAA
485	2	7000.00	IDR	Admin Fee	AAA
496	2	7000.00	IDR	Admin Fee	AAA
507	5	1.25	AUD	Admin Fee	AAA
524	2	7000.00	IDR	Admin Fee	AAA
535	3	4000.00	IDR	Trans Fee	DOK
548	2	7000.00	IDR	Admin Fee	AAA
550	3	4000.00	IDR	Trans Fee	DOK
563	3	4000.00	IDR	Trans Fee	DOK
570	4	6000.00	IDR	Admin Fee	AAA
593	5	1.25	AUD	Admin Fee	AAA
607	5	1.25	AUD	Admin Fee	AAA
610	3	4000.00	IDR	Trans Fee	DOK
624	2	7000.00	IDR	Admin Fee	AAA
645	5	1.25	AUD	Admin Fee	AAA
648	3	4000.00	IDR	Trans Fee	DOK
659	5	1.25	AUD	Admin Fee	AAA
663	2	7000.00	IDR	Admin Fee	AAA
674	2	7000.00	IDR	Admin Fee	AAA
689	3	4000.00	IDR	Trans Fee	DOK
697	5	1.25	AUD	Admin Fee	AAA
367	5	1.25	AUD	Admin Fee	AAA
383	3	4000.00	IDR	Trans Fee	DOK
391	4	6000.00	IDR	Admin Fee	AAA
398	3	4000.00	IDR	Trans Fee	DOK
422	3	4000.00	IDR	Trans Fee	DOK
435	3	4000.00	IDR	Trans Fee	DOK
445	5	1.25	AUD	Admin Fee	AAA
457	4	6000.00	IDR	Admin Fee	AAA
461	2	7000.00	IDR	Admin Fee	AAA
471	5	1.25	AUD	Admin Fee	AAA
498	2	7000.00	IDR	Admin Fee	AAA
504	4	6000.00	IDR	Admin Fee	AAA
530	4	6000.00	IDR	Admin Fee	AAA
544	4	6000.00	IDR	Admin Fee	AAA
548	3	4000.00	IDR	Trans Fee	DOK
559	5	1.25	AUD	Admin Fee	AAA
563	2	7000.00	IDR	Admin Fee	AAA
592	4	6000.00	IDR	Admin Fee	AAA
598	2	7000.00	IDR	Admin Fee	AAA
611	2	7000.00	IDR	Admin Fee	AAA
618	4	6000.00	IDR	Admin Fee	AAA
622	3	4000.00	IDR	Trans Fee	DOK
646	5	1.25	AUD	Admin Fee	AAA
656	4	6000.00	IDR	Admin Fee	AAA
658	5	1.25	AUD	Admin Fee	AAA
662	2	7000.00	IDR	Admin Fee	AAA
684	5	1.25	AUD	Admin Fee	AAA
695	4	6000.00	IDR	Admin Fee	AAA
698	5	1.25	AUD	Admin Fee	AAA
368	5	1.25	AUD	Admin Fee	AAA
370	3	4000.00	IDR	Trans Fee	DOK
379	4	6000.00	IDR	Admin Fee	AAA
382	5	1.25	AUD	Admin Fee	AAA
396	2	7000.00	IDR	Admin Fee	AAA
409	3	4000.00	IDR	Trans Fee	DOK
410	3	4000.00	IDR	Trans Fee	DOK
437	2	7000.00	IDR	Admin Fee	AAA
459	5	1.25	AUD	Admin Fee	AAA
470	4	6000.00	IDR	Admin Fee	AAA
482	5	1.25	AUD	Admin Fee	AAA
492	4	6000.00	IDR	Admin Fee	AAA
496	3	4000.00	IDR	Trans Fee	DOK
497	3	4000.00	IDR	Trans Fee	DOK
510	2	7000.00	IDR	Admin Fee	AAA
511	2	7000.00	IDR	Admin Fee	AAA
518	4	6000.00	IDR	Admin Fee	AAA
536	3	4000.00	IDR	Trans Fee	DOK
560	5	1.25	AUD	Admin Fee	AAA
572	5	1.25	AUD	Admin Fee	AAA
575	2	7000.00	IDR	Admin Fee	AAA
619	5	1.25	AUD	Admin Fee	AAA
622	2	7000.00	IDR	Admin Fee	AAA
636	2	7000.00	IDR	Admin Fee	AAA
637	3	4000.00	IDR	Trans Fee	DOK
643	4	6000.00	IDR	Admin Fee	AAA
649	3	4000.00	IDR	Trans Fee	DOK
650	3	4000.00	IDR	Trans Fee	DOK
675	2	7000.00	IDR	Admin Fee	AAA
369	5	1.25	AUD	Admin Fee	AAA
383	2	7000.00	IDR	Admin Fee	AAA
398	2	7000.00	IDR	Admin Fee	AAA
405	4	6000.00	IDR	Admin Fee	AAA
407	5	1.25	AUD	Admin Fee	AAA
418	4	6000.00	IDR	Admin Fee	AAA
447	5	1.25	AUD	Admin Fee	AAA
458	5	1.25	AUD	Admin Fee	AAA
463	2	7000.00	IDR	Admin Fee	AAA
483	3	4000.00	IDR	Trans Fee	DOK
494	5	1.25	AUD	Admin Fee	AAA
519	5	1.25	AUD	Admin Fee	AAA
524	3	4000.00	IDR	Trans Fee	DOK
557	4	6000.00	IDR	Admin Fee	AAA
576	2	7000.00	IDR	Admin Fee	AAA
582	4	6000.00	IDR	Admin Fee	AAA
596	3	4000.00	IDR	Trans Fee	DOK
609	2	7000.00	IDR	Admin Fee	AAA
610	2	7000.00	IDR	Admin Fee	AAA
623	3	4000.00	IDR	Trans Fee	DOK
632	5	1.25	AUD	Admin Fee	AAA
634	5	1.25	AUD	Admin Fee	AAA
657	4	6000.00	IDR	Admin Fee	AAA
676	3	4000.00	IDR	Trans Fee	DOK
685	5	1.25	AUD	Admin Fee	AAA
687	2	7000.00	IDR	Admin Fee	AAA
688	3	4000.00	IDR	Trans Fee	DOK
370	2	7000.00	IDR	Admin Fee	AAA
372	3	4000.00	IDR	Trans Fee	DOK
385	2	7000.00	IDR	Admin Fee	AAA
392	4	6000.00	IDR	Admin Fee	AAA
396	3	4000.00	IDR	Trans Fee	DOK
409	2	7000.00	IDR	Admin Fee	AAA
424	2	7000.00	IDR	Admin Fee	AAA
435	2	7000.00	IDR	Admin Fee	AAA
436	3	4000.00	IDR	Trans Fee	DOK
446	5	1.25	AUD	Admin Fee	AAA
462	2	7000.00	IDR	Admin Fee	AAA
478	4	6000.00	IDR	Admin Fee	AAA
481	5	1.25	AUD	Admin Fee	AAA
483	2	7000.00	IDR	Admin Fee	AAA
484	3	4000.00	IDR	Trans Fee	DOK
493	5	1.25	AUD	Admin Fee	AAA
509	2	7000.00	IDR	Admin Fee	AAA
510	3	4000.00	IDR	Trans Fee	DOK
537	2	7000.00	IDR	Admin Fee	AAA
543	4	6000.00	IDR	Admin Fee	AAA
561	2	7000.00	IDR	Admin Fee	AAA
562	2	7000.00	IDR	Admin Fee	AAA
574	2	7000.00	IDR	Admin Fee	AAA
576	3	4000.00	IDR	Trans Fee	DOK
585	5	1.25	AUD	Admin Fee	AAA
595	5	1.25	AUD	Admin Fee	AAA
597	3	4000.00	IDR	Trans Fee	DOK
606	5	1.25	AUD	Admin Fee	AAA
609	3	4000.00	IDR	Trans Fee	DOK
621	5	1.25	AUD	Admin Fee	AAA
637	2	7000.00	IDR	Admin Fee	AAA
648	2	7000.00	IDR	Admin Fee	AAA
661	3	4000.00	IDR	Trans Fee	DOK
670	4	6000.00	IDR	Admin Fee	AAA
683	4	6000.00	IDR	Admin Fee	AAA
687	3	4000.00	IDR	Trans Fee	DOK
371	2	7000.00	IDR	Admin Fee	AAA
384	2	7000.00	IDR	Admin Fee	AAA
431	4	6000.00	IDR	Admin Fee	AAA
449	2	7000.00	IDR	Admin Fee	AAA
505	4	6000.00	IDR	Admin Fee	AAA
508	5	1.25	AUD	Admin Fee	AAA
509	3	4000.00	IDR	Trans Fee	DOK
521	5	1.25	AUD	Admin Fee	AAA
522	3	4000.00	IDR	Trans Fee	DOK
532	5	1.25	AUD	Admin Fee	AAA
534	5	1.25	AUD	Admin Fee	AAA
547	5	1.25	AUD	Admin Fee	AAA
556	4	6000.00	IDR	Admin Fee	AAA
597	2	7000.00	IDR	Admin Fee	AAA
608	5	1.25	AUD	Admin Fee	AAA
630	4	6000.00	IDR	Admin Fee	AAA
662	3	4000.00	IDR	Trans Fee	DOK
675	3	4000.00	IDR	Trans Fee	DOK
371	3	4000.00	IDR	Trans Fee	DOK
381	5	1.25	AUD	Admin Fee	AAA
397	2	7000.00	IDR	Admin Fee	AAA
420	5	1.25	AUD	Admin Fee	AAA
423	2	7000.00	IDR	Admin Fee	AAA
424	3	4000.00	IDR	Trans Fee	DOK
448	3	4000.00	IDR	Trans Fee	DOK
449	3	4000.00	IDR	Trans Fee	DOK
450	3	4000.00	IDR	Trans Fee	DOK
480	5	1.25	AUD	Admin Fee	AAA
495	5	1.25	AUD	Admin Fee	AAA
517	4	6000.00	IDR	Admin Fee	AAA
523	2	7000.00	IDR	Admin Fee	AAA
531	4	6000.00	IDR	Admin Fee	AAA
533	5	1.25	AUD	Admin Fee	AAA
535	2	7000.00	IDR	Admin Fee	AAA
562	3	4000.00	IDR	Trans Fee	DOK
571	5	1.25	AUD	Admin Fee	AAA
573	5	1.25	AUD	Admin Fee	AAA
591	4	6000.00	IDR	Admin Fee	AAA
594	5	1.25	AUD	Admin Fee	AAA
604	4	6000.00	IDR	Admin Fee	AAA
620	5	1.25	AUD	Admin Fee	AAA
623	2	7000.00	IDR	Admin Fee	AAA
624	3	4000.00	IDR	Trans Fee	DOK
635	3	4000.00	IDR	Trans Fee	DOK
644	4	6000.00	IDR	Admin Fee	AAA
647	5	1.25	AUD	Admin Fee	AAA
660	5	1.25	AUD	Admin Fee	AAA
673	5	1.25	AUD	Admin Fee	AAA
674	3	4000.00	IDR	Trans Fee	DOK
686	5	1.25	AUD	Admin Fee	AAA
696	4	6000.00	IDR	Admin Fee	AAA
380	5	1.25	AUD	Admin Fee	AAA
385	3	4000.00	IDR	Trans Fee	DOK
394	5	1.25	AUD	Admin Fee	AAA
411	2	7000.00	IDR	Admin Fee	AAA
419	5	1.25	AUD	Admin Fee	AAA
421	5	1.25	AUD	Admin Fee	AAA
430	4	6000.00	IDR	Admin Fee	AAA
433	5	1.25	AUD	Admin Fee	AAA
437	3	4000.00	IDR	Trans Fee	DOK
443	4	6000.00	IDR	Admin Fee	AAA
450	2	7000.00	IDR	Admin Fee	AAA
456	4	6000.00	IDR	Admin Fee	AAA
463	3	4000.00	IDR	Trans Fee	DOK
484	2	7000.00	IDR	Admin Fee	AAA
485	3	4000.00	IDR	Trans Fee	DOK
491	4	6000.00	IDR	Admin Fee	AAA
497	2	7000.00	IDR	Admin Fee	AAA
498	3	4000.00	IDR	Trans Fee	DOK
520	5	1.25	AUD	Admin Fee	AAA
536	2	7000.00	IDR	Admin Fee	AAA
537	3	4000.00	IDR	Trans Fee	DOK
545	5	1.25	AUD	Admin Fee	AAA
549	3	4000.00	IDR	Trans Fee	DOK
550	2	7000.00	IDR	Admin Fee	AAA
558	5	1.25	AUD	Admin Fee	AAA
574	3	4000.00	IDR	Trans Fee	DOK
584	5	1.25	AUD	Admin Fee	AAA
596	2	7000.00	IDR	Admin Fee	AAA
598	3	4000.00	IDR	Trans Fee	DOK
611	3	4000.00	IDR	Trans Fee	DOK
617	4	6000.00	IDR	Admin Fee	AAA
631	4	6000.00	IDR	Admin Fee	AAA
633	5	1.25	AUD	Admin Fee	AAA
650	2	7000.00	IDR	Admin Fee	AAA
669	4	6000.00	IDR	Admin Fee	AAA
672	5	1.25	AUD	Admin Fee	AAA
682	4	6000.00	IDR	Admin Fee	AAA
397	3	4000.00	IDR	Trans Fee	DOK
404	4	6000.00	IDR	Admin Fee	AAA
406	5	1.25	AUD	Admin Fee	AAA
410	2	7000.00	IDR	Admin Fee	AAA
411	3	4000.00	IDR	Trans Fee	DOK
432	5	1.25	AUD	Admin Fee	AAA
434	5	1.25	AUD	Admin Fee	AAA
448	2	7000.00	IDR	Admin Fee	AAA
506	5	1.25	AUD	Admin Fee	AAA
511	3	4000.00	IDR	Trans Fee	DOK
522	2	7000.00	IDR	Admin Fee	AAA
523	3	4000.00	IDR	Trans Fee	DOK
546	5	1.25	AUD	Admin Fee	AAA
549	2	7000.00	IDR	Admin Fee	AAA
561	3	4000.00	IDR	Trans Fee	DOK
569	4	6000.00	IDR	Admin Fee	AAA
575	3	4000.00	IDR	Trans Fee	DOK
583	4	6000.00	IDR	Admin Fee	AAA
605	4	6000.00	IDR	Admin Fee	AAA
635	2	7000.00	IDR	Admin Fee	AAA
636	3	4000.00	IDR	Trans Fee	DOK
649	2	7000.00	IDR	Admin Fee	AAA
661	2	7000.00	IDR	Admin Fee	AAA
663	3	4000.00	IDR	Trans Fee	DOK
671	5	1.25	AUD	Admin Fee	AAA
676	2	7000.00	IDR	Admin Fee	AAA
688	2	7000.00	IDR	Admin Fee	AAA
689	2	7000.00	IDR	Admin Fee	AAA
\.


--
-- Data for Name: transaction_inquiries; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY transaction_inquiries (id, type_id, user_id, request_time, update_time, transaction_id, validation_id, collect_id) FROM stdin;
1	11	1	2012-08-04 19:56:59.727	\N	\N	\N	\N
21	11	1	2012-08-04 22:58:33.212	\N	\N	\N	\N
22	11	1	2012-08-04 22:59:08.019	\N	\N	\N	\N
23	11	1	2012-08-04 23:04:45.506	\N	\N	\N	\N
24	11	1	2012-08-04 23:04:58.744	\N	\N	\N	\N
25	11	1	2012-08-04 23:06:44.683	\N	\N	\N	\N
26	11	1	2012-08-04 23:08:57.476	\N	\N	\N	\N
27	11	1	2012-08-04 23:09:06.041	\N	\N	\N	\N
28	11	1	2012-08-04 23:09:11.466	\N	\N	\N	\N
29	11	1	2012-08-04 23:11:38.556	\N	\N	\N	\N
30	11	1	2012-08-04 23:12:00.641	\N	\N	\N	\N
31	11	1	2012-08-04 23:19:30.374	\N	\N	\N	\N
32	11	1	2012-08-04 23:19:47.49	\N	\N	\N	\N
61	11	1	2012-08-05 17:41:27.428	\N	\N	\N	\N
62	11	1	2012-08-05 17:41:28.701	\N	\N	\N	\N
63	11	1	2012-08-05 17:41:28.953	\N	\N	\N	\N
64	11	1	2012-08-05 17:41:29.136	\N	\N	\N	\N
65	11	1	2012-08-05 17:41:29.319	\N	\N	\N	\N
66	11	1	2012-08-05 17:41:29.499	\N	\N	\N	\N
67	11	1	2012-08-05 17:41:29.73	\N	\N	\N	\N
68	11	1	2012-08-05 17:41:29.884	\N	\N	\N	\N
69	11	1	2012-08-05 17:41:30.032	\N	\N	\N	\N
70	11	1	2012-08-05 17:41:30.185	\N	\N	\N	\N
71	11	1	2012-08-05 17:41:30.353	\N	\N	\N	\N
72	11	1	2012-08-05 17:41:30.535	\N	\N	\N	\N
73	11	1	2012-08-05 17:41:30.711	\N	\N	\N	\N
74	11	1	2012-08-05 17:41:31.69	\N	\N	\N	\N
75	11	1	2012-08-05 17:41:31.871	\N	\N	\N	\N
76	11	1	2012-08-05 17:41:32.014	\N	\N	\N	\N
77	11	1	2012-08-05 17:41:32.158	\N	\N	\N	\N
78	11	1	2012-08-05 17:41:32.297	\N	\N	\N	\N
79	11	1	2012-08-05 17:41:32.44	\N	\N	\N	\N
80	11	1	2012-08-05 17:41:32.599	\N	\N	\N	\N
81	11	1	2012-08-05 17:41:32.797	\N	\N	\N	\N
82	11	1	2012-08-05 17:41:33.023	\N	\N	\N	\N
83	11	1	2012-08-05 17:41:33.167	\N	\N	\N	\N
84	11	1	2012-08-05 17:41:33.31	\N	\N	\N	\N
85	11	1	2012-08-05 17:41:33.454	\N	\N	\N	\N
86	11	1	2012-08-05 17:41:33.601	\N	\N	\N	\N
87	11	1	2012-08-05 17:41:33.746	\N	\N	\N	\N
88	11	1	2012-08-05 17:41:33.883	\N	\N	\N	\N
89	11	1	2012-08-05 17:41:34.02	\N	\N	\N	\N
90	11	1	2012-08-05 17:41:34.158	\N	\N	\N	\N
91	11	1	2012-08-05 17:41:34.301	\N	\N	\N	\N
92	11	1	2012-08-05 17:41:34.441	\N	\N	\N	\N
93	11	1	2012-08-05 17:41:34.58	\N	\N	\N	\N
94	11	1	2012-08-05 17:41:34.723	\N	\N	\N	\N
95	11	1	2012-08-05 17:41:34.858	\N	\N	\N	\N
96	11	1	2012-08-05 17:41:35.002	\N	\N	\N	\N
97	11	1	2012-08-05 17:41:35.153	\N	\N	\N	\N
98	11	1	2012-08-05 17:41:35.311	\N	\N	\N	\N
99	11	1	2012-08-05 17:41:35.462	\N	\N	\N	\N
100	11	1	2012-08-05 17:41:35.626	\N	\N	\N	\N
101	11	1	2012-08-05 17:41:35.764	\N	\N	\N	\N
102	11	1	2012-08-05 17:41:35.892	\N	\N	\N	\N
103	11	1	2012-08-05 17:41:36.025	\N	\N	\N	\N
104	11	1	2012-08-05 17:41:36.154	\N	\N	\N	\N
105	11	1	2012-08-05 17:41:36.284	\N	\N	\N	\N
106	11	1	2012-08-05 17:41:36.421	\N	\N	\N	\N
107	11	1	2012-08-05 17:41:36.553	\N	\N	\N	\N
108	11	1	2012-08-05 17:41:36.697	\N	\N	\N	\N
109	11	1	2012-08-05 17:41:36.836	\N	\N	\N	\N
110	11	1	2012-08-05 17:41:36.973	\N	\N	\N	\N
111	11	1	2012-08-05 17:41:37.119	\N	\N	\N	\N
112	11	1	2012-08-05 17:41:37.279	\N	\N	\N	\N
113	11	1	2012-08-05 17:41:37.423	\N	\N	\N	\N
114	11	1	2012-08-05 17:41:37.554	\N	\N	\N	\N
115	11	1	2012-08-05 17:41:37.682	\N	\N	\N	\N
116	11	1	2012-08-05 17:41:37.807	\N	\N	\N	\N
117	11	1	2012-08-05 17:41:37.935	\N	\N	\N	\N
118	11	1	2012-08-05 17:41:38.075	\N	\N	\N	\N
119	11	1	2012-08-05 17:41:38.206	\N	\N	\N	\N
120	11	1	2012-08-05 17:41:38.342	\N	\N	\N	\N
121	11	1	2012-08-05 17:41:38.487	\N	\N	\N	\N
122	11	1	2012-08-05 17:41:38.619	\N	\N	\N	\N
123	11	1	2012-08-05 17:41:38.761	\N	\N	\N	\N
124	11	1	2012-08-05 17:41:38.924	\N	\N	\N	\N
125	11	1	2012-08-05 17:41:39.073	\N	\N	\N	\N
126	11	1	2012-08-05 17:41:39.226	\N	\N	\N	\N
127	11	1	2012-08-05 17:41:39.351	\N	\N	\N	\N
128	11	1	2012-08-05 17:41:39.475	\N	\N	\N	\N
129	11	1	2012-08-05 17:41:39.608	\N	\N	\N	\N
130	11	1	2012-08-05 17:41:39.731	\N	\N	\N	\N
131	11	1	2012-08-05 17:41:39.861	\N	\N	\N	\N
132	11	1	2012-08-05 17:41:39.992	\N	\N	\N	\N
133	11	1	2012-08-05 17:41:40.13	\N	\N	\N	\N
134	11	1	2012-08-05 17:41:40.259	\N	\N	\N	\N
135	11	1	2012-08-05 17:41:40.395	\N	\N	\N	\N
136	11	1	2012-08-05 17:41:40.534	\N	\N	\N	\N
137	11	1	2012-08-05 17:41:40.669	\N	\N	\N	\N
138	11	1	2012-08-05 17:41:40.804	\N	\N	\N	\N
139	11	1	2012-08-05 17:41:40.945	\N	\N	\N	\N
140	11	1	2012-08-05 17:41:41.07	\N	\N	\N	\N
141	11	1	2012-08-05 17:41:41.191	\N	\N	\N	\N
142	11	1	2012-08-05 17:41:41.316	\N	\N	\N	\N
143	11	1	2012-08-05 17:41:41.442	\N	\N	\N	\N
144	11	1	2012-08-05 17:41:41.571	\N	\N	\N	\N
145	11	1	2012-08-05 17:41:41.703	\N	\N	\N	\N
146	11	1	2012-08-05 17:41:41.839	\N	\N	\N	\N
147	11	1	2012-08-05 17:41:41.969	\N	\N	\N	\N
148	11	1	2012-08-05 17:41:42.101	\N	\N	\N	\N
149	11	1	2012-08-05 17:41:42.232	\N	\N	\N	\N
150	11	1	2012-08-05 17:41:42.371	\N	\N	\N	\N
151	11	1	2012-08-05 17:41:42.513	\N	\N	\N	\N
152	11	1	2012-08-05 17:41:42.663	\N	\N	\N	\N
153	11	1	2012-08-05 17:41:42.795	\N	\N	\N	\N
154	11	1	2012-08-05 17:41:42.909	\N	\N	\N	\N
155	11	1	2012-08-05 17:41:43.056	\N	\N	\N	\N
156	11	1	2012-08-05 17:41:43.181	\N	\N	\N	\N
157	11	1	2012-08-05 17:41:43.302	\N	\N	\N	\N
158	11	1	2012-08-05 17:41:43.433	\N	\N	\N	\N
159	11	1	2012-08-05 17:41:43.563	\N	\N	\N	\N
160	11	1	2012-08-05 17:41:43.711	\N	\N	\N	\N
161	11	1	2012-08-05 17:41:43.84	\N	\N	\N	\N
162	11	1	2012-08-05 17:41:43.972	\N	\N	\N	\N
163	11	1	2012-08-05 17:41:44.129	\N	\N	\N	\N
164	11	1	2012-08-05 17:41:44.278	\N	\N	\N	\N
165	11	1	2012-08-05 17:41:44.426	\N	\N	\N	\N
166	11	1	2012-08-05 17:41:44.548	\N	\N	\N	\N
167	11	1	2012-08-05 17:41:44.675	\N	\N	\N	\N
168	11	1	2012-08-05 17:41:44.804	\N	\N	\N	\N
169	11	1	2012-08-05 17:41:44.93	\N	\N	\N	\N
170	11	1	2012-08-05 17:41:45.053	\N	\N	\N	\N
171	11	1	2012-08-05 17:41:45.196	\N	\N	\N	\N
172	11	1	2012-08-05 17:41:45.324	\N	\N	\N	\N
173	11	1	2012-08-05 17:41:45.454	\N	\N	\N	\N
174	11	1	2012-08-05 18:56:44.939	\N	\N	\N	\N
175	11	1	2012-08-05 18:56:46.377	\N	\N	\N	\N
176	11	1	2012-08-05 18:56:46.609	\N	\N	\N	\N
177	11	1	2012-08-05 18:56:46.783	\N	\N	\N	\N
178	11	1	2012-08-05 18:56:46.949	\N	\N	\N	\N
179	11	1	2012-08-05 18:56:47.102	\N	\N	\N	\N
180	11	1	2012-08-05 18:56:47.271	\N	\N	\N	\N
181	11	1	2012-08-05 18:56:47.395	\N	\N	\N	\N
182	11	1	2012-08-05 18:56:47.524	\N	\N	\N	\N
183	11	1	2012-08-05 18:56:47.646	\N	\N	\N	\N
184	11	1	2012-08-05 18:56:47.769	\N	\N	\N	\N
203	11	1	2012-08-05 18:56:51.062	\N	\N	\N	\N
216	11	1	2012-08-05 18:56:52.724	\N	\N	\N	\N
225	11	1	2012-08-05 18:56:53.88	\N	\N	\N	\N
228	11	1	2012-08-05 18:56:54.539	\N	\N	\N	\N
237	11	1	2012-08-05 18:56:55.679	\N	\N	\N	\N
241	11	1	2012-08-05 18:56:56.166	\N	\N	\N	\N
256	11	1	2012-08-05 18:56:58.037	\N	\N	\N	\N
263	11	1	2012-08-05 18:56:58.899	\N	\N	\N	\N
267	11	1	2012-08-05 18:56:59.385	\N	\N	\N	\N
272	11	1	2012-08-05 18:57:00.001	\N	\N	\N	\N
273	11	1	2012-08-05 18:57:00.158	\N	\N	\N	\N
274	11	1	2012-08-05 18:57:00.277	\N	\N	\N	\N
275	11	1	2012-08-05 18:57:00.397	\N	\N	\N	\N
281	11	1	2012-08-05 18:57:01.143	\N	\N	\N	\N
185	11	1	2012-08-05 18:56:47.912	\N	\N	\N	\N
189	11	1	2012-08-05 18:56:49.287	\N	\N	\N	\N
192	11	1	2012-08-05 18:56:49.653	\N	\N	\N	\N
193	11	1	2012-08-05 18:56:49.782	\N	\N	\N	\N
194	11	1	2012-08-05 18:56:49.904	\N	\N	\N	\N
195	11	1	2012-08-05 18:56:50.027	\N	\N	\N	\N
196	11	1	2012-08-05 18:56:50.149	\N	\N	\N	\N
197	11	1	2012-08-05 18:56:50.273	\N	\N	\N	\N
205	11	1	2012-08-05 18:56:51.299	\N	\N	\N	\N
206	11	1	2012-08-05 18:56:51.426	\N	\N	\N	\N
207	11	1	2012-08-05 18:56:51.547	\N	\N	\N	\N
208	11	1	2012-08-05 18:56:51.674	\N	\N	\N	\N
209	11	1	2012-08-05 18:56:51.796	\N	\N	\N	\N
210	11	1	2012-08-05 18:56:51.925	\N	\N	\N	\N
217	11	1	2012-08-05 18:56:52.848	\N	\N	\N	\N
224	11	1	2012-08-05 18:56:53.737	\N	\N	\N	\N
229	11	1	2012-08-05 18:56:54.657	\N	\N	\N	\N
243	11	1	2012-08-05 18:56:56.406	\N	\N	\N	\N
247	11	1	2012-08-05 18:56:56.936	\N	\N	\N	\N
248	11	1	2012-08-05 18:56:57.061	\N	\N	\N	\N
249	11	1	2012-08-05 18:56:57.186	\N	\N	\N	\N
257	11	1	2012-08-05 18:56:58.154	\N	\N	\N	\N
258	11	1	2012-08-05 18:56:58.276	\N	\N	\N	\N
259	11	1	2012-08-05 18:56:58.396	\N	\N	\N	\N
260	11	1	2012-08-05 18:56:58.53	\N	\N	\N	\N
261	11	1	2012-08-05 18:56:58.653	\N	\N	\N	\N
262	11	1	2012-08-05 18:56:58.772	\N	\N	\N	\N
268	11	1	2012-08-05 18:56:59.50	\N	\N	\N	\N
271	11	1	2012-08-05 18:56:59.877	\N	\N	\N	\N
282	11	1	2012-08-05 18:57:01.261	\N	\N	\N	\N
186	11	1	2012-08-05 18:56:48.061	\N	\N	\N	\N
188	11	1	2012-08-05 18:56:49.167	\N	\N	\N	\N
199	11	1	2012-08-05 18:56:50.557	\N	\N	\N	\N
201	11	1	2012-08-05 18:56:50.825	\N	\N	\N	\N
202	11	1	2012-08-05 18:56:50.942	\N	\N	\N	\N
211	11	1	2012-08-05 18:56:52.084	\N	\N	\N	\N
215	11	1	2012-08-05 18:56:52.606	\N	\N	\N	\N
227	11	1	2012-08-05 18:56:54.423	\N	\N	\N	\N
238	11	1	2012-08-05 18:56:55.804	\N	\N	\N	\N
240	11	1	2012-08-05 18:56:56.049	\N	\N	\N	\N
254	11	1	2012-08-05 18:56:57.808	\N	\N	\N	\N
265	11	1	2012-08-05 18:56:59.159	\N	\N	\N	\N
277	11	1	2012-08-05 18:57:00.65	\N	\N	\N	\N
279	11	1	2012-08-05 18:57:00.911	\N	\N	\N	\N
187	11	1	2012-08-05 18:56:49.031	\N	\N	\N	\N
200	11	1	2012-08-05 18:56:50.707	\N	\N	\N	\N
212	11	1	2012-08-05 18:56:52.214	\N	\N	\N	\N
214	11	1	2012-08-05 18:56:52.488	\N	\N	\N	\N
226	11	1	2012-08-05 18:56:54.303	\N	\N	\N	\N
239	11	1	2012-08-05 18:56:55.931	\N	\N	\N	\N
251	11	1	2012-08-05 18:56:57.452	\N	\N	\N	\N
253	11	1	2012-08-05 18:56:57.691	\N	\N	\N	\N
278	11	1	2012-08-05 18:57:00.788	\N	\N	\N	\N
190	11	1	2012-08-05 18:56:49.407	\N	\N	\N	\N
191	11	1	2012-08-05 18:56:49.538	\N	\N	\N	\N
198	11	1	2012-08-05 18:56:50.414	\N	\N	\N	\N
204	11	1	2012-08-05 18:56:51.178	\N	\N	\N	\N
218	11	1	2012-08-05 18:56:52.977	\N	\N	\N	\N
219	11	1	2012-08-05 18:56:53.10	\N	\N	\N	\N
220	11	1	2012-08-05 18:56:53.233	\N	\N	\N	\N
221	11	1	2012-08-05 18:56:53.355	\N	\N	\N	\N
222	11	1	2012-08-05 18:56:53.475	\N	\N	\N	\N
223	11	1	2012-08-05 18:56:53.594	\N	\N	\N	\N
230	11	1	2012-08-05 18:56:54.771	\N	\N	\N	\N
231	11	1	2012-08-05 18:56:54.914	\N	\N	\N	\N
232	11	1	2012-08-05 18:56:55.037	\N	\N	\N	\N
233	11	1	2012-08-05 18:56:55.161	\N	\N	\N	\N
234	11	1	2012-08-05 18:56:55.289	\N	\N	\N	\N
235	11	1	2012-08-05 18:56:55.417	\N	\N	\N	\N
236	11	1	2012-08-05 18:56:55.538	\N	\N	\N	\N
242	11	1	2012-08-05 18:56:56.291	\N	\N	\N	\N
250	11	1	2012-08-05 18:56:57.314	\N	\N	\N	\N
255	11	1	2012-08-05 18:56:57.923	\N	\N	\N	\N
264	11	1	2012-08-05 18:56:59.033	\N	\N	\N	\N
266	11	1	2012-08-05 18:56:59.273	\N	\N	\N	\N
276	11	1	2012-08-05 18:57:00.523	\N	\N	\N	\N
280	11	1	2012-08-05 18:57:01.025	\N	\N	\N	\N
213	11	1	2012-08-05 18:56:52.352	\N	\N	\N	\N
244	11	1	2012-08-05 18:56:56.522	\N	\N	\N	\N
245	11	1	2012-08-05 18:56:56.681	\N	\N	\N	\N
246	11	1	2012-08-05 18:56:56.801	\N	\N	\N	\N
252	11	1	2012-08-05 18:56:57.576	\N	\N	\N	\N
269	11	1	2012-08-05 18:56:59.616	\N	\N	\N	\N
270	11	1	2012-08-05 18:56:59.736	\N	\N	\N	\N
283	11	1	2012-08-05 18:57:01.377	\N	\N	\N	\N
284	11	1	2012-08-05 18:57:01.499	\N	\N	\N	\N
285	11	1	2012-08-05 18:57:01.618	\N	\N	\N	\N
286	11	1	2012-08-05 18:57:01.74	\N	\N	\N	\N
287	11	1	2012-08-05 22:40:50.106	\N	\N	\N	\N
288	11	1	2012-08-05 22:59:40.633	\N	\N	\N	\N
289	11	1	2012-08-05 22:59:42.015	\N	\N	\N	\N
290	11	1	2012-08-05 22:59:42.232	\N	\N	\N	\N
291	11	1	2012-08-05 22:59:42.431	\N	\N	\N	\N
292	11	1	2012-08-05 22:59:42.586	\N	\N	\N	\N
293	11	1	2012-08-05 22:59:42.712	\N	\N	\N	\N
294	11	1	2012-08-05 22:59:42.855	\N	\N	\N	\N
295	11	1	2012-08-05 22:59:42.98	\N	\N	\N	\N
296	11	1	2012-08-05 22:59:43.104	\N	\N	\N	\N
297	11	1	2012-08-05 22:59:43.242	\N	\N	\N	\N
298	11	1	2012-08-05 22:59:43.367	\N	\N	\N	\N
299	11	1	2012-08-05 22:59:43.514	\N	\N	\N	\N
300	11	1	2012-08-05 22:59:43.684	\N	\N	\N	\N
301	11	1	2012-08-05 22:59:44.692	\N	\N	\N	\N
302	11	1	2012-08-05 22:59:44.894	\N	\N	\N	\N
303	11	1	2012-08-05 22:59:45.013	\N	\N	\N	\N
304	11	1	2012-08-05 22:59:45.135	\N	\N	\N	\N
305	11	1	2012-08-05 22:59:45.254	\N	\N	\N	\N
306	11	1	2012-08-05 22:59:45.374	\N	\N	\N	\N
307	11	1	2012-08-05 22:59:45.545	\N	\N	\N	\N
308	11	1	2012-08-05 22:59:45.665	\N	\N	\N	\N
309	11	1	2012-08-05 22:59:45.797	\N	\N	\N	\N
310	11	1	2012-08-05 22:59:45.93	\N	\N	\N	\N
311	11	1	2012-08-05 22:59:46.057	\N	\N	\N	\N
312	11	1	2012-08-05 22:59:46.199	\N	\N	\N	\N
313	11	1	2012-08-05 22:59:46.337	\N	\N	\N	\N
314	11	1	2012-08-05 22:59:46.474	\N	\N	\N	\N
315	11	1	2012-08-05 22:59:46.589	\N	\N	\N	\N
316	11	1	2012-08-05 22:59:46.71	\N	\N	\N	\N
317	11	1	2012-08-05 22:59:46.826	\N	\N	\N	\N
318	11	1	2012-08-05 22:59:46.941	\N	\N	\N	\N
319	11	1	2012-08-05 22:59:47.056	\N	\N	\N	\N
320	11	1	2012-08-05 22:59:47.179	\N	\N	\N	\N
321	11	1	2012-08-05 22:59:47.307	\N	\N	\N	\N
322	11	1	2012-08-05 22:59:47.43	\N	\N	\N	\N
323	11	1	2012-08-05 22:59:47.554	\N	\N	\N	\N
324	11	1	2012-08-05 22:59:47.675	\N	\N	\N	\N
325	11	1	2012-08-05 22:59:47.812	\N	\N	\N	\N
326	11	1	2012-08-05 22:59:47.992	\N	\N	\N	\N
327	11	1	2012-08-05 22:59:48.162	\N	\N	\N	\N
328	11	1	2012-08-05 22:59:48.302	\N	\N	\N	\N
329	11	1	2012-08-05 22:59:48.434	\N	\N	\N	\N
330	11	1	2012-08-05 22:59:48.586	\N	\N	\N	\N
331	11	1	2012-08-05 22:59:48.729	\N	\N	\N	\N
332	11	1	2012-08-05 22:59:48.85	\N	\N	\N	\N
333	11	1	2012-08-05 22:59:48.976	\N	\N	\N	\N
334	11	1	2012-08-05 22:59:49.12	\N	\N	\N	\N
335	11	1	2012-08-05 22:59:49.242	\N	\N	\N	\N
336	11	1	2012-08-05 22:59:49.362	\N	\N	\N	\N
337	11	1	2012-08-05 22:59:49.487	\N	\N	\N	\N
338	11	1	2012-08-05 22:59:49.617	\N	\N	\N	\N
339	11	1	2012-08-05 22:59:49.766	\N	\N	\N	\N
340	11	1	2012-08-05 22:59:49.896	\N	\N	\N	\N
341	11	1	2012-08-05 22:59:50.012	\N	\N	\N	\N
342	11	1	2012-08-05 22:59:50.129	\N	\N	\N	\N
343	11	1	2012-08-05 22:59:50.251	\N	\N	\N	\N
344	11	1	2012-08-05 22:59:50.367	\N	\N	\N	\N
345	11	1	2012-08-05 22:59:50.485	\N	\N	\N	\N
346	11	1	2012-08-05 22:59:50.608	\N	\N	\N	\N
347	11	1	2012-08-05 22:59:50.73	\N	\N	\N	\N
348	11	1	2012-08-05 22:59:50.852	\N	\N	\N	\N
349	11	1	2012-08-05 22:59:50.971	\N	\N	\N	\N
350	11	1	2012-08-05 22:59:51.087	\N	\N	\N	\N
351	11	1	2012-08-05 22:59:51.216	\N	\N	\N	\N
352	11	1	2012-08-05 22:59:51.349	\N	\N	\N	\N
353	11	1	2012-08-05 22:59:51.478	\N	\N	\N	\N
354	11	1	2012-08-05 22:59:51.592	\N	\N	\N	\N
355	11	1	2012-08-05 22:59:51.705	\N	\N	\N	\N
356	11	1	2012-08-05 22:59:51.816	\N	\N	\N	\N
357	11	1	2012-08-05 22:59:51.933	\N	\N	\N	\N
358	11	1	2012-08-05 22:59:52.048	\N	\N	\N	\N
359	11	1	2012-08-05 22:59:52.169	\N	\N	\N	\N
360	11	1	2012-08-05 22:59:52.29	\N	\N	\N	\N
361	11	1	2012-08-05 22:59:52.421	\N	\N	\N	\N
362	11	1	2012-08-05 22:59:52.544	\N	\N	\N	\N
363	11	1	2012-08-05 22:59:52.677	\N	\N	\N	\N
364	11	1	2012-08-05 22:59:52.824	\N	\N	\N	\N
365	11	1	2012-08-05 22:59:52.953	\N	\N	\N	\N
366	11	1	2012-08-05 22:59:53.079	\N	\N	\N	\N
367	11	1	2012-08-05 22:59:53.19	\N	\N	\N	\N
368	11	1	2012-08-05 22:59:53.307	\N	\N	\N	\N
369	11	1	2012-08-05 22:59:53.42	\N	\N	\N	\N
370	11	1	2012-08-05 22:59:53.535	\N	\N	\N	\N
371	11	1	2012-08-05 22:59:53.652	\N	\N	\N	\N
372	11	1	2012-08-05 22:59:53.782	\N	\N	\N	\N
373	11	1	2012-08-05 22:59:53.921	\N	\N	\N	\N
374	11	1	2012-08-05 22:59:54.051	\N	\N	\N	\N
375	11	1	2012-08-05 22:59:54.181	\N	\N	\N	\N
376	11	1	2012-08-05 22:59:54.321	\N	\N	\N	\N
377	11	1	2012-08-05 22:59:54.486	\N	\N	\N	\N
378	11	1	2012-08-05 22:59:54.649	\N	\N	\N	\N
379	11	1	2012-08-05 22:59:54.797	\N	\N	\N	\N
380	11	1	2012-08-05 22:59:54.921	\N	\N	\N	\N
381	11	1	2012-08-05 22:59:55.056	\N	\N	\N	\N
382	11	1	2012-08-05 22:59:55.193	\N	\N	\N	\N
383	11	1	2012-08-05 22:59:55.306	\N	\N	\N	\N
384	11	1	2012-08-05 22:59:55.426	\N	\N	\N	\N
385	11	1	2012-08-05 22:59:55.545	\N	\N	\N	\N
386	11	1	2012-08-05 22:59:55.674	\N	\N	\N	\N
387	11	1	2012-08-05 22:59:55.796	\N	\N	\N	\N
388	11	1	2012-08-05 22:59:55.922	\N	\N	\N	\N
389	11	1	2012-08-05 22:59:56.044	\N	\N	\N	\N
390	11	1	2012-08-05 22:59:56.178	\N	\N	\N	\N
391	11	1	2012-08-05 22:59:56.32	\N	\N	\N	\N
392	11	1	2012-08-05 22:59:56.448	\N	\N	\N	\N
393	11	1	2012-08-05 22:59:56.579	\N	\N	\N	\N
394	11	1	2012-08-05 22:59:56.698	\N	\N	\N	\N
395	11	1	2012-08-05 22:59:56.813	\N	\N	\N	\N
396	11	1	2012-08-05 22:59:56.939	\N	\N	\N	\N
397	11	1	2012-08-05 22:59:57.055	\N	\N	\N	\N
398	11	1	2012-08-05 22:59:57.174	\N	\N	\N	\N
399	11	1	2012-08-05 22:59:57.296	\N	\N	\N	\N
400	11	1	2012-08-05 22:59:57.416	\N	\N	\N	\N
401	11	1	2012-08-05 23:01:24.305	\N	\N	\N	\N
402	11	1	2012-08-05 23:01:25.596	\N	\N	\N	\N
403	11	1	2012-08-05 23:01:25.755	\N	\N	\N	\N
404	11	1	2012-08-05 23:01:25.877	\N	\N	\N	\N
405	11	1	2012-08-05 23:01:25.998	\N	\N	\N	\N
406	11	1	2012-08-05 23:01:26.121	\N	\N	\N	\N
407	11	1	2012-08-05 23:01:26.253	\N	\N	\N	\N
408	11	1	2012-08-05 23:01:26.376	\N	\N	\N	\N
409	11	1	2012-08-05 23:01:26.51	\N	\N	\N	\N
410	11	1	2012-08-05 23:01:26.646	\N	\N	\N	\N
411	11	1	2012-08-05 23:01:26.779	\N	\N	\N	\N
412	11	1	2012-08-05 23:01:26.915	\N	\N	\N	\N
413	11	1	2012-08-05 23:01:27.055	\N	\N	\N	\N
447	11	1	2012-08-05 23:01:32.118	\N	\N	\N	\N
450	11	1	2012-08-05 23:01:32.486	\N	\N	\N	\N
451	11	1	2012-08-05 23:01:32.615	\N	\N	\N	\N
472	11	1	2012-08-05 23:01:35.224	\N	\N	\N	\N
500	11	1	2012-08-05 23:01:38.639	\N	\N	\N	\N
510	11	1	2012-08-05 23:01:39.921	\N	\N	\N	\N
513	11	1	2012-08-05 23:01:40.291	\N	\N	\N	\N
516	11	1	2012-08-05 23:02:15.274	\N	\N	\N	\N
535	11	1	2012-08-05 23:02:18.60	\N	\N	\N	\N
551	11	1	2012-08-05 23:02:20.618	\N	\N	\N	\N
554	11	1	2012-08-05 23:02:21.009	\N	\N	\N	\N
556	11	1	2012-08-05 23:02:21.245	\N	\N	\N	\N
558	11	1	2012-08-05 23:02:21.476	\N	\N	\N	\N
582	11	1	2012-08-05 23:02:24.534	\N	\N	\N	\N
587	11	1	2012-08-05 23:02:25.149	\N	\N	\N	\N
597	11	1	2012-08-05 23:02:26.391	\N	\N	\N	\N
600	11	1	2012-08-05 23:02:26.774	\N	\N	\N	\N
609	11	1	2012-08-05 23:02:28.149	\N	\N	\N	\N
621	11	1	2012-08-05 23:02:29.632	\N	\N	\N	\N
642	11	1	2012-08-05 23:05:07.518	\N	\N	\N	\N
646	11	1	2012-08-05 23:05:08.002	\N	\N	\N	\N
648	11	1	2012-08-05 23:05:08.252	\N	\N	\N	\N
683	11	1	2012-08-05 23:05:12.614	\N	\N	\N	\N
686	11	1	2012-08-05 23:05:12.977	\N	\N	\N	\N
697	11	1	2012-08-05 23:05:14.298	\N	\N	\N	\N
701	11	1	2012-08-05 23:05:14.801	\N	\N	\N	\N
708	11	1	2012-08-05 23:05:15.713	\N	\N	\N	\N
710	11	1	2012-08-05 23:05:15.946	\N	\N	\N	\N
729	11	1	2012-08-05 23:05:18.381	\N	\N	\N	\N
742	11	1	2012-08-05 23:07:08.242	\N	\N	\N	\N
746	11	1	2012-08-05 23:08:45.491	\N	\N	\N	\N
748	11	1	2012-08-05 23:08:47.641	\N	\N	\N	\N
414	11	1	2012-08-05 23:01:28.024	\N	\N	\N	\N
416	11	1	2012-08-05 23:01:28.311	\N	\N	\N	\N
422	11	1	2012-08-05 23:01:29.048	\N	\N	\N	\N
426	11	1	2012-08-05 23:01:29.565	\N	\N	\N	\N
430	11	1	2012-08-05 23:01:30.048	\N	\N	\N	\N
433	11	1	2012-08-05 23:01:30.418	\N	\N	\N	\N
465	11	1	2012-08-05 23:01:34.354	\N	\N	\N	\N
467	11	1	2012-08-05 23:01:34.599	\N	\N	\N	\N
470	11	1	2012-08-05 23:01:34.983	\N	\N	\N	\N
487	11	1	2012-08-05 23:01:37.049	\N	\N	\N	\N
503	11	1	2012-08-05 23:01:39.066	\N	\N	\N	\N
508	11	1	2012-08-05 23:01:39.68	\N	\N	\N	\N
511	11	1	2012-08-05 23:01:40.046	\N	\N	\N	\N
518	11	1	2012-08-05 23:02:15.519	\N	\N	\N	\N
522	11	1	2012-08-05 23:02:16.035	\N	\N	\N	\N
524	11	1	2012-08-05 23:02:16.294	\N	\N	\N	\N
531	11	1	2012-08-05 23:02:18.069	\N	\N	\N	\N
534	11	1	2012-08-05 23:02:18.462	\N	\N	\N	\N
538	11	1	2012-08-05 23:02:18.988	\N	\N	\N	\N
542	11	1	2012-08-05 23:02:19.493	\N	\N	\N	\N
547	11	1	2012-08-05 23:02:20.10	\N	\N	\N	\N
550	11	1	2012-08-05 23:02:20.486	\N	\N	\N	\N
561	11	1	2012-08-05 23:02:21.857	\N	\N	\N	\N
565	11	1	2012-08-05 23:02:22.396	\N	\N	\N	\N
578	11	1	2012-08-05 23:02:24.043	\N	\N	\N	\N
584	11	1	2012-08-05 23:02:24.772	\N	\N	\N	\N
593	11	1	2012-08-05 23:02:25.91	\N	\N	\N	\N
602	11	1	2012-08-05 23:02:27.064	\N	\N	\N	\N
633	11	1	2012-08-05 23:05:05.178	\N	\N	\N	\N
636	11	1	2012-08-05 23:05:05.567	\N	\N	\N	\N
662	11	1	2012-08-05 23:05:10.027	\N	\N	\N	\N
678	11	1	2012-08-05 23:05:12.034	\N	\N	\N	\N
689	11	1	2012-08-05 23:05:13.336	\N	\N	\N	\N
694	11	1	2012-08-05 23:05:13.951	\N	\N	\N	\N
711	11	1	2012-08-05 23:05:16.072	\N	\N	\N	\N
716	11	1	2012-08-05 23:05:16.713	\N	\N	\N	\N
717	11	1	2012-08-05 23:05:16.86	\N	\N	\N	\N
722	11	1	2012-08-05 23:05:17.484	\N	\N	\N	\N
415	11	1	2012-08-05 23:01:28.197	\N	\N	\N	\N
425	11	1	2012-08-05 23:01:29.439	\N	\N	\N	\N
429	11	1	2012-08-05 23:01:29.931	\N	\N	\N	\N
432	11	1	2012-08-05 23:01:30.291	\N	\N	\N	\N
434	11	1	2012-08-05 23:01:30.543	\N	\N	\N	\N
436	11	1	2012-08-05 23:01:30.786	\N	\N	\N	\N
463	11	1	2012-08-05 23:01:34.093	\N	\N	\N	\N
473	11	1	2012-08-05 23:01:35.346	\N	\N	\N	\N
477	11	1	2012-08-05 23:01:35.849	\N	\N	\N	\N
482	11	1	2012-08-05 23:01:36.462	\N	\N	\N	\N
526	11	1	2012-08-05 23:02:16.563	\N	\N	\N	\N
537	11	1	2012-08-05 23:02:18.852	\N	\N	\N	\N
569	11	1	2012-08-05 23:02:22.878	\N	\N	\N	\N
589	11	1	2012-08-05 23:02:25.398	\N	\N	\N	\N
595	11	1	2012-08-05 23:02:26.154	\N	\N	\N	\N
607	11	1	2012-08-05 23:02:27.831	\N	\N	\N	\N
620	11	1	2012-08-05 23:02:29.517	\N	\N	\N	\N
622	11	1	2012-08-05 23:02:29.746	\N	\N	\N	\N
634	11	1	2012-08-05 23:05:05.305	\N	\N	\N	\N
641	11	1	2012-08-05 23:05:07.399	\N	\N	\N	\N
647	11	1	2012-08-05 23:05:08.125	\N	\N	\N	\N
654	11	1	2012-08-05 23:05:09.033	\N	\N	\N	\N
665	11	1	2012-08-05 23:05:10.422	\N	\N	\N	\N
669	11	1	2012-08-05 23:05:10.909	\N	\N	\N	\N
680	11	1	2012-08-05 23:05:12.274	\N	\N	\N	\N
693	11	1	2012-08-05 23:05:13.841	\N	\N	\N	\N
704	11	1	2012-08-05 23:05:15.226	\N	\N	\N	\N
706	11	1	2012-08-05 23:05:15.473	\N	\N	\N	\N
715	11	1	2012-08-05 23:05:16.577	\N	\N	\N	\N
719	11	1	2012-08-05 23:05:17.124	\N	\N	\N	\N
737	11	1	2012-08-05 23:05:19.389	\N	\N	\N	\N
738	11	1	2012-08-05 23:05:19.526	\N	\N	\N	\N
417	11	1	2012-08-05 23:01:28.434	\N	\N	\N	\N
419	11	1	2012-08-05 23:01:28.672	\N	\N	\N	\N
453	11	1	2012-08-05 23:01:32.878	\N	\N	\N	\N
459	11	1	2012-08-05 23:01:33.613	\N	\N	\N	\N
475	11	1	2012-08-05 23:01:35.605	\N	\N	\N	\N
486	11	1	2012-08-05 23:01:36.932	\N	\N	\N	\N
489	11	1	2012-08-05 23:01:37.297	\N	\N	\N	\N
493	11	1	2012-08-05 23:01:37.802	\N	\N	\N	\N
501	11	1	2012-08-05 23:01:38.791	\N	\N	\N	\N
514	11	1	2012-08-05 23:02:13.851	\N	\N	\N	\N
529	11	1	2012-08-05 23:02:17.819	\N	\N	\N	\N
532	11	1	2012-08-05 23:02:18.20	\N	\N	\N	\N
544	11	1	2012-08-05 23:02:19.726	\N	\N	\N	\N
548	11	1	2012-08-05 23:02:20.226	\N	\N	\N	\N
562	11	1	2012-08-05 23:02:21.98	\N	\N	\N	\N
566	11	1	2012-08-05 23:02:22.531	\N	\N	\N	\N
573	11	1	2012-08-05 23:02:23.395	\N	\N	\N	\N
575	11	1	2012-08-05 23:02:23.647	\N	\N	\N	\N
588	11	1	2012-08-05 23:02:25.275	\N	\N	\N	\N
605	11	1	2012-08-05 23:02:27.51	\N	\N	\N	\N
632	11	1	2012-08-05 23:05:05.052	\N	\N	\N	\N
635	11	1	2012-08-05 23:05:05.439	\N	\N	\N	\N
638	11	1	2012-08-05 23:05:05.816	\N	\N	\N	\N
639	11	1	2012-08-05 23:05:05.981	\N	\N	\N	\N
640	11	1	2012-08-05 23:05:07.235	\N	\N	\N	\N
649	11	1	2012-08-05 23:05:08.385	\N	\N	\N	\N
660	11	1	2012-08-05 23:05:09.768	\N	\N	\N	\N
668	11	1	2012-08-05 23:05:10.79	\N	\N	\N	\N
671	11	1	2012-08-05 23:05:11.149	\N	\N	\N	\N
681	11	1	2012-08-05 23:05:12.388	\N	\N	\N	\N
688	11	1	2012-08-05 23:05:13.217	\N	\N	\N	\N
692	11	1	2012-08-05 23:05:13.724	\N	\N	\N	\N
709	11	1	2012-08-05 23:05:15.832	\N	\N	\N	\N
740	11	1	2012-08-05 23:07:06.12	\N	\N	\N	\N
743	11	1	2012-08-05 23:08:07.141	\N	\N	\N	\N
747	11	1	2012-08-05 23:08:46.713	\N	\N	\N	\N
418	11	1	2012-08-05 23:01:28.549	\N	\N	\N	\N
421	11	1	2012-08-05 23:01:28.922	\N	\N	\N	\N
427	11	1	2012-08-05 23:01:29.70	\N	\N	\N	\N
437	11	1	2012-08-05 23:01:30.908	\N	\N	\N	\N
441	11	1	2012-08-05 23:01:31.419	\N	\N	\N	\N
445	11	1	2012-08-05 23:01:31.876	\N	\N	\N	\N
449	11	1	2012-08-05 23:01:32.363	\N	\N	\N	\N
458	11	1	2012-08-05 23:01:33.491	\N	\N	\N	\N
461	11	1	2012-08-05 23:01:33.853	\N	\N	\N	\N
464	11	1	2012-08-05 23:01:34.225	\N	\N	\N	\N
478	11	1	2012-08-05 23:01:35.988	\N	\N	\N	\N
479	11	1	2012-08-05 23:01:36.118	\N	\N	\N	\N
496	11	1	2012-08-05 23:01:38.149	\N	\N	\N	\N
507	11	1	2012-08-05 23:01:39.566	\N	\N	\N	\N
509	11	1	2012-08-05 23:01:39.804	\N	\N	\N	\N
521	11	1	2012-08-05 23:02:15.912	\N	\N	\N	\N
530	11	1	2012-08-05 23:02:17.945	\N	\N	\N	\N
536	11	1	2012-08-05 23:02:18.725	\N	\N	\N	\N
540	11	1	2012-08-05 23:02:19.253	\N	\N	\N	\N
549	11	1	2012-08-05 23:02:20.356	\N	\N	\N	\N
564	11	1	2012-08-05 23:02:22.266	\N	\N	\N	\N
571	11	1	2012-08-05 23:02:23.122	\N	\N	\N	\N
574	11	1	2012-08-05 23:02:23.522	\N	\N	\N	\N
576	11	1	2012-08-05 23:02:23.767	\N	\N	\N	\N
590	11	1	2012-08-05 23:02:25.522	\N	\N	\N	\N
591	11	1	2012-08-05 23:02:25.654	\N	\N	\N	\N
612	11	1	2012-08-05 23:02:28.532	\N	\N	\N	\N
618	11	1	2012-08-05 23:02:29.29	\N	\N	\N	\N
643	11	1	2012-08-05 23:05:07.638	\N	\N	\N	\N
645	11	1	2012-08-05 23:05:07.877	\N	\N	\N	\N
656	11	1	2012-08-05 23:05:09.267	\N	\N	\N	\N
661	11	1	2012-08-05 23:05:09.902	\N	\N	\N	\N
664	11	1	2012-08-05 23:05:10.29	\N	\N	\N	\N
679	11	1	2012-08-05 23:05:12.159	\N	\N	\N	\N
696	11	1	2012-08-05 23:05:14.181	\N	\N	\N	\N
705	11	1	2012-08-05 23:05:15.353	\N	\N	\N	\N
707	11	1	2012-08-05 23:05:15.597	\N	\N	\N	\N
712	11	1	2012-08-05 23:05:16.198	\N	\N	\N	\N
721	11	1	2012-08-05 23:05:17.362	\N	\N	\N	\N
724	11	1	2012-08-05 23:05:17.728	\N	\N	\N	\N
727	11	1	2012-08-05 23:05:18.113	\N	\N	\N	\N
420	11	1	2012-08-05 23:01:28.799	\N	\N	\N	\N
423	11	1	2012-08-05 23:01:29.17	\N	\N	\N	\N
444	11	1	2012-08-05 23:01:31.762	\N	\N	\N	\N
455	11	1	2012-08-05 23:01:33.11	\N	\N	\N	\N
460	11	1	2012-08-05 23:01:33.732	\N	\N	\N	\N
471	11	1	2012-08-05 23:01:35.104	\N	\N	\N	\N
497	11	1	2012-08-05 23:01:38.275	\N	\N	\N	\N
505	11	1	2012-08-05 23:01:39.325	\N	\N	\N	\N
515	11	1	2012-08-05 23:02:15.113	\N	\N	\N	\N
517	11	1	2012-08-05 23:02:15.397	\N	\N	\N	\N
520	11	1	2012-08-05 23:02:15.782	\N	\N	\N	\N
533	11	1	2012-08-05 23:02:18.339	\N	\N	\N	\N
546	11	1	2012-08-05 23:02:19.973	\N	\N	\N	\N
552	11	1	2012-08-05 23:02:20.761	\N	\N	\N	\N
555	11	1	2012-08-05 23:02:21.13	\N	\N	\N	\N
570	11	1	2012-08-05 23:02:23.001	\N	\N	\N	\N
580	11	1	2012-08-05 23:02:24.296	\N	\N	\N	\N
598	11	1	2012-08-05 23:02:26.517	\N	\N	\N	\N
601	11	1	2012-08-05 23:02:26.93	\N	\N	\N	\N
606	11	1	2012-08-05 23:02:27.641	\N	\N	\N	\N
608	11	1	2012-08-05 23:02:28.035	\N	\N	\N	\N
610	11	1	2012-08-05 23:02:28.268	\N	\N	\N	\N
613	11	1	2012-08-05 23:02:28.659	\N	\N	\N	\N
616	11	1	2012-08-05 23:02:29.028	\N	\N	\N	\N
627	11	1	2012-08-05 23:05:03.269	\N	\N	\N	\N
629	11	1	2012-08-05 23:05:04.698	\N	\N	\N	\N
652	11	1	2012-08-05 23:05:08.775	\N	\N	\N	\N
653	11	1	2012-08-05 23:05:08.918	\N	\N	\N	\N
655	11	1	2012-08-05 23:05:09.15	\N	\N	\N	\N
657	11	1	2012-08-05 23:05:09.389	\N	\N	\N	\N
666	11	1	2012-08-05 23:05:10.553	\N	\N	\N	\N
676	11	1	2012-08-05 23:05:11.781	\N	\N	\N	\N
677	11	1	2012-08-05 23:05:11.901	\N	\N	\N	\N
682	11	1	2012-08-05 23:05:12.502	\N	\N	\N	\N
684	11	1	2012-08-05 23:05:12.733	\N	\N	\N	\N
695	11	1	2012-08-05 23:05:14.066	\N	\N	\N	\N
699	11	1	2012-08-05 23:05:14.555	\N	\N	\N	\N
725	11	1	2012-08-05 23:05:17.857	\N	\N	\N	\N
732	11	1	2012-08-05 23:05:18.781	\N	\N	\N	\N
734	11	1	2012-08-05 23:05:19.019	\N	\N	\N	\N
741	11	1	2012-08-05 23:07:07.318	\N	\N	\N	\N
745	11	1	2012-08-05 23:08:09.258	\N	\N	\N	\N
424	11	1	2012-08-05 23:01:29.299	\N	\N	\N	\N
428	11	1	2012-08-05 23:01:29.815	\N	\N	\N	\N
431	11	1	2012-08-05 23:01:30.172	\N	\N	\N	\N
442	11	1	2012-08-05 23:01:31.535	\N	\N	\N	\N
446	11	1	2012-08-05 23:01:31.995	\N	\N	\N	\N
452	11	1	2012-08-05 23:01:32.756	\N	\N	\N	\N
457	11	1	2012-08-05 23:01:33.375	\N	\N	\N	\N
466	11	1	2012-08-05 23:01:34.482	\N	\N	\N	\N
485	11	1	2012-08-05 23:01:36.815	\N	\N	\N	\N
488	11	1	2012-08-05 23:01:37.173	\N	\N	\N	\N
499	11	1	2012-08-05 23:01:38.517	\N	\N	\N	\N
504	11	1	2012-08-05 23:01:39.202	\N	\N	\N	\N
512	11	1	2012-08-05 23:01:40.171	\N	\N	\N	\N
519	11	1	2012-08-05 23:02:15.647	\N	\N	\N	\N
523	11	1	2012-08-05 23:02:16.164	\N	\N	\N	\N
528	11	1	2012-08-05 23:02:17.693	\N	\N	\N	\N
541	11	1	2012-08-05 23:02:19.372	\N	\N	\N	\N
543	11	1	2012-08-05 23:02:19.61	\N	\N	\N	\N
553	11	1	2012-08-05 23:02:20.891	\N	\N	\N	\N
557	11	1	2012-08-05 23:02:21.361	\N	\N	\N	\N
559	11	1	2012-08-05 23:02:21.60	\N	\N	\N	\N
563	11	1	2012-08-05 23:02:22.121	\N	\N	\N	\N
567	11	1	2012-08-05 23:02:22.646	\N	\N	\N	\N
572	11	1	2012-08-05 23:02:23.258	\N	\N	\N	\N
592	11	1	2012-08-05 23:02:25.791	\N	\N	\N	\N
594	11	1	2012-08-05 23:02:26.034	\N	\N	\N	\N
617	11	1	2012-08-05 23:02:29.162	\N	\N	\N	\N
619	11	1	2012-08-05 23:02:29.404	\N	\N	\N	\N
628	11	1	2012-08-05 23:05:04.556	\N	\N	\N	\N
630	11	1	2012-08-05 23:05:04.815	\N	\N	\N	\N
663	11	1	2012-08-05 23:05:10.157	\N	\N	\N	\N
670	11	1	2012-08-05 23:05:11.026	\N	\N	\N	\N
673	11	1	2012-08-05 23:05:11.405	\N	\N	\N	\N
675	11	1	2012-08-05 23:05:11.653	\N	\N	\N	\N
698	11	1	2012-08-05 23:05:14.435	\N	\N	\N	\N
702	11	1	2012-08-05 23:05:14.938	\N	\N	\N	\N
714	11	1	2012-08-05 23:05:16.45	\N	\N	\N	\N
723	11	1	2012-08-05 23:05:17.605	\N	\N	\N	\N
726	11	1	2012-08-05 23:05:17.99	\N	\N	\N	\N
728	11	1	2012-08-05 23:05:18.236	\N	\N	\N	\N
733	11	1	2012-08-05 23:05:18.904	\N	\N	\N	\N
744	11	1	2012-08-05 23:08:08.331	\N	\N	\N	\N
435	11	1	2012-08-05 23:01:30.665	\N	\N	\N	\N
438	11	1	2012-08-05 23:01:31.038	\N	\N	\N	\N
440	11	1	2012-08-05 23:01:31.302	\N	\N	\N	\N
454	11	1	2012-08-05 23:01:32.994	\N	\N	\N	\N
456	11	1	2012-08-05 23:01:33.247	\N	\N	\N	\N
462	11	1	2012-08-05 23:01:33.976	\N	\N	\N	\N
468	11	1	2012-08-05 23:01:34.724	\N	\N	\N	\N
474	11	1	2012-08-05 23:01:35.486	\N	\N	\N	\N
480	11	1	2012-08-05 23:01:36.234	\N	\N	\N	\N
484	11	1	2012-08-05 23:01:36.698	\N	\N	\N	\N
490	11	1	2012-08-05 23:01:37.431	\N	\N	\N	\N
491	11	1	2012-08-05 23:01:37.562	\N	\N	\N	\N
495	11	1	2012-08-05 23:01:38.029	\N	\N	\N	\N
502	11	1	2012-08-05 23:01:38.921	\N	\N	\N	\N
506	11	1	2012-08-05 23:01:39.44	\N	\N	\N	\N
525	11	1	2012-08-05 23:02:16.429	\N	\N	\N	\N
527	11	1	2012-08-05 23:02:17.531	\N	\N	\N	\N
560	11	1	2012-08-05 23:02:21.724	\N	\N	\N	\N
577	11	1	2012-08-05 23:02:23.896	\N	\N	\N	\N
581	11	1	2012-08-05 23:02:24.417	\N	\N	\N	\N
583	11	1	2012-08-05 23:02:24.653	\N	\N	\N	\N
586	11	1	2012-08-05 23:02:25.021	\N	\N	\N	\N
599	11	1	2012-08-05 23:02:26.652	\N	\N	\N	\N
615	11	1	2012-08-05 23:02:28.90	\N	\N	\N	\N
623	11	1	2012-08-05 23:02:29.861	\N	\N	\N	\N
625	11	1	2012-08-05 23:02:30.106	\N	\N	\N	\N
631	11	1	2012-08-05 23:05:04.936	\N	\N	\N	\N
637	11	1	2012-08-05 23:05:05.692	\N	\N	\N	\N
644	11	1	2012-08-05 23:05:07.756	\N	\N	\N	\N
651	11	1	2012-08-05 23:05:08.642	\N	\N	\N	\N
658	11	1	2012-08-05 23:05:09.515	\N	\N	\N	\N
672	11	1	2012-08-05 23:05:11.277	\N	\N	\N	\N
674	11	1	2012-08-05 23:05:11.531	\N	\N	\N	\N
685	11	1	2012-08-05 23:05:12.853	\N	\N	\N	\N
700	11	1	2012-08-05 23:05:14.678	\N	\N	\N	\N
720	11	1	2012-08-05 23:05:17.244	\N	\N	\N	\N
735	11	1	2012-08-05 23:05:19.136	\N	\N	\N	\N
439	11	1	2012-08-05 23:01:31.17	\N	\N	\N	\N
443	11	1	2012-08-05 23:01:31.646	\N	\N	\N	\N
448	11	1	2012-08-05 23:01:32.239	\N	\N	\N	\N
469	11	1	2012-08-05 23:01:34.863	\N	\N	\N	\N
476	11	1	2012-08-05 23:01:35.725	\N	\N	\N	\N
481	11	1	2012-08-05 23:01:36.349	\N	\N	\N	\N
483	11	1	2012-08-05 23:01:36.581	\N	\N	\N	\N
492	11	1	2012-08-05 23:01:37.686	\N	\N	\N	\N
494	11	1	2012-08-05 23:01:37.916	\N	\N	\N	\N
498	11	1	2012-08-05 23:01:38.392	\N	\N	\N	\N
539	11	1	2012-08-05 23:02:19.121	\N	\N	\N	\N
545	11	1	2012-08-05 23:02:19.847	\N	\N	\N	\N
568	11	1	2012-08-05 23:02:22.761	\N	\N	\N	\N
579	11	1	2012-08-05 23:02:24.181	\N	\N	\N	\N
585	11	1	2012-08-05 23:02:24.893	\N	\N	\N	\N
596	11	1	2012-08-05 23:02:26.272	\N	\N	\N	\N
603	11	1	2012-08-05 23:02:27.219	\N	\N	\N	\N
604	11	1	2012-08-05 23:02:27.38	\N	\N	\N	\N
611	11	1	2012-08-05 23:02:28.403	\N	\N	\N	\N
614	11	1	2012-08-05 23:02:28.773	\N	\N	\N	\N
624	11	1	2012-08-05 23:02:29.982	\N	\N	\N	\N
626	11	1	2012-08-05 23:02:30.228	\N	\N	\N	\N
650	11	1	2012-08-05 23:05:08.509	\N	\N	\N	\N
659	11	1	2012-08-05 23:05:09.648	\N	\N	\N	\N
667	11	1	2012-08-05 23:05:10.674	\N	\N	\N	\N
687	11	1	2012-08-05 23:05:13.097	\N	\N	\N	\N
690	11	1	2012-08-05 23:05:13.463	\N	\N	\N	\N
691	11	1	2012-08-05 23:05:13.591	\N	\N	\N	\N
703	11	1	2012-08-05 23:05:15.078	\N	\N	\N	\N
713	11	1	2012-08-05 23:05:16.324	\N	\N	\N	\N
718	11	1	2012-08-05 23:05:16.992	\N	\N	\N	\N
730	11	1	2012-08-05 23:05:18.509	\N	\N	\N	\N
731	11	1	2012-08-05 23:05:18.661	\N	\N	\N	\N
736	11	1	2012-08-05 23:05:19.26	\N	\N	\N	\N
739	11	1	2012-08-05 23:05:19.65	\N	\N	\N	\N
\.


--
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY transactions (id, corporate_code, user_id, channel_code, created_time, status, cash_in_time, cash_out_time, sender_id, sender_country_code, sender_currency_code, sender_amount, beneficiary_id, beneficiary_country_code, beneficiary_currency_code, beneficiary_amount, forex_reference_id, beneficiary_city_id, auth1, auth2, fee_included, sender_note) FROM stdin;
1	AAA	1	02	2012-08-04 22:59:41.357	20	2012-08-04 22:59:41.358	\N	1	ID	IDR	2500000.00	2	AU	AUD	252.66	4	1	\N	\N	f	\N
21	AAA	1	02	2012-08-05 17:41:27.712	20	2012-07-15 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
22	AAA	1	02	2012-08-05 17:41:28.845	20	2012-08-03 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
23	AAA	1	02	2012-08-05 17:41:29.055	20	2012-07-12 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
24	AAA	1	02	2012-08-05 17:41:29.229	20	2012-08-30 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
25	AAA	1	02	2012-08-05 17:41:29.413	20	2012-08-18 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
26	AAA	1	02	2012-08-05 17:41:29.60	20	2012-07-13 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
27	AAA	1	02	2012-08-05 17:41:29.809	20	2012-07-16 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
28	AAA	1	02	2012-08-05 17:41:29.959	20	2012-07-13 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
29	AAA	1	02	2012-08-05 17:41:30.111	20	2012-07-29 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
30	AAA	1	02	2012-08-05 17:41:30.268	20	2012-08-07 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
31	AAA	1	02	2012-08-05 17:41:30.432	20	2012-08-23 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
32	AAA	1	02	2012-08-05 17:41:30.611	20	2012-08-04 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
33	AAA	1	02	2012-08-05 17:41:30.796	20	2012-08-18 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
34	AAA	1	02	2012-08-05 17:41:31.807	20	2012-08-08 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
35	AAA	1	02	2012-08-05 17:41:31.949	20	2012-07-12 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
36	AAA	1	02	2012-08-05 17:41:32.091	20	2012-08-04 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
37	AAA	1	02	2012-08-05 17:41:32.229	20	2012-08-23 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
38	AAA	1	02	2012-08-05 17:41:32.373	20	2012-08-02 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
39	AAA	1	02	2012-08-05 17:41:32.515	20	2012-08-23 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
40	AAA	1	02	2012-08-05 17:41:32.712	20	2012-08-13 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
41	AAA	1	02	2012-08-05 17:41:32.909	20	2012-07-18 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
42	AAA	1	02	2012-08-05 17:41:33.095	20	2012-08-08 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
43	AAA	1	02	2012-08-05 17:41:33.237	20	2012-07-14 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
44	AAA	1	02	2012-08-05 17:41:33.379	20	2012-07-23 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
45	AAA	1	02	2012-08-05 17:41:33.526	20	2012-08-23 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
46	AAA	1	02	2012-08-05 17:41:33.676	20	2012-08-20 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
47	AAA	1	02	2012-08-05 17:41:33.817	20	2012-08-21 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
48	AAA	1	02	2012-08-05 17:41:33.957	20	2012-08-19 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
49	AAA	1	02	2012-08-05 17:41:34.094	20	2012-08-08 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
50	AAA	1	02	2012-08-05 17:41:34.231	20	2012-07-22 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
51	AAA	1	02	2012-08-05 17:41:34.375	20	2012-07-26 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
52	AAA	1	02	2012-08-05 17:41:34.513	20	2012-08-25 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
53	AAA	1	02	2012-08-05 17:41:34.653	20	2012-08-17 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
54	AAA	1	02	2012-08-05 17:41:34.792	20	2012-08-03 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
55	AAA	1	02	2012-08-05 17:41:34.928	20	2012-08-13 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
56	AAA	1	02	2012-08-05 17:41:35.072	20	2012-08-20 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
57	AAA	1	02	2012-08-05 17:41:35.224	20	2012-08-25 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
58	AAA	1	02	2012-08-05 17:41:35.385	20	2012-08-11 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
59	AAA	1	02	2012-08-05 17:41:35.555	20	2012-08-10 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
60	AAA	1	02	2012-08-05 17:41:35.694	20	2012-07-27 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
61	AAA	1	02	2012-08-05 17:41:35.831	20	2012-08-17 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
62	AAA	1	02	2012-08-05 17:41:35.962	20	2012-07-12 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
63	AAA	1	02	2012-08-05 17:41:36.09	20	2012-08-14 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
64	AAA	1	02	2012-08-05 17:41:36.228	20	2012-08-27 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
65	AAA	1	02	2012-08-05 17:41:36.352	20	2012-08-11 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
66	AAA	1	02	2012-08-05 17:41:36.486	20	2012-07-27 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
67	AAA	1	02	2012-08-05 17:41:36.618	20	2012-08-11 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
68	AAA	1	02	2012-08-05 17:41:36.766	20	2012-08-18 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
69	AAA	1	02	2012-08-05 17:41:36.906	20	2012-08-05 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
70	AAA	1	02	2012-08-05 17:41:37.047	20	2012-08-04 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
71	AAA	1	02	2012-08-05 17:41:37.194	20	2012-07-20 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
75	AAA	1	02	2012-08-05 17:41:37.748	20	2012-07-16 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
85	AAA	1	02	2012-08-05 17:41:39.141	20	2012-08-13 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
87	AAA	1	02	2012-08-05 17:41:39.418	20	2012-08-04 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
98	AAA	1	02	2012-08-05 17:41:40.872	20	2012-07-15 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
101	AAA	1	02	2012-08-05 17:41:41.26	20	2012-07-29 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
115	AAA	1	02	2012-08-05 17:41:43.122	20	2012-07-26 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
123	AAA	1	02	2012-08-05 17:41:44.203	20	2012-08-28 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
127	AAA	1	02	2012-08-05 17:41:44.744	20	2012-08-21 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
72	AAA	1	02	2012-08-05 17:41:37.353	20	2012-07-27 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
74	AAA	1	02	2012-08-05 17:41:37.618	20	2012-08-15 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
86	AAA	1	02	2012-08-05 17:41:39.294	20	2012-07-27 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
112	AAA	1	02	2012-08-05 17:41:42.723	20	2012-08-07 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
73	AAA	1	02	2012-08-05 17:41:37.491	20	2012-07-28 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
99	AAA	1	02	2012-08-05 17:41:41.009	20	2012-08-23 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
100	AAA	1	02	2012-08-05 17:41:41.131	20	2012-07-15 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
111	AAA	1	02	2012-08-05 17:41:42.586	20	2012-07-12 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
113	AAA	1	02	2012-08-05 17:41:42.854	20	2012-07-13 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
125	AAA	1	02	2012-08-05 17:41:44.489	20	2012-08-19 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
76	AAA	1	02	2012-08-05 17:41:37.874	20	2012-08-10 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
84	AAA	1	02	2012-08-05 17:41:38.992	20	2012-08-18 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
88	AAA	1	02	2012-08-05 17:41:39.542	20	2012-07-31 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
97	AAA	1	02	2012-08-05 17:41:40.738	20	2012-07-23 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
102	AAA	1	02	2012-08-05 17:41:41.382	20	2012-08-01 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
110	AAA	1	02	2012-08-05 17:41:42.434	20	2012-08-15 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
114	AAA	1	02	2012-08-05 17:41:42.999	20	2012-07-26 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
124	AAA	1	02	2012-08-05 17:41:44.354	20	2012-07-30 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
126	AAA	1	02	2012-08-05 17:41:44.614	20	2012-07-27 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
77	AAA	1	02	2012-08-05 17:41:38.002	20	2012-07-20 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
78	AAA	1	02	2012-08-05 17:41:38.14	20	2012-07-30 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
79	AAA	1	02	2012-08-05 17:41:38.278	20	2012-08-17 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
80	AAA	1	02	2012-08-05 17:41:38.414	20	2012-08-06 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
81	AAA	1	02	2012-08-05 17:41:38.551	20	2012-08-23 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
82	AAA	1	02	2012-08-05 17:41:38.69	20	2012-07-26 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
83	AAA	1	02	2012-08-05 17:41:38.829	20	2012-07-21 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
89	AAA	1	02	2012-08-05 17:41:39.672	20	2012-08-28 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
90	AAA	1	02	2012-08-05 17:41:39.80	20	2012-07-13 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
91	AAA	1	02	2012-08-05 17:41:39.926	20	2012-08-10 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
92	AAA	1	02	2012-08-05 17:41:40.059	20	2012-08-15 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
93	AAA	1	02	2012-08-05 17:41:40.194	20	2012-08-10 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
94	AAA	1	02	2012-08-05 17:41:40.33	20	2012-07-29 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
95	AAA	1	02	2012-08-05 17:41:40.464	20	2012-08-28 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
96	AAA	1	02	2012-08-05 17:41:40.60	20	2012-07-14 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
103	AAA	1	02	2012-08-05 17:41:41.511	20	2012-07-24 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
116	AAA	1	02	2012-08-05 17:41:43.246	20	2012-08-16 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
128	AAA	1	02	2012-08-05 17:41:44.87	20	2012-08-21 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
104	AAA	1	02	2012-08-05 17:41:41.63	20	2012-08-25 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
105	AAA	1	02	2012-08-05 17:41:41.768	20	2012-07-26 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
106	AAA	1	02	2012-08-05 17:41:41.902	20	2012-08-20 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
107	AAA	1	02	2012-08-05 17:41:42.039	20	2012-07-15 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
108	AAA	1	02	2012-08-05 17:41:42.168	20	2012-07-16 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
109	AAA	1	02	2012-08-05 17:41:42.298	20	2012-08-28 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
117	AAA	1	02	2012-08-05 17:41:43.363	20	2012-08-20 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
118	AAA	1	02	2012-08-05 17:41:43.502	20	2012-07-20 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
119	AAA	1	02	2012-08-05 17:41:43.645	20	2012-08-11 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
120	AAA	1	02	2012-08-05 17:41:43.774	20	2012-08-02 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
121	AAA	1	02	2012-08-05 17:41:43.908	20	2012-07-27 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
122	AAA	1	02	2012-08-05 17:41:44.042	20	2012-08-14 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
129	AAA	1	02	2012-08-05 17:41:44.993	20	2012-08-28 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
130	AAA	1	02	2012-08-05 17:41:45.118	20	2012-08-04 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
131	AAA	1	02	2012-08-05 17:41:45.263	20	2012-08-01 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
132	AAA	1	02	2012-08-05 17:41:45.389	20	2012-07-22 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
133	AAA	1	02	2012-08-05 17:41:45.516	20	2012-08-29 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
134	AAA	1	02	2012-08-05 18:56:45.322	20	2012-07-14 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
135	AAA	1	02	2012-08-05 18:56:46.51	20	2012-08-30 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
136	AAA	1	02	2012-08-05 18:56:46.702	20	2012-08-08 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
137	AAA	1	02	2012-08-05 18:56:46.873	20	2012-08-03 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
138	AAA	1	02	2012-08-05 18:56:47.027	20	2012-08-15 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
139	AAA	1	02	2012-08-05 18:56:47.181	20	2012-08-17 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
140	AAA	1	02	2012-08-05 18:56:47.334	20	2012-07-31 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
141	AAA	1	02	2012-08-05 18:56:47.457	20	2012-07-12 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
142	AAA	1	02	2012-08-05 18:56:47.585	20	2012-08-20 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
143	AAA	1	02	2012-08-05 18:56:47.707	20	2012-08-21 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
144	AAA	1	02	2012-08-05 18:56:47.834	20	2012-08-20 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
145	AAA	1	02	2012-08-05 18:56:47.974	20	2012-07-24 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
146	AAA	1	02	2012-08-05 18:56:48.122	20	2012-08-12 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
147	AAA	1	02	2012-08-05 18:56:49.112	20	2012-08-01 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
148	AAA	1	02	2012-08-05 18:56:49.231	20	2012-08-22 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
149	AAA	1	02	2012-08-05 18:56:49.348	20	2012-08-23 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
150	AAA	1	02	2012-08-05 18:56:49.476	20	2012-07-20 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
151	AAA	1	02	2012-08-05 18:56:49.598	20	2012-08-12 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
152	AAA	1	02	2012-08-05 18:56:49.717	20	2012-07-31 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
153	AAA	1	02	2012-08-05 18:56:49.845	20	2012-08-24 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
154	AAA	1	02	2012-08-05 18:56:49.965	20	2012-08-06 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
155	AAA	1	02	2012-08-05 18:56:50.087	20	2012-08-22 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
156	AAA	1	02	2012-08-05 18:56:50.213	20	2012-08-26 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
157	AAA	1	02	2012-08-05 18:56:50.336	20	2012-08-02 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
158	AAA	1	02	2012-08-05 18:56:50.474	20	2012-07-29 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
159	AAA	1	02	2012-08-05 18:56:50.618	20	2012-08-16 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
160	AAA	1	02	2012-08-05 18:56:50.768	20	2012-08-04 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
161	AAA	1	02	2012-08-05 18:56:50.884	20	2012-07-21 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
162	AAA	1	02	2012-08-05 18:56:51	20	2012-08-14 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
163	AAA	1	02	2012-08-05 18:56:51.122	20	2012-07-25 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
164	AAA	1	02	2012-08-05 18:56:51.243	20	2012-07-16 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
165	AAA	1	02	2012-08-05 18:56:51.366	20	2012-07-13 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
166	AAA	1	02	2012-08-05 18:56:51.487	20	2012-08-01 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
167	AAA	1	02	2012-08-05 18:56:51.612	20	2012-08-06 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
168	AAA	1	02	2012-08-05 18:56:51.737	20	2012-07-22 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
169	AAA	1	02	2012-08-05 18:56:51.858	20	2012-08-10 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
178	AAA	1	02	2012-08-05 18:56:53.038	20	2012-07-30 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
179	AAA	1	02	2012-08-05 18:56:53.161	20	2012-08-13 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
180	AAA	1	02	2012-08-05 18:56:53.296	20	2012-08-21 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
181	AAA	1	02	2012-08-05 18:56:53.417	20	2012-07-30 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
182	AAA	1	02	2012-08-05 18:56:53.535	20	2012-08-23 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
183	AAA	1	02	2012-08-05 18:56:53.654	20	2012-08-03 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
203	AAA	1	02	2012-08-05 18:56:56.468	20	2012-07-27 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
207	AAA	1	02	2012-08-05 18:56:56.997	20	2012-07-22 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
208	AAA	1	02	2012-08-05 18:56:57.125	20	2012-07-26 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
209	AAA	1	02	2012-08-05 18:56:57.248	20	2012-07-12 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
217	AAA	1	02	2012-08-05 18:56:58.217	20	2012-08-20 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
218	AAA	1	02	2012-08-05 18:56:58.337	20	2012-08-07 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
219	AAA	1	02	2012-08-05 18:56:58.458	20	2012-07-26 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
220	AAA	1	02	2012-08-05 18:56:58.588	20	2012-08-04 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
221	AAA	1	02	2012-08-05 18:56:58.713	20	2012-08-03 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
222	AAA	1	02	2012-08-05 18:56:58.832	20	2012-08-16 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
228	AAA	1	02	2012-08-05 18:56:59.56	20	2012-07-27 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
242	AAA	1	02	2012-08-05 18:57:01.323	20	2012-08-07 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
170	AAA	1	02	2012-08-05 18:56:51.998	20	2012-08-17 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
176	AAA	1	02	2012-08-05 18:56:52.789	20	2012-07-20 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
188	AAA	1	02	2012-08-05 18:56:54.601	20	2012-07-13 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
191	AAA	1	02	2012-08-05 18:56:54.973	20	2012-08-29 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
192	AAA	1	02	2012-08-05 18:56:55.10	20	2012-07-18 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
193	AAA	1	02	2012-08-05 18:56:55.224	20	2012-07-13 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
194	AAA	1	02	2012-08-05 18:56:55.357	20	2012-08-28 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
195	AAA	1	02	2012-08-05 18:56:55.479	20	2012-08-24 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
196	AAA	1	02	2012-08-05 18:56:55.613	20	2012-08-18 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
202	AAA	1	02	2012-08-05 18:56:56.351	20	2012-08-26 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
216	AAA	1	02	2012-08-05 18:56:58.10	20	2012-08-04 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
223	AAA	1	02	2012-08-05 18:56:58.963	20	2012-08-04 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
227	AAA	1	02	2012-08-05 18:56:59.446	20	2012-07-17 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
231	AAA	1	02	2012-08-05 18:56:59.939	20	2012-08-18 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
232	AAA	1	02	2012-08-05 18:57:00.072	20	2012-08-22 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
233	AAA	1	02	2012-08-05 18:57:00.217	20	2012-08-04 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
234	AAA	1	02	2012-08-05 18:57:00.339	20	2012-08-26 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
235	AAA	1	02	2012-08-05 18:57:00.459	20	2012-08-11 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
241	AAA	1	02	2012-08-05 18:57:01.206	20	2012-08-19 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
171	AAA	1	02	2012-08-05 18:56:52.144	20	2012-08-26 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
175	AAA	1	02	2012-08-05 18:56:52.666	20	2012-07-31 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
185	AAA	1	02	2012-08-05 18:56:54.218	20	2012-08-11 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
187	AAA	1	02	2012-08-05 18:56:54.484	20	2012-07-25 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
198	AAA	1	02	2012-08-05 18:56:55.866	20	2012-07-14 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
200	AAA	1	02	2012-08-05 18:56:56.111	20	2012-07-28 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
215	AAA	1	02	2012-08-05 18:56:57.982	20	2012-07-23 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
224	AAA	1	02	2012-08-05 18:56:59.095	20	2012-07-25 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
226	AAA	1	02	2012-08-05 18:56:59.333	20	2012-08-30 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
237	AAA	1	02	2012-08-05 18:57:00.718	20	2012-08-06 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
239	AAA	1	02	2012-08-05 18:57:00.972	20	2012-08-14 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
172	AAA	1	02	2012-08-05 18:56:52.283	20	2012-08-10 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
173	AAA	1	02	2012-08-05 18:56:52.417	20	2012-07-22 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
174	AAA	1	02	2012-08-05 18:56:52.547	20	2012-07-28 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
186	AAA	1	02	2012-08-05 18:56:54.366	20	2012-08-19 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
199	AAA	1	02	2012-08-05 18:56:55.997	20	2012-08-12 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
211	AAA	1	02	2012-08-05 18:56:57.514	20	2012-07-26 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
213	AAA	1	02	2012-08-05 18:56:57.752	20	2012-07-20 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
238	AAA	1	02	2012-08-05 18:57:00.849	20	2012-07-25 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
177	AAA	1	02	2012-08-05 18:56:52.919	20	2012-07-20 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
184	AAA	1	02	2012-08-05 18:56:53.798	20	2012-07-22 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
189	AAA	1	02	2012-08-05 18:56:54.716	20	2012-08-25 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
190	AAA	1	02	2012-08-05 18:56:54.837	20	2012-08-14 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
197	AAA	1	02	2012-08-05 18:56:55.74	20	2012-07-12 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
201	AAA	1	02	2012-08-05 18:56:56.226	20	2012-07-12 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
204	AAA	1	02	2012-08-05 18:56:56.593	20	2012-07-27 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
205	AAA	1	02	2012-08-05 18:56:56.741	20	2012-08-12 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
206	AAA	1	02	2012-08-05 18:56:56.862	20	2012-08-23 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
210	AAA	1	02	2012-08-05 18:56:57.381	20	2012-08-22 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
214	AAA	1	02	2012-08-05 18:56:57.867	20	2012-08-28 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
225	AAA	1	02	2012-08-05 18:56:59.218	20	2012-07-20 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
229	AAA	1	02	2012-08-05 18:56:59.683	20	2012-08-17 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
230	AAA	1	02	2012-08-05 18:56:59.801	20	2012-08-29 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
236	AAA	1	02	2012-08-05 18:57:00.585	20	2012-07-27 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
240	AAA	1	02	2012-08-05 18:57:01.087	20	2012-07-28 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
243	AAA	1	02	2012-08-05 18:57:01.439	20	2012-07-27 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
244	AAA	1	02	2012-08-05 18:57:01.557	20	2012-07-25 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
245	AAA	1	02	2012-08-05 18:57:01.678	20	2012-08-16 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
246	AAA	1	02	2012-08-05 18:57:01.802	20	2012-07-19 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
212	AAA	1	02	2012-08-05 18:56:57.638	20	2012-08-11 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
247	AAA	1	02	2012-08-05 22:59:40.965	20	2012-08-15 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
248	AAA	1	02	2012-08-05 22:59:42.144	20	2012-08-12 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
249	AAA	1	02	2012-08-05 22:59:42.347	20	2012-07-24 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
250	AAA	1	02	2012-08-05 22:59:42.501	20	2012-07-26 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
251	AAA	1	02	2012-08-05 22:59:42.653	20	2012-08-09 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
252	AAA	1	02	2012-08-05 22:59:42.787	20	2012-08-07 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
253	AAA	1	02	2012-08-05 22:59:42.919	20	2012-08-07 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
254	AAA	1	02	2012-08-05 22:59:43.045	20	2012-07-29 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
255	AAA	1	02	2012-08-05 22:59:43.173	20	2012-08-03 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
256	AAA	1	02	2012-08-05 22:59:43.305	20	2012-08-11 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
257	AAA	1	02	2012-08-05 22:59:43.432	20	2012-08-29 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
258	AAA	1	02	2012-08-05 22:59:43.581	20	2012-08-09 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
259	AAA	1	02	2012-08-05 22:59:43.751	20	2012-07-27 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
260	AAA	1	02	2012-08-05 22:59:44.829	20	2012-07-12 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
261	AAA	1	02	2012-08-05 22:59:44.957	20	2012-08-10 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
262	AAA	1	02	2012-08-05 22:59:45.079	20	2012-08-08 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
263	AAA	1	02	2012-08-05 22:59:45.20	20	2012-08-19 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
264	AAA	1	02	2012-08-05 22:59:45.316	20	2012-08-30 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
265	AAA	1	02	2012-08-05 22:59:45.457	20	2012-07-22 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
266	AAA	1	02	2012-08-05 22:59:45.607	20	2012-08-20 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
267	AAA	1	02	2012-08-05 22:59:45.73	20	2012-08-10 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
268	AAA	1	02	2012-08-05 22:59:45.866	20	2012-07-21 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
269	AAA	1	02	2012-08-05 22:59:45.994	20	2012-08-25 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
270	AAA	1	02	2012-08-05 22:59:46.117	20	2012-08-20 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
271	AAA	1	02	2012-08-05 22:59:46.264	20	2012-07-29 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
272	AAA	1	02	2012-08-05 22:59:46.401	20	2012-08-27 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
273	AAA	1	02	2012-08-05 22:59:46.533	20	2012-08-05 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
274	AAA	1	02	2012-08-05 22:59:46.656	20	2012-07-26 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
275	AAA	1	02	2012-08-05 22:59:46.772	20	2012-08-03 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
276	AAA	1	02	2012-08-05 22:59:46.888	20	2012-07-12 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
277	AAA	1	02	2012-08-05 22:59:47.001	20	2012-08-09 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
278	AAA	1	02	2012-08-05 22:59:47.119	20	2012-08-15 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
279	AAA	1	02	2012-08-05 22:59:47.244	20	2012-07-26 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
280	AAA	1	02	2012-08-05 22:59:47.371	20	2012-08-28 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
281	AAA	1	02	2012-08-05 22:59:47.496	20	2012-08-11 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
282	AAA	1	02	2012-08-05 22:59:47.614	20	2012-08-11 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
283	AAA	1	02	2012-08-05 22:59:47.733	20	2012-07-12 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
284	AAA	1	02	2012-08-05 22:59:47.886	20	2012-07-28 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
285	AAA	1	02	2012-08-05 22:59:48.065	20	2012-08-02 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
286	AAA	1	02	2012-08-05 22:59:48.233	20	2012-07-22 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
287	AAA	1	02	2012-08-05 22:59:48.374	20	2012-08-01 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
288	AAA	1	02	2012-08-05 22:59:48.528	20	2012-08-08 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
289	AAA	1	02	2012-08-05 22:59:48.653	20	2012-07-23 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
290	AAA	1	02	2012-08-05 22:59:48.791	20	2012-08-04 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
291	AAA	1	02	2012-08-05 22:59:48.915	20	2012-08-21 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
292	AAA	1	02	2012-08-05 22:59:49.045	20	2012-07-20 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
293	AAA	1	02	2012-08-05 22:59:49.18	20	2012-07-18 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
294	AAA	1	02	2012-08-05 22:59:49.301	20	2012-08-06 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
295	AAA	1	02	2012-08-05 22:59:49.425	20	2012-08-08 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
296	AAA	1	02	2012-08-05 22:59:49.551	20	2012-07-24 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
297	AAA	1	02	2012-08-05 22:59:49.687	20	2012-08-27 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
301	AAA	1	02	2012-08-05 22:59:50.191	20	2012-08-17 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
315	AAA	1	02	2012-08-05 22:59:51.878	20	2012-08-05 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
323	AAA	1	02	2012-08-05 22:59:52.883	20	2012-08-14 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
327	AAA	1	02	2012-08-05 22:59:53.365	20	2012-08-14 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
341	AAA	1	02	2012-08-05 22:59:55.254	20	2012-07-26 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
359	AAA	1	02	2012-08-05 22:59:57.478	20	2012-08-16 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
364	AAA	1	02	2012-08-05 23:01:26.063	20	2012-07-18 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
372	AAA	1	02	2012-08-05 23:01:27.119	20	2012-07-24 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
376	AAA	1	02	2012-08-05 23:01:28.492	20	2012-08-18 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
392	AAA	1	02	2012-08-05 23:01:30.483	20	2012-07-27 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
395	AAA	1	02	2012-08-05 23:01:30.846	20	2012-07-31 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
409	AAA	1	02	2012-08-05 23:01:32.551	20	2012-07-12 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
423	AAA	1	02	2012-08-05 23:01:34.286	20	2012-07-28 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
473	AAA	1	02	2012-08-05 23:02:14.119	20	2012-07-20 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
475	AAA	1	02	2012-08-05 23:02:15.34	20	2012-08-07 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
494	AAA	1	02	2012-08-05 23:02:18.664	20	2012-08-14 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
548	AAA	1	02	2012-08-05 23:02:25.458	20	2012-08-30 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
580	AAA	1	02	2012-08-05 23:02:29.691	20	2012-07-23 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
582	AAA	1	02	2012-08-05 23:02:29.92	20	2012-08-10 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
586	AAA	1	02	2012-08-05 23:05:03.545	20	2012-07-23 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
593	AAA	1	02	2012-08-05 23:05:05.368	20	2012-08-04 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
596	AAA	1	02	2012-08-05 23:05:05.755	20	2012-08-19 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
603	AAA	1	02	2012-08-05 23:05:07.816	20	2012-08-16 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
623	AAA	1	02	2012-08-05 23:05:10.355	20	2012-08-12 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
640	AAA	1	02	2012-08-05 23:05:12.448	20	2012-08-06 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
654	AAA	1	02	2012-08-05 23:05:14.126	20	2012-07-17 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
656	AAA	1	02	2012-08-05 23:05:14.365	20	2012-08-07 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
660	AAA	1	02	2012-08-05 23:05:14.864	20	2012-07-25 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
665	AAA	1	02	2012-08-05 23:05:15.533	20	2012-07-23 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
667	AAA	1	02	2012-08-05 23:05:15.779	20	2012-07-24 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
669	AAA	1	02	2012-08-05 23:05:16.011	20	2012-07-22 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
675	AAA	1	02	2012-08-05 23:05:16.773	20	2012-07-30 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
683	AAA	1	02	2012-08-05 23:05:17.787	20	2012-08-29 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
696	AAA	1	02	2012-08-05 23:05:19.453	20	2012-07-29 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
298	AAA	1	02	2012-08-05 22:59:49.831	20	2012-07-12 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
300	AAA	1	02	2012-08-05 22:59:50.074	20	2012-07-12 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
312	AAA	1	02	2012-08-05 22:59:51.536	20	2012-08-08 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
329	AAA	1	02	2012-08-05 22:59:53.595	20	2012-08-01 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
345	AAA	1	02	2012-08-05 22:59:55.736	20	2012-08-26 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
351	AAA	1	02	2012-08-05 22:59:56.524	20	2012-08-28 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
353	AAA	1	02	2012-08-05 22:59:56.757	20	2012-08-30 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
355	AAA	1	02	2012-08-05 22:59:57.002	20	2012-07-29 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
357	AAA	1	02	2012-08-05 22:59:57.235	20	2012-07-12 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
371	AAA	1	02	2012-08-05 23:01:26.977	20	2012-07-25 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
374	AAA	1	02	2012-08-05 23:01:28.259	20	2012-07-12 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
381	AAA	1	02	2012-08-05 23:01:29.107	20	2012-08-17 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
386	AAA	1	02	2012-08-05 23:01:29.76	20	2012-08-24 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
412	AAA	1	02	2012-08-05 23:01:32.94	20	2012-08-18 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
419	AAA	1	02	2012-08-05 23:01:33.794	20	2012-07-24 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
435	AAA	1	02	2012-08-05 23:01:35.783	20	2012-08-29 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
439	AAA	1	02	2012-08-05 23:01:36.291	20	2012-08-26 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
457	AAA	1	02	2012-08-05 23:01:38.453	20	2012-07-12 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
459	AAA	1	02	2012-08-05 23:01:38.704	20	2012-08-30 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
478	AAA	1	02	2012-08-05 23:02:15.707	20	2012-08-07 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
481	AAA	1	02	2012-08-05 23:02:16.097	20	2012-07-15 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
512	AAA	1	02	2012-08-05 23:02:20.951	20	2012-07-26 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
520	AAA	1	02	2012-08-05 23:02:21.92	20	2012-07-21 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
523	AAA	1	02	2012-08-05 23:02:22.331	20	2012-08-05 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
526	AAA	1	02	2012-08-05 23:02:22.709	20	2012-08-02 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
532	AAA	1	02	2012-08-05 23:02:23.458	20	2012-08-21 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
545	AAA	1	02	2012-08-05 23:02:25.089	20	2012-07-19 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
556	AAA	1	02	2012-08-05 23:02:26.452	20	2012-08-05 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
562	AAA	1	02	2012-08-05 23:02:27.286	20	2012-08-16 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
566	AAA	1	02	2012-08-05 23:02:27.928	20	2012-08-09 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
569	AAA	1	02	2012-08-05 23:02:28.336	20	2012-07-18 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
573	AAA	1	02	2012-08-05 23:02:28.834	20	2012-08-13 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
577	AAA	1	02	2012-08-05 23:02:29.348	20	2012-08-04 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
583	AAA	1	02	2012-08-05 23:02:30.045	20	2012-08-01 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
590	AAA	1	02	2012-08-05 23:05:04.994	20	2012-08-26 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
617	AAA	1	02	2012-08-05 23:05:09.577	20	2012-08-23 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
626	AAA	1	02	2012-08-05 23:05:10.736	20	2012-08-10 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
655	AAA	1	02	2012-08-05 23:05:14.245	20	2012-08-15 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
662	AAA	1	02	2012-08-05 23:05:15.143	20	2012-08-07 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
670	AAA	1	02	2012-08-05 23:05:16.134	20	2012-07-12 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
678	AAA	1	02	2012-08-05 23:05:17.186	20	2012-08-20 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
686	AAA	1	02	2012-08-05 23:05:18.174	20	2012-08-13 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
691	AAA	1	02	2012-08-05 23:05:18.847	20	2012-07-29 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
299	AAA	1	02	2012-08-05 22:59:49.957	20	2012-07-30 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
311	AAA	1	02	2012-08-05 22:59:51.406	20	2012-08-02 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
313	AAA	1	02	2012-08-05 22:59:51.652	20	2012-08-05 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
325	AAA	1	02	2012-08-05 22:59:53.137	20	2012-08-02 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
330	AAA	1	02	2012-08-05 22:59:53.712	20	2012-08-18 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
336	AAA	1	02	2012-08-05 22:59:54.56	20	2012-07-17 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
347	AAA	1	02	2012-08-05 22:59:55.986	20	2012-07-30 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
368	AAA	1	02	2012-08-05 23:01:26.574	20	2012-07-26 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
370	AAA	1	02	2012-08-05 23:01:26.843	20	2012-08-22 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
378	AAA	1	02	2012-08-05 23:01:28.74	20	2012-08-25 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
387	AAA	1	02	2012-08-05 23:01:29.878	20	2012-07-16 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
398	AAA	1	02	2012-08-05 23:01:31.236	20	2012-07-31 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
405	AAA	1	02	2012-08-05 23:01:32.058	20	2012-08-09 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
408	AAA	1	02	2012-08-05 23:01:32.427	20	2012-08-03 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
418	AAA	1	02	2012-08-05 23:01:33.674	20	2012-08-19 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
425	AAA	1	02	2012-08-05 23:01:34.542	20	2012-07-29 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
434	AAA	1	02	2012-08-05 23:01:35.661	20	2012-08-04 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
448	AAA	1	02	2012-08-05 23:01:37.356	20	2012-07-15 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
454	AAA	1	02	2012-08-05 23:01:38.092	20	2012-07-30 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
463	AAA	1	02	2012-08-05 23:01:39.259	20	2012-08-04 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
477	AAA	1	02	2012-08-05 23:02:15.582	20	2012-07-14 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
479	AAA	1	02	2012-08-05 23:02:15.85	20	2012-08-03 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
482	AAA	1	02	2012-08-05 23:02:16.23	20	2012-08-08 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
486	AAA	1	02	2012-08-05 23:02:17.633	20	2012-08-11 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
495	AAA	1	02	2012-08-05 23:02:18.788	20	2012-07-23 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
501	AAA	1	02	2012-08-05 23:02:19.556	20	2012-08-21 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
518	AAA	1	02	2012-08-05 23:02:21.663	20	2012-08-23 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
525	AAA	1	02	2012-08-05 23:02:22.592	20	2012-07-29 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
538	AAA	1	02	2012-08-05 23:02:24.241	20	2012-07-28 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
560	AAA	1	02	2012-08-05 23:02:26.99	20	2012-07-20 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
572	AAA	1	02	2012-08-05 23:02:28.716	20	2012-08-18 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
576	AAA	1	02	2012-08-05 23:02:29.22	20	2012-08-23 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
598	AAA	1	02	2012-08-05 23:05:06.045	20	2012-08-04 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
599	AAA	1	02	2012-08-05 23:05:07.337	20	2012-07-18 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
602	AAA	1	02	2012-08-05 23:05:07.703	20	2012-07-14 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
605	AAA	1	02	2012-08-05 23:05:08.063	20	2012-08-19 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
619	AAA	1	02	2012-08-05 23:05:09.832	20	2012-08-29 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
625	AAA	1	02	2012-08-05 23:05:10.614	20	2012-07-25 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
633	AAA	1	02	2012-08-05 23:05:11.595	20	2012-07-19 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
637	AAA	1	02	2012-08-05 23:05:12.096	20	2012-08-20 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
642	AAA	1	02	2012-08-05 23:05:12.681	20	2012-08-02 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
644	AAA	1	02	2012-08-05 23:05:12.913	20	2012-08-12 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
647	AAA	1	02	2012-08-05 23:05:13.277	20	2012-07-30 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
657	AAA	1	02	2012-08-05 23:05:14.493	20	2012-08-06 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
671	AAA	1	02	2012-08-05 23:05:16.259	20	2012-08-22 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
681	AAA	1	02	2012-08-05 23:05:17.545	20	2012-08-27 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
688	AAA	1	02	2012-08-05 23:05:18.443	20	2012-08-17 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
689	AAA	1	02	2012-08-05 23:05:18.575	20	2012-08-23 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
302	AAA	1	02	2012-08-05 22:59:50.31	20	2012-08-05 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
310	AAA	1	02	2012-08-05 22:59:51.28	20	2012-07-24 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
314	AAA	1	02	2012-08-05 22:59:51.763	20	2012-07-12 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
324	AAA	1	02	2012-08-05 22:59:53.017	20	2012-08-03 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
326	AAA	1	02	2012-08-05 22:59:53.25	20	2012-08-20 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
338	AAA	1	02	2012-08-05 22:59:54.868	20	2012-07-27 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
360	AAA	1	02	2012-08-05 23:01:24.584	20	2012-07-29 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
362	AAA	1	02	2012-08-05 23:01:25.819	20	2012-07-30 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
367	AAA	1	02	2012-08-05 23:01:26.446	20	2012-07-29 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
373	AAA	1	02	2012-08-05 23:01:28.144	20	2012-08-29 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
384	AAA	1	02	2012-08-05 23:01:29.501	20	2012-07-26 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
399	AAA	1	02	2012-08-05 23:01:31.362	20	2012-07-29 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
406	AAA	1	02	2012-08-05 23:01:32.177	20	2012-07-23 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
410	AAA	1	02	2012-08-05 23:01:32.678	20	2012-08-16 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
413	AAA	1	02	2012-08-05 23:01:33.056	20	2012-07-27 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
424	AAA	1	02	2012-08-05 23:01:34.415	20	2012-07-14 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
427	AAA	1	02	2012-08-05 23:01:34.785	20	2012-07-31 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
441	AAA	1	02	2012-08-05 23:01:36.524	20	2012-07-25 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
445	AAA	1	02	2012-08-05 23:01:36.99	20	2012-07-30 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
449	AAA	1	02	2012-08-05 23:01:37.498	20	2012-08-12 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
452	AAA	1	02	2012-08-05 23:01:37.864	20	2012-08-24 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
458	AAA	1	02	2012-08-05 23:01:38.581	20	2012-07-14 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
461	AAA	1	02	2012-08-05 23:01:38.985	20	2012-07-20 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
462	AAA	1	02	2012-08-05 23:01:39.126	20	2012-07-23 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
467	AAA	1	02	2012-08-05 23:01:39.741	20	2012-08-03 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
488	AAA	1	02	2012-08-05 23:02:17.885	20	2012-08-06 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
493	AAA	1	02	2012-08-05 23:02:18.526	20	2012-08-15 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
496	AAA	1	02	2012-08-05 23:02:18.913	20	2012-07-16 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
505	AAA	1	02	2012-08-05 23:02:20.04	20	2012-08-18 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
509	AAA	1	02	2012-08-05 23:02:20.551	20	2012-07-28 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
510	AAA	1	02	2012-08-05 23:02:20.692	20	2012-07-30 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
515	AAA	1	02	2012-08-05 23:02:21.306	20	2012-08-24 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
542	AAA	1	02	2012-08-05 23:02:24.714	20	2012-08-21 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
547	AAA	1	02	2012-08-05 23:02:25.333	20	2012-08-02 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
552	AAA	1	02	2012-08-05 23:02:25.973	20	2012-08-21 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
563	AAA	1	02	2012-08-05 23:02:27.447	20	2012-07-16 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
592	AAA	1	02	2012-08-05 23:05:05.243	20	2012-08-11 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
608	AAA	1	02	2012-08-05 23:05:08.444	20	2012-07-19 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
613	AAA	1	02	2012-08-05 23:05:09.092	20	2012-08-01 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
615	AAA	1	02	2012-08-05 23:05:09.333	20	2012-07-30 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
620	AAA	1	02	2012-08-05 23:05:09.962	20	2012-07-13 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
630	AAA	1	02	2012-08-05 23:05:11.208	20	2012-07-13 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
636	AAA	1	02	2012-08-05 23:05:11.958	20	2012-07-25 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
664	AAA	1	02	2012-08-05 23:05:15.414	20	2012-08-11 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
676	AAA	1	02	2012-08-05 23:05:16.924	20	2012-08-25 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
685	AAA	1	02	2012-08-05 23:05:18.052	20	2012-07-12 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
690	AAA	1	02	2012-08-05 23:05:18.723	20	2012-08-06 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
303	AAA	1	02	2012-08-05 22:59:50.425	20	2012-08-11 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
309	AAA	1	02	2012-08-05 22:59:51.153	20	2012-08-02 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
316	AAA	1	02	2012-08-05 22:59:51.996	20	2012-08-20 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
322	AAA	1	02	2012-08-05 22:59:52.739	20	2012-07-31 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
328	AAA	1	02	2012-08-05 22:59:53.481	20	2012-07-24 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
333	AAA	1	02	2012-08-05 22:59:54.11	20	2012-08-05 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
346	AAA	1	02	2012-08-05 22:59:55.855	20	2012-07-24 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
350	AAA	1	02	2012-08-05 22:59:56.379	20	2012-08-26 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
354	AAA	1	02	2012-08-05 22:59:56.873	20	2012-07-26 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
356	AAA	1	02	2012-08-05 22:59:57.114	20	2012-08-05 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
363	AAA	1	02	2012-08-05 23:01:25.939	20	2012-07-22 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
380	AAA	1	02	2012-08-05 23:01:28.987	20	2012-07-25 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
388	AAA	1	02	2012-08-05 23:01:29.995	20	2012-07-31 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
391	AAA	1	02	2012-08-05 23:01:30.357	20	2012-08-01 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
393	AAA	1	02	2012-08-05 23:01:30.605	20	2012-07-16 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
396	AAA	1	02	2012-08-05 23:01:30.967	20	2012-08-03 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
402	AAA	1	02	2012-08-05 23:01:31.708	20	2012-08-04 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
415	AAA	1	02	2012-08-05 23:01:33.318	20	2012-08-10 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
417	AAA	1	02	2012-08-05 23:01:33.555	20	2012-07-28 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
428	AAA	1	02	2012-08-05 23:01:34.926	20	2012-07-17 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
430	AAA	1	02	2012-08-05 23:01:35.164	20	2012-08-02 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
444	AAA	1	02	2012-08-05 23:01:36.872	20	2012-08-04 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
455	AAA	1	02	2012-08-05 23:01:38.212	20	2012-07-19 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
464	AAA	1	02	2012-08-05 23:01:39.388	20	2012-08-11 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
471	AAA	1	02	2012-08-05 23:01:40.231	20	2012-08-25 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
483	AAA	1	02	2012-08-05 23:02:16.363	20	2012-08-15 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
491	AAA	1	02	2012-08-05 23:02:18.26	20	2012-08-04 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
498	AAA	1	02	2012-08-05 23:02:19.181	20	2012-08-04 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
499	AAA	1	02	2012-08-05 23:02:19.313	20	2012-07-20 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
519	AAA	1	02	2012-08-05 23:02:21.788	20	2012-07-16 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
541	AAA	1	02	2012-08-05 23:02:24.594	20	2012-07-13 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
555	AAA	1	02	2012-08-05 23:02:26.333	20	2012-08-04 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
564	AAA	1	02	2012-08-05 23:02:27.58	20	2012-08-11 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
579	AAA	1	02	2012-08-05 23:02:29.578	20	2012-08-08 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
588	AAA	1	02	2012-08-05 23:05:04.754	20	2012-08-15 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
607	AAA	1	02	2012-08-05 23:05:08.319	20	2012-07-16 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
611	AAA	1	02	2012-08-05 23:05:08.838	20	2012-07-15 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
622	AAA	1	02	2012-08-05 23:05:10.218	20	2012-07-13 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
634	AAA	1	02	2012-08-05 23:05:11.717	20	2012-08-17 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
693	AAA	1	02	2012-08-05 23:05:19.08	20	2012-07-19 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
304	AAA	1	02	2012-08-05 22:59:50.548	20	2012-07-15 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
305	AAA	1	02	2012-08-05 22:59:50.671	20	2012-07-27 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
306	AAA	1	02	2012-08-05 22:59:50.789	20	2012-07-24 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
307	AAA	1	02	2012-08-05 22:59:50.91	20	2012-08-20 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
308	AAA	1	02	2012-08-05 22:59:51.03	20	2012-08-06 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
317	AAA	1	02	2012-08-05 22:59:52.111	20	2012-08-28 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
318	AAA	1	02	2012-08-05 22:59:52.229	20	2012-08-22 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
319	AAA	1	02	2012-08-05 22:59:52.357	20	2012-07-23 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
320	AAA	1	02	2012-08-05 22:59:52.486	20	2012-08-29 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
321	AAA	1	02	2012-08-05 22:59:52.606	20	2012-07-23 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
331	AAA	1	02	2012-08-05 22:59:53.845	20	2012-07-30 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
332	AAA	1	02	2012-08-05 22:59:53.981	20	2012-07-28 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
334	AAA	1	02	2012-08-05 22:59:54.243	20	2012-08-06 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
337	AAA	1	02	2012-08-05 22:59:54.712	20	2012-07-24 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
340	AAA	1	02	2012-08-05 22:59:55.115	20	2012-08-05 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
342	AAA	1	02	2012-08-05 22:59:55.372	20	2012-08-18 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
343	AAA	1	02	2012-08-05 22:59:55.486	20	2012-07-13 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
344	AAA	1	02	2012-08-05 22:59:55.613	20	2012-08-21 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
348	AAA	1	02	2012-08-05 22:59:56.109	20	2012-07-23 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
352	AAA	1	02	2012-08-05 22:59:56.641	20	2012-08-11 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
361	AAA	1	02	2012-08-05 23:01:25.701	20	2012-07-24 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
365	AAA	1	02	2012-08-05 23:01:26.186	20	2012-08-26 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
369	AAA	1	02	2012-08-05 23:01:26.718	20	2012-08-28 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
377	AAA	1	02	2012-08-05 23:01:28.613	20	2012-08-05 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
379	AAA	1	02	2012-08-05 23:01:28.864	20	2012-07-26 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
382	AAA	1	02	2012-08-05 23:01:29.228	20	2012-07-29 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
383	AAA	1	02	2012-08-05 23:01:29.366	20	2012-07-29 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
385	AAA	1	02	2012-08-05 23:01:29.623	20	2012-08-22 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
389	AAA	1	02	2012-08-05 23:01:30.117	20	2012-08-04 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
394	AAA	1	02	2012-08-05 23:01:30.726	20	2012-08-21 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
400	AAA	1	02	2012-08-05 23:01:31.479	20	2012-07-12 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
401	AAA	1	02	2012-08-05 23:01:31.593	20	2012-07-28 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
403	AAA	1	02	2012-08-05 23:01:31.823	20	2012-08-16 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
404	AAA	1	02	2012-08-05 23:01:31.938	20	2012-07-16 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
407	AAA	1	02	2012-08-05 23:01:32.303	20	2012-08-24 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
411	AAA	1	02	2012-08-05 23:01:32.815	20	2012-08-14 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
414	AAA	1	02	2012-08-05 23:01:33.19	20	2012-08-17 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
416	AAA	1	02	2012-08-05 23:01:33.432	20	2012-08-04 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
420	AAA	1	02	2012-08-05 23:01:33.913	20	2012-07-14 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
422	AAA	1	02	2012-08-05 23:01:34.15	20	2012-08-28 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
426	AAA	1	02	2012-08-05 23:01:34.656	20	2012-08-01 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
429	AAA	1	02	2012-08-05 23:01:35.046	20	2012-07-24 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
431	AAA	1	02	2012-08-05 23:01:35.283	20	2012-07-24 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
432	AAA	1	02	2012-08-05 23:01:35.428	20	2012-08-21 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
436	AAA	1	02	2012-08-05 23:01:35.909	20	2012-08-18 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
438	AAA	1	02	2012-08-05 23:01:36.179	20	2012-07-16 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
443	AAA	1	02	2012-08-05 23:01:36.757	20	2012-08-27 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
446	AAA	1	02	2012-08-05 23:01:37.109	20	2012-08-18 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
447	AAA	1	02	2012-08-05 23:01:37.236	20	2012-07-14 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
450	AAA	1	02	2012-08-05 23:01:37.62	20	2012-08-27 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
451	AAA	1	02	2012-08-05 23:01:37.745	20	2012-08-13 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
335	AAA	1	02	2012-08-05 22:59:54.384	20	2012-08-15 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
339	AAA	1	02	2012-08-05 22:59:54.998	20	2012-07-16 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
349	AAA	1	02	2012-08-05 22:59:56.241	20	2012-07-25 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
358	AAA	1	02	2012-08-05 22:59:57.354	20	2012-08-15 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
366	AAA	1	02	2012-08-05 23:01:26.317	20	2012-07-23 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
375	AAA	1	02	2012-08-05 23:01:28.375	20	2012-08-03 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
390	AAA	1	02	2012-08-05 23:01:30.232	20	2012-08-01 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
397	AAA	1	02	2012-08-05 23:01:31.105	20	2012-07-27 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
421	AAA	1	02	2012-08-05 23:01:34.035	20	2012-08-16 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
433	AAA	1	02	2012-08-05 23:01:35.542	20	2012-08-05 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
437	AAA	1	02	2012-08-05 23:01:36.053	20	2012-08-17 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
440	AAA	1	02	2012-08-05 23:01:36.408	20	2012-08-09 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
442	AAA	1	02	2012-08-05 23:01:36.64	20	2012-07-16 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
468	AAA	1	02	2012-08-05 23:01:39.862	20	2012-07-28 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
474	AAA	1	02	2012-08-05 23:02:15.216	20	2012-08-16 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
492	AAA	1	02	2012-08-05 23:02:18.401	20	2012-08-12 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
497	AAA	1	02	2012-08-05 23:02:19.055	20	2012-08-26 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
504	AAA	1	02	2012-08-05 23:02:19.909	20	2012-08-12 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
507	AAA	1	02	2012-08-05 23:02:20.293	20	2012-07-27 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
516	AAA	1	02	2012-08-05 23:02:21.42	20	2012-07-12 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
522	AAA	1	02	2012-08-05 23:02:22.193	20	2012-07-14 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
528	AAA	1	02	2012-08-05 23:02:22.944	20	2012-08-14 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
530	AAA	1	02	2012-08-05 23:02:23.183	20	2012-07-18 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
533	AAA	1	02	2012-08-05 23:02:23.582	20	2012-08-23 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
536	AAA	1	02	2012-08-05 23:02:23.959	20	2012-07-26 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
537	AAA	1	02	2012-08-05 23:02:24.102	20	2012-07-26 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
540	AAA	1	02	2012-08-05 23:02:24.481	20	2012-07-26 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
543	AAA	1	02	2012-08-05 23:02:24.832	20	2012-08-20 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
550	AAA	1	02	2012-08-05 23:02:25.718	20	2012-07-25 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
551	AAA	1	02	2012-08-05 23:02:25.851	20	2012-08-02 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
559	AAA	1	02	2012-08-05 23:02:26.838	20	2012-08-02 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
565	AAA	1	02	2012-08-05 23:02:27.735	20	2012-08-12 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
568	AAA	1	02	2012-08-05 23:02:28.207	20	2012-07-30 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
571	AAA	1	02	2012-08-05 23:02:28.595	20	2012-08-04 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
578	AAA	1	02	2012-08-05 23:02:29.463	20	2012-07-27 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
584	AAA	1	02	2012-08-05 23:02:30.165	20	2012-07-27 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
587	AAA	1	02	2012-08-05 23:05:04.64	20	2012-07-24 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
591	AAA	1	02	2012-08-05 23:05:05.117	20	2012-07-26 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
601	AAA	1	02	2012-08-05 23:05:07.581	20	2012-08-05 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
606	AAA	1	02	2012-08-05 23:05:08.192	20	2012-07-20 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
646	AAA	1	02	2012-08-05 23:05:13.156	20	2012-08-01 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
661	AAA	1	02	2012-08-05 23:05:15.001	20	2012-08-04 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
673	AAA	1	02	2012-08-05 23:05:16.513	20	2012-07-26 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
680	AAA	1	02	2012-08-05 23:05:17.428	20	2012-08-30 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
682	AAA	1	02	2012-08-05 23:05:17.664	20	2012-08-19 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
698	AAA	1	02	2012-08-05 23:05:19.708	20	2012-08-18 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
453	AAA	1	02	2012-08-05 23:01:37.976	20	2012-08-23 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
456	AAA	1	02	2012-08-05 23:01:38.332	20	2012-08-17 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
466	AAA	1	02	2012-08-05 23:01:39.627	20	2012-07-23 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
469	AAA	1	02	2012-08-05 23:01:39.984	20	2012-08-17 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
472	AAA	1	02	2012-08-05 23:01:40.354	20	2012-08-25 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
476	AAA	1	02	2012-08-05 23:02:15.463	20	2012-07-19 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
485	AAA	1	02	2012-08-05 23:02:16.624	20	2012-07-16 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
489	AAA	1	02	2012-08-05 23:02:18.013	20	2012-08-08 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
502	AAA	1	02	2012-08-05 23:02:19.673	20	2012-08-29 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
508	AAA	1	02	2012-08-05 23:02:20.422	20	2012-08-18 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
514	AAA	1	02	2012-08-05 23:02:21.193	20	2012-07-16 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
521	AAA	1	02	2012-08-05 23:02:22.042	20	2012-07-18 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
529	AAA	1	02	2012-08-05 23:02:23.062	20	2012-08-26 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
534	AAA	1	02	2012-08-05 23:02:23.705	20	2012-08-23 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
544	AAA	1	02	2012-08-05 23:02:24.959	20	2012-07-17 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
549	AAA	1	02	2012-08-05 23:02:25.587	20	2012-08-13 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
553	AAA	1	02	2012-08-05 23:02:26.10	20	2012-07-25 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
557	AAA	1	02	2012-08-05 23:02:26.58	20	2012-08-27 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
574	AAA	1	02	2012-08-05 23:02:28.961	20	2012-07-29 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
585	AAA	1	02	2012-08-05 23:02:30.292	20	2012-07-14 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
595	AAA	1	02	2012-08-05 23:05:05.633	20	2012-08-08 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
600	AAA	1	02	2012-08-05 23:05:07.463	20	2012-08-09 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
610	AAA	1	02	2012-08-05 23:05:08.709	20	2012-08-16 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
612	AAA	1	02	2012-08-05 23:05:08.978	20	2012-07-31 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
616	AAA	1	02	2012-08-05 23:05:09.457	20	2012-07-23 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
635	AAA	1	02	2012-08-05 23:05:11.837	20	2012-08-17 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
639	AAA	1	02	2012-08-05 23:05:12.332	20	2012-07-23 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
648	AAA	1	02	2012-08-05 23:05:13.401	20	2012-08-04 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
652	AAA	1	02	2012-08-05 23:05:13.898	20	2012-07-22 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
658	AAA	1	02	2012-08-05 23:05:14.612	20	2012-08-30 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
663	AAA	1	02	2012-08-05 23:05:15.286	20	2012-08-10 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
666	AAA	1	02	2012-08-05 23:05:15.658	20	2012-07-23 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
668	AAA	1	02	2012-08-05 23:05:15.892	20	2012-07-18 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
674	AAA	1	02	2012-08-05 23:05:16.648	20	2012-08-19 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
684	AAA	1	02	2012-08-05 23:05:17.92	20	2012-08-28 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
695	AAA	1	02	2012-08-05 23:05:19.32	20	2012-07-14 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
460	AAA	1	02	2012-08-05 23:01:38.854	20	2012-08-13 00:00:00	\N	3	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
465	AAA	1	02	2012-08-05 23:01:39.501	20	2012-08-12 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
487	AAA	1	02	2012-08-05 23:02:17.757	20	2012-08-30 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
503	AAA	1	02	2012-08-05 23:02:19.786	20	2012-08-18 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
513	AAA	1	02	2012-08-05 23:02:21.077	20	2012-08-30 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
517	AAA	1	02	2012-08-05 23:02:21.537	20	2012-07-27 00:00:00	\N	2	ID	IDR	494000.00	1	MY	MYR	163.32	6	\N	\N	\N	t	\N
531	AAA	1	02	2012-08-05 23:02:23.323	20	2012-08-17 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
539	AAA	1	02	2012-08-05 23:02:24.361	20	2012-07-25 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	f	\N
546	AAA	1	02	2012-08-05 23:02:25.208	20	2012-07-30 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
554	AAA	1	02	2012-08-05 23:02:26.216	20	2012-07-24 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
567	AAA	1	02	2012-08-05 23:02:28.093	20	2012-08-04 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
570	AAA	1	02	2012-08-05 23:02:28.467	20	2012-08-20 00:00:00	\N	1	ID	IDR	938000.00	2	MY	MYR	310.11	6	\N	\N	\N	t	\N
581	AAA	1	02	2012-08-05 23:02:29.807	20	2012-07-31 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
589	AAA	1	02	2012-08-05 23:05:04.879	20	2012-08-06 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
614	AAA	1	02	2012-08-05 23:05:09.213	20	2012-08-11 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
618	AAA	1	02	2012-08-05 23:05:09.706	20	2012-08-17 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
624	AAA	1	02	2012-08-05 23:05:10.489	20	2012-07-30 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
627	AAA	1	02	2012-08-05 23:05:10.856	20	2012-08-02 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
629	AAA	1	02	2012-08-05 23:05:11.092	20	2012-07-29 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
632	AAA	1	02	2012-08-05 23:05:11.47	20	2012-07-26 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
641	AAA	1	02	2012-08-05 23:05:12.56	20	2012-07-28 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	t	\N
645	AAA	1	02	2012-08-05 23:05:13.038	20	2012-08-10 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
651	AAA	1	02	2012-08-05 23:05:13.782	20	2012-08-21 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	t	\N
659	AAA	1	02	2012-08-05 23:05:14.741	20	2012-08-02 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
677	AAA	1	02	2012-08-05 23:05:17.065	20	2012-08-07 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
679	AAA	1	02	2012-08-05 23:05:17.308	20	2012-08-13 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
694	AAA	1	02	2012-08-05 23:05:19.196	20	2012-08-24 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	f	\N
697	AAA	1	02	2012-08-05 23:05:19.589	20	2012-07-25 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
470	AAA	1	02	2012-08-05 23:01:40.108	20	2012-07-21 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
480	AAA	1	02	2012-08-05 23:02:15.973	20	2012-07-20 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
484	AAA	1	02	2012-08-05 23:02:16.494	20	2012-08-08 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
490	AAA	1	02	2012-08-05 23:02:18.134	20	2012-07-27 00:00:00	\N	2	MY	MYR	100.00	1	ID	IDR	301461.00	7	\N	\N	\N	t	\N
500	AAA	1	02	2012-08-05 23:02:19.436	20	2012-07-25 00:00:00	\N	3	MY	MYR	150.00	2	ID	IDR	452191.50	7	\N	\N	\N	t	\N
506	AAA	1	02	2012-08-05 23:02:20.165	20	2012-08-19 00:00:00	\N	4	AU	AUD	48.75	5	ID	IDR	482261.32	5	\N	\N	\N	t	\N
511	AAA	1	02	2012-08-05 23:02:20.822	20	2012-08-22 00:00:00	\N	3	ID	IDR	10800200.00	4	AU	AUD	1091.53	4	\N	\N	\N	f	\N
524	AAA	1	02	2012-08-05 23:02:22.456	20	2012-08-18 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
527	AAA	1	02	2012-08-05 23:02:22.825	20	2012-08-14 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
535	AAA	1	02	2012-08-05 23:02:23.829	20	2012-08-11 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
558	AAA	1	02	2012-08-05 23:02:26.713	20	2012-08-08 00:00:00	\N	4	AU	AUD	50.00	5	ID	IDR	494627.00	5	\N	\N	\N	f	\N
561	AAA	1	02	2012-08-05 23:02:27.135	20	2012-08-20 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
575	AAA	1	02	2012-08-05 23:02:29.098	20	2012-08-24 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
594	AAA	1	02	2012-08-05 23:05:05.498	20	2012-07-25 00:00:00	\N	4	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
597	AAA	1	02	2012-08-05 23:05:05.881	20	2012-07-16 00:00:00	\N	3	ID	IDR	1500000.00	4	AU	AUD	151.59	4	\N	\N	\N	f	\N
604	AAA	1	02	2012-08-05 23:05:07.936	20	2012-07-13 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
609	AAA	1	02	2012-08-05 23:05:08.575	20	2012-08-03 00:00:00	\N	3	ID	IDR	869500.00	4	AU	AUD	87.87	4	\N	\N	\N	t	\N
621	AAA	1	02	2012-08-05 23:05:10.088	20	2012-08-04 00:00:00	\N	3	AU	AUD	2579.45	5	ID	IDR	25517312.30	5	\N	\N	\N	t	\N
628	AAA	1	02	2012-08-05 23:05:10.972	20	2012-08-17 00:00:00	\N	3	MY	MYR	700.00	2	ID	IDR	2110227.00	7	\N	\N	\N	f	\N
631	AAA	1	02	2012-08-05 23:05:11.342	20	2012-08-10 00:00:00	\N	1	ID	IDR	944000.00	2	MY	MYR	312.09	6	\N	\N	\N	f	\N
638	AAA	1	02	2012-08-05 23:05:12.218	20	2012-07-13 00:00:00	\N	1	MY	MYR	100.00	2	ID	IDR	301461.00	7	\N	\N	\N	f	\N
643	AAA	1	02	2012-08-05 23:05:12.794	20	2012-07-16 00:00:00	\N	2	ID	IDR	500000.00	1	MY	MYR	165.30	6	\N	\N	\N	f	\N
649	AAA	1	02	2012-08-05 23:05:13.528	20	2012-08-15 00:00:00	\N	3	ID	IDR	1489000.00	4	AU	AUD	150.48	4	\N	\N	\N	t	\N
650	AAA	1	02	2012-08-05 23:05:13.654	20	2012-07-17 00:00:00	\N	3	ID	IDR	10789200.00	4	AU	AUD	1090.42	4	\N	\N	\N	t	\N
653	AAA	1	02	2012-08-05 23:05:14.015	20	2012-07-12 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	t	\N
672	AAA	1	02	2012-08-05 23:05:16.387	20	2012-08-07 00:00:00	\N	4	AU	AUD	2580.70	5	ID	IDR	25529677.97	5	\N	\N	\N	f	\N
687	AAA	1	02	2012-08-05 23:05:18.30	20	2012-08-20 00:00:00	\N	3	ID	IDR	880500.00	4	AU	AUD	88.98	4	\N	\N	\N	f	\N
692	AAA	1	02	2012-08-05 23:05:18.965	20	2012-07-22 00:00:00	\N	3	MY	MYR	1205.00	4	ID	IDR	3632605.05	7	\N	\N	\N	f	\N
\.


--
-- Data for Name: user_permission; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY user_permission (id, value) FROM stdin;
\.


--
-- Data for Name: user_requests; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY user_requests (request_time, username, type_id, request_id, request_data, result_data) FROM stdin;
2012-08-04 19:57:02.663	\N	-1	\N	DefaultRequestBody(Some(Map(senderAmount -> List(250000), beneficiaryCountryCode -> List(AU), beneficiaryCurrencyCode -> List(AUD), senderCountryCode -> List(ID), requestId -> List(10001), agentKey -> List(A0AAA0200001), wordsToken -> List(ABCDEF123), sen	play.mvc.Results$AsyncResult@46f846df
2012-08-04 22:58:36.163	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":2500000}),None,	play.mvc.Results$AsyncResult@5f309025
2012-08-04 22:59:10.988	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":2500000}),None,	play.mvc.Results$AsyncResult@359a274f
2012-08-04 22:59:44.346	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","inquiryId":"22","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount	play.mvc.Results$AsyncResult@4e66ae7
2012-08-04 22:59:47.39	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"123456","wordsToken":"1234567","transactionId":"T0AAAID020000001","auth1":"1234"}),None,None,false)	SimpleResult(200, Map(Content-Type -> application/json; charset=utf-8))
2012-08-04 23:04:48.473	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":200000}),None,N	play.mvc.Results$AsyncResult@183648b1
2012-08-04 23:05:01.725	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":200000}),None,N	play.mvc.Results$AsyncResult@c19af16
2012-08-04 23:06:47.653	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":100000}),None,N	play.mvc.Results$AsyncResult@56d15836
2012-08-04 23:09:00.454	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":100000,"feeIncl	play.mvc.Results$AsyncResult@2bc5cd05
2012-08-04 23:09:09.02	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":100000,"feeIncl	play.mvc.Results$AsyncResult@5cbf5bb7
2012-08-04 23:09:14.438	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":100000,"feeIncl	play.mvc.Results$AsyncResult@54e902fd
2012-08-04 23:11:41.533	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":200000,"feeIncl	play.mvc.Results$AsyncResult@249a2807
2012-08-04 23:12:03.621	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":200000,"feeIncl	play.mvc.Results$AsyncResult@759a2097
2012-08-04 23:19:33.354	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"AU","senderCurrencyCode":"AUD","beneficiaryCountryCode":"ID","beneficiaryCurrencyCode":"IDR","senderAmount":2500000,"feeInc	play.mvc.Results$AsyncResult@47a34181
2012-08-04 23:19:50.474	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":2500000,"feeInc	play.mvc.Results$AsyncResult@5b5ac942
2012-08-05 17:41:29.025	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	SimpleResult(200, Map(Content-Type -> application/json; charset=utf-8))
2012-08-05 17:41:32.055	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@47ced484
2012-08-05 17:41:35.062	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4cdc5b6a
2012-08-05 17:41:38.069	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3d8c4d8e
2012-08-05 17:41:41.074	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6a8bda08
2012-08-05 17:41:44.079	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7a08c0a3
2012-08-05 17:41:47.085	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4c3db835
2012-08-05 17:41:50.143	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@251241f5
2012-08-05 17:41:53.184	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@cfed7c
2012-08-05 17:41:56.234	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2810ba97
2012-08-05 17:41:59.294	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@53c2a7f5
2012-08-05 17:42:02.333	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@78462a0c
2012-08-05 17:42:05.394	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@156e5f3e
2012-08-05 17:42:08.434	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@380febb9
2012-08-05 17:42:11.493	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@437a7f13
2012-08-05 17:42:14.534	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@29c82eaa
2012-08-05 17:42:17.574	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2521ddb8
2012-08-05 17:42:32.794	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@9ce9aa3
2012-08-05 17:42:35.854	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@14beb6
2012-08-05 17:42:38.895	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@12e37fe5
2012-08-05 17:43:33.724	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5ecc357d
2012-08-05 17:43:36.765	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1552dc6d
2012-08-05 17:43:39.784	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6ede4084
2012-08-05 17:44:52.845	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@eb6dcf9
2012-08-05 17:45:35.475	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@283dc831
2012-08-05 17:45:38.495	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4fc5a2f9
2012-08-05 17:45:41.555	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@369f0df0
2012-08-05 17:45:53.74	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@611de50
2012-08-05 17:46:05.906	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@683a412e
2012-08-05 17:48:13.387	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4c7814f
2012-08-05 17:48:31.687	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5b4bf56d
2012-08-05 17:48:34.726	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2d3d0953
2012-08-05 17:48:49.916	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@47f5d05b
2012-08-05 17:49:02.047	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2e78d8ac
2012-08-05 17:49:32.477	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@47f35aaa
2012-08-05 17:50:05.942	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@48dd7c33
2012-08-05 17:50:09.008	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@52dd1d62
2012-08-05 17:50:39.428	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1fd484c3
2012-08-05 17:50:42.468	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4c570021
2012-08-05 17:51:40.188	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2e3d787c
2012-08-05 17:52:10.629	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6b6622e6
2012-08-05 17:42:20.634	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7dcfcba6
2012-08-05 17:43:15.465	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4c7e8bf0
2012-08-05 17:43:18.483	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3385f539
2012-08-05 17:43:21.524	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6f17beff
2012-08-05 17:43:42.844	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@76c5faf5
2012-08-05 17:43:45.884	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@53aa4bb4
2012-08-05 17:43:48.924	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6bf15d27
2012-08-05 17:46:57.486	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@cc65ec
2012-08-05 17:47:49.067	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6f8e4035
2012-08-05 17:47:52.086	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4a70f11d
2012-08-05 17:47:55.146	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@12fc438b
2012-08-05 17:48:07.306	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5ee55d17
2012-08-05 17:48:43.867	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2779bd11
2012-08-05 17:48:46.873	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5fd82058
2012-08-05 17:49:59.877	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5113faf7
2012-08-05 17:50:02.937	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1fcf6aa3
2012-08-05 17:51:46.287	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@549cd641
2012-08-05 17:51:49.328	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@65a7a525
2012-08-05 17:51:52.367	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1889f191
2012-08-05 17:52:13.668	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@59737b19
2012-08-05 17:52:16.708	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6244b22e
2012-08-05 17:52:31.893	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4c418cf
2012-08-05 17:42:23.673	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@74b1896c
2012-08-05 17:42:26.694	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@673ba11c
2012-08-05 17:42:29.754	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@acf3c39
2012-08-05 17:42:41.934	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@f0f559e
2012-08-05 17:42:44.939	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1ae48504
2012-08-05 17:45:11.085	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@c24cb3
2012-08-05 17:45:14.175	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@a06514b
2012-08-05 17:47:18.786	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3f39ef5c
2012-08-05 17:47:21.826	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4f77e2b6
2012-08-05 17:47:24.866	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@67d1a6b3
2012-08-05 17:48:28.627	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@221abac1
2012-08-05 17:49:05.052	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6bfd472
2012-08-05 17:49:08.116	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@8e819b3
2012-08-05 17:49:11.158	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2520aade
2012-08-05 17:49:50.757	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@50c442db
2012-08-05 17:50:15.057	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@15f2cff0
2012-08-05 17:50:18.097	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@23536fd
2012-08-05 17:42:48.004	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@c25b3d5
2012-08-05 17:42:51.009	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1fe398a0
2012-08-05 17:43:00.224	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@f508f8
2012-08-05 17:43:03.265	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5bbc608f
2012-08-05 17:43:06.324	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3dd261e0
2012-08-05 17:44:04.144	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6fee5635
2012-08-05 17:44:07.205	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4dee4aae
2012-08-05 17:44:10.245	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@31f3f16b
2012-08-05 17:44:13.284	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@515c9cc0
2012-08-05 17:44:16.324	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@26e6d0a5
2012-08-05 17:44:19.385	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@299bc338
2012-08-05 17:44:22.424	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@18aaa1c3
2012-08-05 17:44:25.465	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@625a80df
2012-08-05 17:44:28.524	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4fbfc4a4
2012-08-05 17:44:31.567	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3f2a225b
2012-08-05 17:44:34.605	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@38938070
2012-08-05 17:44:37.664	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@13066a26
2012-08-05 17:44:40.685	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7b5b2e86
2012-08-05 17:46:08.91	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5f0ab5d
2012-08-05 17:46:11.956	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2eea6b13
2012-08-05 17:46:17.967	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@476e0993
2012-08-05 17:46:20.972	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@67099f71
2012-08-05 17:47:27.871	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@751dc8af
2012-08-05 17:48:22.546	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4ce03186
2012-08-05 17:48:25.587	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7a67ab8f
2012-08-05 17:50:45.528	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@498f3db0
2012-08-05 17:52:01.489	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2e3fb088
2012-08-05 17:52:04.549	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@fb95de0
2012-08-05 17:52:56.218	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@100a384
2012-08-05 17:42:54.054	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2fc8c0e6
2012-08-05 17:42:57.144	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1e3ec646
2012-08-05 17:43:12.424	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@10d55478
2012-08-05 17:44:55.884	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@124bec88
2012-08-05 17:44:58.935	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@292898f5
2012-08-05 17:45:01.995	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@24177697
2012-08-05 17:45:05.003	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@61ee30d2
2012-08-05 17:45:08.045	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@24b9371e
2012-08-05 17:45:17.236	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@46116f2
2012-08-05 17:46:48.386	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@76dedd5c
2012-08-05 17:46:51.426	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@40800a9e
2012-08-05 17:46:54.466	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@381bfd7
2012-08-05 17:47:43.016	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@23cb2378
2012-08-05 17:47:46.023	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@39401e69
2012-08-05 17:49:53.778	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1d9a43d7
2012-08-05 17:49:56.837	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7b37676c
2012-08-05 17:50:12.012	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@55a330c3
2012-08-05 17:50:51.607	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6791525e
2012-08-05 17:50:54.648	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@34ab5770
2012-08-05 17:50:57.688	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5aa4215
2012-08-05 17:51:00.728	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@768dbb49
2012-08-05 17:51:03.768	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6679fcae
2012-08-05 17:51:31.033	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@353e9cf
2012-08-05 17:51:34.078	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@87210ec
2012-08-05 17:51:37.128	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@319bcae1
2012-08-05 17:51:58.448	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2ebaa4a4
2012-08-05 17:52:19.749	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@726b37ad
2012-08-05 17:52:22.808	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6b3782c3
2012-08-05 17:52:37.964	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6be05ef2
2012-08-05 17:52:50.118	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6faa7dbc
2012-08-05 17:52:53.178	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@29d52a29
2012-08-05 17:43:09.365	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@369ca84f
2012-08-05 17:43:24.564	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1e677aca
2012-08-05 17:43:27.624	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5a182e84
2012-08-05 17:43:54.994	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6a0c282b
2012-08-05 17:45:20.275	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@541d4bce
2012-08-05 17:45:23.295	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1c057a1e
2012-08-05 17:45:26.355	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@17965791
2012-08-05 17:46:14.961	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7efeedca
2012-08-05 17:46:30.106	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@608069b9
2012-08-05 17:46:33.148	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@609ba29e
2012-08-05 17:46:36.206	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@122507c2
2012-08-05 17:47:33.921	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@677951c9
2012-08-05 17:47:36.966	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@615fe0ec
2012-08-05 17:47:39.971	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@364f2b6c
2012-08-05 17:48:37.787	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6be7ed2d
2012-08-05 17:48:40.826	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@42e21073
2012-08-05 17:48:58.982	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@715be530
2012-08-05 17:49:14.177	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@14ed6010
2012-08-05 17:49:17.218	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4b5bc191
2012-08-05 17:50:21.138	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6a566f08
2012-08-05 17:51:09.847	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@49e1d547
2012-08-05 17:51:24.983	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@27cce278
2012-08-05 17:51:28.028	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@38bc9c25
2012-08-05 17:52:25.848	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@ffeaa19
2012-08-05 17:52:28.888	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@44865ee
2012-08-05 17:52:41.008	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7038ce7b
2012-08-05 17:43:30.687	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@797f81fe
2012-08-05 17:46:42.286	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2eeccf6e
2012-08-05 17:46:45.326	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@200e5602
2012-08-05 17:47:00.527	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2e64e02
2012-08-05 17:47:12.706	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6f0164a6
2012-08-05 17:47:15.746	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@627b524c
2012-08-05 17:47:30.916	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@36ac1596
2012-08-05 17:47:58.186	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2f7a62cb
2012-08-05 17:48:10.347	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1ab95774
2012-08-05 17:49:20.276	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@245babce
2012-08-05 17:49:23.317	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@235b869
2012-08-05 17:49:26.377	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@786aceba
2012-08-05 17:49:29.437	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@eb5a4f6
2012-08-05 17:49:41.616	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3910af08
2012-08-05 17:49:44.677	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@588e2e94
2012-08-05 17:49:47.717	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4fc55da3
2012-08-05 17:50:24.178	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@f331de2
2012-08-05 17:50:27.247	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@d029f4
2012-08-05 17:50:30.267	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3f8c2858
2012-08-05 17:51:12.868	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7afdc015
2012-08-05 17:51:15.907	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@382226a7
2012-08-05 17:51:18.914	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5be470fd
2012-08-05 17:51:43.228	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@72e797db
2012-08-05 17:52:34.958	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@17e4be04
2012-08-05 17:52:47.078	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@216f1cbc
2012-08-05 17:43:51.929	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2a205c98
2012-08-05 17:43:57.999	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@687090a8
2012-08-05 17:44:01.054	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4ceb1c86
2012-08-05 17:44:49.805	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5f0101fb
2012-08-05 17:45:44.595	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@22b3cbc0
2012-08-05 17:45:47.656	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3e7cddcb
2012-08-05 17:45:50.696	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3db6fb5d
2012-08-05 17:48:55.976	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@63709dc6
2012-08-05 17:51:06.788	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@43050e86
2012-08-05 17:51:21.978	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@133c0a8a
2012-08-05 17:44:43.724	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1d9f0336
2012-08-05 17:44:46.765	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7c75b5b9
2012-08-05 17:45:29.395	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@aaf8eaa
2012-08-05 17:45:32.435	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@70ad2036
2012-08-05 17:45:56.765	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1adccdc5
2012-08-05 17:45:59.826	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@26b2e7b4
2012-08-05 17:46:02.866	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6c7a0a57
2012-08-05 17:46:24.036	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@14325ad8
2012-08-05 17:46:27.043	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5f348e2f
2012-08-05 17:46:39.246	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@172d40de
2012-08-05 17:47:03.566	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3e898802
2012-08-05 17:47:06.606	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@622f599d
2012-08-05 17:47:09.646	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6f092e60
2012-08-05 17:48:01.226	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3889c1ee
2012-08-05 17:48:04.266	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@740355a4
2012-08-05 17:48:16.427	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3bc79148
2012-08-05 17:48:19.486	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@77d7141e
2012-08-05 17:48:52.921	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5526edd1
2012-08-05 17:49:35.517	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5235b55c
2012-08-05 17:49:38.557	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@755ee1ed
2012-08-05 17:50:33.328	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5601cb79
2012-08-05 17:50:36.368	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@124022f3
2012-08-05 17:50:48.568	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4e9ff7c0
2012-08-05 17:51:55.409	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5d07939f
2012-08-05 17:52:07.588	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@48179cc2
2012-08-05 17:52:44.014	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5dd72c40
2012-08-05 18:56:47.893	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@22e5b6b0
2012-08-05 18:56:50.901	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5a7f56f8
2012-08-05 18:56:53.911	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@53b02da
2012-08-05 18:56:56.917	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@409aec9d
2012-08-05 18:56:59.923	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@567e768e
2012-08-05 18:57:02.931	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@53f61628
2012-08-05 18:57:05.996	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@26e7ee9f
2012-08-05 18:57:09.016	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@f7fb9e0
2012-08-05 18:57:12.076	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@48dd639c
2012-08-05 18:57:15.081	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6e28d8da
2012-08-05 18:57:18.126	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6a4f0359
2012-08-05 18:57:21.131	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@482c7179
2012-08-05 18:57:24.166	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3df161ac
2012-08-05 18:57:27.174	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2ffd46cc
2012-08-05 18:57:30.216	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@451d871
2012-08-05 18:57:33.222	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@42d6f8f6
2012-08-05 18:57:36.287	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1d974d9
2012-08-05 18:57:39.326	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@145a96f8
2012-08-05 18:58:55.367	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6c0da9cc
2012-08-05 18:59:41.008	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6645d9b1
2012-08-05 18:59:47.09	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@40b7522c
2012-08-05 19:00:02.268	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2919c2af
2012-08-05 19:01:21.32	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@36fb8c7a
2012-08-05 19:01:24.38	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@725d9aa7
2012-08-05 19:01:36.56	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@52194e8b
2012-08-05 19:01:39.60	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@689d00c9
2012-08-05 19:01:42.661	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@53c3fdfb
2012-08-05 19:01:45.70	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4de3a949
2012-08-05 19:02:52.71	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4a349f8a
2012-08-05 19:02:55.771	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4ece3b01
2012-08-05 19:03:32.332	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4c7b8922
2012-08-05 19:03:53.632	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4395cbcb
2012-08-05 19:03:56.692	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@24c6af24
2012-08-05 19:04:54.413	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@49b35e79
2012-08-05 19:05:06.612	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@488f612f
2012-08-05 19:05:18.773	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@72fa3f1b
2012-08-05 19:05:52.178	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@57a65cf1
2012-08-05 19:06:13.004	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2f13f599
2012-08-05 19:06:16.044	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3b8628c8
2012-08-05 19:06:34.335	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@24e55d75
2012-08-05 19:06:46.494	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2c90f71b
2012-08-05 19:08:14.706	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	SimpleResult(200, Map(Content-Type -> application/json; charset=utf-8))
2012-08-05 18:57:42.387	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6579b32a
2012-08-05 18:57:54.586	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1828b78a
2012-08-05 19:00:05.308	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3b0def13
2012-08-05 19:00:08.349	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@51eef342
2012-08-05 19:00:54.009	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@65ab90e2
2012-08-05 19:02:58.812	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@56b77265
2012-08-05 19:03:35.371	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@49d93fe7
2012-08-05 19:03:47.531	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5e91a191
2012-08-05 19:04:05.831	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7f42ea9b
2012-08-05 19:06:19.104	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@d8e1916
2012-08-05 19:06:37.375	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@27c1e949
2012-08-05 19:07:10.73	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@34a17d9
2012-08-05 19:07:22.936	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1bb2f524
2012-08-05 19:07:25.997	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4ebfa97b
2012-08-05 19:07:35.156	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3a2f4ac0
2012-08-05 19:07:47.336	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@69cccddf
2012-08-05 19:07:50.395	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@16c701cb
2012-08-05 18:57:45.427	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@683bf9ff
2012-08-05 18:57:57.627	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@873930e
2012-08-05 18:58:00.687	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@72ef33ad
2012-08-05 18:58:21.957	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6e7c6928
2012-08-05 18:58:25.018	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@28ea86ad
2012-08-05 18:58:46.203	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@46a7041a
2012-08-05 18:58:58.408	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@10139edf
2012-08-05 18:59:01.447	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@54fe71ff
2012-08-05 18:59:04.487	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6fb525d8
2012-08-05 18:59:07.548	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@655d2b5e
2012-08-05 18:59:10.588	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@36726b5b
2012-08-05 18:59:13.628	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@23451c74
2012-08-05 18:59:25.788	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7047da9b
2012-08-05 19:01:09.165	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@ade3e9
2012-08-05 19:01:48.76	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4f1b95f
2012-08-05 19:01:51.80	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@519f8603
2012-08-05 19:01:54.84	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3cc863dd
2012-08-05 19:02:07.02	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@164a40a0
2012-08-05 19:02:28.31	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@771b3435
2012-08-05 19:02:31.371	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@44477156
2012-08-05 19:03:07.971	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@441626bf
2012-08-05 19:04:17.972	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@63afe611
2012-08-05 19:04:51.376	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@353180dc
2012-08-05 19:05:49.173	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@76c4db49
2012-08-05 19:06:09.945	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6c48326b
2012-08-05 19:06:28.255	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@19dc2481
2012-08-05 19:06:49.536	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@12e37431
2012-08-05 19:07:01.656	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2f6aa016
2012-08-05 19:07:44.296	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7d75c05
2012-08-05 19:08:11.663	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@48a5d54e
2012-08-05 18:57:48.487	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4ad9cb27
2012-08-05 18:57:51.527	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1b8195b3
2012-08-05 18:58:03.726	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@76a11b29
2012-08-05 18:58:37.147	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@9f57de9
2012-08-05 18:58:40.152	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@50b7ad1a
2012-08-05 18:58:43.198	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@787be75d
2012-08-05 18:59:50.148	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6e7d8dfb
2012-08-05 18:59:53.155	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@64b825d4
2012-08-05 18:59:56.198	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@76653b8e
2012-08-05 19:00:26.63	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@37e0f872
2012-08-05 19:00:38.789	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@9dba6e2
2012-08-05 19:00:41.828	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2dcf4363
2012-08-05 19:03:38.412	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@22d03fac
2012-08-05 19:03:50.592	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@281b4cff
2012-08-05 19:04:08.872	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@557555b8
2012-08-05 19:04:30.123	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5e7e6ceb
2012-08-05 19:05:12.692	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1d96bf64
2012-08-05 19:05:30.973	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@33b76edf
2012-08-05 19:05:43.123	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7d7ca0f5
2012-08-05 19:05:46.128	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4c1aa2e9
2012-08-05 19:06:40.415	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@74a7ef62
2012-08-05 19:07:04.66	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@84cad6e
2012-08-05 19:07:32.118	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3d78cd7b
2012-08-05 19:07:59.536	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6bf053f
2012-08-05 18:58:06.786	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@70c1a0a6
2012-08-05 18:58:15.876	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7ea742a1
2012-08-05 18:58:18.917	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@73cd15da
2012-08-05 19:00:47.91	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@632b6836
2012-08-05 19:00:50.949	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3ba8be19
2012-08-05 19:02:13.10	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1e8f46a6
2012-08-05 19:02:25.24	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@66e0e0e2
2012-08-05 19:03:01.871	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2e73a403
2012-08-05 19:03:20.171	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@566adf7c
2012-08-05 19:03:23.176	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2e447a24
2012-08-05 19:03:26.241	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@725122f2
2012-08-05 19:04:11.912	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3bef1474
2012-08-05 19:04:21.032	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@220a44bf
2012-08-05 19:04:33.184	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6291bbb9
2012-08-05 19:05:15.697	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@43c332b8
2012-08-05 19:06:07.403	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@59ceb2fe
2012-08-05 19:06:31.314	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4fc063f6
2012-08-05 19:06:43.455	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2998cbaa
2012-08-05 19:06:55.606	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@c12241e
2012-08-05 19:07:07.725	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@a85aa40
2012-08-05 18:58:09.827	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3e46c3ff
2012-08-05 18:58:28.057	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@114bc6a3
2012-08-05 18:58:49.247	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@712fe0c0
2012-08-05 18:58:52.307	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@56618102
2012-08-05 18:59:28.828	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7782d113
2012-08-05 18:59:31.888	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2b1f80c3
2012-08-05 18:59:34.928	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@14828232
2012-08-05 18:59:37.968	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@58da88fb
2012-08-05 18:59:44.049	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@327df0b7
2012-08-05 19:01:00.089	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@b5be8ff
2012-08-05 19:01:03.094	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3af89e13
2012-08-05 19:01:06.16	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3380e475
2012-08-05 19:01:18.28	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6f8a8b32
2012-08-05 19:01:57.88	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7202dc8c
2012-08-05 19:02:37.45	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6f4fb163
2012-08-05 19:03:04.911	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3ea4480d
2012-08-05 19:03:59.732	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4fa76be4
2012-08-05 19:04:27.116	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@190083e
2012-08-05 19:04:42.221	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@53a15ae7
2012-08-05 19:04:45.242	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@41990d1a
2012-08-05 19:04:48.292	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@a6a2534
2012-08-05 19:05:00.513	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5735b14d
2012-08-05 19:05:03.573	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@558e0793
2012-08-05 19:05:24.873	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@8d6a3d1
2012-08-05 19:05:58.248	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@263ee73b
2012-08-05 19:06:01.304	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@41f4ec39
2012-08-05 19:07:13.806	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@61fc61a7
2012-08-05 19:08:02.597	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7491616e
2012-08-05 18:58:12.831	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@78615c8b
2012-08-05 18:59:16.648	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6a1dbc8b
2012-08-05 18:59:19.688	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7cdd4f00
2012-08-05 18:59:22.728	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4aaa045f
2012-08-05 19:00:44.849	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@202b723c
2012-08-05 19:00:57.049	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5fa23485
2012-08-05 19:01:15.216	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@31431174
2012-08-05 19:01:27.42	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@590b9bcf
2012-08-05 19:01:30.48	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2ffb0fc2
2012-08-05 19:01:33.52	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5f677970
2012-08-05 19:02:03.98	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@32154dc9
2012-08-05 19:02:34.411	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7c6aa5ee
2012-08-05 19:03:11.011	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7a407909
2012-08-05 19:03:14.071	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7fb8a6af
2012-08-05 19:03:44.492	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5ba1cdb
2012-08-05 19:04:02.793	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@662077b6
2012-08-05 19:04:14.932	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@778ebb11
2012-08-05 19:04:36.189	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5956d3a9
2012-08-05 19:04:57.473	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@181b1f8a
2012-08-05 19:05:09.652	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1d48085
2012-08-05 19:05:21.813	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6f933da1
2012-08-05 19:06:04.344	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@256a4d0a
2012-08-05 19:06:58.611	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@bed44a5
2012-08-05 19:07:29.036	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@76022cac
2012-08-05 19:07:41.236	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@37b2fdc6
2012-08-05 19:07:53.436	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3a71ad88
2012-08-05 18:58:31.118	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@31df58ee
2012-08-05 18:58:34.124	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4aec078b
2012-08-05 18:59:59.204	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1d120be4
2012-08-05 19:00:11.388	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4902f4c3
2012-08-05 19:00:14.429	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3bd92abb
2012-08-05 19:00:32.689	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4cc914b6
2012-08-05 19:00:35.749	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@123b6177
2012-08-05 19:01:12.211	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1e3337df
2012-08-05 19:02:10.04	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5739ad1b
2012-08-05 19:02:40.491	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5f9caedd
2012-08-05 19:02:43.57	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@b704be4
2012-08-05 19:02:46.611	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@68024823
2012-08-05 19:02:49.671	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@796e33ac
2012-08-05 19:03:17.111	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@69af691b
2012-08-05 19:03:29.291	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@65a2327a
2012-08-05 19:03:41.451	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@72d53513
2012-08-05 19:04:24.072	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4b623f3e
2012-08-05 19:04:39.215	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@51b7ecdb
2012-08-05 19:05:34.013	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@64056248
2012-08-05 19:05:37.043	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@16ade920
2012-08-05 19:06:25.205	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2142b533
2012-08-05 19:07:38.195	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@19c29f4
2012-08-05 19:07:56.496	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5db533b0
2012-08-05 19:08:05.636	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@e38e978
2012-08-05 19:00:17.489	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7b50e804
2012-08-05 19:00:20.528	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2f4bd4d1
2012-08-05 19:00:23.588	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@318955ea
2012-08-05 19:00:29.649	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@69b95c3a
2012-08-05 19:02:00.94	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@26b084a6
2012-08-05 19:02:16.107	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2dac2cb7
2012-08-05 19:02:19.17	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@583384c0
2012-08-05 19:02:22.175	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@38ad8817
2012-08-05 19:05:27.912	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@750d3126
2012-08-05 19:05:40.082	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@24456977
2012-08-05 19:05:55.243	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1a996ff
2012-08-05 19:06:22.145	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4ea10ca8
2012-08-05 19:06:52.541	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3e587a0
2012-08-05 19:07:16.836	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@b354f7e
2012-08-05 19:07:19.896	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@75ffcdd7
2012-08-05 19:08:08.656	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@74a7e4d9
2012-08-05 22:40:53.061	\N	-1	\N	DefaultRequestBody(None,None,None,Some({"agentKey":"A0AAA0200001","requestId":"10001","wordsToken":"ABCDEF123","senderCountryCode":"ID","senderCurrencyCode":"IDR","beneficiaryCountryCode":"AU","beneficiaryCurrencyCode":"AUD","senderAmount":250000}),None,N	play.mvc.Results$AsyncResult@2f6728ec
2012-08-05 22:59:43.586	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@28de323b
2012-08-05 22:59:46.602	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1ac7b077
2012-08-05 22:59:49.607	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@25f67136
2012-08-05 22:59:52.613	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@10124a94
2012-08-05 22:59:55.631	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@417cc8df
2012-08-05 22:59:58.642	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4b52c4ce
2012-08-05 23:00:01.679	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@26917854
2012-08-05 23:00:04.70	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1f800b16
2012-08-05 23:00:07.759	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7f83698f
2012-08-05 23:00:10.799	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@56e31774
2012-08-05 23:00:13.839	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@257f2ad0
2012-08-05 23:00:16.939	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6b391509
2012-08-05 23:00:19.978	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@13216ee9
2012-08-05 23:00:23.039	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5ee465ea
2012-08-05 23:00:26.078	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@72325393
2012-08-05 23:00:29.082	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@437668f
2012-08-05 23:00:32.198	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@317bee01
2012-08-05 23:00:35.24	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@34ef98ac
2012-08-05 23:00:38.298	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4e43df5
2012-08-05 23:00:41.348	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6c5a8d3b
2012-08-05 23:00:44.388	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@52d0fd7b
2012-08-05 23:00:47.429	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6fe33a04
2012-08-05 23:00:50.488	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1e1e306f
2012-08-05 23:00:53.528	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2224ece4
2012-08-05 23:00:56.567	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4d846db5
2012-08-05 23:00:59.588	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@a069435
2012-08-05 23:01:33.076	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@408f3be4
2012-08-05 23:02:30.69	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@158412e1
2012-08-05 23:02:33.735	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4317f2b1
2012-08-05 23:02:36.775	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@f50dbe6
2012-08-05 23:03:22.504	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6f4f5643
2012-08-05 23:06:15.811	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@406a67ab
2012-08-05 23:06:49.26	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@62afc459
2012-08-05 23:06:52.281	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@795525a1
2012-08-05 23:06:55.341	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@183a9d7f
2012-08-05 23:06:58.381	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3236eb66
2012-08-05 23:07:01.441	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@64e79f63
2012-08-05 23:07:47.056	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@24544c70
2012-08-05 23:07:50.119	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3714aca8
2012-08-05 23:08:08.419	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6e5a606
2012-08-05 23:08:50.929	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1d690fa6
2012-08-05 23:08:53.935	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7615b26b
2012-08-05 23:01:02.648	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@23652209
2012-08-05 23:01:20.987	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@554ce931
2012-08-05 23:01:23.992	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@446b0224
2012-08-05 23:01:57.387	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@410ba083
2012-08-05 23:02:00.427	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@17e21e3
2012-08-05 23:04:02.049	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@50c94377
2012-08-05 23:04:05.073	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6dbd726
2012-08-05 23:04:08.08	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7e5f1c5e
2012-08-05 23:05:11.959	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@33f0c408
2012-08-05 23:05:24.042	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6dc7cb10
2012-08-05 23:05:27.082	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6ae88006
2012-08-05 23:06:00.532	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6855b715
2012-08-05 23:06:30.99	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2c61514f
2012-08-05 23:07:04.48	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@72314fd4
2012-08-05 23:07:13.652	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@110ca001
2012-08-05 23:07:16.691	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3c36f7e6
2012-08-05 23:07:28.86	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5adfb9cd
2012-08-05 23:07:53.18	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@65435faa
2012-08-05 23:09:12.084	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@a2f1e34
2012-08-05 23:09:15.129	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@44022631
2012-08-05 23:09:18.159	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@89525d0
2012-08-05 23:09:45.617	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@29523ccf
2012-08-05 23:09:48.668	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@52ab94fd
2012-08-05 23:01:05.688	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4418f61b
2012-08-05 23:01:08.768	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@36c8e545
2012-08-05 23:01:11.807	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@15f445b7
2012-08-05 23:01:27.057	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3b0675c3
2012-08-05 23:01:42.094	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6c3315f0
2012-08-05 23:01:45.187	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@39b909d6
2012-08-05 23:01:48.246	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@63e87ba1
2012-08-05 23:02:39.835	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7138dd3f
2012-08-05 23:02:42.875	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6a37292b
2012-08-05 23:02:52.034	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@366a88bb
2012-08-05 23:03:13.324	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@49ca49b9
2012-08-05 23:04:17.283	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4655c819
2012-08-05 23:04:20.323	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@43d7a5a
2012-08-05 23:04:23.364	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@53635ac1
2012-08-05 23:04:26.383	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7b66258a
2012-08-05 23:05:14.966	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@49f92de5
2012-08-05 23:05:30.122	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6a2a55df
2012-08-05 23:05:45.287	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@696be535
2012-08-05 23:05:48.331	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@63001872
2012-08-05 23:05:57.471	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2823796f
2012-08-05 23:06:06.651	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@18020b52
2012-08-05 23:06:09.711	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2a31ac15
2012-08-05 23:06:34.031	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@41e9e2ac
2012-08-05 23:06:37.071	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4efce9a2
2012-08-05 23:07:10.545	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@a7393a2
2012-08-05 23:07:19.73	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@46192223
2012-08-05 23:07:22.77	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@71df8783
2012-08-05 23:07:31.92	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6a086b19
2012-08-05 23:07:34.96	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5f0900d2
2012-08-05 23:07:56.24	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3d877a44
2012-08-05 23:08:32.721	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4f430ea5
2012-08-05 23:08:56.999	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@79d38dae
2012-08-05 23:09:00.004	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7dbc244d
2012-08-05 23:09:39.518	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5f104764
2012-08-05 23:09:42.558	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2899010e
2012-08-05 23:09:51.727	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@19a663bb
2012-08-05 23:09:54.747	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@69edfb80
2012-08-05 23:09:57.808	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6ef3cd97
2012-08-05 23:01:14.867	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3c9e5e7e
2012-08-05 23:01:17.927	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@57ccc11d
2012-08-05 23:01:36.08	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2a73963b
2012-08-05 23:02:15.655	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@24762cda
2012-08-05 23:02:21.673	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5e30e1c0
2012-08-05 23:02:24.679	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@739ffd2f
2012-08-05 23:02:27.685	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@f1bb79c
2012-08-05 23:03:25.544	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1de199c1
2012-08-05 23:03:28.586	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@597baed8
2012-08-05 23:03:40.764	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@55d44a0c
2012-08-05 23:03:43.784	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@16f29cff
2012-08-05 23:06:03.592	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@d737301
2012-08-05 23:08:05.36	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4b065b63
2012-08-05 23:08:20.52	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@266f4689
2012-08-05 23:08:23.559	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@40b07205
2012-08-05 23:08:35.759	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@51854dc0
2012-08-05 23:08:38.778	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7c14dcba
2012-08-05 23:10:03.908	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6649373a
2012-08-05 23:01:30.062	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1e47f9d
2012-08-05 23:01:51.286	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6eb5b17c
2012-08-05 23:01:54.346	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@83bed72
2012-08-05 23:02:18.664	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@43b34d17
2012-08-05 23:02:55.04	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@60bf7a4d
2012-08-05 23:03:16.385	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@67e1a8e
2012-08-05 23:03:19.424	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4249ccd1
2012-08-05 23:03:34.664	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@323f6c10
2012-08-05 23:03:37.724	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@82c61be
2012-08-05 23:03:59.044	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@633c2a46
2012-08-05 23:04:41.583	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5c0e886e
2012-08-05 23:04:44.622	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6ca73f4b
2012-08-05 23:04:47.663	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@54c6552a
2012-08-05 23:04:59.842	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@19e5ec57
2012-08-05 23:05:33.161	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4fb05755
2012-08-05 23:05:36.202	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1120be46
2012-08-05 23:05:51.371	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@54f28c1f
2012-08-05 23:05:54.432	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@21668d9
2012-08-05 23:07:07.54	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2a6cd712
2012-08-05 23:08:11.423	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@c4fa1cd
2012-08-05 23:08:14.46	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@30e4698
2012-08-05 23:08:26.619	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@74a02b5e
2012-08-05 23:08:29.659	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@44875666
2012-08-05 23:09:33.42	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5ab94819
2012-08-05 23:09:36.458	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@43978a17
2012-08-05 23:01:39.087	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2d1484a7
2012-08-05 23:02:03.476	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7f5f987f
2012-08-05 23:02:45.935	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@252c29c2
2012-08-05 23:02:48.976	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@d18ffce
2012-08-05 23:04:11.123	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2b2a653e
2012-08-05 23:04:14.224	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@455ae6c7
2012-08-05 23:04:38.563	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@4ae28886
2012-08-05 23:05:02.882	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7d2149c5
2012-08-05 23:05:20.981	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@69fafb25
2012-08-05 23:06:18.851	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2113727f
2012-08-05 23:06:21.871	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@35c3599d
2012-08-05 23:06:24.93	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1f85555c
2012-08-05 23:06:27.936	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@e99fb6
2012-08-05 23:07:59.281	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1b2d6c22
2012-08-05 23:08:17.479	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@277f0e53
2012-08-05 23:09:06.053	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@48ada913
2012-08-05 23:09:09.079	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@533bd1d3
2012-08-05 23:09:21.218	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7bfabad8
2012-08-05 23:09:24.259	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@463dacfa
2012-08-05 23:02:06.496	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@358510b3
2012-08-05 23:02:09.536	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7d6904c4
2012-08-05 23:02:12.596	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@24177336
2012-08-05 23:03:01.145	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6ff64801
2012-08-05 23:03:04.205	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@2ba2f40
2012-08-05 23:03:07.245	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@3fd6d1b0
2012-08-05 23:03:46.844	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@42264dde
2012-08-05 23:03:49.884	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@73fb1a39
2012-08-05 23:03:52.944	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1e3c1a28
2012-08-05 23:03:55.985	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@75ebcd54
2012-08-05 23:04:29.443	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6141e734
2012-08-05 23:04:32.483	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@faf73d2
2012-08-05 23:04:35.522	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7805cbba
2012-08-05 23:04:50.682	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1a445dcf
2012-08-05 23:04:53.742	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@161bb7fe
2012-08-05 23:04:56.782	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@490439a3
2012-08-05 23:05:05.942	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7b725d6d
2012-08-05 23:05:08.954	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6ed0cc34
2012-08-05 23:05:17.975	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1bff8e70
2012-08-05 23:05:39.241	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7bb3be4f
2012-08-05 23:05:42.282	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@272ab5b8
2012-08-05 23:07:25.83	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5e9ec769
2012-08-05 23:07:38	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5db5889c
2012-08-05 23:07:41.005	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@57947a96
2012-08-05 23:07:44.051	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7b768719
2012-08-05 23:08:02.34	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@6841a9fa
2012-08-05 23:08:47.879	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@39c026b2
2012-08-05 23:09:03.048	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@373f9379
2012-08-05 23:09:27.318	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@5b79c06e
2012-08-05 23:09:30.359	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7b336548
2012-08-05 23:10:00.847	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@aeaed8d
2012-08-05 23:02:58.106	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@f0f7074
2012-08-05 23:03:10.284	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@1642f972
2012-08-05 23:03:31.623	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@7761c32f
2012-08-05 23:06:12.771	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@28da95c1
2012-08-05 23:06:40.075	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@20d675ec
2012-08-05 23:06:43.131	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@58363f95
2012-08-05 23:06:46.221	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@57a20888
2012-08-05 23:08:41.819	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@18b3c498
2012-08-05 23:08:44.859	\N	-1	\N	DefaultRequestBody(None,Some(RawBuffer(inMemory=0, backedByTemporaryFile=null)),None,None,None,None,false)	play.mvc.Results$AsyncResult@56165397
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY users (id, first_name, last_name, email, password, gender, address, country_code, city_id, birth_date, personal_id_type, personal_id, phone_number, postal_code, is_admin, supervisor_id, corporate_code) FROM stdin;
1	Taufik	Gilderbergh	taufik@doku.com	doku@doku.com123	F	Plaza Asia suite	ID	1	1982-01-02	\N	\N	62899000081	12050	\N	\N	DOK
2	Hafiz	Syafoedin	dodo@doku.com	123	F	Plaza Asia suite	ID	1	1982-01-02	\N	\N	62899000082	12050	\N	\N	DOK
3	Budi	Raharjo	budi@warindo.co.id	mainagent@warindo.co.id123	F	Jalan Pramuka VI No. 71B	ID	1	1980-01-02	\N	\N	62899111111	12056	\N	\N	WAR
4	Amanaro	Brin	dodo@mainagent.com	123	M	Jalan Pramuka VI No. 71B	ID	1	1980-01-02	\N	\N	62899111121	12056	\N	\N	WAR
5	Susan	Jelia	susan@warindo.co.id	supervisor@warindo.co.id123	F	Jalan Pramuka VI No. 71B	ID	1	1980-01-02	\N	\N	62899111111	12056	\N	\N	WAR
6	Sergey	Page	dodo@supervisor.com	123	F	Jalan Pramuka VI No. 71B	ID	1	1980-01-02	\N	\N	6289911231	12056	\N	\N	WAR
7	Susi	Firawati	susi@warindo.co.id	agent@warindo.co.id123	F	Jalan Pramuka VI No. 71B	ID	1	1984-05-06	\N	\N	628991123311	12056	\N	\N	WAR
8	Mark	Zuckerberg	dodo@agent.com	123	F	Jalan Pramuka VI No. 71B	ID	1	1984-05-06	\N	\N	628991123311	12056	\N	\N	WAR
\.


--
-- Data for Name: users_groups; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY users_groups (users_id, groups_id) FROM stdin;
1	1
2	1
3	21
4	21
5	31
6	31
7	36
8	36
\.


--
-- Data for Name: users_user_permission; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY users_user_permission (users_id, user_permission_id) FROM stdin;
\.


--
-- Name: pk_channels; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY channels
    ADD CONSTRAINT pk_channels PRIMARY KEY (code);


--
-- Name: pk_cities; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY cities
    ADD CONSTRAINT pk_cities PRIMARY KEY (id);


--
-- Name: pk_corporates; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY corporates
    ADD CONSTRAINT pk_corporates PRIMARY KEY (code);


--
-- Name: pk_countries; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY countries
    ADD CONSTRAINT pk_countries PRIMARY KEY (code);


--
-- Name: pk_currencies; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY currencies
    ADD CONSTRAINT pk_currencies PRIMARY KEY (code);


--
-- Name: pk_customers; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT pk_customers PRIMARY KEY (id);


--
-- Name: pk_fees; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY fees
    ADD CONSTRAINT pk_fees PRIMARY KEY (id);


--
-- Name: pk_forex; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY forex
    ADD CONSTRAINT pk_forex PRIMARY KEY (id);


--
-- Name: pk_forex_references; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY forex_references
    ADD CONSTRAINT pk_forex_references PRIMARY KEY (id);


--
-- Name: pk_forex_setting; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY forex_setting
    ADD CONSTRAINT pk_forex_setting PRIMARY KEY (id);


--
-- Name: pk_groups; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT pk_groups PRIMARY KEY (id);


--
-- Name: pk_news; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY news
    ADD CONSTRAINT pk_news PRIMARY KEY (id);


--
-- Name: pk_transaction_inquiries; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY transaction_inquiries
    ADD CONSTRAINT pk_transaction_inquiries PRIMARY KEY (id);


--
-- Name: pk_transactions; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT pk_transactions PRIMARY KEY (id);


--
-- Name: pk_user_permission; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY user_permission
    ADD CONSTRAINT pk_user_permission PRIMARY KEY (id);


--
-- Name: pk_users; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);


--
-- Name: pk_users_groups; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY users_groups
    ADD CONSTRAINT pk_users_groups PRIMARY KEY (users_id, groups_id);


--
-- Name: pk_users_user_permission; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY users_user_permission
    ADD CONSTRAINT pk_users_user_permission PRIMARY KEY (users_id, user_permission_id);


--
-- Name: play_evolutions_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY play_evolutions
    ADD CONSTRAINT play_evolutions_pkey PRIMARY KEY (id);


--
-- Name: ix_cities_country_1; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_cities_country_1 ON cities USING btree (country_code);


--
-- Name: ix_countries_currency_2; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_countries_currency_2 ON countries USING btree (currency_code);


--
-- Name: ix_customers_agent_4; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_customers_agent_4 ON customers USING btree (agent_id);


--
-- Name: ix_customers_city_3; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_customers_city_3 ON customers USING btree (city_id);


--
-- Name: ix_fees_beneficiarycountry_7; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_fees_beneficiarycountry_7 ON fees USING btree (beneficiary_country_code);


--
-- Name: ix_fees_corporate_5; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_fees_corporate_5 ON fees USING btree (corporate_code);


--
-- Name: ix_fees_currency_8; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_fees_currency_8 ON fees USING btree (currency_code);


--
-- Name: ix_fees_domaincorporate_9; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_fees_domaincorporate_9 ON fees USING btree (domain_corporate_code);


--
-- Name: ix_fees_sendercountry_6; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_fees_sendercountry_6 ON fees USING btree (sender_country_code);


--
-- Name: ix_forex_destination_11; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_forex_destination_11 ON forex USING btree (destination_code);


--
-- Name: ix_forex_origin_10; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_forex_origin_10 ON forex USING btree (origin_code);


--
-- Name: ix_forex_references_forex_12; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_forex_references_forex_12 ON forex_references USING btree (forex_id);


--
-- Name: ix_transaction_fees_currency_26; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transaction_fees_currency_26 ON transaction_fees USING btree (currency_code);


--
-- Name: ix_transaction_fees_domaincor_27; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transaction_fees_domaincor_27 ON transaction_fees USING btree (domain_corporate_code);


--
-- Name: ix_transaction_fees_fee_25; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transaction_fees_fee_25 ON transaction_fees USING btree (fee_id);


--
-- Name: ix_transaction_fees_transacti_24; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transaction_fees_transacti_24 ON transaction_fees USING btree (transaction_id);


--
-- Name: ix_transaction_inquiries_tran_29; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transaction_inquiries_tran_29 ON transaction_inquiries USING btree (transaction_id);


--
-- Name: ix_transaction_inquiries_user_28; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transaction_inquiries_user_28 ON transaction_inquiries USING btree (user_id);


--
-- Name: ix_transactions_beneficiary_19; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_beneficiary_19 ON transactions USING btree (beneficiary_id);


--
-- Name: ix_transactions_beneficiaryci_23; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_beneficiaryci_23 ON transactions USING btree (beneficiary_city_id);


--
-- Name: ix_transactions_beneficiaryco_20; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_beneficiaryco_20 ON transactions USING btree (beneficiary_country_code);


--
-- Name: ix_transactions_beneficiarycu_21; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_beneficiarycu_21 ON transactions USING btree (beneficiary_currency_code);


--
-- Name: ix_transactions_channel_15; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_channel_15 ON transactions USING btree (channel_code);


--
-- Name: ix_transactions_corporate_13; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_corporate_13 ON transactions USING btree (corporate_code);


--
-- Name: ix_transactions_forexreferenc_22; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_forexreferenc_22 ON transactions USING btree (forex_reference_id);


--
-- Name: ix_transactions_sender_16; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_sender_16 ON transactions USING btree (sender_id);


--
-- Name: ix_transactions_sendercountry_17; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_sendercountry_17 ON transactions USING btree (sender_country_code);


--
-- Name: ix_transactions_sendercurrenc_18; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_sendercurrenc_18 ON transactions USING btree (sender_currency_code);


--
-- Name: ix_transactions_user_14; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_transactions_user_14 ON transactions USING btree (user_id);


--
-- Name: ix_users_city_31; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_users_city_31 ON users USING btree (city_id);


--
-- Name: ix_users_corporate_33; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_users_corporate_33 ON users USING btree (corporate_code);


--
-- Name: ix_users_country_30; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_users_country_30 ON users USING btree (country_code);


--
-- Name: ix_users_supervisor_32; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX ix_users_supervisor_32 ON users USING btree (supervisor_id);


--
-- Name: fk_cities_country_1; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY cities
    ADD CONSTRAINT fk_cities_country_1 FOREIGN KEY (country_code) REFERENCES countries(code);


--
-- Name: fk_countries_currency_2; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY countries
    ADD CONSTRAINT fk_countries_currency_2 FOREIGN KEY (currency_code) REFERENCES currencies(code);


--
-- Name: fk_customers_agent_4; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT fk_customers_agent_4 FOREIGN KEY (agent_id) REFERENCES users(id);


--
-- Name: fk_customers_city_3; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT fk_customers_city_3 FOREIGN KEY (city_id) REFERENCES cities(id);


--
-- Name: fk_fees_beneficiarycountry_7; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY fees
    ADD CONSTRAINT fk_fees_beneficiarycountry_7 FOREIGN KEY (beneficiary_country_code) REFERENCES countries(code);


--
-- Name: fk_fees_corporate_5; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY fees
    ADD CONSTRAINT fk_fees_corporate_5 FOREIGN KEY (corporate_code) REFERENCES corporates(code);


--
-- Name: fk_fees_currency_8; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY fees
    ADD CONSTRAINT fk_fees_currency_8 FOREIGN KEY (currency_code) REFERENCES currencies(code);


--
-- Name: fk_fees_domaincorporate_9; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY fees
    ADD CONSTRAINT fk_fees_domaincorporate_9 FOREIGN KEY (domain_corporate_code) REFERENCES corporates(code);


--
-- Name: fk_fees_sendercountry_6; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY fees
    ADD CONSTRAINT fk_fees_sendercountry_6 FOREIGN KEY (sender_country_code) REFERENCES countries(code);


--
-- Name: fk_forex_destination_11; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY forex
    ADD CONSTRAINT fk_forex_destination_11 FOREIGN KEY (destination_code) REFERENCES currencies(code);


--
-- Name: fk_forex_origin_10; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY forex
    ADD CONSTRAINT fk_forex_origin_10 FOREIGN KEY (origin_code) REFERENCES currencies(code);


--
-- Name: fk_forex_references_forex_12; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY forex_references
    ADD CONSTRAINT fk_forex_references_forex_12 FOREIGN KEY (forex_id) REFERENCES forex(id);


--
-- Name: fk_transaction_fees_currency_26; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transaction_fees
    ADD CONSTRAINT fk_transaction_fees_currency_26 FOREIGN KEY (currency_code) REFERENCES currencies(code);


--
-- Name: fk_transaction_fees_domaincor_27; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transaction_fees
    ADD CONSTRAINT fk_transaction_fees_domaincor_27 FOREIGN KEY (domain_corporate_code) REFERENCES corporates(code);


--
-- Name: fk_transaction_fees_fee_25; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transaction_fees
    ADD CONSTRAINT fk_transaction_fees_fee_25 FOREIGN KEY (fee_id) REFERENCES fees(id);


--
-- Name: fk_transaction_fees_transacti_24; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transaction_fees
    ADD CONSTRAINT fk_transaction_fees_transacti_24 FOREIGN KEY (transaction_id) REFERENCES transactions(id);


--
-- Name: fk_transaction_inquiries_tran_29; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transaction_inquiries
    ADD CONSTRAINT fk_transaction_inquiries_tran_29 FOREIGN KEY (transaction_id) REFERENCES transactions(id);


--
-- Name: fk_transaction_inquiries_user_28; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transaction_inquiries
    ADD CONSTRAINT fk_transaction_inquiries_user_28 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fk_transactions_beneficiary_19; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_beneficiary_19 FOREIGN KEY (beneficiary_id) REFERENCES customers(id);


--
-- Name: fk_transactions_beneficiaryci_23; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_beneficiaryci_23 FOREIGN KEY (beneficiary_city_id) REFERENCES cities(id);


--
-- Name: fk_transactions_beneficiaryco_20; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_beneficiaryco_20 FOREIGN KEY (beneficiary_country_code) REFERENCES countries(code);


--
-- Name: fk_transactions_beneficiarycu_21; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_beneficiarycu_21 FOREIGN KEY (beneficiary_currency_code) REFERENCES currencies(code);


--
-- Name: fk_transactions_channel_15; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_channel_15 FOREIGN KEY (channel_code) REFERENCES channels(code);


--
-- Name: fk_transactions_corporate_13; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_corporate_13 FOREIGN KEY (corporate_code) REFERENCES corporates(code);


--
-- Name: fk_transactions_forexreferenc_22; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_forexreferenc_22 FOREIGN KEY (forex_reference_id) REFERENCES forex_references(id);


--
-- Name: fk_transactions_sender_16; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_sender_16 FOREIGN KEY (sender_id) REFERENCES customers(id);


--
-- Name: fk_transactions_sendercountry_17; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_sendercountry_17 FOREIGN KEY (sender_country_code) REFERENCES countries(code);


--
-- Name: fk_transactions_sendercurrenc_18; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_sendercurrenc_18 FOREIGN KEY (sender_currency_code) REFERENCES currencies(code);


--
-- Name: fk_transactions_user_14; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT fk_transactions_user_14 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fk_users_city_31; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_city_31 FOREIGN KEY (city_id) REFERENCES cities(id);


--
-- Name: fk_users_corporate_33; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_corporate_33 FOREIGN KEY (corporate_code) REFERENCES corporates(code);


--
-- Name: fk_users_country_30; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_country_30 FOREIGN KEY (country_code) REFERENCES countries(code);


--
-- Name: fk_users_groups_groups_02; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY users_groups
    ADD CONSTRAINT fk_users_groups_groups_02 FOREIGN KEY (groups_id) REFERENCES groups(id);


--
-- Name: fk_users_groups_users_01; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY users_groups
    ADD CONSTRAINT fk_users_groups_users_01 FOREIGN KEY (users_id) REFERENCES users(id);


--
-- Name: fk_users_supervisor_32; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk_users_supervisor_32 FOREIGN KEY (supervisor_id) REFERENCES users(id);


--
-- Name: fk_users_user_permission_user_01; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY users_user_permission
    ADD CONSTRAINT fk_users_user_permission_user_01 FOREIGN KEY (users_id) REFERENCES users(id);


--
-- Name: fk_users_user_permission_user_02; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY users_user_permission
    ADD CONSTRAINT fk_users_user_permission_user_02 FOREIGN KEY (user_permission_id) REFERENCES user_permission(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: admin
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM doku;
GRANT ALL ON SCHEMA public TO doku;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

